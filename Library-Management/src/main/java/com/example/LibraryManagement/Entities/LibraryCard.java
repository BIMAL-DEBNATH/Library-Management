package com.example.LibraryManagement.Entities;

import com.example.LibraryManagement.Enums.CardStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "LibraryCard")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LibraryCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cardNo;

    @Enumerated(value = EnumType.STRING)
    private CardStatus cardStatus;

    private Integer noOfBooksIssued;

    @OneToOne
    @JoinColumn
   // @PrimaryKeyJoinColumn(name = "RollNo")
    private Student student;


    @OneToMany(mappedBy = "libraryCard",cascade = CascadeType.ALL)
    private List<Transaction>transactionList=new ArrayList<>();
}
