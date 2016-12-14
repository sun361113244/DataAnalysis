package analysis.service;

import analysis.entity.AnalysisLog;

public interface LogService
{
    int writeLog(AnalysisLog analysisLog);
}
