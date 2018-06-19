package com.ski.notation.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 定义所有涉及到的数据源
 * 
 * @author maoyl05
 *
 */
@Configuration
public class DataSourceConfig {
	private static Logger log = LoggerFactory.getLogger(DataSourceConfig.class);

	@Value("${datasource.type}")
	private Class<? extends DataSource> dataSourceType;

	/**
	 * 配置写库
	 * 
	 * @return
	 */
	@Bean(name = "writeDataSource", destroyMethod="close")
	@Primary
	@ConfigurationProperties(prefix = "datasource.write")
	public DataSource writeDataSource() {
		log.info("-------------------- writeDataSource init --------------------");
		return DataSourceBuilder.create().type(dataSourceType).build();
	}

	/**
	 * 配置读库
	 * 
	 * @return
	 */
	@Bean(name = "readDataSource", destroyMethod="close")
	@ConfigurationProperties(prefix = "datasource.read")
	public DataSource readDataSourceOne() {
		log.info("-------------------- readDataSource init --------------------");
		return DataSourceBuilder.create().type(dataSourceType).build();
	}
}
