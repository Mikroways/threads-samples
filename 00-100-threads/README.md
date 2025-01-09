# Qué hace este ejemplo

Este programa en diferentes lenguajes sólo crea threads que duermen. De esta
forma, podemos ver cómo analizar los threads desde el SO usando algunos
comandos.

## Un dato de color

El comando `ps` que cada script propone usar, podés combinarlo con la opción
`-o pid,lwp,psr,state,ucmd ` para evidenciar en qué CPU se asigna cada thread.

## Como se corren estos ejemplos

## Python

Corremos directamente con:

```bash
python 100-threads.py
```

## Ruby

Corremos directamente con:

```bash
ruby 100-threads.rb
```

## Java

Primero compilar:

```bash
javac J100Threads.java
```

Luego ejecutamos:

```bash
java J100Threads
```
