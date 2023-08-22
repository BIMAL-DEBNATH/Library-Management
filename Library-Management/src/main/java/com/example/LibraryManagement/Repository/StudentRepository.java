package com.example.LibraryManagement.Repository;

import com.example.LibraryManagement.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {
}
