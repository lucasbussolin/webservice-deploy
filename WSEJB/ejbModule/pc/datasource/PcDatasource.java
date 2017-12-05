package pc.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PcDatasource {

	// protected static DataSource dataSource;
	private Connection conn;

	public PcDatasource() {
		if (conn == null) {
			conn = pegaConexao();
		}
	}

	private Connection pegaConexao() {
		Connection con = null;
		try {
			System.out.println("Conectando no banco de dados");
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connection = "jdbc:sqlserver://servidorws.database.windows.net:1433;database=webservice";
			String usuario = "wsadm";
			String senha = "Admin4321";
			con = DriverManager.getConnection(connection, usuario, senha);
		} catch (ClassNotFoundException ex) {
			System.out.println("Error: unable to load driver class!");
			System.exit(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (con != null) {
			System.out.println("Conectado com sucesso");
		} else {
			System.out.println("Erro ao conectar!");
		}
		return con;
	}

	public PreparedStatement getPreparedStatement(String sql) {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pstmt;
	}

	public Boolean closeConnection(PreparedStatement psmt) {
		try {
			psmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
}
