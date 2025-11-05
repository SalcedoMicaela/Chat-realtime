// src/main/java/ec/espe/chat/chat_realtime_backend/controller/AuthController.java
package ec.espe.chat.chat_realtime_backend.controller;

import ec.espe.chat.chat_realtime_backend.dto.auth.LoginRequest;
import ec.espe.chat.chat_realtime_backend.dto.auth.LoginResponse;
import ec.espe.chat.chat_realtime_backend.dto.auth.RegisterRequest;
import ec.espe.chat.chat_realtime_backend.model.Account;
import ec.espe.chat.chat_realtime_backend.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AccountService accountService;

    public AuthController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        Account acc = accountService.register(request);
        // En producción, NO devuelvas el passwordHash
        return ResponseEntity.status(201).body(new LoginResponse(
                null, acc.getId(), acc.getUsername(), acc.getEmail(), acc.getRoles()
        ));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        Account acc = accountService.authenticate(request);
        // Aquí podrías generar un JWT real; por ahora un placeholder
        String fakeToken = "tok_" + acc.getId();
        return ResponseEntity.ok(new LoginResponse(
                fakeToken, acc.getId(), acc.getUsername(), acc.getEmail(), acc.getRoles()
        ));
    }
}
