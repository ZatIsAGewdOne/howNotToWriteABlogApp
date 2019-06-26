package blogApp.database.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "blogs", schema = "blog")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_Id")
    private int userId;

    @Column(name = "blog_date")
    private LocalDate blogDate;

    @Column(name = "blog_text")
    private String blogText;

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public int getUserId() {
        return userId;
    }

    public LocalDate getBlogDate() {
        return blogDate;
    }

    public String getBlogText() {
        return blogText;
    }
}
