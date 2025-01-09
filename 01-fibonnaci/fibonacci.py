import sys
import os
import threading

NUM=45

def usage(err):
    print(">> ERROR: ",err)
    print("-s\tSecuencial")
    print("-t\tTantos threads como CPUs (", os.cpu_count, ")")
    sys.exit(1)

def fib(n):
    return n if n < 2 else fib(n - 2) + fib(n - 1)



if len(sys.argv) != 2 :
    usage("Faltan argumentos") 

if sys.argv[1] == "-s":
    print("Calcularemos fib(",NUM,") de forma secuencial")
    fib(NUM)
elif sys.argv[1] == "-t":
    print("Calcularemos fib(",NUM,") en ",os.cpu_count()," threads")
    for _ in range(os.cpu_count()):
        threading.Thread(target=fib, args=(NUM,)).start()
    print("El PID del proceso actual es: ", os.getpid())
    print("Verificar con ps la cantidad de threads: ps -o nlwp -p ",
            os.getpid())
    print("Ver los thread del proceso: ps -L -p ", os.getpid())
    print("Ver en tiempo real los roceso:  watch -n 1 ps -L -o pid,lwp,psr,state,ucmd -p ",
            os.getpid())
else:
    usage("Bad argument")
