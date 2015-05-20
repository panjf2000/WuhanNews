package net.wutnews.app.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ab.image.AbImageLoader;

import net.wutnews.app.R;
import net.wutnews.app.app.entiy.GetCollectListData;
import net.wutnews.app.app.entiy.GetCommentListData;
import net.wutnews.app.frame.widget.CircleImageView;

import java.util.List;

/**
 * Created by Andy on 2015/3/30.
 */
public class MyCommentAdapter extends ArrayAdapter<GetCommentListData> {
    private Context context;
    private ViewHolder mHolder;
    private AbImageLoader imageLoader;
    private String nickname;

    public MyCommentAdapter(Context context, List<GetCommentListData> objects,String nickname) {
        super(context, R.layout.adapter_my_collection, objects);
        this.context = context;
        imageLoader = AbImageLoader.newInstance(context);
        this.nickname=nickname;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        mHolder = null;
        if (view == null) {
            mHolder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.adapter_my_comment, null);
            mHolder.img = (CircleImageView) view.findViewById(R.id.userHead);
            mHolder.title = (TextView) view.findViewById(R.id.newsTitle);
            mHolder.content = (TextView) view.findViewById(R.id.newsContent);
            mHolder.time = (TextView) view.findViewById(R.id.newsCreateTime);
            mHolder.username=(TextView)view.findViewById(R.id.userName);
            view.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) view.getTag();
        }
        mHolder.username.setText(nickname);
        mHolder.time.setText(getItem(position).getCreatetime());
        mHolder.title.setText("[原文]" + getItem(position).getTitle());
        mHolder.content.setText(getItem(position).getContent());
        mHolder.img.setImageResource(R.drawable.app_logo);
        return view;
    }

    private class ViewHolder {
        private CircleImageView img;
        private TextView title, content, time, username;

    }
}
