package net.wutnews.app.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.ab.image.AbImageLoader;
import com.ab.util.AbImageUtil;
import com.lidroid.xutils.DbUtils;

import net.wutnews.app.R;
import net.wutnews.app.app.entiy.DBSubscribeStatus;
import net.wutnews.app.app.entiy.GetTermListData;
import net.wutnews.app.app.util.dbUtil;
import net.wutnews.app.app.util.uurl;
import net.wutnews.app.frame.util.ImageLoaderUtil;

import java.util.List;

/**
 * Created by Pan on 2015/1/27 0027.
 */
public class SubscribeAdapter extends BaseAdapter {

    private Context context;
    private viewHolder mHolder;
    private List<GetTermListData> list;

    public SubscribeAdapter(Context context,List<GetTermListData> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        mHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_subscribe, null);
            mHolder = new viewHolder();
            mHolder.iv = (ImageView) convertView.findViewById(R.id.sub_iv);
            mHolder.mark = (ImageView) convertView.findViewById(R.id.mark);
            convertView.setTag(mHolder);
        } else {
            mHolder = (viewHolder) convertView.getTag();
        }
        ImageLoaderUtil.getInstance().setImage(uurl.IMG_URL + list.get(position).getSmeta(), mHolder.iv);
        if (list.get(position).isSub()) {
            mHolder.mark.setImageResource(R.drawable.subscide_03);
        } else {
            mHolder.mark.setImageResource(R.drawable.unsubscibe_03);
        }


        return convertView;
    }

    private class viewHolder {
        private ImageView iv, mark;
    }
}
