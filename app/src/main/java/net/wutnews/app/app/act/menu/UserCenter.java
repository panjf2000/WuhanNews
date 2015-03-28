package net.wutnews.app.app.act.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.DbUtils;

import net.wutnews.app.R;
import net.wutnews.app.app.act.Login.LoginAct;
import net.wutnews.app.app.act.app.AppBaseAct;
import net.wutnews.app.app.entiy.DBUserinfo;
import net.wutnews.app.app.util.dbUtil;

public class UserCenter extends AppBaseAct implements View.OnClickListener {

    private TextView tv_name, tv_sign;
    private ImageView iv_head;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_center);

        setTitleBar("个人中心", 0);

        findView();

    }

    private void findView() {
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_sign = (TextView) findViewById(R.id.tv_sign);
        iv_head = (ImageView) findViewById(R.id.iv_head);
        iv_head.setOnClickListener(this);
        findViewById(R.id.ll_my_comment).setOnClickListener(this);
        findViewById(R.id.ll_my_collection).setOnClickListener(this);
        findViewById(R.id.ll_logout).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.iv_head:


                break;
            case R.id.ll_my_comment:


                break;
            case R.id.ll_my_collection:

                Intent i = new Intent(this, MyCollection.class);
                startActivity(i);
                break;
            case R.id.ll_logout:
                dbUtil.deleteTable(this, DBUserinfo.class);
                MenuAct.mHandler.sendEmptyMessage(0);
                startActivity(new Intent(this, LoginAct.class));
                finish();
                break;
        }
    }
}
