package com.example.LibraryManagement.Controller;

import com.example.LibraryManagement.Entities.LibraryCard;
import com.example.LibraryManagement.Service.CardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/Card")
public class CardController{
    @Autowired
    CardService cardService;
    @PostMapping("/add")
    public String addCard(@RequestBody LibraryCard card){
        return cardService.addCard(card);
    }

    @PutMapping("/issuedToStudent")
    public ResponseEntity cardIssued(@RequestParam("cardId")Integer cardId, @RequestParam("rollNo")Integer rollNo){

        try {
            String result= cardService.cardIssued(cardId,rollNo);

            return new ResponseEntity(result, HttpStatus.CREATED);

        }catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_GATEWAY);
        }
    }

}
