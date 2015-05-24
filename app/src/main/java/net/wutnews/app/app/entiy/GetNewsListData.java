package net.wutnews.app.app.entiy;

/**
 * Created by Pan on 2015/1/22 0022.
 */
public class GetNewsListData {

    private String id;
    private String post_title;
    private String post_src;
    private String post_excerpt;
    private String post_modified;
    private String post_type;
    private String post_link;
    private boolean post_collect;
    @Override
    public String toString() {
        return "GetNewsListData{" +
                "id='" + id + '\'' +
                ", post_title='" + post_title + '\'' +
                ", post_src='" + post_src + '\'' +
                ", post_excerpt='" + post_excerpt + '\'' +
                ", post_modified='" + post_modified + '\'' +
                ", post_type='" + post_type + '\'' +
                ", post_link='" + post_link + '\'' +
                ", post_collect='" + post_collect + '\'' +
                ", smeta=" + smeta +
                '}';
    }

    public boolean getPost_collect() {
        return post_collect;
    }

    public void setPost_collect(boolean post_collect) {
        this.post_collect = post_collect;
    }


    private GetNewsListDataSmeta smeta;

    public String getSmeta() {
        return smeta.getThumb();
    }

    public void setSmeta(GetNewsListDataSmeta smeta) {
        this.smeta = smeta;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPost_title() {
        return post_title;
    }

    public void setPost_title(String post_title) {
        this.post_title = post_title;
    }

    public String getPost_src() {
        return post_src;
    }

    public void setPost_src(String post_src) {
        this.post_src = post_src;
    }

    public String getPost_excerpt() {
        return post_excerpt;
    }

    public void setPost_excerpt(String post_excerpt) {
        this.post_excerpt = post_excerpt;
    }

    public String getPost_modified() {
        return post_modified;
    }

    public void setPost_modified(String post_modified) {
        this.post_modified = post_modified;
    }

    public String getPost_type() {
        return post_type;
    }

    public void setPost_type(String post_type) {
        this.post_type = post_type;
    }

    public String getPost_link() {
        return post_link;
    }

    public void setPost_link(String post_link) {
        this.post_link = post_link;
    }
}
