package net.wutnews.app.app.act.menu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import net.wutnews.app.R;
import net.wutnews.app.app.act.Login.LoginAct;
import net.wutnews.app.app.act.app.AppBaseAct;
import net.wutnews.app.app.act.news.NewsAct;
import net.wutnews.app.app.act.subscribe.SubscribeAct;
import net.wutnews.app.app.entiy.DBUserinfo;
import net.wutnews.app.app.util.dbUtil;
import net.wutnews.app.frame.util.FileUtil;

import java.io.File;

/**
 * Created by Pan on 2015/1/25 0025.
 */
public class MenuAct extends AppBaseAct implements View.OnClickListener {

    private LinearLayout ll_center, ll_about, ll_collection;
    private ImageView iv_img, iv_cache,iv_night;
    private TextView  tv_clear;
    private DBUserinfo userInfo;
    public static Handler mHandler;
    private PackageManager pm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DBUserinfo userInfo_NIGHT = getUserinfo(this);
        if (userInfo_NIGHT.isNightMode()) {
            this.setTheme(R.style.BrowserThemeNight);

        } else {
            this.setTheme(R.style.BrowserThemeDefault);
        }
        setContentView(R.layout.activity_menu);
        setTitleBar("设置");
        setBottomBar();
        userInfo = getUserinfo(this);
        findView();

        if (userInfo.isNotWifiImg()) {
            iv_img.setImageResource(R.drawable.setting_open);
        }
        if (userInfo.isNotWifiNewsCache()) {
            iv_cache.setImageResource(R.drawable.setting_open);
        }

        if (userInfo_NIGHT.isNightMode()) {
            iv_night.setImageResource(R.drawable.setting_open);

        } else {
            iv_night.setImageResource(R.drawable.setting_close);
        }

        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                finish();
                super.handleMessage(msg);
            }
        };
    }

    private void findView() {
        ll_center = (LinearLayout) findViewById(R.id.ll_center);
        ll_center.setOnClickListener(this);
        ll_about = (LinearLayout) findViewById(R.id.ll_about);
        ll_about.setOnClickListener(this);
        ll_collection = (LinearLayout) findViewById(R.id.ll_collection);
        ll_collection.setOnClickListener(this);
        iv_img = (ImageView) findViewById(R.id.iv_img);
        iv_img.setOnClickListener(this);
        iv_cache = (ImageView) findViewById(R.id.iv_cache);
        iv_cache.setOnClickListener(this);
        iv_night = (ImageView) findViewById(R.id.iv_night);
        iv_night.setOnClickListener(this);
        tv_clear = (TextView) findViewById(R.id.tv_clear);
        tv_clear.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            default:
                break;
            case R.id.ll_center:
                if(getUserinfo(this).getGuestLogin())
                {
                    //toast("你还未登陆，请先登陆！");
                    startActivity(new Intent(this, LoginAct.class));
                }
                else startActivity(new Intent(this, UserCenter.class));

                break;
            case R.id.ll_about:
                startActivity(new Intent(this, AboutAct.class));


                break;
            case R.id.ll_collection:


                break;
            case R.id.tv_clear:

                final AlertDialog.Builder builder = new AlertDialog.Builder(
                        this);
                // 添加按钮的单击事件
                // 设置显示信息
                builder.setMessage("是否清除缓存?")
                        .setIcon(MenuAct.this.getResources().getDrawable(R.drawable.error_icon)).
                                // 设置确定按钮
                                        setPositiveButton(
                                        "确定",
                                        new DialogInterface.OnClickListener() {
                                            // 单击事件
                                            public void onClick(DialogInterface dialog, int which) {

                                                FileUtil.deleteFilesByDirectory(new File(Environment.getExternalStorageDirectory()
                                                        + File.separator + "/ImageLoader/cache"));
                                                Toast.makeText(MenuAct.this,"已清除缓存",Toast.LENGTH_SHORT).show();
                                            }
                                        }).
                        // 设置取消按钮
                                setNegativeButton(
                                "取消",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(
                                            DialogInterface dialog,
                                            int which) {
                                    }
                                });
                // 创建对话框
                AlertDialog ad = builder.create();
                // 显示对话框
                ad.show();


                break;
            case R.id.iv_img:
                DBUserinfo userInfo_IMG = getUserinfo(this);
                if (userInfo_IMG.isNotWifiImg()) {
                    userInfo_IMG.setNotWifiImg(false);
                    iv_img.setImageResource(R.drawable.setting_close);
                } else {
                    userInfo_IMG.setNotWifiImg(true);
                    iv_img.setImageResource(R.drawable.setting_open);
                }
                dbUtil.updateByWhere(this, userInfo_IMG, "id = '" + userInfo.getId() + "'");

                break;
            case R.id.iv_cache:
                DBUserinfo userInfo_CACHE = getUserinfo(this);
                if (userInfo_CACHE.isNotWifiNewsCache()) {
                    userInfo_CACHE.setNotWifiNewsCache(false);
                    iv_cache.setImageResource(R.drawable.setting_close);
                } else {
                    userInfo_CACHE.setNotWifiNewsCache(true);
                    iv_cache.setImageResource(R.drawable.setting_open);
                }
                dbUtil.updateByWhere(this, userInfo_CACHE, "id = '" + userInfo.getId() + "'");
                break;
            case R.id.iv_night:

                DBUserinfo userInfo_NIGHT = getUserinfo(this);
                if (userInfo_NIGHT.isNightMode()) {
                    userInfo_NIGHT.setNightMode(false);
                    this.setTheme(R.style.BrowserThemeNight);
                    iv_night.setImageResource(R.drawable.setting_close);
                    Toast.makeText(this,"已切换至默认模式",Toast.LENGTH_SHORT).show();
                } else {
                    userInfo_NIGHT.setNightMode(true);

                    this.setTheme(R.style.BrowserThemeDefault);
                    iv_night.setImageResource(R.drawable.setting_open);
                    Toast.makeText(this,"已切换至夜间模式",Toast.LENGTH_SHORT).show();
                }
                dbUtil.updateByWhere(this, userInfo_NIGHT, "id = '" + userInfo.getId() + "'");
                Intent intent = new Intent(MenuAct.this, NewsAct.class);
                startActivity(intent);
                finish();
                break;

        }

    }

    private RadioButton rb1, rb2;

    private void setBottomBar() {
        rb1 = (RadioButton) findViewById(R.id.RadioButton00);
        rb2 = (RadioButton) findViewById(R.id.RadioButton01);

        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuAct.this, NewsAct.class);
                startActivity(intent);
                finish();
            }
        });

        rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuAct.this, SubscribeAct.class);
                startActivity(intent);
                finish();
            }
        });

    }

}
