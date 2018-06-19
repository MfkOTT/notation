package com.ski.notation.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import com.github.pagehelper.PageInterceptor;
import com.ski.notation.config.DataSourceConfig;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Mybatis配置
 *
 * @author maoyl05
 *
 */
@Configuration
@AutoConfigureAfter({ DataSourceConfig.class })
@MapperScan(basePackages = "com.ski.notation.dao")
public class MybatisConfig {

	private static Logger log = LoggerFactory.getLogger(MybatisConfig.class);

	@Bean
	public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSourceProxy") DataSourceProxy ds) throws Exception {
		log.info("-------------------- sqlSessionFactory init ---------------------");
		SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
		fb.setDataSource(ds);
		//分页插件
		PageInterceptor interceptor = new PageInterceptor();
		Properties properties = new Properties();
		properties.setProperty("reasonable", "false");
		properties.setProperty("pageSizeZero", "true");
		interceptor.setProperties(properties);
		fb.setPlugins(new Interceptor[]{interceptor});
		return fb.getObject();
	}

	@Bean
	public DataSourceProxy dataSourceProxy(@Qualifier("writeDataSource") DataSource wds,
										   @Qualifier("readDataSource") DataSource rds) {
		log.info("-------------------- dataSourceProxy init ---------------------");
		Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
		targetDataSources.put(DataSourceType.write.getType(), wds);
		targetDataSources.put(DataSourceType.read.getType(), rds);

		DataSourceProxy proxy = new DataSourceProxy();
		proxy.setDefaultTargetDataSource(wds);
		proxy.setTargetDataSources(targetDataSources);
		return proxy;
	}

}
