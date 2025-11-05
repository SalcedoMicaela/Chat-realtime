package ec.espe.chat.chat_realtime_backend.dto;

import ec.espe.chat.chat_realtime_backend.model.enums.RoomType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateRoomRequest {
    @NotBlank private String name;
    @NotNull  private RoomType type;

    public String getName() { return name; }
    public RoomType getType() { return type; }

    public void setName(String name) { this.name = name; }
    public void setType(RoomType type) { this.type = type; }
}
