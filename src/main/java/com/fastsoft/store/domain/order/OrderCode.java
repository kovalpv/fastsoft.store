package com.fastsoft.store.domain.order;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Transient;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class OrderCode implements Serializable {

  private final static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  private final static String CODE_TEMPLATE = "ORDER::%s::%06d";

  private String code;

  static OrderCode of(LocalDate date, Integer id) {
    return new OrderCode(String.format(CODE_TEMPLATE, date.format(DATE_FORMATTER), id));
  }

  @Transient
  public int getNumber() {
    return Integer.parseInt(code.split("::")[2]);
  }


}
