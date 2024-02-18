package step.learning.oop;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class OopDemo {
    private List<Literature> catalog = new ArrayList<>();

    public void run() {
        System.out.println("OOP");
        try {
            catalog.add(new Book("D. Knuth", "Art of Programming"));
            catalog.add(new OldBook("M. Twen", "Tom Soyer"));
            catalog.add(new Newspaper("Daily Mail", "2024-01-29"));
            catalog.add(new Journal( "ArgC & ArgV", 123));
            catalog.add(new PaperMap( "Одеська область" , 50000));
        }
        catch (Exception ex) {
            System.err.println( "Literature add error: " + ex.getMessage() ) ;
            return ;
        }
        System.out.println("--------- ALL ------------");
        for( Literature literature : catalog ) {
            System.out.println( literature.getCard() );
        }
        System.out.println("--------- COPYABLE ------------");
        printCopyable();
        System.out.println("--------- NON-COPYABLE ------------");
        printNonCopyable();
        System.out.println("--------- PERIODIC ------------");
        printPeriodic();
        System.out.println("--------- OLD ------------");
        printOld();
        System.out.println("--------- CardAddon ------------");
        printAddon();
    }

    private void printOld() {
        for( Literature literature : catalog ) {
            // Reflection (об'єктна рефлексія) - засоби "самоаналізу" об'єктів
            Class<?> type = literature.getClass() ;  // ~GetType(C#)
            if( type.isAnnotationPresent(Used.class) ) {   // ~typeof Type
                // System.out.println(literature.getCard());
                // шукаємо чи є метод, помічений як @CardMethod
                for(Method method : type.getDeclaredMethods()) {
                    if(method.isAnnotationPresent(CardMethod.class)) {
                        /* method - помічений як @CardMethod, треба його викликати
                        АЛЕ method - це не метод об'єкту literature, це
                        частина типу даних (type), яку має кожен об'єкт цього типу
                        */
                        try {
                            method.setAccessible(true);
                            System.out.println( method.invoke(literature) );
                        }
                        catch (IllegalAccessException | InvocationTargetException ex) {
                            System.err.println( ex.getMessage() );
                        }
                    }
                }
            }
        }
    }

    private void printAddon() {
        for( Literature literature : catalog ) {
            // Reflection (об'єктна рефлексія) - засоби "самоаналізу" об'єктів
            Class<?> type = literature.getClass() ;  // ~GetType(C#)
            for(Method method : type.getDeclaredMethods()) {
                if(method.isAnnotationPresent(CardAddon.class)) {
                    try {
                        method.setAccessible(true);
                        System.out.println( method.invoke(literature) );
                    }
                    catch (IllegalAccessException | InvocationTargetException ex) {
                        System.err.println( ex.getMessage() );
                    }
                }
            }
        }
    }
    private void printPeriodic() {
        for( Literature literature : catalog ) {
            if( literature instanceof Periodic ) {
                System.out.println(
                        ((Periodic) literature).getPeriod() +
                        ": " +
                        literature.getCard());
            }
        }
    }
    private void printCopyable() {
        for( Literature literature : catalog ) {
            if( literature instanceof Copyable ) {
                System.out.println(literature.getCard());
            }
        }
    }
    private void printNonCopyable() {
        for( Literature literature : catalog ) {
            if( ! ( literature instanceof Copyable ) ) {
                System.out.println(literature.getCard());
            }
        }
    }
}
/*
ООП: парадигма, згідно з якою програма - це сукупність об'єктів,
які взаємодіють один з одним
- інкапсуляція
- поліморфізм
- абстракція/спадкування

Приклад: бібліотека (ТЗ: оцифрувати бібліотеку)
- виділяємо сутності: книга, газета, журнал
- моделювання: книга(автор, назва), газета(дата, назва), журнал(номер, назва)
- абстрагування (групування, generalization):
                 Література(назва)     -- абстракція (групуючий термін)
                /       |        \
    книга(автор)   газета(дата)   журнал(номер)
- поведінка (які методи мають бути)
    getCard() - карточка для каталога
- поліморфізм - поєднання різних сутностей у єдину колекцію
 */
