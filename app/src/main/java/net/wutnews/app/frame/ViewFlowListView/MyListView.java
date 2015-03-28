package net.wutnews.app.frame.ViewFlowListView;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ListView;

public class MyListView extends ListView {
	private GestureDetector gestureDetector;

	public void setGestureDetector(GestureDetector gestureDetector) {
		this.gestureDetector = gestureDetector;
	}

	public MyListView(Context context) {
		super(context);
	}

	public MyListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {

		int x = (int) ev.getX();
		int y = (int) ev.getY();
		int position = pointToPosition(x, y);
		// 只有headview才进行手势操作.
		if (position == 0) {
			// 注入手势
            Log.e("jj", "onTouchEvent...");
			gestureDetector.onTouchEvent(ev);
		}
		return super.onTouchEvent(ev);
	}
}
