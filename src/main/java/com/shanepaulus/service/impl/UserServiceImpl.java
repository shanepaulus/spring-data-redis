package com.shanepaulus.service.impl;

import com.shanepaulus.domain.User;
import com.shanepaulus.mapper.UserMapper;
import com.shanepaulus.model.request.UserSaveRequest;
import com.shanepaulus.repo.UserRepo;
import com.shanepaulus.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
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

    private final UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public User findById(Integer id) {
        return userRepo.getReferenceById(id);
    }

    @Override
    public User save(UserSaveRequest dto) {
        return userRepo.save(userMapper.map(dto));
    }

    @Override
    public void loadUsersIntoCache() {
        log.info("Loading the user list int cache....");

        int pageSize = 400000;
        int pageNumber = 0;
        Page<User> usersPage;
        final String key = "users";
        userRedisTemplate.delete(key);
        final LocalTime currentTime = LocalTime.now();

        do {
            var pageRequest = PageRequest.of(pageNumber, pageSize);
            usersPage = userRepo.findAll(pageRequest);
            log.info("fetched a 400k batch");

            userRedisTemplate.opsForSet().add(key, usersPage.getContent().toArray(new User[0]));
            pageNumber++;
        } while (usersPage.hasNext());

        log.info("Completed the loadUsersIntoCache method in {}ms",
                ChronoUnit.MILLIS.between(currentTime, LocalTime.now()));
    }
}
