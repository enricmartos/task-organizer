package org.emartos.services;

import org.emartos.entities.Role;
import org.emartos.entities.User;
import org.emartos.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//Bean and placed in the app context
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void createUser(User user) {
        //Encoding the password in a hash
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        //Create and set role
        Role userRole = new Role("user");
        List<Role> roles = new ArrayList<>();
        roles.add(userRole);
        user.setRoles(roles);
        //Due to Cascade ALL in roles field in user class
        //this role will be persisting when saving the user
        userRepository.save(user);
    }

    public void createAdmin(User user) {
        //Encoding the password in a hash
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        //Create and set role
        Role userRole = new Role("admin");
        List<Role> roles = new ArrayList<>();
        roles.add(userRole);
        user.setRoles(roles);
        //Due to Cascade ALL in roles field in user class
        //this role will be persisting when saving the user
        userRepository.save(user);
    }

    public User findOne(Long id) {

        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.get();

        return user;
    }

    public User findOneByEmail(String email) {

        User user = userRepository.findByEmail(email);

        return user;
    }
}