# MALIC

## Tabla de contenido

- [MUSKER](#intro)
- [Sustainable Development Goals](#sdg)
- [ConfiguraciónLocal](#local)
- [Requerimientos Aplicación](#req)

<h2 id="intro">MUSKER</h2>

Proporcionar un sistema para el seguimiento y gestión de los animales salvajes en situaciones de gravedad (heridos, malnutrición, desubicados) desde su recogida hasta su posible recuperación y puesta en liberación, contando con el apoyo de personas civiles. 


<h2 id="sdg">Sustainable Development Goals</h2>

Proteger, restablecer y promover el uso sostenible de los ecosistemas terrestres, gestionar sosteniblemente los bosques, luchar contra la desertificación, detener e invertir la degradación de las tierras y detener la pérdida de biodiversidad.


<h2 id="local">Configuración Local</h2>

Para que las aplicaciones de REST y WEB se puedan ejecutar en local es necesario modificar los siguientes ficheros de propiedades, application.properties y musker.properties. En la moyoria de los casos solo se debera descomentar la nueva modificación y comentar la linea no necesaria, para comentar utilizar el signo (#)

Para el caso de REST: 

1) application.properties
```java
    // De:
    server.port=443    
    // A:
    server.port=8080
```

```java
    // De:
    spring.datasource.url=jdbc:mysql://10.128.0.5:3306/musker    
    // A:
    spring.datasource.url=jdbc:mysql://localhost:3306/musker
```

2) musker.properties
```java
    // De:
    IMG="/WEB-INF/classes/static"    
    // A:
    IMG="T:/POPBL6/MUSKER/MALIC/RestSpring/Rest/src/main/resources/static"
```

```java
    // De:
    REMOTE=1    
    // A:
    REMOTE=0
```

Para el caso de WEB: 

1) application.properties
```java
    // De:
    server.port=443    
    // A:
    server.port=80
```

```java
    // De:
    spring.datasource.url=jdbc:mysql://10.128.0.5:3306/musker    
    // A:
    spring.datasource.url=jdbc:mysql://localhost:3306/musker
```
2) musker.properties
```java
    // De:
    URL=https://musker.duckdns.org/api    
    // A:
    URL=http://localhost:8080/api
```

```java
    // De:
    IMG="/WEB-INF/classes/static"    
    // A:
    IMG="T:/POPBL6/MUSKER/MALIC/WebSpring/musker/src/main/resources/static"
```

```java
    // De:
    REMOTE=1    
    // A:
    REMOTE=0
```
<h2 id="req">Requerimientos</h2>

La aplicación se puede ejecutar en ultima versión de los siguientes navegadores:

| | Chrome | Firefox | Internet Explorer | Opera | Safari |
| - | - | - | - | - | - |
| Android | Supported | Supported | N/A | Not Supported | N/A |
| iOS | Supported | N/A | N/A | Not Supported | Supported |
| Mac OS X | Supported | Supported | N/A | Supported | Supported |
| Windows | Supported | Supported | Supported | Supported | Not Supported |

### Versión de componenten

- Java 11
- MySQL 8.*

### Credits

Las aplicación ha sido desarolladas y testeados por los estudiantes de la univerrsidad [Mondragon Faculty of Engineering](https://www.mondragon.edu/en/faculty-of-engineering). Los miembros el equipo son:

- Jon Astigarraga.
- Andoni Ibarguren.
- Mohamed Mchichou.
- Ibai Lopez.
- Estalyn Curay.
