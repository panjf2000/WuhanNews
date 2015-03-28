package net.wutnews.app.app.act.Login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.ab.image.AbImageLoader;

import net.wutnews.app.R;
import net.wutnews.app.app.act.app.AppBaseAct;
import net.wutnews.app.app.act.news.NewsAct;
import net.wutnews.app.app.entiy.DBSubscribeStatus;
import net.wutnews.app.app.util.dbUtil;

import java.util.ArrayList;
import java.util.List;

public class HelloAct extends AppBaseAct {

    private Intent i = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
        mHandler.sendEmptyMessageAtTime(0, 500);

    }

    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            if (getUserinfo(HelloAct.this) != null) {
                if (!getUserinfo(HelloAct.this).getGuestLogin()) {
                    i = new Intent(HelloAct.this, NewsAct.class);
                } else {
                    i = new Intent(HelloAct.this, LoginAct.class);
                }
//                i = new Intent(HelloAct.this, NewsAct.class);
            } else {
                i = new Intent(HelloAct.this, LoginAct.class);
            }


            startActivity(i);
            finish();
        }
    };



}
