package ec.espe.chat.chat_realtime_backend.repository;

import ec.espe.chat.chat_realtime_backend.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    Optional<Account> findByUsername(String username);
    Optional<Account> findByEmail(String email);

    Optional<Account> findByUsernameIgnoreCase(String username);
    Optional<Account> findByEmailIgnoreCase(String email);


    default Optional<Account> findByUsernameOrEmail(String uOrE) {
        return findByUsernameIgnoreCase(uOrE).or(() -> findByEmailIgnoreCase(uOrE));
    }
}
