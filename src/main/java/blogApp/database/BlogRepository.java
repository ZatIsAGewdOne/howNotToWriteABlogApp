package blogApp.database;

import blogApp.database.entities.Blog;
import blogApp.database.entities.BlogAndUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Integer> {

    @Query("select new blogApp.database.entities.BlogAndUser(b.blogText, b.blogDate, u) from Blog b inner join b.user u group by u")
    List<BlogAndUser> findByUser(String username);
}
