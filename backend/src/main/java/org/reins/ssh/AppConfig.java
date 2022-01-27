package org.reins.ssh;

import org.apache.struts2.dispatcher.filter.StrutsPrepareAndExecuteFilter;
import org.hibernate.SessionFactory;
import org.reins.ssh.dao.BookDao;
import org.reins.ssh.dao.UserDao;
import org.reins.ssh.daoimpl.BookDaoImpl;
import org.reins.ssh.daoimpl.UserDaoImpl;
import org.reins.ssh.entity.*;
import org.reins.ssh.repository.BookIconRepository;
import org.reins.ssh.repository.BookRepository;
import org.reins.ssh.service.BookService;
import org.reins.ssh.service.UserService;
import org.reins.ssh.serviceimpl.BookServiceImpl;
import org.reins.ssh.serviceimpl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@Configuration
@ComponentScan
public class AppConfig {

    @Bean
    public BookDao bookDao() {
        return new BookDaoImpl();
    }
    @Bean
    public UserDao userDao() {
        return new UserDaoImpl();
    }

    @Bean
    public BookService bookService() {
        return new BookServiceImpl();
    }
    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }

//    @Bean
//    public BookRepository bookRepository() {
//        return new BookRepository() {
//            @Override
//            public List<Book> findAll() {
//                return null;
//            }
//
//            @Override
//            public List<Book> findAll(Sort sort) {
//                return null;
//            }
//
//            @Override
//            public List<Book> findAllById(Iterable<Integer> iterable) {
//                return null;
//            }
//
//            @Override
//            public <S extends Book> List<S> saveAll(Iterable<S> iterable) {
//                return null;
//            }
//
//            @Override
//            public void flush() {
//
//            }
//
//            @Override
//            public <S extends Book> S saveAndFlush(S s) {
//                return null;
//            }
//
//            @Override
//            public void deleteInBatch(Iterable<Book> iterable) {
//
//            }
//
//            @Override
//            public void deleteAllInBatch() {
//
//            }
//
//            @Override
//            public Book getOne(Integer integer) {
//                return null;
//            }
//
//            @Override
//            public <S extends Book> List<S> findAll(Example<S> example) {
//                return null;
//            }
//
//            @Override
//            public <S extends Book> List<S> findAll(Example<S> example, Sort sort) {
//                return null;
//            }
//
//            @Override
//            public Page<Book> findAll(Pageable pageable) {
//                return null;
//            }
//
//            @Override
//            public <S extends Book> S save(S s) {
//                return null;
//            }
//
//            @Override
//            public Optional<Book> findById(Integer integer) {
//                return Optional.empty();
//            }
//
//            @Override
//            public boolean existsById(Integer integer) {
//                return false;
//            }
//
//            @Override
//            public long count() {
//                return 0;
//            }
//
//            @Override
//            public void deleteById(Integer integer) {
//
//            }
//
//            @Override
//            public void delete(Book book) {
//
//            }
//
//            @Override
//            public void deleteAll(Iterable<? extends Book> iterable) {
//
//            }
//
//            @Override
//            public void deleteAll() {
//
//            }
//
//            @Override
//            public <S extends Book> Optional<S> findOne(Example<S> example) {
//                return Optional.empty();
//            }
//
//            @Override
//            public <S extends Book> Page<S> findAll(Example<S> example, Pageable pageable) {
//                return null;
//            }
//
//            @Override
//            public <S extends Book> long count(Example<S> example) {
//                return 0;
//            }
//
//            @Override
//            public <S extends Book> boolean exists(Example<S> example) {
//                return false;
//            }
//        };
//    }

//    @Bean
//    public BookIconRepository bookIconRepository{ return new BookIconRepository(){};}


    @Bean
    public SessionFactory sessionFactory(){

        org.hibernate.cfg.Configuration config = new org.hibernate.cfg.Configuration();

        config.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        config.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/web?serverTimezone=UTC");
        config.setProperty("hibernate.connection.username", "root");
        config.setProperty("hibernate.connection.password", "123456");
        config.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        config.setProperty("current_session_context_class", "thread");
        config.addAnnotatedClass(Book.class);
        config.addAnnotatedClass(User.class);
        config.addAnnotatedClass(Slot.class);
        config.addAnnotatedClass(Order.class);

        return config.buildSessionFactory();
    }

    @Bean
    public StrutsPrepareAndExecuteFilter strutsPrepareAndExecuteFilter(){
        return new StrutsPrepareAndExecuteFilter();
    }

}
