// OPERACIÓN NUEVA 1: sortBy
// La función anónima recibe un Producto y devuelve el valor por el que se ordena (Int, Double, String...).
// Situación: la tienda quiere ver su catálogo ordenado del producto más barato al más caro.
object SortBy {
  def main(args: Array[String]): Unit = {
    val porPrecio = Datos.productos.sortBy(p => p.precio)

    println("Productos ordenados por precio (menor a mayor):")
    porPrecio.foreach(p => println(s"  ${p.nombre} - ${p.precio}"))

    // Para ordenar de mayor a menor se cambia el signo: sortBy(p => -p.precio)
  }
}
