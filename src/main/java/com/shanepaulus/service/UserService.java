package com.shanepaulus.service;

import com.shanepaulus.domain.User;
import java.util.List;

/**
 * @author Shane Paulus
 * <p>
 * Date Created : 23-May-2023.
 */

public interface UserService {

  List<User> findAll();

  User findById(Integer id);
}
