# sequra-commerce

Desafío de codificación backend para Sequra

# Inicio

Clone o descargue el repositorio https://github.com/RobelDani/sequra-commerce

# Requisitos previos

Abra el proyecto en IDE (preferido Intellij IDEA) y considere instalar Maven Server o Maven plugin server. Ejecute el
siguiente comando para compilar el proyecto.

mvn clean install

# Arrancar proyecto

Ejecute Class Application.class en su servidor IDE o copie engine-[versión].jar en su servidor Tomcat

# Consultar BBDD

Se ha creado una base de datos en memoria con H2 y para poder consultar dicha bbdd basta con poner en su navegador http:
//[serverIp]:8080/h2-console con los datos siguientes:

    Saved Settings: Generic H2 (Embedded)
    Setting Name: Generic H2 (Embedded)

    Driver Class: org.h2.Driver
    JDBC URL: jdbc:h2:mem:testdb
    User Name: sa
    Password:

# Ejemplo de prueba con Postman

Servicio de prueba Sequra URL Servicio: http://[serverIp]:8080/api/disbursements

Request

    "merchantId": 1
    "startDate": "2018-10-12"
    "endDate": "2018-10-19"

Response

    {
        "merchantsRS": [{
            "merchantId": 1,
            "disbursement": 2026.56,
            "startWeek": "2018-01-08",
            "endWeek": "2018-01-14"
        }, {
            "merchantId": 1,
            "disbursement": 1236.17,
            "startWeek": "2018-01-01",
            "endWeek": "2018-01-07"
        }]
    }

# Scheduled

Se ha creado un cron que se ejecutará todos los lunes del año a las 23 horas que calculará los desembolsos por semanas
para cada comerciantes siempre hayan finalizado. La primera carga de desembolsos se realizará el primer lunes después de
arrancar el proyecto.

# Autor

Roberto Daniel Blanco Parra - Trabajo inicial