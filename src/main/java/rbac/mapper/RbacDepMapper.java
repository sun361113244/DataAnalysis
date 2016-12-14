package rbac.mapper;

import org.apache.ibatis.annotations.Param;
import rbac.entity.RbacDep;

import java.util.List;

public interface RbacDepMapper
{
    int selectIsDepCodeExist(@Param("code") String code);

    int selectIsDepNameExist(@Param("name") String name);

    int selectIsDepCodeExistExceptID(@Param("id") Integer id, @Param("code") String code);

    int selectIsDepNameExistExceptID(@Param("id") Integer id, @Param("name") String name);

    List<RbacDep> selectDepList();

    int insertDep(RbacDep dep);

    int updateDep(RbacDep dep);

}
