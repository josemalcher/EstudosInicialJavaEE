

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ListarChamados
 */
@SuppressWarnings("serial")
public class ListarChamados extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		HttpSession sessao = request.getSession();
		if(sessao.getAttribute("login") == null){
			response.sendRedirect("http://localhost:8080/SistemaChamado/LoginServlet");
		}
		
		try {
			//Carrega o driver de conexão
			Class.forName("com.mysql.jdbc.Driver");

			

			try {

				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/rlsys_chamados", "root","mal369");
				
				Statement stm = conn.createStatement();
				
				//DELETA O REGISTRO
				//Quando existir uma query string ID
				if(request.getParameter("id") != null){
					int ID = Integer.parseInt(request.getParameter("id"));
					String SQLDelete = "DELETE FROM chamados WHERE id= ?";
					PreparedStatement pstm = conn.prepareStatement(SQLDelete);
					pstm.setInt(1, ID);
					pstm.execute();
				}
				
				
				
				String SQL = "SELECT * FROM chamados";
				
				ResultSet rs = stm.executeQuery(SQL);
				out.println("<html>");
				out.println("<body>");
				out.println("<table width='100%'>");
					out.println("<tr>");
						out.println("<td>ID</td>");
						out.println("<td>Titulo</td>");
						out.println("<td>Editar</td>");
						out.println("<td>Apagar</td>");
					out.println("</tr>");
					while(rs.next()){
						out.println("<tr>");
						out.println("<td>"+ rs.getInt("id") +"</td>");
						out.println("<td>"+ rs.getString("titulo") +"</td>");
						out.println("<td><a href='http://localhost:8080/SistemaChamado/EditarChamados?id="+rs.getInt("id")+"'>[ Editar ]</a></td>");
						out.println("<td><a href='http://localhost:8080/SistemaChamado/ListarChamados?id="+rs.getInt("id")+"'>[ Apagar ]</a></td>");
						out.println("</tr>");
					}
				out.println("</tr>");
					
				out.println("</table>");
				out.println("<br><a href='http://localhost:8080/SistemaChamado/LoginServlet?msg=logoff'>Sair</a>");
				out.println("</body>");
				out.println("</html>");
				
				stm.close();
				conn.close();

			} catch (SQLException ex) {
				out.println("Problema ao carregar BD");
				out.println(ex.getErrorCode());
				out.println(ex.getMessage());

			}

		} catch (ClassNotFoundException e) {
			out.println("Erro ao carregar o DRIVER de conexão");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
