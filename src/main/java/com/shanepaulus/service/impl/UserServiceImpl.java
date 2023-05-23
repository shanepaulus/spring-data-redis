package com.shanepaulus.service.impl;

import com.shanepaulus.model.UserDto;
import com.shanepaulus.service.UserService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Shane Paulus
 * <p>
 * Date Created : 23-May-2023.
 */

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

  @Override
  public List<UserDto> findAll() {
    return null;
  }
}
