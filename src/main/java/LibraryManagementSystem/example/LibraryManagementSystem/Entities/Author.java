package LibraryManagementSystem.example.LibraryManagementSystem.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Author")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Author {

// pk is AuthorId
// author name
// age
// rating

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer AuthorId;

private  String authorName;

private Integer age;

private  double rating;

//connect author and book
@OneToMany(mappedBy = "author" , cascade = CascadeType.ALL)
private List<Book> bookList = new ArrayList<>();// this represents the all the books of this author


}
