CREATE KEYSPACE productkeyspace WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };
CREATE TABLE productkeyspace.Product (id TimeUUID PRIMARY KEY, name text, category text, company text, price float);


