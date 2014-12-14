<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	if(request.getParameter("txtLogin") != null){
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
				
				response.sendRedirect("http://localhost:8080/SistemaChamado/LoginServlet");

			} catch (SQLException ex) {
				out.println("Problema ao carregar BD");
				out.println(ex.getErrorCode());
				out.println(ex.getMessage());

			}

		} catch (ClassNotFoundException e) {
			out.println("Erro ao carregar o DRIVER de conexão");
		}
		
	}


%>

	<form method="POST" action="Cadastra_usuario.jsp">
		Login: <br /> <input type="text" name="txtLogin"></input>
		<br />
		Senha: <br /> <input type="password" name="txtSenha"></input>
		<br />
		<input type="submit" value="Cadastrar"></input>
	</form>
</body>
</html>