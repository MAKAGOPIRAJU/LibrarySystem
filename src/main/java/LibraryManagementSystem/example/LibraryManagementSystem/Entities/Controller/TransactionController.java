package LibraryManagementSystem.example.LibraryManagementSystem.Entities.Controller;

import LibraryManagementSystem.example.LibraryManagementSystem.Entities.Service.TranscationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TranscationService transcationService;


    @PostMapping("/issueBook")
    public ResponseEntity bookIssued(@RequestParam ("bookId") Integer bookId,
                                    @RequestParam ("cardId") Integer cardId){
        try{
            String response = transcationService.bookIssued(bookId , cardId);
            return new ResponseEntity(response , HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity(e.getMessage() , HttpStatus.BAD_REQUEST);
        }

    }
}
