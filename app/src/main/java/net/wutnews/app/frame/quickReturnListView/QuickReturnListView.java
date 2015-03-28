package net.wutnews.app.frame.quickReturnListView;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.ListView;

import com.lidroid.xutils.util.LogUtils;

public class QuickReturnListView extends ListView {
    
    private static final String TAG = "QuickReturnListView";

    public View quickReturnView;
    private QuickReturnInterceptor quickReturnIntercept;
    private int mLastOffset;
    private int mCachedVerticalScrollRange;
    private int mQuickReturnHeight;
    private int mScrollY;
    private boolean isAnimation;
    private boolean isOnScreen = true;
    private TranslateAnimation anim;
    private OnScrollListener listener;

    public QuickReturnListView(Context context) {
        super(context);
    }

    public QuickReturnListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public QuickReturnListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setQuickReturnView(final View quickReturnView) {
        this.quickReturnView = quickReturnView;
        this.quickReturnIntercept = (QuickReturnInterceptor) quickReturnView;
        // 添加视图树变动的监听器来监听视图的变化
        // 如果Layout重新布局的话，该监听器的方法将会被调用
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mCachedVerticalScrollRange = -1;
                
                // 得到QuickReturnView的高度
                mQuickReturnHeight = quickReturnView.getHeight();
                mCachedVerticalScrollRange = computeVerticalScrollRange();
                Log.i(TAG  , " [h = "+mQuickReturnHeight+"] AND [r = "+mCachedVerticalScrollRange+"]");
            }
        });
    }

    // 防止外部设置OnScrollListener监听器而是本控件失效
    @Override
    public void setOnScrollListener(final OnScrollListener l) {
        listener = l;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        // 注意前面的super关键字
        super.setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                if (listener != null) {
                    listener.onScrollStateChanged(absListView, i);
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                if (listener != null) {
                    listener.onScroll(absListView, i, i2, i3);
                }
                // isScrollUp用于标识用户是否在向上移动：
                // null 表示当前没有移动
                // true 表示向上移动
                // false 表示向下移动
                Boolean isScrollUp = null;
                if (mCachedVerticalScrollRange != -1) {
                    // mScrllY保存的是上一次的位置
                    // computedScrollY保存的是当前位置
                    int computedScrollY = getComputedScrollY();
                    Log.i(TAG, "y = "+mScrollY + " , my = " + computedScrollY);
                    // 如果mScrollY == computedScrollY，表示没有移动，
                    // 上次的mScrollY位置大于本次computedScrollY,表示向上移动
                    // 否则表示向下移动
                    isScrollUp = mScrollY == computedScrollY ? null : mScrollY > computedScrollY;
                    // 保存本次移动到的位置，以便下次使用
                    mScrollY = computedScrollY;
                }

                if (isScrollUp == null) {
                    return;
                }
                // 用户在向上移动 && QuickReturnView不在屏幕上 && QuickReturnView没有在执行动画
                // 那么执行动画让QuickReturnView可见
                if (isScrollUp && !isOnScreen && !isAnimation) {
//                    if(i==0){
//                        LogUtils.i("向上移动");
//                        quickReturnView.setVisibility(View.VISIBLE);
//                        isOnScreen = true;
//                        quickReturnIntercept.setInterceptTouchEvent(true);
//                        isAnimation = false;
//                    }

//                    isAnimation = true;
//                    anim = new TranslateAnimation(0, 0, mQuickReturnHeight, 0);
//                    anim.setFillAfter(true);
//                    anim.setDuration(200);
//                    anim.setAnimationListener(new Animation.AnimationListener() {
//                        @Override
//                        public void onAnimationStart(Animation animation) {
//                        }
//
//                        @Override
//                        public void onAnimationEnd(Animation animation) {
//                            isOnScreen = true;
//                            isAnimation = false;
//                            quickReturnIntercept.setInterceptTouchEvent(false);
//                        }
//
//                        @Override
//                        public void onAnimationRepeat(Animation animation) {
//                        }
//                    });
//                    quickReturnView.startAnimation(anim);
                } 
                // 用户在向下移动 && QuickReturnView在屏幕上 && QuickReturnView没有在执行动画
                // 那么执行动画让QuickReturnView不可见
                else if (!isScrollUp && isOnScreen && !isAnimation) {
                    LogUtils.i("向下移动");
                    quickReturnView.setVisibility(View.GONE);
                    isOnScreen = false;
                    quickReturnIntercept.setInterceptTouchEvent(false);
                    isAnimation = false;
//                    isAnimation = true;
//                    anim = new TranslateAnimation(0, 0, 0, mQuickReturnHeight);
//                    anim.setFillAfter(true);
//                    anim.setDuration(200);
//                    anim.setAnimationListener(new Animation.AnimationListener() {
//                        @Override
//                        public void onAnimationStart(Animation animation) {
//                        }
//
//                        @Override
//                        public void onAnimationEnd(Animation animation) {
//                            isOnScreen = false;
//                            isAnimation = false;
//                            quickReturnIntercept.setInterceptTouchEvent(true);
//                        }
//
//                        @Override
//                        public void onAnimationRepeat(Animation animation) {
//                        }
//                    });
                    // 执行动画
//                    quickReturnView.startAnimation(anim);
                }
            }
        });
    }
    

    public int getComputedScrollY() {
        return computeVerticalScrollOffset() - mLastOffset;
    }

}
