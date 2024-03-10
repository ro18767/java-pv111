package step.learning.db;

import com.google.inject.Inject;
import step.learning.services.rnd.CodeGenerator;

import java.sql.*;
import java.util.Random;

public class DbDemo {
    private final Connection connection;
    private final CodeGenerator codeGenerator ;
    private final Random random ;


    @Inject
    public DbDemo(Connection connection, CodeGenerator codeGenerator, Random random) {
        this.connection = connection;
        this.codeGenerator = codeGenerator;
        this.random = random;
    }

    private void createTable() {
        String sql = "CREATE TABLE  IF NOT EXISTS  randoms (" +
                "id  BIGINT UNSIGNED  PRIMARY KEY  DEFAULT ( UUID_SHORT() )," +
                "rand_int  INT," +
                "rand_str  VARCHAR(64)" +
                ") ENGINE = InnoDB, DEFAULT CHARSET = utf8mb4";
        try( Statement statement = connection.createStatement() ) {
            statement.executeUpdate( sql ) ;
            System.out.println( "Create OK" );
        }
        catch( SQLException ex ) {
            System.err.println( ex.getMessage() );
        }
    }

    private void addRandom() {
        /*
        Підготовлені запити - з розділеними етапами компіляції запиту та
        передачі параметрів. Тимчасова збережна SQL-процедура, яка зникає
        при закритті підключення.
        Вживаються для
         - повторних запитів з різними параметрами (по днях, по місяцях тощо)
         - захисту від інжекцій при наявності даних ненадійного походження
         */
        String sql = "INSERT INTO randoms(rand_int, rand_str) VALUES (?, UUID())";
        try( PreparedStatement prep = connection.prepareStatement( sql ) ) {
            prep.setInt( 1, random.nextInt() ) ;  // 1й знак "?"
            prep.executeUpdate();
            // підготовлений запит можна виконувати кількаразово
            prep.setInt( 1, random.nextInt() ) ;  // 1й знак "?"
            prep.executeUpdate();
            System.out.println( "Insert OK" ) ;
        }
        catch( SQLException ex ) {
            System.err.println( ex.getMessage() );
        }
    }

    private void printRandoms() {
        String sql = "SELECT * FROM randoms" ;
        try( Statement statement = connection.createStatement();
             ResultSet res = statement.executeQuery( sql ) ) {
            while( res.next() ) {
                System.out.printf( "%d  %d  %s\n",
                        res.getLong("id"),
                        res.getInt("rand_int"),
                        res.getString("rand_str")
                );
            }
        }
        catch( SQLException ex ) {
            System.err.println( ex.getMessage() );
        }
    }

    public void run() {
        if( connection != null ) {
            System.out.println( "Connection OK" ) ;
            // createTable();
            addRandom();
            printRandoms();
        }
    }
}
/*
Д.З. написати процедуру додавання до таблиці колонки
зі значенням UUID() - повний формат.
Модифікувати процедури додавання та виведення даних
з урахуванням появи нової колонки.
Додати скріншоти результатів роботи.
 */
/*
Робота з базами даних
1. Підготовка.
- встановлюємо СУБД Oracle MariaDB (окремо або у збірці на кшталт XAMPP)
- створюємо окрему БД та користувача для неї:
 = запускаємо термінал СУБД
 = створення БД
    > CREATE DATABASE java_111 ;
 = створюємо нового користувача i задаємо пароль
    > CREATE USER 'user_111'@'localhost' IDENTIFIED BY 'pass_111' ;
 = видаємо права на БД java_111
    > GRANT ALL PRIVILEGES ON java_111.* TO 'user_111'@'localhost' ;
 == у сучасних СУБД дозволяється єдина команда
      GRANT ALL PRIVILEGES ON java_111.*         --  надання прав
      TO 'user_111'@'localhost'                  --  створення користувача
      IDENTIFIED BY 'pass_111' ;                 --  встановлення паролю
- додаємо до проєкту залежність - драйвер (конектор) БД
  (https://mvnrepository.com/artifact/com.mysql/mysql-connector-j)
2. Створення підключення
- Рекомендовано через IoC
 = реєстрація драйвера
 = відкриття підключення
 = при завершенні - зворотні дії (закриття/дереєстрація)
3. Виконання запитів

 */