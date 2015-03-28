package net.wutnews.app.frame.IdoHttpUtil;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.util.LogUtils;

import net.wutnews.app.frame.util.StringUtil;
import net.wutnews.app.frame.util.gsonUtil;

import java.io.File;
import java.util.Map;

/**
 * http请求与回调
 *
 * @author Pan
 */
public class HttpSender {

    RequestParams params;
    private String mRequestUrl = "";
    private OnHttpResListener onHttpResListener;// 回调接口
    private String name = "http请求描述";// 请求描述
    private int status = 200;// 服务器响应码 默认200成功
    private int unToken = 502;// 服务器响应码 默认502 token过期
    private Object mRequestObj = null;
    private String firstCharForGetRequest = "?";
    private Boolean isShowDialog = true;
    private Dialog dialog;
    private Context context;
    private String encoding = "utf-8";
    private Long httpCacheTime = 0000L;


    /**
     * @param mRequestUrl
     * @param onHttpResListener
     * @param name
     */
    public HttpSender(String mRequestUrl, String name,
                      OnHttpResListener onHttpResListener) {
        super();
        this.mRequestUrl = mRequestUrl;
        this.onHttpResListener = onHttpResListener;
        this.name = name;
    }

    /**
     * @param mRequestUrl
     * @param onHttpResListener
     * @param name
     * @param mRequestObj
     */
    public HttpSender(String mRequestUrl, String name, Object mRequestObj,
                      OnHttpResListener onHttpResListener) {
        super();
        this.mRequestUrl = mRequestUrl;
        this.onHttpResListener = onHttpResListener;
        this.name = name;
        this.mRequestObj = mRequestObj;
    }

    /**
     * @param mRequestUrl
     * @param onHttpResListener
     * @param name
     * @param mRequestObj
     * @deprecated 不建议使用 请用HttpSender(String mRequestUrl, String name, Object mRequestObj,
     * OnHttpResListener onHttpResListener)
     */
    public HttpSender(String mRequestUrl, OnHttpResListener onHttpResListener,
                      String name, Object mRequestObj) {
        super();
        this.mRequestUrl = mRequestUrl;
        this.onHttpResListener = onHttpResListener;
        this.name = name;
        this.mRequestObj = mRequestObj;
    }

    public Long getHttpCacheTime() {
        return httpCacheTime;
    }

