package LibraryManagementSystem.example.LibraryManagementSystem.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "libraryCard")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LibraryCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // the primary key handling will automatically
    // the above annotation will automatically generate the unique values for cardId etc: we can say autoincrement
     private  Integer cardId;
    @Enumerated(value = EnumType.STRING)
    // my sql will only understand the primitive data types to understand the enum datatype we use the above annotation
    private  CardStatus cardStatus;

    /*
     if the parent class author and child class was book so i need to create the foreign key in child class from many books have single author
     hence it will be many to one
     */
    private String name; // the name present on the card

    @OneToOne // it will implies which mapping between the child and parent
    @JoinColumn // here we need to connect the both the tables with the primary key in parent table(student) automatically the primary key of the
    //student table studentId will become the foreign key in LibraryCard by using the annotation
    private  Student student;//which table we would like to connect (which entity we would like to connect)


}
