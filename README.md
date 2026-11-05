# Funciones Anónimas – Programación Funcional

**Autor:** Daniel Castillo
**Materia:** Programación Funcional
**Lenguajes:** Scala, Java, Python

## Cómo ejecutar el código


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

### ¿Qué es una función anónima y de dónde viene el nombre lambda?

Una función anónima es una función **sin nombre**, que se escribe justo en el lugar donde se necesita. Por ejemplo, `x => x * 2` significa "dado un x, devuelve x por 2", y no hace falta ponerle nombre ni definirla aparte.

    val numeros = List(1, 2, 3)
    numeros.map(x => x * 2)    // List(2, 4, 6)

El nombre **lambda** viene del *cálculo lambda*, un sistema matemático que creó Alonzo Church en los años 30 para describir funciones usando la letra griega λ. Por eso en Python se escribe literalmente `lambda`, y en Java y Scala a estas funciones también se les llama expresiones lambda.

### ¿En qué se diferencia de una función nombrada?

Las dos hacen lo mismo. La diferencia es que la nombrada se define aparte con un nombre y se puede reutilizar en varios lugares, mientras que la anónima se escribe donde se usa y queda más corta.

    // Función nombrada
    def duplicar(x: Int): Int = x * 2
    List(1, 2, 3).map(duplicar)       // List(2, 4, 6)

    // Función anónima (misma lógica)
    List(1, 2, 3).map(x => x * 2)     // List(2, 4, 6)

Cuando la lógica es corta y se usa una sola vez (como en un `map` o un `filter`), la anónima es más cómoda. Si la lógica es larga o se repite, conviene darle nombre.

### ¿Qué significa que las funciones sean ciudadanos de primera clase?

Significa que las funciones se tratan como cualquier otro valor (un número o un texto): se pueden **guardar en una variable**, **pasar como parámetro** a otra función y **devolver como resultado**.

    val duplicar = (x: Int) => x * 2               // guardada en una variable
    List(1, 2, 3).map(duplicar)                    // pasada como parámetro

    def aplicar(f: Int => Int, n: Int) = f(n)      // función que recibe otra función
    aplicar(x => x + 10, 5)                        // 15

Por eso se puede pasar una lambda a `map` o `filter`: esos métodos esperan una función como parámetro, y como las funciones son valores, se la podemos entregar directamente. A las funciones que reciben o devuelven otras funciones se les llama *funciones de orden superior*.

### ¿Qué es un closure?

Un closure es una función anónima que **usa una variable definida fuera de ella** y se "acuerda" de esa variable aunque se ejecute después.

    val umbral = 100
    List(50, 150, 300).filter(precio => precio > umbral)    // List(150, 300)

Aquí la lambda `precio => precio > umbral` usa `umbral`, que no está dentro de ella. Se ve mejor en este otro ejemplo:

    def crearSumador(n: Int) = (x: Int) => x + n

    val suma5 = crearSumador(5)
    suma5(10)    // 15

Cuando `crearSumador` termina, su parámetro `n` ya no existe, pero la función que devolvió **recuerda** que `n` valía 5. Esa función con su variable recordada es el closure.
## Parte 3 – Aplicar: tipos de problemas
## Parte 4 – Ampliar: cuatro operaciones nuevas
## Parte 5 – Comparar y concluir
## Referencias