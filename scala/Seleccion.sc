// TIPO DE PROBLEMA: Selección  ->  filter
// Situación: una tienda quiere ver qué productos cuestan más de $100.

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

val caros = productos.filter(p => p.precio > 100)

println("Productos con precio mayor a $100:")
caros.foreach(p => println("  " + p.nombre + " - " + p.precio))
