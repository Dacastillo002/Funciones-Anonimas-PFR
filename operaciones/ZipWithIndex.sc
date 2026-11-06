// OPERACIÓN NUEVA 4: zipWithIndex
// No recibe función anónima: le pega a cada elemento su posición, formando pares (producto, posición).
// Se encadena con foreach, que sí recibe una lambda sobre cada par.
// Situación: la tienda quiere una lista numerada de los muebles que vende.

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

val muebles = productos.filter(p => p.categoria == "Muebles")

println("Muebles numerados:")
// par._1 es el producto y par._2 es su posición (empieza en 0, por eso se suma 1)
muebles.zipWithIndex.foreach(par => println("  " + (par._2 + 1) + ". " + par._1.nombre + " - " + par._1.precio))
