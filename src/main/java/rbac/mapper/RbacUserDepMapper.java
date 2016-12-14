package rbac.mapper;

import org.apache.ibatis.annotations.Param;
import rbac.entity.RbacDep;

import java.util.List;

public interface RbacUserDepMapper
{
    List<RbacDep> selectDepListByUserId(@Param("id") Integer id);

    int deleteByUserId(Integer id);

    void insertUserDep(@Param("id") Integer id, @Param("stationId") Integer stationId);
}
