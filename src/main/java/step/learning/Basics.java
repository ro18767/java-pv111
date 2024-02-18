package step.learning;

import java.util.*;

public class Basics {
    public void run() {
        System.out.println( "Java basics" );
        // Змінні та типи даних
        // є "прімітиви", які поводяться як значимі типи
        // всі інші типи даних - reference
        int x = 10 ;  // 32 біт
        Integer xx = 10 ;  // reference-аналог int
        // беззнакові типи - не передбачені
        byte b = 127;  // 8 біт
        b = -128;
        short s = 16000;  // 16 біт
        long lng = 10000000000L;

        float f = 0.1f;  // 32 біт
        double d = 1.23E-4;  // 64 біт

        char c = 'A';  // UTF-16
        Character chr = 'B';
        boolean bool = true;
        Boolean boolRef = false;

        System.out.println(x);
        System.out.printf("xx = %d, b = %d, s = %d, lng = %d, f = %e, d = %f %n",
                xx, b, s, lng, f, d);
        System.out.println(c + " " + chr.hashCode());
        System.out.println(bool + " " + boolRef);

        control();
        strings();
    }

    private void control() {
        // інструкції управління (розгалуження та цикли)
        int x = 10;
        if( x % 2 == 1 ) {
            System.out.println("x is odd");
        }
        else {
            System.out.println("x is even");
        }
        System.out.println( x % 2 == 0 ? "even" : "odd" );
        switch(x) {
            case 1 :
                System.out.println("one");
                break;
            default:
                System.out.println("other");
        }

        // Arrays and Collections
        int[] arr1 = {1,2,3,4,5};
        int[] arr2 = new int[3];
        for (int i = 0; i < 3; ++i) {
            arr2[i] = i;
        }
        for( int element : arr1 ) {  // foreach
            System.out.println(element);
        }
        int[][] arrs = new int[4][];
        for (int i = 0; i < 4; i++) {
            arrs[i] = new int[5];
            for (int j = 0; j < 5; j++) {
                arrs[i][j] = i * 10 + j + 11;
            }
        }
        System.out.println("---------------------------------------");
        for( int[] subarr : arrs ) {
            for( int element : subarr ) {
                System.out.print(element + " ");
            }
            System.out.println();
        }

        // List<int> list1 -- error - only reference in collections
        List<Integer> list1 ;        // List - interface
        list1 = new ArrayList<>();   // ArrayList - implementation
        list1 = new LinkedList<>();  // <> - 'diamond' operator
        for (int i = 0; i < 10; i++) {
            list1.add( i ) ;
        }
        System.out.println("-----------------------------");
        Map<String, String> dict = new HashMap<>();   // без збереження порядку додавання
        dict.put( "кіт",  "cat"  );
        dict.put( "пес",  "dog"  );
        dict.put( "птах", "bird" );
        dict.put( "риба", "fish" );
        for( String key : dict.keySet() ) {
            System.out.printf( "%s -> %s\n", key, dict.get(key) );
        }
    }

    private void strings() {
        // про рядки
        String str1 = "Hello";  // тільки ""
        String str2 = "Hello";  // pooling - об'єднання рядків однакового значення (не створюються копії)

        if(str1 == str2) {  // "Equal", значить str1 та str2 - посилання на один об'єкт
            System.out.println("Equal");
        }
        else {
            System.out.println("Not Equal");
        }

        String str3 = new String("Hello");  //
        String str4 = new String("Hello");  //

        if(str3 == str4) {  // "Not Equal" - демонстрація того, що порівняння - референсне
            System.out.println("Equal");   // рівними можуть бути тільки два посилання на один об'єкт
        }
        else {
            System.out.println("Not Equal");
        }

        if( str3.equals(str4) ) {  // порівняння за контентом
            System.out.println("Equal");
        }
        else {
            System.out.println("Not Equal");
        }
        // перевантаження операторів у Java відсутнє
    }
}
/*
Д.З. Встановити Java, засоби розробника, IDE
Створити проєкт за Maven архетипом quickstart
Налаштувати Git, опублікувати репозиторій
Прикласти посилання на репозиторій проєкту.
 */