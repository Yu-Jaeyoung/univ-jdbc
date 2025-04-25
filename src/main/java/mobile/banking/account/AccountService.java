package mobile.banking.account;

import mobile.banking.account.repository.AccountRepository;

import java.util.List;
import java.util.Optional;

public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public List<Account> findAccounts() {
        return accountRepository.findAll();
    }

    public Optional<Account> findAccountById(Long id) {
        return accountRepository.findById(id);
    }


    public int updateAccount(Account account) {
        return accountRepository.update(account);
    }

    public int removeAccount(Long id) {
        return accountRepository.delete(id);
    }

    public List<Account> findByCustomerId(Long customerId) {
        return accountRepository.findByCustomerId(customerId);
    }
}
