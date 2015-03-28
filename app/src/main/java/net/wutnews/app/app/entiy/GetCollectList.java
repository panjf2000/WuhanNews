package net.wutnews.app.app.entiy;

import java.util.List;

/**
 * Created by Pan on 2015/3/8.
 */
public class GetCollectList {

    private List<GetCollectListData> data;
    private String status,msg,type,count;

    @Override
    public String toString() {
        return "GetCollectList{" +
                "data=" + data +
                ", status='" + status + '\'' +
                ", msg='" + msg + '\'' +
                ", type='" + type + '\'' +
                ", count='" + count + '\'' +
                '}';
    }

    public List<GetCollectListData> getData() {
        return data;
    }

    public void setData(List<GetCollectListData> data) {
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


