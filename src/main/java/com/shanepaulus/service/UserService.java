package com.shanepaulus.service;

import com.shanepaulus.domain.User;
import com.shanepaulus.model.request.UserSaveRequest;

import java.util.List;

/**
 * @author Shane Paulus
 * <p>
 * Date Created : 23-May-2023.
 */

public interface UserService {
    List<User> findAll();

    User findById(Integer id);

    User save(UserSaveRequest userDto);

    void loadUsersIntoCache();
}
