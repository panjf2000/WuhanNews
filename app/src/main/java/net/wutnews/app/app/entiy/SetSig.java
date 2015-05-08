package net.wutnews.app.app.entiy;

/**
 * Created by Andy on 2015/3/30.
 */
public class SetSig {
    private String user,signature;
    @Override
    public String toString() {
        return "SetSig{" +
                "user='" + user + '\'' +
                ", signature='" + signature + '\'' +
                '}';
    }
    public void setUser(String user){
        this.user = user;
    }
    public String getUser(){
        return user;
    }
    public void setSignature(String signature){
        this.signature = signature;
    }
    public String getSignature(){
        return signature;
    }
}
