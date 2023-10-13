package LibraryManagementSystem.example.LibraryManagementSystem.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;

@Entity
// this class defines the schema of the tables
//we can also say it refers the present class as tables or present class will stored in tables in the database
@Table(name = "student")//naming the table
//by default the table will come as class name

@Getter // by using the lombok library we can directly get this getters
@Setter // by using the lombok library we can directly get this setters

@AllArgsConstructor // create the constructor with all args
@NoArgsConstructor  // create the constructor empty constructor

public class Student {

    @Id // it implies the studentId is the primary key
    private  Integer studentId;

    private String name;

    private  String mobNo;

    private  int age;

    private  String emailId;

    //BIDIRECTIONAL MAPPING

    //step1 : which mapping
    //for each student there was at most one libraryCard only so one to one mapping

    @OneToOne(mappedBy = "student" , cascade = CascadeType.ALL) // mappedBy = "the fk in child variable name"
    // why cascade : all the operations will done in parent table will cascade to child table
    //cascade : the top to bottom operations
    private LibraryCard libraryCard;

}
