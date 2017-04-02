<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="model.Usuario" %>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dados do Usuário Atualizados</title>
</head>
<body>
	<%Usuario antigo= (Usuario)request.getAttribute("antigo"); %>
	<h3>Dados Antigos do Usuário.</h3><br>
	ID: <%=antigo.getId() %> <br>
	Senha: <%=antigo.getSenha()%> <br>
	Nome: <%=antigo.getNome()%> <br>
	CPF: <%=antigo.getCpf()%> <br>
	Função: <%= funcao(antigo.getPerfil()) %> <br>
	Autorizado a config. temperatuta?: <%= temp(antigo.isMudaTemp()) %> <br>
	Jornada de trabalho: <br> Hora inicial: <%=antigo.getHoraEnt() %> Hora final: <%=antigo.getHoraSai()%><br>
	CNPJ da empresa em que trabalha: <%=antigo.getCnpj()%><br>  
           
    
    <%Usuario novo= (Usuario)request.getAttribute("novo"); %>
	<h3>Dados Atualizados.</h3><br>
	ID: <%= novo.getId() %> <br>
	Senha: <%= novo.getSenha()%> <br>
	Nome: <%=novo.getNome()%> <br>
	CPF: <%=novo.getCpf()%> <br>
	Função: <%= funcao(novo.getPerfil()) %> <br>
	Autorizado a config. temperatuta?: <%= temp(novo.isMudaTemp()) %> <br>
	Jornada de trabalho: <br> Hora inicial: <%=novo.getHoraEnt() %> Hora final: <%=novo.getHoraSai()%><br>
	CNPJ da empresa em que trabalha: <%=novo.getCnpj()%> <br><br>  
	
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