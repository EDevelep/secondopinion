package org.secondopinion.configurations;

import java.util.Properties;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class BeanConfig {
	
	
	@Autowired
	private AppProperties appProperties;

	@Bean(name = "sessinFactory")
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(new String[] { appProperties.getDb().getPackagesToScan() });
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}

	@Bean(destroyMethod = "")
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(appProperties.getDb().getDriver());
		dataSource.setUrl(appProperties.getDb().getUrl());
		dataSource.setUsername(appProperties.getDb().getUserName());
		dataSource.setPassword(appProperties.getDb().getPassword());
		return dataSource;
	}

	@SuppressWarnings("serial")
	Properties hibernateProperties() {
		return new Properties() {
			{
				setProperty("hibernate.hbm2ddl.auto", "false");
				setProperty("hibernate.dialect", appProperties.getHibernate().getDialect()); // "org.hibernate.dialect.MySQL5Dialect");
				setProperty("hibernate.show_sql", appProperties.getHibernate().getShow_sql());// "true");
			}
		};
	}

	@Bean
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}

}
