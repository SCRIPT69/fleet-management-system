CREATE TABLE employee (
    personal_number VARCHAR(11) PRIMARY KEY,
    first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR(30) NOT NULL,
    date_of_birth DATE NOT NULL CHECK (
        date_of_birth >= DATE '1900-01-01'
        AND date_of_birth < CURRENT_DATE
    )
);

CREATE TABLE phone (
    personal_number VARCHAR(11) NOT NULL,
    phone_number VARCHAR(20) NOT NULL,
    PRIMARY KEY (personal_number, phone_number),
    FOREIGN KEY (personal_number) REFERENCES employee(personal_number) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE logistician (
    personal_number VARCHAR(11) PRIMARY KEY,
    FOREIGN KEY (personal_number) REFERENCES employee(personal_number) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE manages (
    subordinate_personal_number VARCHAR(11) PRIMARY KEY,
    supervisor_personal_number VARCHAR(11) NOT NULL,
    CHECK (subordinate_personal_number <> supervisor_personal_number),
    FOREIGN KEY (subordinate_personal_number) REFERENCES logistician(personal_number) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (supervisor_personal_number) REFERENCES logistician(personal_number) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE driver (
    personal_number VARCHAR(11) PRIMARY KEY,
    license_number VARCHAR(20) NOT NULL UNIQUE,
    FOREIGN KEY (personal_number) REFERENCES employee(personal_number) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE truck (
    license_plate VARCHAR(10) PRIMARY KEY,
    vin CHAR(17) UNIQUE NOT NULL,
    status VARCHAR(20) NOT NULL CHECK (status IN ('available', 'on_trip', 'in_service', 'requires_inspection')),
    max_weight NUMERIC(10, 2) NOT NULL CHECK (max_weight > 0)
);

CREATE TABLE trip (
    trip_id SERIAL PRIMARY KEY,
    status VARCHAR(20) NOT NULL CHECK (status IN ('planned', 'in_progress', 'completed')) DEFAULT 'planned',
    departure_time TIMESTAMP,
    arrival_time TIMESTAMP,
    CHECK (
        arrival_time IS NULL
        OR (departure_time IS NOT NULL AND arrival_time > departure_time)
    ),
    driver_personal_number VARCHAR(11) NOT NULL,
    logistician_personal_number VARCHAR(11) NOT NULL,
    license_plate VARCHAR(10) NOT NULL,
    FOREIGN KEY (driver_personal_number) REFERENCES driver(personal_number) ON DELETE NO ACTION ON UPDATE CASCADE,
    FOREIGN KEY (logistician_personal_number) REFERENCES logistician(personal_number) ON DELETE NO ACTION ON UPDATE CASCADE,
    FOREIGN KEY (license_plate) REFERENCES truck(license_plate) ON DELETE NO ACTION ON UPDATE CASCADE
);

CREATE TABLE client_company (
    company_id CHAR(8) PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    phone VARCHAR(20) NOT NULL,
    email VARCHAR(50) NOT NULL,
    city VARCHAR(50) NOT NULL,
    street VARCHAR(50) NOT NULL,
    house_number VARCHAR(10) NOT NULL
);

CREATE TABLE orders (
    order_id SERIAL PRIMARY KEY,
    price DECIMAL(10, 2) NOT NULL CHECK (price >= 0),
    status VARCHAR(20) NOT NULL CHECK (status IN ('created', 'assigned', 'delivered', 'cancelled')) DEFAULT 'created',
    order_date DATE NOT NULL CHECK (order_date <= CURRENT_DATE),
    weight NUMERIC(10, 2) NOT NULL CHECK (weight > 0),
    pickup_location VARCHAR(50) NOT NULL,
    delivery_location VARCHAR(50) NOT NULL,
    company_id CHAR(8) NOT NULL,
    FOREIGN KEY (company_id) REFERENCES client_company(company_id) ON DELETE NO ACTION ON UPDATE CASCADE
);

CREATE TABLE transports (
    trip_id INT NOT NULL,
    order_id INT NOT NULL,
    PRIMARY KEY (trip_id, order_id),
    FOREIGN KEY (trip_id) REFERENCES trip(trip_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE trip_expense (
    expense_number INT NOT NULL CHECK (expense_number > 0),
    trip_id INT NOT NULL,
    expense_type VARCHAR(20) NOT NULL CHECK (expense_type IN ('fuel', 'toll', 'parking', 'service')),
    amount DECIMAL(10, 2) NOT NULL CHECK (amount >= 0),
    note TEXT,
    PRIMARY KEY (trip_id, expense_number),
    FOREIGN KEY (trip_id) REFERENCES trip(trip_id) ON DELETE CASCADE ON UPDATE CASCADE
);
