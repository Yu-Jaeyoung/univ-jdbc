package mobile.banking.account.repository;

import mobile.banking.account.AccountHistory;

import java.util.List;

public interface AccountHistoryRepository {
    void save(final AccountHistory accountHistory);

    List<AccountHistory> findAll();

    List<AccountHistory> findByAccountId(final Long accountId);
}
