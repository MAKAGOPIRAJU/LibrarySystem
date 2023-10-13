package LibraryManagementSystem.example.LibraryManagementSystem.Entities.Service;

import LibraryManagementSystem.example.LibraryManagementSystem.Entities.Repoistry.StudentRepoistry;
import LibraryManagementSystem.example.LibraryManagementSystem.Entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import  java.util.*;
import java.util.Optional;

@Service
public class StudentSeriveLayer {
    @Autowired
    private StudentRepoistry studentRepoistry;

    public  String add(Student student) {
      // since the student repository layer was interface and it was extended from jpa repository layer
      // so all the methods were present insides the jpa repository can use in repository layer bcz it was interface
      // now all the List crud repository can used in jpa repository because it was interface of that then listCrud repository will use the all
      // methods in crud repository hence save was come from here
        studentRepoistry.save(student);

        return "Student add succesfully to Db!";
    }

    public  Student findStudent(Integer id) throws Exception{


        Optional<Student> student = studentRepoistry.findById(id); // there is a chance of the id was not present
        //inside the database in that case the jpa will get confused to avoid this
        // hence we are having two  choice the student will may exist or may not exist
        // this was optional case so we need to use optional student type

        if( !student.isPresent()) {
            throw  new Exception("the given id is invalid");
        }

        return student.get(); // if the value was exist from optional student type get the student
    }
    public Student find(Integer id) throws Exception {

        Optional<Student> student = studentRepoistry.findById(id);

        if(!student.isPresent()) {
            throw  new Exception("the given id is invalid");
        }
        return student.get();
    }

    public  List<Student> findAllStudents() {
        return  studentRepoistry.findAll();
    }
}
