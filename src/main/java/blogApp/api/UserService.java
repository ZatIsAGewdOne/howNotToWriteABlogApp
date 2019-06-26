package blogApp.api;

import blogApp.database.UserRepository;
import blogApp.database.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "{id}")
    public Optional<User> getUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()) {
            System.out.println("User not found!");
        }

        return user;
    }

    @PostMapping("create")
    public User createUser(@RequestBody User user) {
        User foundUser = userRepository.findByName(user.getName());
        if (foundUser != null) {
            System.out.println("Name already in use! Please try again!");
        }
        return userRepository.save(user);
    }

    @PostMapping(value = "login")
    public void login(HttpServletResponse response, @RequestParam String name, @RequestParam String password) {
        User user = userRepository.findByName(name);
        if(user != null && BCrypt.checkpw(password, user.getPassword())) {
            System.out.println(user.getName() + " has logged in!");
            response.setStatus(200);
        } else {
            System.out.println("Access denied!");
            response.setStatus(401);
        }
    }
}
