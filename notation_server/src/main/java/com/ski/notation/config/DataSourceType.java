package com.ski.notation.config;

public enum DataSourceType {
	read("read", "从库"), write("write", "主库");
	private String type;
	private String name;

	DataSourceType(String type, String name) {
		this.type = type;
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}
	
}
