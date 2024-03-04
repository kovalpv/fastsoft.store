package com.fastsoft.store.domain.good;


import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodRepository extends JpaRepository<Good, String> {

  default Good getByCode(String code) {
    return findById(code).orElseThrow(() -> new GoodNotFoundException(code));
  }
}
