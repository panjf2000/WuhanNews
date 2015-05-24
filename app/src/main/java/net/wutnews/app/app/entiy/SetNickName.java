package net.wutnews.app.app.entiy;

/**
 * Created by Andy on 2015/5/20.
 */
public class SetNickName {
    private String user,nickname;

    @Override
    public String toString() {
        return "SetNickName{" +
                "user='" + user + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
    public void setUser(String user){this.user = user;}
    public String getUser(){return user;}
    public void setNickname(String nickname){this.nickname=nickname;}
    public String getNickname(){return nickname;}
}
