package com.shanepaulus.repo;

import com.shanepaulus.domain.User;
import java.util.Optional;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Shane Paulus
 * <p>
 * Date Created : 23-May-2023.
 */

@Repository
@CacheConfig(cacheNames = "users")
public interface UserRepo extends CrudRepository<User, Integer> {

  @Cacheable(key = "#id")
  Optional<User> findById(Integer id);

  @CachePut(key = "#user.id")
  <S extends User> S save(S user);

}
