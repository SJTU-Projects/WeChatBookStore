package org.reins.ssh.service;

import org.reins.ssh.entity.Book;
import org.reins.ssh.entity.Order;
import org.reins.ssh.entity.Slot;

import java.util.List;

public interface BookService {
    List<Book> queryAll();
    void changeslot(Slot slot);
    void addbook(Book book);
    List<Slot> findcart();
    void submit(Order order);
    void reclear(int id);
    Book findbook(Integer id);
    List<Book> findall();
}
