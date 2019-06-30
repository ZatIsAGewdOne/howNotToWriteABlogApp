package blogApp.database;

import blogApp.database.entities.Blog;
import blogApp.database.entities.BlogAndUser;
import blogApp.database.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Integer> {

    @Query("select new blogApp.database.entities.BlogAndUser(b.blogText, b.blogDate, u.username, u.id) from Blog b inner join b.user u group by u, b.blogText, b.blogDate having u = :user")
    Collection<BlogAndUser> findBlogsByUser(@Param("user") User user);
}
