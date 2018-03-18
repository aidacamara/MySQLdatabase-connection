package Model;

import javafx.beans.property.SimpleStringProperty;
/**
 * Tabelul Produs din baza de date
 * @author Aida
 *
 */
public class Produs {

	private int id;
	private String nume;
	private double pret;
	private int cantitate;
	
	public Produs(){
		id=-1;
		nume="gol";
		pret=-1;
		cantitate=-1;
	}
	
	public Produs(int idProdus, String nume, double pret, int cantitate) {
		this.id = idProdus;
		this.nume = nume;
		this.pret = pret;
		this.cantitate = cantitate;
	}
	/**
	 * Metoda necesara pentru crearea unui TableView la afisarea tutror datelor din tabel
	 * @return un obiect de tipul SimpleStringProperty care contine id-ul produsului
	 */
	public SimpleStringProperty getIdP(){
		SimpleStringProperty idP=new SimpleStringProperty(Integer.toString(id));
		return idP;
	}
	/**
	 * Metoda necesara pentru crearea unui TableView la afisarea tutror datelor din tabel
	 * @return un obiect de tipul SimpleStringProperty care contine numele produsului
	 */
	public SimpleStringProperty getNumeP(){
		SimpleStringProperty numeP=new SimpleStringProperty(nume);
		return numeP;
	}
	/**
	 * Metoda necesara pentru crearea unui TableView la afisarea tuturor datelor din tabel
	 * @return un obiect de tipul SimpleStringProperty care contine pretul produsului
	 */
	public SimpleStringProperty getPretP(){
		SimpleStringProperty pretP=new SimpleStringProperty(Double.toString(pret));
		return pretP;
	}
	/**
	 * Metoda necesara pentru crearea unui TableView la afisarea tutror datelor din tabel
	 * @return un obiect de tipul SimpleStringProperty care contine cantitatea disponibila a produsului
	 */
	public SimpleStringProperty getCantitateP(){
		SimpleStringProperty cantitateP=new SimpleStringProperty(Integer.toString(cantitate));
		return cantitateP;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int idProdus) {
		this.id = idProdus;
	}
	public String getNume() {
		return nume;
	}
	public void setNume(String nume) {
		this.nume = nume;
	}
	public double getPret() {
		return pret;
	}
	public void setPret(double pret) {
		this.pret = pret;
	}
	public int getCantitate() {
		return cantitate;
	}
	public void setCantitate(int cantitate) {
		this.cantitate = cantitate;
	}
	
	public String toString(){
		return "Produsul: "+id+"\nNume: "+nume+"\nCantitate: "+cantitate+"\nPret: "+pret+"\n";
	}
}
