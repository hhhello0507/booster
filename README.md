# My Auth Template

### Feature
- sign in
- sign up
- refresh
- OAuth2 sign in

### Dependencies
- [jsonwebtoken](https://github.com/jwtk/jjwt)
- [google-api-client](https://github.com/googleapis/google-api-java-client)
- Spring jpa
- Spring security
- Spring web
- Spring validation
- Spring test
- MySQL
- H2 database (Test)

### Architecture

```yaml
├── api # Controller, Service, DTO
│   ├── feature
│   │   ├── Controller.kt
│   │   ├── Service.kt
│   │   ├── data
│   │   │   ├── enumeration
│   │   │   ├── req
│   │   │   └── res
│   │   └── infra
│   └── core # Global code for api
├── foundation # Repository, Entity
│   └── feature
│       ├── Repository.kt
│       └── data
│           ├── entity
│           └── enumeration
├── global # Common global code
└── internal # In system available
    ├── core # Global code for api
    └── feature
```
