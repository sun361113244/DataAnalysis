package sys.view;

import com.alibaba.fastjson.JSON;
import org.springframework.web.servlet.view.AbstractView;
import sys.entity.zTreeNode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by charles on 2015/10/13.
 */
public class zTreeView extends AbstractView
{
    private final static String CONTENT_TYPE = "application/json";

    private final static String CharacterEncoding = "utf-8";

    public zTreeView()
    {
        super();
        setContentType(CONTENT_TYPE);
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        Map<String , Object> responseBody = new HashMap<String, Object>();

        Integer result = (Integer)model.get("result");
        List<zTreeNode> zTreeAllNodes = (List<zTreeNode>) model.get("zTreeAllNodes");
        List<zTreeNode> zTreeCheckedNodes = (List<zTreeNode>) model.get("zTreeCheckedNodes");

        List<zTreeNode> zTreeNodes = new ArrayList<zTreeNode>();
        for(zTreeNode treeNode : zTreeAllNodes)
        {
            if(zTreeCheckedNodes.contains(treeNode))
            {
                treeNode.setChecked(true);
                zTreeNodes.add(treeNode);
            }
            else
            {
                zTreeNodes.add(treeNode);
            }
        }
        responseBody.put("result" ,result);
        responseBody.put("treeNodes", zTreeNodes);
        response.setContentType(CONTENT_TYPE);
        response.setCharacterEncoding(CharacterEncoding);
        response.getWriter().write(JSON.toJSONString(responseBody));
        response.getWriter().close();
    }
}
