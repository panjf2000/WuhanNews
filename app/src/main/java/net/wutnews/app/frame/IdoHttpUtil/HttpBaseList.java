package net.wutnews.app.frame.IdoHttpUtil;

import java.util.List;

public class HttpBaseList<T> {

	private List<T> list;

	@Override
	public String toString() {
		return "HttpBaseList [list=" + list + "]";
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

}
