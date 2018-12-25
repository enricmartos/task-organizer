package org.emartos.repositories;

import org.emartos.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);

    List<User> findByNameLike(String name);
}
