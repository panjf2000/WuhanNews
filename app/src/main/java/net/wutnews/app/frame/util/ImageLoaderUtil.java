package net.wutnews.app.frame.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

/***
 * 异步加载图片工具类
 * @author Pan
 * <p>
 *      可自定义参数 <p>
 *      DisplayImageOptions 总设置 Options
 *      <p>
 *      读取时,读取失败,读取为空时的图片 OnLoadingImage,OnFailImage,OnEmptyImage
 *      <p>
 *      是否缓存 默认是 CacheInMemory,CacheOnDisk
 *      <p>
 *      圆角大小 默认10px setRoundPixels
 *      <P>
 *      设置图片内存缓存最大尺寸 内存默认2*1024*1024 maxMemoryCache 本地默认50*1024*1024
 *      maxDiskCache
 *      <p>
 *      最大线程数 默认5 maxThreadNums
 *      <p>
 *      本地缓存路径 默认"/ImageLoader/cache" cacheDirName
 * 
 * @author Pan
 * 
 */
public class ImageLoaderUtil {

	private ImageLoader imageLoader;
	private DisplayImageOptions options;
	private int onLoadingImage = 0;
	private int onFailImage = 0;
	private int onEmptyImage = 0;
	private boolean isCacheInMemory = true;
	private boolean isCacheOnDisk = true;
	private int roundPixels = 10;
	/**
	 * 设置图片内存缓存最大尺寸 默认2*1024*1024
	 */
	private int maxMemoryCache = 2 * 1024 * 1024;
	/**
	 * 设置图片本地缓存最大尺寸 默认50*1024*1024
	 */
	private int maxDiskCache = 50 * 1024 * 1024;
	/**
	 * 最大线程数 默认5
	 */
	private int maxThreadNums = 3;

	/**
	 * 本地缓存文件夹 默认 ImageLoader/cache
	 */
	private String cacheDirName = "/ImageLoader/cache";

	public DisplayImageOptions getOptions() {
		return options;
	}

	public void setOptions(DisplayImageOptions options) {
		this.options = options;
	}

	public void setOnLoadingImage(int onLoadingImage) {
		this.onLoadingImage = onLoadingImage;
	}

	public void setOnFailImage(int onFailImage) {
		this.onFailImage = onFailImage;
	}

	public void setOnEmptyImage(int onEmptyImage) {
		this.onEmptyImage = onEmptyImage;
	}

	public void setCacheInMemory(boolean isCacheInMemory) {
		this.isCacheInMemory = isCacheInMemory;
	}

	public void setCacheOnDisk(boolean isCacheOnDisk) {
		this.isCacheOnDisk = isCacheOnDisk;
	}

	public void setRoundPixels(int roundPixels) {
		this.roundPixels = roundPixels;
	}

	public void setMaxMemoryCache(int maxMemoryCache) {
		this.maxMemoryCache = maxMemoryCache;
	}

	public void setMaxDiskCache(int maxDiskCache) {
		this.maxDiskCache = maxDiskCache;
	}

	public void setMaxThreadNums(int maxThreadNums) {
		this.maxThreadNums = maxThreadNums;
	}

	public void setCacheDirName(String cacheDirName) {
		this.cacheDirName = cacheDirName;
	}

	private ImageLoaderUtil() {
		imageLoader = ImageLoader.getInstance();
	}

	private static class ImageLoaderUtilInstance {
		private static ImageLoaderUtil instance = new ImageLoaderUtil();
	}

	/**
	 * 单例模式 获取对象
	 */
	public static ImageLoaderUtil getInstance() {
		return ImageLoaderUtilInstance.instance;

	}

	/**
	 * imageloader全局设置 在application里调用
	 * 
	 * @param context
	 */
	public void initImageLoader(Context context) {
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				context)
				.diskCacheSize(maxDiskCache)
				.diskCache(
						new UnlimitedDiscCache(FileUtil
								.createFileDir(cacheDirName)))
				.memoryCacheSize(maxMemoryCache).threadPoolSize(maxThreadNums)
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.tasksProcessingOrder(QueueProcessingType.LIFO).build();
		// 全局初始化此配置
		imageLoader.init(config);
	}

	/**
	 * 默认设置 DisplayImageOptions
	 * 
	 * @return
	 */
	private void configImageLoader() {
		options = new DisplayImageOptions.Builder()
				.cacheOnDisk(isCacheOnDisk)
				.cacheInMemory(isCacheInMemory)
				.displayer(new RoundedBitmapDisplayer(roundPixels)).build();
	}

	/**
	 * 设置图片 可自定义图片设置
	 * 
	 * @param uri
	 *            图片路径
	 * @param imageView
	 *            显示图片控件
	 * 
	 * @author 网络图片 "http://site.com/image.png";
	 *      <p>
	 *      本地图片 "file:///mnt/sdcard/image.png";
	 *      <p>
	 *      相册图片 "content://media/external/audio/albumart/13";
	 *      <p>
	 *      assets图片 "assets://image.png";
	 *      <p>
	 *      drawble图片 "drawable://" + R.drawable.image; .9图不行
	 *      <p>
	 */
	public void setImage(String uri, ImageView imageView) {
		if (options == null) {
			configImageLoader();
		}

		imageLoader.displayImage(uri, imageView, options);
		isCacheOnDisk = true;
		isCacheInMemory = true;

	}

    /**
     * 设置图片并定义圆角
     * @param uri         图片路径
     * @param imageView   显示图片控件
     * @param roundPixels 圆角大小,单位像素
     */
    public void setImageRound(String uri,ImageView imageView,int roundPixels){
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheOnDisk(isCacheOnDisk)
                .cacheInMemory(isCacheInMemory)
                .displayer(new RoundedBitmapDisplayer(roundPixels)).build();
        imageLoader.displayImage(uri, imageView, options);
    }

	/**
	 * 通过uri获取bitmap uri格式详见 setImage方法说明
	 * 
	 * @param uri
	 * @return
	 */
	public Bitmap getBitMap(String uri) {
		return imageLoader.loadImageSync(uri);
	}
}
