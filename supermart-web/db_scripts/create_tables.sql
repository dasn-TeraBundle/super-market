CREATE TABLE Users (
  name            VARCHAR2(20) NOT NULL,
  surname         VARCHAR2(30),
  role            VARCHAR2(15) NOT NULL,
  address         VARCHAR2(150),
  salary          NUMBER(5, 2),
  birthday        DATE,
  employment_date DATE,
  phone           VARCHAR2(15),
  username        VARCHAR2(15),
  password        VARCHAR2(100) NOT NULL,
  CONSTRAINT pk_users PRIMARY KEY (username)
);

CREATE TABLE Suppliers (
  id   VARCHAR2(15),
  name VARCHAR2(30) NOT NULL,
  CONSTRAINT pk_suppliers PRIMARY KEY (id)
);

CREATE TABLE Product_Categories (
  id   VARCHAR2(15),
  name VARCHAR2(30),
  CONSTRAINT pk_product_categories PRIMARY KEY (id)
);

CREATE TABLE Products (
  id       VARCHAR2(20),
  name     VARCHAR2(30),
  category VARCHAR2(15),
  supplier VARCHAR2(15),
  quantity NUMBER(5),
  price    NUMBER(7, 2),
  CONSTRAINT pk_products PRIMARY KEY (id),
  CONSTRAINT fk_products_suppliers FOREIGN KEY (supplier) REFERENCES Suppliers (id) ON DELETE CASCADE,
  CONSTRAINT fk_products_prdcat FOREIGN KEY (category) REFERENCES Product_Categories (id) ON DELETE CASCADE
);

CREATE TABLE Invoices (
  id           VARCHAR2(20),
  billing_date TIMESTAMP,
  CONSTRAINT pk_invoices PRIMARY KEY (id)
);

CREATE TABLE Invoices_Products (
  invoice_id VARCHAR2(20),
  product_id VARCHAR2(20),
  quantity   NUMBER(5),
  cost       NUMBER(10, 2),
  CONSTRAINT fk_ip_invoices FOREIGN KEY (invoice_id) REFERENCES Invoices (id),
  CONSTRAINT fk_ip_products FOREIGN KEY (product_id) REFERENCES Products (id)
)