package com.fastsoft.store.application.good.query;

import com.fastsoft.store.application.good.GoodDto;
import com.fastsoft.store.domain.good.Good;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GoodMapper {

  GoodMapper MAPPER = Mappers.getMapper(GoodMapper.class);

  List<GoodDto> asGoods(List<Good> goods);
}
