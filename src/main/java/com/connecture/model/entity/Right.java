package com.connecture.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import static com.connecture.model.TableConstants.RIGHT;

@Data
@Entity
@Table(name = RIGHT)
public class Right {

  @Id
  private String id;

  private String description ;

}
