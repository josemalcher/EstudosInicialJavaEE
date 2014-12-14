Estudos Inicial JavaEE Básico
====================

Estudos do curso de Java EE da RLSystem - 2014

####Video 01 - 00:15:13
Resumo:

Plataforma de desenvilvimento web em JAVA.

Recursos disponíveis: HTML, CSS, JS, BD.

Java, Clipse, MySQL.

TOMCAT com Servidor Web

SERVLET

JSP

MySQL  - Download do Banco de dados
Tomcat - Download do Servidor 7.0.*

####Video 02 - 00:10:14
Resumo:

Instalação do Servidor TOMCAT(windows)

Linux: Extrir pasta -> mv para /opt/... 

Ajustar Usuário em /tomcat...*/conf/tomcat-users.xml -> descomentar linhas e ajustar usuários

Adicionando Tomcat no Eclipse

####Video 03 - 00:23:53
Sobre o uso do Servlet

Criar um Dynamic web Project - Sistema de Chamados: Versão 2.5 do Servlet

web.xml - sua importancia para versão 2.5 (versão atual 3.*)

extends __HttpServlet__

Metodos doGet e doPost

[Códigos Fonte Vídeo 03](https://github.com/josemalcher/EstudosInicialJavaEE/tree/master/Cod_video_3)

####Video 04 - 00:15:46
Página Novo Chamado

Methods POST/GET

Mudanças da versão 3.0 > Não precisa mais registrar no web.xml Registro direto na classe 

[Códigos Fonte Vídeo 04](https://github.com/josemalcher/EstudosInicialJavaEE/tree/master/Cod_video_4)

####Video 05 - 00:16:18
Usando Banco de Dados

[Buscar Driver do Banco de dados. MySQL(2014)](http://dev.mysql.com/downloads/connector/j/)

Pasta WEB-INF/lib -> local para armazenar bibliotecas.

```java
//Conector do banco de dados
try {
    Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e) {
    out.println("Erro ao carregar o DRIVER de conexão");
    }
```

Criação do Bando de dados - [Chamados, .sql](https://github.com/josemalcher/EstudosInicialJavaEE/blob/master/Cod_video_5/rlsys_chamados.sql)

[Códigos Fonte Vídeo 05](https://github.com/josemalcher/EstudosInicialJavaEE/tree/master/Cod_video_5)

####Video 06

Criação das Query: INSERT e testes.

[Códigos Fonte Vídeo 06](https://github.com/josemalcher/EstudosInicialJavaEE/tree/master/Cod_video_6)

####Video 07
Criação da página de Listagem

Partes do vídeo divido nos commits

[Códigos Fonte Vídeo 07](https://github.com/josemalcher/EstudosInicialJavaEE/tree/master/Cod_video_7)

####Video 08 - 00:24:35
Login e Senha - Session


[Códigos Fonte Vídeo 08](https://github.com/josemalcher/EstudosInicialJavaEE/tree/master/Cod_video_8)

####Video 09
Ler e como gravar Cookies

[Códigos Fonte Vídeo 09](https://github.com/josemalcher/EstudosInicialJavaEE/tree/master/Cod_video_9)

####Video 10 - 00:08:28
Versão mais atual do Servlet

Uso de Anotations

ex: @WebServlet("/PaginaIndex")

####Video 11 - 00:25:47
Introdução ao JSP

[Códigos Fonte Vídeo 11](https://github.com/josemalcher/EstudosInicialJavaEE/tree/master/Cod_video_11)

####Video 12

####Video 13

