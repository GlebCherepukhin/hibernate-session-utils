package com.connecture.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

import static com.connecture.model.TableConstants.USER;

@Data
@Entity
@Table(name = USER)
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  private String login;

  private String password;

  @OneToMany(mappedBy = "creator")
  private List<Post> posts;

  @OneToMany(mappedBy = "author")
  private List<Comment> comments;

  //@OneToMany(mappedBy = "")
  //private List<Permission> permissions;

}
