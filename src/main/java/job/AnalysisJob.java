package job;

import java.util.Map;
import java.util.concurrent.Callable;

public interface AnalysisJob extends Callable<Map<String, Object>>
{
}
