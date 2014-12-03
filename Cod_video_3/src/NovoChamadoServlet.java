

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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
