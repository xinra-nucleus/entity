package com.xinra.nucleus.entity;

import org.springframework.stereotype.Component;

/**
 * Creates entities by creating an instance of the class.
 * 
 * @author Erik Hofer
 */
@Component
public class DefaultEntityFactory implements EntityFactory {

  @Override
  public <T extends BaseEntity> T createEntity(Class<T> type) {
    try {
      return type.newInstance();
    } catch (Exception ex) {
      throw new RuntimeException("Entity could not be created: " + type.getSimpleName());
    }
  }

}
