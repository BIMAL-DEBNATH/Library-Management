package com.example.LibraryManagement.Service;

import com.example.LibraryManagement.Entities.Author;
import com.example.LibraryManagement.Entities.Book;
import com.example.LibraryManagement.Repository.AuthorRepository;
import com.example.LibraryManagement.Repository.StudentRepository;
import com.example.LibraryManagement.RequestDTO.UpdateNameAndPenNameRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;
    public String addAuthor(Author author) {

        if(author.getAuthor_Id()!=null){
            throw new RuntimeException("Author Id is you should not give");
        }

        authorRepository.save(author);
        return "Author has been added successfully added to Db";
    }

    public String updateAuthor(UpdateNameAndPenNameRequest request) throws Exception{

        if(!authorRepository.existsById(request.getAuthorID())){
            throw new RuntimeException("This author does not exist in db");
        }

        Optional<Author>optionalAuthor=authorRepository.findById(request.getAuthorID());

        Author author=optionalAuthor.get();

        author.setName(request.getNewName());
        author.setPenName(request.getNewPenName());

        authorRepository.save(author);

        return "Author has been updated successfully!";
    }


    public Author getAuthorById(Integer authorId) {

        return authorRepository.findById(authorId).get();
    }

    public List<String> getBooksNameListOfAuthor(Integer authorId) {

       Author author= authorRepository.findById(authorId).get();

       List<Book>bookList=author.getBookList();

       List<String>listOfBooks=new ArrayList<>();

       for(Book book:bookList){
           listOfBooks.add(book.getTitle());
       }

       return listOfBooks;

    }



}
