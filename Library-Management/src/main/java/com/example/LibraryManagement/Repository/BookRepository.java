package com.example.LibraryManagement.Repository;

import com.example.LibraryManagement.Entities.Book;
import com.example.LibraryManagement.Enums.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

  List<Book>findBooksByGenre(Genre genre);
  //this is like sql query;

  List<Book>findBooksByIsAvailableFalse();

}
