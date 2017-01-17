package analysis.entity;

import java.util.List;

public class ApiReturn
{
    private Integer return_code;
    private String return_msg;
    private String taskid;
    private List<Object> data;
    private List<String> controllerName;

    public Integer getReturn_code()
    {
        return return_code;
    }

    public void setReturn_code(Integer return_code)
    {
        this.return_code = return_code;
    }

    public String getReturn_msg()
    {
        return return_msg;
    }

    public void setReturn_msg(String return_msg)
    {
        this.return_msg = return_msg;
    }

    public String getTaskid()
    {
        return taskid;
    }

    public void setTaskid(String taskid)
    {
        this.taskid = taskid;
    }

    public List<Object> getData()
    {
        return data;
    }

    public void setData(List<Object> data)
    {
        this.data = data;
    }

    public List<String> getControllerName()
    {
        return controllerName;
    }

    public void setControllerName(List<String> controllerName)
    {
        this.controllerName = controllerName;
    }
}
