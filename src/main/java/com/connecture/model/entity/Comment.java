package com.connecture.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static com.connecture.model.TableConstants.COMMENT;

@Data
@Entity
@Table(name = COMMENT)
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "author")
  private User author;

  @Lob
  private String content;

  @ManyToOne
  @JoinColumn(name = "comment_ref", referencedColumnName = "id")
  private Comment parentComment;


  @ManyToOne
  @JoinColumn(name="post_ref", referencedColumnName = "id")
  private Post parentPost;

  @Temporal(TemporalType.TIMESTAMP)
  private Date created;

  @OneToMany(mappedBy = "parentComment")
  private List<Comment> childComments;

}
