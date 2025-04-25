package mobile.banking.customer.repository;

import mobile.banking.customer.Customer;
import mobile.banking.customer.CustomerSearchCondition;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
    Customer save(final Customer customer);

    Optional<Customer> findById(final Long id);

    List<Customer> findAll();

    int update(final Customer customer);

    int delete(final Long id);

    Optional<Customer> findByEmail(String email);

    List<Customer> findCustomers(CustomerSearchCondition cond);
}
