package net.wutnews.app.app.entiy;

/**
 * Created by Pan on 2015/1/22 0022.
 */
public class SetNewsList {

    private String term, page, user;

    @Override
    public String toString() {
        return "SetNewsList{" +
                "term='" + term + '\'' +
                ", page='" + page + '\'' +
                ", user='" + user + '\'' +
                '}';
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}
