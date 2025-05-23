package mobile.banking.account;

import java.math.BigDecimal;

public class TransferRequest {
    private Long toAccountId;
    private BigDecimal amount;

    public TransferRequest() {
    }

    public TransferRequest(final Long toAccountId, final BigDecimal amount) {
        this.toAccountId = toAccountId;
        this.amount = amount;
    }

    public Long getToAccountId() {
        return toAccountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
