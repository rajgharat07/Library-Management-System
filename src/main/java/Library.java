package main.java;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> getAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.isAvailable()) {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }

    public void borrowBook(Book book) {
        if (book.isAvailable()) {
            book.setBorrowed(true);
        } else {
            throw new IllegalStateException("Book is not available for borrowing.");
        }
    }

    public void returnBook(Book book) {
        book.setBorrowed(false);
    }

    public void showAvailableBooks() {
        System.out.println("Available Books:");
        for (Book book : books) {
            if (book.isAvailable()) {

                System.out.println(book.getTitle());
            }
        }
    }

    public void showBorrowedBooks() {
        System.out.println("Borrowed Books:");
        for (Book book : books) {
            if (!book.isAvailable()) {
                System.out.println(book.getTitle());
            }
        }
    }

    public Book findBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }
}