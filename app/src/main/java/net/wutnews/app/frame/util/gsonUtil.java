package net.wutnews.app.frame.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * JSON转换工具类 使用前调用getInstance方法
 * 
 * @author Pan
 * 
 * @date 2014-11-13
 * @toJson 将对象转换成json字符串
 * @getValue 在json字符串中，根据key值找到value
 * @json2Map 将json格式转换成map对象
 * @json2Bean 将json转换成bean对象
 * @json2List 将json格式转换成List对象
 * @Obj2Map obj 转为 map
 * 
 */
public class gsonUtil {

	private Gson gson;

	private gsonUtil() {
		gson = new Gson();
	}

	private static class gsonUtilHolder {
		private static gsonUtil instance = new gsonUtil();
	}

	/**
	 * 使用方法前调用getInstance,获得gsonUtil实例
	 * @return
	 */
	public static gsonUtil getInstance() {
		return gsonUtilHolder.instance;
	}

	/**
	 * 将对象转换成json字符串
	 * 
	 * @param obj
	 * @return
	 */
	public String toJson(Object obj) {
		if (obj == null) {
			return "{}";
		}
		return gson.toJson(obj);
	}

	/**
	 * 在json字符串中，根据key值找到value
	 * 
	 * @param json
	 * @param key
	 * @return
	 */
	public Object getValue(String json, String key) {
		Object rulsObj = null;
		Map<?, ?> rulsMap = json2Map(json);
		if (rulsMap != null && rulsMap.size() > 0) {
			rulsObj = rulsMap.get(key);
		}
		return rulsObj;
	}

	/**
	 * 将json格式转换成map对象
	 * 
	 * @param json
	 * @return
	 */
	public Map<String, Object> json2Map(String json) {
		Map<String, Object> objMap = null;
		if (gson != null) {
			Type type = new TypeToken<Map<String, Object>>() {
			}.getType();
			objMap = gson.fromJson(json, type);
		}
		if (objMap == null) {
//			objMap = new HashMap<String, Object>();
			return null;
		}
		return objMap;
	}

	/**
	 * 将json转换成bean对象
	 * 
	 * @param <T>
	 * @param json
	 * @param clazz
	 * @return
	 */
	public <T> T json2Bean(String json, Class<T> clazz) {
		T obj = null;
		if (gson != null) {
			obj = gson.fromJson(json, clazz);
		}
		return obj;
	}

	/**
	 * 将json格式转换成List对象
	 * 
	 * @param type
	 * @param json
	 * @return
	 */
	public Object json2List(String json, Type type) {
		if (gson != null) {
			return gson.fromJson(json, type);
		}
		return null;
	}

	
	/**
	 * obj 转为 map
	 * @param obj 需要转的对象
	 * @return 
	 */
	public Map<String, Object> Obj2Map(Object obj) {
		if (obj != null) {
			return json2Map(toJson(obj));
		}
		return null;
	}

}
