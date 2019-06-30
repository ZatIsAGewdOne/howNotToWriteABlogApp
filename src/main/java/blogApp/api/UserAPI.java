package blogApp.api;

import blogApp.database.entities.BlogAndUser;
import blogApp.database.entities.User;
import blogApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("user")
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserAPI {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "{id}")
    public User getUser(@PathVariable int id) {
        User user = userService.getUserById(id);
        if(user == null) {
            System.out.println("User not found!");
        }

        return user;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("create")
    public User createUser(@RequestBody User user) {
        User foundUser = userService.getUserByName(user.getUsername());
        if (foundUser != null) {
            System.out.println("Name already in use! Please try again!");
        }

        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12)));
        return userService.createUser(user);
    }

//    @PostMapping(value = "login")
//    public void login(HttpServletResponse response, @RequestParam String name, @RequestParam String password) throws Exception {
//        User user = userService.getUserByName(name);
//        if(user != null && BCrypt.checkpw(password, user.getPassword())) {
//            System.out.println(user.getUsername() + " has logged in!");
//            response.getWriter().println("<h1>Welcome, " + user.getUsername() + "! </h1>");
//            response.setStatus(200);
//        } else {
//            System.out.println("Access denied!");
//            response.getWriter().println("<h1>Access denied!</h1>");
//            response.setStatus(401);
//        }
//    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("blog")
    public List<BlogAndUser> callOwnBlogs(String username) {
        User user = userService.getUserByName(username);
        if(user == null) throw new UsernameNotFoundException("Access denied!");

        return userService.getAllBlogsByUser(user.getUsername());
    }
}
