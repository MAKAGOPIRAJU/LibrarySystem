package LibraryManagementSystem.example.LibraryManagementSystem.Entities.Controller;

import LibraryManagementSystem.example.LibraryManagementSystem.Entities.Book;
import LibraryManagementSystem.example.LibraryManagementSystem.Entities.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    //add the book associated with primary key
    @PostMapping("/addBook")
    public ResponseEntity addBook(@RequestBody Book book, @RequestParam("authorId") Integer authorId) throws Exception {

        try {
            String result = bookService.add(book, authorId);
            return new ResponseEntity(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    }


