package LibraryManagementSystem.example.LibraryManagementSystem.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
@Entity
@Table(name = "Transcations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transcations {

    // transcation id primary key
    //issued date
    //return date
    //fine
    //createdOn
    //last modified on


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transcationId;

    private Date issuedDate;

    private Date returnDate;

    private Integer fine;

    @CreationTimestamp
    private Date createdOn;// the day of its created it was automatically handled by spring

    @UpdateTimestamp
    private Date lastModified;// the day of its last modified handled by spring automatically


    //connect to book
    @ManyToOne
    @JoinColumn
    private Book book;

    // connect the library-card
    // for each library-card there are lot of transcations so it was many to one from transca to card
    @ManyToOne
    @JoinColumn
    private LibraryCard libraryCard;






}
