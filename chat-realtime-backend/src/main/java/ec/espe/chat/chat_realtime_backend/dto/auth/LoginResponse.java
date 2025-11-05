// src/main/java/ec/espe/chat/chat_realtime_backend/dto/auth/LoginResponse.java
package ec.espe.chat.chat_realtime_backend.dto.auth;

public class LoginResponse {
    private String token;
    private String accountId;
    private String username;
    private String email;
    private String roles;

    public LoginResponse() {}
    public LoginResponse(String token, String accountId, String username, String email, String roles) {
        this.token = token; this.accountId = accountId; this.username = username; this.email = email; this.roles = roles;
    }
    // getters/setters
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public String getAccountId() { return accountId; }
    public void setAccountId(String accountId) { this.accountId = accountId; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getRoles() { return roles; }
    public void setRoles(String roles) { this.roles = roles; }
}
