function selectTableSummaryCharts()
{
    var tblName = $('#table_name').val();
    var showFrequencyHistogramStat = $('#continuous_stat').val();

    $.ajax({
        type: "POST",
        url: getContextPath() + "/analysis/api/DescriptiveStatisticLocal/summary" ,
        data: {
            tblName : tblName ,
            showFrequencyHistogramStat : showFrequencyHistogramStat
        },
        success: function (records) {
            if(records.result == 1)
            {
                $('#table_summary_display').html("");

                var descriptiveSummary = records.descriptiveAnalysis_summary;
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
                    if(descriptiveSummary[i].msg != null)
                    {
                        $('#table_summary_display').append("<b>" + descriptiveSummary[i].msg +"</b>");
                    }
                }
            }
            else
            {
                alert('加载失败');
            }
        }
    });
}