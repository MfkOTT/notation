package com.ski.notation.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * aop拦截设置本地线程变量
 * 
 * @author maoyl05
 *
 */
@Aspect
@Order(1)
@Component
public class DataSourceAop {
	private static Logger log = LoggerFactory.getLogger(DataSourceAop.class);
	
	@Before("@annotation(com.ski.notation.config.annotation.Read)")
	public void setReadDataSourceType() {
		log.info("dataSource切换到：Read");
		com.ski.notation.config.DataSourceContextHolder.change2Read();
	}
	
	@Before("@annotation(com.ski.notation.config.annotation.Write))")
	public void setWriteDataSourceType() {
		log.info("dataSource切换到：write");
		DataSourceContextHolder.change2Write();
	}
}
