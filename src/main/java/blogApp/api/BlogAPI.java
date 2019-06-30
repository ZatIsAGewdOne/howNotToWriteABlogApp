package blogApp.api;

import blogApp.database.entities.Blog;
import blogApp.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;


@RestController
@RequestMapping("blog")
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class BlogAPI {

    private final BlogService blogService;

    public BlogAPI(BlogService blogService) {
        this.blogService = blogService;
    }

    // Returns a list of all the blogs in db.
    @PreAuthorize("permitAll()")
    @GetMapping("listPage")
    public Iterable<Blog> getBlogList(@RequestParam(required = false, defaultValue = "0") int pageNo, @RequestParam(required = false, defaultValue = "3") int pageSize, @RequestParam(required = false, defaultValue = "id") String sortBy) {
        return blogService.blogListPageable(pageNo, pageSize, sortBy);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("list")
    public List<Blog> getBlogList() {
        return blogService.getBlogList();
    }

    // This creates a blog. Need to add roles functionality
    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "create")
    public Blog createBlog(@RequestBody Blog blog) {
        return blogService.createBlog(blog);
    }

    // This updates a blog. Need to add roles functionality
    @PreAuthorize("isAuthenticated()")
    @PutMapping("update")
    public Blog updateBlog(@RequestParam int id, @RequestBody Blog blog) {
        Blog blogToFind = blogService.getBlogById(id);
        if(blogToFind != null){
            blog.setId(id);
        } else {
            System.out.println("Blog not found!");
        }
        return blogService.createBlog(blog);
    }

    // This deletes a blog. Need to add roles functionality
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("delete")
    public void deleteBlog(@RequestParam int id) {
        Blog blogFound = blogService.getBlogById(id);
        if(blogFound == null) {
            System.out.println("Entity not found!");
        }
        blogService.deleteBlog(blogFound);
    }
}
