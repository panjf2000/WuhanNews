package net.wutnews.app.app.entiy;

/**
 * Created by Pan on 2015/1/25 0025.
 */
public class SetLogin {

    private String userinfo;
    private boolean debug=false;

    @Override
    public String toString() {
        return "SetLogin{" +
                "userinfo='" + userinfo + '\'' +
                ", debug='" + debug + '\'' +
                '}';
    }

    public boolean getDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public String getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(String userinfo) {
        this.userinfo = userinfo;
    }
}
