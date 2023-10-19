package LibraryManagementSystem.example.LibraryManagementSystem.Entities.Controller;

import LibraryManagementSystem.example.LibraryManagementSystem.Entities.Author;
import LibraryManagementSystem.example.LibraryManagementSystem.Entities.Service.AuthorSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
   private AuthorSerivce authorSerivce;

    // add the author
     @PostMapping("/addAuthor")
    public ResponseEntity addAuthor(@RequestBody Author author) {

         String response = authorSerivce.addAuthor(author);

         return new ResponseEntity(response , HttpStatus.OK);
     }

    // find all author names
    @GetMapping("/findAllAuthors")
    public ResponseEntity allAuthors() {

       List<String> res = authorSerivce.allAuthors();

       return  new ResponseEntity( res , HttpStatus.OK);
    }
    // get author by Id
    @GetMapping("/findById")
    public ResponseEntity findAuthor( @RequestParam("authorId") Integer authorId) throws  Exception {

        Author author = authorSerivce.findAuthor(authorId);

         return new ResponseEntity( author ,HttpStatus.OK);
    }

}
