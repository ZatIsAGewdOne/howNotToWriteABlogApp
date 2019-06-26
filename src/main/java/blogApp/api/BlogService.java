package blogApp.api;

import blogApp.database.BlogRepository;
import blogApp.database.entities.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.security.RolesAllowed;


@RestController
@RequestMapping("blog")
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    // Returns a list of all the blogs in db.
    @GetMapping("list")
    public Iterable<Blog> getBlogList(@RequestParam int pageNo, @RequestParam(required = false, defaultValue = "3") int pageSize, @RequestParam(required = false, defaultValue = "id") String sortBy) {
        return blogRepository.findAll(PageRequest.of(pageNo, pageSize, Sort.by(sortBy)));
    }


    // This creates a blog. Need to add roles functionality
    @RolesAllowed({"ADMIN", "USER"})
    @PostMapping(value = "create")
    public Blog createBlog(@RequestBody Blog blog) {
        return blogRepository.save(blog);
    }

    // This updates a blog. Need to add roles functionality
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

    // This deletes a blog. Need to add roles functionality
    @DeleteMapping("delete")
    public void deleteBlog(@RequestParam int id) {
        Blog blogFound = blogRepository.getOne(id);
        if(blogFound == null) {
            System.out.println("Entity not found!");
        }
        blogRepository.delete(blogFound);
    }
}
