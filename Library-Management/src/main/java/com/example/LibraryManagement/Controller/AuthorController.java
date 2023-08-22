package com.example.LibraryManagement.Controller;

import com.example.LibraryManagement.Entities.Author;
import com.example.LibraryManagement.RequestDTO.UpdateNameAndPenNameRequest;
import com.example.LibraryManagement.Service.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
@Slf4j
public class AuthorController {
    @Autowired
    AuthorService authorService;

    @PostMapping("/add")
   public ResponseEntity<String> addAuthor(@RequestBody Author author) throws Exception{

        try{
            String result=authorService.addAuthor(author);
            return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
        }catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_GATEWAY);
        }
    }



    @PutMapping("/update")
    public String updateAuthor(@RequestBody UpdateNameAndPenNameRequest updateNameAndPenNameRequest){

        try {
            String res=authorService.updateAuthor(updateNameAndPenNameRequest);
            return res;
        }catch (Exception e){
            log.error("This author is not exist Db{}",e.getMessage());
            return e.getMessage();
        }

    }



    @GetMapping("/getById")

    public Author getAuthorById(@RequestParam("id") Integer authorId){
        return authorService.getAuthorById(authorId);
    }


    @GetMapping("/getBooksName")
    public List<String>getBooksNameListOfAuthor(@RequestParam("id")Integer authorId){

        return authorService.getBooksNameListOfAuthor(authorId);
    }

}
