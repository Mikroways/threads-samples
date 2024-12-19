import sys
import os
import urllib.request
import threading

HTTP_CALL_DELAY_SECONDS=10

def usage(err):
    print(">> ERROR: ",err)
    print("-s\tSecuencial")
    print("-sm\tRepite ",os.cpu_count," el ejemplo secuencial")
    print("-t\tTantos threads como CPUs (", os.cpu_count, ")")
    sys.exit(1)

def do_io():
    return urllib.request.urlopen(f"https://httpbin.org/delay/{HTTP_CALL_DELAY_SECONDS}").read()

if len(sys.argv) != 2 :
    usage("Faltan argumentos") 

if sys.argv[1] == "-s":
    print(do_io().decode('utf-8'))
elif sys.argv[1] == "-sm":
    for _ in range(os.cpu_count()):
        do_io()
elif sys.argv[1] == "-t":
    for _ in range(os.cpu_count()):
        threading.Thread(target=do_io).start()
    print("El PID del proceso actual es: ", os.getpid())
    print("Verificar con ps la cantidad de threads: ps -o nlwp -p ",
            os.getpid())
    print("Ver los thread del proceso: ps -L -p ", os.getpid())
    print("Ver en tiempo real los roceso:  watch -n 1 ps -L -o pid,lwp,state,ucmd -p ",
            os.getpid())
else:
    usage("Bad argument")
