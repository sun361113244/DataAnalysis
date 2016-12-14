package rbac.service;

import rbac.entity.RbacLog;

import java.util.List;

public interface LogService
{
    List<RbacLog> selectLogList();

    int insertLog(RbacLog rbacLog);
}
