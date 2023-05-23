package com.shanepaulus.service.impl;

import com.shanepaulus.domain.User;
import com.shanepaulus.exception.UserNotFoundException;
import com.shanepaulus.repo.UserRepo;
import com.shanepaulus.service.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * @author Shane Paulus
 * <p>
 * Date Created : 23-May-2023.
 */

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

  private final UserRepo userRepo;

  @Override
  public List<User> findAll() {
    List<User> userList = new ArrayList<>();

    for (User user : userRepo.findAll()) {
      userList.add(user);
    }

    return userList;
  }

  @Override
  public User findById(Integer id) {
    return userRepo.findById(id)
        .orElseThrow();
  }
}
