package com.fastsoft.store.domain.good;

import com.fastsoft.store.domain.error.NotFoundException;

/**
 * Ошибка сообщающая о том, что товар не найдена.
 */
public class GoodNotFoundException extends NotFoundException {

  private static final String MESSAGE = "Товар %s не найден.";

  public GoodNotFoundException(String code) {
    super(String.format(MESSAGE, code));
  }
}
