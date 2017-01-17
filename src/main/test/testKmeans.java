import org.apache.commons.math3.geometry.partitioning.Region;
import org.apache.commons.math3.ml.clustering.CentroidCluster;
import org.apache.commons.math3.ml.clustering.Clusterable;
import org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer;
import org.junit.Test;
import util.JdbcUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class testKmeans
{
    public static class LocationWrapper implements Clusterable
    {
        private int key;
        private double[] points;

        public int getKey()
        {
            return key;
        }

        public void setKey(int key)
        {
            this.key = key;
        }

        public void setPoints(double[] points)
        {
            this.points = points;
        }

        @Override
        public double[] getPoint()
        {
            return points;
        }
    }
    @Test
    public void test1() throws SQLException
    {
        List<LocationWrapper> clusterInput = new ArrayList<>();
        JdbcUtil dbTools = new JdbcUtil();
        List<List<Object>> rows = dbTools.findMoreResult("select * from iris" , null);
        for(List<Object> row : rows)
        {
            if(row.get(0) != null && row.get(1) != null && row.get(2) != null && row.get(3) != null && row.get(4) != null)
            {
                LocationWrapper locationWrapper = new LocationWrapper();

                int key = Integer.parseInt(row.get(0).toString());
                double[] elem = new double[4];
                elem[0] = Double.parseDouble(row.get(1).toString());
                elem[1] = Double.parseDouble(row.get(2).toString());
                elem[2] = Double.parseDouble(row.get(3).toString());
                elem[3] = Double.parseDouble(row.get(4).toString());
                locationWrapper.setKey(key);
                locationWrapper.setPoints(elem);

                clusterInput.add(locationWrapper);
            }
        }

        KMeansPlusPlusClusterer<LocationWrapper> clusterer11 = new KMeansPlusPlusClusterer<LocationWrapper>(3 , 1000);
        List<CentroidCluster<LocationWrapper>> clusterResults11 = clusterer11.cluster(clusterInput);
            for (int i=0; i<clusterResults11.size(); i++)
            {
                System.out.println("Cluster " + i);
                for (LocationWrapper locationWrapper : clusterResults11.get(i).getPoints())
                    System.out.println(locationWrapper.getKey());
                System.out.println();
            }


        for(int k = 2;k<11 ;k++)
        {
            KMeansPlusPlusClusterer<LocationWrapper> clusterer = new KMeansPlusPlusClusterer<LocationWrapper>(k , 1000);
            List<CentroidCluster<LocationWrapper>> clusterResults = clusterer.cluster(clusterInput);
//            for (int i=0; i<clusterResults.size(); i++)
//            {
//                System.out.println("Cluster " + i);
//                for (LocationWrapper locationWrapper : clusterResults.get(i).getPoints())
//                    System.out.println(locationWrapper.getKey());
//                System.out.println();
//            }

            double sum = 0;
            for (int i=0; i<clusterResults.size(); i++)
            {
                CentroidCluster<LocationWrapper> locationWrapper = clusterResults.get(i);
                double[] center = new double[4];
                center = locationWrapper.getCenter().getPoint();

//                StringBuilder sb = new StringBuilder();
//                for(int j = 0 ;j < locationWrapper.getCenter().getPoint().length;j++)
//                {
//                    sb.append(locationWrapper.getCenter().getPoint()[j] + ",");
//                }
//                sb.deleteCharAt(sb.length() - 1);
//                System.out.println(sb.toString());

                for (LocationWrapper elem : clusterResults.get(i).getPoints())
                {
                    sum += Math.pow(elem.getPoint()[0] - center[0] ,2);
                    sum += Math.pow(elem.getPoint()[1] - center[1] ,2);
                    sum += Math.pow(elem.getPoint()[2] - center[2] ,2);
                    sum += Math.pow(elem.getPoint()[3] - center[3] ,2);
                }
            }
            System.out.println(k + "\t" +sum);
        }


    }
}
