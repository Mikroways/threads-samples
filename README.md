# Threads una introducción pragmática

En este repositorio, dejamos ejemplos que son usados como complemento de una
capacitación acerca de cómo se manejan los threads en diferentes lenguajes.

La idea es comparar de qué forma se comportan los threads que programamos desde
la perspectiva del SO.

## Requerimientos

Los ejemplos pueden correrse en Linux y también con Linux en Windows usando WSDL.
Las herramientas que usamos, por el momento contempla los siguientes lenguajes:

* **Java:** oracle 20
* **Ruby:** 
    * MRI >= 3
    * Jruby 9.4.2.0
* **Python:**
    * CPython >= 3.10
    * NoGIL >= 3.9.10
* Docker y compose

## Instalando las herramientas

Las pruebas pueden realizarse instalando manualmente los interpretes,
compiladores y runtimes, o mediante [asdf](https://asdf-vm.com/). Se provee un
archivo `.tool-versions` con las versiones usadas.


## Ejemplos

* [**`00-100-threads/`**](./00-100-threads): ejemplo en diferentes lenguajes que
  instancian 100 threads y corroboran con el comando `ps` qué podemos ver desde
  el SO.
* [**`01-fibonnaci/`**](./01-fibonnaci): siempre calculará la la sucesión de
  Fibonacci hasta un número. Elegimos esta función porque está estrechamente
  ligada al uso de CPU. La idea es analizar el uso de la CPU con y sin threads.
* [**`02-io-bound/`**](./02-io-bound): en este caso se invocará una llamada a un
  servicio remoto. Este servicio demorará un tiempo que el proceso deberá
  esperar por IO. Entonces veremos cómo se comporan los threads acá.


## Métricas con prometheus

Además de los fuentes, se provee del stack que realizará scrapping de métricas
de la PC donde se corran los ejemplos usando
[node-exporter](https://github.com/prometheus/node_exporter) y
[prometheus](https://prometheus.io/).

Para iniciar el stack:

```bash
cd prometheus/
docker-compose up -d
```

> Con `docker-compose ps` podemos observar el estado de los contenedores


Aguardamos termine de iniciar los contenedores y podemos acceder a un explorador
de prometehus que mostrará gráficas de uso de la CPU accediendo al siguiente
enlace:

a [Acceso a
  Prometheus](http://localhost:9090/graph?g0.expr=100%20-%20(avg%20by%20(instance%2Ccpu)%20(rate(node_cpu_seconds_total%7Bjob%3D%22node%22%2Cmode%3D%22idle%22%7D%5B1m%5D))%20*%20100)&g0.tab=0&g0.display_mode=lines&g0.show_exemplars=0&g0.range_input=5m&g1.expr=100%20-%20(avg%20by%20(instance)%20(rate(node_cpu_seconds_total%7Bjob%3D%22node%22%2Cmode%3D%22idle%22%7D%5B1m%5D))%20*%20100)&g1.tab=0&g1.display_mode=lines&g1.show_exemplars=0&g1.range_input=5m&g2.expr=100%20-%20(avg%20by%20(instance)%20(rate(node_cpu_seconds_total%7Bjob%3D%22node%22%2Cmode!%3D%22iowait%22%7D%5B1m%5D))%20*%20100)&g2.tab=0&g2.display_mode=lines&g2.show_exemplars=0&g2.range_input=5m)

