package blogApp.service;

import blogApp.database.BlogRepository;
import blogApp.database.entities.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public List<Blog> getBlogList() {
        return blogRepository.findAll();
    }

    public Page<Blog> blogListPageable(int pageNo, int pageSize, String sortBy) {
        return blogRepository.findAll(PageRequest.of(pageNo, pageSize, Sort.by(sortBy)));
    }

    public Page<Blog> blogListPageable(int pageNo, int pageSize) {
        return blogRepository.findAll(PageRequest.of(pageNo, pageSize));
    }

    public Blog getBlogById(int id) {
        Blog blog = blogRepository.getOne(id);

        if (blog == null) throw new EntityNotFoundException("Blog not found!");

        return blog;
    }

    public Blog createBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    public void deleteBlog(Blog blog) {
        blogRepository.delete(blog);
    }
}
