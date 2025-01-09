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

> Proveemos un Vagrantfile que crea una vm con todos los requisitos listos para
> trabajar

### Trabajando con vagrant

Simplemente tenés que tener instalado [vagrant](https://www.vagrantup.com/) y
[VirtualBox](https://www.virtualbox.org/). Luego simplemente corrés:

```bash
vagrant up
```

Y la vm se creará instantáneamente con todos los requisitos listos para empezar:

* La vm tendrá 2 vCPUs y 1GB de RAM
    * Podés modificarlo, dejamos comentado por si querés experimentar con más
      CPU y RAM.
* Te conectás a la vm usando `vagrant ssh`
* Accediendo a la carpeta `/vagrant` de la vm, estás entrando al directorio
  donde clonaste éste repositorio en tu PC, siendo una carpeta compartida. Al
  ingresar, verás un mensaje de la consola indicando que debés habilitar
  **direnv** usando  `direnv allow`, y a continuación las dependencias
  necesarias con `asdf direnv install`.

¡Listo! el ambiente estará listo para comenzar tus pruebas

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
  thread que haec uso intenso de escrituras al disco. Esto generará la espera de
  IO permitiéndonos ver cómo se comportan los threads acá.
* [**`03-thread-inseguro/`**](./03-thread-inseguro): mostramos código no seguro
  respecto de la concurrencia, que funciona gracias a GIL, pero no cuando
  tenemos threads nativos. Es interesante ver como funcionan los ejemplos de
  fibonnaci con estos cambios.

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
  Prometheus](http://localhost:9090/query?g0.expr=100+-+%28avg+by+%28instance%2Ccpu%29+%28rate%28node_cpu_seconds_total%7Bjob%3D%22node%22%2Cmode%3D%22idle%22%7D%5B1m%5D%29%29+*+100%29&g0.show_tree=0&g0.tab=graph&g0.range_input=5m&g0.res_type=auto&g0.res_density=medium&g0.display_mode=lines&g0.show_exemplars=0&g1.expr=avg+by+%28instance%2Ccpu%29+%28rate%28node_cpu_seconds_total%7Bjob%3D%22node%22%2Cmode%3D%22iowait%22%7D%5B1m%5D%29%29+*+100&g1.show_tree=0&g1.tab=graph&g1.range_input=5m&g1.res_type=auto&g1.res_density=medium&g1.display_mode=lines&g1.show_exemplars=0)

El explorador de prometheus, nos permite ejecutar con el botón **Execute** la
query en lenguaje [promQL](https://prometheus.io/docs/prometheus/latest/querying/basics/).
En este link hay dos queries:

* La de arriba muestra el uso real de la CPU, que se calcula como la suma de
  todas las métricas disponibles de la CPU, menos la que contabiliza el tiempo
  libre de CPU. Se muestra una gráfica para cada core de nuestra prueba.
* La de abajo muestra el uso de iowait de cada CPU únicamente.
