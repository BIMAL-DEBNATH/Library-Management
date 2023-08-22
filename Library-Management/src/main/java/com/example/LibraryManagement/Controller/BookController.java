package com.example.LibraryManagement.Controller;

import com.example.LibraryManagement.Entities.Book;
import com.example.LibraryManagement.Enums.Genre;
import com.example.LibraryManagement.RequestDTO.AddBookRequestDto;
import com.example.LibraryManagement.ResponseDTO.BookResponseDTO;
import com.example.LibraryManagement.Service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping("/add")

    public ResponseEntity<String> associateBookToAuthor(@RequestBody AddBookRequestDto request){

        try{
            String res=bookService.associateBookToAuthor(request);
            return new ResponseEntity<>(res, HttpStatus.CREATED);
        }catch (Exception e){

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping("/getByGenre")
    public ResponseEntity getBooksByGenre(@RequestParam("genre") Genre genre){

        List<BookResponseDTO>res=bookService.findBooksByGenre(genre);

        return new ResponseEntity(res,HttpStatus.OK);

    }

    @GetMapping("ByGenre")
    public int countOfBooksInGenre(@RequestParam("genre")Genre genre){
        return bookService.countOfBooksInGenre(genre);
    }


}
