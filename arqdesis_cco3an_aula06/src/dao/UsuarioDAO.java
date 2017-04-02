package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Usuario;

public class UsuarioDAO
{    	
	   String id, nome, senha, cpf, horaEnt, horaSai, cnpj;
	   int perfil;
	   boolean mudaTemp;
	
	   public boolean cadastrar(Usuario usuario)
	   {
	      boolean sucesso= false;
	      String sqlInsert = "insert into usuario values(?, ?, ?, ?, ?, ?, ?, ?, ?);";

	      
	      try(Connection conn = AcessoBD.obtemConexao();
	    		  PreparedStatement stm = conn.prepareStatement(sqlInsert);)
	      {
	         stm.setString(1, usuario.getId());
	         stm.setString(2, usuario.getNome());
	         stm.setString(3, usuario.getSenha());
	         stm.setString(4, usuario.getCpf());
	         stm.setInt(5, usuario.getPerfil());
	         stm.setBoolean(6, usuario.isMudaTemp());
	         stm.setString(7, usuario.getHoraEnt());
	         stm.setString(8, usuario.getHoraSai());
	         stm.setString(9, usuario.getCnpj());
	         stm.execute();
	         sucesso= true;
	         
	      }
	      catch(Exception e)
	      {
	         e.printStackTrace();
	      } 
	      
	      return sucesso;
	   }
	   
	   
	   public Usuario consultar(String id)
	   {
	      Usuario usuario;
	      String sqlSelect= "select * from usuario where idUsuario= ?;";
	      ResultSet rs = null;
	      	      
	      try(Connection conn = AcessoBD.obtemConexao();
	    		  PreparedStatement stm = conn.prepareStatement(sqlSelect);)
	      {
	         stm.setString(1, id);
	         rs = stm.executeQuery();
	         
	         if(rs.next())
	         {
	            id= rs.getString("idUsuario");
	            nome= rs.getString("nome");
	            senha= rs.getString("senha");
	            cpf= rs.getString("cpf");
	            perfil= rs.getInt("perfil");
	            mudaTemp= rs.getBoolean("mudaTemp");
	            horaEnt= rs.getString("horaEntrada");
	            horaSai= rs.getString("horaSaida");
	            cnpj= rs.getString("Empresa_cnpjEmpresa"); 

	         }      
	      }
	      catch(Exception ex)
	      {
	         ex.printStackTrace();   
	      }
	      
	      usuario= new Usuario(id, nome, senha, cpf, horaEnt, horaSai, mudaTemp, perfil, cnpj);
	      return usuario;
	   } 
	   
	   
	   public ArrayList<Usuario> consultarTodos()
	   {
	      Usuario usuario;
	      ArrayList<Usuario> arraylist= new ArrayList<Usuario>();
	      String sqlSelect= "select * from usuario;";
	      ResultSet rs = null;
	      
	      try(Connection conn = AcessoBD.obtemConexao();
	    		  PreparedStatement stm = conn.prepareStatement(sqlSelect);)
	      {

	         rs = stm.executeQuery();
	         
	         while(rs.next())
	         {
	        	id= rs.getString("idUsuario");
		        nome= rs.getString("nome");
		        senha= rs.getString("senha");
		        cpf= rs.getString("cpf");
		        perfil= rs.getInt("perfil");
		        mudaTemp= rs.getBoolean("mudaTemp");
		        horaEnt= rs.getString("horaEntrada");
		        horaSai= rs.getString("horaSaida");
		        cnpj= rs.getString("Empresa_cnpjEmpresa");
	            
	            usuario= new Usuario(id, nome, senha, cpf, horaEnt, horaSai, mudaTemp, perfil, cnpj); 
	            arraylist.add(usuario); 
	         }      
	      }
	      catch(Exception ex)
	      {
	         ex.printStackTrace();   
	      }
	      
	      return arraylist;
	   }
	   
	   
	   public boolean alterar(Usuario usuario)
	   {
	      boolean sucesso= false;
	      String sqlInsert = "update usuario set nome= ?, senha= ?, cpf= ?, perfil= ?, mudaTemp= ?, horaEntrada= ?,"+ 
	                         "horaSaida= ?, Empresa_cnpjEmpresa= ? where idUsuario= ?;";
	      
	      try(Connection conn = AcessoBD.obtemConexao();
	    		  PreparedStatement stm = conn.prepareStatement(sqlInsert);)
	      {
	         
	         stm.setString(1, usuario.getNome());
	         stm.setString(2, usuario.getSenha());
	         stm.setString(3, usuario.getCpf());
	         stm.setInt(4, usuario.getPerfil());
	         stm.setBoolean(5, usuario.isMudaTemp());
	         stm.setString(6, usuario.getHoraEnt());
	         stm.setString(7, usuario.getHoraSai());
	         stm.setString(8, usuario.getCnpj());
	         stm.setString(9, usuario.getId());
	         stm.execute();
	         sucesso= true;
	         
	      }
	      catch(Exception e)
	      {
	         e.printStackTrace();
	      }
	      
	      return sucesso;   
	   }
	   
	   
	   public boolean excluir(String id)
	   {
	      boolean sucesso= false;
	      String sqlInsert = "delete from usuario where idUsuario= ?;";
	      
	      try(Connection conn = AcessoBD.obtemConexao();
	    		  PreparedStatement stm = conn.prepareStatement(sqlInsert);)
	      {

	         stm.setString(1, id);
	         stm.execute();
	         sucesso= true;
	      }
	      catch(Exception e)
	      {
	         e.printStackTrace();
	      }
	      
	      return sucesso; 
	   }
	   


}
