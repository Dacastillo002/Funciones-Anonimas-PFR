// TIPO DE PROBLEMA: Conteo  ->  count
// Situación: la tienda quiere saber cuántos productos son de tecnología y cuántos tienen poco stock.

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

val tecnologia = productos.count(p => p.categoria == "Tecnología")
val pocoStock = productos.count(p => p.stock < 20)

println("Productos de Tecnología: " + tecnologia)
println("Productos con stock menor a 20: " + pocoStock)
