import java.util.List;

// Datos base compartidos por todos los ejemplos (los mismos en Scala y Python)
public class Datos {
    public static final List<Producto> PRODUCTOS = List.of(
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
}
