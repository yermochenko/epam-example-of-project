package service;

public class AccountNotExistsException extends ServiceException {
    private Long id;

    public AccountNotExistsException(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
