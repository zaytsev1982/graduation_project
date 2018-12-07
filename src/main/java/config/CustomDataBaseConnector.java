package config;

import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("service")
@EnableTransactionManagement
@EnableJpaRepositories("repository")
@PropertySource("classpath:db.properties")
public class CustomDataBaseConnector {

    @Value("${db.dialect}")
    private String dialect;

    @Value("${hibernate.show_sql}")
    private String show;

    @Value("${hibernate.hbm2ddl.auto}")
    private String hibernateHmb;

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.H2)
            .addScript("script/schema.sql")
            .addScript("script/insert.sql")
            .build();
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean bean =
            new LocalContainerEntityManagerFactoryBean();
        bean.setDataSource(dataSource());
        bean.setJpaProperties(properties());
        bean.setPackagesToScan("model");
        bean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return bean;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }


    private Properties properties() {
        Properties properties = new Properties();
        properties.setProperty("db.dialect", dialect);
        properties.setProperty("hibernate.show_sql", show);
        properties.setProperty("hibernate.hbm2ddl.auto", hibernateHmb);
        return properties;
    }
}
