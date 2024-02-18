package step.learning.ioc;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import step.learning.services.hash.HashService;
import step.learning.services.rnd.CodeGenerator;

public class IocDemo {
    @Inject @Named("hash-128")
    private HashService hashServiceField ;

    private final HashService hashService ;
    private final CodeGenerator codeGenerator ;

    @Inject
    public IocDemo(@Named("hash-160") HashService hashService, CodeGenerator codeGenerator) {
        this.hashService = hashService;
        this.codeGenerator = codeGenerator;
    }

    public void run() {
        System.out.println("Inversion of Control");
        System.out.println( hashService.hash("123") );
        System.out.println( hashServiceField.hash("123") );
        System.out.println( codeGenerator.newCode(20) ) ;
        // System.out.println( hashService.hashCode() + " " + hashServiceField.hashCode() );
    }

}
/*
IoC - Inversion of Control - Інверсія управління
Схема організації роботи проєкту за якої задача
управління життєвим циклом об'єктів перекладається
на окремий модуль.
Споживачі об'єктів лише декларують залежності, але
самі об'єкти не створюють. IoC аналізує всі залежності,
будує дерево створення об'єктів, впроваджує об'єкти.
Як правило, модуль IoC є окремим рішенням,
наприклад, Google Guice

Д.З. IoC: Зробити додаткові реалізації сервісу CodeGenerator
- з розділенням груп цифр (після кожної четвертої цифри іде "-")
   (1654-7351-8935)
- з цифро-символьними елементами у верхньому реєстрі (W3KL7)
Впровадити залежності, перевірити працездатність (додати до ДЗ скріншоти)
 */