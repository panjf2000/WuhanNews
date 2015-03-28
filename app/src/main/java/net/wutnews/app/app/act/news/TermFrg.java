package net.wutnews.app.app.act.news;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ab.view.sliding.AbSlidingTabView;
import com.lidroid.xutils.util.LogUtils;

import net.wutnews.app.R;
import net.wutnews.app.app.entiy.GetTermList;
import net.wutnews.app.app.entiy.GetTermListData;
import net.wutnews.app.app.entiy.SetUser;
import net.wutnews.app.app.util.uurl;
import net.wutnews.app.frame.IdoHttpUtil.HttpSender;
import net.wutnews.app.frame.IdoHttpUtil.OnHttpResListener;
import net.wutnews.app.frame.base.BaseFragment;
import net.wutnews.app.frame.util.gsonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pan on 2015/1/20 0020.
 */
public class TermFrg extends BaseFragment {
    private AbSlidingTabView mAbSlidingTabView;
    private List<GetTermListData> list;
    private List<Fragment> mFragments;
    private List<String> tabTexts;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.index, null);
        mAbSlidingTabView = (AbSlidingTabView) mView.findViewById(R.id.mAbSlidingTabView);

        //如果里面的页面列表不能下载原因：
        //Fragment里面用的AbTaskQueue,由于有多个tab，顺序下载有延迟，还没下载好就被缓存了。改成用AbTaskPool，就ok了。
        //或者setOffscreenPageLimit(0)

        //缓存数量
        mAbSlidingTabView.getViewPager().setOffscreenPageLimit(5);

        //禁止滑动
//        mAbSlidingTabView.getViewPager().setOnTouchListener(new View.OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return true;
//            }
//
//        });

        getTermList();


        return mView;
    }

    private void getTermList() {

        SetUser set = new SetUser();
        set.setUser(getUserinfo(getActivity()).getUser());

        HttpSender sender = new HttpSender(uurl.GetTermList,"获取栏目列表", set, new OnHttpResListener() {
            @Override
            public void doSuccess(String data) {
                mFragments = new ArrayList<Fragment>();
                tabTexts = new ArrayList<String>();
                GetTermList datalIST = gsonUtil.getInstance().json2Bean(data, GetTermList.class);
                list = datalIST.getData();
                LogUtils.i("获取内容:" + list.toString() + "内容长度: " + list.size());
                for (int x = 0; x < list.size(); x++) {
                    mFragments.add(new NewsFrgText(list.get(x).getTerm_id()));
                    tabTexts.add(list.get(x).getName());
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
        sender.setContext(getActivity());
        sender.send(uurl.MODE);

    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    protected void initView() {
    }
}
