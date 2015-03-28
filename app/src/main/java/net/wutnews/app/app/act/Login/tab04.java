package net.wutnews.app.app.act.Login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.wutnews.app.R;
import net.wutnews.app.frame.base.BaseFragment;

/**
 * Created by Pan on 2015/1/20 0020.
 */
public class tab04 extends BaseFragment implements View.OnClickListener {

    private View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.index,null);

        return mView;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void onClick(View v) {

    }
}
