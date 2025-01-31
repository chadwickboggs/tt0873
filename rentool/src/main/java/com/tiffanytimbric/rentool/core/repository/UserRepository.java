package com.tiffanytimbric.rentool.core.repository;

import com.tiffanytimbric.rentool.core.model.User;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
//@PreAuthorize("hasRole('USER')")
public interface UserRepository extends ListCrudRepository<User, UUID> {

    @NonNull
    Optional<User> findByName(String name);

}
