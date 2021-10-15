drop table if exists character CASCADE;
create table character
(
		id integer primary key auto_increment,
		name varchar(255),
		vision varchar(255),
		weapon varchar(255)
);