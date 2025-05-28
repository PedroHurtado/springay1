
# Uso de `ApplicationContext` como Service Locator en Spring

Aunque la forma recomendada de trabajar con Spring es a través de la inyección de dependencias, en algunos casos puede ser útil usar el contenedor como un **Service Locator**, obteniendo beans manualmente.

---

## Ejemplo de servicio

```java
package com.example.service;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {
    public String greet(String name) {
        return "Hola, " + name + "!";
    }
}
```

---

## Obtener el bean desde el `main`

```java
package com.example;

import com.example.service.GreetingService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.example")
public class App {
    public static void main(String[] args) {
        // Crear el ApplicationContext
        ApplicationContext context = new AnnotationConfigApplicationContext(App.class);

        // Obtener el bean manualmente (Service Locator)
        GreetingService greetingService = context.getBean(GreetingService.class);

        // Usar el bean
        System.out.println(greetingService.greet("Pedro"));
    }
}
```

---

## Patrón `ApplicationContextAware` para obtener beans desde cualquier lugar

```java
package com.example.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContext implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringContext.context = applicationContext;
    }

    public static <T> T getBean(Class<T> beanClass) {
        return context.getBean(beanClass);
    }
}
```

### Uso

```java
GreetingService service = SpringContext.getBean(GreetingService.class);
```

---

## Consideraciones

- Crear una nueva instancia de `ApplicationContext` solo es adecuado **fuera del contexto gestionado por Spring** (como en un `main()`).
- Dentro de una aplicación Spring, es mejor usar **inyección de dependencias**.
- Evitar múltiples contenedores en la misma aplicación para no perder coherencia entre beans.

