package ec.espe.chat.chat_realtime_backend.controller;

import ec.espe.chat.chat_realtime_backend.model.ChatMessage;
import ec.espe.chat.chat_realtime_backend.service.ChatService;
import jakarta.validation.Valid;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatWsController {

    private final ChatService chatService;
    private final SimpMessagingTemplate messagingTemplate;

    public ChatWsController(ChatService chatService, SimpMessagingTemplate messagingTemplate) {
        this.chatService = chatService;
        this.messagingTemplate = messagingTemplate;
    }

    /**
     * Cliente envía a:  /app/chat.send
     * Se emite en:      /topic/rooms/{roomId}
     */
    @MessageMapping("/chat.send")
    public void handleIncoming(@Valid ChatMessage incoming) {
        ChatMessage saved = chatService.persist(incoming);
        String destination = "/topic/rooms/" + saved.getRoomId();
        messagingTemplate.convertAndSend(destination, saved);
    }
}
