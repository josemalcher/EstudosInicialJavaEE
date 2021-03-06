
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

//import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.Driver;
//import com.mysql.jdbc.PreparedStatement;
//import com.mysql.jdbc.Statement;

@SuppressWarnings("serial")
public class NovoChamadoServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
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
			out.println("<br><a href='http://localhost:8080/SistemaChamado/LoginServlet?msg=logoff'>Sair</a>");
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		PrintWriter out = res.getWriter();

		String titulo = req.getParameter("txtTitulo");
		String conteudo = req.getParameter("txtConteudo");

		// if(titulo.equals("")){
		if (titulo.trim().length() < 4) {
			out.println("Preencha o campo titulo");
		} else if (conteudo.trim().length() < 4) {
			out.println("Preencha o campo conteudo");
		} else {
			try {
				Class.forName("com.mysql.jdbc.Driver");

				// String SQL =
				// "INSERT INTO chamados (titulo, conteudo) VALUES (";
				// SQL+= " '"+titulo+"', '"+conteudo+"')";

				// melhoria para evitar SQL injection
				String SQL = "INSERT INTO chamados (titulo, conteudo) VALUES (?, ?)";

				try {

					Connection conn = DriverManager.getConnection(
							"jdbc:mysql://localhost/rlsys_chamados", "root",
							"mal369");
					// Statement stm = conn.createStatement();
					PreparedStatement pstm = conn.prepareStatement(SQL);

					pstm.setString(1, titulo);
					pstm.setString(2, conteudo);

					// stm.execute(SQL);
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
