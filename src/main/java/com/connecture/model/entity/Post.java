package com.connecture.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static com.connecture.model.TableConstants.POST;

@Data
@Entity
@Table(name = POST)
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "creator", referencedColumnName = "id")
  private User creator;

  private String title;

  @Lob
  private String content;

  @Temporal(TemporalType.TIMESTAMP)
  private Date dateCreated;

  @OneToMany(mappedBy = "parentPost")
  private List<Comment> comments;

}
