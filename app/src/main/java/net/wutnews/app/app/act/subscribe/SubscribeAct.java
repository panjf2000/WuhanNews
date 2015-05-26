package net.wutnews.app.app.act.subscribe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.lidroid.xutils.util.LogUtils;

import net.wutnews.app.R;
import net.wutnews.app.app.act.app.AppBaseAct;
import net.wutnews.app.app.act.menu.MenuAct;
import net.wutnews.app.app.act.news.NewsAct;
import net.wutnews.app.app.adapter.SubscribeAdapter;
import net.wutnews.app.app.entiy.DBSubscribeStatus;
import net.wutnews.app.app.entiy.DBUserinfo;
import net.wutnews.app.app.entiy.GetTermList;
import net.wutnews.app.app.entiy.GetTermListData;
import net.wutnews.app.app.entiy.ResponseBase;
import net.wutnews.app.app.entiy.SetNewsTerm;
import net.wutnews.app.app.entiy.SetUser;
import net.wutnews.app.app.util.dbUtil;
import net.wutnews.app.app.util.uurl;
import net.wutnews.app.frame.IdoHttpUtil.HttpSender;
import net.wutnews.app.frame.IdoHttpUtil.OnHttpResListener;
import net.wutnews.app.frame.util.gsonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pan on 2015/1/25 0025.
 */
public class SubscribeAct extends AppBaseAct implements View.OnClickListener {

    private GridView gridView;
    private List<GetTermListData> dbSub;
    private SubscribeAdapter adapter;
    private String msg;
    private LinearLayout ll_nightframe;
    //private boolean blFlag;
    private DBUserinfo userInfo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*SharedPreferences preferences = getSharedPreferences("default_night",
                MODE_PRIVATE);
        blFlag = preferences.getBoolean("default_night",true);
        if (blFlag) {
            this.setTheme(R.style.ThemeNight);
        }
        else {
            this.setTheme(R.style.ThemeDefault);
        }*/

        //设置夜间模式主题
        userInfo = getUserinfo(this);
        if (userInfo.isNightMode()) {
            this.setTheme(R.style.ThemeNight);

        } else {
            this.setTheme(R.style.ThemeDefault);
        }
        setContentView(R.layout.activity_subscribe);
        setTitleBar("订阅");
        setBottomBar();
        findView();
        //设置夜间模式
        if (userInfo.isNightMode()) {
            ll_nightframe.setVisibility(View.VISIBLE);
        } else {
            ll_nightframe.setVisibility(View.GONE);
        }
        getAllSub();
    }

    private String opSub(String termid,boolean isSub)
    {
        SetNewsTerm set = new SetNewsTerm();
        set.setUser(getUserinfo(this).getUser());
        set.setTermid(termid);
        String url;
        if(isSub)
        {
            url=uurl.SubscribeTerm;
        }
        else url=uurl.UnsubscribeTerm;
        HttpSender sender = new HttpSender(url, "订阅或取消栏目", set, new OnHttpResListener() {
            @Override
            public void doSuccess(String data) {
                ResponseBase datalIST = gsonUtil.getInstance().json2Bean(data, ResponseBase.class);
                if (datalIST.getMsg().equals("SUBSCRIBE_SUCCESS")) {
                    msg="SUBSCRIBE_SUCCESS";
                }
                else msg=datalIST.getMsg();
            }

        });
        sender.setContext(this);
        sender.send(uurl.MODE);
        return msg;
    }

    private void findView() {
        gridView = (GridView) findViewById(R.id.sub_gv);
        ll_nightframe = (LinearLayout)findViewById(R.id.sub_nightframe_ll);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LogUtils.i("改变前dbSub.get(position): " + dbSub.get(position).toString());
                if (dbSub.get(position).isSub()) {
                    dbSub.get(position).setSubscribe(false);
                    toast(opSub(dbSub.get(position).getTerm_id(),false));

                } else {
                    dbSub.get(position).setSubscribe(true);
                    toast(opSub(dbSub.get(position).getTerm_id(),true));
                }
                adapter.notifyDataSetChanged();
            }

        });
    }

    @Override
    public void onClick(View v) {

    }

    private RadioButton rb1, rb3;

    private void setBottomBar() {
        rb1 = (RadioButton) findViewById(R.id.RadioButton00);
        rb3 = (RadioButton) findViewById(R.id.RadioButton02);

        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubscribeAct.this, NewsAct.class);
                startActivity(intent);
                finish();
            }
        });
        rb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubscribeAct.this, MenuAct.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void getAllSub() {
        SetUser set = new SetUser();
        set.setUser(getUserinfo(this).getUser());
        HttpSender sender = new HttpSender(uurl.GetTermList, "获取栏目列表", set, new OnHttpResListener() {
            @Override
            public void doSuccess(String data) {
                GetTermList datalIST = gsonUtil.getInstance().json2Bean(data, GetTermList.class);
                if (datalIST.getStatus().equals("200")) {
                    dbSub = datalIST.getData();
                    if (dbSub.size() == 0) {
                        toast("您还没有订阅任何新闻!");
                    } else {
                        adapter = new SubscribeAdapter(SubscribeAct.this, dbSub);
                        gridView.setAdapter(adapter);
                    }
                }


            }

        });
        sender.setContext(this);
        sender.send(uurl.MODE);


    }
}
