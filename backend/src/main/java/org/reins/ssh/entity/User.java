package org.reins.ssh.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="users")
public class User implements Serializable {
    private  Long id;
    private  String username;
    private  String password;
    private  String role;
    public User(){};

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment",strategy ="increment")
    public Long getId(){ return id;}
    private  void setId(Long id){this.id=id;}


    public String getUsername(){return username;}
    public  void setUsername(String username){this.username=username;}


    public String getPassword(){return password;}
    public  void setPassword(String password){this.password=password;}

    public String getRole(){return role;}
    public  void setRole(String role){this.role=role;}
}
