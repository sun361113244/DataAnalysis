package rbac.mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by charles on 16/8/18.
 */
public interface BaseMapper<T>
{
    /**
     * 插入一条记录
     *
     * @param t
     */
    public void insert(T t);

    /**
     * 根据Id删除一条记录
     *
     * @param id
     */
    public void deleteById(String id);

    /**
     * 更新一条记录
     *
     * @param t
     */
    public void update(T t);

    /**
     * 根据Id查询一条记录
     *
     * @param id
     * @return
     */
    public T selectById(String id);

    /**
     * 列出所有记录
     *
     * @return
     */
    public List<T> list();

    /**
     * 查询记录条数
     *
     * @return
     */
    public long count(Map<String, Object> map);

    /**
     * 按照条件查询
     *
     * @return
     */
    public List<T> find(Map<String, Object> map);
}
