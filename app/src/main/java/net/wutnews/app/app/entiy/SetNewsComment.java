package net.wutnews.app.app.entiy;

/**
 * Created by Andy on 2015/3/27.
 */
public class SetNewsComment {
    private String user,postid,content,termid;
    @Override
    public String toString() {
        return "SetNewsComment{" +
                "user='" + user + '\'' +
                ", postid='" + postid + '\'' +
                ", content='" + content + '\'' +
                ", termid='" + termid + '\'' +
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
    public String getPostid()
    {
        return postid;
    }
    public void setPostid(String postid)
    {
        this.postid = postid;
    }
    public String getContent()
    {
        return this.content;
    }
    public void setContent(String content)
    {
        this.content=content;
    }
    public String getTermid()
    {
        return termid;
    }
    public void setTermid(String termid)
    {this.termid=termid;}

}
