package step.learning.oop;

public abstract class Literature {
    private final String title;

    public Literature(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public abstract String getCard();
}
