package com.strore.book;

import com.strore.book.dao.Address;
import com.strore.book.dao.Book;
import com.strore.book.dao.Users;
import com.strore.book.service.AddressService;
import com.strore.book.service.BookService;
import com.strore.book.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class BookApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookApplication.class, args);
    }

    @Autowired
    UserService userService;

    @Autowired
    AddressService addressService;

    @Autowired
    BookService bookService;

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        System.out.println("hello world, I have just started up");

        Address address = new Address();
        address.setCity("Kiev");
        address.setHomeNumber("4");
        address.setStreet("Main Street");
        Address address1 = new Address();
        address1.setCity("Lviv");
        Users users = new Users();
        users.setAddress(address);
        users.setEmail("someEmail@gmail.com");
        users.setName("Smith");
        userService.createUsers(users);
        Users users1 = new Users();
        users1.setName("Jon Dorian");
        users1.setEmail("gmailEmail@gmail.com");
        users1.setAddress(address1);
        userService.createUsers(users1);

//        userService.findAll().forEach(System.out::println);
//
//        userService.findAllByName("Smith").forEach(System.out::println);
//
//        userService.findWhereEmailIsGmail().forEach(System.out::println);
//
//        userService.findWhereNameStartsFromSmith().forEach(System.out::println);


        Book book1 = new Book();

        book1.setName("Queen of Shadows");
        book1.setAuthor("Sarah J. Maas");
        book1.setPrice(9.98);
        book1.setQuantity(11);
        book1.setRating(4.54);

        Book book2 = new Book();

        book2.setName("The Return of the King");
        book2.setAuthor("J.R.R. Tolkien");
        book2.setPrice(9.99);
        book2.setQuantity(5);
        book2.setRating(4.53);

        Book book3 = new Book();

        book3.setName("Acheron");
        book3.setAuthor("Sherrilyn Kenyon");
        book3.setPrice(9.99);
        book3.setQuantity(8);
        book3.setRating(4.55);

        Book book4 = new Book();

        book4.setName("Queen of Shadows");
        book4.setAuthor(" Sarah J. Maas");
        book4.setPrice(9.98);
        book4.setQuantity(11);
        book4.setRating(4.54);

        bookService.createBook(book1);
        bookService.createBook(book2);
        bookService.createBook(book3);
        bookService.createBook(book4);

    }

}
