package pc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pc.datasource.PcDatasource;
import viewobject.personalcomputer.Gpu;

public class GpuDao {

	private PcDatasource connection;
	
	public void saveGpu(Gpu gpu) {
		PreparedStatement stmt = null;
		try {
			connection = new PcDatasource();
			String sql = "INSERT INTO gpu VALUES(?, ?, ?)";
			stmt = connection.getPreparedStatement(sql);
			stmt.setString(1, gpu.getNome());
			stmt.setString(2, gpu.getFabricante());
			stmt.setDouble(3, gpu.getPreco());
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Couldnt save object in database!\n SqlState: " + e.getSQLState() + "\nErrorCode: "
					+ e.getErrorCode() + " " + "\nMessage: " + e.getMessage());
		} finally {
			if (stmt != null) {
				connection.closeConnection(stmt);
			}
		}
	}
	
	public Gpu getGpuByPrice(Double preco) {
		Gpu retorno = null;
		PreparedStatement stmt = null;
		try {
			connection = new PcDatasource();
			String sql = "SELECT nome, preco, fabricante FROM gpu "
					+ "WHERE preco BETWEEN ? AND ? ";
			stmt = connection.getPreparedStatement(sql);
			stmt.setDouble(1, preco - (preco * 0.9));
			stmt.setDouble(2, preco + (preco * 0.9));
			
			ResultSet resultado = stmt.executeQuery();
			while (resultado.next()) {
				retorno = new Gpu();
				retorno.setNome(resultado.getString("nome"));
				retorno.setFabricante(resultado.getString("fabricante"));
				retorno.setPreco(resultado.getDouble("preco"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.closeConnection(stmt);
			}
		}
		return retorno;
	}
	
}
