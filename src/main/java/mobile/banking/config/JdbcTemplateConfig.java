package mobile.banking.config;

import javax.sql.DataSource;

import mobile.banking.account.AccountHistoryService;
import mobile.banking.account.AccountService;
import mobile.banking.account.repository.AccountHistoryRepository;
import mobile.banking.account.repository.AccountRepository;
import mobile.banking.account.repository.jdbctemplate.JdbcTemplateAccountHistoryRepository;
import mobile.banking.account.repository.jdbctemplate.JdbcTemplateAccountRepository;
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

    @Bean
    public AccountRepository accountRepository() {
        return new JdbcTemplateAccountRepository(dataSource);
    }

    @Bean
    public AccountService accountService() {
        return new AccountService(accountRepository(), accountHistoryRepository());
    }

    @Bean
    public AccountHistoryService accountHistoryService() {
        return new AccountHistoryService(accountHistoryRepository());
    }

    @Bean
    public AccountHistoryRepository accountHistoryRepository() {
        return new JdbcTemplateAccountHistoryRepository(dataSource);
    }
}
