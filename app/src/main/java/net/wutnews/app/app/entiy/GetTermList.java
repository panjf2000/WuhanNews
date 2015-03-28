package net.wutnews.app.app.entiy;

import com.google.gson.reflect.TypeToken;

import net.wutnews.app.frame.util.gsonUtil;

import java.util.List;

/**
 * Created by Pan on 2015/1/22 0022.
 */
public class GetTermList {

    private String status;
    private String count;
    private String msg;
    private boolean type;
    private List<GetTermListData> data;


    @Override
    public String toString() {
        return "GetTermList{" +
                "status='" + status + '\'' +
                ", count='" + count + '\'' +
                ", msg='" + msg + '\'' +
                ", type=" + type +
                ", data=" + data +
                '}';
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<GetTermListData> getData() {
        return data;
    }

    public void setData(List<GetTermListData> data) {
        this.data = data;
    }
}
