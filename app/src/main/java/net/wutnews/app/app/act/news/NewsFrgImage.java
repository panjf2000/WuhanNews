package net.wutnews.app.app.act.news;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ab.view.pullview.AbPullToRefreshView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;


import net.wutnews.app.R;
import net.wutnews.app.app.act.app.AppBaseFrg;
import net.wutnews.app.app.adapter.NewsListImageAdapter;
import net.wutnews.app.app.entiy.DaoMaster;
import net.wutnews.app.app.entiy.DaoSession;
import net.wutnews.app.app.entiy.GetNewsList;
import net.wutnews.app.app.entiy.GetNewsListData;
import net.wutnews.app.app.entiy.GetNewsListDataDao;
import net.wutnews.app.app.entiy.SetNewsList;
import net.wutnews.app.app.util.uurl;
import net.wutnews.app.frame.IdoHttpUtil.HttpSender;
import net.wutnews.app.frame.IdoHttpUtil.OnHttpResListener;
import net.wutnews.app.frame.util.gsonUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pan on 2015/1/31 0031.
 * 大图合并listview
 */
public class NewsFrgImage extends AppBaseFrg implements AdapterView.OnItemClickListener, AbPullToRefreshView.OnHeaderRefreshListener, AbPullToRefreshView.OnFooterLoadListener {
    private String termId = "3";
    public  String imagePath  =  "/data/data/wutnews/app/";

    public NewsFrgImage(String termId) {
        this.termId = termId;
    }


