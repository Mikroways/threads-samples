# ¿Qué vemos en este directorio?

Tartamos de evidenciar dos cosas:

1. ¿Cómo funcionan en versiones del lenguaje que no están atadas a las
   restricciones impuestas por GIL? Por ello, verás que hay link simbólicos a
   los ejemplos de **fibbonaci** e **io-bound** vistos anteriormente.
1. Por otro lado, analizar el código que usará una variable global dentro de un
   thread. Este código no es seguro porque justamente la forma en que se modifica
   la variable global, no utiliza un mutex. Ahora bien, como python y ruby
   utilizan GIL, con las virtuales de referencia para ambos lenguajes (MRI y
   CPython), todo funciona. ¿Qué pasará con implementaciones que quitan GIL?

## Instalando nuevas versiones de python y ruby

Te recomendamos usar asdf. Simplemente correr:

```bash
asdf install python nogil-3.9.10
asdf install ruby jruby-9.4.8.0
```

Cuando se desea swicthear entre uno y otro, en éste subdirectorio usar:

```bash
asdf local python xxxx
asdf local ruby yyyy
```

## Como se corren estos ejemplos

## Python

Primero verificamos la versión de python:

```bash
python --version
```

> Primero probamos con la versio n CPyhon. Luego instalar y probar con [Python
> nogil](https://github.com/colesbury/nogil)

Corremos directamente con:

```bash
python bad.py
```

> Repetir con ambas implementaciones de python para corroborar que con nogil no
> siempre de el resultado esperado

## Ruby

Corremos directamente con:

```bash
bundle
ruby bad.rb
```

Al igual que con python, la idea es ver como responde con MRI y jruby.

## ¿Y los ejemplos anteriores?

Simplemente correrlos como se indicaba en los directorios donde se definían, a
excepción de que en este caso deben correrse desde éste msimo directorio dado
que asdf y direnv consideran que en él, están las versiones modificadas de los
lenguajes a probar.
