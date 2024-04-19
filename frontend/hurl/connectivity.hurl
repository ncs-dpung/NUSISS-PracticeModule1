GET {{host}}

HTTP 200
[Asserts]
xpath "string(//html/head/title)" == "AdvancedInventoryManagementSystem"
