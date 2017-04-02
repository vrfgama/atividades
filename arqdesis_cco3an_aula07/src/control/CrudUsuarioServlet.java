package control;

import java.io.IOException;

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
				
				request.setAttribute("usuario", usuario);
				
				RequestDispatcher dispatch= request.getRequestDispatcher("sucesso.jsp");
				dispatch.forward(request, response);
				
			}else{
				
				request.setAttribute("usuario", usuario);
				
				RequestDispatcher dispatch= request.getRequestDispatcher("erro.jsp");
				dispatch.forward(request, response);
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
				
				request.setAttribute("usuario", usuario);
				
				RequestDispatcher dispatch= request.getRequestDispatcher("erro.jsp");
				dispatch.forward(request, response);
			}
		}	
		break;
		case "Remover":
		{	
			Usuario usuario= new Usuario();
			UsuarioService service= new UsuarioService();
			String id = request.getParameter("idUsuario");
			
			if(service.excluir(id)){
				
				request.setAttribute("usuario", usuario);
				
				RequestDispatcher dispatch= request.getRequestDispatcher("sucesso.jsp");
				dispatch.forward(request, response);
				
			}else{
				
				request.setAttribute("usuario", usuario);
				
				RequestDispatcher dispatch= request.getRequestDispatcher("erro.jsp");
				dispatch.forward(request, response);
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
				
				request.setAttribute("usuario", usuario);
				
				RequestDispatcher dispatch= request.getRequestDispatcher("erro.jsp");
				dispatch.forward(request, response);
			}
		}	
		break;
		}
	}
	
}
