package org.emartos.repositories;

import org.emartos.entities.Task;
import org.emartos.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {

    //https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#_supported_query_keywords
    //Supported query keyworks - spring data jpa
    List<Task> findByUser(User user);

}
