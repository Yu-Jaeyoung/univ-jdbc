package mobile.banking.customer.repository;

import mobile.banking.customer.Customer;
import mobile.banking.customer.repository.jdbc.JdbcCustomerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class CustomerRepositoryTest {
    // private static final JdbcCustomerRepository customerRepository = new JdbcCustomerRepository();

    @Autowired
    private CustomerRepository customerRepository;

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

    @Test
    @DisplayName("고객정보 수정 테스트")
    void update() {
        Customer customer = new Customer("Alice Kim", "alice@example.com", "010-0000-0000");
        Customer createdCustomer = customerRepository.save(customer);
        createdCustomer.setCellphone("010-0000-0000");
        customerRepository.update(createdCustomer);
        Customer updatedCustomer = customerRepository.findById(createdCustomer.getId()).get();
        assertThat(updatedCustomer).isEqualTo(createdCustomer);
    }

    @Test
    @DisplayName("고객정보 삭제 테스트")
    void delete() {
        Customer customer = new Customer("Alice Kim", "alice@example.com", "010-0000-0000");
        Customer createdCustomer = customerRepository.save(customer);
        customerRepository.delete(createdCustomer.getId());
        assertThat(customerRepository.findById(createdCustomer.getId())).isEmpty();
    }

    @Test
    @DisplayName("전체 검색 테스트")
    void findAll() {

        Customer customer1 = new Customer("Alice Kim", "alice@example.com", "010-0000-0000");
        Customer customer2 = new Customer("Bob Lee", "bob@example.com", "010-1111-1111");
        Customer customer3 = new Customer("Jack Lee", "jack@example.com", "010-2222-2222");

        List<Customer> expectedCustomerList = List.of(
                customerRepository.save(customer1),
                customerRepository.save(customer2),
                customerRepository.save(customer3)
        );

        List<Customer> actualCustomerList = customerRepository.findAll();

        assertThat(actualCustomerList.size()).isEqualTo(expectedCustomerList.size());
        assertThat(actualCustomerList).containsExactlyInAnyOrderElementsOf(expectedCustomerList);
    }
}