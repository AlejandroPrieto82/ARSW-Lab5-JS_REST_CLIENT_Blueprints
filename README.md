# ARSW-Lab5-JS_REST_CLIENT_Blueprints

### Autor: **Alejandro Prieto**
[Alejandro Prieto GitHub](https://github.com/AlejandroPrieto82)

---

## Parte I

### 1. Integración de Beans

Se integraron al proyecto base los *Beans* desarrollados en el ejercicio anterior.
✔ Se copiaron únicamente las clases, **sin archivos de configuración**.
✔ Se verificó la correcta configuración del esquema de inyección de dependencias usando las anotaciones `@Service` y `@Autowired`.

---

### 2. Inicialización de planos en `InMemoryBlueprintPersistence`

El bean de persistencia se modificó para que por defecto se inicialice con al menos **tres planos**, incluyendo **dos planos asociados a un mismo autor**.

![Integracion 3 planos](/img/image-0.png)
**Inicialización con planos por defecto**

---

### 3. Exposición del recurso `/blueprints`

Se configuró el recurso REST `/blueprints` para que retorne, en formato **JSON**, el conjunto de todos los planos.

![Resultado JSON petición GET](/img/image-1.png)
**Respuesta JSON con todos los planos**

---

### 4. Ejecución de la aplicación con Maven

Se validó el funcionamiento de la aplicación ejecutando:

```bash
mvn compile
mvn spring-boot:run
```

![Resultado mvn compile](/img/image-2.png)
**Compilación exitosa**

![Resultado mvn spring-boot](/img/image-3.png)
**Aplicación en ejecución**

---

### 5. Consulta de planos por autor

Se implementó el recurso `/blueprints/{author}`, que retorna en formato JSON todos los planos de un autor específico.
✔ Si el autor no existe, responde con **HTTP 404**.

![Resultado llamar al recurso por autor](/img/image-4.png)
**Consulta por autor**

---

## Parte II

### 1. Manejo de peticiones POST

Se agregó soporte para crear nuevos planos mediante peticiones **POST** al recurso `/blueprints`.

✔ Al enviar un JSON con los detalles del plano, la API lo persiste correctamente.

![Resultado de crear y consultar plano con POST](/img/image-5.png)
**Creación y consulta de un nuevo plano**

---

### 2. Verificación con `curl`

Se probó la creación de un plano usando el comando `curl`, enviando un objeto JSON con autor, nombre y puntos.

---

### 3. Manejo de peticiones PUT

Se implementó soporte para actualizar un plano existente mediante el verbo **PUT** en el recurso:
`/blueprints/{author}/{bpname}`.

![Actualizar plano](/img/image-6.png)
**Actualización de un plano existente**

---

## Parte III

### 1. Análisis de concurrencia

Se identificaron las condiciones de carrera presentes en el sistema y se implementó una solución que evita sincronización global para preservar el rendimiento.

✔ Se reemplazó `HashMap` por `ConcurrentHashMap`.
✔ Se usaron operaciones atómicas como `putIfAbsent` y `replace`.
✔ Se aseguraron copias defensivas para evitar mutaciones compartidas.

El análisis completo se encuentra en el archivo:
[ANALISIS\_CONCURRENCIA.txt](/ANALISIS_CONCURRENCIA.txt)

---

## Generar Javadoc

Para generar la documentación de la API en formato **Javadoc**, ejecute el siguiente comando en la raíz del proyecto:

```cmd
mvn javadoc:javadoc
```

La documentación generada estará disponible en:

```
target/site/apidocs/index.html
target\reports\apidocs\index.html
```

Abra ese archivo en un navegador para explorar la documentación de las clases y métodos de la aplicación.  