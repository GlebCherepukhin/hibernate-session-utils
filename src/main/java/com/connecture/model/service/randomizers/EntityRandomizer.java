package com.connecture.model.service.randomizers;

import java.util.stream.Stream;

public interface EntityRandomizer<E> {

  E getOne();

  default Stream<E> getStream(int length) {
    return Stream.generate(this::getOne).limit(length);
  }
}
