package com.connecture.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static com.connecture.config.DataSourceProoperties.*;
import static java.util.stream.Collectors.toList;

@Configuration
@ComponentScan(basePackages = "com.connecture")
@EnableWebMvc
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class MvcConfiguration extends WebMvcConfigurerAdapter {

  @Autowired
  private Environment environment;

  @Bean
  public ViewResolver getViewResolver() {
    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
    resolver.setPrefix("/WEB-INF/views/");
    resolver.setSuffix(".jsp");
    return resolver;
  }

  @Bean
  public LocalSessionFactoryBean sessionFactory() {
    LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
    sessionFactory.setDataSource(dataSource());
    sessionFactory.setPackagesToScan(
      "com.connecture.model.entity");
    sessionFactory.setHibernateProperties(hibernateProperties());

    return sessionFactory;
  }

  @Bean
  public DataSource dataSource() {
    BasicDataSource dataSource = new BasicDataSource();
    dataSource.setDriverClassName(environment.getProperty(DS_DRIVER_CLASS.getValue()));
    dataSource.setUrl(environment.getProperty(DS_URL.getValue()));
    dataSource.setUsername(environment.getProperty(DS_USERNAME.getValue()));
    dataSource.setPassword(environment.getProperty(DS_PASSWORD.getValue()));

    return dataSource;
  }

  @Bean
  @Autowired
  public HibernateTransactionManager transactionManager(
    SessionFactory sessionFactory) {

    HibernateTransactionManager txManager
      = new HibernateTransactionManager();
    txManager.setSessionFactory(sessionFactory);

    return txManager;
  }

  public Properties hibernateProperties() {
    Properties properties = new Properties();

    List<String> propertiesList = Arrays.stream(HibernateProperties.values())
      .map(HibernateProperties::getValue)
      .map(environment::getProperty)
      .collect(toList());

    Arrays.stream(HibernateProperties.values())
      .map(HibernateProperties::getValue)
      .forEach(propKey -> properties.setProperty(propKey, environment.getProperty(propKey)));

    return properties;
  }


  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
  }
}
