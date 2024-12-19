import threading
import time
import sys
import os

SLEEP_TIME=60*60*24

print("Ejemplo con Python version: ", sys.version)

def thread_function():
    time.sleep(SLEEP_TIME)

for _ in range(100):
    threading.Thread(target=thread_function).start()

print("El PID del proceso actual es: ",os.getpid())
print("Verificar con ps la cantidad de threads:  ps -o nlwp -p ", os.getpid())
print("Ver los thread del proceso: ps -L -p ", os.getpid())
time.sleep(SLEEP_TIME)
