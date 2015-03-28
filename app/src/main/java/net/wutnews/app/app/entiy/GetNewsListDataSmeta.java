package net.wutnews.app.app.entiy;

/**
 * Created by Pan on 2015/1/22 0022.
 */
public class GetNewsListDataSmeta {

    private String thumb;

    @Override
    public String toString() {
        return "GetNewsListDataSmeta{" +
                "thumb='" + thumb + '\'' +
                '}';
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }
}
