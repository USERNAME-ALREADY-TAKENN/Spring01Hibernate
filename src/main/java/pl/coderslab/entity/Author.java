package pl.coderslab.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
@Getter
@Setter
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String firstName;
    String lastName;

    @ManyToMany(mappedBy = "authors")
    private List<Book> books = new ArrayList<>();
}
