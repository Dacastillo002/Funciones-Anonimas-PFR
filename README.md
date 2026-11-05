# Funciones Anónimas – Programación Funcional

**Autor:** Daniel Castillo
**Materia:** Programación Funcional
**Lenguajes:** Scala, Java, Python

## Cómo ejecutar el código
(lo llenas al final)

## Parte 1 – Explorar: la programación funcional en el mundo real

### Caso 1: Apache Spark (procesamiento de datos masivos)

**Qué problema resuelve:** procesar conjuntos de datos enormes repartidos en
muchas computadoras (por ejemplo, analizar logs de millones de líneas).

**Lenguaje o herramienta:** Spark está escrito en Scala y ofrece APIs en
Scala, Java, Python y R.

**Ventaja del enfoque funcional:** en lugar de programar cada paso a mano,
uno le pasa funciones a operaciones como `filter`, `map` o `reduce` y Spark
se encarga de repartir ese trabajo entre las máquinas. Además, como las
funciones no modifican los datos originales ni tienen efectos secundarios,
es más fácil ejecutarlas en paralelo sin que se estorben entre ellas.

**Fuentes:**
- OpenSourceForU, "Scala: The powerhouse of Apache Spark" (2017):
  https://www.opensourceforu.com/2017/06/scala-powerhouse-apache-spark/
- Towards Data Science, artículo "Why Functional Programming?" sobre Spark:
  https://towardsdatascience.com/?p=169014
- Diapositivas "The Spark Framework" (CWI, 2018), con ejemplos de lambdas en Spark:
  https://event.cwi.nl/lsde/2018/slides/04-The%20Spark%20Framework.pdf

**Fragmento de código real:** ejemplo clásico de Spark para contar errores
en un log (aparece en el artículo original de Spark y en material de clase):

    val file = spark.textFile("hdfs://...")
    val errs = file.filter(_.contains("ERROR"))
    val ones = errs.map(_ => 1)
    val count = ones.reduce(_+_)

Qué hace: lee un archivo de log, se queda solo con las líneas que contienen
"ERROR" (`filter` con la función anónima `_.contains("ERROR")`), convierte
cada una en un 1 (`map` con `_ => 1`) y los suma (`reduce` con `_+_`).
Las tres operaciones reciben funciones anónimas.
Fuente: https://dmice.ohsu.edu/bedricks/courses/cs624/lecs/w2_lec2.pdf

### Caso 2: Twitter (servicios con millones de peticiones)

**Qué problema resuelve:** soportar un crecimiento enorme de usuarios y
peticiones. Twitter empezó con Ruby on Rails y empezó a tener problemas de
escalabilidad y concurrencia.

**Lenguaje o herramienta:** pasaron sus servicios al JVM y usaron Scala
(también Java). Crearon Finagle, una librería para servidores asíncronos
escrita en Scala.

**Ventaja del enfoque funcional:** según las fuentes, eligieron Scala por ser
un lenguaje de alto nivel, con tipado estático y funcional, que corre sobre
el JVM. El cambio de plataforma les dio una mejora de rendimiento de más de
10 veces y llegaron a manejar entre 10 000 y 20 000 peticiones por segundo
por servidor.
(Ojo: el motivo principal fue rendimiento y concurrencia; lo funcional fue
una parte de la decisión.)

**Fuentes:**
- InfoQ, "Scaling Twitter to New Peaks" (2013):
  https://www.infoq.com/news/2013/08/scaling-twitter/
- High Scalability, "Scaling Twitter: Making Twitter 10000 Percent Faster":
  https://highscalability.com/scaling-twitter-making-twitter-10000-percent-faster/
## Parte 2 – Entender: ¿qué es una función anónima?
## Parte 3 – Aplicar: tipos de problemas
## Parte 4 – Ampliar: cuatro operaciones nuevas
## Parte 5 – Comparar y concluir
## Referencias