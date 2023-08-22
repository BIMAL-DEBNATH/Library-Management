package com.example.LibraryManagement.Controller;

import com.example.LibraryManagement.Entities.Student;
import com.example.LibraryManagement.Enums.CardStatus;
import com.example.LibraryManagement.Enums.Department;
import com.example.LibraryManagement.Service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody Student student){
        try {
            String result= studentService.addStudent(student);
            return new ResponseEntity(result, HttpStatus.CREATED);
        }catch (Exception e){
            log.error("Student not added successfully {}",e.getMessage());
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }


    @GetMapping("/get")
    public ResponseEntity getDepartmentById(@RequestParam("id") Integer studentID){

        try {
            Department result=studentService.getDepartmentById(studentID);
            return new ResponseEntity<>(result,HttpStatus.FOUND);

        }catch (Exception e){

            log.error("Id is invalid!!{}",e.getMessage());
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/getCard")

    public CardStatus getCardStatusByStudentId(@RequestParam("id")Integer studentId){
        return studentService.getCardStatusByStudentId(studentId);
    }

}
