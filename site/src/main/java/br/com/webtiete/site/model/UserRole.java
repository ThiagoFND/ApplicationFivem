package br.com.webtiete.site.model;

public enum UserRole {
    USER("user"),
	ADMIN("admin"),
	POLICIAL("policial"),
	INSTRUTOR("instrutor");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
