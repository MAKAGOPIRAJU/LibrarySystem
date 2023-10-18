package LibraryManagementSystem.example.LibraryManagementSystem.Entities.Service;


import LibraryManagementSystem.example.LibraryManagementSystem.Entities.Book;
import LibraryManagementSystem.example.LibraryManagementSystem.Entities.CardStatus;
import LibraryManagementSystem.example.LibraryManagementSystem.Entities.LibraryCard;
import LibraryManagementSystem.example.LibraryManagementSystem.Entities.Repoistry.BookRepoistry;
import LibraryManagementSystem.example.LibraryManagementSystem.Entities.Repoistry.CardRepository;
import LibraryManagementSystem.example.LibraryManagementSystem.Entities.Repoistry.TransactionRepository;
import LibraryManagementSystem.example.LibraryManagementSystem.Entities.Transcations;
import LibraryManagementSystem.example.LibraryManagementSystem.Enums.TransactionStatus;
import LibraryManagementSystem.example.LibraryManagementSystem.Exceptions.BookIsNotPresent;
import LibraryManagementSystem.example.LibraryManagementSystem.Exceptions.CardIsNotInValidCase;
import LibraryManagementSystem.example.LibraryManagementSystem.Exceptions.CardNotPresent;
import LibraryManagementSystem.example.LibraryManagementSystem.Exceptions.MaximumNoOfBooksExceeded;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TranscationService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private BookRepoistry bookRepoistry;
    @Autowired
    private CardRepository cardRepository;

    private  final int  maximumNoOfbooksLimit = 3;
    public String bookIssued(Integer bookId ,Integer cardId) throws Exception{


         // here we need to create the new transaction now
        Transcations transcation = new Transcations();
        transcation.setTransactionStatus(TransactionStatus.PENDING); //intially the transaction was pending

        // validations
        // does the book is availabe
        Optional<Book> optionalBook = bookRepoistry.findById(cardId);
        Book book = optionalBook.get();
        if(!(optionalBook.isPresent())) {
            throw new BookIsNotPresent("book is not present in the library");
        }

        // does the card is available
        Optional<LibraryCard> optionalLibraryCard = cardRepository.findById(cardId);
         LibraryCard libraryCard = optionalLibraryCard.get();
        if(!(optionalLibraryCard.isPresent())) {
            throw new CardNotPresent("card is not present in the library");
        }

        // if the card is present still there is a chance it was not in active state
        if(!(libraryCard.getCardStatus().equals(CardStatus.ACTIVE))){
            throw new CardIsNotInValidCase("the card is not in active state");
        }

        //now both the card and book is in valid state

        if(libraryCard.getNoOfBooksTaken() == maximumNoOfbooksLimit) {
            throw  new MaximumNoOfBooksExceeded("the student was already taken the " + maximumNoOfbooksLimit + " books");
        }

        //now we can say the transaction is valid
        transcation.setTransactionStatus(TransactionStatus.ISSUED);//book was issued now

        libraryCard.setNoOfBooksTaken(libraryCard.getNoOfBooksTaken() + 1);//update library card


        //connect the Entities now
        book.getTranscationsList().add(transcation);
        libraryCard.getTranscationsList().add(transcation);

        // save to the DB
        //here we are update the two things one is book and library card in the normal scenario we
        //need to add the parents due to bidirectional but here add one transaction is enough

        transactionRepository.save(transcation);

        return "the " + book.getBookName() + " issued to the " + libraryCard.getStudent().getName() + " successfully!";

    }
}
