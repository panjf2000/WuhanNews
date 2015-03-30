package net.wutnews.app.app.entiy;

import java.util.List;

/**
 * Created by Andy on 2015/3/30.
 */
public class GetCommentList {
    private String status, msg, type, count;
    private List<GetCommentListData> data;

    @Override
    public String toString() {
        return "GetCommentList{" +
                "data=" + data +
                ", status='" + status + '\'' +
                ", msg='" + msg + '\'' +
                ", type='" + type + '\'' +
                ", count='" + count + '\'' +
                '}';
    }

    public List<GetCommentListData> getData() {
        return data;
    }

    public void setData(List<GetCommentListData> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
