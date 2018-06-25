package com.connecture.model.service.randomizers;

import com.connecture.model.entity.Post;
import com.connecture.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.sun.xml.internal.ws.util.StringUtils.capitalize;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

@Component
public class PostRandomizer implements EntityRandomizer<Post> {

  @Autowired
  UserRandomizer userRandomizer;

  @Override
  public Post getOne() {
    return createPost(userRandomizer.getOne());
  }

  public Post getOne(User creator) {
    return createPost(creator);
  }

  private Post createPost(User creator) {
    Post post = new Post();
    post.setTitle(capitalize(randomAlphabetic(10).toUpperCase()));
    post.setContent(capitalize(randomAlphabetic(40).toUpperCase()));
    post.setDateCreated(new Date());
    post.setCreator(creator);
    return post;
  }
}
