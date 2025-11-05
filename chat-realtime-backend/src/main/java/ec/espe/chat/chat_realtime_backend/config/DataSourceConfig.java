package ec.espe.chat.chat_realtime_backend.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.boot.jdbc.DataSourceBuilder;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource() {
        Dotenv env = Dotenv.load();
        String host = env.get("SUPABASE_DB_HOST");
        String port = env.get("SUPABASE_DB_PORT", "5432");
        String db   = env.get("SUPABASE_DB_NAME", "postgres");
        String user = env.get("SUPABASE_DB_USER");
        String pass = env.get("SUPABASE_DB_PASSWORD");

        // SSL requerido por Supabase
        String url = String.format(
                "jdbc:postgresql://%s:%s/%s?sslmode=require",
                host, port, db
        );

        return DataSourceBuilder.create()
                .url(url)
                .username(user)
                .password(pass)
                .driverClassName("org.postgresql.Driver")
                .build();
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource ds) {
        return new JdbcTemplate(ds);
    }
}
