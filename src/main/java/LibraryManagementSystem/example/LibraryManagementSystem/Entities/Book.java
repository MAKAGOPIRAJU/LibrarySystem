package LibraryManagementSystem.example.LibraryManagementSystem.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Entity
@Table(name = "Book")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {

// bookId
// bookName
// price
// no of pages
// Genre
// rating

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Integer bookId;

 private String bookName;

 private  int price;

 private Integer noOfPages;

@Enumerated(value = EnumType.STRING)
 private Genre Genre;

 private  double rating;

 @ManyToOne
 @JoinColumn
 private Author author;

 //connect to transactions
 @OneToMany(mappedBy = "book" , cascade = CascadeType.ALL)
 List<Transcations> transcationsList = new ArrayList<>();
}
