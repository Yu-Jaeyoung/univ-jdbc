package mobile.banking.customer.repository.jdbc;

import mobile.banking.customer.Customer;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JdbcCustomerRepositoryTest {
    private static final JdbcCustomerRepository customerRepository
            = new JdbcCustomerRepository();

    @Test
    void crud() {
        Customer customer = new Customer("홍길자", "giljaa@example.com", "010-4567-8765");

        // create
        Customer createdCustomer = customerRepository.save(customer);

        // read
        Customer findCustomer = customerRepository.findById(createdCustomer.getId()).get();
        assertThat(findCustomer).isEqualTo(createdCustomer);

        // update
        createdCustomer.setCellphone("010-0000-0000");
        customerRepository.update(createdCustomer);
        Customer updatedCustomer = customerRepository.findById(createdCustomer.getId()).get();
        assertThat(updatedCustomer).isEqualTo(createdCustomer);

        //delete
        customerRepository.delete(createdCustomer.getId());
        assertThat(customerRepository.findById(createdCustomer.getId())).isEmpty();
    }
}