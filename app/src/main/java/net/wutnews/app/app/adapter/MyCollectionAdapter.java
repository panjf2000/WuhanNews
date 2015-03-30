package net.wutnews.app.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ab.image.AbImageLoader;
import com.ab.image.toolbox.ImageLoader;
import com.ab.util.AbImageUtil;
import com.lidroid.xutils.util.LogUtils;

import net.wutnews.app.R;
import net.wutnews.app.app.act.news.NewsDetail;
import net.wutnews.app.app.entiy.GetCollectList;
import net.wutnews.app.app.entiy.GetCollectListData;
import net.wutnews.app.app.util.uurl;

import java.util.List;

/**
 * Created by Pan on 2015/3/5.
 */
public class MyCollectionAdapter extends ArrayAdapter<GetCollectListData> {

    private Context context;
    private ViewHolder mHolder;
    private AbImageLoader imageLoader;

    public MyCollectionAdapter(Context context, List<GetCollectListData> objects) {
        super(context, R.layout.adapter_my_collection, objects);
        this.context = context;
        imageLoader = AbImageLoader.newInstance(context);
    }


    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        mHolder = null;
        if (view == null) {
            mHolder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.adapter_my_collection, null);
            mHolder.img = (ImageView) view.findViewById(R.id.img);
            mHolder.delete = (ImageView) view.findViewById(R.id.delete_chose);
            mHolder.term = (TextView) view.findViewById(R.id.term);
            mHolder.title = (TextView) view.findViewById(R.id.title);
            mHolder.time = (TextView) view.findViewById(R.id.time);
            view.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) view.getTag();
        }


        if (getItem(position).isVisible()) {
            mHolder.delete.setVisibility(View.VISIBLE);
        } else {
            mHolder.delete.setVisibility(View.GONE);

        }

        if (getItem(position).isDelete()) {
            mHolder.delete.setImageResource(R.drawable.delete_chose);
        } else {
            mHolder.delete.setImageResource(R.drawable.delete_unchosed);
        }

        mHolder.time.setText(getItem(position).getCreatetime());
        mHolder.title.setText(getItem(position).getTitle());
        mHolder.term.setText(getItem(position).getTerm_name());

        mHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getItem(position).isDelete()) {
                    getItem(position).setDelete(false);
                } else {
                    getItem(position).setDelete(true);
                }
                notifyDataSetChanged();
                LogUtils.i(position + "是否被选择: " + getItem(position).isDelete());

            }
        });
        if (!getItem(position).getSmeta().equals("")) {
            imageLoader.display(mHolder.img, uurl.IMG_URL + getItem(position).getSmeta());
        } else {
            mHolder.img.setImageResource(R.drawable.app_logo);
        }


        return view;
    }

    private class ViewHolder {
        private ImageView img, delete;
        private TextView term, title, time;

    }

}
