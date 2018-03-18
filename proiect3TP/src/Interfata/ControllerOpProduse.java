package Interfata;

import java.util.ArrayList;
import java.util.List;

import Model.Client;
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
 * Contine cate o metoda pentru fiecare operatie posibila pe tabelul cu produse
 * Metodele sunt apelate la apasarea butonului din interfata corespunzator operatiei dorite 
 */
public class ControllerOpProduse {


	@FXML
	private TextField idProdus;
	
	@FXML
	private TextField numeProdus;
	
	@FXML
	private TextField cantitate;
	
	@FXML
	private TextField pret;
	
	
	public ControllerOpProduse() {

	}

	@FXML
	private void initialize() {
		
	}
	
	public void findById(){
		String idString=idProdus.getText();
		Produs produs=Service.findByIdProdus(idString);
		
		Stage stage = new Stage();
		Group root=new Group();
		Scene scene = new Scene(root);
        stage.setTitle("Produs");
        stage.setWidth(300);
        stage.setHeight(500);
 
        TextArea produsTextArea=new TextArea();
        GridPane panel=new GridPane();
        panel.add(produsTextArea,0,10);
        
		produsTextArea.setText(produs.toString()+"\n");
		
        root.getChildren().add(panel);
        stage.setScene(scene);
        stage.show();
	}
	
	public void findAll(){

		Stage stage = new Stage();
		Group root=new Group();
		Scene scene = new Scene(root);
        stage.setTitle("Produse");
        stage.setWidth(300);
        stage.setHeight(500);
 
        //TextArea produseTextArea=new TextArea();
        GridPane panel=new GridPane();
        //panel.add(produseTextArea,0,10);
        List<Produs> result=Service.findAllProdus();
        List<Produs> produseAL=new ArrayList<Produs>();
        for(Produs produs: result){
			//produseTextArea.appendText(produs.toString()+"\n");
        	produseAL.add(produs);
		}
       
        ObservableList<Produs> data = FXCollections.observableList(produseAL);
        TableView<Produs> table = new TableView<Produs>();
        table.setItems(data);
        
        TableColumn<Produs,String> numeCol=new TableColumn<Produs,String>("Nume");
        TableColumn<Produs,String> idCol=new TableColumn<Produs,String>("Id");
        TableColumn<Produs,String> pretCol=new TableColumn<Produs,String>("Pret");
        TableColumn<Produs,String> cantitateCol=new TableColumn<Produs,String>("Cantitate");
        
        numeCol.setCellValueFactory(new Callback<CellDataFeatures<Produs, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<Produs, String> p) {
                // p.getValue() returns the Person instance for a particular TableView row
                return p.getValue().getNumeP();
            }
         });
        
        idCol.setCellValueFactory(new Callback<CellDataFeatures<Produs, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<Produs, String> p) {
                // p.getValue() returns the Person instance for a particular TableView row
                return p.getValue().getIdP();
            }
         });
        
        pretCol.setCellValueFactory(new Callback<CellDataFeatures<Produs, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<Produs, String> p) {
                // p.getValue() returns the Person instance for a particular TableView row
                return p.getValue().getPretP();
            }
         });
        
        cantitateCol.setCellValueFactory(new Callback<CellDataFeatures<Produs, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<Produs, String> p) {
                // p.getValue() returns the Person instance for a particular TableView row
                return p.getValue().getCantitateP();
            }
         });
        
        table.getColumns().addAll(idCol,numeCol,pretCol,cantitateCol);

    	root.getChildren().add(panel);
        root.getChildren().addAll(table);
        stage.setScene(scene);
        stage.show();
		
	}
	
	public void deleteByIdProdus(){
		String idString=idProdus.getText();
		Service.deleteByIdProdus(idString);
	}
	
	public void insertNew(){
		String idString=idProdus.getText();
		String nume=numeProdus.getText();
		String cantitateString=cantitate.getText();
		String pretString=pret.getText();
		
		Service.insertProdus(idString,nume,cantitateString,pretString);
	}
	
	public void update(){
		String idString=idProdus.getText();
		String nume=numeProdus.getText();
		String cantitateString=cantitate.getText();
		String pretString=pret.getText();
		
		Service.updateProdus(idString,nume,cantitateString,pretString);
	}
}
