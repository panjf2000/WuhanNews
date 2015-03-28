package net.wutnews.app.app.entiy;

/**
 * Created by Andy on 2015/3/27.
 */
public class DeleteCollection {
    private String user,collectid;

    @Override
    public String toString() {
        return "DeletCollection{" +
                "user=" + user +
                ", collectid='" + collectid + '\'' +
                '}';
    }
    public String getUser()
    {
        return user;
    }
    public void setUser(String user)
    {
        this.user=user;

    }
    public String getCollectid()
    {
        return collectid;
    }
    public void setCollectid(String collectid)
    {
        this.collectid=collectid;
    }
}
