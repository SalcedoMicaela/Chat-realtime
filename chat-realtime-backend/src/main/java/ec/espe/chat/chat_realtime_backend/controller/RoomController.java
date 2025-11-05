package ec.espe.chat.chat_realtime_backend.controller;

import ec.espe.chat.chat_realtime_backend.dto.CreateRoomRequest;
import ec.espe.chat.chat_realtime_backend.dto.CreateRoomResponse;
import ec.espe.chat.chat_realtime_backend.model.Room;
import ec.espe.chat.chat_realtime_backend.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) { this.roomService = roomService; }

    @PostMapping
    public ResponseEntity<CreateRoomResponse> create(@RequestHeader(value = "X-User-Id", required = false) String userId,
                                                     @Valid @RequestBody CreateRoomRequest req) {
        String ownerId = (userId == null || userId.isBlank()) ? "owner" : userId;
        Room room = roomService.createRoom(req, ownerId);
        return ResponseEntity.ok(new CreateRoomResponse(room.getId(), room.getName(), room.getType()));
    }

    @GetMapping
    public ResponseEntity<List<Room>> list() {
        return ResponseEntity.ok(roomService.findAll());
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<Room> get(@PathVariable String roomId) {
        Room room = roomService.getById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("Room no encontrada"));
        return ResponseEntity.ok(room);
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<Void> delete(@PathVariable String roomId) {
        roomService.deleteById(roomId);
        return ResponseEntity.noContent().build();
    }
}
