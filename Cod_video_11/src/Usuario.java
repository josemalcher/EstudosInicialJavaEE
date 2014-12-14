

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Usuario
 */
public class Usuario extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("txtLogin") != null){
			PrintWriter out = response.getWriter();
			out.println("Formulário Enviando");
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				String SQL = "INSERT INTO usuarios (login, senha) VALUES (?, ?)";
				try {
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/rlsys_chamados", "root","mal369");

					PreparedStatement pstm = conn.prepareStatement(SQL);

					pstm.setString(1, request.getParameter("txtLogin"));
					pstm.setString(2, request.getParameter("txtSenha"));

					pstm.execute();
					pstm.close();
					conn.close();
					
					response.sendRedirect("http://localhost:8080/SistemaChamado/cadastro_usuario2.jsp?msg=sucesso");

				} catch (SQLException ex) {
					out.println("Problema ao carregar BD");
					out.println(ex.getErrorCode());
					out.println(ex.getMessage());

				}

			} catch (ClassNotFoundException e) {
				out.println("Erro ao carregar o DRIVER de conexão");
			}
			
		}
		
	}

}
