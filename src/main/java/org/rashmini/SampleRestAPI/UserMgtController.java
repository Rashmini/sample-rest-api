package org.rashmini.SampleRestAPI;

import org.rashmini.SampleRestAPI.model.User;
import org.rashmini.SampleRestAPI.service.UserMgtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserMgtController {

    @Autowired
    UserMgtService userMgtService;

    @GetMapping
    public User[] getUsers() {

        return userMgtService.getUserList().toArray(new User[0]);
    }

    @PostMapping
    public ResponseEntity<Object> addUser(@RequestBody User user) {

        userMgtService.addUser(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{surname}")
    public List<User> getUsersBySurname(@PathVariable("surname") String surname) {

        return userMgtService.getUsersBySurname(surname);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUserById(@PathVariable("id") String id) {

        User user = userMgtService.deleteUserById(id);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
