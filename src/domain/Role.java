package domain;

public enum Role {
    CASHIER("role.cashier"),
    MANAGER("role.manager"),
    ADMINISTRATOR("role.admin");

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
