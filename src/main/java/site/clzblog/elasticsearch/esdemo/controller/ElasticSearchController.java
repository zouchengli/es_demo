package site.clzblog.elasticsearch.esdemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import site.clzblog.elasticsearch.esdemo.entity.User;
import site.clzblog.elasticsearch.esdemo.repository.UserRepository;

import java.util.*;

@Slf4j
@RestController
public class ElasticSearchController {
    @Autowired
    private UserRepository userRepository;

    @PutMapping("add")
    public User add(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("get/by/id")
    public Optional<User> getById(String id) {
        return userRepository.findById(id);
    }

    @Scheduled(fixedDelay = 1000)
    public void scheduleAddUser() {
        Random random = new Random();
        List<User> users = new LinkedList();
        long started = System.currentTimeMillis();
        for (int i = 1; i <= 1000000; i++) {
            User user = new User(UUID.randomUUID().toString(), UUID.randomUUID().toString(), random.nextInt(2), random.nextInt(99));
            users.add(user);
            if (i % 10000 == 0) {
                userRepository.saveAll(users);
                log.info("Insert rows {} consumed millis {} ms", i, System.currentTimeMillis() - started);
                started = System.currentTimeMillis();
            }
        }
    }
}
