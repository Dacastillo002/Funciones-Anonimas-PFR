// OPERACIÓN NUEVA 1: sortBy
// La función anónima recibe un producto y devuelve el valor por el que se ordena.
// Situación: la tienda quiere ver su catálogo del producto más barato al más caro.

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

val porPrecio = productos.sortBy(p => p.precio)

println("Productos ordenados por precio (menor a mayor):")
porPrecio.foreach(p => println("  " + p.nombre + " - " + p.precio))

// Para ordenar de mayor a menor se cambia el signo: productos.sortBy(p => -p.precio)
