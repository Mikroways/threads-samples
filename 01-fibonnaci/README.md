# Qué hace este ejemplo

Calcular la sucesión de fibonnaci para numeros grandes, se vuelve intensa en
cómputo, y a veces en memoria si se usa recursión, por el tamaño del stack.

De esta forma, aprovechando el intenso cómputo, veremos en diferentes lenguajes
lo que demora calcular fibonnaci para un número alto en los diferentes
lenguajes:

* Sin threads con un único cálculo de fibonnaci hasta un N
* Con tantos threads como CPUs tiene la PC donde se corren los ejemplos, de
  forma de ver cómo funcionan los threads en cada lenguaje al calular M veces
  (con M siendo la cantidad de núcleos), la sucesión de fibonnaci hasta N.
    * Podemos considerar que si se paraleliza, sería c asi el mismo tiempo para
      1 que para M veces.

## Como se corren estos ejemplos

## Python

Corremos directamente con:

```bash
python fibonacci.py
```

## Ruby

Corremos directamente con:

```bash
bundle
ruby fibonacci.rb
```

## Java

Primero compilar:

```bash
javac Fibonacci.java
```

Luego ejecutamos:

```bash
java Fibonacci
```

