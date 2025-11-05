package ec.espe.chat.chat_realtime_backend.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Table(name = "accounts", indexes = {
        @Index(name = "ux_accounts_username", columnList = "username", unique = true),
        @Index(name = "ux_accounts_email", columnList = "email", unique = true)
})
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Account {

    @Id
    @Column(length = 36)
    private String id;

    @Column(nullable = false, length = 40, unique = true)
    private String username;

    @Column(nullable = false, length = 120, unique = true)
    private String email;

    @Column(nullable = false, length = 120)
    private String passwordHash;

    @Column(nullable = false)
    private Boolean active;

    @Column(nullable = false)
    private Instant createdAt;
}
