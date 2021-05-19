package com.strore.book.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.strore.book.dao.Book;
import com.strore.book.dao.Users;
import com.strore.book.service.BookService;
import com.strore.book.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.management.ObjectName;
import java.util.List;
import java.util.Objects;

import static com.strore.book.controller.UsersController.getStringResponseEntity;

@Controller
@RequestMapping(path = "books")
public class BookController {

    @Autowired
    BookService bookService;


    ObjectMapper mapper = new ObjectMapper();

    @GetMapping(path = "/{id}")
    public ResponseEntity<String> getBookById(@PathVariable Long id) throws JsonProcessingException {
        Book book = bookService.findById(id);
        return getStringResponseEntity(Objects.nonNull(book), mapper.writeValueAsString(book), "not found book");
    }

    @GetMapping(path = "")
    public ResponseEntity<String> getBooks() throws JsonProcessingException {
        List<Book> books = bookService.findAll();
        return getStringResponseEntity(Objects.nonNull(books), mapper.writeValueAsString(books), "not found book");
    }

    @PostMapping(path = "")
    public ResponseEntity<String> addBook(@RequestBody Book book){
        try {
            bookService.createBook(book);
            return ResponseEntity.ok("success");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("has error");
        }
    }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable Long id){
        Book book = bookService.findById(id);
        if (Objects.nonNull(book)) {
            bookService.deleteBook(id);
            return ResponseEntity.ok("deleted");
        }
        return ResponseEntity.badRequest().body("not found");
    }


    @PutMapping(path = "/{id}")
    public ResponseEntity<String> changeBook(@PathVariable Long id,@RequestBody Book book){
        try {
            Book book1 = bookService.findById(id);
            if (Objects.isNull(book1)){
                return ResponseEntity.badRequest().body("user not found");
            }
            book.setId(id);
            book.setCreatedAtV2(book1.getCreatedAt());
            bookService.createBook(book);
            return ResponseEntity.ok("success");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("has error");
        }
    }


}
