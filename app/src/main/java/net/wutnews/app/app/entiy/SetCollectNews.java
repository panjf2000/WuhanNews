package net.wutnews.app.app.entiy;

/**
 * Created by Administrator on 2015/2/15 0015.
 */
public class SetCollectNews {

    private String user,postid,termid;

    @Override
    public String toString() {
        return "SetCollectNews{" +
                "user='" + user + '\'' +
                ", postid='" + postid + '\'' +
                ", termid='" + termid + '\'' +
                '}';
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public String getTermid() {
        return termid;
    }

    public void setTermid(String termid) {
        this.termid = termid;
    }
}
