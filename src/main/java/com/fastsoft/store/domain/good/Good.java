package com.fastsoft.store.domain.good;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Товар содержит только актуальные данные по товару на текущий момент времени. В системе
 * отсутствует история по стоимости.
 */
@Data
@Entity
@Table(name = "goods")
@NoArgsConstructor
@AllArgsConstructor
public final class Good {

  /**
   * Код товара.
   */
  @Id
  private String code;

  /**
   * Наименование товара.
   */
  @Column(name = "name")
  private String name;

  /**
   * Процент НДС.
   */
  @Column(name = "vat_percent")
  private Double vatPercent;

  /**
   * Стоимость НДС.
   */
  @Column(name = "price")
  private Double price;

}
