package service;

import domain.Account;

public class AccountAmountNotEnoughException extends ServiceException {
    private Account account;
    private Long amount;

    public AccountAmountNotEnoughException(Account account, Long amount) {
        this.account = account;
        this.amount = amount;
    }

    public Account getAccount() {
        return account;
    }

    public Long getAmount() {
        return amount;
    }
}
