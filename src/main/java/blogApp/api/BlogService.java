package blogApp.api;

import blogApp.database.BlogRepository;
import blogApp.database.entities.Blog;
import blogApp.database.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("blog")
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;


    @GetMapping("list")
    public Iterable<Blog> getBlogList(@RequestParam int pageNo, @RequestParam(required = false, defaultValue = "3") int pageSize, @RequestParam(required = false, defaultValue = "id") String sortBy) {
        return blogRepository.findAll(PageRequest.of(pageNo, pageSize, Sort.by(sortBy)));
    }

    @RolesAllowed({"ADMIN", "USER"})
    @PostMapping(value = "create")
    public Blog createBlog(HttpServletRequest request, @RequestBody Blog blog) {
        return blogRepository.save(blog);
    }

    @PutMapping("update")
    public Blog updateBlog(@RequestParam int id, @RequestBody Blog blog) {
        Blog blogToFind = blogRepository.getOne(id);
        if(blogToFind != null){
            blog.setId(id);
        } else {
            System.out.println("Blog not found!");
        }
        return blogRepository.save(blog);
    }

    @DeleteMapping("delete")
    public void deleteBlog(@RequestParam int id) {
        Blog blogFound = blogRepository.getOne(id);
        if(blogFound == null) {
            System.out.println("Entity not found!");
        }
        blogRepository.delete(blogFound);
    }
}
