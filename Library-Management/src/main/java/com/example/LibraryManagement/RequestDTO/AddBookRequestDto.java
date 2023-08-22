package com.example.LibraryManagement.RequestDTO;

import com.example.LibraryManagement.Enums.Genre;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class AddBookRequestDto {
    private String title;
    private Boolean isAvailable;
    private Genre genre;
    private Date publicationDate;
    private Integer price;
    private Integer authorId;

}
