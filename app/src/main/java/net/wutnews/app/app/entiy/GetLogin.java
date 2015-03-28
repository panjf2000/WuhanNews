package net.wutnews.app.app.entiy;

/**
 * Created by Pan on 2015/1/25 0025.
 */
public class GetLogin {

    private int status;
    private String msg;
    private boolean type;
    private GetLoginData data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public GetLoginData getData() {
        return data;
    }

    public void setData(GetLoginData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "GetLogin{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", type=" + type +
                ", data=" + data +
                '}';
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
