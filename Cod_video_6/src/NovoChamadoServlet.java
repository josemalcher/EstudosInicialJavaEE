

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Driver;
import com.mysql.jdbc.Statement;

public class NovoChamadoServlet extends HttpServlet {

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
		}else{
			try {
				Class.forName("com.mysql.jdbc.Driver");

				String SQL = "INSERT INTO chamados (titulo, conteudo) VALUES (";
				SQL+= " '"+titulo+"', '"+conteudo+"')";
				
				try{
					//Fiz o Casting para sumir com erro na execução
					Connection conn =  (Connection) DriverManager.getConnection("jdbc:mysql://localhost/rlsys_chamados","root","mal369");
					Statement stm = (Statement) conn.createStatement();
					stm.execute(SQL);
					stm.close();
					conn.close();

				}catch(SQLException ex){
					out.println("Problema ao carregar BD"+ex.getMessage());
					
				}

			} catch (ClassNotFoundException e) {
				out.println("Erro ao carregar o DRIVER de conexão");
			}
		}



	}

}
