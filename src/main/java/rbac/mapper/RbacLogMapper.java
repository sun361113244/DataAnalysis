package rbac.mapper;

import rbac.entity.RbacLog;

import java.util.List;

public interface RbacLogMapper
{
    List<RbacLog> selectLogList();

    int insertLog(RbacLog rbacLog);
}
