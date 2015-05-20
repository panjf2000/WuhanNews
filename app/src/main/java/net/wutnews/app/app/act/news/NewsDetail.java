package net.wutnews.app.app.act.news;

import android.os.Bundle;
import android.os.Handler;
import android.telephony.CellIdentityCdma;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.wutnews.app.R;
import net.wutnews.app.app.act.app.AppBaseAct;
import net.wutnews.app.app.entiy.DeleteCollection;
import net.wutnews.app.app.entiy.GetCollectNews;
import net.wutnews.app.app.entiy.ResponseBase;
import net.wutnews.app.app.entiy.SetCollectNews;
import net.wutnews.app.app.entiy.SetNewsComment;
import net.wutnews.app.app.util.uurl;
import net.wutnews.app.frame.IdoHttpUtil.HttpSender;
import net.wutnews.app.frame.IdoHttpUtil.OnHttpResListener;
import net.wutnews.app.frame.util.StringUtil;
import net.wutnews.app.frame.util.gsonUtil;


public class NewsDetail extends AppBaseAct implements View.OnClickListener {

    private WebView news_detail_webview;

    private LinearLayout ll_share, ll_collection, ll_comment, ll_menu, share_menu, menu_menu;

    private ImageView iv_share, iv_collection, iv_comment, iv_menu, hide, hide2;
    private TextView tv_share, tv_collection, tv_comment, tv_menu;

    private LinearLayout ll_menulayout,ll_edtlayout,ll_edt_send,ll_edt_edt,ll_edt_menu;
    private EditText edt_comment;
    private Handler mHandler =new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAbContentView(R.layout.activity_news_detail);

