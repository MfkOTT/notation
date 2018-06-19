package com.ski.notation.util;

import com.github.pagehelper.util.StringUtil;
import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: OkHttp
 * @Description:okhttp请求工具类
 * @author itw_liyx02
 * @date: 2017年4月23日
 * @version V1.0
 */
public class OkHttp {
	private static OkHttpClient httpClient;

	// 非常有必要，要不此类还是可以被new，但是无法避免反射，好恶心
	private OkHttp() {
		httpClient = new OkHttpClient();
	}

	// 使用单实例模式
	private static class LazyHolder {
		private static final OkHttp INSTANCE = new OkHttp();
	}

	// 获取当前实例
	public static OkHttp getInstance() {
		return LazyHolder.INSTANCE;
	}

	public String doPost(String url, String data) throws IOException {
		RequestBody body = RequestBody.create(
				MediaType.parse("application/json; charset=utf-8"), data);
		Request request = new Request.Builder().url(url).post(body).build();
		Response response = httpClient.newCall(request).execute();
		String rspString = "";
		if (response.isSuccessful()) {
			rspString = response.body().string();
		} else {
			throw new IOException("Unexpected code " + response);
		}
		return rspString;
	}

	public String doPostXML(String url, String data) throws IOException {
		RequestBody body = RequestBody.create(
				MediaType.parse("application/xml; charset=utf-8"), data);
		Request request = new Request.Builder().url(url).post(body).build();
		Response response = httpClient.newCall(request).execute();
		String rspString = "";
		if (response.isSuccessful()) {
			rspString = response.body().string();
		} else {
			throw new IOException("Unexpected code " + response);
		}
		return rspString;
	}
}
