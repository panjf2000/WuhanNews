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
import net.wutnews.app.frame.util.ImageLoaderUtil;

import java.util.List;

/**
 * Created by Pan on 2015/1/22 0022.
 */
public class NewsListAdapter extends ArrayAdapter<GetNewsListData> {

    private Context context;
    private ViewHolder mHolder;
    private List<GetNewsListData> listUp;
    private String termId;
    private boolean isFirst = true;
    private ImageLoaderUtil imageUtil;
    private String term;
    public NewsListAdapter(Context context, List<GetNewsListData> objects, List<GetNewsListData> listUp, String termId,String term) {
        super(context, R.layout.adapter_news_list_item, objects);
        this.context = context;
        this.listUp = listUp;
        this.termId = termId;
        this.term = term;
        imageUtil = ImageLoaderUtil.getInstance();

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        mHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_news_list_item, null);
            mHolder = new ViewHolder();
            mHolder.img = (ImageView) convertView.findViewById(R.id.news_list_img);
            mHolder.icon = (TextView) convertView.findViewById(R.id.news_list_icon);
            mHolder.title = (TextView) convertView.findViewById(R.id.news_list_title);
            mHolder.time = (TextView) convertView.findViewById(R.id.news_list_time);
            mHolder.layout = (LinearLayout) convertView.findViewById(R.id.layout);
            mHolder.playView = (AbSlidingPlayView) convertView.findViewById(R.id.mAbSlidingPlayView);
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }
        if (position == 0) {
            mHolder.playView.setVisibility(View.VISIBLE);
            mHolder.layout.setVisibility(View.GONE);

            for (int i = 0; i < listUp.size(); i++) {
                final View mPlayView = convertView.inflate(context, R.layout.play_view_item, null);
                ImageView mPlayImage = (ImageView) mPlayView.findViewById(R.id.mPlayImage);
                TextView mPlayText = (TextView) mPlayView.findViewById(R.id.mPlayText);
                mPlayText.setText(listUp.get(i).getPost_title());
                if (!listUp.get(i).getSmeta().equals("")) {
//                    imageLoader.setMaxHeight(400);
//                    imageLoader.setMaxWidth(400);
//                    imageLoader.display(mPlayImage, uurl.IMG_URL + listUp.get(i).getSmeta());
                    imageUtil.setImage(uurl.IMG_URL + listUp.get(i).getSmeta(), mPlayImage);
                }
                mPlayImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
                mHolder.playView.addView(mPlayView);
                if (isFirst) {
                    isFirst = false;
                    mHolder.playView.startPlay();
                }
                mHolder.playView.setOnItemClickListener(new AbSlidingPlayView.AbOnItemClickListener() {
                    @Override
                    public void onClick(int position) {
                        Intent i = new Intent(context, NewsDetail.class);
                        i.putExtra("news_url", getItem(position).getPost_link());
                        i.putExtra("news_collection", getItem(position).getPost_collect());
                        i.putExtra("newsId", getItem(position).getId());
                        i.putExtra("termId", termId);
                        context.startActivity(i);
                    }
                });
            }

        }else if(position<4)
        {
            mHolder.layout.setVisibility(View.GONE);
            mHolder.playView.setVisibility(View.GONE);
        }
        else{
            mHolder.layout.setVisibility(View.VISIBLE);
            mHolder.playView.setVisibility(View.GONE);
            if (!getItem(position).getSmeta().equals("")) {
//                imageLoader.setMaxHeight(56);
//                imageLoader.setMaxWidth(56);
//                imageLoader.display(mHolder.img, uurl.IMG_URL + getItem(position).getSmeta());
                imageUtil.setImage(uurl.IMG_URL + getItem(position).getSmeta(),mHolder.img);
            } else {
                mHolder.img.setImageResource(R.drawable.app_logo);
            }
            mHolder.title.setText(getItem(position).getPost_title());
            mHolder.time.setText(getItem(position).getPost_modified());
            mHolder.icon.setText(this.term);  //设置栏目名称

        }


        return convertView;
    }

    private class ViewHolder {
        private ImageView img;
        private TextView title, time, icon;
        private AbSlidingPlayView playView;
        private LinearLayout layout;

    }
}
