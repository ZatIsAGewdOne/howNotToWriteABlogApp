package blogApp.service;

import blogApp.database.BlogRepository;
import blogApp.database.UserRepository;
import blogApp.database.entities.BlogAndUser;
import blogApp.database.entities.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final BlogRepository blogRepository;

    public UserService(UserRepository userRepository, BlogRepository blogRepository) {
        this.userRepository = userRepository;
        this.blogRepository = blogRepository;
    }

    public User getUserByName(String username) {
        User user = userRepository.findByUsername(username);
        if(user == null) ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return user;
    }

    public User getUserById(int id) {
        User user = userRepository.getOne(id);
        if(user == null) ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return user;
    }

    public User createUser(User user) {
        User foundUser = userRepository.getOne(user.getId());
        if(foundUser != null) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already in-use");
        }

        return userRepository.save(user);
    }

    public Collection<BlogAndUser> getAllBlogsByUserId(int id) {
        User user = userRepository.getOne(id);
        if(user == null) {
            ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } else if (id == user.getId()) {
            return blogRepository.findBlogsByUser(user);
        }

        return null;

    }

    public User findUserEntity(User user) {
        User foundUser = userRepository.getOne(user.getId());

        if(foundUser == null) ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return foundUser;
    }
}
