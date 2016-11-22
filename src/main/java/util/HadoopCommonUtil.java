package util;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;
import org.apache.mahout.common.HadoopUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class HadoopCommonUtil
{
    private static Configuration conf;

    private static final Pattern SLASH = Pattern.compile(":");

    /**
     * 获得Configuration对象
     */
    public static Configuration getConf()
    {
        if(conf != null)
        {
            return conf;
        }
        conf = new Configuration();

        return  conf;
    }

    public static void SequenceFiles(String input , String output , boolean isOverWrite , boolean onCluster) throws IOException
    {
        if(isOverWrite)
            HadoopUtil.delete(getConf() , new Path(output));

        if(onCluster)
        {
            SequenceFilesOnCluster(input , output);
        }
        else
        {
            SequenceFilesLocal(input , output);
        }
    }

    private static void SequenceFilesOnCluster(String input, String output)
    {

    }

    private static void SequenceFilesLocal(String input, String output) throws IOException
    {
        FileSystem fs = FileSystem.get(getConf());
        SequenceFile.Writer writer = new SequenceFile.Writer(fs , conf , new Path(output) , Text.class , Text.class);

        Path path = new Path(input);
        InputStream inputStream = null;
        BufferedReader reader = null;

        try
        {
            inputStream = fs.open(path);
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;

            Text Key;
            Text value = new Text();

            reader.readLine();
            while ((line = reader.readLine()) != null)
            {
                String[] tmp = SLASH.split(line);
                if(tmp == null)
                    throw new IOException("字符串错误!!");
                Key = new Text(tmp[0]);
                if(tmp.length > 1)
                    value = new Text(tmp[1]);
                else
                    value = new Text("");

                writer.append(Key , value);
            }
        } finally
        {
            reader.close();
            IOUtils.closeStream(inputStream);
            writer.close();
            fs.close();
        }
    }
}

