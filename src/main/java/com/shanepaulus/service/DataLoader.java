package com.shanepaulus.service;

import com.shanepaulus.domain.User;
import com.shanepaulus.repo.UserRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

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
    private static final String[] NAMES = {
            "Alice", "Bob", "Charlie", "David", "Eve", "Frank", "Grace", "Henry", "Ivy", "Jack", "Kate",
            "Leo", "Mia",
            "Noah", "Olivia", "Peter", "Quinn", "Ryan", "Sophia", "Thomas", "Uma", "Victor", "Wendy",
            "Xavier", "Yara", "Zoe"
    };

    private static final String[] SURNAMES = {
            "Smith", "Johnson", "Williams", "Brown", "Jones", "Miller", "Davis", "Garcia", "Rodriguez",
            "Wilson"
    };

    private static final String[] NICKNAMES = {
            "Awesome", "Cool", "Fantastic", "Great", "Incredible", "Magical", "Super", "Wonderful"
    };

    private final UserRepo userRepo;
    private final ForkJoinPool dataLoaderTreadPool;

    private static String getRandomValue(Random random, String[] values) {
        int index = random.nextInt(values.length);
        return values[index];
    }

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
        final int recordCount = 400000;
        final Random random = new Random();
        final List<User> userList = new ArrayList<>();

        for (int index = 0; index < recordCount; index++) {
            var name = getRandomValue(random, NAMES);
            var surName = getRandomValue(random, SURNAMES);
            var nickName = getRandomValue(random, NICKNAMES);

            userList.add(new User(null, name, surName, nickName));
            //userRepo.save(new User(null, name, surName, nickName));
        }

        userRepo.saveAll(userList);
        log.info("Competed cycle from {}", Thread.currentThread().getName());
    }
}
