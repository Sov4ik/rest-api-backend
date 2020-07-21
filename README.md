# Test jwtApplication with Spring Boot 2 & Hibernate ORM

## About
This is a test application that shows how to work with:
* **[Spring Boot](https://spring.io/projects/spring-boot)**
* **[Spring Data JPA](https://spring.io/projects/spring-data-jpa)**
* **[Spring Security](https://spring.io/projects/spring-security)**
* **[JWT (JSON Web Token)](https://jwt.io)**
* **[Spring MVC Tests](https://spring.io/guides/gs/testing-web/)**
* **[PostgreSQL Database](https://www.postgresql.org/)**
* **[RESTful API](https://restfulapi.net)**

## Requirements
This demo is build with with Maven 4.0.0 and Java 8.

## Dependency
– If you want to use PostgreSQL:
```xml
<dependency>
  <groupId>org.postgresql</groupId>
  <artifactId>postgresql</artifactId>
  <scope>runtime</scope>
</dependency>
```
– or MySQL:
```xml
<dependency>
  <groupId>mysql</groupId>
  <artifactId>mysql-connector-java</artifactId>
  <scope>runtime</scope>
</dependency>
```
## Configure Spring Datasource, JPA, App properties
Open `src/main/resources/application.properties`
- For PostgreSQL:
```
spring.datasource.url= jdbc:postgresql://localhost:5432/testdb
spring.datasource.username= postgres
spring.datasource.password= root

spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation= true
spring.jpa.properties.hibernate.dialect= org.hibernate.dialect.PostgreSQLDialect

# Hibernate ddl auto (create, create-drop, validate, update)
spring.jpa.hibernate.ddl-auto= update

# App Properties
app.jwtSecret= SecretKey
app.jwtExpirationMs= 19089032
```
- For MySQL
```
spring.datasource.url= jdbc:mysql://localhost:3306/testdb?useSSL=false
#(problem with timezone) spring.datasource.url=jdbc:mysql://localhost:3306/testdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC

#spring.datasource.url=jdbc:mysql://localhost:3306/mydb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC

spring.datasource.username= root
spring.datasource.password= root

spring.jpa.properties.hibernate.dialect= org.hibernate.dialect.MySQL5InnoDBDialect
spring.jpa.hibernate.ddl-auto= update

# App Properties
app.jwtSecret= SecretKey
app.jwtExpirationMs= 19089032
```
## Run Spring Boot application
```
mvn spring-boot:run
```

## Run following SQL insert statements
```
INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');
### Generating password hashes for new users
```

I'm using [bcrypt](https://en.wikipedia.org/wiki/Bcrypt) to encode passwords.
