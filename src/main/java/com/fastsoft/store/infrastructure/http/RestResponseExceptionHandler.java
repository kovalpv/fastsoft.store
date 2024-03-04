package com.fastsoft.store.infrastructure.http;

import com.fastsoft.store.domain.error.NotFoundException;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {NotFoundException.class})
  protected ResponseEntity<Object> handleNotFound(NotFoundException exception, WebRequest request) {

    return handleException(exception, request, HttpStatus.NOT_FOUND);
  }

  private ResponseEntity<Object> handleException(Exception exception, WebRequest request,
      HttpStatus status) {

    ErrorMessage body =
        new ErrorMessage(
            LocalDateTime.now(), status, exception.getMessage(), request.getContextPath());

    return handleExceptionInternal(exception, body, new HttpHeaders(), status, request);
  }

}
