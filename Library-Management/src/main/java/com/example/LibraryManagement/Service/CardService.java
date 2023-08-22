package com.example.LibraryManagement.Service;

import com.example.LibraryManagement.Entities.LibraryCard;
import com.example.LibraryManagement.Entities.Student;
import com.example.LibraryManagement.Repository.CardRepository;
import com.example.LibraryManagement.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardService {
    @Autowired
    CardRepository cardRepository;
    @Autowired
    StudentRepository studentRepository;
    public String addCard(LibraryCard card) {

        cardRepository.save(card);
        return "Library card added successfully!";
    }

    public String cardIssued(Integer cardId, Integer rollNo) throws Exception{


        if(!cardRepository.existsById(cardId)){
            throw new RuntimeException("This card id is not valid!");
        }

        if(!studentRepository.existsById(rollNo)){
            throw new RuntimeException("This student rollNo is not Valid!");
        }

        Optional<LibraryCard>optionalLibraryCard=cardRepository.findById(cardId);
        Optional<Student>optionalStudent=studentRepository.findById(rollNo);

        LibraryCard Card=optionalLibraryCard.get();
        Student student=optionalStudent.get();

           student.setLibraryCard(Card);
           Card.setStudent(student);

           studentRepository.save(student);

           return "Library card has been issued to the student";
    }
}
