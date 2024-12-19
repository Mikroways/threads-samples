puts "Ejemplo con Ruby version: #{RUBY_VERSION}"
100.times do
  Thread.new { sleep }
end

puts "El PID del proceso actual es: #{Process.pid}"
puts "Verificar con ps la cantidad de threads: ps -o nlwp -p #{Process.pid}"
puts "Ver los thread del proceso: ps -L -p #{Process.pid}"
sleep
