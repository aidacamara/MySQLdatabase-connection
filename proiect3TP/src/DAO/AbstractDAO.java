package DAO;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import Connection.ConnectionFactory;

/**
 * 
 * @author Aida
 * Contine operatiile de afisarea tuturor datelor din tabel si gasirea unui rand specfic
 * Se bazeaza pe tactica reflection, metodele pot fi folosite de oricare dintre modele
 * @param <T> generic, se inlocuieste cu unul dintre modele atunci cand se declara un obiect
 */

public class AbstractDAO<T> {
	private static final Logger LOGGER=Logger.getLogger(AbstractDAO.class.getName());
	
	private final Class<T> type;
	
	@SuppressWarnings("unchecked")
	public AbstractDAO(){
		this.type=(Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	private String createSelectQuery(String field){
		StringBuilder sb=new StringBuilder();
		sb.append("SELECT ");
		sb.append(" * ");
		sb.append(" FROM ");
		sb.append(type.getSimpleName());
		sb.append(" WHERE "+field+" =?");
		return sb.toString();
	}
	
	private String createSelectAllQuery(){
		StringBuilder sb=new StringBuilder();
		sb.append("SELECT ");
		sb.append(" * ");
		sb.append(" FROM ");
		sb.append(type.getSimpleName());
		return sb.toString();
	}
	
	private String createDeleteQuery(String field){
		StringBuilder sb=new StringBuilder();
		sb.append("DELETE ");
		sb.append(" FROM ");
		sb.append(type.getSimpleName());
		sb.append(" WHERE "+field+" =?");
		return sb.toString();
	}
	
	
	
	/**
	 * Gaseste inregistrarea care are id ul specificat in parametru
	 * Creeaza conexiunea la baza de date si executa interogarea generata de metoda createSelectQuery(String field)
	 * @param id valoarea id-ului cautat in tabel
	 * @return un obiect de tip T care are id-ul egal cu valoarea parametrului
	 */
	
	public T findById(int id){
		Connection connection=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		String query=createSelectQuery("id");
		
		try {
			connection=ConnectionFactory.getConnection();
			statement=connection.prepareStatement(query);
			statement.setInt(1, id);
			resultSet=statement.executeQuery();
			return createObjects(resultSet).get(0);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName()+" DAO:findById "+ e.getMessage());
			
		}finally{
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
		
	}
	/**
	 * Gaseste toate inregitrarile dintr-un tabel 
	 * Creeaza conexiunea la baza de date si executa interogarea generata de metoda createSelectAllQuery()
	 * @return o lista de obiecte de tip T care se gasesc in tabelul din baza de date
	 */
	
	public List<T> FindAll(){
		Connection connection=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		String query=createSelectAllQuery();
		
		try {
			connection=ConnectionFactory.getConnection();
			statement=connection.prepareStatement(query);
			
			resultSet=statement.executeQuery();
			return createObjects(resultSet);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName()+" DAO:findAll "+ e.getMessage());
			
		}finally{
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
		
	}
	
	/**
	 * Sterge din tabelul corespunzator obiectul care are id-ul specificat
	 * Creeaza conexiunea la baza de date si executa interogarea generata de metoda createDeleteQuery(String field)
	 * @param id id valoarea id-ului cautat in tabel
	 */
	
	public void deleteById(int id){
		Connection connection=null;
		PreparedStatement statement=null;
		//ResultSet resultSet=null;
		String query=createDeleteQuery("id");
		
		try {
			connection=ConnectionFactory.getConnection();
			statement=connection.prepareStatement(query);
			statement.setInt(1, id);
			statement.executeUpdate();
			//return createObjects(resultSet).get(0);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName()+" DAO:deleteById "+ e.getMessage());
			
		}finally{
			//ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		//return null;
	}
	
	
	
	private List<T> createObjects(ResultSet resultSet){
		List<T> list=new ArrayList<T>();
		try {
			while(resultSet.next()){
				
					T instance=type.newInstance();
					for(Field field: type.getDeclaredFields()){
						Object value=resultSet.getObject(field.getName());
						PropertyDescriptor propertyDescriptor=new PropertyDescriptor(field.getName(),type);
						Method method=propertyDescriptor.getWriteMethod();
						method.invoke(instance, value);
					}
				list.add(instance);
				
			}
		}catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println(e.getMessage());
		}
		catch(IntrospectionException e){
			//e.printStackTrace();
			System.out.println(e.getMessage());
		}
		catch(InvocationTargetException e){
			//e.printStackTrace();
			System.out.println(e.getMessage());
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return list;
	}
	

	//public abstract int insert(T object);
}
