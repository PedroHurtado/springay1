# @Component vs @Bean en Spring Framework

En Spring Framework, tanto `@Component` como `@Bean` son anotaciones fundamentales para la gestión de beans, pero tienen propósitos y usos diferentes.

## @Component

`@Component` es una anotación de nivel de clase que marca una clase como un componente de Spring. Cuando el container de Spring escanea el classpath, automáticamente detecta estas clases y las registra como beans.

```java
@Component
public class UserService {
    public void saveUser(User user) {
        // lógica de negocio
    }
}
```

### Anotaciones especializadas

Spring también proporciona anotaciones especializadas que son meta-anotaciones de `@Component`:
- `@Service` - para la capa de servicios
- `@Repository` - para la capa de acceso a datos
- `@Controller` - para controladores web

## @Bean

`@Bean` es una anotación de nivel de método que se usa dentro de clases marcadas con `@Configuration`. Te permite definir manualmente cómo crear y configurar un bean específico.

```java
@Configuration
public class AppConfig {
    
    @Bean
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/mydb");
        dataSource.setUsername("user");
        dataSource.setPassword("password");
        return dataSource;
    }
}
```

## Comparación entre @Bean y @Component

| Aspecto | @Component | @Bean |
|---------|------------|-------|
| **Nivel de aplicación** | Clase | Método |
| **Control de creación** | Automático por Spring | Manual y personalizable |
| **Flexibilidad** | Limitada | Máxima |
| **Uso típico** | Clases propias | Clases de terceros o configuraciones complejas |
| **Ubicación** | Cualquier clase | Dentro de clases `@Configuration` |

### Control de creación
- **@Component**: Spring crea automáticamente la instancia usando el constructor por defecto o inyección de dependencias
- **@Bean**: Tienes control total sobre cómo se crea el objeto, permitiendo lógica personalizada de inicialización

### Cuándo usar cada uno
- **@Component**: Para clases que tú mismo escribes y controlas
- **@Bean**: Para clases de terceros, configuraciones complejas, o cuando necesitas lógica especial de creación

### Flexibilidad
- **@Component**: Menos flexible, Spring maneja todo automáticamente
- **@Bean**: Máxima flexibilidad, puedes personalizar cada aspecto de la creación del bean

## Ejemplo práctico de cuándo usar @Bean

```java
@Configuration
public class SecurityConfig {
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12); // configuración específica
    }
    
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate template = new RestTemplate();
        template.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        return template; // configuración personalizada
    }
}
```

En estos casos, `@Bean` es ideal porque necesitas configurar parámetros específicos o usar clases de librerías externas que no puedes modificar para agregar `@Component`.

## Conclusión

La elección entre ambos depende principalmente de si necesitas control granular sobre la creación del bean o si puedes delegar esa responsabilidad a Spring. 

- Usa `@Component` (y sus especializaciones) para tus propias clases cuando Spring puede manejar la creación automáticamente
- Usa `@Bean` cuando necesites configuración personalizada, trabajar con clases de terceros, o aplicar lógica compleja durante la creación del bean