package net.wutnews.app.app.entiy;

/**
 * Created by Pan on 2015/1/23 0023.
 */
public class SetUser {

    private String user;

    @Override
    public String toString() {
        return "SetUser{" +
                "user='" + user + '\'' +
                '}';
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
