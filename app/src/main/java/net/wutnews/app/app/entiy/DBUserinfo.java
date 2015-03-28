package net.wutnews.app.app.entiy;

/**
 * Created by Pan on 2015/1/25 0025.
 */
public class DBUserinfo {

    private boolean guestLogin =true;
    private boolean isNightMode=false;
    private boolean notWifiImg = false;
    private boolean notWifiNewsCache = false;

    @Override
    public String toString() {
        return "DBUserinfo{" +
                "guestLogin=" + guestLogin +
                ", isNightMode=" + isNightMode +
                ", notWifiImg=" + notWifiImg +
                ", notWifiNewsCache=" + notWifiNewsCache +
                ", id='" + id + '\'' +
                ", user='" + user + '\'' +
                '}';
    }

    public boolean isNotWifiImg() {
        return notWifiImg;
    }

    public void setNotWifiImg(boolean notWifiImg) {
        this.notWifiImg = notWifiImg;
    }

    public boolean isNotWifiNewsCache() {
        return notWifiNewsCache;
    }

    public void setNotWifiNewsCache(boolean notWifiNewsCache) {
        this.notWifiNewsCache = notWifiNewsCache;
    }

    public boolean isNightMode() {
        return isNightMode;
    }

    public void setNightMode(boolean isNightMode) {
        this.isNightMode = isNightMode;
    }

    public boolean isGuestLogin() {
        return guestLogin;
    }

    private String id,user;

    public DBUserinfo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean getGuestLogin() {
        return guestLogin;
    }

    public void setGuestLogin(boolean guestLogin) {
        this.guestLogin = guestLogin;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public DBUserinfo(boolean guestLogin, String id, String user) {
        this.guestLogin = guestLogin;
        this.id = id;
        this.user = user;
    }
}
