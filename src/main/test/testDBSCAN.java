import org.apache.commons.math3.ml.clustering.Cluster;
import org.apache.commons.math3.ml.clustering.Clusterable;
import org.apache.commons.math3.ml.clustering.DBSCANClusterer;
import org.junit.Test;
import util.JdbcUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class testDBSCAN
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

        DBSCANClusterer clusterer = new DBSCANClusterer(0.5, 10);

        List<Cluster<LocationWrapper>> clusters = clusterer.cluster(clusterInput);
        for (int i=0; i<clusters.size(); i++)
        {
            System.out.println("Cluster " + i);
            for (LocationWrapper locationWrapper : clusters.get(i).getPoints())
                System.out.println(locationWrapper.getKey());
            System.out.println();
        }
    }
}
