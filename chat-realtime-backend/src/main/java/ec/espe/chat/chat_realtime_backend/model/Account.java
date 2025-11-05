package ec.espe.chat.chat_realtime_backend.model;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;

import java.time.Instant;

@Entity
@Table(name = "accounts",
        uniqueConstraints = {
                @UniqueConstraint(name="ux_accounts_username", columnNames = "username"),
                @UniqueConstraint(name="ux_accounts_email", columnNames = "email")
        })
@DynamicUpdate
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // ← deja el id en null hasta persist
    @Column(length = 36)
    private String id;

    @Column(nullable = false, length = 32)
    private String username;

    @Column(nullable = false, length = 128)
    private String email;

    @Column(nullable = false, length = 255)
    private String passwordHash;

    @Column(nullable = false)
    private boolean active = true;

    @Column(nullable = false)
    private Instant createdAt = Instant.now();


    @Column(nullable = false, length = 64)
    private String roles = "USER";

    @Version // ← habilita control de versión para updates (no afecta inserts)
    private Long version;

    // getters/setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; } // NO la uses al crear
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public String getRoles() { return roles; }
    public void setRoles(String roles) { this.roles = roles; }
    public Long getVersion() { return version; }
    public void setVersion(Long version) { this.version = version; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

}
