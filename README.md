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

## Parte 3 – Aplicar: ¿en qué problemas sirven las funciones anónimas?

Para todos los ejemplos uso la misma lista de 12 productos (nombre, categoría, precio y stock), definida en `scala/Producto.scala`, `java/Datos.java` y `python/producto.py`. El código completo de cada ejemplo está en las carpetas `scala/`, `java/` y `python/`; aquí muestro solo la parte donde aparece la función anónima.

### 3.1 Selección (`filter`)

**Situación real:** una tienda quiere ver qué productos cuestan más de $100.

    // Scala
    Datos.productos.filter(p => p.precio > 100)

    // Java
    Datos.PRODUCTOS.stream().filter(p -> p.precio() > 100).toList()

    // Python
    list(filter(lambda p: p.precio > 100, PRODUCTOS))

**Salida (igual en los tres):**

    Productos con precio mayor a $100:
      Laptop - 850.0
      Monitor - 220.0
      Escritorio - 180.0

![Ejecución selección](img/seleccion.png)

### 3.2 Transformación (`map`)

**Situación real:** la tienda quiere saber cuánto dinero tiene en inventario de cada producto (precio por stock).

    // Scala
    Datos.productos.map(p => (p.nombre, p.precio * p.stock))

    // Java
    Datos.PRODUCTOS.stream().map(p -> p.nombre() + ": " + (p.precio() * p.stock())).toList()

    // Python
    list(map(lambda p: (p.nombre, p.precio * p.stock), PRODUCTOS))

**Salida (igual en los tres):**

    Valor en inventario por producto:
      Laptop: 6800.0
      Mouse: 620.0
      Teclado: 875.0
      Monitor: 2640.0
      Cuaderno: 300.0
      Esfero: 150.0
      Carpeta: 140.0
      Silla: 950.0
      Escritorio: 1080.0
      Lámpara: 750.0
      Mochila: 720.0
      Botella: 440.0

![Ejecución transformación](img/transformacion.png)

### 3.3 Validación (`exists`, `forall`)

**Situación real:** antes de hacer un pedido, la tienda revisa el estado de su inventario: si hay algo con poco stock y si todo está en orden.

    // Scala
    Datos.productos.exists(p => p.stock < 10)
    Datos.productos.forall(p => p.precio > 0)

    // Java
    Datos.PRODUCTOS.stream().anyMatch(p -> p.stock() < 10)
    Datos.PRODUCTOS.stream().allMatch(p -> p.precio() > 0)

    // Python
    any(map(lambda p: p.stock < 10, PRODUCTOS))
    all(map(lambda p: p.precio > 0, PRODUCTOS))

**Salida (igual en los tres):**

    ¿Hay algún producto con stock menor a 10? true
    ¿Todos los productos tienen precio mayor a 0? true
    ¿Todos los productos tienen stock mayor a 10? false

![Ejecución validación](img/validacion.png)

### 3.4 Conteo (`count`)

**Situación real:** la tienda quiere saber cuántos productos son de tecnología y cuántos tienen poco stock.

    // Scala
    Datos.productos.count(p => p.categoria == "Tecnología")

    // Java
    Datos.PRODUCTOS.stream().filter(p -> p.categoria().equals("Tecnología")).count()

    // Python
    len(list(filter(lambda p: p.categoria == "Tecnología", PRODUCTOS)))

**Salida (igual en los tres):**

    Productos de Tecnología: 4
    Productos con stock menor a 20: 5

![Ejecución conteo](img/conteo.png)

## Parte 4 – Ampliar: cuatro operaciones nuevas

Además de las operaciones vistas en clase, investigué cuatro más: `sortBy`, `groupBy`, `partition` y `zipWithIndex`. Todas usan la misma lista de productos de la Parte 3 y el código está en la carpeta `operaciones/`.

### Ficha 1: `sortBy`

**Qué hace:** ordena una lista según el criterio que le indiquemos.

**Función anónima:** recibe un producto y devuelve el valor por el que se va a ordenar (un número o un texto). No devuelve Boolean, devuelve el criterio.

**Ejemplo en Scala:**

    Datos.productos.sortBy(p => p.precio)

**Salida:**

    Esfero - 0.5
    Carpeta - 1.75
    Cuaderno - 2.5
    Botella - 8.0
    Mouse - 15.5
    Lámpara - 25.0
    Teclado - 35.0
    Mochila - 40.0
    Silla - 95.0
    Escritorio - 180.0
    Monitor - 220.0
    Laptop - 850.0

(Para ordenar de mayor a menor se cambia el signo: `sortBy(p => -p.precio)`.)

**Problema que resuelve:** una tienda quiere ver su catálogo del producto más barato al más caro. Se parece al tipo *Extremos* (`maxBy`, `minBy`), pero en lugar de dar solo el mejor o el peor, ordena todos.

### Ficha 2: `groupBy`

**Qué hace:** separa los elementos en grupos según una característica y devuelve un `Map` donde cada clave es un grupo.

**Función anónima:** recibe un producto y devuelve la clave del grupo al que pertenece (por ejemplo, su categoría).

**Ejemplo en Scala:**

    Datos.productos.groupBy(p => p.categoria)

Para mostrar cuántos hay en cada grupo se usa `.length` sobre cada lista.

**Salida:**

    Accesorios: 2
    Muebles: 3
    Papelería: 3
    Tecnología: 4

**Problema que resuelve:** la tienda quiere saber cuántos productos tiene por categoría. Es un tipo de problema **nuevo**, que podría llamarse *Agrupamiento*, porque los de la tabla 3.1 no separan la lista en varios grupos a la vez.

### Ficha 3: `partition`

**Qué hace:** divide una lista en dos: los elementos que cumplen una condición y los que no, en un solo paso.

