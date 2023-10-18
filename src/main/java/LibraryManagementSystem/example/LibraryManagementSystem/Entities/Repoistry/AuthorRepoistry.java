package LibraryManagementSystem.example.LibraryManagementSystem.Entities.Repoistry;

import LibraryManagementSystem.example.LibraryManagementSystem.Entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepoistry extends JpaRepository<Author, Integer> {

}
