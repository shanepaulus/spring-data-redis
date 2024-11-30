package com.shanepaulus.mapper;

import com.shanepaulus.domain.User;
import com.shanepaulus.model.request.UserSaveRequest;
import org.mapstruct.Mapper;

/**
 * @author Shane Paulus
 * <p>
 * Date Created : 24-May-2023.
 */

@Mapper(componentModel = "spring")
public interface UserMapper {
    User map(UserSaveRequest dto);
}
