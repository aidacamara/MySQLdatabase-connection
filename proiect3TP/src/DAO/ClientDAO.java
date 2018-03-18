package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.Statement;

import Connection.ConnectionFactory;
import Model.Client;
/**
 * 
 * @author Aida
 * Contine operatiile de inserare si actualizarea unui client
 * Extinde clasa AbstractDAO
 */
public class ClientDAO extends AbstractDAO<Client> {

	private static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO client (id,nume)" + " VALUES (?,?)";
	private static final String updateStatementString= "UPDATE client SET nume=? WHERE id=?";
	
	/**
	 * Insereaza in tabel un client 
	 * Creeaza conexiunea cu baza de date si inlocuieste "?" din interogarea MySQL cu variabilele
	 *  de instanta ale clientului care urmeaza sa fie introdus 
	 * @param client obiectul de tipul Client care o sa fie inserat in tabel
	 * @return valoarea id-ului clientului introdus
	 */
	public int insert(Client client){
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement=null;
		int insertedId=-1;
		try {
			insertStatement=dbConnection.prepareStatement(insertStatementString,Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1, client.getId());
			insertStatement.setString(2, client.getNume());
			insertStatement.executeUpdate();
			ResultSet rs=insertStatement.getGeneratedKeys();
			if(rs.next()){
				insertedId=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println(e.getMessage());
		}finally{
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
		
	}
	
	
	/**
	 * Actualizeaza campul nume al clientului care are id-ul egal cu paramtetrul id
	 * Stabileste conexiunea cu baza de date
	 * Inlocuieste "?" din interogarea mySQL cu parametrii functiei si executa update-ul
	 * @param id id-ul clientului cautat
	 * @param value valoarea cu care se va actualiza clientul cautat
	 * @return id-ul clientului care a fost actualizat
	 */
	public int updateById( int id, String value){
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement updateStatement=null;
		int updatedId=-1;
		try {
			updateStatement=dbConnection.prepareStatement(updateStatementString,Statement.RETURN_GENERATED_KEYS);
			updateStatement.setString(1, value);
			updateStatement.setInt(2, id);
			updateStatement.executeUpdate();
			ResultSet rs=updateStatement.getGeneratedKeys();
			if(rs.next()){
				updatedId=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println(e.getMessage());
		}finally{
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(dbConnection);
		}
		return updatedId;
		
	
	}

	

}
