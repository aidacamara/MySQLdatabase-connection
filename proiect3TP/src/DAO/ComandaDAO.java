package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import com.mysql.jdbc.Statement;

import Connection.ConnectionFactory;
import Model.Comanda;
import Model.Produs;
import Validare.ComandaValidator;
import Validare.ValidatorProdus;
/**
 * 
 * @author Aida
 * Contine operatiile de inserare si actualizarea unei comenzi
 * Extinde clasa AbstractDAO
 */
public class ComandaDAO extends AbstractDAO<Comanda> {
	private static final Logger LOGGER = Logger.getLogger(ComandaDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO comanda (id,idClient,idProdus,cantitate)" + " VALUES (?,?,?,?)";
	private static final String updateStatementString= "UPDATE comanda SET idClient=?, idProdus=?, cantitate=? WHERE id=?";
	
	/**
	 * Insereaza in tabel o comanda 
	 * Creeaza conexiunea cu baza de date
	 * Verifica daca comanda care urmeaza sa fie introdusa este valida
	 * Inlocuieste "?" din interogarea MySQL cu variabilele
	 * de instanta ale comenzii care urmeaza sa fie introdusa 
	 * @param comanda obiectul de tipul Comanda care o sa fie inserat in tabel
	 * @return valoarea id-ului comenzii introduse
	 */
	public int insert(Comanda comanda){
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement=null;
		int insertedId=-1;
		try {
			insertStatement=dbConnection.prepareStatement(insertStatementString,Statement.RETURN_GENERATED_KEYS);
			ComandaValidator validator=new ComandaValidator();
			try{
				validator.validate(comanda);
			}catch(IllegalArgumentException e){
				//e.printStackTrace();
				System.out.println(e.getMessage());
			}
			insertStatement.setInt(1, comanda.getId());
			insertStatement.setInt(2, comanda.getIdClient());
			insertStatement.setInt(3, comanda.getIdProdus());
			insertStatement.setInt(4, comanda.getCantitate());
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
	 * Actualizeaza campurile idClient, idProdus si cantitatea comenzii care are id-ul egal cu paramtetrul id
	 * Stabileste conexiunea cu baza de date
	 * Verifica daca actualizarile sunt valide
	 * Inlocuieste "?" din interogarea mySQL cu parametrii functiei si executa update-ul
	 * @param id id-ul comenzii cautate
	 * @param idClient valoarea cu care se va actualiza id-ul clientului
	 * @param idProdus valoarea cu care se va actualiza id-ul produsului comandat
	 * @param cantitate valoarea cu care se va actualiza canitatea de produse
	 * @return id-ul comenzii care a fost actualizata
	 */
	
	public int updateById(int id,int idClient, int idProdus, int cantitate){
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement updateStatement=null;
		int updatedId=-1;
		try {
			updateStatement=dbConnection.prepareStatement(updateStatementString,Statement.RETURN_GENERATED_KEYS);
			ComandaValidator validator=new ComandaValidator();
			Comanda comanda=new Comanda(id, idClient, idProdus,cantitate);
			try{
				validator.validate(comanda);
			}catch(IllegalArgumentException e){
				//e.printStackTrace();
				//System.out.println(e.getMessage());
				System.out.println(e.getMessage());
			}
			
			updateStatement.setInt(1, idClient);
			updateStatement.setInt(2, idProdus);
			updateStatement.setInt(3, cantitate);
			updateStatement.setInt(4, id);
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
