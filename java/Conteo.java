// TIPO DE PROBLEMA: Conteo  ->  count
// Situación: la tienda quiere saber cuántos productos son de tecnología y cuántos tienen poco stock.
public class Conteo {
    public static void main(String[] args) {
        // filter deja los que cumplen y count() los cuenta
        long tecnologia = Datos.PRODUCTOS.stream()
                .filter(p -> p.categoria().equals("Tecnología"))
                .count();
        long pocoStock = Datos.PRODUCTOS.stream()
                .filter(p -> p.stock() < 20)
                .count();

        System.out.println("Productos de Tecnología: " + tecnologia);
        System.out.println("Productos con stock menor a 20: " + pocoStock);
    }
}
