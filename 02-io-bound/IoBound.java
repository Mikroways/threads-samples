// IoBound.java
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import java.nio.charset.StandardCharsets;

@SuppressWarnings("deprecation")
public class IoBound {
    public static double FILE_SIZE_GB = 1.5;
    public static String TMP_FILE = "/tmp/io-bound-java";
    public static void main(String[] args) {
        if (args.length != 2) usage("Bad number of arguments: "+args.length);
        
        if (args[0].equals("-t")) {
          int times = Integer.parseInt(args[1]);
          for (int i = 0; i < times; i++) {
              final int num = i;
              new Thread(() -> doIo(num)).start();
          }
          System.out.println("El PID del proceso actual es: " +
            ProcessHandle.current().pid());
          System.out.println("Verificar con ps la cantidad de threads:  ps -o nlwp -p " +
            ProcessHandle.current().pid());
          System.out.println("Ver los thread del proceso: ps -L -p " +
            ProcessHandle.current().pid());
          System.out.println("Ver en tiempo real los roceso:  watch -n 1 ps -L -o pid,lwp,state,ucmd -p " +
            ProcessHandle.current().pid());
        } else usage("Bad option: " + args[0]);
    }
    private static void usage(String str)
    {
      System.out.println("Crea archivos en "+ TMP_FILE +"-XX.dat cada uno de "+FILE_SIZE_GB+"GB");
      System.out.println(">> ERROR: "+ str);
      System.out.println("-t NUM\tTantos threads como se indique. Cada thread crea un archivo");
      System.exit(1);
    }
    private static void doIo(int num) {
      String fileName = TMP_FILE + "-" + num + ".dat";
      Random random = new Random();
      byte[] buffer = new byte[1024]; // Bloques de 1 KB
      int fileSizeBytes = new Double(FILE_SIZE_GB * 1024 * 1024).intValue();
      try {
        FileOutputStream fos = new FileOutputStream(fileName);
        for (int i = 0; i < fileSizeBytes; i++) {
            random.nextBytes(buffer);
            fos.write(buffer);
        }
      }catch(Exception e) {
        System.err.println("Error processing thread #"+num+": "+ e.toString());
      }
    }
}
