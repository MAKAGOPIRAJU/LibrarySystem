package LibraryManagementSystem.example.LibraryManagementSystem.Entities.Service;

import LibraryManagementSystem.example.LibraryManagementSystem.Entities.Author;
import LibraryManagementSystem.example.LibraryManagementSystem.Entities.Repoistry.AuthorRepoistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorSerivce{

    @Autowired
    private AuthorRepoistry authorRepoistry;

    public  String addAuthor(Author author) {

        authorRepoistry.save(author);

        return "author with " +  author.getAuthorId() + " added successfully " ;
    }

    public List<String> allAuthors() {

        List<Author> list = authorRepoistry.findAll();

        List<String> li = new ArrayList<>();

        for( Author author : list) li.add(author.getAuthorName());

        return li;
    }

    public  Author findAuthor(Integer authorId) throws Exception{

        Optional<Author> author = authorRepoistry.findById(authorId);

        if(!author.isPresent()) {
           throw new Exception("Author doest not exist");
        }

        return author.get();
    }


}
