package mobile.banking.account;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AccountHistory {
    private Long accountId;
    private LocalDateTime transactionTime;
    private TransactionType transactionType;
    private BigDecimal amount;
    private BigDecimal balance;

    public enum TransactionType {
        DEPOSIT, WITHDRAW
    }

    public AccountHistory() {
    }

    public AccountHistory(final Long accountId, final LocalDateTime transactionTime,
                          final TransactionType transactionType, final BigDecimal amount, final BigDecimal balance) {
        this.accountId = accountId;
        this.transactionTime = transactionTime;
        this.transactionType = transactionType;
        this.amount = amount;
        this.balance = balance;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(LocalDateTime transactionTime) {
        this.transactionTime = transactionTime;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "AccountHistory{" +
                ", accountId=" + accountId +
                ", transactionTime=" + transactionTime +
                ", transactionType='" + transactionType + '\'' +
                ", amount=" + amount +
                ", balance=" + balance +
                '}';
    }
}

