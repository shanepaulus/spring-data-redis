package com.shanepaulus.service.impl;

import com.shanepaulus.domain.User;
import com.shanepaulus.mapper.UserMapper;
import com.shanepaulus.model.UserDto;
import com.shanepaulus.repo.UserRepo;
import com.shanepaulus.service.UserService;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author Shane Paulus
 * <p>
 * Date Created : 23-May-2023.
 */

@Service
@AllArgsConstructor
@Slf4j
@CacheConfig(cacheNames = "users")
public class UserServiceImpl implements UserService {

  private final UserRepo userRepo;

  @Override
  @Cacheable(value = "users", keyGenerator = "customKeyGenerator")
  public List<User> findAll() {
    List<User> userList = new ArrayList<>();

    for (User user : userRepo.findAll()) {
      userList.add(user);
    }

    return userList;
  }

  @Override
  @Cacheable(key = "#id", unless = "#result == null")
  public User findById(Integer id) {
    return userRepo.findById(id)
        .orElseThrow();
  }

  @Override
  @CachePut(key = "#result.id", unless = "#result == null")
  public User save(UserDto userDto) {
    User user = UserMapper.INSTANCE.mapFromDto(userDto);
    return userRepo.save(user);
  }
}