    private View mView;
    private AbPullToRefreshView mAbPullToRefreshView = null;
    private ListView mListView = null;
    private int page = 1;
    private List<GetNewsListData> list, cacheList, listUp, listDown;
    private NewsListImageAdapter newsListAdapter;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser) {
            getNewsList();
        } else {

        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.activity_news_list, null);
        cacheList = new ArrayList<>();
        list = new ArrayList<>();
        findView();


        return mView;

    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
    }


    private void getNewsList() {
        SetNewsList set = new SetNewsList();
        set.setPage("1");
        set.setTerm(termId + "");
        set.setUser(getUserinfo(getActivity()).getUser());
        HttpSender sender = new HttpSender(uurl.GetNewsList, "获取新闻列表", set, new OnHttpResListener() {
            @Override
            public void doSuccess(String data) {
                GetNewsList datalIST = gsonUtil.getInstance().json2Bean(data, GetNewsList.class);
                list = datalIST.getData();
                listUp = new ArrayList<>();

                if (list.size() > 4) {
                    for (int x = 0; x < 4; x++) {
                        listUp.add(list.get(x));
                    }
                } else {
                    for (int x = 0; x < list.size(); x++) {
                        listUp.add(list.get(x));
                    }
                }
                initData();
                newsListAdapter = new NewsListImageAdapter(getActivity(), list, listUp);
                mListView.setAdapter(newsListAdapter);


            }

        });
        sender.send(uurl.MODE);
    }

    private void initData() {

//


    }


    private void findView() {

        mListView = (ListView) mView.findViewById(R.id.mListView);
        //获取ListView对象
        mAbPullToRefreshView = (AbPullToRefreshView) mView.findViewById(R.id.mPullRefreshView);
        //设置监听器
        mListView.setOnItemClickListener(this);
        mAbPullToRefreshView.setOnHeaderRefreshListener(this);
        mAbPullToRefreshView.setOnFooterLoadListener(this);
        //设置进度条的样式
        mAbPullToRefreshView.getHeaderView().setHeaderProgressBarDrawable(this.getResources().getDrawable(R.drawable.progress_circular));
        mAbPullToRefreshView.getFooterView().setFooterProgressBarDrawable(this.getResources().getDrawable(R.drawable.progress_circular));

    }


    @Override
    public void setResource() {
        //设置加载的资源
        this.setLoadDrawable(R.drawable.progress_loading3);
        this.setLoadMessage("正在查询,请稍候");

        this.setRefreshDrawable(R.drawable.ic_refresh2);
        this.setRefreshMessage("请求出错，请重试");
    }

    @Override
    public void onFooterLoad(AbPullToRefreshView view) {
        page++;
        SetNewsList set = new SetNewsList();
        set.setPage(page + "");
        set.setTerm(termId + "");
        set.setUser(getUserinfo(getActivity()).getUser());
        HttpSender sender = new HttpSender(uurl.GetNewsList, "获取新闻列表", set, new OnHttpResListener() {
            @Override
            public void doSuccess(String data) {
                GetNewsList datalIST = gsonUtil.getInstance().json2Bean(data, GetNewsList.class);
                cacheList.clear();
                cacheList = datalIST.getData();
                if (cacheList.size() == 0) {

                    toast("已经没有新内容啦!");
                    mAbPullToRefreshView.onFooterLoadFinish();
                    return;
                }
                list.addAll(cacheList);
                newsListAdapter = new NewsListImageAdapter(getActivity(), list, listUp);
                mListView.setAdapter(newsListAdapter);
                mAbPullToRefreshView.onFooterLoadFinish();
                addCache(list, termId);
            }

        });
        sender.send(uurl.MODE);

    }

    @Override
    public void onHeaderRefresh(AbPullToRefreshView view) {
        page = 1;
        SetNewsList set = new SetNewsList();
        set.setPage(page + "");
        set.setTerm(termId + "");
        set.setUser(getUserinfo(getActivity()).getUser());
        HttpSender sender = new HttpSender(uurl.GetNewsList, "获取新闻列表", set, new OnHttpResListener() {
            @Override
            public void doSuccess(String data) {
                GetNewsList datalIST = gsonUtil.getInstance().json2Bean(data, GetNewsList.class);
                list.clear();
                listUp.clear();
                list = datalIST.getData();
                if (list.size() > 4) {
                    for (int x = 0; x < 4; x++) {
                        listUp.add(list.get(x));
                    }
//                    for (int x=0;x<4;x++){
//                        list.remove(0);
//                    }
                } else {
                    for (int x = 0; x < list.size(); x++) {
                        listUp.add(list.get(x));
                    }
//                    list.clear();
                }
                initData();
                newsListAdapter = new NewsListImageAdapter(getActivity(), list, listUp);
                mListView.setAdapter(newsListAdapter);
                mAbPullToRefreshView.onHeaderRefreshFinish();
                addCache(list,termId);
            }

        });
//        sender.setContext(getActivity());
        sender.send(uurl.MODE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(getActivity(), NewsDetail.class);
        i.putExtra("news_url", list.get(position).getPost_link());
        i.putExtra("news_collection", list.get(position).getPost_collect());
        i.putExtra("termId", termId + "");
        i.putExtra("newsId", list.get(position).getId());
        startActivity(i);
    }

    public void addCache(List<GetNewsListData> list,String termId){


        SQLiteOpenHelper helper = new DaoMaster.DevOpenHelper(this.getActivity(),"newslistdb",null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();

        GetNewsListDataDao getNewsListDataDao = daoSession.getGetNewsListDataDao();
        getNewsListDataDao.deleteAll();
        for(GetNewsListData data : list){

            download(data.getSmeta());

            data.setTerm_id(termId);
            data.setThumb(data.getSmeta());

            getNewsListDataDao.insert( data);


        }

    }

    private void download(String smeta) {

        HttpUtils http = new HttpUtils();
        HttpHandler handler = http.download(uurl.IMG_URL+smeta,
                imagePath+smeta,
                true, // 如果目标文件存在，接着未完成的部分继续下载。服务器不支持RANGE时将从新下载。
                false, // 如果从请求返回信息中获取到文件名，下载完成后自动重命名。
                new RequestCallBack<File>() {

                    @Override
                    public void onStart() {
                        Log.i("status","start");
                    }

                    @Override
                    public void onLoading(long total, long current, boolean isUploading) {
                        Log.i("status","load");
                    }

                    @Override
                    public void onSuccess(ResponseInfo<File> responseInfo) {
                        Log.i("status","success");
                    }


                    @Override
                    public void onFailure(HttpException error, String msg) {
                        Log.i("status","failure");
                    }
                });

    }
}
