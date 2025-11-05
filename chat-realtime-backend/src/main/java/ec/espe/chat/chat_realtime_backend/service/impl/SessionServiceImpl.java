package ec.espe.chat.chat_realtime_backend.service.impl;

import ec.espe.chat.chat_realtime_backend.dto.JoinRequest;
import ec.espe.chat.chat_realtime_backend.dto.JoinResponse;
import ec.espe.chat.chat_realtime_backend.service.SessionService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SessionServiceImpl implements SessionService {

    private static class SessionInfo {
        String sessionId;
        String roomId;
        String userId;
        String displayName;
        Instant lastPing;
    }

    private final Map<String, SessionInfo> sessions = new ConcurrentHashMap<>();

    @Override
    public JoinResponse join(String roomId, JoinRequest req, String userId) {
        String sid = UUID.randomUUID().toString();
        SessionInfo s = new SessionInfo();
        s.sessionId = sid;
        s.roomId = roomId;
        s.userId = (userId == null || userId.isBlank()) ? "anonymous" : userId;
        s.displayName = req.getDisplayName();
        s.lastPing = Instant.now();
        sessions.put(sid, s);
        return new JoinResponse(sid, roomId, s.userId);
    }

    @Override
    public void ping(String sessionId) {
        SessionInfo s = sessions.get(sessionId);
        if (s != null) s.lastPing = Instant.now();
    }

    @Override
    public void leave(String sessionId) {
        sessions.remove(sessionId);
    }
}
