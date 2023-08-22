package com.example.LibraryManagement.Repository;

import com.example.LibraryManagement.Entities.Book;
import com.example.LibraryManagement.Entities.LibraryCard;
import com.example.LibraryManagement.Entities.Transaction;
import com.example.LibraryManagement.Enums.TransactionStatus;
import com.example.LibraryManagement.Enums.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
 List<Transaction>findTransactionsByBookAndLibraryCardAndTransactionStatusAndTransactionType(Book book,
 LibraryCard libraryCard, TransactionStatus transactionStatus, TransactionType transactionType);


}
