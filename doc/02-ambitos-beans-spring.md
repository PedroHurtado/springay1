
# Ámbitos (Scopes) de los Beans en Spring

En Spring Framework, los **beans** pueden tener diferentes **ámbitos** que determinan cuántas instancias se crean y cómo se gestionan. Aquí están los más comunes:

---

## 🔁 1. `singleton` (por defecto)

- **Descripción**: Spring crea **una única instancia** del bean por contenedor de Spring.
- **Uso típico**: Servicios, utilidades, lógica de negocio.
- **Ejemplo**:
  ```java
  @Component
  @Scope("singleton")
  public class MyService { }
  ```

---

## 🔄 2. `prototype`

- **Descripción**: Cada vez que se solicita el bean, se crea **una nueva instancia**.
- **Uso típico**: Objetos con estado mutable o de corta duración.
- **Ejemplo**:
  ```java
  @Component
  @Scope("prototype")
  public class MyComponent { }
  ```
- **Nota**: Spring no gestiona el ciclo de vida completo (no invoca `@PreDestroy`).

---

## 🌐 3. `request` *(solo en aplicaciones web)*

- **Descripción**: Se crea una instancia por **cada petición HTTP**.
- **Uso típico**: Beans que contienen datos específicos de la petición.
- **Ejemplo**:
  ```java
  @Component
  @Scope("request")
  public class RequestScopedBean { }
  ```

---

## 👤 4. `session` *(solo en aplicaciones web)*

- **Descripción**: Una instancia por **sesión de usuario** HTTP.
- **Uso típico**: Información de usuario persistente como un carrito de compra.
- **Ejemplo**:
  ```java
  @Component
  @Scope("session")
  public class SessionScopedBean { }
  ```

---

## 🌍 5. `application`

- **Descripción**: Una única instancia **por aplicación web** (ServletContext).
- **Ejemplo**:
  ```java
  @Component
  @Scope("application")
  public class AppScopedBean { }
  ```

---

## 🧵 6. `websocket` *(en aplicaciones WebSocket)*

- **Descripción**: Una instancia por **sesión WebSocket**.
- **Ejemplo**:
  ```java
  @Component
  @Scope("websocket")
  public class WebSocketScopedBean { }
  ```

---

## 📝 Resumen de scopes

| Scope        | Contexto           | Instancia por...           |
|--------------|--------------------|-----------------------------|
| `singleton`  | General             | Contenedor de Spring        |
| `prototype`  | General             | Cada solicitud de bean      |
| `request`    | Web                 | Petición HTTP               |
| `session`    | Web                 | Sesión HTTP                 |
| `application`| Web                 | Aplicación (ServletContext) |
| `websocket`  | WebSocket           | Sesión WebSocket            |
