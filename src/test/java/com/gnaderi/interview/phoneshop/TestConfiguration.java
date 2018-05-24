package com.gnaderi.interview.phoneshop;

import com.gnaderi.interview.phoneshop.repository.ProductCrudRepository;
import com.gnaderi.interview.phoneshop.repository.ProductTypeCrudRepository;
import com.gnaderi.interview.phoneshop.service.InvoiceCalculatorService;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import java.beans.PropertyVetoException;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackageClasses = {ProductCrudRepository.class, ProductTypeCrudRepository.class})
public class TestConfiguration {
    @Bean(destroyMethod = "shutdown")
    public EmbeddedDatabase dataSource() {
        return new EmbeddedDatabaseBuilder().
                setType(EmbeddedDatabaseType.H2).
                addScript("sql/PhoneShopDBScript.sql").
                build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws ClassNotFoundException, PropertyVetoException {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setJpaProperties(new Properties());
        emf.setJpaVendorAdapter(jpaAdapter());
        emf.setPackagesToScan("com.gnaderi.interview.phoneshop");
        return emf;
    }

    private JpaVendorAdapter jpaAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        return new JpaTransactionManager();
    }

    @Bean
    InvoiceCalculatorService calculatorService() {
        return new InvoiceCalculatorService();
    }

    @Bean
    public KieContainer kieContainer() {
        KieServices kieServices = KieServices.Factory.get();
        return kieServices.getKieClasspathContainer();
    }

    @Bean
    public TestRestTemplate restTemplateBuilder() {
        return new TestRestTemplate();
    }

}
