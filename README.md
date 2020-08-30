# Caching Data with Spring Boot

- https://docs.spring.io/spring-boot/docs/2.1.6.RELEASE/reference/html/boot-features-caching.html
- http://localhost:8080/h2-console

### Annotations

- @EnableCaching - Enables Spring Caching functionality.
- @Cacheable - First check the cached method before actually invoking the method and then caching the result.
- @CacheEvict - Remove one or more/all values so that fresh values can be loaded into the cache again.

### Person URLs

| RESTful URL  | HTTP Action  |  Noun | Business Operation  |Sample Valid Request Body | 
|---|:-------------:|:-------------:|:-------------:|:-------------:|
|/api/person     |GET   |person|get all persons    ||  
|/api/person     |POST  |person|create person      |[JSON](#person)|  
|/api/person/{id}|PUT   |person|update person      |[JSON](#person)| 
|/api/person/{id}|GET   |person|get person by id   ||  
|/api/person/{id}|DELETE|person|delete person by id||  

### Sample Valid JSON Request Body

##### <a id="person">Person -> /api/person</a>
```json
{
	"name": "Johnny",
	"email": "examplex@domain.com",
	"mobileNumber": "123456789x"
}
```