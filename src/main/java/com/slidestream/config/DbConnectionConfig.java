package com.slidestream.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbConnectionConfig {
    Logger LOGGER = LoggerFactory.getLogger(DbConnectionConfig.class);

//    @Override
//    public PlatformTransactionManager annotationDrivenTransactionManager() {
//        try {
//            HibernateTransactionManager txManager = new HibernateTransactionManager();
//            txManager.setSessionFactory(sessionFactory(txManager.getDataSource()));
//            txManager.setDefaultTimeout(30000);
//            txManager.setRollbackOnCommitFailure(true);
//            return txManager;
//        } catch(IOException e) {
//            LOGGER.error("Configuration of hibernate session failed!", e);
//            return null;
//        }
//    }

//    @Bean
//    public SessionFactory sessionFactory(DataSource dataSource) throws IOException
//    {
//            LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
//            localSessionFactoryBean.setDataSource(dataSource);
//            Properties hibernateProperties = new Properties();
//            hibernateProperties.setProperty(Environment.DIALECT, "org.hibernate.dialect.SQLServerDialect");
//            hibernateProperties.setProperty(Environment.FLUSH_BEFORE_COMPLETION, "true");
//            hibernateProperties.setProperty(Environment.URL, "jdbc:sqlserver://LOCALHOST;databaseName=SLIDESTREAM");
//            hibernateProperties.setProperty(Environment.USER, "sa");
//            hibernateProperties.setProperty(Environment.PASS, "letmein");
//            hibernateProperties.setProperty(Environment.SHOW_SQL, "false");
//            hibernateProperties.setProperty(Environment.ORDER_INSERTS, "true");
//            hibernateProperties.setProperty(Environment.STATEMENT_BATCH_SIZE, "50");
//            hibernateProperties.setProperty(Environment.DEFAULT_BATCH_FETCH_SIZE, "50");
//            hibernateProperties.setProperty(Environment.MAX_FETCH_DEPTH, "3");
//            localSessionFactoryBean.setHibernateProperties(hibernateProperties);
//            localSessionFactoryBean.setPackagesToScan("uk.co.utilisoft.tool.domain");
//            localSessionFactoryBean.afterPropertiesSet();
//            return localSessionFactoryBean.getObject();
//    }
}
