package main.java;
public class Book {
    private String isbn;
    final String title;
    final String author;
    final int publicationYear;
    private boolean borrowed;

    public Book(String isbn, String title, String author, int publicationYear)
    {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.borrowed = false;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public boolean isAvailable() {
        return !borrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }

    public static void main(String []args)
    {
        Book obj = new Book("true" , "Avanger" , "anup" , 2019);
        String title = obj.getAuthor();
        System.out.println(title);


    }
}