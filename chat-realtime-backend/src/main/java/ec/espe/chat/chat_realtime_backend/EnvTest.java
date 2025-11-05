package ec.espe.chat.chat_realtime_backend;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class EnvTest implements CommandLineRunner {
    @Override
    public void run(String... args) {
        Dotenv dotenv = Dotenv.load();
        System.out.println("Cloud Name: " + dotenv.get("CLOUDINARY_CLOUD_NAME"));
    }
}
