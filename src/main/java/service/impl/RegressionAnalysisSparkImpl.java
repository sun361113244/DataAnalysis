package service.impl;

import entity.CorrelationAnalysis;
import entity.FeatureType;
import entity.RegressionRes;
import entity.TbColumn;
import org.apache.spark.api.java.JavaDoubleRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.mllib.regression.LinearRegressionModel;
import org.apache.spark.mllib.regression.LinearRegressionWithSGD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.springframework.stereotype.Service;
import scala.Tuple2;
import service.RegressionAnalysis;
import util.Config;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Service("RegressionAnalysisSparkImpl")
public class RegressionAnalysisSparkImpl implements RegressionAnalysis ,Serializable
{
    public List<CorrelationAnalysis> analysis(Dataset<Row> jdbcDF, List<TbColumn> tbColumns, double r2_upper)
    {
        List<CorrelationAnalysis> correlationAnalysises = new ArrayList<>();

        for(int i = 0 ;i < tbColumns.size(); i++ )
        {
            for(int j = i + 1 ;j < tbColumns.size(); j++ )
            {
                if(tbColumns.get(i).getFeatureType() == FeatureType.CONTINUOUS &&
                        tbColumns.get(j).getFeatureType() == FeatureType.CONTINUOUS)
                {
                    final int finalI = i;
                    final int finalJ = j;


                    JavaRDD<LabeledPoint> labeledPointJavaRDD = jdbcDF.javaRDD().map(
                            new Function<Row, LabeledPoint >()
                            {
                                public LabeledPoint call(Row row)
                                {
                                    LabeledPoint labeledPoint = new LabeledPoint(Double.parseDouble(row.get(finalI).toString()) ,
                                            Vectors.dense(Double.parseDouble(row.get(finalJ).toString()) , 1.0));
                                    return labeledPoint;
                                }
                            }
                    );

                    if(labeledPointJavaRDD.count() < Config.MIN_REG_DOT)
                        continue;
                    RegressionRes regressionRes = linearRegressionWithSGD(labeledPointJavaRDD , 100);

                    CorrelationAnalysis correlationAnalysis = new CorrelationAnalysis();
                    correlationAnalysis.setTb_col1(tbColumns.get(i).clone());
                    correlationAnalysis.setTb_col2(tbColumns.get(j).clone());
                    correlationAnalysis.setRegressionSumSquare(regressionRes.getSSR());
                    correlationAnalysis.setrSquare(regressionRes.getR2());
                    correlationAnalysis.setSlope(regressionRes.getWeights()[0]);
                    correlationAnalysis.setIntercept(regressionRes.getWeights()[1]);

                    if(correlationAnalysis.getrSquare() >= r2_upper)
                        correlationAnalysises.add(correlationAnalysis);
                }
            }
        }
        return correlationAnalysises;
    }

    private RegressionRes linearRegressionWithSGD(JavaRDD<LabeledPoint> labeledPointJavaRDD, int numIterations)
    {
        final LinearRegressionModel lineModel =
                LinearRegressionWithSGD.train(JavaRDD.toRDD(labeledPointJavaRDD), numIterations);

        RegressionRes regressionRes = new RegressionRes();

        double[] weights = new double[lineModel.weights().size()];
        for(int i = 0 ; i < weights.length ; i ++)
        {
            weights[i] = lineModel.weights().apply(i);
        }

        JavaRDD<Tuple2<Double , Double>> valuesAndPreds = labeledPointJavaRDD.map(
                new Function<LabeledPoint, Tuple2<Double, Double>>()
                {
                    public Tuple2<Double, Double> call(LabeledPoint labeledPoint) throws Exception
                    {
                        double prediction = lineModel.predict(labeledPoint.features());
                        return new Tuple2<>(prediction, labeledPoint.label());
                    }
                }
        );

        final double mean = new JavaDoubleRDD(valuesAndPreds.map(
                new Function<Tuple2<Double, Double>, Object>() {
                    public Object call(Tuple2<Double, Double> pair) {
                        return pair._1();
                    }
                }
        ).rdd()).mean();

        double SST = new JavaDoubleRDD(valuesAndPreds.map(
            new Function<Tuple2<Double, Double>, Object>() {
                public Object call(Tuple2<Double, Double> pair) {
                    return Math.pow(pair._2() - mean, 2.0);
                }
            }
        ).rdd()).sum();
        double SSE = new JavaDoubleRDD(valuesAndPreds.map(
                new Function<Tuple2<Double, Double>, Object>() {
                    public Object call(Tuple2<Double, Double> pair) {
                        return Math.pow(pair._1() - mean, 2.0);
                    }
                }
        ).rdd()).sum();
        double SSR = new JavaDoubleRDD(valuesAndPreds.map(
                new Function<Tuple2<Double, Double>, Object>() {
                    public Object call(Tuple2<Double, Double> pair) {
                        return Math.pow(pair._1() - pair._2(), 2.0);
                    }
                }
        ).rdd()).sum();

        double r2 = 1 - SSR / SST;
        regressionRes.setWeights(weights);
        regressionRes.setSST(SST);
        regressionRes.setSSE(SSE);
        regressionRes.setSSR(SSR);
        regressionRes.setR2(r2);

        return regressionRes;
    }
}
