package blogApp.database;

import blogApp.database.entities.Blog;
import org.springframework.data.repository.CrudRepository;

public interface BlogRepository extends CrudRepository<Blog, Integer> {
        /*
    Implements CRUD functionality to the blog table in database
     */
}
