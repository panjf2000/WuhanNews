package net.wutnews.app.app.act.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.lidroid.xutils.util.LogUtils;

import net.wutnews.app.R;
import net.wutnews.app.app.act.app.AppBaseAct;
import net.wutnews.app.app.act.news.NewsDetail;
import net.wutnews.app.app.adapter.MyCollectionAdapter;
import net.wutnews.app.app.entiy.DBUserinfo;
import net.wutnews.app.app.entiy.DeleteCollection;
import net.wutnews.app.app.entiy.GetCollectList;
import net.wutnews.app.app.entiy.GetCollectListData;
import net.wutnews.app.app.entiy.ResponseBase;
import net.wutnews.app.app.entiy.SetUser;
import net.wutnews.app.app.util.uurl;
import net.wutnews.app.frame.IdoHttpUtil.HttpSender;
import net.wutnews.app.frame.IdoHttpUtil.OnHttpResListener;
import net.wutnews.app.frame.util.gsonUtil;

import java.util.ArrayList;
import java.util.List;

public class MyCollection extends AppBaseAct implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ListView lv;
    private MyCollectionAdapter adapter;
    private List<GetCollectListData> list;
    private LinearLayout ll_delete;
    private boolean isVisible = false, isGoing = true;
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
        setContentView(R.layout.activity_my_collection);

        setTitleBar("我的收藏", 0);
        tv_edit.setVisibility(View.VISIBLE);
        tv_edit.setOnClickListener(this);
        lv = (ListView) findViewById(R.id.lv);
        ll_delete = (LinearLayout) findViewById(R.id.ll_delete);
        ll_delete.setOnClickListener(this);
        lv.setOnItemClickListener(this);

        ll_nightframe = (LinearLayout)findViewById(R.id.my_nightframe_ll);
        if (userInfo.isNightMode()) {
            ll_nightframe.setVisibility(View.VISIBLE);
        } else {
            ll_nightframe.setVisibility(View.GONE);
        }

        getCollectList();


    }


//    private void initData() {
//        list = new ArrayList<>();
//        for (int x = 0; x < 8; x++) {
//            GetCollectListData set = new GetCollectListData();
//            set.setId(x + "");
//            set.setTerm_name("校园" + x);
//            set.setTitle("标题" + x);
//            set.setCreatetime("2015-02-02");
//            list.add(set);
//        }
//    }

    private void getCollectList() {
//        initData();
        SetUser set = new SetUser();
        set.setUser(getUserinfo(this).getUser());
        HttpSender sender = new HttpSender(uurl.CollectList, "获取收藏", set, new OnHttpResListener() {
            @Override
            public void doSuccess(String data) {

                GetCollectList temp = gsonUtil.getInstance().json2Bean(data, GetCollectList.class);
                if (temp.getStatus().equals("200")) {
                    list = temp.getData();
                    LogUtils.i("我的收藏list: " + list.toString());
                    adapter = new MyCollectionAdapter(MyCollection.this, list);
                    lv.setAdapter(adapter);
                }


            }
        });
        sender.setContext(this);
        sender.send(uurl.MODE);
    }

    private void deleteCollection(final String id) {
        DeleteCollection deleteCollection = new DeleteCollection();
        deleteCollection.setUser(getUserinfo(this).getUser());
        deleteCollection.setCollectid(id);
        HttpSender sender = new HttpSender(uurl.DeleteCollection, "删除收藏", deleteCollection, new OnHttpResListener() {
            @Override
            public void doSuccess(String data) {

                ResponseBase temp = gsonUtil.getInstance().json2Bean(data, ResponseBase.class);
                if (temp.getStatus() == 200) {

                    LogUtils.i("刪除收藏: " + id);

                }


            }
        });
        sender.setContext(this);
        sender.send(uurl.MODE);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            default:
                break;
            case R.id.ll_delete:
                isGoing = true;
                if (isVisible) {
                    for (int x = 0; x < list.size(); x++) {
                        LogUtils.i("是否被选择: " + list.get(x).isDelete());
                    }
                    while (isGoing) {
                        isGoing = false;
                        for (int x = 0; x < list.size(); x++) {
                            LogUtils.i("是否被选择: " + list.get(x).isDelete());
                            if (list.get(x).isDelete()) {
                                deleteCollection(list.get(x).getId());
                                list.remove(x);
                                LogUtils.i("删除list" + x);
                                isGoing = true;
                                break;
                            }

                        }
                    }


                    ll_delete.setVisibility(View.GONE);
                    isVisible = false;
                    for (int x = 0; x < list.size(); x++) {
                        list.get(x).setVisible(isVisible);
                    }
//                    adapter.notifyDataSetChanged();
                    tv_edit.setText("编辑");
                }


                break;
            case R.id.titlebar_edit:


                for (int x = 0; x < list.size(); x++) {
                    list.get(x).setVisible(!isVisible);
                }
                adapter.notifyDataSetChanged();

                if (!isVisible) {
                    ll_delete.setVisibility(View.VISIBLE);
                    tv_edit.setText("取消");
                    isVisible = true;
                } else {
                    tv_edit.setText("编辑");
                    ll_delete.setVisibility(View.GONE);
                    isVisible = false;
                }

                break;
        }


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(MyCollection.this, NewsDetail.class);
        i.putExtra("news_url", list.get(position).getUrl());
        i.putExtra("news_collection", true);
        i.putExtra("termId", list.get(position).getTermid() + "");
        i.putExtra("newsId", list.get(position).getId());
        startActivity(i);
    }
}
