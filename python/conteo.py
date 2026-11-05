# TIPO DE PROBLEMA: Conteo  ->  count
# Situación: la tienda quiere saber cuántos productos son de tecnología y cuántos tienen poco stock.
from producto import PRODUCTOS

# Python no tiene count con lambda: se filtra con filter y se cuenta con len
tecnologia = len(list(filter(lambda p: p.categoria == "Tecnología", PRODUCTOS)))
poco_stock = len(list(filter(lambda p: p.stock < 20, PRODUCTOS)))

print(f"Productos de Tecnología: {tecnologia}")
print(f"Productos con stock menor a 20: {poco_stock}")
