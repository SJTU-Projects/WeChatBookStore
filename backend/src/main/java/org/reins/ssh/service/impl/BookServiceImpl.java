//package org.reins.ssh.service.impl;
//
//import org.reins.ssh.dao.BookDao;
//import org.reins.ssh.entity.Book;
//import org.reins.ssh.entity.Order;
//import org.reins.ssh.entity.Slot;
//import org.reins.ssh.service.BookService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class BookServiceImpl implements BookService {
//    @Autowired
//    BookDao bookDao;
//
//    public List<Book> queryAll() {
//        System.out.println("Service processing ...");
//
//        List<Book> list=bookDao.queryAll();
//        System.out.println("Service processed ...");
//
//        return list;
//    }
//    public void changeslot(Slot slot)
//    {
//        System.out.println("Service slot ...");
//        bookDao.changeslot(slot);
//        System.out.println("Service sloted ...");
//    }
//    public  void addbook(Book book){
//        System.out.println("Service slot ...");
//        bookDao.addbook(book);
//        System.out.println("Service sloted ...");
//    }
//    public  List<Slot> findcart()
//    {
//        List<Slot> slist=bookDao.findcart();
//        return slist;
//    }
//    public  void  submit(Order order)
//    {
//        bookDao.submit(order);
//    }
//    public  void reclear(int id)
//    {
//        bookDao.reclear(id);
//    }
//    @Override
//    public  Book findbook(Integer id){return bookDao.findbook(id);}
//}
