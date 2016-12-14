package rbac.service;

import rbac.entity.RbacDep;

import java.util.List;

public interface DepService
{
    int selectIsDepCodeExist(String code);

    int selectIsDepNameExist(String name);

    int selectIsDepCodeExistExceptID(Integer id, String code);

    int selectIsDepNameExistExceptID(Integer id, String name);

    List<RbacDep> selectDepList();

    int insertDep(RbacDep dep);

    int updateDep(RbacDep dep);

}
