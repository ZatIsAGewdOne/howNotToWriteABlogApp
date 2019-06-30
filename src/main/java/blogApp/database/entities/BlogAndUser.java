package blogApp.database.entities;

import java.time.LocalDate;

public class BlogAndUser {

    private String blogText;
    private LocalDate blogDate;
    private User user;

    public BlogAndUser(String blogText, LocalDate blogDate, User user) {
        this.blogText = blogText;
        this.blogDate = blogDate;
        this.user = user;
    }

    public String getBlogText() {
        return blogText;
    }

    public void setBlogText(String blogText) {
        this.blogText = blogText;
    }

    public LocalDate getBlogDate() {
        return blogDate;
    }

    public void setBlogDate(LocalDate blogDate) {
        this.blogDate = blogDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
