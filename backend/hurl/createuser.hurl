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

# Admin Get Staff
GET {{host}}/staff
Authorization: Bearer {{jwt}}

HTTP 200

# Admin Create Staff
POST {{host}}/staff
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
username: jsonpath "$.staff.user.username"
password: jsonpath "$.password"

# Admin Get Staff
GET {{host}}/staff/{{staff_id}}
Authorization: Bearer {{jwt}}

HTTP 200

# Admin Update Staff
PATCH {{host}}/staff/{{staff_id}}
Authorization: Bearer {{jwt}}
{
  "firstName": "Buck"
}

HTTP 200
[Asserts]
jsonpath "$.firstName" == "Buck"

# Staff Login & get token
POST {{host}}/auth/login
{
  "username": "{{username}}",
  "password": "{{password}}"
} 

HTTP 200
[Captures]
staffjwt: jsonpath "$.jwt"

# Staff Get All Staff without authorisation
GET {{host}}/staff
Authorization: Bearer {{staffjwt}}

HTTP 403

# Staff Get Self
GET {{host}}/staff/{{staff_id}}
Authorization: Bearer {{staffjwt}}

HTTP 200

#Admin Delete Staff
DELETE {{host}}/staff/{{staff_id}}
Authorization: Bearer {{jwt}}

HTTP 204

