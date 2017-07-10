package com.marcuschiu.config.data;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Properties;

/**
 * Created by marcus.chiu on 10/16/16.
 * @Configuration - indicates this class contains bean(s) methods annotated with @Bean
 * @ComponentScan - equivalent to 'context:component-scan base-package="..." in web.xml
 * This provides Spring where to look up for beans/classes
 * It will scan for any @Component classes (this includes @Controller, @Service, @Repository)
 * @EnableTransactionManagement - equivalent to Spring's tx:* XML namespace
 * enables Spring's annotation-driven transaction management
 * @PropertySource - used to declare a set of properties (which are defined in a
 * properties file in application classpath) into Spring run-time Environment bean
 */
@Configuration
@EnableTransactionManagement
public class PersistenceConfig {

    private String entityPath = "com.marcuschiu.data";

    @Value("${jdbc.driverClassName}")
    private String driverClassName;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    @Value("${hibernate.dialect}")
    private String hibernateDialect;

    @Value("${hibernate.show_sql}")
    private Boolean hibernateShowSQL;

    @Value("${hibernate.format_sql}")
    private Boolean hibernateFormatSQL;

    /**
     * This method creates a LocalSessionFactoryBean
     * This method mirrors exactly the XML based a_configuration
     * It will be injected into Bean method transactionManager
     * @return LocalSessionFactoryBean
     */
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(entityPath);
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    /**
     * This method is called from sessionFactory()
     * @return Properties -
     */
    private Properties hibernateProperties() {
        //create new properties object
        Properties properties = new Properties();

        //start - set properties object's configurations

        properties.put("hibernate.dialect", hibernateDialect);
        properties.put("hibernate.show_sql", hibernateShowSQL);
        properties.put("hibernate.format_sql", hibernateFormatSQL);

        //end

        //return properties object
        return properties;
    }

    /**
     * This method is called from sessionFactory()
     * @return DataSource - the database connection, source of data, data source
     */
    @Bean
    @DependsOn("flyway")
    public DataSource dataSource() {
        //create new data source object

        // DriverManagerDataSource needs group id = 'org.springframework' artifact id = 'spring-jdbc' in maven
        // DriverManagerDataSource dataSource = new DriverManagerDataSource();

        // BasicDataSource needs 'commons-dbcp' in maven
        BasicDataSource dataSource = new BasicDataSource();

        //start - set the data source object's configurations

        //set database driver type
        dataSource.setDriverClassName(driverClassName);
        //set url of database
        dataSource.setUrl(url);
        //set username of database
        dataSource.setUsername(username);
        //set password of database
        dataSource.setPassword(password);

        //Prevent MySQL timeouts
        // http://blog.netgloo.com/2015/07/09/spring-boot-communications-link-failure-with-mysql-and-hibernate/
//        dataSource.setTestWhileIdle(true);
//        dataSource.setTimeBetweenEvictionRunsMillis(60000);
//        dataSource.setValidationQuery("SELECT 1");
        dataSource.setDefaultTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

        //end

        //return data source object
        return dataSource;
    }

    /**
     * @param sessionFactory is a bean created from the sessionFactory() Bean method
     *                       is "Autowired" by Spring
     * @return HibernateTransactionManager - provide transaction support for the session
     * created by the sessionFactory
     */
    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);
        return txManager;
    }


    //Bottom two methods for dealing with EntityManagerFactory bean

    @Bean
    public Properties hibernateProperties2(){
        final Properties properties = new Properties();

        properties.put( "hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect" );
        properties.put( "hibernate.connection.driver_class", "org.postgresql.Driver" );
        properties.put( "hibernate.hbm2ddl.auto", "create-drop" );

        return properties;
    }

//    /**
//     * JPA EntityManagerFactory
//     * @param dataSource
//     * @return EntityManagerFactory - this creates EntityManager Objects
//     */
//    @Bean
//    public EntityManagerFactory entityManagerFactory( DataSource dataSource){
//        System.out.println("creating EntityManagerFactory");
//        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(dataSource);
//        em.setPackagesToScan("com.kibo.order.data.entity.entity");
//        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//        em.setJpaProperties(hibernateProperties());
//        em.setPersistenceUnitName("mytestdomain");
//        em.setPersistenceProviderClass(HibernatePersistenceProvider.class);
//        em.afterPropertiesSet();
//
//        return em.getObject();
//    }
}