package LibraryManagementSystem.example.LibraryManagementSystem.Entities.Repoistry;

import LibraryManagementSystem.example.LibraryManagementSystem.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//
@Repository
public interface StudentRepoistry extends JpaRepository<Student,Integer> {

}
