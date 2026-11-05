# TIPO DE PROBLEMA: Selección  ->  filter
# Situación: una tienda quiere ver qué productos cuestan más de $100.
from producto import PRODUCTOS

# La lambda  lambda p: p.precio > 100  devuelve True o False por cada producto
caros = list(filter(lambda p: p.precio > 100, PRODUCTOS))

print("Productos con precio mayor a $100:")
for p in caros:
    print(f"  {p.nombre} - {p.precio}")
