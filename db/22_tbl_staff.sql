START TRANSACTION;
INSERT INTO inventory_db.tbl_user (user_id,created_at,created_by,updated_at,updated_by,email,password,user_name,staff_id) VALUES
	 (1,'2024-04-11 13:23:55.619000','SYSTEM','2024-04-11 13:23:55.637000','SYSTEM','admin@email.com','$2a$10$f3YWCTxe8zXCeO2syi6wtu9G/.UgQXt9m1B2EIZ0NUG7a6BRZTvSu','admin',NULL);
INSERT INTO inventory_db.tbl_staff (staff_id,created_at,created_by,updated_at,updated_by,first_name,last_name,user_id) VALUES
	 (1,'2024-04-11 13:23:55.629000','SYSTEM','2024-04-11 13:23:55.629000','SYSTEM','admin','admin',1);
UPDATE inventory_db.tbl_user
SET staff_id = 1
WHERE user_id = 1;
INSERT INTO inventory_db.tbl_user (user_id,created_at,created_by,updated_at,updated_by,email,password,user_name,staff_id) VALUES
	 (2,'2024-04-11 13:23:55.619000','SYSTEM','2024-04-11 13:23:55.637000','SYSTEM','sales@email.com','$2a$10$f3YWCTxe8zXCeO2syi6wtu9G/.UgQXt9m1B2EIZ0NUG7a6BRZTvSu','sales_rep',NULL);
INSERT INTO inventory_db.tbl_staff (staff_id,created_at,created_by,updated_at,updated_by,first_name,last_name,user_id) VALUES
	 (2,'2024-04-11 13:23:55.629000','SYSTEM','2024-04-11 13:23:55.629000','SYSTEM','sales','sales',2);
UPDATE inventory_db.tbl_user
SET staff_id = 2
WHERE user_id = 2;
INSERT INTO inventory_db.tbl_user (user_id,created_at,created_by,updated_at,updated_by,email,password,user_name,staff_id) VALUES
	 (3,'2024-04-11 13:23:55.619000','SYSTEM','2024-04-11 13:23:55.637000','SYSTEM','warehouse@email.com','$2a$10$f3YWCTxe8zXCeO2syi6wtu9G/.UgQXt9m1B2EIZ0NUG7a6BRZTvSu','warehouse_mgr',NULL);
INSERT INTO inventory_db.tbl_staff (staff_id,created_at,created_by,updated_at,updated_by,first_name,last_name,user_id) VALUES
	 (3,'2024-04-11 13:23:55.629000','SYSTEM','2024-04-11 13:23:55.629000','SYSTEM','warehouse','warehouse',3);
UPDATE inventory_db.tbl_user
SET staff_id = 3
WHERE user_id = 3;
INSERT INTO inventory_db.tbl_user (user_id,created_at,created_by,updated_at,updated_by,email,password,user_name,staff_id) VALUES
	 (4,'2024-04-11 13:23:55.619000','SYSTEM','2024-04-11 13:23:55.637000','SYSTEM','procurement@email.com','$2a$10$f3YWCTxe8zXCeO2syi6wtu9G/.UgQXt9m1B2EIZ0NUG7a6BRZTvSu','procurement_offr',NULL);
INSERT INTO inventory_db.tbl_staff (staff_id,created_at,created_by,updated_at,updated_by,first_name,last_name,user_id) VALUES
	 (4,'2024-04-11 13:23:55.629000','SYSTEM','2024-04-11 13:23:55.629000','SYSTEM','procurement','procurement',4);
UPDATE inventory_db.tbl_user
SET staff_id = 4
WHERE user_id = 4;
COMMIT;
