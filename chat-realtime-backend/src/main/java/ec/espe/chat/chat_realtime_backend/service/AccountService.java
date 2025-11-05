package ec.espe.chat.chat_realtime_backend.service;

import ec.espe.chat.chat_realtime_backend.model.Account;

public interface AccountService {
    Account register(String username, String email, String password);
    Account authenticate(String username, String password);
}
