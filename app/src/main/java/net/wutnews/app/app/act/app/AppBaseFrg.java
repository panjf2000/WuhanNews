package net.wutnews.app.app.act.app;

import android.content.Context;

import net.wutnews.app.app.entiy.DBUserinfo;
import net.wutnews.app.app.util.dbUtil;
import net.wutnews.app.frame.base.BaseFragment;

/**
 * Created by Pan on 2015/1/25 0025.
 */
public class AppBaseFrg extends BaseFragment {
    @Override
    protected void initView() {

    }

    public DBUserinfo getUserinfo(Context context){
        if(dbUtil.selectAll(context, DBUserinfo.class).size()==0){
            return null;
        }
        return (DBUserinfo) dbUtil.selectAll(context, DBUserinfo.class).get(0);
    }
}
