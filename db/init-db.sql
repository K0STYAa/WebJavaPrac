CREATE SEQUENCE airport_id_seq;
CREATE SEQUENCE airline_company_id_seq;
CREATE SEQUENCE customer_id_seq;
CREATE SEQUENCE flight_number_seq;
CREATE SEQUENCE c_a_id_seq;
CREATE SEQUENCE p_t_id_seq;

create table if not exists airports(
	airport_id            integer DEFAULT nextval('airport_id_seq') PRIMARY KEY,
	airport_name          varchar(80) NOT NULL
);

create table if not exists airlines(
	airline_company_id    integer DEFAULT nextval('airline_company_id_seq') PRIMARY KEY,
	airline_company_name  varchar(80) NOT NULL
);

create table if not exists customers(
	customer_id           integer DEFAULT nextval('customer_id_seq') PRIMARY KEY,
	full_name             varchar(150) NOT NULL,
	address               varchar(80) NOT NULL,
	phone_number          varchar(20) NOT NULL,
	email                varchar(50) NOT NULL
);

create table if not exists flights(
	flight_number         integer DEFAULT nextval('flight_number_seq') PRIMARY KEY,
	airline_company_id    integer references airlines ON DELETE CASCADE NOT NULL,
	departure_id          integer references airports ON DELETE CASCADE NOT NULL,
	arrival_id            integer references airports ON DELETE CASCADE NOT NULL,
	departure_time        timestamp NOT NULL,
	arrival_time          timestamp NOT NULL,
	cost                  integer NOT NULL,
	number                integer NOT NULL,
	miles                 integer NOT NULL
);

create table if not exists cust_air_relation(
	c_a_id                integer DEFAULT nextval('c_a_id_seq') PRIMARY KEY,
	airline_id            integer references airlines ON DELETE CASCADE NOT NULL,
	customer              integer references customers ON DELETE CASCADE NOT NULL,
	discount              integer NOT NULL,
	miles                 integer NOT NULL
);

create table if not exists paid_tickets(
	p_t_id                integer DEFAULT nextval('p_t_id_seq') PRIMARY KEY,
	flight_number         integer references flights ON DELETE CASCADE NOT NULL,
	customer_id           integer references customers ON DELETE CASCADE NOT NULL
);
