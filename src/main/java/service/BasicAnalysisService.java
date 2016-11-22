package service;

import entity.BasicAnalysis;
import entity.TbColumn;

import java.util.List;

public interface BasicAnalysisService
{
    List<BasicAnalysis> selectSummaryInfo(String dBName, String tblName, List<TbColumn> cols);
}
