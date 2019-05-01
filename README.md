# atix-labs-test
## Ejercicio 1.a
- La clase no está bien diseñada.
	1. No es extensible para otros tipos de cuenta
	2. No valida que el valor de tipo al crearse el objeto sea CAJA_AHORRO o CUENTA_CORRIENTE. Como consecuencia, no se ejecuta la validación de saldo suficiente por lo que la cuenta puede quedar con saldo negativo.
- Agregaría un getter para el atributo saldo y opcionalmente para el atributo titular y nroCuenta.

## Ejercicio 1.b
Suposiciones

## Ejercicio 2
###Strategy
El patrón Strategy se utiliza cuando es necesario modificar un algoritmo en tiempo de ejecución. Se define una familia de algoritmos que son encapsuladas en distintas clases.  El cliente puede cambiar las estrategias.
Las instancias necesarias de cada clase de estrategia dependen del problema. Una estrategia puede tener estado

###Pruebas unitarias automatizadas
Podemos mencionar como principales ventajas de las pruebas unitarias las siguientes:
1. Los errores son más fáciles de localizar.
2. Los problemas son encontrados al principio del desarrollo.
3. Mejora la estructura del código ya que se necesita crear pruebas unitarias simples.
4. Dan facilidad para encarar refactors u otros cambios del código.
5. Los casos de prueba pueden ser utilizados como documentación.

###Observer
El patrón Observer se puede utilizar cuando un objeto (sujeto) necesita notificar a clientes (observadores) de modificaciones. El sujeto mantiene una lista de dependientes, a los que les notifica automáticamete al ocurrir un cambio en su estado. 

La principal ventaja al utilizar este patrón es evitar el acoplamiento entre las clases sujeto y observadores, y la comprobación continua por parte de los observadores del cambio de estado del sujeto. Este patrón de diseño es la base del modelo MVC.

## Ejercicio 3
```sql
SELECT username
FROM Usuario u 
	INNER JOIN Persona p ON u.id = p.idUsuario
WHERE nombre LIKE 'Jorg%'
``` 

```sql
SELECT EXTRACT(MONTH FROM fechaNac)
FROM Usuario u 
	INNER JOIN Persona p ON u.id = p.idUsuario
GROUP BY EXTRACT(MONTH FROM fechaNac)
HAVING COUNT (*) > 10
``` 