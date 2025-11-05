package ec.espe.chat.chat_realtime_backend.service.impl;

import ec.espe.chat.chat_realtime_backend.dto.auth.LoginRequest;
import ec.espe.chat.chat_realtime_backend.dto.auth.RegisterRequest;
import ec.espe.chat.chat_realtime_backend.model.Account;
import ec.espe.chat.chat_realtime_backend.repository.AccountRepository;
import ec.espe.chat.chat_realtime_backend.service.AccountService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repo;
    private final PasswordEncoder encoder;

    public AccountServiceImpl(AccountRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    @Override
    @Transactional
    public Account register(RegisterRequest req) {

        // Buscar en la base de datos (sin usar existsByXXX)
        if (repo.findByUsernameIgnoreCase(req.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username ya existe");
        }

        if (repo.findByEmailIgnoreCase(req.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email ya existe");
        }

        // Crear la cuenta
        Account a = new Account();
        //a.setId(UUID.randomUUID().toString());
        a.setUsername(req.getUsername());
        a.setEmail(req.getEmail());
        a.setPasswordHash(encoder.encode(req.getPassword()));
        a.setRoles("USER");
        a.setActive(true);
        a.setCreatedAt(Instant.now());

        try {
            return repo.saveAndFlush(a); // usar saveAndFlush para asegurar que se escriba inmediatamente
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Username o email ya existe");
        }
    }


    @Override
    @Transactional(readOnly = true)
    public Account authenticate(LoginRequest req) {
        // Buscar por username o email de manera insensible a mayúsculas/minúsculas
        Account a = repo.findByUsernameIgnoreCase(req.getUsernameOrEmail())
                .or(() -> repo.findByEmailIgnoreCase(req.getUsernameOrEmail()))
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        if (!encoder.matches(req.getPassword(), a.getPasswordHash())) {
            throw new IllegalArgumentException("Credenciales inválidas");
        }

        return a;
    }
}
