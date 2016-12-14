package rbac.service;

import rbac.entity.RbacDep;

import java.util.List;

public interface UserDepService
{
    List<RbacDep> selectDepListByUserId(Integer id);

    int deleteByUserId(Integer id);

    void insertUserDep(Integer id, Integer stationId);

    int insertUserDeps(Integer id, Integer[] nodes);
}
