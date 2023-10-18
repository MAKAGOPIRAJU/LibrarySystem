package LibraryManagementSystem.example.LibraryManagementSystem.Entities.Service;

import LibraryManagementSystem.example.LibraryManagementSystem.Entities.Author;
import LibraryManagementSystem.example.LibraryManagementSystem.Entities.Book;
import LibraryManagementSystem.example.LibraryManagementSystem.Entities.Repoistry.AuthorRepoistry;
import LibraryManagementSystem.example.LibraryManagementSystem.Entities.Repoistry.BookRepoistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepoistry bookRepoistry;
    @Autowired
    private AuthorRepoistry authorRepoistry;

    public String add(Book book, Integer id) throws Exception{

        Optional<Author> author = authorRepoistry.findById(id);

        if(!author.isPresent())  throw new Exception("Author Does not Exist");

        Author authorObj = author.get();
        book.setAuthor(authorObj);
        bookRepoistry.save(book);
        authorObj.getBookList().add(book);
        return "add the book  " + book.getBookName() + " written by " + authorObj.getAuthorName()+ " into to Db !";
    }


}
