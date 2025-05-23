package mobile.banking.account;

import mobile.banking.account.repository.AccountHistoryRepository;
import mobile.banking.account.repository.AccountRepository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountHistoryRepository accountHistoryRepository;

    public AccountService(AccountRepository accountRepository, AccountHistoryRepository accountHistoryRepository) {
        this.accountRepository = accountRepository;
        this.accountHistoryRepository = accountHistoryRepository;
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

    @Transactional
    public void transfer(Long fromAccountId, Long toAccountId, BigDecimal amount) {

        Account fromAccount = accountRepository.findById(fromAccountId).orElse(null);

        if (fromAccount == null) {
            return;
        }

        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));

        Account toAccount = accountRepository.findById(toAccountId).orElse(null);

        if (toAccount == null) {
            return;
        }

        toAccount.setBalance(toAccount.getBalance().add(amount));

        accountRepository.update(fromAccount);

        AccountHistory fromAccountHistory = new AccountHistory(
                fromAccount.getId(),
                LocalDateTime.now(),
                AccountHistory.TransactionType.WITHDRAW,
                amount,
                fromAccount.getBalance()
        );
        accountHistoryRepository.save(fromAccountHistory);


        // if (true) throw new RuntimeException("Error");

        accountRepository.update(toAccount);

        AccountHistory toAccountHistory = new AccountHistory(
                toAccount.getId(),
                LocalDateTime.now(),
                AccountHistory.TransactionType.DEPOSIT,
                amount,
                toAccount.getBalance()
        );

        accountHistoryRepository.save(toAccountHistory);

    }

    @Transactional
    public void deposit(Long id, BigDecimal amount) {
        Account account = accountRepository.findById(id).orElse(null);

        if (account == null) {
            return;
        }

        account.setBalance(account.getBalance().add(amount));
        accountRepository.update(account);

        AccountHistory accountHistory = new AccountHistory(
                account.getId(),
                LocalDateTime.now(),
                AccountHistory.TransactionType.DEPOSIT,
                amount,
                account.getBalance()
        );

        accountHistoryRepository.save(accountHistory);
    }

    @Transactional
    public void withdraw(Long id, BigDecimal amount) {
        Account account = accountRepository.findById(id).orElse(null);

        if (account == null) {
            return;
        }

        account.setBalance(account.getBalance().subtract(amount));
        accountRepository.update(account);

        AccountHistory accountHistory = new AccountHistory(
                account.getId(),
                LocalDateTime.now(),
                AccountHistory.TransactionType.WITHDRAW,
                amount,
                account.getBalance()
        );

        accountHistoryRepository.save(accountHistory);
    }


}
