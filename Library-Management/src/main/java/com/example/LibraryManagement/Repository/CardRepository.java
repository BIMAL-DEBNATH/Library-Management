package com.example.LibraryManagement.Repository;

import com.example.LibraryManagement.Entities.LibraryCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<LibraryCard,Integer> {

}
