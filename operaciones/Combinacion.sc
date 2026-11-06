// COMBINACIÓN: groupBy (nueva) -> map y filter (vistas en clase) -> sortBy (nueva)
// Pregunta: ¿Qué categorías tienen más de 60 unidades en stock en total,
//           ordenadas de la que más stock tiene a la que menos?

// Datos de la tienda (los mismos en Scala, Java y Python)
case class Producto(nombre: String, categoria: String, precio: Double, stock: Int)

val productos = List(
  Producto("Laptop", "Tecnología", 850.0, 8),
  Producto("Mouse", "Tecnología", 15.5, 40),
  Producto("Teclado", "Tecnología", 35.0, 25),
  Producto("Monitor", "Tecnología", 220.0, 12),
  Producto("Cuaderno", "Papelería", 2.5, 120),
  Producto("Esfero", "Papelería", 0.5, 300),
  Producto("Carpeta", "Papelería", 1.75, 80),
  Producto("Silla", "Muebles", 95.0, 10),
  Producto("Escritorio", "Muebles", 180.0, 6),
  Producto("Lámpara", "Muebles", 25.0, 30),
  Producto("Mochila", "Accesorios", 40.0, 18),
  Producto("Botella", "Accesorios", 8.0, 55)
)

val resultado = productos
  .groupBy(p => p.categoria)                                                   // 1. agrupa por categoría
  .map(par => (par._1, par._2.foldLeft(0)((acc, p) => acc + p.stock)))         // 2. suma el stock de cada grupo
  .filter(par => par._2 > 60)                                                  // 3. deja las de más de 60
  .toList
  .sortBy(par => -par._2)                                                      // 4. ordena de mayor a menor

println("Categorías con más de 60 unidades en stock (de mayor a menor):")
resultado.foreach(par => println("  " + par._1 + ": " + par._2))
