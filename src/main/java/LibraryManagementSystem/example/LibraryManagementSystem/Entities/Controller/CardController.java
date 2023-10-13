package LibraryManagementSystem.example.LibraryManagementSystem.Entities.Controller;

import LibraryManagementSystem.example.LibraryManagementSystem.Entities.LibraryCard;
import LibraryManagementSystem.example.LibraryManagementSystem.Entities.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardController {

 @Autowired
    private CardService cardService;
@PostMapping("/generatePlainCard")
 public ResponseEntity generatingNewCards()  {

      LibraryCard card = cardService.generateCard();

     String response = "new Card is generated and it having cardId is " + card.getCardId();

     return new ResponseEntity(response , HttpStatus.OK);

 }

 @PostMapping("/studentAssociatedWithPlainCard")
    public  ResponseEntity associatedWithPlainCard(@RequestParam("cardId") Integer cardId,
                                                    @RequestParam("studentId") Integer studentId){

    String response = "the card with card number "+ cardId + " is associated with the student who is having the studentId as "+ studentId + " successfully";
    return new ResponseEntity(response , HttpStatus.OK);
 }
}
