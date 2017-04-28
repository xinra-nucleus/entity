package com.xinra.nucleus.entity;

import com.xinra.nucleus.common.ApplicationContextProvider;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

/**
 * Superclass for all entities.
 * 
 * @author Erik Hofer
 */
@MappedSuperclass
public abstract class BaseEntity {
  
  protected static class Pk implements EntityPk {
    
    protected String id;
    
    protected Pk(String id) {
      this.id = id;
    }

    @Override
    public String getId() {
      return id;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj == null) {
        return false;
      }
      if (!(obj instanceof Pk)) {
        return false;
      }
      Pk other = (Pk) obj;
      if (id == null) {
        if (other.id != null) {
          return false;
        }
      } else if (!id.equals(other.id)) {
        return false;
      }
      return true;
    }
    
    @Override
    public String toString() {
      return id;
    }
  }
  
  protected BaseEntity() {}

  @Id
  protected String id;
  
  /**
   * Returns the primary key of this entity.
   */
  public EntityPk getPk() {
    return new Pk(id);
  }
  
  @PrePersist
  protected void generateId() {
    if (id == null) {
      id = ApplicationContextProvider.getApplicationContext().getBean(EntityIdGenerator.class)
          .generateId(this);
    }
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof BaseEntity)) {
      return false;
    }
    BaseEntity other = (BaseEntity) obj;
    if (id == null) {
      if (other.id != null) {
        return false;
      }
    } else if (!id.equals(other.id)) {
      return false;
    }
    return true;
  }
  
  @Override
  public String toString() {
    return getClass().getSimpleName() + "[" + id + "]";
  }
}
