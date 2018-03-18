package Interfata;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import DAO.AbstractDAO;
import DAO.ClientDAO;
import DAO.ComandaDAO;
import DAO.ProdusDAO;
import Model.Client;
import Model.Comanda;
import Model.Produs;
/**
 * 
 * @author Aida
 * Face legatura dintre controllerele interfetelor si logica aplicatiei
 * Comunica cu pachetul DAO 
 */
public class Service {

	public static Client findByIdClient(int id){
		AbstractDAO<Client> clientDAO=new ClientDAO();
		return (Client) clientDAO.findById(id); 
	}
	
	public static List<Client> findAllClient(){
		AbstractDAO<Client> clientDAO=new ClientDAO();
		List<Client> result=clientDAO.FindAll();
		
		return result;
	}
	
	public static void deleteByIdClient(int id){
		AbstractDAO<Client> clientDAO=new ClientDAO();
		clientDAO.deleteById(id);
	}
	
	public static void insertClient(int id, String nume){
		ClientDAO clientDAO=new ClientDAO();
		Client client=new Client(id,nume);
		clientDAO.insert(client);
	}
	
	public static void updateClient(int id, String nume){
		ClientDAO clientDAO=new ClientDAO();
		
		clientDAO.updateById(id,nume);
	}
	
	public static Produs findByIdProdus(String idString){
		try{
			int id=Integer.parseInt(idString);
			AbstractDAO<Produs> produsDAO=new ProdusDAO();
			return (Produs)produsDAO.findById(id);
		}catch(NumberFormatException e){
			System.out.println("date gresite");
		}
		return null;
		
	}
	
	public static List<Produs> findAllProdus(){
		
			AbstractDAO<Produs> produsDAO=new ProdusDAO();
			return produsDAO.FindAll();
		
	}
	
	public static void deleteByIdProdus(String idString){
		try{
			int id=Integer.parseInt(idString);
			AbstractDAO<Produs> produsDAO=new ProdusDAO();
			produsDAO.deleteById(id);
		}catch(NumberFormatException e){
			System.out.println("date gresite");
		}
		
	}
	
	public static void insertProdus(String idString,String nume,String cantitateString,String pretString){
		try{
			int id=Integer.parseInt(idString);
			int cantitate=Integer.parseInt(cantitateString);
			double pret=Double.parseDouble(pretString);
			ProdusDAO produsDAO=new ProdusDAO();
			Produs produs=new Produs(id,nume,pret,cantitate);
			produsDAO.insert(produs);
		}catch(NumberFormatException e){
			System.out.println("date gresite");
		}
	}
	
	public static void updateProdus(String idString,String nume,String cantitateString,String pretString){
		try{
			int id=Integer.parseInt(idString);
			int cantitate=Integer.parseInt(cantitateString);
			double pret=Double.parseDouble(pretString);
			ProdusDAO produsDAO=new ProdusDAO();
			produsDAO.updateById(id,nume,pret,cantitate);
		}catch(NumberFormatException e){
			System.out.println("date gresite");
		}
	}
	
	public static Comanda findByIdComanda(String idString){
		try{
			int id=Integer.parseInt(idString);
			AbstractDAO<Comanda> comandaDAO=new ComandaDAO();
			return (Comanda)comandaDAO.findById(id);
		}catch(NumberFormatException e){
			System.out.println("date gresite");
		}
		return null;
		
	}
	
	public static List<Comanda> findAllComanda(){
		
			AbstractDAO<Comanda> comandaDAO=new ComandaDAO();
			return comandaDAO.FindAll();
		
	}
	
	public static void deleteByIdComanda(String idString){
		try{
			int id=Integer.parseInt(idString);
			AbstractDAO<Comanda> comandaDAO=new ComandaDAO();
			comandaDAO.deleteById(id);
		}catch(NumberFormatException e){
			System.out.println("date gresite");
		}
		
	}
	
	public static void insertComanda(String idString,String idClientString,String idProdusString,String cantitateString){
		try{
			int id=Integer.parseInt(idString);
			int idClient=Integer.parseInt(idClientString);
			int idProdus=Integer.parseInt(idProdusString);
			int cantitate=Integer.parseInt(cantitateString);
			ComandaDAO comandaDAO=new ComandaDAO();
			Comanda comanda= new Comanda(id, idClient, idProdus, cantitate);
			comandaDAO.insert(comanda);
		}catch(NumberFormatException e){
			System.out.println("date gresite");
		}
	}
	
	public static void updateComanda(String idString,String idClientString,String idProdusString,String cantitateString){
		try{
			int id=Integer.parseInt(idString);
			int idClient=Integer.parseInt(idClientString);
			int idProdus=Integer.parseInt(idProdusString);
			int cantitate=Integer.parseInt(cantitateString);
			ComandaDAO comandaDAO=new ComandaDAO();
			comandaDAO.updateById(id, idClient, idProdus, cantitate);
		}catch(NumberFormatException e){
			System.out.println("date gresite");
		}
	}
	
	public static void scrieFisier(String idComandaString, String idClientString, String idProdusString, String cantitateString){
		try{
			
			int idProdus=Integer.parseInt(idProdusString);
			int cantitate=Integer.parseInt(cantitateString);
			
			AbstractDAO<Produs> produsDAO=new ProdusDAO();
			Produs produs=produsDAO.findById(idProdus);
			double totalPlata=produs.getPret()*cantitate;
			
			try{
			    PrintWriter writer = new PrintWriter("Comanda"+idComandaString+".txt", "UTF-8");
			    writer.println("Comanda "+idComandaString);
			    writer.println("Clientul "+idClientString);
			    writer.println("Total plata "+totalPlata);
			    writer.close();
			} catch (IOException e) {
			  System.out.println("eroare la scrierea in fisier");
			}
		}catch(NumberFormatException e){
			System.out.println("date gresite");
		}
		
	}
	
}
