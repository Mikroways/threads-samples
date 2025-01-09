// Fibonacci.java

public class Fibonacci {
    public static void main(String[] args) {
        if (args.length != 1) usage("Bad number of arguments: "+args.length);
        
        if (args[0].equals("-t")) {
          int times = Runtime.getRuntime().availableProcessors();
          for (int i = 0; i < times; i++) {
              new Thread(() -> fib(50)).start();
          }
          System.out.println("El PID del proceso actual es: " +
            ProcessHandle.current().pid());
          System.out.println("Verificar con ps la cantidad de threads:  ps -o nlwp -p " +
            ProcessHandle.current().pid());
          System.out.println("Ver los thread del proceso: ps -L -p " +
            ProcessHandle.current().pid());
          System.out.println("Ver en tiempo real los roceso:  watch -n 1 ps -L -o pid,lwp,psr,state,ucmd -p " +
            ProcessHandle.current().pid());
        }else {
          if (args[0].equals("-s")) fib(50);
          else usage("Bad option: " + args[1]);
        }
    }
    private static void usage(String str)
    {
      System.out.println(">> ERROR: "+ str);
      System.out.println("-s\tSecuencial\n-t\tTantos threads como CPUs ("+
          Runtime.getRuntime().availableProcessors()+")");
      System.exit(1);
    }
    private static int fib(int n) {
        return n < 2 ? n : fib(n - 2) + fib(n - 1);
    }
}
