import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest req, HttpServletResponse res)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		String login_name = "";

		Cookie[] ck = req.getCookies();

		if (ck != null) {
			for (Cookie cookie : ck) {
				if (cookie.getName().equals("login_name")) {
					login_name = cookie.getValue();
				}
			}
		}
		
		//apagar Cookie - EXEMPLO
		Cookie[] ck2 = req.getCookies();

		if (ck != null) {
			for (Cookie cookie : ck2) {
				if (cookie.getName().equals("login_name")) {
					cookie.setMaxAge(0);
					res.addCookie(cookie);
				}
			}
		}

		try {
			PrintWriter out = res.getWriter();
			out.println("<html>");
			out.println("<head>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h2>Sistema de Chamado</h2>");
			out.println("<hr>");
			out.println("<h3>LOGIN</h3>");
			out.println("<br><a href='http://localhost:8080/SistemaChamado/ListarChamados'>Listar Chamado</a>");
			out.println("<br><a href='http://localhost:8080/SistemaChamado/Index'>Voltar Inicio</a>");
			out.println("<br><a href='http://localhost:8080/SistemaChamado/Sair'>Sair</a>");
			out.println("<hr/>");
			out.println("<h2>Preencha o formulario de Login</h2>");
			out.println("<hr/>");
			out.println("<form method='POST'>");
			out.println("Login: <br> <input type='text' name='txtLogin' value ='"+login_name+"' />");
			out.println("Senha: <br> <input type='password' name='txtSenha'/>");
			out.println("<br> <input type='submit' value='LOGar'/>");

			// Sair
			if (req.getParameter("msg") != null) {
				if (req.getParameter("msg").equals("logoff")) {
					HttpSession sessao = req.getSession();
					// sessao.removeAttribute("login");
					sessao.invalidate();

					out.println("<span style='color:red'>Deslogado com sucesso</span>");
				}
			}

			if (req.getParameter("msg") != null) {
				if (req.getParameter("msg").equals("error")) {
					out.println("<span style='color:red'>Login ou senha Incorretos</span>");
				}
			}

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

		String login = req.getParameter("txtLogin");
		String senha = req.getParameter("txtSenha");
		
		Cookie ck = new Cookie("login_name", login);
		ck.setMaxAge(60*60*24);//em segundos
		res.addCookie(ck);		
		

		try {
			Class.forName("com.mysql.jdbc.Driver");

			try {

				Connection conn = DriverManager.getConnection(
						"jdbc:mysql://localhost/rlsys_chamados", "root",
						"mal369");

				String SQL = "SELECT * FROM usuarios where login=? and senha=?";

				PreparedStatement pstm = conn.prepareStatement(SQL);

				pstm.setString(1, login);
				pstm.setString(2, senha);

				ResultSet rs = pstm.executeQuery();

				if (rs.next()) {
					pstm.close();
					conn.close();
					HttpSession sessao = req.getSession();
					sessao.setAttribute("login", login);
					pstm.execute();
					res.sendRedirect("http://localhost:8080/SistemaChamado/ListarChamados");
				} else {
					pstm.close();
					conn.close();
					res.sendRedirect("http://localhost:8080/SistemaChamado/LoginServlet?msg=error");
				}

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
