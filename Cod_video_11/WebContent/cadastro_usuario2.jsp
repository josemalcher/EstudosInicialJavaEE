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
	if(request.getParameter("msg") != null){
		out.println("Cadastrado com sucesso");
	}
%>
	<form method="POST" action="Usuario">
		Login: <br /> <input type="text" name="txtLogin"></input>
		<br />
		Senha: <br /> <input type="password" name="txtSenha"></input>
		<br />
		<input type="submit" value="Cadastrar"></input>
	</form>
</body>
</html>