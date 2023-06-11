package com.shanepaulus.service;

import com.shanepaulus.domain.User;
import com.shanepaulus.repo.UserRepo;
import jakarta.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

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

  @EventListener(ApplicationReadyEvent.class)
  public void init() {
    log.info("About to load data into the DB....");

    List<User> userList = new ArrayList<>();
    userList.add(new User(null, "Shane", "Paulus", "Paulie"));
    userList.add(new User(null, "Joseph", "Knight", ""));
    userList.add(new User(null, "Johnny", "Copper", "Coop"));
    userList.forEach(userRepo::save);
  }


  @PreDestroy
  public void beforeShutdown() {
    log.info("About to delete all data....");
    userRepo.deleteAll();
  }
}
