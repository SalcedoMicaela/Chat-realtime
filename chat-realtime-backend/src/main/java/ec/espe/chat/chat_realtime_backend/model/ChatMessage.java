package ec.espe.chat.chat_realtime_backend.model;

import ec.espe.chat.chat_realtime_backend.model.enums.MessageType;
import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Table(name = "chat_messages", indexes = {
        @Index(name = "ix_msgs_room_ts", columnList = "roomId,ts")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessage {

    @Id
    @Column(length = 36)
    private String id;

    @Column(nullable = false, length = 36)
    private String roomId;

    @Column(nullable = false, length = 40)
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 8)
    private MessageType type;

    @Column(columnDefinition = "text")
    private String content;       // para TEXT

    @Column(length = 500)
    private String fileUrl;       // para FILE

    @Column(nullable = false)
    private Instant ts;

    // Getter y Setter personalizados para ts si deseas renombrarlos
    public Instant getCreatedAt() {
        return ts;
    }

    public void setCreatedAt(Instant createdAt) {
        this.ts = createdAt;
    }
}
