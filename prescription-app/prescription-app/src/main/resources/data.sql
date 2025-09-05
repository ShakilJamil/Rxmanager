-- Initialize the users table with a default admin user
INSERT INTO users (username, password) VALUES
('admin', '$2a$10$EqIn3.LBp2z6uk/3jITAbeCDOvu4HUs6rbFFijGEJLu4Zck6bZf0y');




-- Initialize the prescription table with sample data
INSERT INTO prescription (prescription_date, patient_name, patient_age, patient_gender, diagnosis, medicines, next_visit_date) VALUES
('2025-09-01', 'John Doe', 30, 'Male', 'Common cold', 'Paracetamol 500mg, Cetirizine 10mg', '2025-09-08'),
('2025-09-02', 'Jane Smith', 45, 'Female', 'Hypertension', 'Amlodipine 5mg', NULL),
('2025-09-03', 'Alice Johnson', 25, 'Other', 'Allergic rhinitis', 'Loratadine 10mg', '2025-09-10'),
('2025-09-01', 'Bob Wilson', 60, 'Male', 'Diabetes', 'Metformin 500mg', '2025-09-15'),
('2025-09-04', 'Sarah Brown', 28, 'Female', 'Migraine', 'Sumatriptan 50mg', NULL);