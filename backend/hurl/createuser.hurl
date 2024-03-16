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

# Admin Get Staff
GET http://localhost:8080/staff
Authorization: Bearer {{jwt}}

HTTP 200

# Admin Create Staff
POST http://localhost:8080/staff
Authorization: Bearer {{jwt}}
{
  "firstName": "John",
  "lastName": "Doe",
  "user": {
    "username": "john.doe",
    "email": "john@email.com",
    "roles": []
  }
}

HTTP 200
[Captures]
staff_id: jsonpath "$.staff.id"

# Admin Get Staff
GET http://localhost:8080/staff/{{staff_id}}
Authorization: Bearer {{jwt}}

HTTP 200

# Admin Update Staff
PATCH http://localhost:8080/staff/{{staff_id}}
Authorization: Bearer {{jwt}}
{
  "firstName": "Buck"
}

HTTP 200
[Asserts]
jsonpath "$.firstName" == "Buck"

# Admin Delete Staff
DELETE http://localhost:8080/staff/{{staff_id}}
Authorization: Bearer {{jwt}}

HTTP 204
