package step.learning.oop;

import java.text.ParseException;

public class PaperMap extends Literature implements Copyable {
    int scale;

    public PaperMap(String title, int scale) throws ParseException {
        super(title);
        if (scale <= 0) {
            throw new NumberFormatException("Journal number must be positive");
        }
        if ((scale % 1000) > 0) {
            throw new NumberFormatException("the scale must be a multiple of a 1000");
        }
        this.scale = scale;
    }

    public int getScale() {
        return scale;
    }


    @Override
    public String getCard() {
        return String.format("PaperMap. Title: '%s'. Number: '1:%d'",
                super.getTitle(), this.getScale());
    }

}
