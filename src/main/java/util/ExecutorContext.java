package util;

import job.AnalysisJob;

import java.util.Map;
import java.util.concurrent.*;

public class ExecutorContext
{
    private static final int NTHREADS = Config.THREAD_COUNT;

    private static final ExecutorService exec = Executors.newFixedThreadPool(NTHREADS);

    private ExecutorContext()
    {

    }

    public static Future<Map<String , Object>> submit(AnalysisJob job)
    {
        return exec.submit(job);
    }
}
