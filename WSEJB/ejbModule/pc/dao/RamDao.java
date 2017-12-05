package pc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pc.datasource.PcDatasource;
import viewobject.personalcomputer.Ram;

public class RamDao {
	private PcDatasource connection;

	public void saveRam(Ram ram) {
		PreparedStatement stmt = null;
		try {
			connection = new PcDatasource();
			String sql = "INSERT INTO ram(nome, fabricante, slot, valor) "
					+ "VALUES(?, ?, ?, ?)";
			stmt = connection.getPreparedStatement(sql);
			
			stmt.setString(1, ram.getNome());
			stmt.setString(2, ram.getFabricante());
			stmt.setString(3, ram.getSlot());
			stmt.setDouble(4, ram.getPreco());
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

	public Ram getRamByPriceAndSocket(Double valorRam, String slotRam) {
		Ram retorno = null;
		PreparedStatement stmt = null;
		try {
			connection = new PcDatasource();
			String sql = "SELECT nome, fabricante, slot, valor FROM ram "
					+ "WHERE valor BETWEEN ? AND ? "
					+ "AND slot = ?";
			stmt = connection.getPreparedStatement(sql);
			stmt.setDouble(1, valorRam - (valorRam * 0.9));
			stmt.setDouble(2, valorRam + (valorRam * 0.9));
			stmt.setString(3, slotRam);
			ResultSet resultado = stmt.executeQuery();
			while (resultado.next()) {
				retorno = new Ram();
				retorno.setNome(resultado.getString("nome"));
				retorno.setFabricante(resultado.getString("fabricante"));
				retorno.setPreco(resultado.getDouble("valor"));
				retorno.setSlot(resultado.getString("slot"));
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
