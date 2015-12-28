
package ar.com.fabrica.servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import ar.com.fabrica.error.BasicError;
import ar.com.fabrica.modelos.BusinessDelegateNuevoArticulo;
import ar.com.fabrica.xml.XMLReader;
import fabrica.configuration.StateManager;
import fabrica.vo.ArtHogarVO;
import fabrica.vo.ArtRopaVO;
import fabrica.vo.ArticuloVO;
import fabrica.vo.MateriaPrimaVO;

public class NuevoArticulo extends HttpServlet {
	
	private Logger logger = Logger.getLogger(NuevoArticulo.class);
	
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("NuevoArticulo.jsp").forward(request, response);

	}
	
	private void guardarArticulo(HttpServletRequest request){
		
		String tipoArticulo = (String) request.getParameter("tipoArticulo");
		
		ArticuloVO articuloVO = null;
		
		try{
			//FILL ART INFO
			if (tipoArticulo.equals("Ropa")) {
				articuloVO = new ArtRopaVO();
				((ArtRopaVO) articuloVO).setTalle(request.getParameter("Talle"));
				((ArtRopaVO) articuloVO).setOrigen(request.getParameter("Origen"));
			} else {
				articuloVO = new ArtHogarVO();
				((ArtHogarVO) articuloVO).setNombre(request.getParameter("Nombre"));
				((ArtHogarVO) articuloVO).setComposicion(request.getParameter("Composicion"));
				((ArtHogarVO) articuloVO).setMedidas(request.getParameter("Medidas"));
				((ArtHogarVO) articuloVO).setCategoria(request.getParameter("Categoria"));
				((ArtHogarVO) articuloVO).setLinea(request.getParameter("Linea"));
			}
			
			((ArticuloVO) articuloVO).setReferencia(request.getParameter("Referencia"));
			((ArticuloVO) articuloVO).setDescripcion(request.getParameter("Descripcion"));
			((ArticuloVO) articuloVO).setColor(request.getParameter("Color"));
			((ArticuloVO) articuloVO).setSeccion(request.getParameter("Seccion"));
			((ArticuloVO) articuloVO).setPrecioVenta(Float.parseFloat(request.getParameter("Precio Vta Unit")));
			((ArticuloVO) articuloVO).setTiempoDeFabricacion(Integer.parseInt(request.getParameter("Tiempo")));
			
		 	//FILL MAT PRI
			Integer cantidadMaterias = Integer.parseInt((String) request.getParameter("cantMaterias"));
		 	ArrayList<MateriaPrimaVO> materiasPrimasVO = new ArrayList<MateriaPrimaVO>();
		 	MateriaPrimaVO materiaPrimaVO;
		 	
		 	for (int i=1; i<=cantidadMaterias; i++){
		 		materiaPrimaVO = new MateriaPrimaVO();
		 		materiaPrimaVO.setCodigo(Integer.parseInt((String) request.getParameter("selectMaterias"+i)));
		 		materiaPrimaVO.setStock(Integer.parseInt((String) request.getParameter("inputMaterias"+i)));
		 		materiasPrimasVO.add(materiaPrimaVO);
		 	}
		 	
		 	articuloVO.setMateriasPrimas(materiasPrimasVO);
		 	new BusinessDelegateNuevoArticulo().guardarArticulo(articuloVO);
		 	
		 	request.setAttribute("error", new BasicError("","Alta de artículo exitosa"));
	
		}catch (Exception ex) {
			System.err.println(" Hubo un error procesando el xml, puede ver mas detalles en el log");
			StringWriter sw = new StringWriter();
			ex.printStackTrace(new PrintWriter(sw));
			logger.error(sw.toString());
			request.setAttribute("error", new BasicError("","Hubo un error intentando guardar el articulo, puede ver mas detalles en el log"));
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		String action = (String) request.getParameter("action");
		
		if (action != null && action.equals("guardar")){
			this.guardarArticulo(request);
		}
		else{
			this.procesarXML(request);
		}
		
		request.getRequestDispatcher("NuevoArticulo.jsp").forward(request, response);

	}
	
	private void procesarXML(HttpServletRequest request){
		ArticuloVO articuloVO= null;
		try {
			if (ServletFileUpload.isMultipartContent(request)){
				DiskFileItemFactory factory = new DiskFileItemFactory();
				factory.setSizeThreshold(4096);
				factory.setRepository(new File("/home/pablo/"));
				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setSizeMax(10000000);
				List<FileItem> fileItems = (List<FileItem>) upload.parseRequest(request);
				FileItem fi = fileItems.iterator().next();
				fi.write(new File(StateManager.getConfiguration().getXmlProperties().get("xml.write").concat("dummy.xml")));
				articuloVO = new XMLReader().getArticulo(new File(StateManager.getConfiguration().getXmlProperties().get("xml.write").concat("dummy.xml")));
			}
		}
		
		catch (Exception ex) {
			System.err.println(" Hubo un error procesando el xml, puede ver mas detalles en el log");
			StringWriter sw = new StringWriter();
			ex.printStackTrace(new PrintWriter(sw));
			logger.error(sw.toString());
			request.setAttribute("error", new BasicError("","Hubo un error intentando guardar el articulo, puede ver mas detalles en el log"));
		}
		
		try {
			logger.error(articuloVO.getSeccion());
			System.err.println(articuloVO.getSeccion());
			Map<String, String> campos = new HashMap<String, String>();
			if (articuloVO.getClass().equals(ArtRopaVO.class)) {
				request.setAttribute("tipoArticulo", "Ropa");
				ArtRopaVO auxArt = (ArtRopaVO) articuloVO;
				campos.put("Referencia", auxArt.getReferencia());
				campos.put("Linea", auxArt.getLinea());
				campos.put("Descripcion", auxArt.getDescripcion());
				campos.put("Talle", auxArt.getTalle());
				campos.put("Color", auxArt.getColor());
				campos.put("Seccion", auxArt.getSeccion());
				campos.put("Precio Vta Unit", ((Float) auxArt.getPrecioVenta()).toString());
				campos.put("Origen", auxArt.getOrigen());
				
			} else {
				request.setAttribute("tipoArticulo", "Hogar");
				ArtHogarVO auxArt = (ArtHogarVO) articuloVO;
				campos.put("Referencia", auxArt.getReferencia());
				campos.put("Seccion", auxArt.getSeccion());
				campos.put("Nombre", auxArt.getNombre());
				campos.put("Descripcion", auxArt.getDescripcion());
				campos.put("Composicion", auxArt.getComposicion());
				campos.put("Medidas", auxArt.getMedidas());
				campos.put("Precio Vta Unit", ((Float) auxArt.getPrecioVenta()).toString());
				campos.put("Color", auxArt.getColor());
				campos.put("Categoria", auxArt.getCategoria());
				campos.put("Linea", auxArt.getLinea());
			}
			request.setAttribute("campos", campos);
		}
		catch (Exception ex){
			System.err.println(" Hubo un error procesando el xml, puede ver mas detalles en el log");
			StringWriter sw = new StringWriter();
			ex.printStackTrace(new PrintWriter(sw));
			logger.error(sw.toString());
			request.setAttribute("error", new BasicError("","Hubo un error intentando guardar el articulo, puede ver mas detalles en el log"));
		}
		
		BusinessDelegateNuevoArticulo bd = new BusinessDelegateNuevoArticulo();
		ArrayList<MateriaPrimaVO> materiasPrimasVO = bd.obtenerMateriasPrimas();
		request.setAttribute("materiasPrimasVO", materiasPrimasVO);

	}
}
