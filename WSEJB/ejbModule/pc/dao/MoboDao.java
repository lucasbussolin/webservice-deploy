package pc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pc.datasource.PcDatasource;
import viewobject.personalcomputer.PlacaMae;

public class MoboDao {

	private PcDatasource connection;
	
	public void saveMobo(PlacaMae mobo) {
		PreparedStatement stmt = null;
		try {
			connection = new PcDatasource();
			String sql = "INSERT INTO placamae(nome, fabricante, socket, slotram, totalram, quantidadepci, valor) "
					+ "VALUES(?, ?, ?, ?, ?, ?, ?)";
			stmt = connection.getPreparedStatement(sql);
			
			stmt.setString(1, mobo.getNome());
			stmt.setString(2, mobo.getFabricante());
			stmt.setString(3, mobo.getSocket());
			stmt.setString(4, mobo.getSlotRam());
			stmt.setInt(5, mobo.getTotalRam());
			stmt.setInt(6, mobo.getQtdPcie());
			stmt.setDouble(7, mobo.getValor());
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

	public PlacaMae getMoboByPriceAndSocket(Double valorMobo, String socket) {
		PlacaMae retorno = null;
		PreparedStatement stmt = null;
		try {
			connection = new PcDatasource();
			String sql = "SELECT * FROM placamae "
					+ "WHERE valor BETWEEN ? AND ? "
					+ "AND socket = ?";
			stmt = connection.getPreparedStatement(sql);
			
			stmt.setDouble(1, valorMobo - (valorMobo * .9));
			stmt.setDouble(2, valorMobo + (valorMobo * .9));
			stmt.setString(3, socket);
			
			ResultSet result = stmt.executeQuery();
			
			while (result.next()) {
				retorno = new PlacaMae();
				retorno.setNome(result.getString("nome"));
				retorno.setFabricante(result.getString("fabricante"));
				retorno.setSocket(result.getString("socket"));
				retorno.setSlotRam(result.getString("slotram"));
				retorno.setTotalRam(result.getInt("totalram"));
				retorno.setQtdPcie(result.getInt("quantidadepci"));
				retorno.setValor(result.getDouble("valor"));
			}
				
		} catch (SQLException e) {
			System.out.println("Couldnt get object in database!\n SqlState: " + e.getSQLState() + "\nErrorCode: "
					+ e.getErrorCode() + " " + "\nMessage: " + e.getMessage());
		} finally {
			if (stmt != null) {
				connection.closeConnection(stmt);
			}
		}
		return retorno;
	}
}
