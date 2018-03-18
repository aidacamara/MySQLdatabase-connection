package Interfata;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import Model.Comanda;
import Model.Produs;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;
/**
 * 
 * @author Aida
 *
 * Contine cate o metoda pentru fiecare operatie posibila pe tabelul cu clienti
 * Metodele sunt apelate la apasarea butonului din interfata corespunzator operatiei dorite 
 */
public class ControllerOpComenzi {

	@FXML
	private TextField idComanda;
	
	@FXML
	private TextField idClient;
	
	@FXML
	private TextField idProdus;
	
	@FXML
	private TextField cantitate;

	public ControllerOpComenzi() {

	}

	@FXML
	private void initialize() {
		
	}
	
	public void findById(){
		String idString=idComanda.getText();
		Comanda comanda=Service.findByIdComanda(idString);
		
		Stage stage = new Stage();
		Group root=new Group();
		Scene scene = new Scene(root);
        stage.setTitle("Comanda");
        stage.setWidth(300);
        stage.setHeight(500);
 
        TextArea comandaTextArea=new TextArea();
        GridPane panel=new GridPane();
        panel.add(comandaTextArea,0,10);
        
		comandaTextArea.setText(comanda.toString()+"\n");
		
        root.getChildren().add(panel);
        stage.setScene(scene);
        stage.show();
	}
	
	public void findAll(){

		Stage stage = new Stage();
		Group root=new Group();
		Scene scene = new Scene(root);
        stage.setTitle("Comenzi");
        stage.setWidth(300);
        stage.setHeight(500);
 
        //TextArea comenziTextArea=new TextArea();
        GridPane panel=new GridPane();
        //panel.add(comenziTextArea,0,10);
        List<Comanda> result=Service.findAllComanda();
        List<Comanda> comenziAL=new ArrayList<Comanda>();
        
        for(Comanda comanda: result){
			//comenziTextArea.appendText(comanda.toString()+"\n");
        	comenziAL.add(comanda);
		}
        
        ObservableList<Comanda> data = FXCollections.observableList(comenziAL);
        TableView<Comanda> table = new TableView<Comanda>();
        table.setItems(data);
        
        TableColumn<Comanda,String> clientCol=new TableColumn<Comanda,String>("Client");
        TableColumn<Comanda,String> idCol=new TableColumn<Comanda,String>("Id");
        TableColumn<Comanda,String> produsCol=new TableColumn<Comanda,String>("Produs");
        TableColumn<Comanda,String> cantitateCol=new TableColumn<Comanda,String>("Cantitate");
        
        clientCol.setCellValueFactory(new Callback<CellDataFeatures<Comanda, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<Comanda, String> p) {
                // p.getValue() returns the Person instance for a particular TableView row
                return p.getValue().getIdClientP();
            }
         });
        
        idCol.setCellValueFactory(new Callback<CellDataFeatures<Comanda, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<Comanda, String> p) {
                // p.getValue() returns the Person instance for a particular TableView row
                return p.getValue().getIdP();
            }
         });
        
        produsCol.setCellValueFactory(new Callback<CellDataFeatures<Comanda, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<Comanda, String> p) {
                // p.getValue() returns the Person instance for a particular TableView row
                return p.getValue().getIdProdusP();
            }
         });
        
        cantitateCol.setCellValueFactory(new Callback<CellDataFeatures<Comanda, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<Comanda, String> p) {
                // p.getValue() returns the Person instance for a particular TableView row
                return p.getValue().getCantitateP();
            }
         });
        
        table.getColumns().addAll(idCol,clientCol,produsCol,cantitateCol);

    	root.getChildren().add(panel);
        root.getChildren().addAll(table);
        stage.setScene(scene);
        stage.show();
		
	}
	
	public void deleteById(){
		String idString=idComanda.getText();
		Service.deleteByIdComanda(idString);
	}
	
	public void insertNew(){
		String idString=idComanda.getText();
		String idClientString=idClient.getText();
		String idProdusString=idProdus.getText();
		String cantitateString=cantitate.getText();
		
		
		Service.insertComanda(idString,idClientString, idProdusString, cantitateString);
		Service.scrieFisier(idString, idClientString, idProdusString, cantitateString);
	}
	
	public void update(){
		String idString=idComanda.getText();
		String idClientString=idClient.getText();
		String idProdusString=idProdus.getText();
		String cantitateString=cantitate.getText();
		
		Service.updateComanda(idString,idClientString, idProdusString, cantitateString);
	}
}
