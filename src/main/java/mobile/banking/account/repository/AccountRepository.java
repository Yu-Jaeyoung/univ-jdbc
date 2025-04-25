package mobile.banking.account.repository;

import mobile.banking.account.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {
    Account save(final Account account);

    Optional<Account> findById(final Long id);

    List<Account> findAll();

    int update(final Account account);

    int delete(final Long id);

    List<Account> findByCustomerId(Long customerId);
}
