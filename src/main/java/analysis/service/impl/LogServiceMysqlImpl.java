package analysis.service.impl;



import analysis.entity.AnalysisLog;
import org.springframework.stereotype.Service;
import analysis.service.LogService;
import sys.dao.AnalysisLogMapper;

import javax.annotation.Resource;

@Service
public class LogServiceMysqlImpl implements LogService
{
    @Resource
    private AnalysisLogMapper analysisLogMapper;

    @Override
    public int writeLog(AnalysisLog analysisLog)
    {
        return analysisLogMapper.writeLog(analysisLog);
    }
}
