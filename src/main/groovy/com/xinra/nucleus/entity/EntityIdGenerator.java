package com.xinra.nucleus.entity;

/**
 * Used to generate entity IDs. Implementation is resolved by Spring.
 * 
 * @author Erik Hofer
 */
public interface EntityIdGenerator {
  
  /**
   * Generates an entity ID that should be globally unique.
   * @param entity the entity for which the ID should be generated
   */
  String generateId(BaseEntity entity);

}
