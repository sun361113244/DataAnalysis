package util;

import com.alibaba.fastjson.JSON;
import entity.TbColumn;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

@Component
public class JdbcUtil
{
    //数据库用户名
    private static final String USERNAME = Config.JDBCUSERNAME;
    //数据库密码
    private static final String PASSWORD = Config.JDBCPASSWORD;
    //驱动信息
    private static final String DRIVER = Config.JDBCDRIVERCLASSNAME;
    //数据库地址
    private static final String URL = Config.JDBCURL;

    private Connection connection;

    private PreparedStatement pstmt;

    private ResultSet resultSet;

    public JdbcUtil()
    {
        try
        {
            Class.forName(DRIVER);
            getConnection();
            System.out.println("数据库连接成功！");

        } catch (Exception e)
        {

        }
    }

    public Connection getConnection()
    {
        try
        {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return connection;
    }

    public boolean updateByPreparedStatement(String sql, List<Object> params) throws SQLException
    {
        boolean flag = false;
        int result = -1;
        pstmt = connection.prepareStatement(sql);
        int index = 1;
        if (params != null && !params.isEmpty())
        {
            for (int i = 0; i < params.size(); i++)
            {
                pstmt.setObject(index++, params.get(i));
            }
        }
        result = pstmt.executeUpdate();
        flag = result > 0 ? true : false;
        return flag;
    }

    public List<String> selectAllTables() throws SQLException
    {
        List<String> tblNames = new ArrayList<>();
        String[] types = {"TABLE"};
        resultSet = connection.getMetaData().getTables(null , null,"%" , types);
        while (resultSet.next())
        {
            tblNames.add(resultSet.getString(3));
        }
        return tblNames;
    }

    public List<TbColumn> findSchema(String tableName) throws SQLException
    {
        List<TbColumn> tbColumns = new ArrayList<>();

        String sql = String.format("select * from %s where 1=2" , tableName);
        pstmt = connection.prepareStatement(sql);
        ResultSetMetaData rsd = pstmt.executeQuery().getMetaData();

        for(int i =0 ; i<rsd.getColumnCount();i++)
        {
            TbColumn tbColumn = new TbColumn();
            tbColumn.setCol_type(rsd.getColumnClassName(i+1));
            tbColumn.setCol_name(rsd.getColumnName(i+1));
            tbColumn.setCol_num(i);
            tbColumn.setNullable(rsd.isNullable(i+1) == ResultSetMetaData.columnNullable);

            tbColumns.add(tbColumn);
        }

        return tbColumns;
    }

    public List<Object> findSimpleResult(String sql, List<Object> params) throws SQLException
    {
        List<Object> map = new ArrayList<>();
        int index = 1;
        pstmt = connection.prepareStatement(sql);
        if (params != null && !params.isEmpty())
        {
            for (int i = 0; i < params.size(); i++)
            {
                pstmt.setObject(index++, params.get(i));
            }
        }
        resultSet = pstmt.executeQuery();//返回查询结果
        ResultSetMetaData metaData = resultSet.getMetaData();
        int col_len = metaData.getColumnCount();
        while (resultSet.next())
        {
            for (int i = 0; i < col_len; i++)
            {
                Object cols_value = resultSet.getObject(i + 1);
                map.add(cols_value);
            }
        }
        return map;
    }

    public List<List<Object>> findMoreResult(String sql, List<Object> params) throws SQLException
    {
        List<List<Object>> list = new ArrayList<List<Object>>();
        int index = 1;
        pstmt = connection.prepareStatement(sql);
        if (params != null && !params.isEmpty())
        {
            for (int i = 0; i < params.size(); i++)
            {
                pstmt.setObject(index++, params.get(i));
            }
        }
        resultSet = pstmt.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int cols_len = metaData.getColumnCount();
        while (resultSet.next())
        {
            List<Object> objectList = new ArrayList<>();
            for (int i = 0; i < cols_len; i++)
            {
                Object cols_value = resultSet.getObject(i + 1);
                objectList.add(cols_value);
            }
            list.add(objectList);
        }

        return list;
    }

    public <T> T findSimpleRefResult(String sql, List<Object> params,
                                     Class<T> cls) throws Exception
    {
        T resultObject = null;
        int index = 1;
        pstmt = connection.prepareStatement(sql);
        if (params != null && !params.isEmpty())
        {
            for (int i = 0; i < params.size(); i++)
            {
                pstmt.setObject(index++, params.get(i));
            }
        }
        resultSet = pstmt.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int cols_len = metaData.getColumnCount();
        while (resultSet.next())
        {
            //通过反射机制创建一个实例
            resultObject = cls.newInstance();
            for (int i = 0; i < cols_len; i++)
            {
                String cols_name = metaData.getColumnName(i + 1);
                Object cols_value = resultSet.getObject(cols_name);
                if (cols_value == null)
                {
                    cols_value = "";
                }
                Field field = cls.getDeclaredField(cols_name);
                field.setAccessible(true); //打开javabean的访问权限
                field.set(resultObject, cols_value);
            }
        }
        return resultObject;

    }

    public <T> List<T> findMoreRefResult(String sql, List<Object> params,
                                         Class<T> cls) throws Exception
    {
        List<T> list = new ArrayList<T>();
        int index = 1;
        pstmt = connection.prepareStatement(sql);
        if (params != null && !params.isEmpty())
        {
            for (int i = 0; i < params.size(); i++)
            {
                pstmt.setObject(index++, params.get(i));
            }
        }
        resultSet = pstmt.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int cols_len = metaData.getColumnCount();
        while (resultSet.next())
        {
            //通过反射机制创建一个实例
            T resultObject = cls.newInstance();
            for (int i = 0; i < cols_len; i++)
            {
                String cols_name = metaData.getColumnName(i + 1);
                Object cols_value = resultSet.getObject(cols_name);
                if (cols_value == null)
                {
                    cols_value = "";
                }
                Field field = cls.getDeclaredField(cols_name);
                field.setAccessible(true); //打开javabean的访问权限
                field.set(resultObject, cols_value);
            }
            list.add(resultObject);
        }
        return list;
    }

    public void releaseConn()
    {
        if (resultSet != null)
        {
            try
            {
                resultSet.close();
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) throws SQLException
    {
        JdbcUtil jdbcUtils = new JdbcUtil();

        List<String> tableNames = jdbcUtils.selectAllTables();



//        String sql = "select * from iris ";
//        List<List<Object>> params = jdbcUtils.findMoreResult(sql , null);
//
//        for(List<Object> map : params)
//        {
//
//            for(Object obj : map)
//            {
//                System.out.print(obj + "\t");
//            }
//            System.out.println();
//        }
//
//        List<TbColumn> tbColumns = jdbcUtils.findSchema("iris");


        System.out.println(JSON.toJSONString(tableNames));

    }
}