// OPERACIÓN NUEVA 3: partition
// La función anónima recibe un producto y devuelve true o false.
// El resultado es un par de listas: la primera con los que dieron true y la segunda con los que dieron false.
// Situación: la tienda quiere separar los productos con poco stock de los que tienen suficiente.

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

val partes = productos.partition(p => p.stock < 20)

// partes._1 es la primera lista (stock bajo) y partes._2 es la segunda (stock suficiente)
println("Stock bajo (menor a 20): " + partes._1.map(p => p.nombre))
println("Stock suficiente: " + partes._2.map(p => p.nombre))
