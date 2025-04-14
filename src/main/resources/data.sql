INSERT INTO surgery(surgery_id, name, address, phone) VALUES
('S10', 'Surgery 10', '10 Main Street', '555-0010'),
('S13', 'Surgery 13', '13 Main Street', '555-0013'),
('S15', 'Surgery 15', '15 Main Street', '555-0015');


INSERT INTO patient(patient_id, first_name, last_name, phone, email, address, dob, has_unpaid_bill) VALUES
('P100', 'Gillian', 'White', '555-1000', 'gillian.white@example.com', '1 Oak Lane', '1985-02-10', false),
('P105', 'Jill', 'Bell', '555-1005', 'jill.bell@example.com', '2 Oak Lane', '1990-05-15', false),
('P108', 'Ian', 'MacKay', '555-1008', 'ian.mackay@example.com', '3 Oak Lane', '1980-08-20', false),
('P110', 'John', 'Walker', '555-1010', 'john.walker@example.com', '4 Oak Lane', '1975-12-01', false);

INSERT INTO dentist(dentist_id, first_name, last_name, phone, email, specialization) VALUES
('D001', 'Tony', 'Smith', '555-2001', 'tony.smith@example.com', 'General'),
('D002', 'Helen', 'Pearson', '555-2002', 'helen.pearson@example.com', 'General'),
('D003', 'Robin', 'Plevin', '555-2003', 'robin.plevin@example.com', 'General');


INSERT INTO appointment(appointment_id, date, time, status, dentist_dentist_id, patient_patient_id, surgery_surgery_id) VALUES
('A001', '2013-09-12', '10:00:00', 'Scheduled', 'D001', 'P100', 'S15'),
('A002', '2013-09-12', '12:00:00', 'Scheduled', 'D001', 'P105', 'S15'),
('A003', '2013-09-13', '10:00:00', 'Scheduled', 'D002', 'P108', 'S10'),
('A004', '2013-09-14', '14:00:00', 'Scheduled', 'D002', 'P108', 'S10'),
('A005', '2013-09-14', '16:30:00', 'Scheduled', 'D003', 'P105', 'S15'),
('A006', '2013-09-15', '18:00:00', 'Scheduled', 'D003', 'P110', 'S13');


