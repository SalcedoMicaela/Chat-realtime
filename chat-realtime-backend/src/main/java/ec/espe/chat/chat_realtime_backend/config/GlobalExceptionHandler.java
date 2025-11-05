// src/main/java/ec/espe/chat/chat_realtime_backend/config/GlobalExceptionHandler.java
package ec.espe.chat.chat_realtime_backend.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex) {
        String msg = ex.getBindingResult().getFieldErrors().stream()
                .findFirst().map(err -> err.getField() + ": " + err.getDefaultMessage())
                .orElse("Datos inválidos");
        return ResponseEntity.badRequest().body(Map.of("error", msg));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArg(IllegalArgumentException ex) {
        String m = ex.getMessage() != null ? ex.getMessage() : "Solicitud inválida";
        // mapea mensajes comunes a 409 si son duplicados
        if (m.toLowerCase().contains("existe")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", m));
        }
        return ResponseEntity.badRequest().body(Map.of("error", m));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAny(Exception ex) {
        // Log real en consola
        ex.printStackTrace();
        return ResponseEntity.status(500).body(Map.of("error", "Error interno"));
    }
}
