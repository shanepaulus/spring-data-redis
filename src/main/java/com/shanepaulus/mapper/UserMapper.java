package com.shanepaulus.mapper;

import com.shanepaulus.domain.User;
import com.shanepaulus.model.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author Shane Paulus
 * <p>
 * Date Created : 24-May-2023.
 */

@Mapper
public interface UserMapper {

  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  User mapFromDto(UserDto userDto);

}
