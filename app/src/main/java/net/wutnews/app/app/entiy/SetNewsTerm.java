package net.wutnews.app.app.entiy;

/**
 * Created by Andy on 2015/3/27.
 */
public class SetNewsTerm {
    private String user,termid;
    @Override
    public String toString() {
        return "SetNewsTerm{" +
                "termid='" + termid + '\'' +
                ", user='" + user + '\'' +
                '}';
    }
    public String getUser()
    {
        return user;
    }
    public void setUser(String user)
    {
        this.user = user;
    }
    public String getTermid()
    {
        return termid;
    }
    public void setTermid(String termid)
    {
        this.termid=termid;
    }

}
