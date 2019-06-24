package blogApp.database.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "blogs", schema = "blog")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userId;
    private LocalDate blogDate;
    private String blogText;

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
