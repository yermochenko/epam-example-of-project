package domain;

public enum Role {
    CASHIER("Кассир"),
    MANAGER("Менеджер"),
    ADMINISTRATOR("Администратор");

    private String name;

    private Role(String name) {
        this.name = name;
    }

    public Long getId() {
        return Long.valueOf(ordinal());
    }

    public String getName() {
        return name;
    }
}
