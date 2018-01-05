package domain;

import java.util.List;

public class Account extends Entity {
    private String client;
    private Long balance;
    private List<Transfer> history;

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public List<Transfer> getHistory() {
        return history;
    }

    public void setHistory(List<Transfer> history) {
        this.history = history;
    }
}
