package net.wutnews.app.app.util;

import net.wutnews.app.frame.IdoHttpUtil.HttpSender;

/**
 * Created by Pan on 2015/1/22 0022.
 */
public class uurl {

    public static final HttpSender.HttpMode MODE = HttpSender.HttpMode.Get;
    public static final String SERVER_URL = "Http://lgfb.wutnews.net/";//服务器地址
    public static final String IMG_URL = "http://lgfb.wutnews.net/data/upload/";//图片下载地址前缀
    public static final String AES_KEY = "wutnews77AZXtvRf";//AES加密KEY



    public static final String GetNewsList = SERVER_URL+"api/post/info";//新闻列表
    public static final String GetTermList = SERVER_URL+"api/term/info";//栏目列表
    public static final String GetLogin = SERVER_URL+"api/login/school";//校园卡登录
    public static final String GetGuestLogin = SERVER_URL+"api/login/guest";//游客登录
    public static final String CollectNews = SERVER_URL+"api/collect/newa";//收藏新闻
    public static final String CollectList = SERVER_URL+"api/collect/show";//获取收藏
    public static final String DeleteCollection=SERVER_URL+"api/collect/delete";//删除收藏
    public static final String SubscribeTerm=SERVER_URL+"api/term/subscribe";//订阅栏目
    public static final String UnsubscribeTerm=SERVER_URL+"api/term/cancel";//取消订阅
    public static final String SendComment=SERVER_URL+"api/comment/insert";
    public static final String CommentList=SERVER_URL+"api/comment/show";
    public static final String DeleteComment=SERVER_URL+"api/comment/delete";
}
