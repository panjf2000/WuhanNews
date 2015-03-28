package net.wutnews.app.app.act.app;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.ab.view.slidingmenu.SlidingMenu;

import net.wutnews.app.R;
import net.wutnews.app.app.act.menu.SlidingMenuList;
import net.wutnews.app.app.act.news.TermFrg;
import net.wutnews.app.frame.base.BaseActivity;

/**
 * Created by Pan on 2015/1/20 0020.
 */
public class MainAct extends BaseActivity {

    private SlidingMenu menu;
    private ImageView indexMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAbContentView(R.layout.main_act);

        indexMenu = (ImageView) findViewById(R.id.index_back);

        //主视图的Fragment添加
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, new TermFrg())
                .commit();
        //SlidingMenu的配置
        menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);
//
        //slidingmenu的事件模式，如果里面有可以滑动的请用TOUCHMODE_MARGIN
        //可解决事件冲突问题
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);

        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setShadowDrawable(R.drawable.shadow);
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        menu.setFadeDegree(0.35f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
//
        //menu视图的Fragment添加
        menu.setMenu(R.layout.sliding_menu);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.menu_frame, new SlidingMenuList())
                .commit();
//
        indexMenu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (menu.isMenuShowing()) {
                    menu.showContent();
                } else {
                    menu.showMenu();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (menu.isMenuShowing()) {
            menu.showContent();
        } else {
            super.onBackPressed();
        }


    }


    @Override
    protected void initView() {

    }

}
