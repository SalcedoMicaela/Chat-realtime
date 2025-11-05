package ec.espe.chat.chat_realtime_backend.service.impl;

import ec.espe.chat.chat_realtime_backend.model.ChatMessage;
import ec.espe.chat.chat_realtime_backend.repository.MessageRepository;
import ec.espe.chat.chat_realtime_backend.service.ChatService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class ChatServiceImpl implements ChatService {

    private final MessageRepository chatMessageRepository;

    public ChatServiceImpl(MessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    @Override
    public ChatMessage persist(ChatMessage message) {
        // Asegúrate que ChatMessage tenga setters: id, createdAt, etc.
        if (message.getId() == null || message.getId().isBlank()) {
            message.setId(UUID.randomUUID().toString());
        }
        if (message.getCreatedAt() == null) {
            message.setCreatedAt(Instant.now());
        }
        return chatMessageRepository.save(message);
    }
}
