package com.shanepaulus.service;

import com.shanepaulus.domain.User;
import com.shanepaulus.repo.UserRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

/**
 * @author Shane Paulus
 * <p>
 * Date Created : 23-May-2023.
 */

//@Component
//@Profile("instance-1")
@AllArgsConstructor
@Slf4j
public class DataLoader {

    private static final String[] NAMES = {
            "Alice", "Bob", "Charlie", "David", "Eve", "Frank", "Grace", "Henry", "Ivy", "Jack", "Kate", "Leo", "Mia",
            "Noah", "Olivia", "Peter", "Quinn", "Ryan", "Sophia", "Thomas", "Uma", "Victor", "Wendy", "Xavier", "Yara", "Zoe"
    };

    private static final String[] SURNAMES = {
            "Smith", "Johnson", "Williams", "Brown", "Jones", "Miller", "Davis", "Garcia", "Rodriguez", "Wilson"
    };

    private static final String[] NICKNAMES = {
            "Awesome", "Cool", "Fantastic", "Great", "Incredible", "Magical", "Super", "Wonderful"
    };

    private final UserRepo userRepo;
    private final ForkJoinPool dataLoaderTreadPool;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        log.info("About to load data into the DB....");

        for (int index = 0; index < dataLoaderTreadPool.getParallelism(); index++) {
            dataLoaderTreadPool.execute(this::loadDummyData);
        }

        log.info("Completed loading the data into the DB...");
    }

    private void loadDummyData() {
        log.info("Executing loadDummyData from {}", Thread.currentThread().getName());
        final int recordCount = 3468012;
        final Random random = new Random();

        for (int index = 0; index < recordCount; index++) {
            String name = getRandomValue(random, NAMES);
            String surName = getRandomValue(random, SURNAMES);
            String nickName = getRandomValue(random, NICKNAMES);

            userRepo.save(new User(null, name, surName, nickName));
        }

        log.info("Competed cycle from {}", Thread.currentThread().getName());
    }

    private static String getRandomValue(Random random, String[] values) {
        int index = random.nextInt(values.length);
        return values[index];
    }
}
