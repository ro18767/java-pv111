package step.learning.oop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)   // доступна під час виконання
@Target(ElementType.TYPE)             // для використання з типами (класи, тощо)
public @interface Used {
}
