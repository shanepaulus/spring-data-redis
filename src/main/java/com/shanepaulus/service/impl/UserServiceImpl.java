package com.shanepaulus.service.impl;

import com.shanepaulus.domain.User;
import com.shanepaulus.mapper.UserMapper;
import com.shanepaulus.model.UserDto;
import com.shanepaulus.repo.UserRepo;
import com.shanepaulus.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Shane Paulus
 * <p>
 * Date Created : 23-May-2023.
 */

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final RedisTemplate<String, User> userRedisTemplate;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        loadUsersIntoCache();
    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();

        for (User user : userRepo.findAll()) {
            userList.add(user);
        }

        return userList;
    }

    @Override
    public User findById(Integer id) {
        return userRepo.findById(id)
                .orElseThrow();
    }

    @Override
    public User save(UserDto userDto) {
        User user = UserMapper.INSTANCE.mapFromDto(userDto);
        return userRepo.save(user);
    }

    @Override
    public void loadUsersIntoCache() {
        log.info("Loading the user list int cache....");
        int pageSize = 250000;
        int pageNumber = 0;
        Page<User> usersPage;
        final String key = "users";
        userRedisTemplate.delete(key);

        do {
            PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
            usersPage = userRepo.findAll(pageRequest);
            log.info("fetched a 250k batch");

            userRedisTemplate.opsForSet().add(key, usersPage.getContent().toArray(new User[0]));
            pageNumber++;
        } while (usersPage.hasNext());

        log.info("Completed the loadUsersIntoCache method at {}", LocalDateTime.now());
    }
}
