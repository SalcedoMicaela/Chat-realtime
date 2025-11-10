package ec.espe.chat.chat_realtime_backend.dto;

import ec.espe.chat.chat_realtime_backend.model.enums.RoomType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;

public class CreateRoomRequest {

    @NotBlank
    private String name;

    @NotNull
    private RoomType type;

    @NotNull
    @Min(1)
    private Integer maxUploadMb;

    @NotNull
    @Min(1)
    private Integer anonymousUsers;

    // Getters
    public String getName() { return name; }
    public RoomType getType() { return type; }
    public Integer getMaxUploadMb() { return maxUploadMb; }
    public Integer getAnonymousUsers() { return anonymousUsers; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setType(RoomType type) { this.type = type; }
    public void setMaxUploadMb(Integer maxUploadMb) { this.maxUploadMb = maxUploadMb; }
    public void setAnonymousUsers(Integer anonymousUsers) { this.anonymousUsers = anonymousUsers; }
}
