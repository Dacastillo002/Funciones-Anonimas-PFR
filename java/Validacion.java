// TIPO DE PROBLEMA: Validación  ->  exists, forall  (en Java: anyMatch, allMatch)
// Situación: antes de hacer un pedido, la tienda revisa el estado de su inventario.
public class Validacion {
    public static void main(String[] args) {
        // anyMatch equivale a exists: ¿al menos uno cumple?
        boolean hayStockBajo = Datos.PRODUCTOS.stream().anyMatch(p -> p.stock() < 10);
        // allMatch equivale a forall: ¿todos cumplen?
        boolean todosConPrecio = Datos.PRODUCTOS.stream().allMatch(p -> p.precio() > 0);
        boolean todosConBuenStock = Datos.PRODUCTOS.stream().allMatch(p -> p.stock() > 10);

        System.out.println("¿Hay algún producto con stock menor a 10? " + hayStockBajo);
        System.out.println("¿Todos los productos tienen precio mayor a 0? " + todosConPrecio);
        System.out.println("¿Todos los productos tienen stock mayor a 10? " + todosConBuenStock);
    }
}
