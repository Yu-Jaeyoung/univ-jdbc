package mobile.banking.account.repository.jdbctemplate;

import mobile.banking.account.AccountHistory;
import mobile.banking.account.repository.AccountHistoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;

public class JdbcTemplateAccountHistoryRepository implements AccountHistoryRepository {

    private static final Logger log = LoggerFactory.getLogger(JdbcTemplateAccountHistoryRepository.class);

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateAccountHistoryRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(final AccountHistory accountHistory) {
        String sql = "INSERT INTO account_history (account_id, transaction_type, amount, balance) " +
                "VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                accountHistory.getAccountId(), accountHistory.getTransactionType().name(),
                accountHistory.getAmount(), accountHistory.getBalance());
    }

    @Override
    public List<AccountHistory> findAll() {
        String sql = "SELECT * FROM account_history ORDER BY transaction_time DESC";
        return jdbcTemplate.query(sql, accountHistoryRowMapper);
    }

    @Override
    public List<AccountHistory> findByAccountId(final Long accountId) {
        String sql = "SELECT * FROM account_history WHERE account_id = ? ORDER BY transaction_time DESC";
        return jdbcTemplate.query(sql, accountHistoryRowMapper, accountId);
    }

    private final RowMapper<AccountHistory> accountHistoryRowMapper = (rs, rowNum) -> new AccountHistory(
            rs.getLong("account_id"),
            rs.getTimestamp("transaction_time").toLocalDateTime(),
            AccountHistory.TransactionType.valueOf(rs.getString("transaction_type")),
            rs.getBigDecimal("amount"),
            rs.getBigDecimal("balance")
    );
}


