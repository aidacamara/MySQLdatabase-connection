package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 * Tabelul Client din baza de date
 * @author Aida
 *
 */
public class Client {

	private int id;
	private String nume;

	
	public Client(){
		id=-1;
		
		nume="gol";
	
	
	}
	
	public Client(int id, String nume){
		this.id=id;
		this.nume=nume;
	
		
	}
	/**
	 * Metoda necesara pentru crearea unui TableView la afisarea tutror datelor din tabel
	 * @return un obiect de tipul SimpleStringProperty care contine numele clientului
	 */
	public SimpleStringProperty getNumeP(){
		SimpleStringProperty numeP=new SimpleStringProperty(nume);
		return numeP;
	}
	/**
	 * Metoda necesara pentru crearea unui TableView la afisarea tutror datelor din tabel
	 * @return un obiect de tipul SimpleStringProperty care contine id-ul clientului
	 */
	public SimpleStringProperty getIdP(){
		SimpleStringProperty idP=new SimpleStringProperty(Integer.toString(id));
		return idP;
	}
	

	
	public void setId(int idClient) {
		this.id = idClient;
		
		
	}
	public void setNume(String nume) {
		this.nume = nume;
		
	
	}
	public int getId() {
		return id;
	}
	public String getNume() {
		return nume;
	}
	

	public String toString(){
		return "Clientul "+id+" Nume: "+nume;
	}
}
