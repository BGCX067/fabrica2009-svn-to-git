delete from articuloByMateriaPrima;

delete from articuloByCentroDistribucion;

delete from itemFabricacionComenzada;

delete from itemSolicitud;

delete from solicitudFabricacion;

delete from fabricacionComenzada;

delete from articulo;

delete from centroDistribucion;

delete from materiaPrima;

insert into materiaPrima values(1,'LANA',100);
insert into materiaPrima values(2,'BOTON',700);
insert into materiaPrima values(3,'HILO',250);
insert into materiaPrima values(4,'CIERRE',320);
insert into materiaPrima values(5,'ALGODON',2140);

insert into centroDistribucion values (1,'Tortuguitas');
insert into centroDistribucion values (2,'Centro');
insert into centroDistribucion values (3,'Santiago');
insert into centroDistribucion values (4,'Parque Norte');
insert into centroDistribucion values (5,'Shopping Centro');
insert into centroDistribucion values (6,'Montevideo Sur');

insert into solicitudFabricacion values ( '2009-13-06', 2);
insert into solicitudFabricacion values ( '2009-14-06', 3);

insert into articulo values('H','Blabla','6000','Jabon para manos','Amarillo',10.99,'hogar',0,0,1,null,null,'Jabon','','20X10cm','Blabla');
insert into articulo values('R','BLABLA','8000','Camisa','Negro',6.99,'Sport',0,0,1,'Medium','Origen',null,null,null,null);
insert into articulo values('H','Blabla','6001','Shampoo Anticaspa','Azul',15.99,'hogar',0,0,1,null,null,'Shampoo','','30X20cm','Composicion');
insert into articulo values('R','Ropa deportiva','8001','Remera','Rojo',16.99,'Sport',0,30,0,'Large','Origen',null,null,null,null);


insert into articuloByMateriaPrima (cantidad,materia_id,articulo_id) values(123,2,1);
insert into articuloByMateriaPrima (cantidad,materia_id,articulo_id) values(43243,3,2);
insert into articuloByMateriaPrima (cantidad,materia_id,articulo_id) values(12,4,2);
insert into articuloByMateriaPrima (cantidad,materia_id,articulo_id) values(4234,1,3);
insert into articuloByMateriaPrima (cantidad,materia_id,articulo_id) values(423,2,3);
insert into articuloByMateriaPrima (cantidad,materia_id,articulo_id) values(85,2,4);

insert into articuloByCentroDistribucion values(1,1);
insert into articuloByCentroDistribucion values(1,2);
insert into articuloByCentroDistribucion values(2,1);
insert into articuloByCentroDistribucion values(2,2);
insert into articuloByCentroDistribucion values(3,1);
insert into articuloByCentroDistribucion values(3,3);
insert into articuloByCentroDistribucion values(4,3);
insert into articuloByCentroDistribucion values(4,5);

insert into itemSolicitud (cantidad,cantidad_entregada,solicitud_id,articulo_id) values (10,0,1,1);
insert into itemSolicitud (cantidad,cantidad_entregada,solicitud_id,articulo_id) values (1,0,1,4);
insert into itemSolicitud (cantidad,cantidad_entregada,solicitud_id,articulo_id) values (100,0,2,3);
insert into itemSolicitud (cantidad,cantidad_entregada,solicitud_id,articulo_id) values (150,0,2,2);

