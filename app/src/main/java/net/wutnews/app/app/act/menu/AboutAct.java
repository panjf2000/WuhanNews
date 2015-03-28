package net.wutnews.app.app.act.menu;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidroid.xutils.util.LogUtils;

import net.wutnews.app.R;
import net.wutnews.app.app.act.app.AppBaseAct;

import org.w3c.dom.Text;

public class AboutAct extends AppBaseAct implements View.OnClickListener {

    private LinearLayout ll_suggest;
    private TextView version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setTitleBar("关于我们", 0);

        findView();

        version.setText("武汉理工大学新闻客户端 for Android " + this.getResources().getString(R.string.version_name));

    }

    private void findView() {
        ll_suggest = (LinearLayout) findViewById(R.id.ll_suggest);
        ll_suggest.setOnClickListener(this);
        version = (TextView) findViewById(R.id.version);
    }


    @Override
    public void onClick(View v) {

    }
}
