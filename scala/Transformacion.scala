// TIPO DE PROBLEMA: Transformación  ->  map
// Situación: la tienda quiere saber cuánto dinero tiene en inventario de cada producto (precio x stock).
object Transformacion {
  def main(args: Array[String]): Unit = {
    // La función anónima convierte cada Producto en un par (nombre, valor del inventario)
    val valores = Datos.productos.map(p => (p.nombre, p.precio * p.stock))

    println("Valor en inventario por producto:")
    valores.foreach(v => println(s"  ${v._1}: ${v._2}"))
  }
}
