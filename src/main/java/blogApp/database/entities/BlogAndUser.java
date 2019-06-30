package blogApp.database.entities;

import java.time.LocalDate;

public class BlogAndUser {

    private String blogText;
    private LocalDate blogDate;
    private String username;
    private int userId;

    public BlogAndUser(String blogText, LocalDate blogDate, String username, int userId) {
        this.blogText = blogText;
        this.blogDate = blogDate;
        this.username = username;
        this.userId = userId;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
