package LibraryManagementSystem.example.LibraryManagementSystem.Entities.Repoistry;

import LibraryManagementSystem.example.LibraryManagementSystem.Entities.Transcations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transcations , Integer> {
}
