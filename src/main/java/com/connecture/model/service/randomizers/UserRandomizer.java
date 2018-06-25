package com.connecture.model.service.randomizers;

import com.connecture.model.entity.User;
import org.springframework.stereotype.Component;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.apache.commons.lang3.StringUtils.capitalize;


@Component
public class UserRandomizer implements EntityRandomizer<User> {

  @Override
  public User getOne() {
    User user = new User();
    user.setFirstName(capitalize(randomAlphabetic(10).toUpperCase()));
    user.setLastName(capitalize(randomAlphabetic(10).toUpperCase()));
    user.setLogin(capitalize(randomAlphabetic(10).toUpperCase()));
    user.setPassword(capitalize(randomAlphanumeric(6).toUpperCase()));
    return user;
  }

}
