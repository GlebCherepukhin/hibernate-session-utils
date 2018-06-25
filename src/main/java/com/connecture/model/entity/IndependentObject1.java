package com.connecture.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "INDEPENDENT_OBJECT1")
public class IndependentObject1 {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private int id;
  private String column1;
  private int column2;
}
