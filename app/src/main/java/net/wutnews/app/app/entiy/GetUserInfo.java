package net.wutnews.app.app.entiy;

/**
 * Created by Andy on 2015/5/19.
 */
public class GetUserInfo {
    private int status;
    private String msg;
    private boolean type;
    private GetUserDe data;

    @Override
    public String toString() {
        return "GetUserInfo{" +
                "status='" + status + '\'' +
                ",msg='" + msg + '\'' +
                ",type='" + type +'\'' +
                ",data='"+ data +'\'' +
                '}';
    }
    public void setStatus(int status){
        this.status=status;
    }
    public int getStatus(){
        return this.status;
    }
    public void setData(GetUserDe data){
    this.data=data;
    }
    public GetUserDe getData(){
        return data;
    }
    public void setMsg(String msg){
        this.msg=msg;
    }
    public String getMsg(){
        return msg;
    }
    public void setType(boolean type){this.type = type;}
    public boolean getType(){
        return type;
    }
}
