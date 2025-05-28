# Diferencia entre PUT y PATCH en HTTP


---

## 🔁 PUT vs 🩹 PATCH

| Característica             | PUT                           | PATCH                           |
|---------------------------|-------------------------------|---------------------------------|
| Tipo de actualización     | Reemplaza todo el recurso     | Modifica parcialmente el recurso |
| Envío de campos           | Se deben enviar todos         | Solo los que se quieren cambiar |
| Idempotencia              | Sí                            | Sí (pero depende de la implementación) |

---

## 🧑‍💻 Ejemplo práctico

### Usuario original

```json
{
  "id": 1,
  "nombre": "Pedro",
  "email": "pedro@email.com",
  "activo": true
}
```

### ✅ PUT (Reemplaza todo)

```http
PUT /usuarios/1
Content-Type: application/json

{
  "id": 1,
  "nombre": "Pedro Hurtado",
  "email": "pedro@email.com",
  "activo": true
}
```

> Reemplaza todo el recurso. Todos los campos deben estar presentes.

---

### ✅ PATCH (Actualiza parcialmente)

```http
PATCH /usuarios/1
Content-Type: application/json

{
  "nombre": "Pedro Hurtado"
}
```

> Solo se actualiza el campo `nombre`.

---

## ⚠️ Idempotencia de PATCH

PATCH es idempotente **por definición**, es decir, **hacer la misma solicitud varias veces debería producir el mismo resultado que hacerla una sola vez**.

### ❌ Ejemplo de PATCH no idempotente

```http
PATCH /usuarios/1
Content-Type: application/json

{
  "creditos": "+10"
}
```

**Servidor (pseudocódigo):**

```ts
if (body.creditos.startsWith("+")) {
  usuario.creditos += parseInt(body.creditos.slice(1));
}
```

- Hacer esta petición tres veces **aumenta el crédito en +30**, por lo tanto **no es idempotente**.

---

### ✅ Ejemplo de PATCH idempotente

```http
PATCH /usuarios/1
Content-Type: application/json

{
  "creditos": 50
}
```

**Servidor (pseudocódigo):**

```ts
usuario.creditos = body.creditos;
```

- Hacer esta petición múltiples veces mantiene el crédito en **50**.  
- Esto **sí es idempotente**.

---

## 🧠 Conclusión

- Usa `PUT` para reemplazos completos.
- Usa `PATCH` para actualizaciones parciales.
- Asegúrate de que tus `PATCH` sean idempotentes si así lo requiere tu API.

