


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Cliente;
import service.ClienteService;

/**
 * Servlet implementation class CrudClientesServlet
 */
@WebServlet("/crudClientes")
public class CrudClientesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrudClientesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("idCliente"));
		String nome = request.getParameter("nomeCliente");
		String fone = request.getParameter("foneCliente");
		String email = request.getParameter("emailCliente");
		
		String oQueFazer = request.getParameter("oQueFazer");
		
		Cliente cliente = new Cliente();
		cliente.setId(id);
		cliente.setNome(nome);
		cliente.setFone(fone);
		cliente.setEmail(email);
		
		ClienteService service = new ClienteService();
		
		switch (oQueFazer){
		case "Cadastrar":
		{
			if(service.criar(cliente)){
				PrintWriter out = response.getWriter();
				out.println("<html><head><title>Cadastro Cliente</title></head><body>");
				out.println("<h3>Operação realizada com sucesso\nO ID do cliente é: "+cliente.getId()+".</h3>");
				out.println("<input type=button value=voltar onclick=history.go(-1) />  <br>" );
				out.println("</body></html>");
			}else{
				PrintWriter out = response.getWriter();
				out.println("<html><head><title>Cadastro Cliente</title></head><body>");
				out.println("<h3>Erro ao cadastrar</h3>");
				out.println("<input type=button value=voltar onclick=history.go(-1) />  <br>" );
				out.println("</body></html>");
			}
		}	
		break;
		case "Consultar":
		{	
			Cliente cliente2= service.carregar(cliente.getId());
			if(cliente2.getNome() != null)
			{	
				PrintWriter out = response.getWriter();
				out.println("<html><head><title>Consulta Cliente</title></head><body>");
				out.println(	"<h3>Dados do cliente consultado.</h3><br>");
				out.println(	"id: "+cliente2.getId()+"<br>");
				out.println(	"nome: "+cliente2.getNome()+"<br>");
				out.println(	"fone: "+cliente2.getFone()+"<br>");
				out.println(	"e-mail: "+cliente2.getEmail()+"<br>");
				out.println(	"<input type=button value=voltar onclick=history.go(-1) />  <br>" );
			    out.println("</body></html>");				
			}else if(cliente2.getNome() == null){
				PrintWriter out = response.getWriter();
				out.println("<html><head><title>Consulta Cliente</title></head><body>");
				out.println("<h3>Falha na consulta!</h3>");
				out.println("<input type=button value=voltar onclick=history.go(-1) />  <br>" );
				out.println("</body></html>");	
			}
		}	
		break;
		case "Remover":
		{	
			if(service.excluir(cliente.getId())){
				PrintWriter out = response.getWriter();
				out.println("<html><head><title>Remoção de Cadastro</title></head><body>");
				out.println("<h3>Cliente com ID: "+cliente.getId()+" foi removido com sucesso.</h3>");
				out.println("<input type=button value=voltar onclick=history.go(-1) />  <br>" );
				out.println("</body></html>");
			}else{
				PrintWriter out = response.getWriter();
				out.println("<html><head><title>Remoção de Cadastro</title></head><body>");
				out.println("<h3>Erro no processo de exclusão!</h3>");
				out.println("<input type=button value=voltar onclick=history.go(-1) />  <br>" );
				out.println("</body></html>");
			}
		}
		break;
		case "Atualizar":
		{	
			Cliente antigo= new Cliente(); 
			Cliente novo= new Cliente();
			antigo= service.carregar(cliente.getId());
			
			if(service.atualizar(cliente)){
				
				novo= service.carregar(cliente.getId());
				
				PrintWriter out = response.getWriter();
				out.println("<html><head><title>Atualização de Cadastro Cliente</title></head><body>");
				out.println("<h3>Dados do cliente com ID: "+cliente.getId()+" foram atualizados com sucesso.</h3><br>");
				
				out.println("<h3>Dados anteriores:</h3><br>");
				out.println(	"nome: "+antigo.getNome()+"<br>");
				out.println(	"fone: "+antigo.getFone()+"<br>");
				out.println(	"e-mail: "+antigo.getEmail()+"<br><br>");
				
				out.println("<h3>Dados atualizadoss:</h3><br>");
				out.println(	"nome: "+novo.getNome()+"<br>");
				out.println(	"fone: "+novo.getFone()+"<br>");
				out.println(	"e-mail: "+novo.getEmail()+"<br><br>");
				
				out.println("<input type=button value=voltar onclick=history.go(-1) />  <br>" );
				out.println("</body></html>");
			}else{
				PrintWriter out = response.getWriter();
				out.println("<html><head><title>Atualização de Cadastro Cliente</title></head><body>");
				out.println("<h3>Erro no processo de atualização!</h3>");
				out.println("<input type=button value=voltar onclick=history.go(-1) />  <br>" );
				out.println("</body></html>");
			}
		}	
		break;
		}
	}

}
