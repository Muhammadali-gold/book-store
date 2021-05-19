package com.strore.book.service;

import com.strore.book.dao.Book;
import com.strore.book.dao.Users;
import com.strore.book.repository.AddressRepository;
import com.strore.book.repository.BookRepository;
import com.strore.book.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BookService {

    @Autowired
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public void createBook(Book book) {
        bookRepository.save(book);
    }

    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public Book findById(Long userId){
        return bookRepository.findById(userId).orElse(null);
    }


    public void deleteBook(Long id){
        bookRepository.deleteById(id);
    }

}
