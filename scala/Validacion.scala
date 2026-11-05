// TIPO DE PROBLEMA: Validación  ->  exists, forall
// Situación: antes de hacer un pedido, la tienda revisa el estado de su inventario.
object Validacion {
  def main(args: Array[String]): Unit = {
    // exists: ¿al menos uno cumple?
    val hayStockBajo = Datos.productos.exists(p => p.stock < 10)
    // forall: ¿todos cumplen?
    val todosConPrecio = Datos.productos.forall(p => p.precio > 0)
    val todosConBuenStock = Datos.productos.forall(p => p.stock > 10)

    println(s"¿Hay algún producto con stock menor a 10? $hayStockBajo")
    println(s"¿Todos los productos tienen precio mayor a 0? $todosConPrecio")
    println(s"¿Todos los productos tienen stock mayor a 10? $todosConBuenStock")
  }
}
