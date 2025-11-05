package ec.espe.chat.chat_realtime_backend.dto;

public class JoinResponse {
    private String sessionId;
    private String roomId;
    private String userId;

    public JoinResponse(String sessionId, String roomId, String userId) {
        this.sessionId = sessionId; this.roomId = roomId; this.userId = userId;
    }

    public String getSessionId() { return sessionId; }
    public String getRoomId() { return roomId; }
    public String getUserId() { return userId; }
}
