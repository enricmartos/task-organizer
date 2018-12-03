package org.emartos.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.List;

@Entity
public class User {
    @Id //Id as a primary key column
    @GeneratedValue //Hibernate will create this value
    private Long id;

    @Email
    private String email;

    @NotNull
    private String name;
    @Size(min = 4)
    private String password;
    // "user" is the field in Task entity
    //User is the owner of the relationship
    //All the operations (updates, delete) should also be performed on Task table
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Task> tasks;

    //One user can have multiple rols, and one role can be assigned to multiple users
    //User owner of the relationship, because contains the join table annotation
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="user_roles", joinColumns = {
            @JoinColumn(name="user_id", referencedColumnName = "id")
    }, inverseJoinColumns = {
            @JoinColumn(name="role_id", referencedColumnName = "id")
    })
    private List<Role> roles;

    public User(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public User () {

    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
