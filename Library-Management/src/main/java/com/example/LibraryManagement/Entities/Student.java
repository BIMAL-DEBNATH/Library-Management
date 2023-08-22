package com.example.LibraryManagement.Entities;

import com.example.LibraryManagement.Enums.Department;
import com.example.LibraryManagement.Enums.Gender;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Student")
public class Student {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer rollNo;

    private String name;

    private Integer age;

    @Enumerated(value=EnumType.STRING)
    private Gender gender;

    @Enumerated(value=EnumType.STRING)
    private Department department;

    @Column(name = "Student_EmailId", unique = true)
    private String gmail;

    @OneToOne(mappedBy="student", cascade = CascadeType.ALL)
    private  LibraryCard libraryCard;

}
