// OPERACIÓN NUEVA 3: partition
// La función anónima recibe un Producto y devuelve un Boolean.
// El resultado es una tupla con dos listas: (los que dieron true, los que dieron false).
// Situación: la tienda quiere separar los productos con poco stock de los que tienen suficiente.
object Partition {
  def main(args: Array[String]): Unit = {
    val (stockBajo, stockSuficiente) = Datos.productos.partition(p => p.stock < 20)

    println("Stock bajo (menor a 20): " + stockBajo.map(p => p.nombre).mkString(", "))
    println("Stock suficiente: " + stockSuficiente.map(p => p.nombre).mkString(", "))
  }
}
