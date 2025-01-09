import sys
import os
import random
import threading

FILE_SIZE_GB = 1.5
TMP_FILE = "/tmp/io-bound-python"

def usage(err):
    print(f"Crea archivos en {TMP_FILE}-XX.dat cada uno de {FILE_SIZE_GB}GB")
    print(">> ERROR: ",err)
    print("-t NUM\tTantos threads como se indique. Cada thread crea un archivo")
    sys.exit(1)

def do_io(num):
    filename = f"{TMP_FILE}-{num}.dat"
    file_size_bytes = int(FILE_SIZE_GB * 1024 * 1024 * 1024)
    with open(filename, 'wb') as f:
        for _ in range(file_size_bytes // 1024):  # Escribir bloques de 1 KB
            f.write(random.randbytes(1024))

if len(sys.argv) != 3 :
    usage("Faltan argumentos") 

elif sys.argv[1] == "-t":
    num = int(sys.argv[2])
    thread=0
    for i in range(num):
        threading.Thread(target=do_io, args=([i])).start()
    print("El PID del proceso actual es: ", os.getpid())
    print("Verificar con ps la cantidad de threads: ps -o nlwp -p ",
            os.getpid())
    print("Ver los thread del proceso: ps -L -p ", os.getpid())
    print("Ver en tiempo real los roceso:  watch -n 1 ps -L -o pid,lwp,state,ucmd -p ",
            os.getpid())
else:
    usage("Bad argument")
