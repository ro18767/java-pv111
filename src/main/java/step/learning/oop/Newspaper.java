package step.learning.oop;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Newspaper extends Literature implements Periodic {
    private static final SimpleDateFormat sqlFormat =
            new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat locFormat =
            new SimpleDateFormat("dd.MM.yyyy");

    private final Date date ;  // datetime

    public Newspaper(String title, String dateString) throws ParseException {
        super(title);
        this.date = sqlFormat.parse( dateString ) ;
    }

    public Date getDate() {
        return this.date ;
    }

    @Override
    public String getCard() {
        return String.format( "Newspaper. Title: '%s'. Date: '%s'",
                super.getTitle(), locFormat.format( this.getDate() ) ) ;
    }

    @Override
    public String getPeriod() {
        return "Daily";
    }
}
