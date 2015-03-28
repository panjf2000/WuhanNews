package net.wutnews.app.app.act.app;

import android.app.Application;

import net.wutnews.app.frame.util.ImageLoaderUtil;

/**
 * Created by Pan on 2015/1/22 0022.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderUtil.getInstance().initImageLoader(getApplicationContext());
    }
}
