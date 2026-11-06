// OPERACIÓN NUEVA 2: groupBy
// La función anónima recibe un producto y devuelve el grupo al que pertenece (aquí, su categoría).
// El resultado es un Map: cada categoría apunta a la lista de sus productos.
// Situación: la tienda quiere saber cuántos productos tiene en cada categoría.

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

val porCategoria = productos.groupBy(p => p.categoria)

println("Cantidad de productos por categoría:")
// porCategoria("Muebles") es la lista de los productos de esa categoría; .length los cuenta
println("  Accesorios: " + porCategoria("Accesorios").length)
println("  Muebles: " + porCategoria("Muebles").length)
println("  Papelería: " + porCategoria("Papelería").length)
println("  Tecnología: " + porCategoria("Tecnología").length)
