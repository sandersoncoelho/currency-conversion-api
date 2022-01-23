# Currency Conversion API

This guide walks you through the currency-conversion-api, a RestFull
web service with Spring WebFlux and embedded database. This application
can make conversion between currencies accessing a external service,
[ExchangeRateApi](http://api.exchangeratesapi.io).

##Environment
* Java 11
* Gradle 7.3.3
* Git

## Installation
Download application source:
```bash
$ git clone https://github.com/sandersoncoelho/currency-conversion-api.git
```

## Run application
Access the application root directory, build and run:
```bash
$ cd currency-conversion-api/
$ ./gradlew build
$ ./gradlew bootRun
```

## Access endpoints
Import in postman collection and environments configurations available on
postman directory.

* List currency transactions by user:

Parameters:
```
userId: Long
```
```bash
$ curl --location --request GET 'http://localhost:8080/currency-transaction/user/1'
```

* Get a currency conversion:

Parameters:
```
userId: Long,
originCurrency: String,
originValue: BigDecimal,
destinationCurrency: String
```
```bash
$ curl --location --request GET 'http://localhost:8080/currency-transaction/conversion?userId=1&originCurrency=EUR&originValue=45.34&destinationCurrency=USD'
```

## Access OpenAPI documentation:
In your browser:

```bash
http://localhost:8080/swagger-ui.html
```
## Heroku environment
The application is also available on Heroku service using its default
CI/CD pipelines. In postman, just import heroku_environment.postman_environment.json
to access the application.
* Url application:
```
https://sleepy-bastion-58959.herokuapp.com
https://sleepy-bastion-58959.herokuapp.com/swagger-ui.html
```