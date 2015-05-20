package net.wutnews.app.app.act.menu;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lidroid.xutils.util.LogUtils;

import net.wutnews.app.R;
import net.wutnews.app.app.act.app.AppBaseAct;
import net.wutnews.app.app.act.news.NewsDetail;
import net.wutnews.app.app.adapter.MyCommentAdapter;
import net.wutnews.app.app.entiy.GetCommentList;
import net.wutnews.app.app.entiy.GetCommentListData;
import net.wutnews.app.app.entiy.SetUser;
import net.wutnews.app.app.util.uurl;
import net.wutnews.app.frame.IdoHttpUtil.HttpSender;
import net.wutnews.app.frame.IdoHttpUtil.OnHttpResListener;
import net.wutnews.app.frame.util.gsonUtil;

import java.util.List;

public class MyComment extends AppBaseAct implements View.OnClickListener, AdapterView.OnItemClickListener {
    private ListView lv;
    private List<GetCommentListData> list;
    private MyCommentAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_comment);
        setTitleBar("我的评论", 0);
        lv = (ListView) findViewById(R.id.cm_lv);
        lv.setOnItemClickListener(this);

        getCommentList();
    }

    private void getCommentList() {

        SetUser set = new SetUser();
        set.setUser(getUserinfo(this).getUser());
        final String nickname=this.getIntent().getExtras().getString("nickname");
        HttpSender sender = new HttpSender(uurl.CommentList, "获取评论", set, new OnHttpResListener() {
            @Override
            public void doSuccess(String data) {

                GetCommentList temp = gsonUtil.getInstance().json2Bean(data, GetCommentList.class);
                if (temp.getStatus().equals("200")) {
                    list = temp.getData();
                    LogUtils.i("我的评论list: " + list.toString());
                    adapter = new MyCommentAdapter(MyComment.this, list, nickname);
                    lv.setAdapter(adapter);
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
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(MyComment.this, NewsDetail.class);
        i.putExtra("news_url", list.get(position).getUrl());
        //i.putExtra("news_collection", list.get(position).getPost_collect());
        i.putExtra("termId", 1 + "");
        i.putExtra("newsId", list.get(position).getId());
        startActivity(i);
    }
}
