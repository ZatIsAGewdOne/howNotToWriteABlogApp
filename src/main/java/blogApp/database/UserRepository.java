package blogApp.database;

import blogApp.database.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>, org.springframework.data.repository.Repository<User, Integer> {
    // This implements CRUD functionality to User table in database

    User findByName(String name);
    User findDistinctByName(String name);
}
