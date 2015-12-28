package ar.com.fabrica.servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import ar.com.fabrica.error.BasicError;
import ar.com.fabrica.modelos.BusinessDelegateSolicitudFabricacion;
import ar.com.fabrica.xml.XMLReader;
import fabrica.configuration.StateManager;
import fabrica.vo.ArticuloEnListadoVO;
import fabrica.vo.SolFabrVO;

public class SolicitudFabricacion extends HttpServlet {

	private Logger logger = Logger.getLogger(NuevoArticulo.class);
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {		
		
		ArrayList<ArticuloEnListadoVO> articulosEnListado = null;
		
		try {
			articulosEnListado = new BusinessDelegateSolicitudFabricacion().cargarSolicitudesAnteriores();
			request.setAttribute("articulosSolicitud", articulosEnListado);
		}
		
		catch (Exception ex) {
			System.err.println(" Hubo un error procesando el xml, puede ver mas detalles en el log");
			StringWriter sw = new StringWriter();
			ex.printStackTrace(new PrintWriter(sw));
			logger.error(sw.toString());
			request.setAttribute("error", new BasicError("","Hubo un error procesando las materias primas, puede ver mas detalles en el log"));
		}
		
		request.setAttribute("action", "get");
		request.getRequestDispatcher("SolicitudFabricacion.jsp").forward(request, response);
	}
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		SolFabrVO solFabrVO = null;
		ArrayList<ArticuloEnListadoVO> articulosEnListado = null;
		
		try {
			if (ServletFileUpload.isMultipartContent(request)) {
				DiskFileItemFactory factory = new DiskFileItemFactory();
				factory.setSizeThreshold(4096);
				factory.setRepository(new File("/home/pablo/"));
				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setSizeMax(10000000);
				List<FileItem> fileItems = (List<FileItem>) upload.parseRequest(request);
				FileItem fi = fileItems.iterator().next();
				fi.write(new File(StateManager.getConfiguration().getXmlProperties().get("xml.write").concat("dummy.xml")));
				solFabrVO = new XMLReader().getNuevasFabricaciones(new File(StateManager.getConfiguration().getXmlProperties().get("xml.write").concat("dummy.xml")));
				articulosEnListado = new BusinessDelegateSolicitudFabricacion().procesarNuevasFabricaciones(solFabrVO);
			}
			request.setAttribute("action", "post");
			request.setAttribute("articulosSolicitud", articulosEnListado);

		}

		catch (Exception ex) {
			System.err.println(" Hubo un error procesando el xml, puede ver mas detalles en el log");
			StringWriter sw = new StringWriter();
			ex.printStackTrace(new PrintWriter(sw));
			logger.error(sw.toString());
			request.setAttribute("error", new BasicError("","Hubo un error procesando las materias primas, puede ver mas detalles en el log"));
		}
		request.getRequestDispatcher("SolicitudFabricacion.jsp").forward(request, response);		
	}
}
