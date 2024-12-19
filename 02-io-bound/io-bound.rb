require 'open-uri'
require 'parallel'

HTTP_CALL_DELAY_SECONDS=10

def usage(err)
  puts ">> ERROR: #{err}"
  puts "-s\tSecuencial"
  puts "-sm\tRepite #{Parallel.processor_count} el ejemplo secuencial"
  puts "-t\tTantos threads como CPUs (#{Parallel.processor_count})"
  exit 1
end

def do_io
  URI.open "https://httpbin.org/delay/#{HTTP_CALL_DELAY_SECONDS}" do |io|
    io.read
  end
end



usage("Faltan argumentos") if ARGV.length != 1
case ARGV.pop
  when "-s"
    puts do_io
    exit 0
  when "-sm"
    Parallel.processor_count.times { do_io }
    exit 0
  when "-t"
    threads = []
    Parallel.processor_count.times do 
      threads << Thread.new { do_io }
    end
    puts "El PID del proceso actual es: #{Process.pid}"
    puts "Verificar con ps la cantidad de threads: ps -o nlwp -p #{Process.pid}"
    puts "Ver los thread del proceso: ps -L -p #{Process.pid}"
    puts "Ver en tiempo real los roceso:  watch -n 1 ps -L -o pid,lwp,state,ucmd -p #{Process.pid}"
    threads.each &:join
  else
    usage("Bad argument")
    exit 1;
end

