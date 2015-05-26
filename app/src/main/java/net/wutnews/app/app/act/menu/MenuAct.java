package net.wutnews.app.app.act.menu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.SeekBar;
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

    private LinearLayout ll_center, ll_about, ll_collection,ll_nightframe;
    private ImageView iv_img, iv_cache,iv_night;
    private TextView  tv_clear;
    private SeekBar mseekbar;
    private DBUserinfo userInfo;
    public static Handler mHandler;
    private static boolean blFlag = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userInfo = getUserinfo(this);
        //设置夜间模式主题
        /*SharedPreferences preferences = getSharedPreferences("default_night",
                MODE_PRIVATE);
        blFlag = preferences.getBoolean("default_night",true);
        if (blFlag) {
            this.setTheme(R.style.ThemeNight);
        }
        else {
            this.setTheme(R.style.ThemeDefault);
        }*/
       if (userInfo.isNightMode()) {
            this.setTheme(R.style.ThemeNight);

        } else {
            this.setTheme(R.style.ThemeDefault);
        }
        setContentView(R.layout.activity_menu);
        setTitleBar("设置");
        setBottomBar();

        findView();

        if (userInfo.isNotWifiImg()) {
            iv_img.setImageResource(R.drawable.setting_open);
        }
        if (userInfo.isNotWifiNewsCache()) {
            iv_cache.setImageResource(R.drawable.setting_open);
        }
        //设置夜间模式按钮
        if (userInfo.isNightMode()) {
            iv_night.setImageResource(R.drawable.setting_open);
            ll_nightframe.setVisibility(View.VISIBLE);
        } else {
            iv_night.setImageResource(R.drawable.setting_close);
            ll_nightframe.setVisibility(View.GONE);
        }
        /*if (blFlag) {
            iv_night.setImageResource(R.drawable.setting_open);
            ll_nightframe.setVisibility(View.VISIBLE);
        }
        else {
            iv_night.setImageResource(R.drawable.setting_close);
            ll_nightframe.setVisibility(View.GONE);
        }*/
        //设置字体大小
        mseekbar.setProgress(userInfo.getTextSize());

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
        mseekbar = (SeekBar)findViewById(R.id.seekbar);
        mseekbar.setOnSeekBarChangeListener(mSeekbarListener);
        ll_nightframe = (LinearLayout)findViewById(R.id.nightframe_ll);
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
                    this.setTheme(R.style.ThemeDefault);
                    iv_night.setImageResource(R.drawable.setting_close);
                    ll_nightframe.setVisibility(View.GONE);
                    Toast.makeText(this,"已切换至默认模式",Toast.LENGTH_SHORT).show();
                } else {
                    userInfo_NIGHT.setNightMode(true);

                    this.setTheme(R.style.ThemeNight);
                    iv_night.setImageResource(R.drawable.setting_open);
                    ll_nightframe.setVisibility(View.VISIBLE);
                    Toast.makeText(this,"已切换至夜间模式",Toast.LENGTH_SHORT).show();
                }
                dbUtil.updateByWhere(this, userInfo_NIGHT, "id = '" + userInfo.getId() + "'");

                /*SharedPreferences preferences = getSharedPreferences("default_night",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                if (blFlag) {
                    this.setTheme(R.style.ThemeDefault);
                    iv_night.setImageResource(R.drawable.setting_close);
                    ll_nightframe.setVisibility(View.GONE);
                    Toast.makeText(this,"已切换至默认模式",Toast.LENGTH_SHORT).show();
                    blFlag = true;
                    editor.putBoolean("default_night",true);
                } else {
                    this.setTheme(R.style.ThemeNight);
                    iv_night.setImageResource(R.drawable.setting_open);
                    ll_nightframe.setVisibility(View.VISIBLE);
                    Toast.makeText(this,"已切换至夜间模式",Toast.LENGTH_SHORT).show();
                    blFlag =false;
                    editor.putBoolean("default_night",false);

                }
                // 提交修改
                editor.commit();*/
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

    private SeekBar.OnSeekBarChangeListener mSeekbarListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            DBUserinfo userInfo_TEXTSIZE = getUserinfo(MenuAct.this);
            int min = 15;
            int max = 40;
            int mtextsize = 20;
            String standard = "";
            userInfo_TEXTSIZE.setTextSize(i);//保存字体大小seekbar进度条位置
            dbUtil.updateByWhere(MenuAct.this, userInfo_TEXTSIZE, "id = '" + userInfo.getId() + "'");
            if(i>=0&&i<20){mtextsize = min + ((max - min)*0);standard = "最小";}
            if(i>=20&&i<40){mtextsize = min + (int)((max - min)*0.2);standard = "小";}
            if(i>=40&&i<60){mtextsize = min + (int)((max - min)*0.4);standard = "标准";}
            if(i>=60&&i<80){mtextsize = min + (int)((max - min)*0.6);standard = "大";}
            if(i>=80&&i<=100){mtextsize = min + (int)((max - min)*0.8);standard = "最大";}

            mtextsize = min + (int)((max - min)*(float)i/(float)100);
            Toast.makeText(MenuAct.this,"字体大小："+standard,Toast.LENGTH_SHORT).show();
        }
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
}
