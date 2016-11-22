package sys.view;

import com.alibaba.fastjson.JSON;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class DataTablesServerSideView  extends AbstractView
{
    private final static String CONTENT_TYPE = "application/json";

    private final static String CharacterEncoding = "utf-8";


    public DataTablesServerSideView()
    {
        super();
        setContentType(CONTENT_TYPE);
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        response.setContentType(CONTENT_TYPE);
        response.setCharacterEncoding(CharacterEncoding);
        response.getWriter().write("{");
        response.getWriter().write("\"draw\":" + (model.get("draw") != null ? model.get("draw"): 0) + ",");
        response.getWriter().write("\"recordsTotal\":" + (model.get("recordsTotal") != null ? model.get("recordsTotal"): 0) + ",");
        response.getWriter().write("\"recordsFiltered\":" + (model.get("recordsFiltered") != null ? model.get("recordsFiltered"): 0) + ",");
        response.getWriter().write("\"data\":" );

        response.getWriter().write(JSON.toJSONString(model.get("records")));
        response.getWriter().write("}");
        response.getWriter().close();
    }
}

