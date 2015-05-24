package net.wutnews.app.app.entiy;

/**
 * Created by Andy on 2015/5/19.
 */
public class GetUserDe {
    private String user_nicename="null",
            avatar,
            signature="null";

    @Override
    public String toString() {
        return "GetUserDe{" +
                "user_nicename='" + user_nicename + '\'' +
                ", avatar='" + avatar + '\'' +
                ", signature='" + signature +'\'' +
                '}';
    }
    public void setUser_nicename(String user_nicename){
        this.user_nicename=user_nicename;
    }
    public String getUser_nicename(){
        return user_nicename;
    }
    public void setAvatar(String path){
        this.avatar=path;
    }
    public String getAvatar(){
        return avatar;
    }
    public void setSignature(String signature)
    {
        this.signature=signature;
    }
    public String getSignature(){
        return signature;
    }
}
