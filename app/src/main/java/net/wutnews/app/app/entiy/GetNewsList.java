package net.wutnews.app.app.entiy;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.wutnews.app.frame.util.gsonUtil;

import java.util.List;

/**
 * Created by Pan on 2015/1/22 0022.
 */
public class GetNewsList {

    private String status,count,termname;
    private List<GetNewsListData> data;


    @Override
    public String toString() {
        return "GetNewsList{" +
                "status='" + status + '\'' +
                ", termname'"+termname+'\''+
                ", count='" + count + '\'' +
                ", data=" + data +
                '}';
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

    public List<GetNewsListData> getData() {
        return (List<GetNewsListData>)gsonUtil.getInstance().json2List(gsonUtil.getInstance().toJson(data),new TypeToken<List<GetNewsListData>>(){}.getType());
    }

    public void setData(List<GetNewsListData> data) {
        this.data = data;
    }
    public void setTermname(String termname)
    {
        this.termname=termname;
    }
    public String getTermname()
    {
        return this.termname;
    }
}
