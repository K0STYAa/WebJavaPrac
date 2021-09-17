INSERT INTO airports (airport_id, airport_name) VALUES
	(101, 'airp_1'),
	(102, 'airp_2');
	
INSERT INTO airlines (airline_company_id, airline_company_name) VALUES
	(101, 'airline_1'),
	(102, 'airline_2');
	
INSERT INTO customers (customer_id, full_name, address, phone_number, email) VALUES
	(101, 'Kostya', 'adr_1', '34324234', 'email@1.ru'),
	(102, 'Anna', 'adr_2', '4324325214', 'email@2.ru');
	
INSERT INTO flights (flight_number, airline_company_id, departure_id, arrival_id, departure_time, arrival_time, cost, number, miles) VALUES
	(101, 101, 101, 102, '01-01-2021 14:00:00', '01-01-2021 14:52:11', 7000, 5, 300),
	(102, 102, 102, 101, '01-01-2021 16:00:00', '01-01-2021 17:12:09', 11000, 3, 400),
	(103, 101, 102, 101, '01-01-2021 23:30:00', '02-01-2021 00:32:31', 6000, 4, 350);
	
INSERT INTO cust_air_relation (c_a_id, airline_id, customer, discount, miles) VALUES
	(101, 101, 101, 2, 2500),
	(102, 102, 101, 10, 13600),
	(103, 101, 102, 3, 3500),
	(104, 102, 102, 7, 7900);

INSERT INTO paid_tickets (p_t_id, flight_number, customer_id) VALUES
	(101, 101, 101),
	(102, 101, 102),
	(103, 102, 101),
	(104, 103, 102);
