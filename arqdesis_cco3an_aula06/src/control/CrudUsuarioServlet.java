package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Usuario;
import service.UsuarioService;

/**
 * Servlet implementation class CrudUsuarioServlet
 */
@WebServlet("/crudUsuario")
public class CrudUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrudUsuarioServlet() {
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
		// TODO Auto-generated method stub
		//doGet(request, response);
		

		String oQueFazer = request.getParameter("oQueFazer");

		switch (oQueFazer){
		case "Cadastrar":
		{	
			String id = request.getParameter("idUsuario");
			String senha = request.getParameter("senhaUsuario");
			String nome = request.getParameter("nomeUsuario");
			String cpf = request.getParameter("cpfUsuario");			
			int funcao= Integer.parseInt(request.getParameter("funcaoUsuario"));
			boolean configAr= Boolean.parseBoolean(request.getParameter("tempUsuario"));			
			String hrIni = request.getParameter("hrInicialUsuario");
			String hrFim = request.getParameter("hrFinalUsuario");
			String cnpj = request.getParameter("EmpresaUsuario");
			
			Usuario usuario= new Usuario(id, nome, senha, cpf, hrIni, hrFim, configAr, funcao, cnpj);
			UsuarioService service= new UsuarioService();
			
			
			if(service.cadastrar(usuario)){
				PrintWriter out = response.getWriter();
				out.println("<html><head><title>Cadastro Usuario</title></head><body>");
				out.println("<h3>Operação realizada com sucesso\nO ID do cliente é: "+usuario.getId()+".</h3>");
				out.println("<input type=button value=voltar onclick=history.go(-1) />  <br>" );
				out.println("</body></html>");
			}else{
				PrintWriter out = response.getWriter();
				out.println("<html><head><title>Cadastro Usuario</title></head><body>");
				out.println("<h3>Erro ao cadastrar</h3>");
				out.println("<input type=button value=voltar onclick=history.go(-1) />  <br>" );
				out.println("</body></html>");
			}
		}	
		break;
		case "Consultar":
		{	
			UsuarioService service= new UsuarioService();
			Usuario usuario= new Usuario();
			String id = request.getParameter("idUsuario");
			usuario= service.consultar(id);
			
			if(usuario.getNome() != null)
			{	
				request.setAttribute("usuario", usuario);
				
				RequestDispatcher dispatch= request.getRequestDispatcher("consulta.jsp");
				dispatch.forward(request, response);
				
			}else if(usuario.getNome() == null){
				PrintWriter out = response.getWriter();
				out.println("<html><head><title>Consulta Usuario</title></head><body>");
				out.println("<h3>Falha na consulta!</h3>");
				out.println("<input type=button value=voltar onclick=history.go(-1) />  <br>" );
				out.println("</body></html>");	
			}
		}	
		break;
		case "Remover":
		{	
			UsuarioService service= new UsuarioService();
			String id = request.getParameter("idUsuario");
			
			if(service.excluir(id)){
				PrintWriter out = response.getWriter();
				out.println("<html><head><title>Remoção de Cadastro</title></head><body>");
				out.println("<h3>Cliente com ID: "+id+" foi removido com sucesso.</h3>");
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
			String id = request.getParameter("idUsuario");
			String senha = request.getParameter("senhaUsuario");
			String nome = request.getParameter("nomeUsuario");
			String cpf = request.getParameter("cpfUsuario");			
			int funcao= Integer.parseInt(request.getParameter("funcaoUsuario"));
			boolean configAr= Boolean.parseBoolean(request.getParameter("tempUsuario"));			
			String hrIni = request.getParameter("hrInicialUsuario");
			String hrFim = request.getParameter("hrFinalUsuario");
			String cnpj = request.getParameter("EmpresaUsuario");
			
			Usuario usuario= new Usuario(id, nome, senha, cpf, hrIni, hrFim, configAr, funcao, cnpj);
			
			UsuarioService service= new UsuarioService();
			Usuario antigo= new Usuario(); 
			Usuario novo= new Usuario();
			antigo= service.consultar(usuario.getId());
			
			if(service.alterar(usuario)){
				
				novo= service.consultar(usuario.getId());
				
				request.setAttribute("antigo", antigo);
				request.setAttribute("novo", novo);				
				
				RequestDispatcher dispatch= request.getRequestDispatcher("atualiza.jsp");
				dispatch.forward(request, response);

			}else{
				PrintWriter out = response.getWriter();
				out.println("<html><head><title>Atualização de Cadastro Usuario</title></head><body>");
				out.println("<h3>Erro no processo de atualização!</h3>");
				out.println("<input type=button value=voltar onclick=history.go(-1) />  <br>" );
				out.println("</body></html>");
			}
		}	
		break;
		}
	}
	
}
