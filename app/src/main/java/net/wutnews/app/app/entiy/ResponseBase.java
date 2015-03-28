package net.wutnews.app.app.entiy;

/**
 * Created by Andy on 2015/1/20.
 */
public class ResponseBase {
    private int status;
    private boolean type;
    private String msg;
    public ResponseBase(){};
    public ResponseBase(int status,boolean type,String msg)
    {
        this.status = status;
        this.type=type;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ResponseBase{" +
                "status='" + status + '\'' +
                ", type=" + type +
                ", msg='" + msg + '\'' +
                '}';
    }
    public int getStatus()
    {
        return status;
    }
    public void setStatus(int status)
    {
        this.status=status;
    }
    public boolean getType()
    {
        return this.type;
    }
    public void setType(boolean type)
    {
        this.type = type;
    }
    public String getMsg()
    {
        return this.msg;
    }
    public void setMsg(String msg)
    {
        this.msg=msg;
    }
}
