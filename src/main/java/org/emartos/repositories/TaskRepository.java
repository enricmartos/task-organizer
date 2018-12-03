package org.emartos.repositories;

import org.emartos.entities.Task;
import org.emartos.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    //https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#_supported_query_keywords
    //Supported query keyworks - spring data jpa
    List<Task> findByUser(User user);

}
