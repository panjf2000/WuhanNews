package net.wutnews.app.app.entiy;

/**
 * Created by Andy on 2015/3/30.
 */
public class GetCommentListData {
    private String id,title,content,createtime,url;

    @Override
    public String toString() {
        return "GetCommentListData{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", createtime='" + createtime + '\'' +
                ", url='" + url + '\'' +
//                ", smeta='" + smeta + '\'' +
                '}';
    }
    public void setId(String id){
        this.id=id;
    }
    public String getId(){
        return this.id;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }
    public void setContent(String content){
        this.content = content;
    }
    public String getContent(){
        return content;
    }
    public void setUrl(String url){
        this.url=url;
    }
    public String getUrl(){
        return url;
    }
    public void setCreatetime(String createtime){
        this.createtime = createtime;
    }
    public String getCreatetime(){
        return this.createtime;
    }
}
