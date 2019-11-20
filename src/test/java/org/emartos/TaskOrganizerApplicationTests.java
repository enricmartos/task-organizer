//package org.emartos;
//
//import org.emartos.entities.User;
//import org.emartos.repositories.UserRepository;
//import org.emartos.services.UserService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import static org.junit.Assert.assertNotNull;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class TaskOrganizerApplicationTests {
//
//    @Autowired
//    private UserService userService;
//
//
//    @Test
//    public void find_existing_users() {
//
//        User userDB = new User("testUser@mail.com",
//                "testUser", "123456");
//
//        String roleName = "USER";
//        userDB.setRole(roleName);
//        userService.saveOne(userDB);
//
//        User userTest = userService.findByEmail("testUser@mail.com");
//        assertNotNull(userTest);
//    }
//}