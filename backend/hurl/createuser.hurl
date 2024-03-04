# Healthcheck
GET http://localhost:8080/api/healthcheck

HTTP 200
[Asserts]
body contains "HEALTHY"

# Admin Login & get token
POST http://localhost:8080/auth/login
{
  "username": "admin",
  "password": "password"
} 

HTTP 200
[Captures]
jwt: jsonpath "$.jwt"
