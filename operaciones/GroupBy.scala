// OPERACIÓN NUEVA 2: groupBy
// La función anónima recibe un Producto y devuelve la clave del grupo al que pertenece.
// El resultado es un Map[clave, List[Producto]].
// Situación: la tienda quiere saber cuántos productos tiene en cada categoría.
object GroupBy {
  def main(args: Array[String]): Unit = {
    val porCategoria = Datos.productos.groupBy(p => p.categoria)

    println("Cantidad de productos por categoría:")
    // toList y sortBy solo sirven para imprimir las categorías en orden alfabético
    porCategoria.toList.sortBy(par => par._1).foreach(par => println(s"  ${par._1}: ${par._2.length}"))
  }
}
