package com.example.LibraryManagement.RequestDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateNameAndPenNameRequest {
    private Integer authorID;
    private String newName;
    private String newPenName;
}
