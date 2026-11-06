import java.util.List;

// TIPO DE PROBLEMA: Conteo  ->  count
// Situación: la tienda quiere saber cuántos productos son de tecnología y cuántos tienen poco stock.
public class Conteo {
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

        // En Java se filtra y luego se cuenta con count()
        long tecnologia = productos.stream().filter(p -> p.categoria.equals("Tecnología")).count();
        long pocoStock = productos.stream().filter(p -> p.stock < 20).count();

        System.out.println("Productos de Tecnología: " + tecnologia);
        System.out.println("Productos con stock menor a 20: " + pocoStock);
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
