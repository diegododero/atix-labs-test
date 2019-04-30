# atix-labs-test
## Ejercicio 1.a
- La clase no está bien diseñada.
	1. No es extensible para otros tipos de cuenta
	2. No valida que el valor de tipo al crearse el objeto sea CAJA_AHORRO o CUENTA_CORRIENTE. Como consecuencia, no se ejecuta la validación de saldo suficiente por lo que la cuenta puede quedar con saldo negativo.
- Agregaría un getter para el saldo

## Ejercicio 2

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