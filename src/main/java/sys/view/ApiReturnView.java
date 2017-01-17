package sys.view;

import analysis.entity.ApiReturn;
import com.alibaba.fastjson.JSON;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class ApiReturnView extends AbstractView
{
    private final static String CONTENT_TYPE = "application/json";

    private final static String CharacterEncoding = "utf-8";

    public ApiReturnView()
    {
        super();
        setContentType(CONTENT_TYPE);
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
                                           HttpServletResponse response) throws Exception
    {
        ApiReturn apiReturn = (ApiReturn) model.get("apiReturn");

        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append(String.format("\"taskid\": \"%s\" ," , apiReturn.getTaskid()));
        if(apiReturn.getReturn_msg() != null)
            sb.append(String.format("\"return_msg\": \"%s\" ," , apiReturn.getReturn_msg()));
        if(apiReturn.getData() != null)
        {
            sb.append("\"data\":{");
            for(int i = 0; i< apiReturn.getControllerName().size();i++)
            {
                sb.append(String.format("\"%s\":%s ," , apiReturn.getControllerName().get(i) ,JSON.toJSONString(apiReturn.getData().get(i))));
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append("},");
        }
        sb.append(String.format("\"return_code\": %d}" , apiReturn.getReturn_code()));

        response.setContentType(CONTENT_TYPE);
        response.setCharacterEncoding(CharacterEncoding);
        response.getWriter().write(sb.toString());
        System.out.println(JSON.toJSONString(model));
        response.getWriter().close();
    }
}
