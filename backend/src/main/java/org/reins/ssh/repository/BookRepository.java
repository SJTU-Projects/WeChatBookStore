package org.reins.ssh.repository;

import org.reins.ssh.entity.Book;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
}
