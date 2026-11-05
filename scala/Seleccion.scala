// TIPO DE PROBLEMA: Selección  ->  filter
// Situación: una tienda quiere ver qué productos cuestan más de $100.
object Seleccion {
  def main(args: Array[String]): Unit = {
    // La función anónima  p => p.precio > 100  devuelve true o false por cada producto
    val caros = Datos.productos.filter(p => p.precio > 100)

    println("Productos con precio mayor a $100:")
    caros.foreach(p => println(s"  ${p.nombre} - ${p.precio}"))
  }
}
