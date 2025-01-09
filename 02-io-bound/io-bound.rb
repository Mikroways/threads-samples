require 'securerandom'

FILE_SIZE_GB = 1.5
TMP_FILE = "/tmp/io-bound-ruby"

def usage(err)
  puts "Crea archivos en #{TMP_FILE}-XX.dat cada uno de #{FILE_SIZE_GB}GB"
  puts ">> ERROR: #{err}"
  puts "-t NUM\tTantos threads como se indique. Cada thread crea un archivo"
  exit 1
end

def do_io(num)
    filename = "#{TMP_FILE}-#{num}.dat"
    File.open(filename, 'wb') do |file|
      file_size_bytes = (FILE_SIZE_GB * 1024 * 1024).to_i
      file_size_bytes.times do
        file.write(SecureRandom.random_bytes(1024)) # Escribe bloques de 1 KB
      end
    end
end



usage("Faltan argumentos") if ARGV.length != 2
case ARGV.shift
  when "-t"
    count = ARGV.shift.to_i
    threads = []
    count.times do |i|
      threads << Thread.new { do_io(i) }
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

