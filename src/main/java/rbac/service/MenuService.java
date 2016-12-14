package rbac.service;


import rbac.entity.RbacUri;

import java.util.List;

public interface MenuService
{
    String getMenuStr(List<RbacUri> menus, Integer index);
}
