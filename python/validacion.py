# TIPO DE PROBLEMA: Validación  ->  exists y forall
# Situación: antes de hacer un pedido, la tienda revisa el estado de su inventario.
# (En Java se llaman anyMatch y allMatch; en Python, any y all)

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

hay_stock_bajo = any(map(lambda p: p.stock < 10, productos))
todos_con_precio = all(map(lambda p: p.precio > 0, productos))
todos_con_buen_stock = all(map(lambda p: p.stock > 10, productos))

# .lower() es solo para que se imprima true/false como en Scala y Java
print("¿Hay algún producto con stock menor a 10? " + str(hay_stock_bajo).lower())
print("¿Todos los productos tienen precio mayor a 0? " + str(todos_con_precio).lower())
print("¿Todos los productos tienen stock mayor a 10? " + str(todos_con_buen_stock).lower())
