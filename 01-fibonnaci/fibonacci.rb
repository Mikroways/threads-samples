require 'parallel'

NUM=45

def usage(err)
  puts ">> ERROR: #{err}"
  puts "-s\tSecuencial implementacion recursiva"
  puts "-so\tSecuencial implementacion iterativa"
  puts "-t\tTantos threads como CPUs (#{Parallel.processor_count})"
  puts "-to\tTantos threads coo CPUs (#{Parallel.processor_count}) iterativa"
  exit 1
end

def fib(n)
  return n if n < 2
  fib(n - 2) + fib(n - 1)
end

def fib_optimized(n)
  i1, i2 = 0, 1
  n.times do
    i1, i2 = i2, i1+i2
  end
  i1
end


usage("Faltan argumentos") if ARGV.length != 1
case ARGV.pop
  when "-s"
    puts "fibonacci(#{NUM}) = #{fib(NUM)}"
    exit 0
  when "-so"
    puts "fibonacci(#{NUM}) = #{fib_optimized(NUM)}"
    exit 0
  when "-t"
    doFib = Proc.new { fib NUM}
  when "-to"
    doFib = Proc.new { fib_optimized NUM}
  else
    usage("Bad argument")
    exit 1;
end

puts "El PID del proceso actual es: #{Process.pid}"
puts "Verificar con ps la cantidad de threads: ps -o nlwp -p #{Process.pid}"
puts "Ver los thread del proceso: ps -L -p #{Process.pid}"
puts "Ver en tiempo real los roceso:  watch -n 1 ps -L -o pid,lwp,psr,state,ucmd -p #{Process.pid}"

# Procedemos con threads
threads = []
Parallel.processor_count.times do 
  threads << Thread.new(&doFib)
end
threads.each &:join
