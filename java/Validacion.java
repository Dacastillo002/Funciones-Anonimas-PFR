import java.util.List;

// TIPO DE PROBLEMA: Validación  ->  exists y forall
// Situación: antes de hacer un pedido, la tienda revisa el estado de su inventario.
// (En Java se llaman anyMatch y allMatch; en Python, any y all)
public class Validacion {
    public static void main(String[] args) {
        List<Producto> productos = List.of(
            new Producto("Laptop", "Tecnología", 850.0, 8),
            new Producto("Mouse", "Tecnología", 15.5, 40),
            new Producto("Teclado", "Tecnología", 35.0, 25),
            new Producto("Monitor", "Tecnología", 220.0, 12),
            new Producto("Cuaderno", "Papelería", 2.5, 120),
            new Producto("Esfero", "Papelería", 0.5, 300),
            new Producto("Carpeta", "Papelería", 1.75, 80),
            new Producto("Silla", "Muebles", 95.0, 10),
            new Producto("Escritorio", "Muebles", 180.0, 6),
            new Producto("Lámpara", "Muebles", 25.0, 30),
            new Producto("Mochila", "Accesorios", 40.0, 18),
            new Producto("Botella", "Accesorios", 8.0, 55)
        );

        boolean hayStockBajo = productos.stream().anyMatch(p -> p.stock < 10);
        boolean todosConPrecio = productos.stream().allMatch(p -> p.precio > 0);
        boolean todosConBuenStock = productos.stream().allMatch(p -> p.stock > 10);

        System.out.println("¿Hay algún producto con stock menor a 10? " + hayStockBajo);
        System.out.println("¿Todos los productos tienen precio mayor a 0? " + todosConPrecio);
        System.out.println("¿Todos los productos tienen stock mayor a 10? " + todosConBuenStock);
    }
}


class Producto {
    String nombre;
    String categoria;
    double precio;
    int stock;

    Producto(String nombre, String categoria, double precio, int stock) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.stock = stock;
    }
}
