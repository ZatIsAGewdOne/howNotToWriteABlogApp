package blogApp.database.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "blogs")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private User user;

    private LocalDate blogDate;

    private String blogText;

    public Blog() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBlogDate(LocalDate blogDate) {
        this.blogDate = blogDate;
    }

    public void setBlogText(String blogText) {
        this.blogText = blogText;
    }

    public int getId() {
        return id;
    }

    public LocalDate getBlogDate() {
        return blogDate;
    }

    public String getBlogText() {
        return blogText;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
