package com.ski.notation.config;

public class DataSourceContextHolder {
	private static final ThreadLocal<String> local = new ThreadLocal<String>();

	public static ThreadLocal<String> getLocal() {
		return local;
	}

	/**
	 * 切换到读库
	 */
	public static void change2Read() {
		local.set(com.ski.notation.config.DataSourceType.read.getType());
	}

	/**
	 * 切换到写库
	 */
	public static void change2Write() {
		local.set(DataSourceType.write.getType());
	}

	public static String getDatabaseType() {
		return local.get();
	}
}
