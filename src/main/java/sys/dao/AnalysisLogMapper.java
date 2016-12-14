package sys.dao;

import analysis.entity.AnalysisLog;

public interface AnalysisLogMapper
{
    public int writeLog(AnalysisLog analysisLog);
}
