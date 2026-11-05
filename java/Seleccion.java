import java.util.List;

// TIPO DE PROBLEMA: Selección  ->  filter
// Situación: una tienda quiere ver qué productos cuestan más de $100.
public class Seleccion {
    public static void main(String[] args) {
        // La lambda  p -> p.precio() > 100  devuelve true o false por cada producto
        List<Producto> caros = Datos.PRODUCTOS.stream()
                .filter(p -> p.precio() > 100)
                .toList();

        System.out.println("Productos con precio mayor a $100:");
        caros.forEach(p -> System.out.println("  " + p.nombre() + " - " + p.precio()));
    }
}
