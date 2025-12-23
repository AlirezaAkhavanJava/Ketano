package com.arcade.ketano.error.dependencies;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserExistMessage {
    private LocalDateTime time;
    private int status;
    private String error;
    private String message;
    private String path;
}
