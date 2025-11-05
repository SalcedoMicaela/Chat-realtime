package ec.espe.chat.chat_realtime_backend.controller;

import ec.espe.chat.chat_realtime_backend.dto.auth.LoginRequest;
import ec.espe.chat.chat_realtime_backend.dto.auth.LoginResponse;
import ec.espe.chat.chat_realtime_backend.dto.auth.RegisterRequest;
import ec.espe.chat.chat_realtime_backend.model.Account;
import ec.espe.chat.chat_realtime_backend.service.AccountService;
import ec.espe.chat.chat_realtime_backend.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AccountService accountService;
    private final JwtUtil jwtUtil;

    public AuthController(AccountService accountService, JwtUtil jwtUtil) {
        this.accountService = accountService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<LoginResponse> register(@Valid @RequestBody RegisterRequest request) {
        Account acc = accountService.register(request.getUsername(), request.getEmail(), request.getPassword());
        String token = jwtUtil.generateToken(acc.getId(), acc.getUsername());
        return ResponseEntity.ok(new LoginResponse(token, acc.getId(), acc.getUsername(), acc.getEmail()));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        Account acc = accountService.authenticate(request.getUsername(), request.getPassword());
        String token = jwtUtil.generateToken(acc.getId(), acc.getUsername());
        return ResponseEntity.ok(new LoginResponse(token, acc.getId(), acc.getUsername(), acc.getEmail()));
    }
}
