create table geodata
(
    ip_id         bigint not null PRIMARY KEY,
    city          varchar(255),
    full_location varchar(255),
    latitude      double,
    longitude     double
);