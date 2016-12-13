package util;

import job.AnalysisJob;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.*;

@Component
public class ExecutorContext
{
    private int NTHREADS = Config.THREAD_COUNT;

    private ExecutorService exec = Executors.newFixedThreadPool(NTHREADS);

    private ExecutorContext()
    {

    }

    public Future<Map<String , Object>> submit(AnalysisJob job)
    {
        return exec.submit(job);
    }
}
