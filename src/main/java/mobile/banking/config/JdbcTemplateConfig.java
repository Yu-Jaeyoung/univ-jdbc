package mobile.banking.config;

import javax.sql.DataSource;

import mobile.banking.customer.CustomerService;
import mobile.banking.customer.repository.CustomerRepository;
import mobile.banking.customer.repository.jdbctemplate.JdbcTemplateCustomerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JdbcTemplateConfig {
    private final DataSource dataSource;

    public JdbcTemplateConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public CustomerRepository customerRepository() {
        return new JdbcTemplateCustomerRepository(dataSource);
    }

    @Bean
    public CustomerService customerService() {
        return new CustomerService(customerRepository());
    }
}
