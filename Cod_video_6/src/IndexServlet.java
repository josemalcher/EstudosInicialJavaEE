

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class IndexServlet extends HttpServlet{

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res){
		
		try {
			PrintWriter out = res.getWriter();
			out.println("<html>");
				out.println("<head>");
				out.println("</head>");
				out.println("<body>");
					out.println("<h2>Sistema de Chamado</h2>");
					out.println("<hr/>");
					out.println("<br><a href='http://localhost:8080/SistemaChamado/NovoChamado'>Novo Chamado</a>");
					out.println("<br><a href='http://localhost:8080/SistemaChamado/ListarChamados'>Listar Chamado</a>");
					out.println("<br><a href='http://localhost:8080/SistemaChamado/Sair'>Sair</a>");
				out.println("</body>");
			out.println("</html>");
		} catch (IOException e) {
			
		}
		
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res){
		
	}
	
}
