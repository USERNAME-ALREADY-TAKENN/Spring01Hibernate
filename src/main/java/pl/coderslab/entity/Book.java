package pl.coderslab.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.*;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Size(min = 5)
    String title;

    @Min(1)
    @Max(10)
    int rating;

    @Size(max=600)
    @Column(columnDefinition = "TEXT")
    String description;

    @Min(1)
    int pages;

    @NotNull
    @ManyToOne
    Publisher publisher;

    @NotEmpty
    @ManyToMany
    List<Author> authors = new ArrayList<>();

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", rating=" + rating +
                ", description='" + description +
                '}';
    }
}
