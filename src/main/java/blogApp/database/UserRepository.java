package blogApp.database;

import blogApp.database.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // This implements CRUD functionality to User table in database

    User findByName(String name);
    User findDistinctByName(String name);
}
