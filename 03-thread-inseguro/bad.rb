
@counter = 0

5.times.map do
  Thread.new do
    temp = @counter
    print "." if ARGV.length > 0
    temp = temp + 1
    @counter = temp
  end
end.each(&:join)

puts "El resultado siempre debería ser 5, pero fue #{@counter}"
