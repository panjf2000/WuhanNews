package net.wutnews.app.frame.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import net.wutnews.app.R;


/**
 * Created by Pan on 2015/1/26.
 * 自定义对话框
 */
public class CustomDialog extends Dialog
{

    private static final int THEME = R.style.CustomDialog; //自定义对话框主题

    public CustomDialog(Context context, int layoutResID)
    {
        this(context, layoutResID, 0, 0);
    }

    public CustomDialog(Context context, int layoutResID, int width, int height)
    {
        super(context, THEME);
        setContentView(layoutResID);
        if(width == 0 || height == 0)
        {
            return;
        }
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        //set width,height by density and gravity
        float density = getDensity(context);
        params.width = (int) (width * density);
        params.height = (int) (height * density);
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);
    }

    private float getDensity(Context context)
    {
        Resources resources = context.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.density;
    }

    @Override
    public void onBackPressed()
    {
        this.dismiss();
    }
}
