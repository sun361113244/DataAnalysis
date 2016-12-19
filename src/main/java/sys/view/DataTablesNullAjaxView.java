package sys.view;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class DataTablesNullAjaxView extends AbstractView
{
    private final static String CONTENT_TYPE = "application/json";

    private final static String CharacterEncoding = "utf-8";

    public DataTablesNullAjaxView()
    {
        super();
        setContentType(CONTENT_TYPE);
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        List<String> dataTablesSchemas = (List<String>) model.get("dataTablesSchemas");
        List<List<Object>> tbData = (List<List<Object>>) model.get("tbData");

        StringBuilder sb = new StringBuilder("[");
        for(List<Object> line : tbData)
        {
            int loc = 0;

            sb.append("{");
            for(String colName : dataTablesSchemas)
            {
                sb.append(String.format("\"%s\":" ,colName));
                sb.append(String.format("\"%s\"," ,line.get(loc)!= null? line.get(loc).toString() : ""));
                loc++;
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append("},");
        }
        sb.deleteCharAt(sb.length() -1);
        sb.append("]");

        response.setContentType(CONTENT_TYPE);
        response.setCharacterEncoding(CharacterEncoding);
        response.getWriter().write("{");
        response.getWriter().write("\"draw\":" + (model.get("draw") != null ? model.get("draw") : 0) + ",");
        response.getWriter().write("\"recordsTotal\":" + (tbData != null ? tbData.size() : 0) + ",");
        response.getWriter().write("\"recordsFiltered\":" + (tbData != null ? tbData.size() : 0) + ",");
        response.getWriter().write("\"data\":" );

        response.getWriter().write(sb.toString());
        response.getWriter().write("}");
        response.getWriter().close();
    }
}
