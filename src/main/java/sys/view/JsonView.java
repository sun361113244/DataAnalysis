package sys.view;

import com.alibaba.fastjson.JSON;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class JsonView extends AbstractView
{
    private final static String CONTENT_TYPE = "application/json";

    private final static String CharacterEncoding = "utf-8";

    public JsonView()
    {
        super();
        setContentType(CONTENT_TYPE);
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
                                           HttpServletResponse response) throws Exception
    {
        response.setContentType(CONTENT_TYPE);
        response.setCharacterEncoding(CharacterEncoding);
        response.getWriter().write(JSON.toJSONString(model));
        response.getWriter().close();
    }
}
