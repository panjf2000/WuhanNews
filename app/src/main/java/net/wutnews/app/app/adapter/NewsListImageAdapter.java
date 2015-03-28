package net.wutnews.app.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ab.image.AbImageLoader;
import com.ab.view.sliding.AbSlidingPlayView;

import net.wutnews.app.R;
import net.wutnews.app.app.act.news.NewsDetail;
import net.wutnews.app.app.entiy.GetNewsListData;
import net.wutnews.app.app.util.uurl;

import java.util.List;

/**
 * Created by Pan on 2015/1/22 0022.
 */
public class NewsListImageAdapter extends ArrayAdapter<GetNewsListData> {

    private Context context;
    private ViewHolder mHolder;

    private List<GetNewsListData> listUp;

    public NewsListImageAdapter(Context context, List<GetNewsListData> objects, List<GetNewsListData> listUp) {
        super(context, R.layout.adapter_news_list_item_image, objects);
        this.context = context;

        this.listUp = listUp;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        mHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_news_list_item_image, null);
            mHolder = new ViewHolder();
            mHolder.img = (ImageView) convertView.findViewById(R.id.img);
            mHolder.title = (TextView) convertView.findViewById(R.id.title);
            mHolder.count = (TextView) convertView.findViewById(R.id.count);
            mHolder.type = (TextView) convertView.findViewById(R.id.type);
            mHolder.imageLoader = AbImageLoader.newInstance(context);
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }

        mHolder.imageLoader.display(mHolder.img, uurl.IMG_URL + getItem(position).getSmeta());

        mHolder.title.setText(getItem(position).getPost_title());


        return convertView;
    }

    private class ViewHolder {
        private ImageView img;
        private TextView title, count, type;
        private AbImageLoader imageLoader;

    }
}
