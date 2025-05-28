
# Verbos HTTP: Idempotencia y Seguridad

## Idempotentes

| Verbo     | ¿Idempotente? | ¿Seguro? | Explicación |
|-----------|----------------|----------|-------------|
| GET       | ✅ Sí          | ✅ Sí     | Recupera un recurso sin modificarlo. |
| HEAD      | ✅ Sí          | ✅ Sí     | Igual que GET pero solo devuelve headers. |
| PUT       | ✅ Sí          | ❌ No     | Reemplaza un recurso por completo. Mismo input, mismo resultado. |
| DELETE    | ✅ Sí          | ❌ No     | Eliminar varias veces tiene el mismo efecto que una sola vez. |
| OPTIONS   | ✅ Sí          | ✅ Sí     | Consulta métodos permitidos, no cambia estado. |

## No Idempotentes

| Verbo     | ¿Idempotente? | ¿Seguro? | Explicación |
|-----------|----------------|----------|-------------|
| POST      | ❌ No          | ❌ No     | Cada llamada puede crear un nuevo recurso o tener efectos secundarios. |
| PATCH     | ❌ No          | ❌ No     | Modifica parcialmente. Varias llamadas pueden causar efectos acumulativos. |

---

## ¿Por qué PUT es idempotente pero inseguro?

### ✅ PUT es Idempotente

Una operación es **idempotente** si realizarla una o más veces produce el mismo efecto. Es decir:

- Si haces un `PUT /recurso/123` con el mismo cuerpo de datos múltiples veces, el resultado es el mismo estado final del recurso.

#### Ejemplo:
```http
PUT /usuario/1
{
  "nombre": "Pedro",
  "email": "pedro@example.com"
}
```
Repetir esta petición no cambia el resultado final: el usuario queda con esos mismos datos.

---

### ❌ PUT es Inseguro

En HTTP, un método "seguro" no debe modificar el estado del servidor. PUT **sí lo hace**:

- Crea o reemplaza recursos.
- Cambia datos del sistema.

#### Ejemplo:
```http
PUT /saldo/cliente/1
{
  "saldo": 0
}
```
Este ejemplo cambia claramente el estado del servidor.

---

### Resumen

| Propiedad      | ¿PUT lo cumple? | Explicación                                   |
|----------------|------------------|-----------------------------------------------|
| Idempotente    | ✅ Sí            | Repetir la misma petición produce el mismo estado final. |
| Seguro         | ❌ No            | Modifica el estado del recurso. |
