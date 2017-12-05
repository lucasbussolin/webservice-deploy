package pc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pc.datasource.PcDatasource;
import viewobject.personalcomputer.Psu;

public class PsuDao {

	private PcDatasource connection;
	
	public Psu getPsuByPrice(Double preco) {
		Psu retorno = null;
		PreparedStatement stmt = null;
		
		try {
			connection = new PcDatasource();
			String sql = "SELECT nome, fabricante, potencia, classificacao, valor FROM psu "
					+ "WHERE valor BETWEEN ? AND ? ";
			stmt = connection.getPreparedStatement(sql);
			stmt.setDouble(1, preco - (preco * 0.99));
			stmt.setDouble(2, preco + (preco * 0.99));
			
			ResultSet resultado = stmt.executeQuery();
			while (resultado.next()) {
				retorno = new Psu();
				retorno.setNome(resultado.getString("nome"));
				retorno.setFabricante(resultado.getString("fabricante"));
				retorno.setPotencia(resultado.getInt("potencia"));
				retorno.setClassficacao(resultado.getString("classificacao"));
				retorno.setValor(resultado.getDouble("valor"));
			}	
		} 
		catch (SQLException e) {
			System.out.println("Couldnt get object in database!\n SqlState: " + e.getSQLState() + "\nErrorCode: "
					+ e.getErrorCode() + " " + "\nMessage: " + e.getMessage());
		}
		catch (Exception e) {
			
		} finally {
			if (connection != null) {
				connection.closeConnection(stmt);
			}
		}
		return retorno;
	}
	
	public void savePsu(Psu psu) {
		PreparedStatement stmt = null;
		try {
			connection = new PcDatasource();
			String sql = "INSERT INTO psu(nome, fabricante, potencia, classificacao, valor) VALUES(?, ?, ?, ?, ?)";
			stmt = connection.getPreparedStatement(sql);
			stmt.setString(1, psu.getNome());
			stmt.setString(2, psu.getFabricante());
			stmt.setInt(3, psu.getPotencia());
			stmt.setString(4, psu.getClassficacao());
			stmt.setDouble(5, psu.getValor());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Couldnt save object in database!\n SqlState: " + e.getSQLState() + "\nErrorCode: "
					+ e.getErrorCode() + " " + "\nMessage: " + e.getMessage());
		} finally {
			if (connection != null) {
				connection.closeConnection(stmt);
			}
		}
	}
}
