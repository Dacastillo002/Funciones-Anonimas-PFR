// OPERACIÓN NUEVA 4: zipWithIndex
// No recibe función anónima: convierte cada elemento en un par (elemento, posición).
// Se encadena con foreach/map, que sí reciben una lambda sobre ese par.
// Situación: la tienda quiere una lista numerada de los muebles que vende.
object ZipWithIndex {
  def main(args: Array[String]): Unit = {
    val muebles = Datos.productos.filter(p => p.categoria == "Muebles")

    println("Muebles numerados:")
    // La posición empieza en 0, por eso se muestra i + 1
    muebles.zipWithIndex.foreach { case (p, i) => println(s"  ${i + 1}. ${p.nombre} - ${p.precio}") }
  }
}
