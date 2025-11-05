package ec.espe.chat.chat_realtime_backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DbHealthController {

    private final JdbcTemplate jdbc;

    @GetMapping("/api/db-health")
    public String health() {
        Integer one = jdbc.queryForObject("SELECT 1", Integer.class);
        return "db-ok:" + one;
    }
}
