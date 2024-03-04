package com.fastsoft.store.domain.order;

import com.fastsoft.store.domain.good.Good;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Запись товара.
 */
@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

  /**
   * Код записи заказа.
   */
  private String code;

  /**
   * Код Товара.
   */
  @Column(name = "good_code")
  private String goodCode;

  /**
   * Наименования товара.
   */
  @Column(name = "name")
  private String name;

  /**
   * Цена товара.
   */
  @Column(name = "price")
  private Double price;

  /**
   * Количество товаров в заказе.
   */
  @Column(name = "cnt")
  private Integer count;

  /**
   * Процент НДС.
   */
  @Column(name = "vat_percent")
  private Double vatPercent;

  /**
   * НДС.
   */
  @Column(name = "vat")
  private Double vat;

  /**
   * Общая стоимость записи заказа.
   *
   * @return Возвращает Цена товара x Количество товаров в заказе.
   */
  @Transient
  public Double getAmount() {
    return price * count;
  }

  public OrderItem(String code, Good good, Integer count) {
    this.code = code;
    this.goodCode = good.getCode();
    this.name = good.getName();
    this.price = good.getPrice();
    this.count = count;
    this.vatPercent = good.getVatPercent();
    this.vat = good.getVatPercent() * count * good.getPrice() / 100;
  }

}
