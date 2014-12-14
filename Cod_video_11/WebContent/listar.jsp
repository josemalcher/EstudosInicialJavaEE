<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
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
	<table>
		<tr>
			<td>ID</td>
			<td>Login</td>
		</tr>
		<%
			try {
				Class.forName("com.mysql.jdbc.Driver");
				String SQL = "SELECT * FROM usuarios";
				try {
					Connection conn = DriverManager.getConnection(
							"jdbc:mysql://localhost/rlsys_chamados", "root",
							"mal369");

					Statement stm = conn.prepareStatement(SQL);

					ResultSet rs = stm.executeQuery(SQL);
					while(rs.next()){
		%>

		<tr>
			<td><% out.println(rs.getString("id")); %></td>
			<td><% out.println(rs.getString("login")); %></td>
		</tr>
		<tr>
			<!-- Outro modo de chamar as variáveis -->
			<td><%=rs.getString("id")%></td>
			<td><%=rs.getString("login")%></td>
		</tr>

	</table>
	<%
		}
			} catch (SQLException ex) {
			out.println("Problema ao carregar BD");
			out.println(ex.getErrorCode());
			out.println(ex.getMessage());

		}

			} catch (ClassNotFoundException e) {
		out.println("Erro ao carregar o DRIVER de conexão");
			}
	%>

	<form method="POST" action="Cadastra_usuario.jsp">
		Login: <br /> <input type="text" name="txtLogin"></input> <br />
		Senha: <br /> <input type="password" name="txtSenha"></input> <br />
		<input type="submit" value="Cadastrar"></input>
	</form>
</body>
</html>