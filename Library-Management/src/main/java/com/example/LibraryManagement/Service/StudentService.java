package com.example.LibraryManagement.Service;

import com.example.LibraryManagement.Entities.LibraryCard;
import com.example.LibraryManagement.Entities.Student;
import com.example.LibraryManagement.Enums.CardStatus;
import com.example.LibraryManagement.Enums.Department;
import com.example.LibraryManagement.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    public String addStudent(Student student) throws Exception{

        if(student.getRollNo()!=null){
            throw new RuntimeException("Student rollNo should not give");
        }

        studentRepository.save(student);
        return "Student added successfully!!";
    }

    public Department getDepartmentById(Integer studentID) {
        Optional<Student>studentOptional=studentRepository.findById(studentID);

        if(!studentOptional.isPresent()){
            throw new RuntimeException("RollNo is not valid");
        }
        Student st=studentOptional.get();

        return st.getDepartment();

    }

    public CardStatus getCardStatusByStudentId(Integer studentId) {

        Student student=studentRepository.findById(studentId).get();

        LibraryCard libraryCard=student.getLibraryCard();
        CardStatus cardStatus=libraryCard.getCardStatus();
        return  cardStatus;

    }
}
