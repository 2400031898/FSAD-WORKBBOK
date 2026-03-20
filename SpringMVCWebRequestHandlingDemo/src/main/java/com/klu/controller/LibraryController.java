package com.klu.controller;
import com.klu.model.Book;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
@RestController
public class LibraryController {
    private List<Book> bookList = new ArrayList<>();
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to Online Library System 📚";
    }
    @GetMapping("/count")
    public int getTotalBooks() {
        return bookList.size();
    }
    @GetMapping("/price")
    public double getSamplePrice() {
        return 499.99;
    }
    @GetMapping("/books")
    public List<String> getBookTitles() {
        List<String> titles = new ArrayList<>();
        for (Book b : bookList) {
            titles.add(b.getTitle());
        }
        return titles;
    }
    @GetMapping("/books/{id}")
    public Object getBookById(@PathVariable int id) {
        for (Book b : bookList) {
            if (b.getId() == id) {
                return b;
            }
        }
        return "Book not found";
    }
    @GetMapping("/search")
    public String searchBook(@RequestParam String title) {
        return "You searched for book: " + title;
    }
    @GetMapping("/author/{name}")
    public String getAuthor(@PathVariable String name) {
        return "Books written by author: " + name;
    }
    @PostMapping("/addbook")
    public String addBook(@RequestBody Book book) {
        bookList.add(book);
        return "Book added successfully: " + book.getTitle();
    }
    @GetMapping("/viewbooks")
    public List<Book> viewBooks() {
        return bookList;
    }
}