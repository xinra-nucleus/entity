package com.xinra.nucleus.entity;

import javax.persistence.MappedSuperclass;
import javax.persistence.OptimisticLockException;
import javax.persistence.Version;

/**
 * Superclass for entities that use optimistic locking.
 * 
 * @author Erik Hofer
 */
@MappedSuperclass
public abstract class VersionedEntity extends BaseEntity {
  
  protected static class Pk extends BaseEntity.Pk {
    
    protected int lockVersion;

    protected Pk(String id, int lockVersion) {
      super(id);
      this.lockVersion = lockVersion;
    }
    
  }
  
  @Version
  protected int lockVersion;

  @Override
  public EntityPk getPk() {
    return new Pk(id, lockVersion);
  }
  
  /**
   * Checks if this entity's {@code lockVersion} matches that of a given {@code entityPk}.
   * 
   * @param entityPk the {@code entityPk} to compare with this entity
   * @throws OptimisticLockException 
   *    if the {@code lockVersion} of the entity and {@code entityPk} do not match
   */
  public void checkStaleness(EntityPk entityPk) throws OptimisticLockException {
    if (id == null) {
      throw new IllegalStateException("This entity has not been persisted yet!");
    }
    if (!id.equals(entityPk.getId())) {
      throw new IllegalArgumentException("Id of entity and entityPk do not match!");
    }
    // if (globally unique) IDs match, we can cast safely
    if (lockVersion != ((Pk) entityPk).lockVersion) {
      throw new OptimisticLockException("Lock version of entity and entityPk do not match!", 
          null, this);
    }
  }
}
