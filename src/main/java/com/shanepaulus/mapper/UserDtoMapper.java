package com.shanepaulus.mapper;

import com.shanepaulus.domain.User;
import com.shanepaulus.model.UserDto;
import com.shanepaulus.model.UserRequest;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author Shane Paulus
 * <p>
 * Date Created : 23-May-2023.
 */

@Mapper
public interface UserDtoMapper {

  UserDtoMapper INSTANCE = Mappers.getMapper(UserDtoMapper.class);

  List<UserDto> fromUserList(List<User> users);

  UserDto mapFromUser(User user);

  UserDto mapFromRequest(UserRequest userRequest);

}
