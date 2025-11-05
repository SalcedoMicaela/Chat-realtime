package ec.espe.chat.chat_realtime_backend.repository;

import ec.espe.chat.chat_realtime_backend.model.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SessionRepository extends JpaRepository<UserSession, String> {
    boolean existsByRoomIdAndNicknameAndActiveIsTrue(String roomId, String nickname);
    boolean existsByDeviceIdAndActiveIsTrue(String deviceId);
    Optional<UserSession> findFirstById(String id);
    List<UserSession> findAllByActiveTrue();
}
