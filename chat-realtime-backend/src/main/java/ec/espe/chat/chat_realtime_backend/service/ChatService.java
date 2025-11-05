package ec.espe.chat.chat_realtime_backend.service;

import ec.espe.chat.chat_realtime_backend.model.ChatMessage;

public interface ChatService {
    ChatMessage persist(ChatMessage message);
}
