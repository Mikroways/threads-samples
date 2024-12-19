// J100Threads
import java.io.*;

public class J100Threads {
    static final int SLEEP_TIME = 1000*60*60*24;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Ejemplo con Java versión: " +
            System.getProperty("java.version"));

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
              try {
              threadFunction();
              }catch (InterruptedException e) {  }
            }).start();
        }

        System.out.println("El PID del proceso actual es: " +
          ProcessHandle.current().pid());
        System.out.println("Verificar con ps la cantidad de threads:  ps -o nlwp -p " +
          ProcessHandle.current().pid());
        System.out.println("Ver los thread del proceso: ps -L -p " +
          ProcessHandle.current().pid());
        Thread.sleep(J100Threads.SLEEP_TIME);
    }
    private static void threadFunction() throws InterruptedException {
        Thread.sleep(J100Threads.SLEEP_TIME);
    }
}
