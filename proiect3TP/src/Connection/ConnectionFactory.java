package Connection;


import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clasa in care se implementeaza conexiunea la baza de date din MySQL Workbench si inchiderea acesteia
 * @author Aida
 *
 */

public class ConnectionFactory {

	private static final Logger LOGGER=Logger.getLogger(ConnectionFactory.class.getName());
	private static final String DRIVER="com.mysql.jdbc.Driver";
	private static final String DBURL="jdbc:mysql://localhost:3306/temaTP?useSSL=false";
	private static final String USER="root";
	private static final String PASS="root";
	
	private static ConnectionFactory singleInstance=new ConnectionFactory();
	
	private ConnectionFactory(){
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Connection createConnection(){
		Connection connection=null;
		try {
			connection=DriverManager.getConnection(DBURL,USER,PASS);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Eroare la conectare la baza de date");
			e.printStackTrace();
		}
		return connection;
	}
	
	public static Connection getConnection(){
		return singleInstance.createConnection();
	}
	/**
	 * Inchide conexiunea la baza de date 
	 * @param connection conexiunea care trebuie inchisa
	 */
	public static void close(Connection connection){
		if(connection!=null){
			try{
				connection.close();
			}catch(SQLException e){
				LOGGER.log(Level.WARNING, "Eroare la inchiderea conexiunii");
			}
		}
	}
	/**
	 * Inchide interogarea care s-a facut pe baza de date
	 * @param statement interogarea folosita
	 */
	public static void close(Statement statement){
		try {
			statement.close();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Eroare la inchiderea statement ului");
		}
	}
	/**
	 * Inchide rezultatul generat in urma interogarii bazei de date
	 * @param resultSet rezultatul generat in urma interogarii bazei de date
	 */
	public static void close(ResultSet resultSet){
		try {
			resultSet.close();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Eroare la inchiederea rezultatului");
		}
	}
	
}
