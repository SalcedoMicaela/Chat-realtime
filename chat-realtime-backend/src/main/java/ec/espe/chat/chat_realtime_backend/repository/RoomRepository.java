package ec.espe.chat.chat_realtime_backend.repository;

import ec.espe.chat.chat_realtime_backend.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, String> {
    boolean existsByPinHash(String pinHash);
}

