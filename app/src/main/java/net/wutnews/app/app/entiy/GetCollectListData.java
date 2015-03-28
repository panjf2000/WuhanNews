package net.wutnews.app.app.entiy;

/**
 * Created by Pan on 2015/3/8.
 */
public class GetCollectListData {

    private String id,term_name,title,createtime,url,smeta;
    private boolean isDelete,isVisible;

    @Override
    public String toString() {
        return "GetCollectListData{" +
                "id='" + id + '\'' +
                ", term_name='" + term_name + '\'' +
                ", title='" + title + '\'' +
                ", createtime='" + createtime + '\'' +
                ", url='" + url + '\'' +
                ", smeta='" + smeta + '\'' +
                ", isDelete=" + isDelete +
                ", isVisible=" + isVisible +
                '}';
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTerm_name() {
        return term_name;
    }

    public void setTerm_name(String term_name) {
        this.term_name = term_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSmeta() {
        return smeta;
    }

    public void setSmeta(String smeta) {
        this.smeta = smeta;
    }
}
