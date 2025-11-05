package ec.espe.chat.chat_realtime_backend.service;

import ec.espe.chat.chat_realtime_backend.dto.JoinRequest;
import ec.espe.chat.chat_realtime_backend.dto.JoinResponse;

public interface SessionService {
    JoinResponse join(String roomId, JoinRequest req, String userId);
    void ping(String sessionId);
    void leave(String sessionId);
}
