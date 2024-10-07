- POJO in Java stands for Plain Old Java Object. 


### JPA - Java Persistant API 
- It is basically a set of rules that provide us the way to acheive ORM.
 - A way to achieve ORM, includes interfaces and annotations that you use in your Java classes, requires a persistence provider (ORM tools) for implementation.
 - To use JPA, you need a persistence provider. A persistence provider is a specific implementation of the JPA specification. Examples of JPA persistence providers include Hibernate, EclipseLink, and OpenJPA. These providers implement the JPA interfaces and provide the underlying functionality to interact with databases.
 - Spring Data JPA is built on top of the JPA (Java Persistence API) specification, but it is not a JPA implementation itself. Instead, it simplifies working with JPA by providing higher-level abstractions and utilities. However, to use Spring Data JPA effectively, you still need a JPA implementation, such as Hibernate, EclipseLink, or another JPA-compliant provider, to handle the actual database interactions.
 - JPA is primarily designed for working with relational databases, where data is stored in tables with a predefined schema. MongoDB, on the other hand, is a NoSQL database that uses a different data model, typically based on collections of documents, which are schema-less or have flexible schemas. This fundamental difference in data models and storage structures is why JPA is not used with MongoDB.
 - Query Method DSL and Criteria API are two different ways to interact with a database when using Spring Data JPA for relational databases and Spring Data MongoDB for MongoDB databases.
Spring Data JPA is a part of the Spring Framework that simplifies data access in Java applications, while Spring Data MongoDB provides similar functionality for MongoDB.
 - Query Method DSL is a simple and convenient way to create queries based on method naming conventions, while the Criteria API offers a more dynamic and programmatic approach for building complex and custom queries

# Spring Security

### WebSecurityConfigurerAdapter
WebSecurityConfigurerAdapter is a utility class in the Spring Security framework that provides default configurations and allows customization of certain features. By extending it, you can configure and customize Spring Security for your application needs.
#### Configure method:
This method provides a way to configure how requests are secured. It defines how request matching should be done and what security actions should be applied.
  - ```http.authorizeRequests():``` This tells Spring Security to start authorizing the requests.

  - ```.antMatchers("/hello").permitAll():``` This part specifies that HTTP requests matching the path /hello should be permitted (allowed) for all users, whether they are authenticated or not.
  
  - ```.anyRequest().authenticated():``` This is a more general matcher that specifies any request (not already matched by previous matchers)  should be authenticated, meaning users have to provide valid credentials to access these endpoints.

  - ```.and():``` This is a method to join several configurations. It helps to continue the configuration from the root (HttpSecurity).
  
  - ```.formLogin():``` This enables form-based authentication. By default, it will provide a form for the user to enter their username 
  and password. If the user is not authenticated and they try to access a secured endpoint, they'll be redirected to the default login form.

#### TO BE NOTED:
  - When we use the .formLogin() method in our security configuration without specifying .loginPage("/custom-path"), the default login page becomes active.
  - Spring Security provides an in-built controller that handles the /login path. This controller is responsible for rendering the default login form when a GET request is made to /login.
  - By default, Spring Security also provides logout functionality. When .logout() is configured, a POST request to /logout will log the user out and invalidate their session.
  - Basic Authentication by design is stateless.
  - Some applications do mix Basic Authentication with session management for various reasons. This isn't standard behavior and requires additional setup and logic. In such scenarios, once the user's credentials are verified via Basic Authentication, a session might be established, and the client is provided a session cookie. This way, the client won't need to send the Authorization header with every request, and the server can rely on the session cookie to identify the authenticated user.

When you log in with Spring Security, it manages your authentication across multiple requests, despite HTTP being stateless.
1. Session Creation: After successful authentication, an HTTP session is formed. Your authentication details are stored in this session.
2. Session Cookie: A JSESSIONID cookie is sent to your browser, which gets sent back with subsequent requests, helping the server recognize your session.
3.SecurityContext: Using the JSESSIONID, Spring Security fetches your authentication details for each request.
4. Session Timeout: Sessions have a limited life. If you're inactive past this limit, you're logged out. 5. Logout: When logging out, your session ends, and the related cookie is removed.
6. Remember-Me: Spring Security can remember you even after the session ends using a different persistent cookie (typically have a longer lifespan).
In essence, Spring Security leverages sessions and cookies, mainly JSESSIONID, to ensure you remain authenticated across requests.

CSRF (Cross-Site Request Forgery) is a type of attack on web applications. It happens when an attacker tricks a user into doing something they didn't intend to do on a different website where they are already logged in. Spring Boot Security uses CSRF protection to stop these tricks and keep your application safe.
