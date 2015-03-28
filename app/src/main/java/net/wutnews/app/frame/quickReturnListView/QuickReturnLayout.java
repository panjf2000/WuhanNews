package net.wutnews.app.frame.quickReturnListView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

public class QuickReturnLayout extends FrameLayout implements QuickReturnInterceptor{
    
    public QuickReturnLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private boolean mIntercept = false;

    // 返回true表示不接收用户的触摸事件而交给其他View处理
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mIntercept;
    }
    
    @Override
    public void setInterceptTouchEvent(boolean intercept){
        this.mIntercept = intercept;
    }
}
