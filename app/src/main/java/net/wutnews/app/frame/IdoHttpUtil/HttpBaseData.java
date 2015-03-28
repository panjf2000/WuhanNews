package net.wutnews.app.frame.IdoHttpUtil;

import com.google.gson.JsonObject;

public class HttpBaseData {

	private String info;
	private JsonObject data;
	private double status;

	@Override
	public String toString() {
		return "HttpBaseData [data=" + data + ", info=" + info + ", status="
				+ status + "]";
	}

	public JsonObject getData() {
		return data;
	}

	public void setData(JsonObject data) {
		this.data = data;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public double getStatus() {
		return status;
	}

	public void setStatus(double status) {
		this.status = status;
	}

}
