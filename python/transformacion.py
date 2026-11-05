# TIPO DE PROBLEMA: Transformación  ->  map
# Situación: la tienda quiere saber cuánto dinero tiene en inventario de cada producto (precio x stock).
from producto import PRODUCTOS

# La lambda convierte cada Producto en un par (nombre, valor del inventario)
valores = list(map(lambda p: (p.nombre, p.precio * p.stock), PRODUCTOS))

print("Valor en inventario por producto:")
for nombre, valor in valores:
    print(f"  {nombre}: {valor}")
