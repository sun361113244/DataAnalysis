package rbac.service.impl;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import rbac.entity.RbacUri;
import rbac.service.MenuService;
import sys.tree.TreeHelper;
import sys.tree.TreeNode;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class MenuServiceOnColorAdminImpl implements MenuService
{
    public String getMenuStr(List<RbacUri> menus, Integer index)
    {
        List<TreeNode> menuTree = TreeHelper.changeEnititiesToTreeNodes(menus);

        AddRootNode(menuTree);

        TreeHelper treeHelper = new TreeHelper(menuTree);
        String htmlStr = GenerateMenuHtmlStr(treeHelper.getRoot() ,index);
        return htmlStr;
    }

    private void AddRootNode(List<TreeNode> menuTree)
    {
        RbacUri rootUri = new RbacUri(0, -1 ,"root");
        TreeNode treeNode = new TreeNode();
        treeNode.setObj(rootUri);
        treeNode.setParentId(rootUri.getParentId());
        treeNode.setSelfId(rootUri.getId());
        treeNode.setNodeName(rootUri.getName());
        menuTree.add(treeNode);
    }

    private String GenerateMenuHtmlStr(TreeNode rootNode, Integer index)
    {
        Element root = DocumentHelper.createElement(rootNode.getNodeName()).addText("");
        Document document = DocumentHelper.createDocument(root);

        Collections.sort(rootNode.getChildList(), new Comparator<TreeNode>()
        {
            public int compare(TreeNode o1, TreeNode o2)
            {
                return o1.getSelfId() > o2.getSelfId() ? 1:-1;
            }
        });

        for(int i=0;i<rootNode.getChildList().size();i++)
        {
            handleTreeNode(root ,rootNode.getChildList().get(i) ,index);
        }
        String result = root.asXML();
        return result.substring(6 ,result.length() - 7);
    }

    private void handleTreeNode(Element root, TreeNode treeNode, Integer index)
    {
        RbacUri menuItem = (RbacUri)treeNode.getObj();
        String contextPath = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getContextPath();

        // 叶子节点
        if(treeNode.getChildList() != null && treeNode.getChildList().size() == 0)
        {
            // 第一层节点
            if(menuItem.getParentId() == 0)
            {
                Element li = root.addElement("li").addText("");
                if( index != null && treeNode.getSelfId() == index)
                    li.addAttribute("class" , "active");
                Element a = li.addElement("a");
                if(menuItem.getUri().equals("#"))
                    a.addAttribute("href" , menuItem.getUri());
                else
                    a.addAttribute("href", contextPath + menuItem.getUri());
                a.addElement("i").addAttribute("class", menuItem.getIconcls()).addText("");
                a.addElement("span").addText(menuItem.getName());
            }
            else
            {
                Element li = root.addElement("li").addText("");
                Element a = li.addElement("a");
                if(menuItem.getUri().equals("#"))
                    a.addAttribute("href" , menuItem.getUri());
                else
                    a.addAttribute("href" , contextPath + menuItem.getUri());
                a.addText(menuItem.getName());
            }
        }
        else
        {
            // 枝干节点
            if(treeNode.getChildList() != null && treeNode.getChildList().size() > 0)
            {
                // 第一层节点
                if(menuItem.getParentId() == 0)
                {
                    Element li = root.addElement("li").addText("");
                    if( index != null && treeNode.getSelfId() == index)
                        li.addAttribute("class" , "has-sub active");
                    else
                        li.addAttribute("class" , "has-sub");
                    Element a = li.addElement("a");
                    if(menuItem.getUri().equals("#"))
                        a.addAttribute("href" , menuItem.getUri());
                    else
                        a.addAttribute("href" , contextPath + menuItem.getUri());
                    a.addElement("b").addAttribute("class", "caret pull-right").addText("");
                    a.addElement("i").addAttribute("class", menuItem.getIconcls()).addText("");
                    a.addElement("span").addText(menuItem.getName());
                    Element ul = li.addElement("ul").addAttribute("class" ,"sub-menu");
                    Collections.sort(treeNode.getChildList(), new Comparator<TreeNode>()
                    {
                        public int compare(TreeNode o1, TreeNode o2)
                        {
                            return o1.getSelfId() > o2.getSelfId() ? 1:-1;
                        }
                    });
                    for(int i=0;i<treeNode.getChildList().size();i++)
                    {
                        handleTreeNode(ul ,treeNode.getChildList().get(i), index);
                    }
                }
                else
                {
                    Element li = root.addElement("li").addAttribute("class", "has-sub").addText("");
                    Element a = li.addElement("a");
                    if(menuItem.getUri().equals("#"))
                        a.addAttribute("href" , menuItem.getUri());
                    else
                        a.addAttribute("href" , contextPath + menuItem.getUri());
                    a.addElement("b").addAttribute("class", "caret pull-right").addText("");
                    a.addElement("span").addText(menuItem.getName());
                    Element ul = li.addElement("ul").addAttribute("class" ,"sub-menu");
                    Collections.sort(treeNode.getChildList(), new Comparator<TreeNode>()
                    {
                        public int compare(TreeNode o1, TreeNode o2)
                        {
                            return o1.getSelfId() > o2.getSelfId() ? 1:-1;
                        }
                    });
                    for(int i=0;i<treeNode.getChildList().size();i++)
                    {
                        handleTreeNode(ul ,treeNode.getChildList().get(i), index);
                    }
                }
            }
            else
            {
                try
                {
                    throw new Exception("treeNode.getChildList()返回null");
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

}
