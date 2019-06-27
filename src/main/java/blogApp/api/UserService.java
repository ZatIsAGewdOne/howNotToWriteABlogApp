package blogApp.api;

import blogApp.database.UserRepository;
import blogApp.database.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
@RequestMapping("user")
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
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

        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12)));
        return userRepository.save(user);
    }

    @PostMapping(value = "login")
    public void login(HttpServletResponse response, @RequestParam String name, @RequestParam String password) throws Exception {
        User user = userRepository.findByName(name);
        if(user != null && BCrypt.checkpw(password, user.getPassword())) {
            System.out.println(user.getName() + " has logged in!");
            response.getWriter().println("<h1>Welcome, " + user.getName() + "! </h1>");
            response.setStatus(200);
        } else {
            System.out.println("Access denied!");
            response.getWriter().println("<h1>Access denied!</h1>");
            response.setStatus(401);
        }
    }
}
