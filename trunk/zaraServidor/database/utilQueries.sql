SELECT a.id,a.descripcion,asf.cantidad, mp.codigo, mp.nombre, mp.stock / sum(amp.cantidad), amp.cantidad, mp.stock
from solicitudFabricacion sf
inner join articuloBySolicitudFabricacion asf on sf.id = asf.solicitud_id
inner join articuloByMateriaPrima amp on amp.ARTICULO_ID = asf.articulo_id
inner join materiaPrima mp on amp.MATERIA_ID = mp.codigo
inner join articulo a on asf.articulo_id = a.id 	
group by a.id, mp.codigo

select a.referencia, a.descripcion, cd.nombre, sum(afc.cantidad_fabricada)
from articulo a 
inner join articuloBySolicitudFabricacion asf on asf.articulo_id = a.id
inner join solicitudFabricacion sf on sf.id = asf.solicitud_id
inner join centroDistribucion cd on cd.id = sf.centroDistribucion_id
inner join articuloByFabricacionComenzada afc on afc.item_sol_fabr_id = asf.id
inner join fabricacionComenzada fc on fc.id = afc.fabricacion_id
where datediff(now(),fc.fecha) > a.tiempoDeFabricacion
group by a.id, cd.id
order by cd.nombre	


SELECT a.descripcion, mp.nombre, amp.cantidad FROM articuloByMateriaPrima amp 
inner join articulo a on amp.articulo_id = a.idArticulo
inner join materiaPrima mp on amp.MATERIA_id = mp.codigo
