# Funciones Anónimas – Programación Funcional

**Autor:** Daniel Castillo
**Materia:** Programación Funcional
**Lenguajes:** Scala, Java, Python

## Cómo ejecutar el código


## Parte 1 – Explorar: la programación funcional en el mundo real
## Parte 1 – Explorar: la programación funcional en el mundo real

### ¿Dónde y para qué se usa la programación funcional fuera del aula?

Se usa sobre todo donde hay muchísimos datos o muchísimas peticiones al mismo tiempo: en el procesamiento de datos masivos (como Apache Spark) y en servicios con mucho tráfico (como los de Twitter). Sirve porque las funciones no modifican los datos originales, lo que facilita ejecutarlas en paralelo, y porque el código queda más corto: uno dice qué quiere hacer (filtrar, transformar) y no cómo recorrer los datos.

### Caso 1: Apache Spark

**Qué problema resuelve:** procesar conjuntos de datos enormes repartidos en muchas computadoras, por ejemplo analizar logs de millones de líneas.

**Lenguaje o herramienta:** Spark está escrito en Scala y tiene APIs en Scala, Java, Python y R.

**Ventaja del enfoque funcional:** en vez de programar cada paso a mano, uno le pasa una función a operaciones como `map` o `filter` y Spark reparte ese trabajo entre las máquinas.

**Ejemplo de código sencillo:** elevar al cuadrado cada número de un conjunto de datos de Spark (RDD). La idea viene de un libro sobre Spark en su versión de Python; yo la adapté a Scala:

    // Python (del libro de Kane)
    rdd.map(lambda x: x*x)

    // Scala (adaptado)
    val rdd = sc.parallelize(List(1, 2, 3, 4))
    val cuadrados = rdd.map(x => x * x)
    cuadrados.collect()   // Array(1, 4, 9, 16)

**Qué hace:** `sc.parallelize` crea un conjunto de datos con 1, 2, 3 y 4. Luego `map` aplica a cada elemento la función anónima `x => x * x`, que significa "dado un x, devuelve x por x". El resultado son los cuadrados: 1, 4, 9 y 16. La función anónima le dice a Spark qué hacer con cada elemento, y Spark se encarga de repartir el trabajo.

### Caso 2: Twitter

**Qué problema resuelve:** aguantar un crecimiento enorme de usuarios y peticiones. Twitter empezó con Ruby on Rails, llegó a sus límites de concurrencia y movió sus servicios a la JVM.

**Lenguaje o herramienta:** Scala (y Java) sobre la JVM. Crearon Finagle, una librería para servidores asíncronos escrita en Scala.

**Ventaja del enfoque funcional:** Scala les gustó por ser de alto nivel, con tipado estático y funcional. Con el cambio de plataforma lograron más de 10 veces de mejora de rendimiento. (El motivo principal fue rendimiento y concurrencia; lo funcional fue parte de la decisión.)

**Ejemplo de código real (documentación oficial de Finagle):** 

    val f: Future[Array[Byte]] = fetchUrl(url).flatMap { bytes =>
      val images = findImageUrls(bytes)
      if (images.isEmpty)
        Future.exception(new Exception("no image"))
      else
        fetchUrl(images.head)
    }

    f.onSuccess { image =>
      println("Found image of size " + image.size)
    }

**Qué hace:** `fetchUrl` devuelve un `Future`, que es un valor que todavía no está listo porque la descarga tarda. El código dentro de `flatMap { bytes => ... }` es una función anónima que se ejecuta cuando la descarga termina: busca las imágenes y, si hay, descarga la primera. Después, `onSuccess { image => ... }` es otra función anónima que se ejecuta cuando todo salió bien e imprime el tamaño de la imagen. Así el programa no se queda esperando y puede atender otras peticiones, que es lo que necesita un servicio como Twitter.

## Parte 2 – Entender: ¿qué es una función anónima?
## Parte 3 – Aplicar: tipos de problemas
## Parte 4 – Ampliar: cuatro operaciones nuevas
## Parte 5 – Comparar y concluir
## Referencias