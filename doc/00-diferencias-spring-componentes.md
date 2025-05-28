
# Diferencias entre `@Component`, `@Service` y `@Repository` en Spring

En Spring Framework, las anotaciones `@Component`, `@Service` y `@Repository` se usan para definir **beans gestionados por el contenedor de Spring**, pero existen **diferencias semánticas y funcionales** entre ellas:

---

## `@Component`

- **Propósito general**: Marca una clase como un **componente genérico**.
- **Uso**: Para cualquier clase que debe ser gestionada por Spring como un bean.
- **Ejemplo**:
  ```java
  @Component
  public class MyUtility {
      // lógica auxiliar
  }
  ```
- **Scope**: Es la base de las otras anotaciones (`@Service`, `@Repository`, `@Controller`).
- **Funcionalidades añadidas**: Ninguna específica.

---

## `@Service`

- **Propósito**: Indica que una clase ofrece **lógica de negocio**.
- **Uso**: Clases de la **capa de servicio**, coordinan entre controladores y repositorios.
- **Ejemplo**:
  ```java
  @Service
  public class UserService {
      public void registerUser(User user) {
          // lógica de negocio
      }
  }
  ```
- **Funcionalidades añadidas**:
  - Identificación semántica como capa de servicio.
  - Compatible con AOP para aplicar **transacciones**, logs, etc.

---

## `@Repository`

- **Propósito**: Marca una clase como parte de la **capa de acceso a datos (DAO)**.
- **Uso**: Clases que interactúan con bases de datos usando JPA o JDBC.
- **Ejemplo**:
  ```java
  @Repository
  public class UserRepository {
      public User findById(Long id) {
          // acceso a base de datos
      }
  }
  ```
- **Funcionalidades añadidas**:
  - Traducción automática de excepciones (`SQLException` → `DataAccessException`).
  - Integración con Spring Data (por ejemplo, extendiendo `JpaRepository`).

---

## Tabla comparativa

| Anotación     | Capa de aplicación | Función principal                  | Funcionalidad extra de Spring                    |
|---------------|--------------------|------------------------------------|--------------------------------------------------|
| `@Component`  | General             | Bean genérico                      | Ninguna                                          |
| `@Service`    | Servicio            | Lógica de negocio                  | Integración con AOP y transacciones              |
| `@Repository` | Acceso a datos      | Interacción con base de datos      | Traducción de excepciones, integración con JPA   |

---

## Conclusión

- Todas se detectan mediante **component scan**.
- Usarlas correctamente mejora la **organización del código**.
- Aunque funcionalmente intercambiables, **semánticamente cada una tiene su lugar** en la arquitectura Spring.
