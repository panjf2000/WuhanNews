package net.wutnews.app.frame.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaScannerConnection;
import android.os.Environment;
import android.os.StatFs;

import com.lidroid.xutils.util.LogUtils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.text.DecimalFormat;

/**
 * 文件处理工具类
 * 
 * @author Pan
 * 
 * 
 * 
 */
public class FileUtil {
	/**
	 * sd卡不存在
	 */
	public static final int COPY_FILE_RESULT_TYPE_SDCARD_NOT_EXIST = 101;
	/**
	 * 拷贝成功
	 */
	public static final int COPY_FILE_RESULT_TYPE_COPY_SUCCESS = 102;
	/**
	 * 拷贝失败
	 */
	public static final int COPY_FILE_RESULT_TYPE_COPY_FAILD = 103;
	/**
	 * 目录错误
	 */
	public static final int COPY_FILE_RESULT_TYPE_DIR_ERROR = 104;
	/**
	 * 源文件不存在
	 */
	public static final int COPY_FILE_RESULT_TYPE_SOURCE_FILE_NOT_EXIST = 105;
	/**
	 * 源文件已经存在
	 */
	public static final int COPY_FILE_RESULT_TYPE_SOURCE_FILE_EXIST = 106;
	
	/**
	 * 判断sd卡是否安装
	 * 
	 * @author Pan
	 * @return
	 */
	public static boolean existSdcard() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 获取SD卡的path
	 * 
	 * @author Pan
	 * @since 2014-11-09 
	 * @return
	 */
	public static String getSdPath() {
		File sdDir = null;
		boolean sdCardExist = Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
		if (sdCardExist) {
			sdDir = Environment.getExternalStorageDirectory(); // 获取根目录
		}
		if (sdDir != null) {
			return sdDir.toString();
		} else {
			return "";
		}
	}

