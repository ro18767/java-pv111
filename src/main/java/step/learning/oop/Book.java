package step.learning.oop;

public class Book extends Literature implements Copyable {
    private final String author;

    public Book(String author, String title) {
        super( title );
        this.author = author ;
    }

    public String getAuthor() {
        return author;
    }

    public String getCard() {
        return String.format( "Book. Author: '%s'. Title: '%s'",
                this.getAuthor(), super.getTitle() ) ;
    }
}
/*
    Literature[ -title +getTitle +setTitle +getCard ]
    Book[ [[Literature]] -author +getAuthor +setAuthor +getCard ]
 */
