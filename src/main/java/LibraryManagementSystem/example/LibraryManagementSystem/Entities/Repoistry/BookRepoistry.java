package LibraryManagementSystem.example.LibraryManagementSystem.Entities.Repoistry;

import LibraryManagementSystem.example.LibraryManagementSystem.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;



public interface BookRepoistry extends JpaRepository<Book, Integer> {
    Book findBookByBookName(String bookName);

}
