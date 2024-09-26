package test.java;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import main.java.Book;
import main.java.Library;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class LibraryTest {
    private Library library;

    @BeforeEach
    public void setUp() {
        library = new Library();
    }

    @Test
    public void testAddBook() {
        Book book = new Book("978-3-16-148410-0", "The Great Gatsby", "F. Scott Fitzgerald", 1925);
        library.addBook(book);
        System.out.println("Book added: " + book);
        assertEquals(1, library.getAvailableBooks().size(), "Book was not added to the library.");
        System.out.println("Total available books: " + library.getAvailableBooks().size());
    }

    @Test
    public void testBorrowAvailableBook() {
        Book book = new Book("978-3-16-148410-0", "The Great Gatsby", "F. Scott Fitzgerald", 1925);
        library.addBook(book);
        System.out.println("Book added: " + book);
        library.borrowBook(book);
        System.out.println("Book borrowed: " + book);
        assertFalse(book.isAvailable(), "Book should not be available after being borrowed.");
        System.out.println("Book availability: " + book.isAvailable());
    }

    @Test
    public void testBorrowUnavailableBook() {
        Book book = new Book("978-3-16-148410-0", "The Great Gatsby", "F. Scott Fitzgerald", 1925);
        library.addBook(book);
        System.out.println("Book added: " + book);
        library.borrowBook(book); // First borrow
        System.out.println("First borrow successful: " + book);
        assertThrows(IllegalStateException.class, () -> library.borrowBook(book), "Borrowing an unavailable book should throw an exception.");
    }

    @Test
    public void testReturnBook() {
        Book book = new Book("978-3-16-148410-0", "The Great Gatsby", "F. Scott Fitzgerald", 1925);
        library.addBook(book);
        System.out.println("Book added: " + book);
        library.borrowBook(book);
        System.out.println("Book borrowed: " + book);
        library.returnBook(book);
        System.out.println("Book returned: " + book);
        assertTrue(book.isAvailable(), "Book should be available after being returned.");
        System.out.println("Book availability after return: " + book.isAvailable());
    }

    @Test
    public void testShowAvailableBooks() {
        // Capture the output of the showAvailableBooks method
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out; // Save the original System.out
        System.setOut(new PrintStream(outputStream)); // Redirect System.out to our output stream

        // Add some books to the library
        Book book1 = new Book("978-3-16-148410-0", "The Great Gatsby", "F. Scott Fitzgerald", 1925);
        Book book2 = new Book("978-1-56619-909-4", "To Kill a Mockingbird", "Harper Lee", 1960);
        Book book3 = new Book("978-0-7432-7356-5", "1984", "George Orwell", 1949);

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        // Borrow one book to make it unavailable
        library.borrowBook(book1);

        // Call the method to display available books
        library.showAvailableBooks();

        // Reset System.out back to original so further prints show on terminal
        System.setOut(originalOut);

        // Convert the captured output to a string
        String output = outputStream.toString();

        // Print the captured output to the terminal
        System.out.println("Captured Output:\n" + output);

        // Assert that the available books are displayed correctly
        assertTrue(output.contains("To Kill a Mockingbird"), "'To Kill a Mockingbird' should be in available books.");
        assertTrue(output.contains("1984"), "'1984' should be in available books.");
        assertFalse(output.contains("The Great Gatsby"), "'The Great Gatsby' should not be available.");
    }
}