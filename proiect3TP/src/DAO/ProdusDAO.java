package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import com.mysql.jdbc.Statement;

import Connection.ConnectionFactory;
import Model.Produs;
import Validare.ValidatorProdus;
/**
 * 
 * @author Aida
 * Contine operatiile de inserare si actualizarea unui produs
 * Extinde clasa AbstractDAO
 * 
 */
public class ProdusDAO extends AbstractDAO<Produs> {

	private static final Logger LOGGER = Logger.getLogger(ProdusDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO produs (id,nume,pret,cantitate)" + " VALUES (?,?,?,?)";
	private static final String updateStatementString= "UPDATE produs SET nume=?, pret=?, cantitate=? WHERE id=?";
	/**
	 * Insereaza in tabel o comanda 
	 * Creeaza conexiunea cu baza de date
	 * Verifica daca produsul care urmeaza sa fie introdus este valid
	 * Inlocuieste "?" din interogarea MySQL cu variabilele
	 * de instanta ale produsului care urmeaza sa fie introdus
	 * @param produs  obiectul de tipul Produs care o sa fie inserat in tabel
	 * @return valoarea id-ului produsului introdus
	 */
	public int insert(Produs produs){
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement=null;
		int insertedId=-1;
		try {
			insertStatement=dbConnection.prepareStatement(insertStatementString,Statement.RETURN_GENERATED_KEYS);
			ValidatorProdus validator=new ValidatorProdus();
			try{
				validator.validate(produs);
			}catch(IllegalArgumentException e){
				//e.printStackTrace();
				System.out.println(e.getMessage());
			}
			insertStatement.setInt(1, produs.getId());
			insertStatement.setString(2, produs.getNume());
			insertStatement.setDouble(3, produs.getPret());
			insertStatement.setInt(4,produs.getCantitate());
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
	 * Actualizeaza campurile nume, pret si cantitatea produsului care are id-ul egal cu paramtetrul id
	 * Stabileste conexiunea cu baza de date
	 * Verifica daca actualizarile sunt valide
	 * Inlocuieste "?" din interogarea mySQL cu parametrii functiei si executa update-ul
	 * @param id  id-ul produsului cautate
	 * @param nume valoarea cu care se va actualiza denumirea prodsului
	 * @param pret valoarea cu care se va actualiza pretul produsului
	 * @param cantitate valoarea cu care se va actualiza cantitatea disponibia a produsului
	 * @return id-ul produsului inserat
	 */
	public int updateById(int id,String nume,double pret,int cantitate){
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement updateStatement=null;
		int updatedId=-1;
		try {
			updateStatement=dbConnection.prepareStatement(updateStatementString,Statement.RETURN_GENERATED_KEYS);
			ValidatorProdus validator=new ValidatorProdus();
			Produs produs=new Produs(id,nume,pret,cantitate);
			try{
				validator.validate(produs);
			}catch(IllegalArgumentException e){
				//e.printStackTrace();
				System.out.println(e.getMessage());
			}
			
			updateStatement.setString(1, nume);
			updateStatement.setDouble(2, pret);
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
