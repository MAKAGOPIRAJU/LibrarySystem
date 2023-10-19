package LibraryManagementSystem.example.LibraryManagementSystem.Entities.Controller;


import LibraryManagementSystem.example.LibraryManagementSystem.Entities.Service.StudentSeriveLayer;
import LibraryManagementSystem.example.LibraryManagementSystem.Entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController
@RequestMapping("/student")
public class StudentControll {
    @Autowired
    private StudentSeriveLayer studentSeriveLayer;

    @PostMapping("/add_student")

    public  String addStudent(@RequestBody Student student){


        return studentSeriveLayer.add(student);
    }


     // check the student is exist or not

    @GetMapping("/findStudent")

    public  Student findStudent(@RequestParam ("id") Integer id) throws  Exception{

        try {
            return  studentSeriveLayer.findStudent(id); // if there is no exception
        }
        catch (Exception e) {
            return studentSeriveLayer.findStudent(id);//if there is a exception
        }
    }

    @GetMapping("/findId/{id}") // always using this way to get the exception messages properly avoid the above method

    public ResponseEntity find(@PathVariable ("id") Integer id) throws Exception {

        //WHY RESPONSEENTITY :
        // here i am not mentioning any data type hence it will take any return type

        try {
            Student student = studentSeriveLayer.find(id);
            return  new ResponseEntity(student , HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity(e.getMessage() , HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findAllTheStudents")

    public  ResponseEntity findAll() {

        List<Student> list = studentSeriveLayer.findAllStudents();

        if(list.size() == 0) {
            return  new ResponseEntity("no users are Exist" , HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(list , HttpStatus.OK);
    }


}
