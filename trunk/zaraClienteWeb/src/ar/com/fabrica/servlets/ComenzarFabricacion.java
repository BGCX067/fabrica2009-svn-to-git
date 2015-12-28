package ar.com.fabrica.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.com.fabrica.modelos.BusinessDelegateComenzarFabricacion;
import fabrica.vo.ArticuloEnListadoVO;

public class ComenzarFabricacion extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BusinessDelegateComenzarFabricacion bd = new BusinessDelegateComenzarFabricacion();
		
		ArrayList<ArticuloEnListadoVO> articulosComenzados = bd.calculaArticulosComenzados();
		ArrayList<ArticuloEnListadoVO> articulosPendientes = bd.calculaArticulosPendientes();
		
		request.setAttribute("articulosComenzados", articulosComenzados);
		request.setAttribute("articulosPendientes", articulosPendientes);
		request.getRequestDispatcher("ComenzarFabricacion.jsp").forward(request, response);

	}
}
