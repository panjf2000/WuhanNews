package net.wutnews.app.app.act.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.lidroid.xutils.util.LogUtils;

import net.wutnews.app.R;
import net.wutnews.app.app.act.app.AppBaseAct;
import net.wutnews.app.app.act.news.NewsAct;
import net.wutnews.app.app.entiy.DBUserinfo;
import net.wutnews.app.app.entiy.GetLogin;
import net.wutnews.app.app.entiy.SetGuestLogin;
import net.wutnews.app.app.entiy.SetLogin;
import net.wutnews.app.app.util.dbUtil;
import net.wutnews.app.app.util.uurl;
import net.wutnews.app.frame.IdoHttpUtil.HttpSender;
import net.wutnews.app.frame.IdoHttpUtil.OnHttpResListener;
import net.wutnews.app.frame.util.AESEncryptionUtil;
import net.wutnews.app.frame.util.StringUtil;
import net.wutnews.app.frame.util.gsonUtil;

public class LoginAct extends AppBaseAct implements View.OnClickListener {

    private ImageView iv_head, qq, wecheat, weibo;
    private EditText edt_username, edt_password;
    private Button btn_login, btn_forget, btn_guest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitleBar("理工发布");
        findView();

//        edt_username.setText("214219");
//        edt_password.setText("061803");
    }

    private void findView() {
        iv_head = (ImageView) findViewById(R.id.iv_head);
        iv_head.setOnClickListener(this);
        edt_username = (EditText) findViewById(R.id.edt_username);
        edt_password = (EditText) findViewById(R.id.edt_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
        btn_forget = (Button) findViewById(R.id.btn_forget);
        btn_forget.setOnClickListener(this);
        btn_guest = (Button) findViewById(R.id.btn_guest);
        btn_guest.setOnClickListener(this);
        findViewById(R.id.qq).setOnClickListener(this);
        findViewById(R.id.wecheat).setOnClickListener(this);
        findViewById(R.id.weibo).setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_head:

                break;
            case R.id.btn_login:

                getLogin();
                break;
            case R.id.btn_forget:
                break;
            case R.id.btn_guest:
                getGuestLogin();
                break;
            case R.id.qq:

                break;
            case R.id.wecheat:

                break;
            case R.id.weibo:

                break;
            default:
                break;
        }
    }


    /**
     * 校园卡登录
     */
    private void getLogin() {

        if (StringUtil.isBlank(StringUtil.getEditText(edt_username))) {
            toast("请输入校园卡账号");
            return;
        }
        if (StringUtil.isBlank(StringUtil.getEditText(edt_password))) {
            toast("请输入密码");
            return;
        }
        String userinfo = "";
        try {

            userinfo = AESEncryptionUtil.encrypt(StringUtil.getEditText(edt_username) + ";" + StringUtil.getEditText(edt_password), uurl.AES_KEY);
            LogUtils.i(StringUtil.getEditText(edt_username) + ";" + StringUtil.getEditText(edt_password) + " 加密结果为: " + userinfo);
        } catch (Exception e) {
            e.printStackTrace();
            toast("用户名或密码错误");
            return;
        }

        SetLogin setLogin = new SetLogin();
        setLogin.setUserinfo(userinfo);
        setLogin.setDebug(true);

        HttpSender sender = new HttpSender(uurl.GetLogin, "登录接口", setLogin, new OnHttpResListener() {
            @Override
            public void doSuccess(String data) {
                LogUtils.i(data);
                GetLogin loginObj = gsonUtil.getInstance().json2Bean(data, GetLogin.class);
                if (loginObj.getMsg().equals("LOGIN_SUCCESS")) {
                    DBUserinfo userinfoData = new DBUserinfo();
                    userinfoData.setGuestLogin(false);
                    userinfoData.setId(loginObj.getData().getId());
                    userinfoData.setUser(loginObj.getData().getUser_auth());
                    dbUtil.deleteTable(LoginAct.this, DBUserinfo.class);
                    dbUtil.addData(LoginAct.this, userinfoData);
                    Intent i = new Intent(LoginAct.this, NewsAct.class);
                    startActivity(i);
                    finish();
                } else {
                    toast(loginObj.getMsg());
                }

            }
        });
        sender.setContext(this);
        sender.send(HttpSender.HttpMode.Post);

    }

    /**
     * 游客登录
     */
    private void getGuestLogin() {
        HttpSender sender = new HttpSender(uurl.GetGuestLogin, "游客登录", new SetGuestLogin(), new OnHttpResListener() {
            @Override
            public void doSuccess(String data) {
                LogUtils.i(data);
                GetLogin loginObj = gsonUtil.getInstance().json2Bean(data, GetLogin.class);
                if (loginObj.getMsg().equals("LOGIN_SUCCESS")) {
                    DBUserinfo userinfoData = new DBUserinfo();
                    userinfoData.setGuestLogin(true);
                    userinfoData.setId(loginObj.getData().getId());
                    userinfoData.setUser(loginObj.getData().getUser_auth());
                    dbUtil.deleteTable(LoginAct.this, DBUserinfo.class);
                    dbUtil.addData(LoginAct.this, userinfoData);
                    Intent i = new Intent(LoginAct.this, NewsAct.class);
                    startActivity(i);
                    finish();
                } else {
                    toast(loginObj.getMsg());
                }
            }
        });
        sender.setContext(this);
        sender.send(HttpSender.HttpMode.Post);
    }
}
