package org.emartos;

import org.emartos.entities.Project;
import org.emartos.entities.Task;
import org.emartos.entities.User;
import org.emartos.services.ProjectService;
import org.emartos.services.TaskService;
import org.emartos.services.UserService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.lang.UsesSunHttpServer;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskOrganizerApplicationTests {


    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private ProjectService projectService;

    //Before executing any test, this method will be executed
    @Before
    public void initDb() {

        {
            User newUser = new User("testUser@mail.com",
                    "testUser", "123456");
            userService.createUser(newUser);
        }
        {
            User newUser = new User("testAdmin@mail.com",
                    "testAdmin", "123456");
            userService.createAdmin(newUser);
        }
        {
            Project project = new Project("Development");
            projectService.createOne(project);
        }

            Task userTask = new Task("03-01-2018", "00:11",
                    "11:00", "You need to work today");
            User user = userService.findByEmail("testUser@mail.com");
            userTask.setProject(projectService.findByName("Development"));
            userTask.setUser(user);
            taskService.createOne(userTask);
    }


//    @Test
//    public void testTask() {
//        User user = userService.findOneByEmail("testUser@mail.com");
//        List<Task> task = taskService.findUserTask(user);
//        assertNotNull(task);
//    }

    @Test
    public void testUser() {
        User user = userService.findByEmail("testUser@mail.com");
        assertNotNull(user);
        User admin = userService.findByEmail("testAdmin@mail.com");
        assertEquals(admin.getEmail(), "testAdmin@mail.com");
    }

}