    public void setHttpCacheTime(Long httpCacheTime) {
        this.httpCacheTime = httpCacheTime;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Boolean getIsShowDialog() {
        return isShowDialog;
    }

    public void setIsShowDialog(Boolean isShowDialog) {
        this.isShowDialog = isShowDialog;
    }

    public String getFirstCharForGetRequest() {
        return firstCharForGetRequest;
    }

    public void setFirstCharForGetRequest(String firstCharForGetRequest) {
        this.firstCharForGetRequest = firstCharForGetRequest;
    }

    public String getmRequestUrl() {
        return mRequestUrl;
    }

    public void setmRequestUrl(String mRequestUrl) {
        this.mRequestUrl = mRequestUrl;
    }

    public Object getmRequestObj() {
        return mRequestObj;
    }

    public void setmRequestObj(Object mRequestObj) {
        this.mRequestObj = mRequestObj;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUnToken() {
        return unToken;
    }

    public void setUnToken(int unToken) {
        this.unToken = unToken;
    }

    public String getName() {
        return name;
    }

    // -------------------------------------------构造函数--------------------------------------------------------


    //	public HttpSender(String mRequestUrl, OnHttpResListener onHttpResListener) {
//		super();
//		this.mRequestUrl = mRequestUrl;
//		this.onHttpResListener = onHttpResListener;
//		this.mRequestObj = null;
//	}
    public void setName(String name) {
        this.name = name;
    }


    //	public HttpSender(String mRequestUrl, Object mRequestObj,
//			OnHttpResListener onHttpResListener) {
//		super();
//		this.mRequestUrl = mRequestUrl;
//		this.onHttpResListener = onHttpResListener;
//		this.mRequestObj = mRequestObj;
//	}
    public OnHttpResListener getOnHttpResListener() {
        return onHttpResListener;
    }

    public void setOnHttpResListener(OnHttpResListener onHttpResListener) {
        this.onHttpResListener = onHttpResListener;
    }

    // -------------------------------------------------公开调用方法------------------------------------------

    /**
     * 请求执行操作,最后调用
     *
     * @param mode GET:get请求,Post:post不带文件请求,File:post带文件请求(
     *             调用File前一定要先执行addFile方法)
     */
    public void send(HttpMode mode) {

        switch (mode) {
            case Get:
                requestGet();

                break;
            case Post:
                requestPost();

                break;

            default:
                break;
        }
    }

    // --------------------------------------------回调操作------------------------------------------------

    /**
     * Get请求
     */
    private void requestGet() {
        if (StringUtil.isBlank(mRequestUrl)) {
            LogUtils.e(name + "GET请求 Url为空");
            return;
        }
        StringBuilder sb = new StringBuilder(mRequestUrl);
        HttpUtils connect = new HttpUtils();
        connect.configCurrentHttpCacheExpiry(httpCacheTime);
        if (mRequestObj != null) {
            if (StringUtil.isBlank(firstCharForGetRequest)) {
                firstCharForGetRequest = "&";
            }
            sb.append(firstCharForGetRequest);

            Map<String, Object> map = gsonUtil.getInstance().Obj2Map(
                    mRequestObj);
            for (String key : map.keySet()) {
                sb.append(key + "=" + map.get(key));
                sb.append("&");

            }
            sb.deleteCharAt(sb.length() - 1);
        }
        LogUtils.i("GET请求名称: " + name + "\n" + "GET请求Url: " + sb.toString());
        connect.send(HttpMethod.GET, sb.toString(), new onReqCallBack());

    }

    // ------------------------------------------------请求操作----------------------------------------------

    /**
     * POST 文件上传,提交文件只需提交文件路径即可
     */
    private void requestPost() {
        // TODO Auto-generated method stub
        if (StringUtil.isBlank(mRequestUrl)) {
            LogUtils.e(name + "File请求 Url为空");
            return;
        }
        if (null == params) {
            params = new RequestParams();
        }
        if (mRequestObj != null) {
            LogUtils.i("File请求名称: " + name + "\n" + "File请求Url: "
                    + mRequestUrl.toString());
            Map<String, Object> map = gsonUtil.getInstance().Obj2Map(
                    mRequestObj);
            for (String key : map.keySet()) {
                LogUtils.i("File提交参数: " + key + " = " + map.get(key).toString());
                params.addBodyParameter(key, map.get(key).toString());
            }

        }
        HttpUtils connect = new HttpUtils();
        connect.send(HttpMethod.POST, mRequestUrl, params, new onReqCallBack());

    }

    /**
     * POST 带文件上传时 调用此方法
     *
     * @param keyName
     * @param file
     */
    public void addFile(String keyName, File file) {
        if (null == params) {
            params = new RequestParams();
        }
        params.addBodyParameter(keyName, file);
        LogUtils.i("File提交文件: " + keyName + " = " + file.toString());
    }

    /**
     * 显示等待对话框
     */
    private void showDialog() {
        if (!isShowDialog)
            return;
        if (context == null) {
            return;
        }
        if (dialog == null) {
            ProgressDialog mPd = new ProgressDialog(context,
                    ProgressDialog.THEME_HOLO_LIGHT);
            mPd.setMessage("玩命加载中...");
            dialog = mPd;
        }
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        if (!dialog.isShowing())
            dialog.show();
    }

    /**
     * 关闭等待对话框
     */
    private void dismissDialog() {
        if (dialog != null && dialog.isShowing())
            dialog.dismiss();
    }

    public enum HttpMode {
        Get, Post;
    }

    /**
     * 回调监听类 onStart:开始请求回调 onFailure:请求失败回调 onSuccess:请求成功回调 onLoading:请求中回调
     *
     * @author Pan
     */
    private class onReqCallBack extends RequestCallBack<String> {

        @Override
        public void onStart() {
            showDialog();
        }

        @Override
        public void onFailure(HttpException arg0, String arg1) {
            // TODO Auto-generated method stub
            dismissDialog();
        }

        @Override
        public void onSuccess(ResponseInfo<String> arg0) {
            // TODO Auto-generated method stub
            LogUtils.i(name+":json返回:----->\n" + arg0.result.toString());
            onHttpResListener.doSuccess(arg0.result.toString());
//            HttpBaseData baseData = gsonUtil.getInstance().json2Bean(
//                    arg0.result, HttpBaseData.class);
//            String info = baseData.getInfo();
//            int status = (int) baseData.getStatus();
//            String data = baseData.getData().toString();
//            LogUtils.i("info:----->\n" + info);
//            LogUtils.i("data:----->\n" + data);
//            LogUtils.i("status:----->\n" + status);
//
//            if (onHttpResListener != null) {
//                if (status == 200) {
//                    onHttpResListener.doSuccess(arg0.result);
//                } else {
//                    onHttpResListener.doFailure(arg0.result);
//                }
//            }
            dismissDialog();
        }

        @Override
        public void onLoading(long total, long current, boolean isUploading) {
            // TODO Auto-generated method stub
        }

    }
}
