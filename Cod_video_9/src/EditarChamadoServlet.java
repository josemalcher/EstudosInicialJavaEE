import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.Driver;
//import com.mysql.jdbc.PreparedStatement;
//import com.mysql.jdbc.Statement;

@SuppressWarnings("serial")
public class EditarChamadoServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		PrintWriter out = res.getWriter();

		try {
			// Carrega o driver de conexão
			Class.forName("com.mysql.jdbc.Driver");

			try {

				Connection conn = DriverManager.getConnection(
						"jdbc:mysql://localhost/rlsys_chamados", "root",
						"mal369");

				//Statement stm = conn.createStatement();

				String SQL = "SELECT * FROM chamados WHERE id=?";
				
				PreparedStatement pstm = conn.prepareStatement(SQL);
				pstm.setInt(1, Integer.parseInt(req.getParameter("id")));
				
				ResultSet rs = pstm.executeQuery();
				
				if(rs.next()){
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
					out.println("ID <br> <input type='text' name='txtID' readonly='readonly' value='"+ rs.getString("id") +"'/>");
					out.println("Titulo <br> <input type='text' name='txtTitulo' value='"+ rs.getString("titulo") +"'/>");
					out.println("<br>Conteudo <br> <textarea name='txtConteudo' rows='10' colums='4'>"+ rs.getString("conteudo") +"</textarea>");
					out.println("<br> <input type='submit' value='Atualizar Chamado'/>");
					out.println("</form>");
					out.println("</body>");
					out.println("</html>");
				}else{
					out.println("<html>");
					out.println("<head>");
					out.println("</head>");
					out.println("<body>");
					out.println("<h2>Sistema de Chamado</h2>");
					out.println("<h3>CHAMADO NÃO EXISTE</h3>");
					out.println("<br><a href='http://localhost:8080/SistemaChamado/ListarChamados'>Listar Chamado</a>");
					out.println("<br><a href='http://localhost:8080/SistemaChamado/Index'>Voltar Inicio</a>");
					out.println("<br><a href='http://localhost:8080/SistemaChamado/LoginServlet?msg=logoff'>Sair</a>");
					out.println("<hr/>");
					out.println("</body>");
					out.println("</html>");
				}
				
				pstm.close();
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		PrintWriter out = res.getWriter();

		int id = Integer.parseInt(req.getParameter("id"));
		String titulo = req.getParameter("txtTitulo");
		String conteudo = req.getParameter("txtConteudo");

		if (titulo.trim().length() < 4) {
			out.println("Preencha o campo titulo");
		} else if (conteudo.trim().length() < 4) {
			out.println("Preencha o campo conteudo");
		} else {
			try {
				Class.forName("com.mysql.jdbc.Driver");

				String SQL = "UPDATE chamados SET titulo = ? , conteudo= ? WHERE id= ?";

				try {

					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/rlsys_chamados", "root","mal369");
					PreparedStatement pstm = conn.prepareStatement(SQL);

					pstm.setString(1, titulo);
					pstm.setString(2, conteudo);
					pstm.setInt(3, id);

					pstm.execute();
					pstm.close();
					conn.close();
					
					res.sendRedirect("http://localhost:8080/SistemaChamado/ListarChamados");

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
