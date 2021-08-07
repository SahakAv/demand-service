## About The Project


Demand service DISQO interview task implementation
The main idea of this platform would be to provide service to the customers on demand.


### Built With
* Java 11
* Spring Boot
* Zuul Netflix Proxy
* Feign declarative client
* PostgreSQL database
* Eureka service discovery server
* Docker  compose 


### High  level architecture overview

Eureka server and ZUUL proxy are build to have single JWT authentication 
entrypoint and proxy them to microservices


<p align="center">
    <img src="images/diagram.PNG" alt="Logo">
</p>

Auth-service is deployed as ZUUL proxy which will handle all requests, do authentication with login service
then verify requested microservice service name from Eureka service discovery and request to MS
Authentication service is a single entry point for all requests to all microservices, it will proxy them by a registered service in Eureka.  For authentication, JWT tokens are used.
Communication between microservices done by Feign declarative rest client.


### Local deployment

1. Clone the repo
2. Build all microservices by a gradle
   ```sh
   gradle build
   ```
3. Build and up docker compose 
   ```sh
   docker-compose build && docker-compose up
   ```
4. Verify services 
* 8761 port should be opened for Eureka http://localhost:8761/
* 8080 proxy authentication service should be deployed


## Usage
For auth can be requested 
http://localhost:8080/login-service/auth
with body
```json
   {
   "username" : "admin",
    "password" : "admin"
}
   ```
response will be JWT token
```json
  eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pMW4iLCJpYXQiOjE2MjgzMjQ3NDQsImV4cCI6MTYyODQxMTE0NH0.ZQDHe2Cvput2tgXpPCvyTzKt_mnRRHKDSU66TtbDvvVgDe35GGtcJP3qiYcbupF69lM-bq-pHrwPjk2egS1OpQ
   ```

Two users are inserted for easy testing purposes
* username - admin  password - admin  role SERVICE_PROVIDER
* username - user  password - user  role CUSTOMER


Customer users can be registered
POST   
```sh
  http://localhost:8080/login-service/signup
   ```

with body

```json
 {
   "username" : "username",
   "password" : "password",
   "userType" : "usertype"
}
   ```
then login with auth to get JWT token


# Service flow example
All requests should use jwt token on Authorization header
### Create service provider
 
POST 
```sh
http://localhost:8080/provider-service/provider
 ```
body 
```sh
{
    "serviceTypeName" : "clean",
    "serviceProviderName" : "CLEANING service"
}
 ```
response 
```sh
{
    "id": 1,
    "serviceType": {
        "id": 1,
        "serviceName": "clean"
    },
    "name": "CLEANING service",
    "owner": "admin"
}
 ```
### Create customer request
Service type should exist, service provider is optional(if exist will be validated )

POST
```sh
http://localhost:8080/customer-service/customer/request
 ```
body
```sh
{   
    "serviceType" : "clean",
    "serviceProvider" : "CLEANING service",
     "address" : "some address",
      "date" :  "2015-05-04"
}
 ```
response
```sh
{
    "id": 7,
    "serviceType": "clean",
    "address": "some address ",
    "date": "2015-05-04",
    "status": "NEW",
    "assigned": null
}
 ```
### Verify requests that can be assigned
Service provider can verify new created requests
GET
```sh
http://localhost:8080/provider-service/provider/requests/new
 ```

response
```sh
[
    {
        "id": "6",
        "serviceType": "clean",
        "date": "2015-05-04",
        "address": "Cempty ",
        "status": "NEW",
        "owner": "user",
        "assigned": null
    },
    {
        "id": "7",
        "serviceType": "clean",
        "date": "2015-05-04",
        "address": "Cempty ",
        "status": "NEW",
        "owner": "user",
        "assigned": null
    }
]
 ```

### Accept request
Service provider can verify new created requests
GET
```sh
http://localhost:8080/provider-service/provider/request/accept/11/CLEANING service
 ```

response
```sh
{
    "id": "6",
    "serviceType": "clean",
    "date": "2015-05-04",
    "address": "Cempty ",
    "status": "ASSIGNED_TO_PROVIDER",
    "owner": "user",
    "assigned": "CLEANING service"
}
 ```

### Close request
Service provider can verify new created requests
GET
```sh
http://localhost:8080/provider-service/provider/request/close/6
 ```

response
```sh
{
    "id": "6",
    "serviceType": "clean",
    "date": "2015-05-04",
    "address": "Cempty ",
    "status": "CLOSED",
    "owner": "user",
    "assigned": "CLEANING service"
}
 ```