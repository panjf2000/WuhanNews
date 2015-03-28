package net.wutnews.app.app.act.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.wutnews.app.R;
import net.wutnews.app.frame.base.BaseFragment;

/**
 * Created by Pan on 2015/1/21 0021.
 */
public class SlidingMenuList extends BaseFragment {

    private View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_menu,null);


        return mView;
    }

    @Override
    protected void initView() {

    }
}