	/**
	 * 读取assets下的文本数据
	 *
	 * @param fileName
	 * @return
	 */
	public static String getStringFromAssets(Context context, String fileName) {
		try {
			InputStreamReader inputReader = new InputStreamReader(context
					.getResources().getAssets().open(fileName));
			BufferedReader bufReader = new BufferedReader(inputReader);
			String line = "";
			String Result = "";
			while ((line = bufReader.readLine()) != null)
				Result += line;
			return Result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 读取文本流文件
	 *
	 * @param is
	 * @return
	 */
	public static String readStream(InputStream is) {
		try {
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			int i = is.read();
			while (i != -1) {
				bo.write(i);
				i = is.read();
			}
			return bo.toString();
		} catch (IOException e) {
			return "";
		}
	}

	/**
	 * 获得文件大小
	 *
	 * @param f
	 * @return
	 * @throws java.io.IOException
	 */
	@SuppressWarnings("resource")
	private static long getFileSize(File f) throws IOException {
		long s = 0;
		if (f.exists()) {
			FileInputStream fis = null;
			fis = new FileInputStream(f);
			s = fis.available();
		}
		return s;
	}

	/**
	 * 返回文件大小
	 *
	 * @param f
	 * @return
	 * @throws java.io.IOException
	 */
	public static String formatFileSize(File f) {// 转换文件大小
		String fileSizeString = "";
		try {
			long fileS = getFileSize(f);
			DecimalFormat df = new DecimalFormat("#.00");
			if (fileS < 1024) {
				fileSizeString = df.format((double) fileS) + "b";
			} else if (fileS < 1048576) {
				fileSizeString = df.format((double) fileS / 1024) + "kb";
			} else if (fileS < 1073741824) {
				fileSizeString = df.format((double) fileS / 1048576) + "mb";
			} else {
				fileSizeString = df.format((double) fileS / 1073741824) + "gb";
			}
		} catch (Exception e) {
		}
		return fileSizeString;
	}

	/**
	 * 拷贝资源文件到sd卡
	 *
	 * @param context
	 * @param resId
	 * @param databaseFilename
	 *            如数据库文件拷贝到sd卡中
	 */
	public static void copyResToSdcard(Context context, int resId,
			String databaseFilename) {// name为sd卡下制定的路径
		try {
			// 不存在得到数据库输入流对象
			InputStream is = context.getResources().openRawResource(resId);
			// 创建输出流
			FileOutputStream fos = new FileOutputStream(databaseFilename);
			// 将数据输出
			byte[] buffer = new byte[8192];
			int count = 0;
			while ((count = is.read(buffer)) > 0) {
				fos.write(buffer, 0, count);
			}
			// 关闭资源
			fos.close();
			is.close();
		} catch (Exception e) {
		}
	}

	/**
	 * 拷贝文件(默认不删除源文件)
	 *
	 * @param context
	 * @param fromPath
	 *            源文件完整路径
	 * @param toPath
	 *            目标文件完整路径
	 * @return 返回拷贝状态(详见该类静态变量: {@linkplain #COPY_FILE_RESULT_TYPE_COPY_FAILD},
	 *         {@link #COPY_FILE_RESULT_TYPE_COPY_SUCCESS},
	 *         {@link #COPY_FILE_RESULT_TYPE_DIR_ERROR},
	 *         {@link #COPY_FILE_RESULT_TYPE_SDCARD_NOT_EXIST},
	 *         {@link #COPY_FILE_RESULT_TYPE_SOURCE_FILE_EXIST},
	 *         {@link #COPY_FILE_RESULT_TYPE_SOURCE_FILE_NOT_EXIST})
	 * @see #copyFile(android.content.Context, String, String, boolean)
	 */
	public static int copyFile(Context context, String fromPath, String toPath) {
		return copyFile(context, fromPath, toPath, false);
	}

	/**
	 * 拷贝文件(默认不删除源文件)
	 *
	 * @param context
	 * @param fromFile
	 *            源文件
	 * @param toFile
	 *            目标文件
	 * @return 返回拷贝状态(详见该类静态变量: {@linkplain #COPY_FILE_RESULT_TYPE_COPY_FAILD},
	 *         {@link #COPY_FILE_RESULT_TYPE_COPY_SUCCESS},
	 *         {@link #COPY_FILE_RESULT_TYPE_DIR_ERROR},
	 *         {@link #COPY_FILE_RESULT_TYPE_SDCARD_NOT_EXIST},
	 *         {@link #COPY_FILE_RESULT_TYPE_SOURCE_FILE_EXIST},
	 *         {@link #COPY_FILE_RESULT_TYPE_SOURCE_FILE_NOT_EXIST})
	 * @see #copyFile(android.content.Context, java.io.File, java.io.File, boolean)
	 */
	public static int copyFile(Context context, File fromFile, File toFile) {
		return copyFile(context, fromFile, toFile, false);
	}

	/**
	 * 拷贝文件(可以选择是否删除源文件)
	 * 
	 * @param context
	 * @param fromPath
	 *            源文件路径
	 * @param toPath
	 *            目标文件路径
	 * @param isDeleteRes
	 *            是否删除源文件
	 * @return 返回拷贝状态(详见该类静态变量: {@linkplain #COPY_FILE_RESULT_TYPE_COPY_FAILD},
	 *         {@link #COPY_FILE_RESULT_TYPE_COPY_SUCCESS},
	 *         {@link #COPY_FILE_RESULT_TYPE_DIR_ERROR},
	 *         {@link #COPY_FILE_RESULT_TYPE_SDCARD_NOT_EXIST},
	 *         {@link #COPY_FILE_RESULT_TYPE_SOURCE_FILE_EXIST},
	 *         {@link #COPY_FILE_RESULT_TYPE_SOURCE_FILE_NOT_EXIST})
	 */
	public static int copyFile(Context context, String fromPath, String toPath,
			boolean isDeleteRes) {
		if (!StringUtil.isBlank(fromPath) || !StringUtil.isBlank(toPath)) {
			File fromFile = new File(fromPath);
			File toFile = new File(toPath);
			return copyFile(context, fromFile, toFile, isDeleteRes);
		} else {
			return COPY_FILE_RESULT_TYPE_DIR_ERROR;
		}
	}

	/**
	 * 拷贝文件(可以选择是否删除源文件)
	 * 
	 * @param context
	 * @param fromFile
	 *            源文件
	 * @param toFile
	 *            目标文件
	 * @param isDeleteRes
	 *            是否删除源文件
	 * @return 返回拷贝状态 (详见该类静态变量: {@linkplain #COPY_FILE_RESULT_TYPE_COPY_FAILD},
	 *         {@link #COPY_FILE_RESULT_TYPE_COPY_SUCCESS},
	 *         {@link #COPY_FILE_RESULT_TYPE_DIR_ERROR},
	 *         {@link #COPY_FILE_RESULT_TYPE_SDCARD_NOT_EXIST},
	 *         {@link #COPY_FILE_RESULT_TYPE_SOURCE_FILE_EXIST},
	 *         {@link #COPY_FILE_RESULT_TYPE_SOURCE_FILE_NOT_EXIST})
	 */

	private static int copyFile(Context context, File fromFile, File toFile,
			boolean isDeleteRes) {
		if (fromFile == null || toFile == null) {
			return COPY_FILE_RESULT_TYPE_DIR_ERROR;
		}
		if (existSdcard()) {
			try {
				if (fromFile.exists()) {
					String toPath = toFile.getAbsolutePath();
					String toDir = toPath.substring(0, toPath.lastIndexOf("/"));
					File toDirFile = new File(toDir);
					if (!toDirFile.exists()) {
						toDirFile.mkdirs();
					}
					if (!toFile.exists()) {
						toFile.createNewFile();
					}
					FileInputStream in = new FileInputStream(fromFile);
					FileOutputStream out = new FileOutputStream(toFile);
					byte[] buffer = new byte[1024];
					int count = 0;
					while ((count = in.read(buffer)) > 0) {
						out.write(buffer, 0, count);
					}
					// 关闭资源
					out.close();
					in.close();
				} else {
					return COPY_FILE_RESULT_TYPE_SOURCE_FILE_NOT_EXIST;
				}
				if (isDeleteRes) {// 判断是否删除源文件
					if (fromFile.exists()) {
						fromFile.delete();
					}
				}
				if (toFile.exists()) {
					return COPY_FILE_RESULT_TYPE_COPY_SUCCESS;
				} else {
					return COPY_FILE_RESULT_TYPE_COPY_FAILD;
				}
			} catch (Exception e) {
				return COPY_FILE_RESULT_TYPE_COPY_FAILD;
			}
		} else {
			return COPY_FILE_RESULT_TYPE_SDCARD_NOT_EXIST;
		}
	}

	/**
	 * 创建文件至SD卡
	 * 
	 * @param dirName
	 * @param fileName /dirname/+文件名
	 * @return
	 */
	public static File createFile(String dirName,
			String fileName) {
		if (existSdcard()) {
			File dir = new File(getSdPath()+dirName);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			return new File(dir, fileName);
		}
		return null;
	}
	
	/**
	 *  创建文件夹到SD卡根目录下
	 * @param dirName 文件夹名字 栗子: /dirname
	 * @return
	 */
	public static File createFileDir(String dirName){
		if(existSdcard()){
			File dir = new File(getSdPath()+dirName);
			if(!dir.exists()){
				dir.mkdirs();
			}
			return dir;
		}
		return null;
	}

	/**
	 * 
	 * 获取文件后缀名
	 * 
	 * @param file
	 * @see #MIME_MapTable
	 */
	public String getMIMEType(File file) {
		String type = "*/*";
		String fName = file.getName();
		int dotIndex = fName.lastIndexOf(".");
		if (dotIndex < 0) {
			return type;
		}
		String end = fName.substring(dotIndex, fName.length()).toLowerCase();
		if (end == "")
			return type;
		for (int i = 0; i < MIME_MapTable.length; i++) {
			if (end.equals(MIME_MapTable[i][0]))
				type = MIME_MapTable[i][1];
		}
		return type;
	}

	/**
	 * 后缀名格式表
	 */
	private final String[][] MIME_MapTable = { { ".3gp", "video/3gpp" },
			{ ".apk", "application/vnd.android.package-archive" },
			{ ".asf", "video/x-ms-asf" }, { ".avi", "video/x-msvideo" },
			{ ".bin", "application/octet-stream" }, { ".bmp", "image/bmp" },
			{ ".c", "text/plain" }, { ".class", "application/octet-stream" },
			{ ".conf", "text/plain" }, { ".cpp", "text/plain" },
			{ ".doc", "application/msword" },
			{ ".exe", "application/octet-stream" }, { ".gif", "image/gif" },
			{ ".gtar", "application/x-gtar" }, { ".gz", "application/x-gzip" },
			{ ".h", "text/plain" }, { ".htm", "text/html" },
			{ ".html", "text/html" }, { ".jar", "application/java-archive" },
			{ ".java", "text/plain" }, { ".jpeg", "image/jpeg" },
			{ ".jpg", "image/jpeg" }, { ".js", "application/x-javascript" },
			{ ".log", "text/plain" }, { ".m3u", "audio/x-mpegurl" },
			{ ".m4a", "audio/mp4a-latm" }, { ".m4b", "audio/mp4a-latm" },
			{ ".m4p", "audio/mp4a-latm" }, { ".m4u", "video/vnd.mpegurl" },
			{ ".m4v", "video/x-m4v" }, { ".mov", "video/quicktime" },
			{ ".mp2", "audio/x-mpeg" }, { ".mp3", "audio/x-mpeg" },
			{ ".mp4", "video/mp4" },
			{ ".mpc", "application/vnd.mpohun.certificate" },
			{ ".mpe", "video/mpeg" }, { ".mpeg", "video/mpeg" },
			{ ".mpg", "video/mpeg" }, { ".mpg4", "video/mp4" },
			{ ".mpga", "audio/mpeg" },
			{ ".msg", "application/vnd.ms-outlook" }, { ".ogg", "audio/ogg" },
			{ ".pdf", "application/pdf" }, { ".png", "image/png" },
			{ ".pps", "application/vnd.ms-powerpoint" },
			{ ".ppt", "application/vnd.ms-powerpoint" },
			{ ".prop", "text/plain" },
			{ ".rar", "application/x-rar-compressed" },
			{ ".rc", "text/plain" }, { ".rmvb", "audio/x-pn-realaudio" },
			{ ".rtf", "application/rtf" }, { ".sh", "text/plain" },
			{ ".tar", "application/x-tar" },
			{ ".tgz", "application/x-compressed" }, { ".txt", "text/plain" },
			{ ".wav", "audio/x-wav" }, { ".wma", "audio/x-ms-wma" },
			{ ".wmv", "audio/x-ms-wmv" },
			{ ".wps", "application/vnd.ms-works" }, { ".xml", "text/xml" },
			{ ".xml", "text/plain" }, { ".z", "application/x-compress" },
			{ ".zip", "application/zip" }, { "", "*/*" } };

	/**
	 * 计算sdcard上的剩余空间,新方法无法支持api-18以下的系统
	 * 
	 * @return
	 */
	@SuppressLint("NewApi")
	public static long freeSpaceOnSdCard() {
		File path = Environment.getExternalStorageDirectory(); // 取得sdcard文件路径
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSizeLong();
		long availableBlocks = stat.getAvailableBlocksLong();
		LogUtils.i("SD Free Size: " + availableBlocks * blockSize + " byte");
		return availableBlocks * blockSize;

	}


	/**
	 * 删除文件及文件目录下的所有文件
	 * 
	 * @param file
	 */
	public static void deleteFileUnderFolder(File file) {
		if (!file.exists()) {
			return;
		} else {
			if (file.isFile()) {
				file.delete();
				return;
			}
			if (file.isDirectory()) {
				File[] childFile = file.listFiles();
				if (childFile == null || childFile.length == 0) {
					return;
				}
				for (File f : childFile) {
					deleteFileUnderFolder(f);
				}
			}
		}
	}

	/**
	 * 根据文件名获取资源id
	 * 
	 * @param variableName
	 * @param context
	 * @param c
	 * @return
	 */
	public static int getResIdByFieldName(String variableName, Context context,
			Class<?> c) {
		try {
			Field idField = c.getDeclaredField(variableName);
			return idField.getInt(idField);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * 获取存储路径，如果sd卡存在，使用sd卡，否则存储本地
	 * 
	 * @param folderName
	 * @param fileName
	 * @param format
	 * @return
	 */
	public static File getFileCustom(String folderName, String fileName,
			String format) {
		File file = null;
		String saveDir = "";
		if (existSdcard()) {
			saveDir = getSdPath() + File.separator + folderName;
		} else {
			saveDir = File.separator + folderName;
		}
		File dir = new File(saveDir);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		if (null == format || "".equals(format)) {
			file = new File(saveDir, fileName);
		} else {
			file = new File(saveDir, fileName + "." + format);
		}
		file.delete();
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return file;
	}
    /**
     * 删除方法 这里只会删除某个文件夹下的文件，如果传入的directory是个文件，将不做处理
     *
     * @param directory
     * @return
     */
    public static  void deleteFilesByDirectory(File directory) {
        if (directory != null && directory.exists() && directory.isDirectory()) {
            for (File item : directory.listFiles()) {
                String fileName=item.getName();
                item.delete();
            }
        }

    }
}
