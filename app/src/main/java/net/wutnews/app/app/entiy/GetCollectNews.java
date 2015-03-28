package net.wutnews.app.app.entiy;

/**
 * Created by Administrator on 2015/2/15 0015.
 */
public class GetCollectNews {

    private String msg, type, collectid;
    private int status;

    @Override
    public String toString() {
        return "GetCollectNews{" +
                "msg='" + msg + '\'' +
                ", type='" + type + '\'' +
                ", collectid='" + collectid + '\'' +
                ", status=" + status +
                '}';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCollectid() {
        return collectid;
    }

    public void setCollectid(String collectid) {
        this.collectid = collectid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {

        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
