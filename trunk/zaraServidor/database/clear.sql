update articulo set esNuevo = 1 , fabricadoPorNuevo = 0;
delete from itemFabricacionComenzada;
delete from itemSolicitud;
delete from fabricacionComenzada;
delete from solicitudFabricacion;
update materiaPrima set stock=600;
