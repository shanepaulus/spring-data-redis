package com.shanepaulus.api;

import com.shanepaulus.model.UserDto;
import com.shanepaulus.service.UserService;
import java.util.List;
import lombok.AllArgsConstructor;
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
    return null;
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDto> get(@PathVariable("id") Integer id) {
    return null;
  }
}
