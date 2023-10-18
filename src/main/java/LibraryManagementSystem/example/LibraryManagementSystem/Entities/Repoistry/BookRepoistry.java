package LibraryManagementSystem.example.LibraryManagementSystem.Entities.Repoistry;

import LibraryManagementSystem.example.LibraryManagementSystem.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepoistry extends JpaRepository<Book, Integer> {



}
