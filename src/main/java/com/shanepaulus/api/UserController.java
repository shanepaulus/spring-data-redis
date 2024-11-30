package com.shanepaulus.api;

import com.shanepaulus.mapper.UserResponseMapper;
import com.shanepaulus.model.request.UserSaveRequest;
import com.shanepaulus.model.response.UserResponse;
import com.shanepaulus.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Shane Paulus
 * <p>
 * Date Created : 23-May-2023.
 */

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserResponseMapper userResponseMapper;

    @GetMapping
    public List<UserResponse> getAll() {
        return userResponseMapper.map(userService.findAll());
    }

    @GetMapping("/{id}")
    public UserResponse get(@PathVariable("id") Integer id) {
        return userResponseMapper.map(userService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse save(@RequestBody UserSaveRequest request) {
        return userResponseMapper.map(userService.save(request));
    }
}
