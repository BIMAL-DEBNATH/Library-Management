package com.example.LibraryManagement.Repository;

import com.example.LibraryManagement.Entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author,Integer> {
   @Query(value = "select * from author where age>=:enterAge;",nativeQuery = true)
    List<Author>findAuthorGreaterThanAge(Integer enterAge); //search param

}
