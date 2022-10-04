package br.com.ProjetoFinalCrud.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.ProjetoFinalCrud.factory.ConnectionFactory;
import br.com.ProjetoFinalCrud.model.educador;

public class EducadorDAO {
	
	public void save(educador Educador) {
		String sql = "INSERT INTO educador(nome,telefone,cpf)"+
	"VALUES(?,?,?)";
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, Educador.getNome());
			pstm.setInt(2, Educador.getTelefone());
			pstm.setInt(3, Educador.getCpf());
			
			pstm.execute();			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstm != null) {
					pstm.close();
				}
				if(conn != null) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void removeById(int id) {
		String sql = "DELETE FROM educador WHERE id = ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			pstm.execute();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstm != null) {
					pstm.close();
				}
				if(conn != null) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void update(educador educador) {
		String sql = "UPDATE educador SET nome = ?,"
				+ " telefone = ?,cpf = ? " + "WHERE id = ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, educador.getNome());
			pstm.setInt(2, educador.getTelefone());
			pstm.setInt(3, educador.getCpf());
			pstm.setInt(4, educador.getId());
			pstm.execute();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstm != null) {
					pstm.close();
				}
				if(conn != null) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	public List<educador> geteducadores(){
		String sql = "SELECT * FROM educador";
		List<educador> educadores = new ArrayList<educador>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				educador educador = new educador();
				educador.setId(rset.getInt("id"));
				educador.setNome(rset.getString("nome"));
				educador.setTelefone(rset.getInt("telefone"));
				educador.setCpf(rset.getInt("cpf"));
				educadores.add(educador);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rset != null) {
					rset.close();
				}
				if (pstm != null) {
					pstm.close();
				}
				if(conn != null) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return educadores;
	}
}

