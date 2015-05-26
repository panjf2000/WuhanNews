package net.wutnews.app.app.act.menu;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidroid.xutils.util.LogUtils;

import net.wutnews.app.R;
import net.wutnews.app.app.act.app.AppBaseAct;
import net.wutnews.app.app.entiy.DBUserinfo;

import org.w3c.dom.Text;

public class AboutAct extends AppBaseAct implements View.OnClickListener {

    private LinearLayout ll_suggest;
    private TextView version;
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
        setContentView(R.layout.activity_about);
        setTitleBar("关于我们", 0);

        findView();
        if (userInfo.isNightMode()) {
            ll_nightframe.setVisibility(View.VISIBLE);
        } else {
            ll_nightframe.setVisibility(View.GONE);
        }
        version.setText("武汉理工大学新闻客户端 for Android " + this.getResources().getString(R.string.version_name));

    }

    private void findView() {
        ll_suggest = (LinearLayout) findViewById(R.id.ll_suggest);
        ll_suggest.setOnClickListener(this);
        version = (TextView) findViewById(R.id.version);
        ll_nightframe = (LinearLayout)findViewById(R.id.about_nightframe_ll);
    }


    @Override
    public void onClick(View v) {

    }
}
