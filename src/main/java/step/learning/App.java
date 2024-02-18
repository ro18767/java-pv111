package step.learning;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import step.learning.async.AsyncDemo;
import step.learning.db.DbDemo;
import step.learning.ioc.IocDemo;
import step.learning.ioc.ServiceModule;
import step.learning.oop.OopDemo;

import java.util.Scanner;

public class App
{

    public static Scanner scanner = new Scanner(System.in);
    public static void main( String[] args )
    {
        // System.out.println( "Hello World!" );
        // new Basics().run();
//         new FileIo().run();
         new OopDemo().run();
        // new AsyncDemo().run();
        // new DbDemo().run();

        // Injector injector = Guice.createInjector( new ServiceModule() ) ;   // модуль IoC
        // IocDemo instance = injector.getInstance( IocDemo.class ) ;  // заміна new - Object Resolve
        // instance.run();
//        try( ServiceModule services = new ServiceModule() ) {
//            Guice.createInjector( services ).getInstance( DbDemo.class ).run();
//        } catch (Exception ignored) {}

    }
}
/*
Java:
 - JRE (JVM) - середовище виконання
 - JDK - засоби розробника (~компілятор)
 - IDE - Intellij Idea (Apache NetBeans, Eclipse, ...)

= Мова транслюючого типу (видає проміжний код - файли .class)
= GL (покоління) 4, парадигма - ООП, типізація - статична
= Висока зворотня сумісність
= Прив'язка до файлової системи:
 - файли класів повинні називатись так само, як і самі класи (+.java)
  -> один файл - один клас (public)
 - пакети (package) - називають як папки, точка у назвах - підпапки
= Casing
 - Types: CapitalCamelCase
 - methods, vars: lowerCamelCase
 - const: PASCAL_CASE
 - packages: snake_case
 */
