import sys
import threading

counter = 0

def bad_done():
    global counter
    temp = counter
    if len(sys.argv) > 1:
        sys.stdout.write(".")
    temp = temp + 1
    counter = temp

for _ in range(5):
    threading.Thread(target=bad_done).start()

print("El resultado siempre debería ser 5, pero fue ", counter)
