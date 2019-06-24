package blogApp.database.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
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
