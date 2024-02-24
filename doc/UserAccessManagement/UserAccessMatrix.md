| Functionality \ Role              | Admin | Sales Rep. | Warehouse Mgr.  | Procurement Off. |
|-----------------------------------|:-----:|:----------:|:---------------:|:----------------:|
| User Account Mgmt.                |   ✅   |            |                 |                  |
| Role-Based Access                 |   ✅   |            |                 |                  |
| Product Inventory (CRUD & Search) |   ✅   |     🔍      |        ✅        |        ✅         |
| Customer Order Management         |   ✅   |     ✅      |       🔍        |                  |
| Supplier Order Management         |   ✅   |            |        🔍         |        ✅         |
| Delivery Tracking (To Customer)   |   ✅   |     ✅      |        ✅        |                  |
| Delivery Tracking (From Supplier) |   ✅   |            |        ✅        |        ✅         |
| Delivery Updates (To Customer)    |   ✅   |     ✅      |        ✅        |                  |
| Delivery Updates (From Supplier)  |   ✅   |            |        ✅        |        ✅         |
| Inventory Reports                 |   ✅   |            |        ✅        |                  |
| Supplier Management               |   ✅   |            |                 |        ✅         |
| Customer Profiles                 |   ✅   |     ✅      |                 |                  |
| Customer Order Creation           |   ✅   |     ✅      |                 |                  |
| Supplier Order Creation           |   ✅   |   [..](..)         |                 |        ✅         |
| Supplier Profile Creation         |   ✅   |            |                 |        ✅         |

- ✅ indicates full access.
- 🔍 indicates read-only access.

- Page ( C, R, U , D)
- roles: 4
- Option 1： JWT: [ [Roles] ] + hardcoded if-else
- Option 2: JWT: [ [Inventory] : [C,R,U,D], [User] : [C,R,U,D] ] - FE (Final Decision) ✅

- Role - RoleName , Key, C, R, U, D - BE (Final Decision) ✅
- Data example - PO, Inventory, true, true, true, true

- Role - RoleName, Key, Access
- Data example - PO, Inventory, 【C,R,U,D】

- Reson of choosing Option 2:
    - More flexible, robust
    - Easier to maintain

- Cons:
    - More complex to implement
