create table app.goods (
  code        varchar(255) primary key,
  name        varchar(255),
  vat_percent double precision,
  price       double precision
);

create table app.orders (
  code        varchar(255) primary key,
  number      numeric,
  date_at     date,
  amount      double precision,
  vat         double precision,
  modified_at timestamp
);

create table app.order_items (
  code        varchar(255) primary key,
  good_code   varchar(255) references app.goods(code),
  order_code  varchar(255) references app.orders(code),
  name        varchar(255),
  cnt         numeric,
  vat_percent double precision,
  vat         double precision,
  price       double precision
);
