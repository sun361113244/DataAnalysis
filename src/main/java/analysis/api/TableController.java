package analysis.api;

import analysis.entity.TbColumn;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import sys.entity.DataTablesSchema;
import util.JdbcUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/analysis/api/table")
public class TableController
{
    @Resource
    private JdbcUtil jdbcUtil;

    @RequestMapping(value = "/selectScheme")
    public ModelAndView selectScheme(@RequestParam("tblName") String tblName) throws Exception
    {
        ModelAndView mav = new ModelAndView("JsonView");

        List<TbColumn> tbColumns = jdbcUtil.findSchema(tblName);

        List<DataTablesSchema> dataTablesSchemas = new ArrayList<>();
        for(TbColumn tbColumn : tbColumns)
        {
            DataTablesSchema dataTablesSchema = new DataTablesSchema();
            dataTablesSchema.setData(tbColumn.getCol_name());
            dataTablesSchema.setTitle(tbColumn.getCol_name());

            dataTablesSchemas.add(dataTablesSchema);
        }

        mav.addObject("table_schema" , dataTablesSchemas);
        mav.addObject("result" , 1);

        return mav;
    }

    @RequestMapping(value = "/selectTableData")
    public ModelAndView selectTableData(@RequestParam("tblName") String tblName) throws Exception
    {
        ModelAndView mav = new ModelAndView("DataTablesNullAjaxView");

        List<TbColumn> tbColumns = jdbcUtil.findSchema(tblName);
        List<String> dataTablesSchemas = new ArrayList<>();
        for(TbColumn tbColumn : tbColumns)
        {
            dataTablesSchemas.add(tbColumn.getCol_name());
        }
        String sql = "select * from " + tblName;
        List<List<Object>> tbData = jdbcUtil.findMoreResult(sql ,null);
        mav.addObject("dataTablesSchemas" , dataTablesSchemas);
        mav.addObject("tbData" , tbData);
        mav.addObject("result" , 1);

        return mav;
    }
}
