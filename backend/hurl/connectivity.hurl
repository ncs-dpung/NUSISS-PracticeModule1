# Healthcheck
GET {{host}}/api/healthcheck

HTTP 200
[Asserts]
body contains "HEALTHY"

# Admin Login & get token
POST {{host}}/auth/login
{
  "username": "admin",
  "password": "password"
} 

HTTP 200
[Captures]
jwt: jsonpath "$.jwt"

# Admin Get Staff
GET {{host}}/staff
Authorization: Bearer {{jwt}}

HTTP 200
