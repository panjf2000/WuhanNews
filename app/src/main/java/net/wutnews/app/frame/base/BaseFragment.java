package net.wutnews.app.frame.base;

import android.content.Context;
import android.widget.Toast;

import com.ab.fragment.AbFragment;

import net.wutnews.app.app.entiy.DBUserinfo;
import net.wutnews.app.app.util.dbUtil;

/**
 * Created by Pan on 2015/1/20 0020.
 */
public abstract class BaseFragment extends AbFragment {


    /**
     * 初始化控件
     */
    protected abstract void initView();

    public DBUserinfo getUserinfo(Context context){
        if(dbUtil.selectAll(context, DBUserinfo.class).size()==0){
            return null;
        }
        return (DBUserinfo) dbUtil.selectAll(context, DBUserinfo.class).get(0);
    }

    private Toast toast = null;
    /**
     * toast方法,零延时切换显示消息
     *
     * @param msg 要显示的消息
     */
    public void toast(String msg) {
        if (null == msg || msg.equals("")) {
            return;
        }
        if (null == toast)
            toast = Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT);
        else
            toast.setText(msg);
        toast.show();
    }




}
