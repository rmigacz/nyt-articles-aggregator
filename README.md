# New York Times articles aggregator

Searching for New York Times articles with the use of caching.  
It is a monolithic Java Spring Boot application, implementing Hexagonal architecture.  
Inspiration: https://github.com/jakubnabrdalik/hentai

## Getting started

### Prerequisites

- Java SE Development Kit 8 or higher
- Docker
- New York Times API key:
    - See *Register apps* section: https://developer.nytimes.com/get-started

### Available profiles

The project offers 4 different Maven build profiles - three of them are related  with the caching, 
one with running integration tests.

|Profile name|Description|Properties names|Properties default values|
|---|---|---|---|
|without-cache|disables caching|-|-|
|caffeine-cache|enables in-memory caching with Caffeine|caffeine-spec|initialCapacity=100,maximumSize=500,expireAfterAccess=5m,recordStats|
|redis-cache|enables caching with Redis|redis-port, redis-host, redis-password|6379, localhost, -|
|integration-test|runs integration tests with disabled caching|-|-|

### Installing 

TODO

## Deployment

TODO

## Code style

https://google.github.io/styleguide/javaguide.html

## Built With

* [Spring Boot](https://spring.io/projects/spring-boot) - cache server
* [Caffeine](https://redis.io/) - cache db for the server
* [Redis](https://redis.io/) - cache db for the server
* [Maven](https://maven.apache.org/) - dependency management
* [Docker](https://maven.apache.org/) - dependency management
* [Spock](http://spockframework.org/) - testing and specification framework