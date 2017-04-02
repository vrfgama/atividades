<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="model.Usuario" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Consulta de Usuário</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					datatoggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> 10 <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="index.html">Manter Usuário</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="index.html">Usuário</a></li>
				</ul>
			</div>
		</div>
	</nav>	 
	<div id="main" class="container">  
		<h3 class="page-header">Consultar Usuário</h3>	
		<div id="painel" class="row">	
	
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
    	</div>      
     </div>   
         
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