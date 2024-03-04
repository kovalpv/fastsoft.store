package com.fastsoft.store.infrastructure.http;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;

public record ErrorMessage(
    LocalDateTime timestamp,
    HttpStatus status,
    String message,
    String path) {

}
