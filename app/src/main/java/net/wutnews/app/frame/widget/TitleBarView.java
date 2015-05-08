package net.wutnews.app.frame.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.wutnews.app.R;


/**
 * Created by Pan on 2015/1/27.
 * 标题栏
 */
public class TitleBarView extends RelativeLayout
{

    private Context   mContext;
    private TextView  mCenterText; //标题中心文字
    private TextView  mLeftText; //标题栏左侧文字
    private TextView  mRightText; //标题栏右侧文字
    private ImageView mLeftImage; //标题栏左侧图片
    private ImageView mRightImage; //标题栏右侧图片

    public TitleBarView(Context context)
    {
        super(context);
        mContext = context;
        initWidgetValue();
    }

    public TitleBarView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public TitleBarView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        mContext = context;
        initWidgetValue();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleBarView, defStyle, 0);
        String centerText = typedArray.getString(R.styleable.TitleBarView_center_text);
        String leftText = typedArray.getString(R.styleable.TitleBarView_left_text);
        String rightText = typedArray.getString(R.styleable.TitleBarView_right_text);
        int leftImageResId = typedArray.getResourceId(R.styleable.TitleBarView_left_image, 0);
        int rightImageResId = typedArray.getResourceId(R.styleable.TitleBarView_right_image, 0);
        setContent(centerText, leftText, rightText, leftImageResId, rightImageResId);
        typedArray.recycle();
    }

    /**
     * 初始化控件变量
     */
    private void initWidgetValue()
    {
        LayoutInflater.from(mContext).inflate(R.layout.titlebar_1, this);
        mCenterText = (TextView) findViewById(R.id.customTitleCenterText);
        mLeftText = (TextView) findViewById(R.id.customTitleLeftText);
        mRightText = (TextView) findViewById(R.id.customTitleRightText);
        mLeftImage = (ImageView) findViewById(R.id.customTitleLeftImage);
        mRightImage = (ImageView) findViewById(R.id.customTitleRightImage);
    }

    /**
     * 设置标题栏文字和图片
     */
    public void setContent(String centerText, String leftText, String rightText, int leftImageResId, int rightImageResId)
    {
        if(centerText == null || centerText.equals(""))
        {
            mCenterText.setVisibility(View.GONE);
        }
        else
        {
            mCenterText.setVisibility(View.VISIBLE);
            mCenterText.setText(centerText);
        }

        if(leftText == null || leftText.equals(""))
        {
            mLeftText.setVisibility(View.GONE);
        }
        else
        {
            mLeftText.setVisibility(View.VISIBLE);
            mLeftText.setText(leftText);
        }

        if(rightText == null || rightText.equals(""))
        {
            mRightText.setVisibility(View.GONE);
        }
        else
        {
            mRightText.setVisibility(View.VISIBLE);
            mRightText.setText(rightText);
        }

        if(leftImageResId == 0)
        {
            mLeftImage.setVisibility(View.GONE);
        }
        else
        {
            mLeftImage.setVisibility(View.VISIBLE);
            mLeftImage.setImageResource(leftImageResId);
        }

        if(rightImageResId == 0)
        {
            mRightImage.setVisibility(View.GONE);
        }
        else
        {
            mRightImage.setVisibility(View.VISIBLE);
            mRightImage.setImageResource(rightImageResId);
        }
    }

    //设置中间tv内容
    public void setCenterText(String centerText){
        if(centerText == null || centerText.equals(""))
        {
            mCenterText.setVisibility(View.GONE);
        }
        else
        {
            mCenterText.setVisibility(View.VISIBLE);
            mCenterText.setText(centerText);

        }
    }

    //设置中间tv点击事件
    public void setmCenterTextOnclickListener(OnClickListener listener){
        mCenterText.setOnClickListener(listener);
    }

    public void setLeftTextOnClickListener(OnClickListener listener)
    {
        mLeftText.setOnClickListener(listener);
    }

    public void setRightTextOnClickListener(OnClickListener listener)
    {
        mRightText.setOnClickListener(listener);
    }

    public void setLeftImageOnClickListener(OnClickListener listener)
    {
        mLeftImage.setOnClickListener(listener);
    }

    public void setRightImageOnClickListener(OnClickListener listener)
    {
        mRightImage.setOnClickListener(listener);
    }

}
