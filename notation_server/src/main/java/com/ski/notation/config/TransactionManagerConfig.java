package com.ski.notation.config;

import com.ski.notation.config.DataSourceProxy;
import com.ski.notation.config.MybatisConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 配置事务管理器
 * 
 * @author maoyl05
 *
 */
@Configuration
@AutoConfigureAfter({ MybatisConfig.class })
@EnableTransactionManagement
public class TransactionManagerConfig extends DataSourceTransactionManagerAutoConfiguration {
	private static Logger log = LoggerFactory.getLogger(TransactionManagerConfig.class);

	/**
	 * 自定义事务 MyBatis自动参与到spring事务管理中，无需额外配置，
	 * 只要org.mybatis.spring.SqlSessionFactoryBean引用的数据源与DataSourceTransactionManager引用的数据源一致即可，否则事务管理会不起作用。
	 * 
	 * @return
	 */
	@Bean
	public DataSourceTransactionManager transactionManager(@Qualifier("dataSourceProxy") DataSourceProxy ds) {
		log.info("-------------------- transactionManager init ---------------------");
		return new DataSourceTransactionManager(ds);
	}
}
