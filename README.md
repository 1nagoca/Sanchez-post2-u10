# PostContenido 2 - Unidad 10

## Pruebas E2E con Selenium, Postman y Newman

### Autor

Camilo Sánchez

### Descripción del Proyecto

Este proyecto corresponde al laboratorio PostContenido 2 de la Unidad 10 de Programación Web.

El objetivo es implementar pruebas de extremo a extremo (E2E) para una aplicación ToDo desarrollada con Spring Boot, utilizando:

* Selenium WebDriver
* Page Object Model (POM)
* Postman
* Newman
* GitHub Actions

Las pruebas verifican el correcto funcionamiento de la interfaz web, la API REST y la integración continua mediante automatización.


---

## Estructura del Proyecto

```text
src
├── main
│   └── java
│
├── test
│   └── java
│       └── e2e
│           ├── TareasPage
│           ├── NuevaTareaPage
│           └── TareasE2ETest
│
postman
├── ColeccionToDo.json
├── env-local.json
└── env-ci.json

.github
└── workflows
    └── api-tests.yml
```

### Ejecución de Pruebas Selenium

Ejecutar:

```bash
mvn test
```

Los tests se ejecutan en modo headless utilizando Google Chrome.

---

## Conclusiones

Se implementó una estrategia completa de pruebas E2E utilizando Selenium WebDriver para la interfaz gráfica, Postman para validar la API REST y Newman integrado con GitHub Actions para automatizar la ejecución en integración continua. Esto permite detectar errores tempranamente y garantizar la calidad del software antes de cada despliegue.