package ec.espe.chat.chat_realtime_backend.service.impl;

import ec.espe.chat.chat_realtime_backend.dto.CreateRoomRequest;
import ec.espe.chat.chat_realtime_backend.model.Room;
import ec.espe.chat.chat_realtime_backend.repository.RoomRepository;
import ec.espe.chat.chat_realtime_backend.service.RoomService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) { this.roomRepository = roomRepository; }

    @Override
    public Room createRoom(CreateRoomRequest req, String ownerId) {

        Room r = new Room();
        r.setId(generateUniqueRoomId());

        r.setName(req.getName());
        r.setType(req.getType());
        r.setOwnerId(ownerId);
        r.setCreatedBy(ownerId);

        r.setMaxUploadMb(req.getMaxUploadMb());
        r.setAnonymousUsers(req.getAnonymousUsers());
        r.setActive(true);

        r.setPinHash(generateUniquePin());

        return roomRepository.save(r);
    }

    @Override
    public List<Room> findAll() { return roomRepository.findAll(); }

    @Override
    public Optional<Room> getById(String roomId) { return roomRepository.findById(roomId); }

    @Override
    public void deleteById(String roomId) { roomRepository.deleteById(roomId); }

    private String generateUniqueRoomId() {
        String id;
        do {
            id = "Sala-" + randomAlphaNum(4);
        } while (roomRepository.existsById(id));

        return id;
    }

    private String generateUniquePin() {
        String pin;
        do {
            pin = randomAlphaNum(6); // >= 4
        } while (roomRepository.existsByPinHash(pin));

        return pin;
    }

    private String randomAlphaNum(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int idx = (int) (Math.random() * chars.length());
            sb.append(chars.charAt(idx));
        }
        return sb.toString();
    }



}
