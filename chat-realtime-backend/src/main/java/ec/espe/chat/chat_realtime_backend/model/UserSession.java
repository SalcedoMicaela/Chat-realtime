package ec.espe.chat.chat_realtime_backend.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Table(name = "user_sessions", indexes = {
        @Index(name = "ix_sessions_room_nick_active", columnList = "roomId,nickname,active"),
        @Index(name = "ix_sessions_device_active", columnList = "deviceId,active")
})
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UserSession {

    @Id
    @Column(length = 36)
    private String id;

    @Column(nullable = false, length = 36)
    private String roomId;

    @Column(nullable = false, length = 40)
    private String nickname;

    @Column(nullable = false, length = 64)
    private String deviceId;

    @Column(nullable = true, length = 64)
    private String ip;

    @Column(nullable = true)
    private Instant lastSeen;

    @Column(nullable = false)
    private Boolean active;
}
