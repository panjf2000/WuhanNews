package net.wutnews.app.app.act.menu;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import net.wutnews.app.R;
import net.wutnews.app.app.act.app.AppBaseAct;
import net.wutnews.app.app.entiy.ResponseBase;
import net.wutnews.app.app.entiy.SetSig;
import net.wutnews.app.app.util.uurl;
import net.wutnews.app.frame.IdoHttpUtil.HttpSender;
import net.wutnews.app.frame.IdoHttpUtil.OnHttpResListener;
import net.wutnews.app.frame.util.gsonUtil;
import net.wutnews.app.frame.widget.TitleBarView;

public class PostActivity extends AppBaseAct {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        TitleBarView titleBarView = (TitleBarView) findViewById(R.id.title_bar_view);
        View.OnClickListener onClickListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                EditText postcontent = (EditText) findViewById(R.id.post_content);
                String content = postcontent.getText().toString();
                postMessage(content);

            }
        };
        View.OnClickListener lonclickListener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        };
        titleBarView.setRightTextOnClickListener(onClickListener);
        titleBarView.setLeftImageOnClickListener(lonclickListener);
    }

    private void postMessage(String content){
        SetSig setSig = new SetSig();
        setSig.setUser(getUserinfo(this).getUser());
        setSig.setSignature(content);
        HttpSender sender = new HttpSender(uurl.SetSignature, "设置签名", setSig, new OnHttpResListener() {
            @Override
            public void doSuccess(String data) {

                ResponseBase tmp = gsonUtil.getInstance().json2Bean(data, ResponseBase.class);
                if (tmp.getStatus() == 200) {
                    if (tmp.getMsg().equals("SET_SUCCESS")) {
                        toast("个签设置成功!");

                    }
                }


            }
        });
        sender.setContext(this);
        sender.send(uurl.MODE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_post, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
