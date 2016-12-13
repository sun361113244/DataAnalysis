package aspects;

import entity.AnalysisFilter;
import entity.TbColumn;
import exception.ArgsException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import util.JdbcUtil;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@Aspect
@Component
@Order(2)
public class ArgsCheckAspects
{
    private final int LOCALRECORDMAX = 10000;

    private final int LOCALCOLUMNMAX = 50;

    @Resource
    private JdbcUtil jdbcUtil;

    @Before("execution(public org.springframework.web.servlet.ModelAndView api..*(..))")
    public void beforecheck(JoinPoint jp) throws Exception
    {
        List<Object> args = Arrays.asList(jp.getArgs());
        if(args.get(0) == null)
            throw new ArgsException("不存在表名!");

        AnalysisFilter analysisFilter = (AnalysisFilter)args.get(0);
        String tblName = analysisFilter.getTblName();
        if(tblName == null || tblName.length() > 100 || tblName.contains(";") || tblName.contains("("))
            throw new ArgsException("表名异常!");

        String sql = "select count(1) from " + tblName;

        List<Object> objectList = jdbcUtil.findSimpleResult(sql , null);

        if(Integer.parseInt(objectList.get(0).toString()) == 0)
            throw new ArgsException("表格内无记录!");

        if(Integer.parseInt(objectList.get(0).toString()) > LOCALRECORDMAX)
            throw new ArgsException("数据项超过" + LOCALRECORDMAX + "!");

        List<TbColumn> tbColumns = jdbcUtil.findSchema(tblName);
        if(tbColumns.size() > LOCALCOLUMNMAX)
            throw new ArgsException("数据列超过" + LOCALCOLUMNMAX + "!");

    }
}
