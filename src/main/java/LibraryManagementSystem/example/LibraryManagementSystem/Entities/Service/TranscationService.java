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

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class TranscationService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private BookRepoistry bookRepoistry;
    @Autowired
    private CardRepository cardRepository;

    private  final int  maximumNoOfbooksLimit = 4;
    private static final Integer FINE_PER_DAY = 5;
    public String bookIssued(Integer bookId ,Integer cardId) throws Exception{


         // here we need to create the new transaction now
        Transcations transcation = new Transcations();
        transcation.setTransactionStatus(TransactionStatus.PENDING); //intially the transaction was pending

        // validations
        // does the book is availabe
        Optional<Book> optionalBook = bookRepoistry.findById(bookId);
        if(!(optionalBook.isPresent())) {
            throw new BookIsNotPresent("book is not present in the library");
        }
        Book book = optionalBook.get();
        // does the card is available
        Optional<LibraryCard> optionalLibraryCard = cardRepository.findById(cardId);
        if(!(optionalLibraryCard.isPresent())) {
            throw new CardNotPresent("card is not present in the library");
        }
        LibraryCard libraryCard = optionalLibraryCard.get();

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
    public String returnBook(Integer bookId,Integer cardId){

        Book book = bookRepoistry.findById(bookId).get();
        LibraryCard card = cardRepository.findById(cardId).get();

        //I need to find out that issue Transaction

        Transcations transaction = transactionRepository.findTransactionByBookAndCardAndTransactionStatus(book,card,TransactionStatus.ISSUED);

       Date issuedDate = transaction.getCreatedOn();

        //Predefined method that you can use to calculate days
        long milliSeconds = Math.abs(System.currentTimeMillis()-issuedDate.getTime());
        long days = TimeUnit.DAYS.convert(milliSeconds,TimeUnit.MILLISECONDS);

        int fineAmount = 0;

        if(days>15){
            fineAmount = Math.toIntExact((days - 15) * FINE_PER_DAY);
        }

        Transcations newTransaction = new Transcations();

        newTransaction.setTransactionStatus(TransactionStatus.COMPLETED);
        newTransaction.setReturnDate(new Date());
        newTransaction.setFine(fineAmount);

        //Setting the FK's
        newTransaction.setBook(book);
        newTransaction.setLibraryCard(card);


        card.setNoOfBooksTaken(card.getNoOfBooksTaken() - 1);

        book.getTranscationsList().add(newTransaction);
        card.getTranscationsList().add(newTransaction);

        transactionRepository.save(newTransaction);

        return "book has been returned";

    }

}
