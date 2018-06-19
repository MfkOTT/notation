package com.ski.notation.config;

import com.ski.notation.config.DataSourceContextHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 构建数据源代理，使满足数据源动态切换
 * 
 * @author maoyl05
 *
 */
public class DataSourceProxy extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		// 获取当前线程中设置的变量值
		return DataSourceContextHolder.getDatabaseType();
	}
}
