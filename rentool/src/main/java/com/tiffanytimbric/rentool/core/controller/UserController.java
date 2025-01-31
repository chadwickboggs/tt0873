package com.tiffanytimbric.rentool.core.controller;

import com.tiffanytimbric.rentool.core.model.User;
import com.tiffanytimbric.rentool.core.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.tiffanytimbric.rentool.core.util.LangUtil.opt;

@RestController
//@PreAuthorize("hasRole('USER')")
public class UserController {

    private final UserRepository userRepository;

    public UserController(
            @NonNull final UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    @GetMapping("/userExist/{id}")
    @NonNull
    public boolean doesExist(
            @PathVariable @Nullable final UUID id
    ) {
        if (id == null) {
            return userRepository.count() > 0;
        }

        return userRepository.existsById(id);
    }

    @GetMapping("/user")
    @NonNull
    public ResponseEntity<List<User>> readAllUsers() {
        return ResponseEntity.ofNullable(
                userRepository.findAll()
        );
    }

    @GetMapping("/user/{id}")
    @NonNull
    public ResponseEntity<User> readUser(
            @PathVariable @Nullable final UUID id
    ) {
        if (id == null) {
            return ResponseEntity.of(Optional.empty());
        }

        return ResponseEntity.of(
                userRepository.findById(id)
        );
    }

    @GetMapping("/userByName/{name}")
    @NonNull
    public ResponseEntity<User> readUserByName(
            @PathVariable final String name
    ) {
        return ResponseEntity.of(
                userRepository.findByName(name)
        );
    }

    @PostMapping("/user")
    @NonNull
    public ResponseEntity<User> createUser(
            @RequestBody @Valid @Nullable final User user
    ) {
        if (user == null) {
            return ResponseEntity.of(Optional.empty());
        }

        if (opt(user.getId()).isEmpty()) {
            user.setId(UUID.randomUUID());
        }

        final User savedUser = userRepository.save(user);

        return ResponseEntity.of(
                Optional.of(savedUser)
        );
    }

    @PutMapping("/user")
    @NonNull
    public ResponseEntity<User> updateUser(
            @RequestBody @Valid @Nullable final User user
    ) {
        if (user == null) {
            return ResponseEntity.of(Optional.empty());
        }

        final User savedUser = userRepository.save(user);

        return ResponseEntity.of(
                Optional.of(savedUser)
        );
    }

    @PatchMapping("/user")
    @NonNull
    public ResponseEntity<User> patchUser(
            @RequestBody @Valid @Nullable final User user
    ) {
        if (user == null) {
            return ResponseEntity.of(Optional.empty());
        }

        throw new ResponseStatusException(
                HttpStatusCode.valueOf(400),
                "Invalid method, method not implemented.  Method Name: \"patchUser\""
        );
    }

    @DeleteMapping("/user/{id}")
    @NonNull
    public ResponseEntity<User> deleteUser(
            @PathVariable @Nullable final UUID id
    ) {
        if (id == null) {
            return ResponseEntity.of(Optional.empty());
        }

        final Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()) {
            return ResponseEntity.of(Optional.empty());
        }

        userRepository.deleteById(id);

        return ResponseEntity.of(userOpt);
    }

}
