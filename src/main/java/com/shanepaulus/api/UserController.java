package com.shanepaulus.api;

import com.shanepaulus.mapper.UserDtoMapper;
import com.shanepaulus.model.UserDto;
import com.shanepaulus.service.UserService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Shane Paulus
 * <p>
 * Date Created : 23-May-2023.
 */

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping
  public ResponseEntity<List<UserDto>> getAll() {
    return new ResponseEntity<>(UserDtoMapper.INSTANCE.fromUserList(userService.findAll()),
        HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDto> get(@PathVariable("id") Integer id) {
    return new ResponseEntity<>(UserDtoMapper.INSTANCE.mapFromUser(userService.findById(id)),
        HttpStatus.OK);
  }
}
