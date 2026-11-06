// TIPO DE PROBLEMA: Validación  ->  exists y forall
// Situación: antes de hacer un pedido, la tienda revisa el estado de su inventario.
// (En Java se llaman anyMatch y allMatch; en Python, any y all)

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

val hayStockBajo = productos.exists(p => p.stock < 10)
val todosConPrecio = productos.forall(p => p.precio > 0)
val todosConBuenStock = productos.forall(p => p.stock > 10)

println("¿Hay algún producto con stock menor a 10? " + hayStockBajo)
println("¿Todos los productos tienen precio mayor a 0? " + todosConPrecio)
println("¿Todos los productos tienen stock mayor a 10? " + todosConBuenStock)
