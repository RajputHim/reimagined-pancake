# reimagined-pancake
Gorgeous Glam is an Online E-commerce Application where Admin can ADD/REMOVE/VIEW/UPDATE products from the database 
whereas Customer can buy products and track their order delivery status.

# Features
#### Admin
- Registration
- Login and Logout
- Add/Update/Delete/View Product

#### Customer
- Registration
- Login and Logout
- View Products
- Add Products to Cart
- Remove Products from cart
- Place Order
- View all orders

#### Authentication
- Generated session key while login.
- Stored the session key in MySQL and later used it for Authentication.

# ER Diagram
![ER Diagram GorgeousGlam](https://user-images.githubusercontent.com/105914405/220267222-57ecec52-e4d3-42c1-a9e7-4a3f31728670.png)



# Modules
- Admin Module
- Customer Module
- Product Module
- Cart Module
- Address Module
- Orders Module

# Technology Used
- JAVA
- Spring Boot Framework
- Spring MVC
- Spring data JPA
- Maven
- lombok
- Hibernate
- MySQL
- Swagger

# Installation and Run
- Open this project in IDE
- Update the database configuration inside the application.properties file which is present in the src/main/resources folder.
- Update the username and password as per your local database configuration.

```
server.port=8888 
spring.datasource.url=jdbc:mysql://localhost:3306/GorgeousGlam
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver 
spring.datasource.username=your_username_here
spring.datasource.password=your_password_here
```
- Run this project using spring boot

## API Root Endpoint

```
https://localhost:8888/
```

```
https://localhost:8888/swagger-ui.html
```

