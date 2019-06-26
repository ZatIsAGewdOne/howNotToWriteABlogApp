package blogApp.security;

public enum Role {
    USER("USER"), ADMIN("ADMIN");

    private String value;

    Role(String user) {
       this.value = user;
    }

    public String getValue() {
        return value;
    }
}
