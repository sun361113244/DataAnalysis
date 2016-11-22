package util;

import org.apache.spark.sql.SparkSession;
import org.springframework.stereotype.Component;

@Component
public class SparkContextBean
{

    private volatile static SparkSession spark;

    private SparkContextBean()
    {
        String master = "local";
        String appName = "sparkapp";

        spark = SparkSession
                .builder()
                .master(master)
                .appName(appName)
                .config("spark.some.config.option", "some-value")
                .getOrCreate();
    }

    public static SparkSession getSparkSession()
    {
        if(spark == null)
        {
            synchronized (SparkContextBean.class)
            {
                if(spark == null)
                    new SparkContextBean();
            }
        }
        return spark;
    }
}
