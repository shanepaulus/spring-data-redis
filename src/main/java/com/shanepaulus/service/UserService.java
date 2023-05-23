package com.shanepaulus.service;

import com.shanepaulus.model.UserDto;
import java.util.List;

/**
 * @author Shane Paulus
 * <p>
 * Date Created : 23-May-2023.
 */

public interface UserService {

  List<UserDto> findAll();

}
