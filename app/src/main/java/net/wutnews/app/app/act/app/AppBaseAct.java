package net.wutnews.app.app.act.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import net.wutnews.app.R;
import net.wutnews.app.app.act.menu.MenuAct;
import net.wutnews.app.app.act.news.NewsAct;
import net.wutnews.app.app.act.subscribe.SubscribeAct;
import net.wutnews.app.app.entiy.DBUserinfo;
import net.wutnews.app.app.util.dbUtil;
import net.wutnews.app.frame.base.BaseActivity;

/**
 * Created by Pan on 2015/1/25 0025.
 */
public class AppBaseAct extends BaseActivity {

    public TextView tv_title, tv_edit;
    public ImageView iv_back;



    public DBUserinfo getUserinfo(Context context){
        if(dbUtil.selectAll(context, DBUserinfo.class).size()==0){
            return null;
        }
        return (DBUserinfo) dbUtil.selectAll(context, DBUserinfo.class).get(0);
    }

    @Override
    protected void initView() {


    }

    /**
     * 设置标题栏
     *
     * @param title 标题
     */
    public void setTitleBar(String title) {

        tv_title = (TextView) findViewById(R.id.titlebar_title);
        tv_edit = (TextView) findViewById(R.id.titlebar_edit);
        iv_back = (ImageView) findViewById(R.id.index_back);
        tv_title.setText(title);

    }

    /**
     * 设置标题栏
     *
     * @param title  标题
     * @param isBack 是否显示返回按钮并且设置图标从drawable中获取 0为使用默认图标
     */
    public void setTitleBar(String title, int isBack) {

        tv_title = (TextView) findViewById(R.id.titlebar_title);
        tv_edit = (TextView) findViewById(R.id.titlebar_edit);
        iv_back = (ImageView) findViewById(R.id.index_back);
        tv_title.setText(title);
        if (isBack != 0) {
            iv_back.setImageResource(isBack);
        }
        iv_back.setVisibility(View.VISIBLE);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }



}
