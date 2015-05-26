package net.wutnews.app.app.act.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.lidroid.xutils.util.LogUtils;
import net.wutnews.app.R;
import net.wutnews.app.app.act.Login.LoginAct;
import net.wutnews.app.app.act.app.AppBaseAct;
import net.wutnews.app.app.entiy.DBUserinfo;
import net.wutnews.app.app.entiy.GetUserDe;
import net.wutnews.app.app.entiy.GetUserInfo;
import net.wutnews.app.app.entiy.SetUser;
import net.wutnews.app.app.util.dbUtil;
import net.wutnews.app.app.util.uurl;
import net.wutnews.app.frame.IdoHttpUtil.HttpSender;
import net.wutnews.app.frame.IdoHttpUtil.OnHttpResListener;
import net.wutnews.app.frame.util.ImageLoaderUtil;
import net.wutnews.app.frame.util.gsonUtil;

public class UserCenter extends AppBaseAct implements View.OnClickListener {

    private TextView tv_name, tv_sign;
    private ImageView iv_head;
    private GetUserDe uData;
    private ImageLoaderUtil imageUtil;
    private DBUserinfo userInfo;
    private LinearLayout ll_nightframe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userInfo = getUserinfo(this);
        if (userInfo.isNightMode()) {
            this.setTheme(R.style.ThemeNight);

        } else {
            this.setTheme(R.style.ThemeDefault);
        }
        setContentView(R.layout.activity_user_center);
        imageUtil = ImageLoaderUtil.getInstance();
        setTitleBar("个人中心", 0);
        findView();
        if (userInfo.isNightMode()) {
            ll_nightframe.setVisibility(View.VISIBLE);
        } else {
            ll_nightframe.setVisibility(View.GONE);
        }
        getUserInfo();
    }

    private void findView() {
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_sign = (TextView) findViewById(R.id.tv_sign);
        iv_head = (ImageView) findViewById(R.id.iv_head);
        ll_nightframe = (LinearLayout)findViewById(R.id.uc_nightframe_ll);
        iv_head.setOnClickListener(this);
        findViewById(R.id.ll_my_comment).setOnClickListener(this);
        findViewById(R.id.ll_my_collection).setOnClickListener(this);
        findViewById(R.id.edt_profile).setOnClickListener(this);
        findViewById(R.id.ll_logout).setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.iv_head:
                Intent hIntent=new Intent(this, AddPhotoActivity.class);
                startActivity(hIntent);
                break;
            case R.id.ll_my_comment:

                Intent intent = new Intent(this, MyComment.class);
                intent.putExtra("nickname",uData.getUser_nicename());
                startActivity(intent);
                break;
            case R.id.ll_my_collection:

                Intent i = new Intent(this, MyCollection.class);
                startActivity(i);
                break;
            case R.id.edt_profile:
                Intent pIntent = new Intent(this, PostActivity.class);
                pIntent.putExtra("nickname",uData.getUser_nicename());
                pIntent.putExtra("signature",uData.getSignature());
                startActivity(pIntent);
                break;
            case R.id.ll_logout:
                dbUtil.deleteTable(this, DBUserinfo.class);
                MenuAct.mHandler.sendEmptyMessage(0);
                startActivity(new Intent(this, LoginAct.class));
                finish();
                break;
        }
    }
    private void getUserInfo()
    {
        SetUser set = new SetUser();
        set.setUser(getUserinfo(this).getUser());
        HttpSender sender = new HttpSender(uurl.GetUserInfo, "获取个人信息", set, new OnHttpResListener() {
            @Override
            public void doSuccess(String data) {
                LogUtils.i("我的信息list: " + data.toString());
                GetUserInfo temp = gsonUtil.getInstance().json2Bean(data, GetUserInfo.class);
                if (temp.getMsg().equals("GET_SUCCESS")) {
                    uData = temp.getData();
                    tv_name.setText(uData.getUser_nicename());
                    tv_sign.setText(uData.getSignature());
                    if(uData.getAvatar()!=null)
                    imageUtil.setImage(uurl.IMG_URL + uData.getAvatar(), iv_head);

                }
            }
        });
        sender.setContext(this);
        sender.send(uurl.MODE);
    }
}
