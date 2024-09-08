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