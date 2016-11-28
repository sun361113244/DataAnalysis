package util;

import org.apache.spark.sql.SparkSession;
import org.springframework.stereotype.Component;

public class SparkContextBean
{
    private volatile static SparkSession spark;

    static
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
        return spark;
    }
}
