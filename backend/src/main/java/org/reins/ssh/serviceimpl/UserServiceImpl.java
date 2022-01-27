package org.reins.ssh.serviceimpl;



import org.reins.ssh.dao.UserDao;
import org.reins.ssh.entity.User;
import org.reins.ssh.service.BookService;
import org.reins.ssh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    public List<User> queryAll() {
        System.out.println("Service processing ...");

        List<User> list=userDao.queryAll();
        System.out.println("Service processed ...");

        return list;
    }
}
