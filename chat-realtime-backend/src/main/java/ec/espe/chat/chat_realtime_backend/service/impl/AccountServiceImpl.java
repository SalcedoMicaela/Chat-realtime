package ec.espe.chat.chat_realtime_backend.service.impl;

import ec.espe.chat.chat_realtime_backend.model.Account;
import ec.espe.chat.chat_realtime_backend.repository.AccountRepository;
import ec.espe.chat.chat_realtime_backend.service.AccountService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public AccountServiceImpl(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Account register(String username, String email, String password) {
        if (accountRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("El usuario ya existe");
        }
        if (accountRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("El email ya está registrado");
        }
        Account a = new Account();
        a.setId(UUID.randomUUID().toString());
        a.setUsername(username);
        a.setEmail(email);
        a.setPasswordHash(passwordEncoder.encode(password));
        return accountRepository.save(a);
    }

    @Override
    public Account authenticate(String username, String password) {
        Account a = accountRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no existe"));
        if (!passwordEncoder.matches(password, a.getPasswordHash())) {
            throw new IllegalArgumentException("Credenciales inválidas");
        }
        return a;
    }
}
