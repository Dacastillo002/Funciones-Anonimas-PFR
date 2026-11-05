import java.util.List;

// TIPO DE PROBLEMA: Transformación  ->  map
// Situación: la tienda quiere saber cuánto dinero tiene en inventario de cada producto (precio x stock).
public class Transformacion {
    public static void main(String[] args) {
        // La lambda convierte cada Producto en un texto "nombre: valor del inventario"
        List<String> valores = Datos.PRODUCTOS.stream()
                .map(p -> p.nombre() + ": " + (p.precio() * p.stock()))
                .toList();

        System.out.println("Valor en inventario por producto:");
        valores.forEach(v -> System.out.println("  " + v));
    }
}
