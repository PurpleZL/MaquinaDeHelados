CREATE TABLE helado (
    posicion VARCHAR(255) PRIMARY KEY,
    nombre TEXT NOT NULL,
    precio NUMERIC NOT NULL,
    tipo TEXT NOT NULL,
    cantidad INTEGER NOT NULL
);

create table venta(
	fecha_hora text not null,
	posicion text not null,
	nombre text not null,
	precio numeric not null,
	tipo text not null,
	cantidad integer not null
);

insert into helado values ('00','ChocoBomba',1.1,'tarrina',5);
insert into helado values ('01','FresaGuapi',0.8,'palo',5);
insert into helado values ('02','LimónCanela',1.5,'cucurucho',5);
insert into helado values ('10','FrigLemon',1.8,'tarrina',5);
insert into helado values ('11','PiñaHelada',1,'palo',5);
insert into helado values ('12','MoraGuay',1.2,'cucurucho',5);
insert into helado values ('20','ChocoLoco',1.5,'tarrina',5);
insert into helado values ('21','TuttiFrutti',1.1,'palo',5);
insert into helado values ('22','Mentazul',1.1,'cucurucho',5);