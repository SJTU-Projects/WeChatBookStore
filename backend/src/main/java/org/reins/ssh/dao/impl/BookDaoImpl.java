//package org.reins.ssh.dao.impl;
//
//import com.alibaba.fastjson.JSONArray;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.reins.ssh.dao.BookDao;
//import org.reins.ssh.entity.Book;
//import org.reins.ssh.entity.Bookicon;
//import org.reins.ssh.entity.Order;
//import org.reins.ssh.entity.Slot;
//import org.reins.ssh.repository.BookIconRepository;
//import org.reins.ssh.repository.BookRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Optional;
//
//@Transactional
//@Repository
////@Service
//public class BookDaoImpl implements BookDao {
//    @Autowired
//    private SessionFactory sessionFactory;
//    @Autowired
//    private BookRepository bookRepository;
//    @Autowired
//    private BookIconRepository bookIconRepository;
//
//    public List<Book> queryAll() {
//        List<Book> list;
//
//        System.out.println("Dao processing ...");
//
//        list = sessionFactory.openSession().createQuery("from Book").list();
//        for (Book book:
//                list) {
//            System.out.println(book.getId() + "  ");
//        }
//        System.out.println("Dao processed...");
//        //sessionFactory.close();
//        return list;
//    }
//    public void changeslot(Slot slot)
//    {
//        long userid=slot.getUserid();
//        long bookid=slot.getBookid();
//        long number=slot.getNumber();
//        List<Slot> list;
//        list = sessionFactory.openSession().createQuery("from Slot").list();
//        for (Slot li : list) {
//            if (li.getBookid()!=null && li.getBookid().equals(bookid) && li.getUserid()!=null && li.getUserid().equals(userid)) {
//                //需要update而不是add一个新的进去
//                long num = li.getNumber() + number;
//                long idd = li.getId();
//                //add=true;
//                Session session = sessionFactory.openSession();
//                session.beginTransaction();
//                Slot tmpslot = session.get(Slot.class, idd);
//                tmpslot.setNumber(num);
//                session.getTransaction().commit();
//                return;
//            }
//            //System.out.println("Book " + tmpstr + " " + book.getId());
//        }//要是找不到，那就应该加一个新的进去
//
//        Session session=sessionFactory.openSession();
//        System.out.println("Dao getsession ...");
//        session.beginTransaction();
//        session.save(slot);
//        System.out.println("Dao save...");
//        session.getTransaction().commit();
//        System.out.println("Dao commit...");
//    }
//    public void addbook(Book book)
//    {
//        Session session=sessionFactory.openSession();
//        System.out.println("Dao getsession ...");
//        session.beginTransaction();
//        session.save(book);
//        System.out.println("Dao save...");
//        session.getTransaction().commit();
//        System.out.println("Dao commit...");
//    }
//    public  List<Slot>findcart()
//    {
//        List<Slot> slist;
//        slist=sessionFactory.openSession().createQuery("from Slot ").list();
//        //sessionFactory.close();
//        return slist;
//    }
//    public  void submit(Order order)
//    {
//        Session session=sessionFactory.openSession();
//        System.out.println("Dao getsession ...");
//        session.beginTransaction();
//        session.save(order);
//        System.out.println("Dao save...");
//        session.getTransaction().commit();
//        System.out.println("Dao commit...");
//    }
//    public  void reclear(int id)
//    {
//        List<Slot> list;
//        Long l= (long) id;
//        list = sessionFactory.openSession().createQuery("from Slot").list();
//        for (Slot li : list) {
//            if ( li.getUserid()!=null && li.getUserid().equals(l)) {
//                Session session = sessionFactory.openSession();
//                session.beginTransaction();
//                //删除
//                long idd = li.getId();
//                Slot p =(Slot) session.get(Slot.class, idd);
//                session.delete(p);
//                session.getTransaction().commit();
//            }
//            //System.out.println("Book " + tmpstr + " " + book.getId());
//        }
//    }
//
//    @Override
//    public  Book findbook(Integer id)
//    {
//        System.out.println( "id:" + id);
//        //Long ID=id.longValue();
//        Book book = bookRepository.getOne(id);
//        if(book!=null)
//        {
//            System.out.println( "  "+ book.getName() );
//        }
//        else{
//            System.out.println( " booooom " );
//        }
//        long count=bookRepository.count();
//        System.out.println( "  "+ count );
//
//        //List<Book> book1 = bookRepository.findAll();
////        for (Book book2:
////                book1) {
////            if(book2!=null)
////            {
////                System.out.println(book2.getId() + "  ");
////            }
////            else{System.out.println("circle null" + "  ");break;}
////        }
//
//        Optional<Bookicon> icon = bookIconRepository.findById(id);
//        if (icon.isPresent()){
//            System.out.println("Not Null " + id);
//            //book.setIcon(icon.get());
//        }
//        else{
//            System.out.println("It's Null");
//            //book.setIcon(null);
//            //System.out.println("It's Null");
//        }
//        return book;
//    }
//}
