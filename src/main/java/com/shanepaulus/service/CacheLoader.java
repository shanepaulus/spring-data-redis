package com.shanepaulus.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Profile("instance-2")
@AllArgsConstructor
@Slf4j
public class CacheLoader {

  private final UserService userService;

  @EventListener(ApplicationReadyEvent.class)
  public void init() {
    log.info("About to load the data into cache....");
    userService.loadUsersIntoCache();
  }
}
