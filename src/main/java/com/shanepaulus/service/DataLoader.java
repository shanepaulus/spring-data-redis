package com.shanepaulus.service;

import com.shanepaulus.domain.User;
import com.shanepaulus.repo.UserRepo;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author Shane Paulus
 * <p>
 * Date Created : 23-May-2023.
 */

@Component
@Profile("instance-1")
@AllArgsConstructor
@Slf4j
public class DataLoader {

  private final UserRepo userRepo;

  @PostConstruct
  public void init() {
    log.info("About to load data into the DB....");

    List<User> userList = new ArrayList<>();
    userList.add(new User(null, "Shane", "Paulus", "Paulie"));
    userList.add(new User(null, "Joseph", "Knight", ""));
    userList.add(new User(null, "Johnny", "Copper", "Coop"));
    userList.forEach(userRepo::save);

    JedisPool jedisPool = new JedisPool("localhost", 6379);
    Jedis jedis = jedisPool.getResource();
    jedis.set("test", "something!");
  }


  @PreDestroy
  public void beforeShutdown() {
    userRepo.deleteAll();
  }
}
