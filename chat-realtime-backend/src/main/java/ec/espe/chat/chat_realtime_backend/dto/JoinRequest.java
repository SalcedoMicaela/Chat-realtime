package ec.espe.chat.chat_realtime_backend.dto;

import jakarta.validation.constraints.NotBlank;

public class JoinRequest {
    @NotBlank private String displayName;

    public String getDisplayName() { return displayName; }
    public void setDisplayName(String displayName) { this.displayName = displayName; }
}
