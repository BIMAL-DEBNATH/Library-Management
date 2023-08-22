package com.example.LibraryManagement.Service;

import com.example.LibraryManagement.CustomException.BookNotAvailableException;
import com.example.LibraryManagement.CustomException.BookNotFoundException;
import com.example.LibraryManagement.Entities.Book;
import com.example.LibraryManagement.Entities.LibraryCard;
import com.example.LibraryManagement.Entities.Transaction;
import com.example.LibraryManagement.Enums.CardStatus;
import com.example.LibraryManagement.Enums.TransactionStatus;
import com.example.LibraryManagement.Enums.TransactionType;
import com.example.LibraryManagement.Repository.BookRepository;
import com.example.LibraryManagement.Repository.CardRepository;
import com.example.LibraryManagement.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {
    @Autowired
    BookRepository bookRepository;

    @Value("${book.maxLimit}")
    private Integer maxBookList;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    TransactionRepository transactionRepository;
    public String issueABook(Integer bookId, Integer cardId) throws Exception{

        Transaction transaction=new Transaction(TransactionStatus.PENDING, TransactionType.ISSUED, 0);
        Optional<Book>optionalBook=bookRepository.findById(bookId);

        if(!optionalBook.isPresent()){
            throw new BookNotFoundException("Library does not contain this book");
        }

        Book book=optionalBook.get();

        if(!book.getIsAvailable()){
            throw new BookNotAvailableException("This book is not available currently!");
        }


        Optional<LibraryCard>optionalLibraryCard=cardRepository.findById(cardId);

        if(!optionalLibraryCard.isPresent()){
            throw new Exception("This LibraryCard is not Found");
        }

        LibraryCard libraryCard=optionalLibraryCard.get();

        if(!libraryCard.getCardStatus().equals(CardStatus.ACTIVE)){ //
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("This LibraryCard is not Active");
        }

        if(libraryCard.getNoOfBooksIssued()>=maxBookList){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new RuntimeException("You have already taken maximum books");
        }

        /*All the failed and invalid cases are over*/

        transaction.setTransactionStatus(TransactionStatus.SUCCESS);

        book.setIsAvailable(false);
        libraryCard.setNoOfBooksIssued(libraryCard.getNoOfBooksIssued()+1);


        transaction.setBook(book);
        transaction.setLibraryCard(libraryCard);

        Transaction newTransactionWithId=transactionRepository.save(transaction);

        book.getTransactionList().add(newTransactionWithId);
        libraryCard.getTransactionList().add(newTransactionWithId);

           /*since transaction child is already save , not mandatory to save book and child (casecade.all)*/
        bookRepository.save(book);
        cardRepository.save(libraryCard);

        return "Transaction has been save successfully!";
    }


    public String returnBook(Integer cardId, Integer bookId){

        Book book=bookRepository.findById(bookId).get();
        LibraryCard card=cardRepository.findById(cardId).get();


        List<Transaction>transactionList=transactionRepository.findTransactionsByBookAndLibraryCardAndTransactionStatusAndTransactionType
                (book,card,TransactionStatus.SUCCESS,TransactionType.ISSUED);

       Transaction latestTransaction=transactionList.get(transactionList.size()-1);

       Date isuueDate=latestTransaction.getCreatedAt();

       long milliSecondTime=Math.abs(System.currentTimeMillis()-isuueDate.getTime());

       long no_of_days_issue= TimeUnit.DAYS.convert(milliSecondTime,TimeUnit.MILLISECONDS);


       int findAmount=0;

       if(no_of_days_issue>15){
           findAmount=(int)((no_of_days_issue-15)*3);
       }

        book.setIsAvailable(Boolean.TRUE);
        card.setNoOfBooksIssued(card.getNoOfBooksIssued()-1);

       Transaction transaction=new Transaction(TransactionStatus.SUCCESS,TransactionType.RETURN,findAmount);
       transaction.setBook(book);
       transaction.setLibraryCard(card);

        Transaction newTransactionWithId=transactionRepository.save(transaction);

        book.getTransactionList().add(newTransactionWithId);
        card.getTransactionList().add(newTransactionWithId);

        //saving the parents

        bookRepository.save(book);
        cardRepository.save(card);

        return "Book has successfully been issued !";








    }



}
