// IoBound.java
import java.net.URL;
import java.net.URLConnection;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@SuppressWarnings("deprecation")
public class IoBound {
    public static String DELAYED_URL="https://httpbin.org/delay/10";
    public static void main(String[] args) {
        if (args.length != 1) usage("Bad number of arguments: "+args.length);
        
        if (args[0].equals("-t")) {
          int times = Runtime.getRuntime().availableProcessors();
          for (int i = 0; i < times; i++) {
              new Thread(() -> doIo()).start();
          }
          System.out.println("El PID del proceso actual es: " +
            ProcessHandle.current().pid());
          System.out.println("Verificar con ps la cantidad de threads:  ps -o nlwp -p " +
            ProcessHandle.current().pid());
          System.out.println("Ver los thread del proceso: ps -L -p " +
            ProcessHandle.current().pid());
          System.out.println("Ver en tiempo real los roceso:  watch -n 1 ps -L -o pid,lwp,state,ucmd -p " +
            ProcessHandle.current().pid());
        }else {
          if (args[0].equals("-s")) System.out.println(doIo());
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
    private static String doIo() {
      try {
        URL url = new URL(DELAYED_URL);
        URLConnection con = url.openConnection();
        InputStream in = con.getInputStream();
        String encoding = con.getContentEncoding();
        encoding = encoding == null ? "utf-8" : encoding;
        return new String(in.readAllBytes(), encoding);
      }catch(Exception e) {
        return e.toString();
      }
    }
}
