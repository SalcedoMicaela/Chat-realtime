package ec.espe.chat.chat_realtime_backend.repository;

import ec.espe.chat.chat_realtime_backend.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<ChatMessage, String> {
}
