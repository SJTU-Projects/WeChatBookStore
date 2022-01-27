package org.reins.ssh.repository;

import org.reins.ssh.entity.Bookicon;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface BookIconRepository extends MongoRepository<Bookicon, Integer> {
}
