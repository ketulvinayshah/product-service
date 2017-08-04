CREATE KEYSPACE productkeyspace WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };
CREATE TABLE IF NOT EXISTS productkeyspace.Product (id timeuuid,
    name text,
    category text,
    company text,
    price float,
    PRIMARY KEY (id, name))
WITH CLUSTERING ORDER BY (name DESC);