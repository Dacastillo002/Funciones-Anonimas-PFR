// COMBINACIÓN: groupBy (nueva) -> map y filter (vistas en clase) -> sortBy (nueva)
// Pregunta: ¿Qué categorías tienen más de 60 unidades en stock en total,
//           ordenadas de la que más stock tiene a la que menos?
object Combinacion {
  def main(args: Array[String]): Unit = {
    val resultado = Datos.productos
      .groupBy(p => p.categoria)                                                      // 1. agrupa por categoría
      .map { case (cat, ps) => (cat, ps.foldLeft(0)((acc, p) => acc + p.stock)) }     // 2. suma el stock de cada grupo
      .filter { case (_, total) => total > 60 }                                       // 3. deja las de más de 60
      .toList
      .sortBy { case (_, total) => -total }                                           // 4. ordena de mayor a menor

    println("Categorías con más de 60 unidades en stock (de mayor a menor):")
    resultado.foreach { case (cat, total) => println(s"  $cat: $total") }
  }
}
