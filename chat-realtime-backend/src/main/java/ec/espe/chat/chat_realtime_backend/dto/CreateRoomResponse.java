package ec.espe.chat.chat_realtime_backend.dto;

import ec.espe.chat.chat_realtime_backend.model.enums.RoomType;

public class CreateRoomResponse {

    private String id;
    private String name;
    private RoomType type;
    private String pin;

    public CreateRoomResponse(String id, String name, RoomType type, String pin) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.pin = pin;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public RoomType getType() { return type; }
    public String getPin() { return pin; }
}