        if (this.getIntent().getExtras() != null) {
            setTitleBar("新闻详情", 0);
            initView();

            news_detail_webview.loadUrl(this.getIntent().getExtras().getString("news_url"));
//            news_detail_webview.addJavascriptInterface(new Object(){
//                public void clickOnAndroid(){
//                    new Handler().post(new Runnable() {
//                        @Override
//                        public void run() {
//                        news_detail_webview.loadUrl("javascript:LGFB.showComment()");
//                        }
//                    });
//                }
//
//            },"LGFB");
            news_detail_webview.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }
            });
            if (this.getIntent().getBooleanExtra("news_collection", false)) {
                iv_collection.setImageResource(R.drawable.news_collection_color);
                tv_collection.setTextColor(this.getResources().getColor(R.color.app_color));
            } else {
                iv_collection.setImageResource(R.drawable.news_collection_white);
                tv_collection.setTextColor(this.getResources().getColor(R.color.white));
            }
        } else {
            toast("网络异常,请稍后重试");
            finish();
        }

    }

    @Override
    protected void initView() {
        news_detail_webview = (WebView) findViewById(R.id.news_detail_webview);
        news_detail_webview.getSettings().setJavaScriptEnabled(true);
        iv_share = (ImageView) findViewById(R.id.iv_share);
        iv_collection = (ImageView) findViewById(R.id.iv_collection);
        iv_comment = (ImageView) findViewById(R.id.iv_comment);
        iv_menu = (ImageView) findViewById(R.id.iv_menu);

        tv_share = (TextView) findViewById(R.id.tv_share);
        tv_collection = (TextView) findViewById(R.id.tv_collection);
        tv_comment = (TextView) findViewById(R.id.tv_comment);
        tv_menu = (TextView) findViewById(R.id.tv_menu);

        ll_share = (LinearLayout) findViewById(R.id.ll_share);
        ll_share.setOnClickListener(this);
        ll_collection = (LinearLayout) findViewById(R.id.ll_collection);
        ll_collection.setOnClickListener(this);
        ll_comment = (LinearLayout) findViewById(R.id.ll_comment);
        ll_comment.setOnClickListener(this);
        ll_menu = (LinearLayout) findViewById(R.id.ll_menu);
        ll_menu.setOnClickListener(this);
        share_menu = (LinearLayout) findViewById(R.id.share_menu);
        share_menu.setOnClickListener(this);
        menu_menu = (LinearLayout) findViewById(R.id.menu_menu);
        menu_menu.setOnClickListener(this);

        hide = (ImageView) findViewById(R.id.hide);
        hide.setOnClickListener(this);
        hide2 = (ImageView) findViewById(R.id.hide2);
        hide2.setOnClickListener(this);

        ll_menulayout = (LinearLayout) findViewById(R.id.ll_menulayout);
        ll_edtlayout = (LinearLayout) findViewById(R.id.ll_edtlayout);
        ll_edt_send = (LinearLayout) findViewById(R.id.ll_edt_send);
        ll_edt_send.setOnClickListener(this);
        ll_edt_edt = (LinearLayout) findViewById(R.id.ll_edt_edt);
        ll_edt_edt.setOnClickListener(this);
        ll_edt_menu = (LinearLayout) findViewById(R.id.ll_edt_menu);
        ll_edt_menu.setOnClickListener(this);
        edt_comment = (EditText) findViewById(R.id.edt_comment);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            default:
                break;

            case R.id.ll_share:
                share_menu.setVisibility(View.VISIBLE);
                iv_share.setImageResource(R.drawable.news_share_color);
                tv_share.setTextColor(this.getResources().getColor(R.color.app_color));

                break;
            case R.id.ll_collection:
                if (this.getIntent().getBooleanExtra("news_collection", false)) {
                    toast("该文章已收藏！");
                    //deleteCollect(getUserinfo(this).getUser(),);
                }else{
                    collectNews();
                }

                break;
            case R.id.ll_comment:
                news_detail_webview.loadUrl("javascript:LGFB.showComment()");

                break;
            case R.id.ll_menu:
                menu_menu.setVisibility(View.VISIBLE);
                iv_menu.setImageResource(R.drawable.news_menu_color);
                tv_menu.setTextColor(this.getResources().getColor(R.color.app_color));
                break;
            case R.id.hide:
                share_menu.setVisibility(View.GONE);
                iv_share.setImageResource(R.drawable.news_share_white);
                tv_share.setTextColor(this.getResources().getColor(R.color.white));
                break;
            case R.id.hide2:
                menu_menu.setVisibility(View.GONE);
                iv_menu.setImageResource(R.drawable.news_menu_white);
                tv_menu.setTextColor(this.getResources().getColor(R.color.white));
                break;

            case R.id.ll_edt_send://发送评论
                sendComment(StringUtil.getEditText(edt_comment));
                news_detail_webview.loadUrl("javascript:LGFB.showComment()");
                break;
            case R.id.ll_edt_edt:
                ll_menulayout.setVisibility(View.VISIBLE);
                ll_edtlayout.setVisibility(View.GONE);


                break;
            case R.id.ll_edt_menu:
                ll_menulayout.setVisibility(View.GONE);
                ll_edtlayout.setVisibility(View.VISIBLE);
                if(share_menu.getVisibility()==View.VISIBLE){
                    share_menu.setVisibility(View.GONE);
                }
                if(menu_menu.getVisibility()==View.VISIBLE)
                {
                    menu_menu.setVisibility(View.GONE);
                }
                break;


        }

    }

    //收藏新闻接口
    private void collectNews() {

        SetCollectNews set = new SetCollectNews();
        set.setPostid(this.getIntent().getExtras().getString("newsId"));
        set.setTermid(this.getIntent().getExtras().getString("termId"));

        set.setUser(getUserinfo(this).getUser());

        HttpSender sender = new HttpSender(uurl.CollectNews, "收藏新闻", set, new OnHttpResListener() {
            @Override
            public void doSuccess(String data) {
                GetCollectNews temp = gsonUtil.getInstance().json2Bean(data, GetCollectNews.class);
                if (temp.getStatus() == 200) {
                    if (temp.getMsg().equals("COLLECT_SUCCESS")) {
                        toast("已添加到我的收藏!");
                        iv_collection.setImageResource(R.drawable.news_collection_color);
                        tv_collection.setTextColor(NewsDetail.this.getResources().getColor(R.color.app_color));
                    }  else if (temp.getMsg().equals("COLLECT_EXIST")) {
                        toast("该文章已收藏过!");
                        //deleteCollect(user,temp.getCollectid());
                    } else if (temp.getMsg().equals("GUEST_NO_COLLECT")) {
                        toast("游客禁止收藏!");
                    } else {
                        toast(temp.getMsg());
                    }
                }
            }
        });
        sender.setContext(this);
        sender.send(uurl.MODE);
    }

    //取消收藏
    private void deleteCollect(String user,String cid){
        DeleteCollection dSet=new DeleteCollection();
        dSet.setUser(user);
        dSet.setCollectid(cid);
        HttpSender sender = new HttpSender(uurl.DeleteCollection, "取消收藏", dSet, new OnHttpResListener() {
            @Override
            public void doSuccess(String data) {
               ResponseBase temp = gsonUtil.getInstance().json2Bean(data, ResponseBase.class);
                if (temp.getStatus() == 200) {
                    if (temp.getMsg().equals("COLLECT_DELETE_SUCCESS")) {

                        iv_collection.setImageResource(R.drawable.news_collection_white);
                        tv_collection.setTextColor(NewsDetail.this.getResources().getColor(R.color.white));
                    }
                }
            }
        });
        sender.setContext(this);
        sender.send(uurl.MODE);

    }

    private void sendComment(String content)
    {
        SetNewsComment setNewsComment=new SetNewsComment();
        setNewsComment.setUser(getUserinfo(this).getUser());
        setNewsComment.setTermid(this.getIntent().getExtras().getString("termId"));
        setNewsComment.setPostid(this.getIntent().getExtras().getString("newsId"));
        setNewsComment.setContent(content);
        HttpSender sender=new HttpSender(uurl.SendComment,"新建评论",setNewsComment,new OnHttpResListener() {
            @Override
            public void doSuccess(String data) {
                ResponseBase responseBase=gsonUtil.getInstance().json2Bean(data,ResponseBase.class);
                if (responseBase.getStatus() == 200) {
                    if (responseBase.getMsg().equals("INSERT_SUCCESS")) {
                        toast("评论成功!");
                        edt_comment.setText("");
                        ll_menulayout.setVisibility(View.VISIBLE);
                        ll_edtlayout.setVisibility(View.GONE);
                    }
                }
            }
        });
        sender.setContext(this);
        sender.send(uurl.MODE);
    }
}
