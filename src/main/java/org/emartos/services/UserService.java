package org.emartos.services;

import org.emartos.entities.User;
import org.emartos.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    // READ

    public List<User> findAll() {

        List<User> users = new ArrayList<User>();

        for (User user: userRepository.findAll()) {
            users.add(user);
        }
        return users;
    }

    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    public List<User> findByName(String name) {
        //this will match all the names that contain string name (sql query notation)
        return  userRepository.findByNameLike("%"+name+"%");
    }

    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user;
    }

    //CREATE
    public void saveOne(User user) {
        //Encoding the password in a hash
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        //Create and set role
        userRepository.save(user);
    }

    public boolean isUserPresent(String email) {
        User user = userRepository.findByEmail(email);
        if (user!=null)
            return true;
        return false;
    }
}
