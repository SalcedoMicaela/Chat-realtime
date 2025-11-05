package ec.espe.chat.chat_realtime_backend.model;

import ec.espe.chat.chat_realtime_backend.model.enums.RoomType;
import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @Column(length = 36, nullable = false, updatable = false)
    private String id;

    @Column(length = 120, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private RoomType type;

    @Column(name = "owner_id", length = 36, nullable = false)
    private String ownerId;

    @Column(nullable = false)
    private Boolean active = true; // valor por defecto

    @Column(name = "created_at", nullable = false)
    private Instant createdAt = Instant.now();

    @Column(name = "created_by", length = 36, nullable = false)
    private String createdBy;
    @Column(name = "max_upload_mb", nullable = false)
    private Integer maxUploadMb;

    // Constructor vacío para JPA
    public Room() {
        this.id = UUID.randomUUID().toString();
        this.active = true;
        this.createdAt = Instant.now();
    }

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public RoomType getType() { return type; }
    public void setType(RoomType type) { this.type = type; }

    public String getOwnerId() { return ownerId; }
    public void setOwnerId(String ownerId) { this.ownerId = ownerId; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }

    public Integer getMaxUploadMb() {
        return maxUploadMb;
    }

    public void setMaxUploadMb(Integer maxUploadMb) {
        this.maxUploadMb = maxUploadMb;
    }
}
