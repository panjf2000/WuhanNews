package net.wutnews.app.app.entiy;

/**
 * Created by Pan on 2015/1/22 0022.
 */
public class GetTermListData {

    private String term_id,name,smeta;
    private boolean subscribe;

    @Override
    public String toString() {
        return "GetTermListData{" +
                "term_id='" + term_id + '\'' +
                ", name='" + name + '\'' +
//                ", open_comment='" + open_comment + '\'' +
                ", smeta='" + smeta + '\'' +
                ", subscribe=" + subscribe +
                '}';
    }

    public boolean isSub() {
        return subscribe;
    }

    public void setSubscribe(boolean subscribe) {
        this.subscribe = subscribe;
    }

    public String getSmeta() {
        return smeta;
    }

    public void setSmeta(String smeta) {
        this.smeta = smeta;
    }

    public String getTerm_id() {
        return term_id;
    }

    public void setTerm_id(String term_id) {
        this.term_id = term_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   /* public String getOpen_comment() {
        return open_comment;
    }

    public void setOpen_comment(String open_comment) {
        this.open_comment = open_comment;
    }*/
}
