// TIPO DE PROBLEMA: Conteo  ->  count
// Situación: la tienda quiere saber cuántos productos son de tecnología y cuántos tienen poco stock.
object Conteo {
  def main(args: Array[String]): Unit = {
    // count recibe una función anónima que devuelve Boolean y cuenta los que dan true
    val tecnologia = Datos.productos.count(p => p.categoria == "Tecnología")
    val pocoStock = Datos.productos.count(p => p.stock < 20)

    println(s"Productos de Tecnología: $tecnologia")
    println(s"Productos con stock menor a 20: $pocoStock")
  }
}
