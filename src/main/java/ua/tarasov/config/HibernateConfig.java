package ua.tarasov.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import static org.hibernate.cfg.Environment.*;

@Configuration
@ComponentScan(basePackages = "ua.tarasov")
@EnableTransactionManagement
@PropertySource(value = "classpath:application.properties")
public class HibernateConfig {

	@Autowired
	private Environment environment;

	@Bean
	public LocalSessionFactoryBean sessionFactory() {

		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		Properties properties = new Properties();
		properties.put(DRIVER, environment.getProperty("mysql.driver"));
		properties.put(URL, environment.getProperty("mysql.url"));
		properties.put(USER, environment.getProperty("mysql.user"));
		properties.put(PASS, environment.getProperty("mysql.password"));
		properties.put(SHOW_SQL, environment.getProperty("hibernate.show_sql"));
		properties.put(HBM2DDL_AUTO, environment.getProperty("hibernate.hbm2ddl.auto"));
		properties.put(DIALECT, environment.getProperty("hibernate.dialect"));
		properties.put(C3P0_MIN_SIZE, environment.getProperty("hibernate.c3p0.min_size"));
		properties.put(C3P0_MAX_SIZE, environment.getProperty("hibernate.c3p0.max_size"));
		properties.put(C3P0_ACQUIRE_INCREMENT, environment.getProperty("hibernate.c3p0.acquire_increment"));
		properties.put(C3P0_TIMEOUT, environment.getProperty("hibernate.c3p0.timeout"));
		properties.put(C3P0_MAX_STATEMENTS, environment.getProperty("hibernate.c3p0.max_statements"));
		sessionFactoryBean.setHibernateProperties(properties);
		sessionFactoryBean.setPackagesToScan("ua.tarasov.model");
		return sessionFactoryBean;
	}

	@Bean
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}
}
