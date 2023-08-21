package dev.ionutb.movies;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface UserRepository  extends MongoRepository<User, ObjectId> {

    Optional<User> findByUsername(String username);

}

