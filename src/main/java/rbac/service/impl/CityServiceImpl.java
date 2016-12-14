package rbac.service.impl;

import org.springframework.stereotype.Service;
import rbac.entity.Area;
import rbac.entity.City;
import rbac.entity.Province;
import rbac.mapper.AreaMapper;
import rbac.mapper.CityMapper;
import rbac.mapper.ProvinceMapper;
import rbac.service.CityService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by charles on 16/8/18.
 */
@Service
public class CityServiceImpl implements CityService
{
    @Resource
    private CityMapper cityMapper;
    @Resource
    private ProvinceMapper provinceMapper;
    @Resource
    private AreaMapper areaMapper;

    public List<Province> getAllProvince() {
        return provinceMapper.list();
    }

    public List<City> getCityByProvinceId(String id) {
        return cityMapper.getCityByProvinceId(id);
    }

    public List<Area> getAreaByCityId(String id) {
        return areaMapper.getAreaByCityId(id);
    }
}
