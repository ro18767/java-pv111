package step.learning.async;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class MultiTask {
    private final ExecutorService taskPool =
            Executors.newFixedThreadPool(3) ;
    public void demo() {
        // Continuations - нитки коду
        // task1.1 - task1.2  -- нитка (задачі, що мають йти послідовно)
        // task2.1 - task2.2  -- інша нитка, яка може йти паралельно з першою

        Supplier<String> supplier = () -> {   // ~ Callable
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Supplier data";
        };
        Consumer<String> consumer = (String supply) -> {
            System.out.println("Got: " + supply);
        };
        Function<String, String> transform = (source) -> {
            return "Processed " + source ;
        };
        Future<?> task1 = CompletableFuture
                .supplyAsync(supplier, taskPool)
                .thenApply(transform)
                .thenApply(transform)
                .thenAccept(consumer);
        try {
            task1.get(2, TimeUnit.SECONDS);
        }
        catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new RuntimeException(e);
        }
        taskPool.shutdown();
    }
    public void demo3() {
        long startTime = System.nanoTime();
        long tick = (long) 1e6;  // nano/milli
        try {
            Future<String> task1 = taskPool.submit( () -> {
                Thread.sleep(1000);
                return "Task 1 finishes";
            });
            Future<String> task2 = taskPool.submit( () -> {
                Thread.sleep(1000);
                return "Task 2 finishes";
            });

            String res1 = task1.get();   // ~ res1 = await  async(){...}
            System.out.printf( "%d ms: Task finishes with '%s'\n",
                    (System.nanoTime() - startTime) / tick,
                    res1 );

            String res2 = task2.get();
            System.out.printf( "%d ms: Task finishes with '%s'\n",
                    (System.nanoTime() - startTime) / tick,
                     res2);
        }
        catch (InterruptedException | ExecutionException e) {
            System.err.println(e.getMessage());
        }

        taskPool.shutdown();
        System.out.printf("%d ms: MultiTask finishes\n",
                (System.nanoTime() - startTime) / tick );
    }
    public void demo2() {
        // Callable - функціональний інтерфейс з методом, що повертає значення
        Future<String> task = taskPool.submit( () -> {
            try {
                Thread.sleep( 1000);
                return "Task finishes";
            }
            catch (InterruptedException e) {
                return "Sleeping interrupted";
            }
        } ) ;
        try {
            System.out.println(   // task.get() - блокує даний потік
                    task.get()    // (очікує завершення задачі)
            ) ;                   //  повертає результат задачі
        }
        catch (InterruptedException | ExecutionException e) {
            System.err.println(e.getMessage());
        }
        taskPool.shutdown();
    }
    public void demo1() {
        long startTime = System.nanoTime();
        long tick = (long) 1e6;  // nano/milli
        Runnable runnable = () -> {
            System.out.printf("%d ms: Task starts\n",
                    (System.nanoTime() - startTime) / tick );
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {
                System.err.println("Sleeping interrupted");
            }
            finally {
                System.out.printf("%d ms: Task finishes\n",
                        (System.nanoTime() - startTime) / tick );
            }
        };
        for (int i = 0; i < 10; i++) {
            taskPool.submit(runnable);
        }

        taskPool.shutdown();
        System.out.printf("%d ms: MultiTask finishes\n",
                (System.nanoTime() - startTime) / tick );
    }

    public void demoDz() {


        Function<Integer, Integer> transform = (source) -> {
            try {
                Thread.sleep(1000);
                return source;
            }
            catch (InterruptedException e) {
                System.err.println("Sleeping interrupted");
                return 0;
            }
            finally {
                System.out.println("transform task finishes " + source);
            }
        };

        AtomicReference<String> result = new AtomicReference<>("");

        Consumer<Integer> consumer = (source) -> {
            result.updateAndGet(val -> {
                String new_val = val + source;
                System.out.println("consumer task finishes " + new_val);
                return new_val;
            });

        };

        List<Future<?>> taskList = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            Future<?> task = CompletableFuture.completedFuture(i)
                    .thenApplyAsync(transform, taskPool)
                    .thenAccept(consumer);
            taskList.add(task);
        }

        for (Future<?> task : taskList) {
            try {
                task.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        taskPool.shutdown();


        System.out.println("MultiTask finishes");
    }
}
/*
Багатозадачність
Підхід до реалізації асинхронності з використанням об'єктів рівня мови
програмування або платформи.
Частіше за все, якщо дозволяє ОС, багатозадачність фактично реалізується
у вигляді багатопоточності.
Відмінними (від багатопоточності) є наступні риси:
- є пул потоків (pool, executor, worker, looper...), створення
   задачі - суть передача її до виконавця (поміщення у пул)
- задача починає виконуватись відразу при передачі у пул
- пул треба завершувати. без завершення програма зависає
- частіше за все, пул є обмеженим (за кількістю одночасних задач),
   задачі виконуються по мірі звільнення виконавців
- задачі можуть повертати дані

   -----1s-----              -----1s-----
task1=...  task1.get()   task2=...  task2.get()   // await call1() await call2()

task1=... task2=... task1.get() task2.get()
    -------------1s--------
             -------------1s----------


var f = func()  ?? String / Task<String> ?

Д.З. Виконати попереднє Д.З. (pandigital)
з використанням засобів багатозадачності.
 */