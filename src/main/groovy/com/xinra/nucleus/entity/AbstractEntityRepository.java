package com.xinra.nucleus.entity;

import com.google.common.collect.Streams;
import java.util.stream.Collectors;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Supertype of all entity repositories.
 * 
 * @author Erik Hofer
 * 
 * @param <T> the entity type
 */
@NoRepositoryBean
public interface AbstractEntityRepository<T extends BaseEntity> 
    extends PagingAndSortingRepository<T, String> {
  
  /**
   * Retrieves an entity by its PK.
   * 
   * @param pk must not be {@literal null}.
   * @return the entity with the given id or {@literal null} if none found
   * @throws IllegalArgumentException if {@code pk} is {@literal null}
   */
  default T findOne(EntityPk pk) {
    if (pk == null) {
      throw new IllegalArgumentException("PK must not be null!");
    }
    return findOne(pk.getId());
  }
  
  /**
   * Returns whether an entity with the given PK exists.
   * 
   * @param pk must not be {@literal null}.
   * @return true if an entity with the given id exists, {@literal false} otherwise
   * @throws IllegalArgumentException if {@code pk} is {@literal null}
   */
  default boolean exists(EntityPk pk) {
    if (pk == null) {
      throw new IllegalArgumentException("PK must not be null!");
    }
    return exists(pk.getId());
  }
  
  /**
   * Returns all instances of the type with the given PKs.
   * 
   * @param pks must not be {@literal null}.
   * @throws IllegalArgumentException if {@code pks} is {@literal null}
   */
  default Iterable<T> findAllByPk(Iterable<EntityPk> pks) {
    if (pks == null) {
      throw new IllegalArgumentException("PKs must not be null!");
    }
    return findAll(Streams.stream(pks).map(EntityPk::getId).collect(Collectors.toList()));
  }
  
  /**
   * Deletes the entity with the given PK.
   * 
   * @param pk must not be {@literal null}.
   * @throws IllegalArgumentException in case the given {@code pk} is {@literal null}
   */
  default void delete(EntityPk pk) {
    if (pk == null) {
      throw new IllegalArgumentException("PK must not be null!");
    }
    delete(pk.getId());
  }

}
