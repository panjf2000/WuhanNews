package net.wutnews.app.app.entiy;

/**
 * Created by Pan on 2015/1/25 0025.
 */
public class GetLoginData {

    private String id,user_auth;

    @Override
    public String toString() {
        return "GetLogin{" +
                "id='" + id + '\'' +
                ", user_auth='" + user_auth + '\'' +
                '}';
    }

    public String getUser_auth() {
        return user_auth;
    }

    public void setUser_auth(String user_auth) {
        this.user_auth = user_auth;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
