package com.example.LibraryManagement.Service;

import com.example.LibraryManagement.Entities.Author;
import com.example.LibraryManagement.Entities.Book;
import com.example.LibraryManagement.Enums.Genre;
import com.example.LibraryManagement.Repository.AuthorRepository;
import com.example.LibraryManagement.Repository.BookRepository;
import com.example.LibraryManagement.RequestDTO.AddBookRequestDto;
import com.example.LibraryManagement.ResponseDTO.BookResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class BookService {
    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;
    public String associateBookToAuthor(AddBookRequestDto request)throws Exception{

        Optional<Author>optionalAuthor=authorRepository.findById(request.getAuthorId());

        if(!optionalAuthor.isPresent()){
            throw new RuntimeException("this author does not valid");
        }

        Author author=optionalAuthor.get();

        Book book=new Book(request.getTitle(), request.getIsAvailable(),request.getGenre(),request.getPublicationDate(),request.getPrice());

        book.setAuthor(author);

        List<Book>list=author.getBookList();
        list.add(book);

        authorRepository.save(author);
        return "Book has been added in author";
    }


    public List<BookResponseDTO> findBooksByGenre(Genre genre){

        List<Book>bookList=bookRepository.findBooksByGenre(genre);

        List<BookResponseDTO>dtoList=new ArrayList<>();

        for(Book book:bookList){
            BookResponseDTO bookResponseDTO=new BookResponseDTO(book.getTitle(),book.getIsAvailable(),
                                           book.getGenre(),book.getPublicationDate(),book.getPrice(),
                                           book.getAuthor().getName());
            dtoList.add(bookResponseDTO);
        }

        return dtoList;
    }


    public int countOfBooksInGenre(Genre genre) {

        List<Book>bookList=bookRepository.findBooksByGenre(genre);
        return bookList.size();
    }

}
