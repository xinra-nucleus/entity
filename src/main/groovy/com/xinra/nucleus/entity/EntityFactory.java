package com.xinra.nucleus.entity;

public interface EntityFactory {
  
  /**
   * Creates an entity instance of the desired type.
   */
  <T extends BaseEntity> T createEntity(Class<T> type);

}
