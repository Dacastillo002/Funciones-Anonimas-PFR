# TIPO DE PROBLEMA: Conteo  ->  count
# Situación: la tienda quiere saber cuántos productos son de tecnología y cuántos tienen poco stock.

# Datos de la tienda (los mismos en Scala, Java y Python)
class Producto:
    def __init__(self, nombre, categoria, precio, stock):
        self.nombre = nombre
        self.categoria = categoria
        self.precio = precio
        self.stock = stock

productos = [
    Producto("Laptop", "Tecnología", 850.0, 8),
    Producto("Mouse", "Tecnología", 15.5, 40),
    Producto("Teclado", "Tecnología", 35.0, 25),
    Producto("Monitor", "Tecnología", 220.0, 12),
    Producto("Cuaderno", "Papelería", 2.5, 120),
    Producto("Esfero", "Papelería", 0.5, 300),
    Producto("Carpeta", "Papelería", 1.75, 80),
    Producto("Silla", "Muebles", 95.0, 10),
    Producto("Escritorio", "Muebles", 180.0, 6),
    Producto("Lámpara", "Muebles", 25.0, 30),
    Producto("Mochila", "Accesorios", 40.0, 18),
    Producto("Botella", "Accesorios", 8.0, 55),
]

# En Python se filtra y luego se cuenta con len()
tecnologia = len(list(filter(lambda p: p.categoria == "Tecnología", productos)))
poco_stock = len(list(filter(lambda p: p.stock < 20, productos)))

print("Productos de Tecnología: " + str(tecnologia))
print("Productos con stock menor a 20: " + str(poco_stock))
