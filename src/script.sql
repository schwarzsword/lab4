create table labuser (
  login    varchar(50) primary key,
  password varchar(255) not null
);

create table point (
  id        serial primary key,
  x         double precision not null,
  y         double precision not null,
  r         double precision not null,
  entering  varchar(20)      not null,
  parent varchar(50)    references labuser(username)
);