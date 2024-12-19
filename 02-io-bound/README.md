# Qué hace este ejemplo

Invocar al endpoint de httpbin que impone un delay:

```
https://httpbin.org/delay/HTTP_CALL_DELAY_SECONDS
```

El delay máximo es de 10 segundos. Lo interesante es que cuando el proceso
espera por la respuesta del servicio, queda en io wait. Veamos qué podemos
inferir ahora respecto a los threads.

## Como se corren estos ejemplos

## Python

Corremos directamente con:

```bash
python io-bound.py
```

## Ruby

Corremos directamente con:

```bash
ruby io-bound.rb
```

## Java

Primero compilar:

```bash
javac IoBound.java
```

Luego ejecutamos:

```bash
java IoBound
```

