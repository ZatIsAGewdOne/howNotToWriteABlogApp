package blogApp.api;

import blogApp.database.UserRepository;
import blogApp.database.entities.User;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
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

    @GetMapping(value = "{id}", produces = "application/json")
    public Optional<User> getUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()) {
            System.out.println("User not found!");
        }

        return user;
    }

    @PostMapping(value = "login")
    public void login(HttpServletRequest request, HttpServletResponse response, @RequestParam("name") String name, @RequestParam("password") String password) {
        response.reset();
        User user = userRepository.findByName(name);
        if(user != null && BCrypt.checkpw(password, user.getPassword())) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            System.out.println(user.getName() + " has logged in!");
            response.setStatus(200);
        } else {
            System.out.println("Access denied!");
            response.setStatus(401);
        }
    }
}
