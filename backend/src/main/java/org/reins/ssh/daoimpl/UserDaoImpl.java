package org.reins.ssh.daoimpl;

import org.hibernate.SessionFactory;
import org.reins.ssh.dao.BookDao;
import org.reins.ssh.dao.UserDao;
import org.reins.ssh.entity.Book;
import org.reins.ssh.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
@Service
public class UserDaoImpl implements UserDao {
    @Autowired
    private SessionFactory sessionFactory;

    public List<User> queryAll() {
        List<User> list;

        System.out.println("Dao processing ...");

        list = sessionFactory.openSession().createQuery("from User").list();
//        for (Book book:
//                list) {
//            System.out.println(book.getId() + "  ");
//        }
        //System.out.println("Dao processed...");

        return list;
    }
}

