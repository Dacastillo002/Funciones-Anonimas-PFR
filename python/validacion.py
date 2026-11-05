# TIPO DE PROBLEMA: Validación  ->  exists, forall  (en Python: any, all)
# Situación: antes de hacer un pedido, la tienda revisa el estado de su inventario.
from producto import PRODUCTOS

# any equivale a exists: ¿al menos uno cumple?
hay_stock_bajo = any(map(lambda p: p.stock < 10, PRODUCTOS))
# all equivale a forall: ¿todos cumplen?
todos_con_precio = all(map(lambda p: p.precio > 0, PRODUCTOS))
todos_con_buen_stock = all(map(lambda p: p.stock > 10, PRODUCTOS))

# .lower() es solo para que se imprima true/false igual que en Scala y Java
print(f"¿Hay algún producto con stock menor a 10? {str(hay_stock_bajo).lower()}")
print(f"¿Todos los productos tienen precio mayor a 0? {str(todos_con_precio).lower()}")
print(f"¿Todos los productos tienen stock mayor a 10? {str(todos_con_buen_stock).lower()}")
