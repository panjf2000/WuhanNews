package net.wutnews.app.app.entiy;

/**
 * Created by Pan on 2015/1/27 0027.
 */
public class DBSubscribeStatus {

    private int id;
    private boolean sub = false;
    private String img;
    private String no;

    public DBSubscribeStatus() {
    }

    public DBSubscribeStatus(boolean sub, String img, String no) {
        this.sub = sub;
        this.img = img;
        this.no = no;
    }

    @Override
    public String toString() {
        return "DBSubscribeStatus{" +
                "id='" + id + '\'' +
                ", sub=" + sub +
                ", img='" + img + '\'' +
                ", no='" + no + '\'' +
                '}';
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public boolean getSub() {
        return sub;
    }

    public void setSub(boolean sub) {
        this.sub = sub;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public boolean isSub() {
        return sub;
    }
}
