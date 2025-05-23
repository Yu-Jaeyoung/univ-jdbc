package mobile.banking.account;

import mobile.banking.account.repository.AccountHistoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AccountHistoryService {

    private static final Logger log = LoggerFactory.getLogger(AccountHistoryService.class);

    private final AccountHistoryRepository accountHistoryRepository;

    public AccountHistoryService(final AccountHistoryRepository accountHistoryRepository) {
        this.accountHistoryRepository = accountHistoryRepository;
    }

    public List<AccountHistory> findAllHistories() {
        return accountHistoryRepository.findAll();
    }

    public List<AccountHistory> findHistoriesByAccountId(final Long accountId) {
        return accountHistoryRepository.findByAccountId(accountId);
    }
}
