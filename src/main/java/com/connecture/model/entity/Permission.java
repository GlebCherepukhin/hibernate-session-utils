package com.connecture.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static com.connecture.model.TableConstants.PERMISSION;

@Data
@Entity
@Table(name = PERMISSION)
public class Permission {

  @EmbeddedId
  private PermissionPK id;

  @Temporal(TemporalType.TIMESTAMP)
  private Date expires;

  @Data
  @Embeddable
  private static class PermissionPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "right_id", referencedColumnName = "id")
    private Right right;
  }
}
