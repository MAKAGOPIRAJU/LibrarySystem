package LibraryManagementSystem.example.LibraryManagementSystem.Entities.Service;

import LibraryManagementSystem.example.LibraryManagementSystem.Entities.CardStatus;
import LibraryManagementSystem.example.LibraryManagementSystem.Entities.LibraryCard;
import LibraryManagementSystem.example.LibraryManagementSystem.Entities.Repoistry.CardRepository;
import LibraryManagementSystem.example.LibraryManagementSystem.Entities.Repoistry.StudentRepoistry;
import LibraryManagementSystem.example.LibraryManagementSystem.Entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private StudentRepoistry studentRepoistry;

    public LibraryCard generateCard() { // creating the new blank cards

        LibraryCard libraryCard = new LibraryCard();

        // parameters present inside it
        // 1.cardId
        // 2.cardStatus
        // 3.name
        // 4.student_id foreign key

        // now i dont want to add all the parameters first i want to maintain set of cards

        // so cardId will automatically handled by @GenerateValue
        // give the cardStatus input

        libraryCard.setCardStatus(CardStatus.NEW);

       libraryCard =  cardRepository.save(libraryCard); // add this newly generated card into the database

        return  libraryCard;
    }

    public void  associatedWithPlainCard(Integer cardId , Integer studentId) {

        // 1 . update the attribute in libraryCard

        // in the library card we have
        /*
          1.cardId
          2.nameOnCard(student not attached anythin at)
          3.card Status
          4.student entity

         I need to associate the student whose id is studentId with this cardId card
         update the nameOnCard
         update cardStatus
         update student entity
         */

        // update the attributes in LibraryCard

        // first get the card by cardId and student by studentId
        Optional<LibraryCard> card = cardRepository.findById(cardId);
        Optional<Student>   studentObj = studentRepoistry.findById(studentId);

        LibraryCard libraryCard = card.get();
        Student student     = studentObj.get();

        //update the attributes
        libraryCard.setCardStatus(CardStatus.ACTIVE);
        libraryCard.setName(student.getName());
        libraryCard.setStudent(student);


      // 2. update the attributes in student because of its bidirectional mapping
        /*
         in the student we are having

         1.studentId
         2.name
         3.mobNo
         4.age
         5.emailId
         6.libraryCard

         so i need to update the student with studentId

         name  wont change
         mobNo wont change
         age wont change
         emailId wont change
         libraryCard was just get updated add to student entity
         */

        // update the student
        student.setLibraryCard(libraryCard);

        // so the both the libraryCard and student was get update i need to save them now
        //but due to bidirectional mapping the student will automatically saves the libraryCard
        //because in the student entity we have the casecade function so it will caseCade the all with child table

        studentRepoistry.save(student);


    }

}
