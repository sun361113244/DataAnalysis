function selectTableSummaryCharts()
{
    var tblName = $('#table_name').val();
    var showFrequencyHistogramStat = $('#continuous_stat').val();

    $.ajax({
        type: "POST",
        url: getContextPath() + "/analysis/api/CorrelationAnalysisLocal/summary" ,
        data: {
            taskid : "id_123456" ,
            ip : "127.0.0.11" ,
            port : 5555 ,
            user : "user" ,
            pwd : "pwd" ,
            db : "ddd" ,
            schema : "schema",
            table : tblName ,
            db_type : "postgresql" ,
            showFrequencyHistogramStat : showFrequencyHistogramStat
        },
        success: function (result) {
            if(result.return_code == 0)
            {
                $('#table_summary_display').html("");

                var descriptiveSummary = result.data.descriptiveAnalysis_summary;
                var correlationSummary = result.data.correlationAnalysis_summary;
                if(descriptiveSummary != null)
                {
                    for(var i=0;i<descriptiveSummary.length;i++)
                    {
                        if(descriptiveSummary[i].frequencyHistogramAnalysisEchartsVo != null)
                        {
                            var id = "frequencyDiagram" + i;
                            var frequencyoption = descriptiveSummary[i].frequencyHistogramAnalysisEchartsVo;
                            $('#table_summary_display').append("<div id='"+ id +"' style='width: 360px;height:240px;'></div>");

                            var myChart = echarts.init(document.getElementById(id));
                            myChart.setOption(frequencyoption);
                        }

                        if(descriptiveSummary[i].kdeAnalysisEchartsVo != null)
                        {
                            var id = "kdeDiagram" + i;
                            var kdeoption = descriptiveSummary[i].kdeAnalysisEchartsVo;
                            $('#table_summary_display').append("<div id='"+ id +"' style='width: 360px;height:240px;'></div>");

                            var myChart = echarts.init(document.getElementById(id));
                            myChart.setOption(kdeoption);
                        }
                        if(descriptiveSummary[i].classifyAnalysisEchartsVo != null)
                        {
                            var id = "classifyDiagram" + i;
                            var classifyoption = descriptiveSummary[i].classifyAnalysisEchartsVo;
                            $('#table_summary_display').append("<div id='"+ id +"' style='width: 360px;height:240px;'></div>");

                            var myChart = echarts.init(document.getElementById(id));
                            myChart.setOption(classifyoption);
                        }
                        if(descriptiveSummary[i].msg != null)
                        {
                            $('#table_summary_display').append("<b>" + descriptiveSummary[i].msg +"</b>");
                        }
                    }
                }
                if(correlationSummary != null)
                {
                    for(var i=0;i<correlationSummary.length;i++)
                    {
                        if(correlationSummary[i].regressionAnalysisEchartsVo != null)
                        {
                            var id = "regressionDiagram" + i;
                            var regressionoption = correlationSummary[i].regressionAnalysisEchartsVo;
                            $('#table_summary_display').append("<div id='"+ id +"' style='width: 360px;height:240px;'></div>");

                            var myChart = echarts.init(document.getElementById(id));
                            myChart.setOption(regressionoption);
                        }
                        if(correlationSummary[i].msg != null)
                        {
                            $('#table_summary_display').append("<b>" + correlationSummary[i].msg +"</b>");
                        }
                    }
                }
            }
            else
            {
                alert("加载错误!" + result.return_msg);
            }
        } ,
        error : function(result)
        {
            alert("json错误:" + result.responseText);
        }
    });
}