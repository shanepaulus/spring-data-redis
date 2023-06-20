package com.shanepaulus.repo;

import com.shanepaulus.domain.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Shane Paulus
 * <p>
 * Date Created : 23-May-2023.
 */

@Repository
@CacheConfig(cacheNames = "users")
public interface UserRepo extends JpaRepository<User, Integer> {

}
