package service;

import modelo.Cliente;
import dao.ClienteDAO;


public class ClienteService {
	ClienteDAO dao = new ClienteDAO();
	
	public boolean criar(Cliente cliente) {
		return dao.criar(cliente);
	}
	
	public boolean atualizar(Cliente cliente){
		return dao.atualizar(cliente);
	}
	
	public boolean excluir(int id){
		return dao.excluir(id);
	}
	
	public Cliente carregar(int id){
		return dao.carregar(id);
	}

}
