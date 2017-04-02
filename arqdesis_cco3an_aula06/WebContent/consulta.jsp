<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="model.Usuario" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Consulta de Usuário</title>
</head>
<body>
	<%Usuario usuario= (Usuario)request.getAttribute("usuario"); %>
	<h3>Dados do usuário consultado.</h3><br>
	ID: <%=usuario.getId() %> <br>
	Senha: <%=usuario.getSenha()%> <br>
	Nome: <%=usuario.getNome()%> <br>
	CPF: <%=usuario.getCpf()%> <br>
	Função: <%= funcao(usuario.getPerfil()) %> <br>
	Autorizado a config. temperatuta?: <%= temp(usuario.isMudaTemp()) %> <br>
	Jornada de trabalho: <br> Hora inicial: <%=usuario.getHoraEnt() %> Hora final: <%=usuario.getHoraSai()%><br>
	CNPJ da empresa em que trabalha: <%=usuario.getCnpj()%> <br><br>  
           
    <input type=button value=voltar onclick=history.go(-1) />      
           
    <%! String funcao(int perfil){
		if(perfil==0) return "Funcionário";
		else if(perfil==1) return "Atendente";
		else return "Sindico";
		} 
				
		String temp(boolean t){
		if(t==true) return "Sim";
		else return "Não";
	} %>        
</body>
</html>