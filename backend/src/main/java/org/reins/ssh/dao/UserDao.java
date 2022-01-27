package org.reins.ssh.dao;

import org.reins.ssh.entity.User;

import java.util.List;


public interface UserDao {
    List<User> queryAll();
}
