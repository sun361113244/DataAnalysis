package rbac.service;

import rbac.entity.Area;
import rbac.entity.City;
import rbac.entity.Province;

import java.util.List;

/**
 * Created by charles on 16/8/18.
 */
public interface CityService
{
    List<Province> getAllProvince();

    List<City> getCityByProvinceId(String id);

    List<Area> getAreaByCityId(String id);
}
