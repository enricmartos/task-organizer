package org.emartos.repositories;

import org.emartos.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);

    List<User> findByNameLike(String name);
}
