package step.learning.oop;

@Used  // "наша" анотація - оголошена у пакеті
public class OldBook extends Book {  // залишаються інтерфейси базового класу
    public OldBook(String author, String title) {
        super(author, title);
    }

    @Override
    public String getCard() {
        return "Old" + super.getCard();
    }

    @CardMethod
    public String getOwnCard() {
        return "--Old" + super.getCard();
    }
}
/*
Оголосити класи OldMap (OldNewspaper) з анотацією @Used,
переконатись, що вона також потрапляє у відповідний цикл.
Створити метод, який виводить лише нові видання (старі
не виводяться)
* Створити анотацію @CardAddon, яка призначена для методу
  Реалізувати перевірку цієї анотації: якщо такий метод є,
  то окрім getCard() виводити додатково результат відповідного
  методу.
 */