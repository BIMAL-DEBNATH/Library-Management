package com.example.LibraryManagement.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Authors")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer author_Id;

    private String name;

    @Column(unique = true)
    private String emailId;

    private Integer age;

    private String penName;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Book>bookList=new ArrayList<>();

}
