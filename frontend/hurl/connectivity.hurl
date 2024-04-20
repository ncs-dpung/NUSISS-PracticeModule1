# FE Healthcheck
GET {{host}}

HTTP 200
[Asserts]
xpath "string(//html/head/title)" == "AdvancedInventoryManagementSystem"

# BE Healthcheck (through FE/nginx proxy)
GET {{host}}/api/healthcheck

HTTP 200
[Asserts]
body contains "HEALTHY"

# Admin Login & get token
POST {{host}}/api/auth/login
{
  "username": "admin",
  "password": "password"
}

HTTP 200
[Captures]
jwt: jsonpath "$.jwt"

# Admin Get Staff
GET {{host}}/api/staff
Authorization: Bearer {{jwt}}

HTTP 200
