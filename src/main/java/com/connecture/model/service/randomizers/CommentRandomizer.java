package com.connecture.model.service.randomizers;

import com.connecture.model.entity.Comment;
import com.connecture.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.StringUtils.capitalize;

@Component
public class CommentRandomizer implements EntityRandomizer<Comment> {

  @Autowired
  UserRandomizer userRandomizer;

  @Override
  public Comment getOne() {
    return createComment(userRandomizer.getOne());
  }

  public Comment getOne(User author) {
    return createComment(author);
  }

  private Comment createComment(User author) {
    Comment comment = new Comment();
    comment.setAuthor(author);
    comment.setContent(capitalize(randomAlphabetic(20).toUpperCase()));
    comment.setCreated(new Date());
    return comment;
  }
}
