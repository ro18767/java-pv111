package step.learning.oop;

import java.text.ParseException;

public class Journal extends Literature implements Copyable, Periodic {
    int number;
    public Journal(String title, int number) throws ParseException {
        super(title);
        if(number <= 0) {
            throw new NumberFormatException("Journal number must be positive") ;
        }
        this.number = number ;
    }
    public int getNumber() {
        return number;
    }


    @Override
    public String getCard() {
        return String.format( "Journal. Title: '%s'. Number: '%d'",
                super.getTitle(), this.getNumber() ) ;
    }

    @Override
    public String getPeriod() {
        return "Monthly";
    }
}
/*
Реалізувати сутність "Журнал" (номер, назва)
Додати до каталогу "ArgC & ArgV" номер 123
переконатись у виведенні всього каталогу
* перевірка номера на додатність

Д.З. Реалізувати сутність "Мапа" (назва, масштаб[1:100000, 1:20000])
Додати до каталогу мапу "Одеська область" з масштабом 1:50000
переконатись у виведенні всього каталогу
* перевірка масштабу на кратність 1000
 */
