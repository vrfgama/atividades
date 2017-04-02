package service;

import model.Usuario;

import java.util.ArrayList;

import dao.UsuarioDAO;

public class UsuarioService
{	

	UsuarioDAO usuariodao= new UsuarioDAO();
	
	public boolean cadastrar(Usuario usuario)
	{
		return usuariodao.cadastrar(usuario);
	}
	
	public boolean alterar(Usuario usuario)
	{		
		return usuariodao.alterar(usuario);
	}
	
	public boolean excluir(String id)
	{
		return usuariodao.excluir(id);
	}
	
	public Usuario consultar(String id)
	{	
		return usuariodao.consultar(id);
	}
	
	public ArrayList<Usuario> consultarTodos()
	{
		return usuariodao.consultarTodos();
	}

}
