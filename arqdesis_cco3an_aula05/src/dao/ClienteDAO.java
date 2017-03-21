package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Cliente;

public class ClienteDAO {
	public boolean criar(Cliente cliente) {
		boolean sucesso= false;
		String sqlInsert = "INSERT INTO cliente(id, nome, fone, email) VALUES (?, ?, ?, ?)";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setInt(1, cliente.getId());
			stm.setString(2, cliente.getNome());
			stm.setString(3, cliente.getFone());
			stm.setString(4, cliente.getEmail());
			stm.execute();
			sucesso= true;
			/*
			String sqlQuery = "SELECT LAST_INSERT_ID()";  // retorna sempre zero
			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
					ResultSet rs = stm2.executeQuery();) {
				if (rs.next()) {
					cliente.setId(rs.getInt(1));
					System.out.println(cliente.getId());
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}*/
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sucesso;
	}

	public boolean atualizar(Cliente cliente) {
		boolean sucesso= false;
		String sqlUpdate = "UPDATE cliente SET nome=?, fone=?, email=? WHERE id=?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setString(1, cliente.getNome());
			stm.setString(2, cliente.getFone());
			stm.setString(3, cliente.getEmail());
			stm.setInt(4, cliente.getId());
			stm.execute();
			sucesso= true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sucesso;
	}

	public boolean excluir(int id) {
		boolean sucesso= false;
		String sqlDelete = "DELETE FROM cliente WHERE id = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, id);
			stm.execute();
			sucesso= true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sucesso;
	}

	public Cliente carregar(int id) {
		Cliente cliente = new Cliente();
		cliente.setId(id);
		String sqlSelect = "SELECT nome, fone, email FROM cliente WHERE cliente.id = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, cliente.getId());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					cliente.setNome(rs.getString("nome"));
					cliente.setFone(rs.getString("fone"));
					cliente.setEmail(rs.getString("email"));
				} else {
					cliente.setId(-1);
					cliente.setNome(null);
					cliente.setFone(null);
					cliente.setEmail(null);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return cliente;
	}

}
