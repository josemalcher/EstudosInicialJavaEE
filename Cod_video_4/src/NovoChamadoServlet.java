

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NovoChamado
 */
public class NovoChamadoServlet extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			PrintWriter out = res.getWriter();
			out.println("<html>");
				out.println("<head>");
				out.println("</head>");
				out.println("<body>");
					out.println("<h2>Sistema de Chamado</h2>");
					out.println("<h3>Novo Chamado</h3>");
					out.println("<br><a href='http://localhost:8080/SistemaChamado/ListarChamados'>Listar Chamado</a>");
					out.println("<br><a href='http://localhost:8080/SistemaChamado/Index'>Voltar Inicio</a>");
					out.println("<br><a href='http://localhost:8080/SistemaChamado/Sair'>Sair</a>");
					out.println("<hr/>");
					out.println("<h2>Preencha o formulario</h2>");
					out.println("<hr/>");
					out.println("<form method='POST'>");
						out.println("Titulo <br> <input type='text' name='txtTitulo'/>");
						out.println("<br>Conteudo <br> <textarea name='txtConteudo' rows='10' colums='4'></textarea>");
						out.println("<br> <input type='submit' value='Abrir Chamado'/>");
					out.println("</form>");
				out.println("</body>");
			out.println("</html>");
		} catch (IOException e) {

		}
		
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		
		String titulo = req.getParameter("txtTitulo");
		String conteudo = req.getParameter("txtConteudo");
		
		//if(titulo.equals("")){
		if(titulo.trim().length() < 4){
			out.println("Preencha o campo titulo");
		}else if(conteudo.trim().length() < 4){
			out.println("Preencha o campo conteudo"); 
		}
	}

}
