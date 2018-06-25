package com.connecture.model.service.randomizers;

import com.connecture.model.entity.IndependentObject1;
import org.springframework.stereotype.Component;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.StringUtils.capitalize;

@Component
public class IndependentObject1Randomizer implements EntityRandomizer<IndependentObject1> {
  @Override
  public IndependentObject1 getOne() {
    IndependentObject1 object1 = new IndependentObject1();
    object1.setColumn1(capitalize(randomAlphabetic(10).toUpperCase()));
    object1.setColumn2(10);
    return object1;
  }
}
