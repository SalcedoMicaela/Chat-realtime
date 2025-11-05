// src/main/java/ec/espe/chat/chat_realtime_backend/service/AccountService.java
package ec.espe.chat.chat_realtime_backend.service;

import ec.espe.chat.chat_realtime_backend.dto.auth.LoginRequest;
import ec.espe.chat.chat_realtime_backend.dto.auth.RegisterRequest;
import ec.espe.chat.chat_realtime_backend.model.Account;

public interface AccountService {
    Account register(RegisterRequest request);        // crea y devuelve la cuenta
    Account authenticate(LoginRequest request);       // valida credenciales
}
