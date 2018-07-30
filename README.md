# Ping Application 

## Prerequisite for Running
Run **_ping_warmup.sql_** to set up DB table

Additionally you will need to include a **_application.properties_** file under **_ping-app/src/main/resources_**

Example file contents:

```
# App Properties
server.port=8091

## Spring DATASOURCE (DataSourceAutoConfiguration & DataSourceProperties)
spring.datasource.url=jdbc:mysql://localhost:3306/ping_warmup?useSSL=false
spring.datasource.username=[username goes here]
spring.datasource.password=[password goes here]
spring.datasource.driver-class-name=com.mysql.jdbc.Driver


## Hibernate Properties
# The SQL dialect makes Hibernate generate better SQL for the chosen database
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.naming.implicit-strategy=org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyJpaImpl
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
spring.jpa.show-sql=true
```

## How to Run via CLI
```
/ping-app> gradle bootRun
```

or 

```
/ping-app> ./gradlew bootRun
```

## Application Endpoints
POST Request for Creating a Ping record
```
http://localhost:8091/adrian-rendon/ping
```
GET Request for fetching a Ping Summary
```
http://localhost:8091/adrian-rendon/ping/summary
```