**Función anónima:** recibe un producto y devuelve un `Boolean`. El resultado es una tupla con dos listas: primero los que dieron `true` y luego los que dieron `false`.

**Ejemplo en Scala:**

    val (stockBajo, stockSuficiente) = Datos.productos.partition(p => p.stock < 20)

**Salida:**

    Stock bajo (menor a 20): Laptop, Monitor, Silla, Escritorio, Mochila
    Stock suficiente: Mouse, Teclado, Cuaderno, Esfero, Carpeta, Lámpara, Botella

**Problema que resuelve:** la tienda quiere separar los productos con poco stock de los que tienen suficiente. Se parece al tipo *Selección* (`filter`), con la diferencia de que `filter` solo te deja los que cumplen y `partition` conserva también los que no.

### Ficha 4: `zipWithIndex`

**Qué hace:** convierte cada elemento en un par (elemento, posición). La posición empieza en 0.

**Función anónima:** esta operación **no recibe** una función anónima. Se encadena con otra que sí la reciba, como `foreach` o `map`, que trabajan sobre el par.

**Ejemplo en Scala (con `filter` y `foreach`):**

    Datos.productos
      .filter(p => p.categoria == "Muebles")
      .zipWithIndex
      .foreach { case (p, i) => println(s"${i + 1}. ${p.nombre} - ${p.precio}") }

**Salida:**

    1. Silla - 95.0
    2. Escritorio - 180.0
    3. Lámpara - 25.0

**Problema que resuelve:** la tienda quiere una lista numerada de los muebles que vende. Es un tipo de problema **nuevo**: *Enumeración* (numerar o usar la posición de cada elemento).

### 4.3 Combinar operaciones

**Pregunta:** ¿qué categorías tienen más de 60 unidades en stock en total, ordenadas de la que más stock tiene a la que menos?

**Cadena de operaciones:**

    Datos.productos
      .groupBy(p => p.categoria)
      .map { case (cat, ps) => (cat, ps.foldLeft(0)((acc, p) => acc + p.stock)) }
      .filter { case (_, total) => total > 60 }
      .toList
      .sortBy { case (_, total) => -total }

**Salida:**

    Papelería: 500
    Tecnología: 85
    Accesorios: 73

**Qué hace cada paso:**
1. `groupBy` (nueva): agrupa los productos por categoría.
2. `map` con `foldLeft` (vistas en clase): para cada categoría suma el stock de sus productos con `foldLeft`, y deja un par (categoría, stock total).
3. `filter` (vista en clase): se queda solo con las categorías cuyo total es mayor a 60. Muebles (46) queda fuera.
4. `sortBy` (nueva): ordena de mayor a menor stock. Se usa `-total` para invertir el orden.

## Parte 5 – Comparar y concluir

### 5.1 Scala, Java y Python frente a frente

| Criterio | Scala | Java | Python |
|---|---|---|---|
| Sintaxis de la lambda | `p => p.precio > 100` | `p -> p.precio() > 100` | `lambda p: p.precio > 100` |
| Cómo se declara el tipo | Se infiere solo; se puede escribir `(p: Producto) => ...` | Se infiere por el contexto del stream; se puede escribir `(Producto p) -> ...` | No se declara, el tipo es dinámico |
| Operaciones con listas disponibles | Están directo en la lista: `filter`, `map`, `exists`, `forall`, `count` | Hay que pasar por `.stream()` y al final volver a lista con `.toList()` | Funciones sueltas: `filter`, `map`, `any`, `all`, `len`; no son métodos de la lista |
| Restricción del lenguaje | Casi ninguna para lo que usé | Solo puede usar variables locales que no cambian (*effectively final*) | La lambda solo puede tener una expresión, no varias líneas |
| Legibilidad (mi opinión) | La más corta y directa | Más larga por `.stream()` y `.toList()`, pero ordenada | Clara en lo simple, pero con `list(filter(lambda...))` se lee al revés |

**¿En qué lenguaje fue más natural resolver los problemas?** En Scala, porque las operaciones están directamente en la lista y no hay que convertir nada. El código queda corto y se lee de izquierda a derecha.

**¿En qué tipo de problema hubo más diferencias?** En **Conteo**. En Scala bastó con `count(p => ...)`, en Java tuve que usar `filter(...).count()` y en Python `len(list(filter(...)))`. También hubo diferencias en **Validación**: cada lenguaje usa nombres distintos (`exists`/`anyMatch`/`any`, `forall`/`allMatch`/`all`), y en Python `any` y `all` reciben una lista de booleanos, por eso usé `map` con la lambda. La razón es que Scala trae más operaciones listas en sus colecciones, y Java y Python reparten la tarea entre varias funciones.

### 5.2 Conclusión

Las funciones anónimas son la pieza que hace útiles a operaciones como `map`, `filter` o `groupBy`: la operación sabe *cómo* recorrer la lista, y la función anónima le dice *qué hacer* con cada elemento. Sin ellas habría que escribir un ciclo cada vez, o crear una función con nombre para cada condición pequeña. Con ellas, una pregunta como "¿qué productos cuestan más de $100?" se resuelve en una línea, y se pueden encadenar varias operaciones, como hice en la combinación de la Parte 4.

Esto se ve en los casos que investigué. En Apache Spark, la función anónima `x => x * x` que se le pasa a `map` es lo que Spark reparte entre las máquinas para que cada una la aplique a su parte de los datos. En Twitter, con Finagle, las funciones anónimas dentro de `flatMap` y `onSuccess` indican qué hacer cuando una operación que tarda termina, y así el programa no se queda esperando. En los dos casos la idea es la misma que usé con mi lista de productos: separar *qué recorrer* de *qué hacer con cada elemento*.
## Referencias