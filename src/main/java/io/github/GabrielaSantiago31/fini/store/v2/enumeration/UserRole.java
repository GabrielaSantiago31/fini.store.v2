package io.github.GabrielaSantiago31.fini.store.v2.enumeration;

public enum UserRole {
	ADMIN("admin"),
    USER("user");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
