package org.emartos.services;

import org.emartos.entities.User;
import org.emartos.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//Bean and placed in the app context
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // READ

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        Optional<User> optUser = userRepository.findById(id);
        User user = optUser.get();
        return user;
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
    public void createUser(User user) {
        //Encoding the password in a hash
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        //Create and set role
        user.setRole("USER");
        userRepository.save(user);
    }

    public void createAdmin(User user) {
        //Encoding the password in a hash
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        //Create and set role
        user.setRole("ADMIN");
        userRepository.save(user);
    }

    public boolean isUserPresent(String email) {
        User user = userRepository.findByEmail(email);
        if (user!=null)
            return true;
        return false;
    }
}
