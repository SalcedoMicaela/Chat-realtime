package ec.espe.chat.chat_realtime_backend.service;

import ec.espe.chat.chat_realtime_backend.dto.CreateRoomRequest;
import ec.espe.chat.chat_realtime_backend.model.Room;

import java.util.List;
import java.util.Optional;

public interface RoomService {
    Room createRoom(CreateRoomRequest req, String ownerId);
    List<Room> findAll();
    Optional<Room> getById(String roomId);
    void deleteById(String roomId);
}
