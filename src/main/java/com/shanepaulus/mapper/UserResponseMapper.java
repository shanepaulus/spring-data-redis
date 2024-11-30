package com.shanepaulus.mapper;

import com.shanepaulus.domain.User;
import com.shanepaulus.model.response.UserResponse;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author Shane Paulus
 * <p>
 * Date Created : 23-May-2023.
 */

@Mapper(componentModel = "spring")
public interface UserResponseMapper {
    List<UserResponse> map(List<User> users);

    UserResponse map(User user);
}
