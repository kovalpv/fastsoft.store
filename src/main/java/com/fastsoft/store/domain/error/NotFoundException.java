package com.fastsoft.store.domain.error;

/**
 * Ошибка сообщающая о том, что сущность не найдена.
 */
public class NotFoundException extends RuntimeException {

  public NotFoundException(String message) {
    super(message);
  }
}