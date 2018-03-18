package Model;

import javafx.beans.property.SimpleStringProperty;
/**
 * Tabelul Comanda din baza de date
 * @author Aida
 *
 */
public class Comanda {

	private int id;
	private int idClient;
	private int idProdus;
	private int cantitate;
	
	public Comanda(){
		id=-1;
		idClient=-1;
		idProdus=-1;
		cantitate=-1;
	}
	public Comanda(int idOrders, int idClient, int idProdus, int cantitatate) {
		this.id = idOrders;
		this.idClient = idClient;
		this.idProdus = idProdus;
		this.cantitate = cantitatate;
	}
	/**
	 * Metoda necesara pentru crearea unui TableView la afisarea tutror datelor din tabel
	 * @return un obiect de tipul SimpleStringProperty care contine id-ul comenzii
	 */
	public SimpleStringProperty getIdP(){
		SimpleStringProperty idP=new SimpleStringProperty(Integer.toString(id));
		return idP;
	}
	/**
	 * Metoda necesara pentru crearea unui TableView la afisarea tutror datelor din tabel
	 * @return un obiect de tipul SimpleStringProperty care contine id-ul clientului care a facut comanda
	 */
	public SimpleStringProperty getIdClientP(){
		SimpleStringProperty idP=new SimpleStringProperty(Integer.toString(idClient));
		return idP;
	}
	/**
	 * Metoda necesara pentru crearea unui TableView la afisarea tutror datelor din tabel
	 * @return un obiect de tipul SimpleStringProperty care contine id-ul produsului comandat
	 */
	public SimpleStringProperty getIdProdusP(){
		SimpleStringProperty idP=new SimpleStringProperty(Integer.toString(idProdus));
		return idP;
	}
	/**
	 * Metoda necesara pentru crearea unui TableView la afisarea tutror datelor din tabel
	 * @return un obiect de tipul SimpleStringProperty care contine cantitatea produsului comandat
	 */
	public SimpleStringProperty getCantitateP(){
		SimpleStringProperty cantitateP=new SimpleStringProperty(Integer.toString(cantitate));
		return cantitateP;
	}
	public int getId() {
		return id;
	}
	public void setId(int idOrders) {
		this.id = idOrders;
	}
	public int getIdClient() {
		return idClient;
	}
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	public int getIdProdus() {
		return idProdus;
	}
	public void setIdProdus(int idProdus) {
		this.idProdus = idProdus;
	}
	public int getCantitate() {
		return cantitate;
	}
	public void setCantitate(int cantitatate) {
		this.cantitate = cantitatate;
	}
	
	public String toString(){
		return "Comanda: "+id+"\nID client: "+idClient+"\nID produs: "+idProdus+"\ncantitate: "+cantitate+"\n";
	}
	
}
