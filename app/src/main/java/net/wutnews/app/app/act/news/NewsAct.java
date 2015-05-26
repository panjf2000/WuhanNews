package net.wutnews.app.app.act.news;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;
import com.ab.view.sliding.AbSlidingTabView;
import com.lidroid.xutils.util.LogUtils;
import net.wutnews.app.R;
import net.wutnews.app.app.act.app.AppBaseAct;
import net.wutnews.app.app.act.menu.MenuAct;
import net.wutnews.app.app.act.subscribe.SubscribeAct;
import net.wutnews.app.app.entiy.DBUserinfo;
import net.wutnews.app.app.entiy.GetTermList;
import net.wutnews.app.app.entiy.GetTermListData;
import net.wutnews.app.app.entiy.SetUser;
import net.wutnews.app.app.util.uurl;
import net.wutnews.app.frame.IdoHttpUtil.HttpSender;
import net.wutnews.app.frame.IdoHttpUtil.OnHttpResListener;
import net.wutnews.app.frame.util.gsonUtil;

import java.util.ArrayList;
import java.util.List;

public class NewsAct extends AppBaseAct {
    private AbSlidingTabView mAbSlidingTabView;
    private List<GetTermListData> list;
    private List<Fragment> mFragments;
    private List<String> tabTexts;
    private static boolean isExit = false;
    private DBUserinfo userInfo;
    private LinearLayout ll_nightframe;
    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };
//    private List<DBSubscribeStatus> dbSub;

    @Override
    protected void initView() {
        mAbSlidingTabView = (AbSlidingTabView) findViewById(R.id.mAbSlidingTabView);
        //缓存数量
        mAbSlidingTabView.getViewPager().setOffscreenPageLimit(4);
        ll_nightframe = (LinearLayout)findViewById(R.id.na_nightframe_ll);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userInfo = getUserinfo(this);
        if (userInfo.isNightMode()) {
            this.setTheme(R.style.ThemeNight);

        } else {
            this.setTheme(R.style.ThemeDefault);
        }
        setAbContentView(R.layout.activity_news);

        setTitleBar("首页");
        setBottomBar();
        initView();
        if (userInfo.isNightMode()) {
            ll_nightframe.setVisibility(View.VISIBLE);
        } else {
            ll_nightframe.setVisibility(View.GONE);
        }
        getTermList();
    }

    private void getTermList() {

        SetUser set = new SetUser();
        set.setUser(getUserinfo(this).getUser());

        HttpSender sender = new HttpSender(uurl.GetTermList, "获取栏目列表", set, new OnHttpResListener() {
            @Override
            public void doSuccess(String data) {
                mFragments = new ArrayList<>();
                tabTexts = new ArrayList<>();
                GetTermList datalIST = gsonUtil.getInstance().json2Bean(data, GetTermList.class);
                list = datalIST.getData();
                if (list.size() == 0) {
                    toast("您还没有订阅任何新闻!");
                }

//                dbSub = (List<DBSubscribeStatus>) dbUtil.selectAll(NewsAct.this, DBSubscribeStatus.class);
//                LogUtils.e("订阅数量: " + dbSub.size());
//
//                if (dbSub.size() == 0) {
//                    LogUtils.e("新订阅");
//                    for (int x = 0; x < list.size(); x++) {
//                        DBSubscribeStatus set = new DBSubscribeStatus();
//                        set.setNo(list.get(x).getTerm_id());
//                        if (x < list.size() / 2) {
//                            set.setSub(true);
//                        } else {
//                            set.setSub(false);
//                        }
//                        set.setImg(list.get(x).getSmeta());
//                        dbUtil.addData(NewsAct.this, set);
//                        dbSub.add(set);
//                    }
//                } else if (dbSub.size() < list.size()) {
//                    LogUtils.e("补充订阅");
//                    for (int x = 0; x < list.size(); x++) {
//                        List<DBSubscribeStatus> temp = (List<DBSubscribeStatus>) dbUtil.selectByWhere(NewsAct.this, DBSubscribeStatus.class, "no='" + list.get(x).getTerm_id() + "'");
//                        if (temp.size() == 0) {
//                            DBSubscribeStatus set = new DBSubscribeStatus();
//                            set.setSub(false);
//                            set.setNo(list.get(x).getTerm_id());
//                            set.setImg(list.get(x).getSmeta());
//                            dbUtil.addData(NewsAct.this, set);
//                            dbSub.add(set);
//                        }
//
//                    }

//                } else {
//                    LogUtils.e("订阅数据库: " + dbSub.toString());
//                }


                for (int x = 0; x <list.size(); x++) { //list.size()


//                    if (dbSub.get(x).getSub()) {
                    LogUtils.e("流程正常" + list.get(x).getName());
                    if(list.get(x).isSub()) {
                        if (!list.get(x).getTerm_id().equals("2")) {
                            mFragments.add(new NewsFrgText(list.get(x).getTerm_id()));
                        } else {
                            mFragments.add(new NewsFrgImage(list.get(x).getTerm_id()));
                        }

                        tabTexts.add(list.get(x).getName());
                    }
//                    }
                }
                //设置样式
                mAbSlidingTabView.setTabTextColor(Color.WHITE);
                mAbSlidingTabView.setTabTextSize(36);
                mAbSlidingTabView.setTabSelectColor(Color.WHITE);
                mAbSlidingTabView.setTabBackgroundResource(R.drawable.tab_bg);
                mAbSlidingTabView.setTabLayoutBackgroundResource(R.drawable.px_app_color);
                mAbSlidingTabView.setTabPadding(20, 8, 20, 8);
                mAbSlidingTabView.addItemViews(tabTexts, mFragments);

            }

        });
        sender.setContext(this);
        sender.send(uurl.MODE);

    }


    private RadioButton rb2, rb3;

    private void setBottomBar() {
        rb2 = (RadioButton) findViewById(R.id.RadioButton01);
        rb3 = (RadioButton) findViewById(R.id.RadioButton02);


        rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewsAct.this, SubscribeAct.class);
                startActivity(intent);
                finish();
            }
        });

        rb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewsAct.this, MenuAct.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            finish();
            System.exit(0);
        }
    }
}
