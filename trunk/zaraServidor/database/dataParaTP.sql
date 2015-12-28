delete from articuloByMateriaPrima;
delete from articuloByCentroDistribucion;
delete from itemFabricacionComenzada;
delete from itemSolicitud;
delete from solicitudFabricacion;
delete from fabricacionComenzada;
delete from articulo;
delete from centroDistribucion;
delete from materiaPrima;

insert into materiaPrima values(1,'LANA',0);
insert into materiaPrima values(2,'BOTON',0);
insert into materiaPrima values(3,'HILO',0);
insert into materiaPrima values(4,'CIERRE',0);
insert into materiaPrima values(5,'ALGODON',0);

insert into centroDistribucion values (1,'Tortuguitas');/*roberto*/
insert into centroDistribucion values (2,'Centro'); /*Dommage*/
insert into centroDistribucion values (3,'Belgrano'); /*Ucraniano*/
insert into centroDistribucion values (4,'Santiago'); /*Claverie*/
insert into centroDistribucion values (5,'Parque Norte'); /*Gon-Colo*/
insert into centroDistribucion values (6,'Shopping Centro'); 
insert into centroDistribucion values (7,'Montevideo Sur');

select * from articulo