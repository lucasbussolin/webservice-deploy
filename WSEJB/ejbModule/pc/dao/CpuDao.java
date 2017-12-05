package pc.dao;
import java.sql.SQLException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import pc.datasource.PcDatasource;
import viewobject.personalcomputer.Cpu;

public class CpuDao {

	private PcDatasource connection;

	public void saveCpu(Cpu cpu) {
		PreparedStatement stmt = null;
		try {
			connection = new PcDatasource();
			String sql = "INSERT INTO cpu VALUES(?, ?, ?, ?)";
			stmt = connection.getPreparedStatement(sql);
			
			stmt.setString(1, cpu.getNome());
			stmt.setString(2, cpu.getFabricante());
			stmt.setString(3, cpu.getSocket());
			stmt.setDouble(4, cpu.getPreco());

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
	
	public Cpu getCpuByPrice(Double valor) {
		Cpu retorno = null;
		PreparedStatement stmt = null;
		try {
			connection = new PcDatasource();
			String sql = "SELECT nome, fabricante, socket, preco FROM cpu "
					+ "WHERE preco BETWEEN ? AND ? ";
			stmt = connection.getPreparedStatement(sql);
			stmt.setDouble(1, (valor * 0.9) - valor);
			stmt.setDouble(2, (valor * 0.9) + valor);
			
			ResultSet result = stmt.executeQuery();
			while (result.next()) {
				retorno = new Cpu();
				retorno.setNome(result.getString("nome"));
				retorno.setFabricante(result.getString("fabricante"));
				retorno.setSocket(result.getString("socket"));
				retorno.setPreco(result.getDouble("preco"));
			}
			
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
