package sys.dao;

import entity.AnalysisLog;

public interface AnalysisLogMapper
{
    public int writeLog(AnalysisLog analysisLog);
}
