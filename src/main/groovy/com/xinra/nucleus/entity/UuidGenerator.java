package com.xinra.nucleus.entity;

import java.util.UUID;
import org.springframework.stereotype.Component;

/**
 * Generates a random UUIDs to be used as entity IDs.
 * 
 * @author Erik Hofer
 */
@Component
public class UuidGenerator implements EntityIdGenerator {

  @Override
  public String generateId(BaseEntity entity) {
    return UUID.randomUUID().toString();
  }

}
