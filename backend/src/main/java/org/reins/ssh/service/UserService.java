package org.reins.ssh.service;

import org.reins.ssh.entity.User;

import java.util.List;

public interface UserService {
    List<User> queryAll();
}
