package ec.espe.chat.chat_realtime_backend.controller;

import ec.espe.chat.chat_realtime_backend.dto.JoinRequest;
import ec.espe.chat.chat_realtime_backend.dto.JoinResponse;
import ec.espe.chat.chat_realtime_backend.service.SessionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sessions")
public class SessionController {

    private final SessionService sessionService;

    public SessionController(SessionService sessionService) { this.sessionService = sessionService; }

    @PostMapping("/{roomId}/join")
    public ResponseEntity<JoinResponse> join(@PathVariable String roomId,
                                             @RequestHeader(value = "X-User-Id", required = false) String userId,
                                             @Valid @RequestBody JoinRequest request) {
        JoinResponse res = sessionService.join(roomId, request, userId);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/{sessionId}/ping")
    public ResponseEntity<Void> ping(@PathVariable String sessionId) {
        sessionService.ping(sessionId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{sessionId}")
    public ResponseEntity<Void> leave(@PathVariable String sessionId) {
        sessionService.leave(sessionId);
        return ResponseEntity.noContent().build();
    }
}
