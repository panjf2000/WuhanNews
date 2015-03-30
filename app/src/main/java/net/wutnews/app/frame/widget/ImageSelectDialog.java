package net.wutnews.app.frame.widget;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import net.wutnews.app.R;


/**
 * Created by YUQIANG on 2014/12/10.
 * 选择拍照还是从图库选择照片的对话框
 */
public class ImageSelectDialog extends CustomDialog
{

    public ImageSelectDialog(Context context, final OnSelectListener listener)
    {
        super(context, R.layout.dialog_get_image);
        Button camera = (Button) findViewById(R.id.camera);
        camera.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                dismiss();
                listener.onSelectCamera();
            }
        });
        Button gallery = (Button) findViewById(R.id.gallery);
        gallery.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                dismiss();
                listener.onSelectGallery();
            }
        });
        Button cancel = (Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                dismiss();
            }
        });
    }

    public interface OnSelectListener
    {
        public void onSelectCamera();
        public void onSelectGallery();
    }

}
