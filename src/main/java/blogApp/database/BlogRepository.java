package blogApp.database;

import blogApp.database.entities.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BlogRepository extends JpaRepository<Blog, Integer> {
        /*
    Implements CRUD functionality to the blog table in database
     */
}
