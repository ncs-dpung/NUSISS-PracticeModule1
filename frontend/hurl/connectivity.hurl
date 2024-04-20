# FE Healthcheck
GET {{host}}

HTTP 200
[Asserts]
xpath "string(//html/head/title)" == "AdvancedInventoryManagementSystem"

# BE Healthcheck (through FE/nginx proxy)
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

# Admin Get Staff
GET {{host}}/staff
Authorization: Bearer {{jwt}}

HTTP 200
