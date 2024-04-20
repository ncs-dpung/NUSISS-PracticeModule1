# Healthcheck
GET {{host}}/healthcheck

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

# Get Orders
GET {{host}}/orders/all
Authorization: Bearer {{jwt}}

HTTP 200
