package blogApp.service;

import blogApp.database.BlogRepository;
import blogApp.database.UserRepository;
import blogApp.database.entities.BlogAndUser;
import blogApp.database.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BlogRepository blogRepository;

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

    public List<BlogAndUser> getAllBlogsByUser(String username) {
        User user = userRepository.findByUsername(username);

        if(user == null) throw new UsernameNotFoundException("Access denied!");

        return blogRepository.findByUser(user.getUsername());
    }
}
