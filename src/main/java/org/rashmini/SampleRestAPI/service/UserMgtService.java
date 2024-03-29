package org.rashmini.SampleRestAPI.service;

import org.rashmini.SampleRestAPI.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Service
public class UserMgtService {

    List<User> userList = new ArrayList<>(Arrays.asList(new User(UUID.randomUUID().toString(), "Smith", "Jane", "jane@mail.com"),
            new User(UUID.randomUUID().toString(), "Taylor", "John", "john@mail.com")));

    public List<User> getUserList() {

        return userList;
    }

    public User addUser(User user) {

        String uuid = UUID.randomUUID().toString();
        user.setId(uuid);

        userList.add(user);

        return user;
    }

    public List<User> getUsersBySurname(String surname) {
        List<User> users = new ArrayList<>();
        for (User user : userList) {
            if (user.getSurname().equals(surname)) {
                users.add(user);
            }
        }
        return users;
    }

    public User deleteUserById(String id) {

        Iterator<User> iterator = userList.iterator();

        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getId().equals(id)) {
                iterator.remove();
                return user;
            }
        }

        return null;
    }
}
