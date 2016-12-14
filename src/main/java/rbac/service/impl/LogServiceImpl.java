package rbac.service.impl;

import org.springframework.stereotype.Service;
import rbac.entity.RbacLog;
import rbac.mapper.RbacLogMapper;
import rbac.service.LogService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LogServiceImpl implements LogService
{
    @Resource
    private RbacLogMapper rbacLogMapper;

    public List<RbacLog> selectLogList()
    {
        return rbacLogMapper.selectLogList();
    }

    public int insertLog(RbacLog rbacLog)
    {
        return rbacLogMapper.insertLog(rbacLog);
    }
}
