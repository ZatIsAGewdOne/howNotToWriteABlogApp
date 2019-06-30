package blogApp.service;

import blogApp.database.BlogRepository;
import blogApp.database.UserRepository;
import blogApp.database.entities.BlogAndUser;
import blogApp.database.entities.User;
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
        if(user == null) throw new UsernameNotFoundException("Access denied!");

        return user;
    }

    public User getUserById(int id) {
        User user = userRepository.getOne(id);
        if(user == null) throw new UsernameNotFoundException("Access denied!");

        return user;
    }

    public User createUser(User user) {
        User foundUser = userRepository.getOne(user.getId());
        if(foundUser != null) {
            System.out.println("The username is already in use");
        }

        return userRepository.save(user);
    }

    public Collection<BlogAndUser> getAllBlogsByUserId(int id) {
        User user = userRepository.getOne(id);
        if(user == null) {
            throw new UsernameNotFoundException("Access denied!");
        } else if (id == user.getId()) {
            return blogRepository.findBlogsByUser(user);
        } else {
            return null;
        }

    }

    public User findUserEntity(User user) {
        User foundUser = userRepository.getOne(user.getId());

        if(foundUser == null) throw new UsernameNotFoundException("Access denied!");

        return foundUser;
    }
}
