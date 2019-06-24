package blogApp.api;

import blogApp.database.BlogRepository;
import blogApp.database.entities.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("blog")
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @GetMapping("list")
    public Iterable<Blog> getBlogList() {
        Iterable<Blog> blogs = blogRepository.findAll();
        return blogs;
    }
}
