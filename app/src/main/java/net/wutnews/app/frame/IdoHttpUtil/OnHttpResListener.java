package net.wutnews.app.frame.IdoHttpUtil;

/**
 * 
 * @author Pan 服务器返回数据监听
 */
public interface OnHttpResListener {

	/**
	 * 
	 * @param data
	 *            返回json数据
	 */
	public void doSuccess(String data);
//
//	/**
//	 *
//	 * @param data
//	 *            返回json数据
//	 */
//	public void doFailure(String data);
//
//	/**
//	 *
//	 * @param name
//	 *            接口描述
//	 * @param info
//	 *            服务器返回错误信息
//	 */
//	public void serveError(String info, String name);
//
//	/**
//	 *
//	 * @param info
//	 *            错误信息
//	 * @param name
//	 *            接口描述
//	 */
//	public void localError(String info, String name);

}
