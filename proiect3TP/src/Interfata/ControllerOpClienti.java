package Interfata;

import java.util.ArrayList;
import java.util.List;

import Model.Client;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
/**
 * 
 * @author Aida
 *
 * Contine cate o metoda pentru fiecare operatie posibila pe tabelul cu clienti
 * Metodele sunt apelate la apasarea butonului din interfata corespunzator operatiei dorite 
 */
public class  ControllerOpClienti{

	@FXML
	private TextField idClient;
	
	@FXML
	private TextField numeClient;
	
	@FXML
	private Button findId;
	
	@FXML
	private Button deleteId;
	
	@FXML
	private Button updateId;
	
	@FXML
	private Button findAll;
	
	@FXML
	private Button insert;
	
	public ControllerOpClienti() {

	}

	@FXML
	private void initialize() {
		
	}
	
	
	
	public void findById(){
		String idString=idClient.getText();
		int id=Integer.parseInt(idString);
		
		
		Stage stage = new Stage();
		Group root=new Group();
		Scene scene = new Scene(root);
        stage.setTitle("Client");
        stage.setWidth(300);
        stage.setHeight(500);
 
        TextArea clientiTextArea=new TextArea();
        GridPane panel=new GridPane();
        panel.add(clientiTextArea,0,10);
        Client client=Service.findByIdClient(id);
       
			clientiTextArea.setText(client.toString()+"\n");
		
       
        root.getChildren().add(panel);
        stage.setScene(scene);
        stage.show();
		
	}
	
	
	public void findAll(){
		Stage stage = new Stage();
		Group root=new Group();
		Scene scene = new Scene(root);
        stage.setTitle("Clienti");
        stage.setWidth(300);
        stage.setHeight(500);
 
        //TextArea clientiTextArea=new TextArea();
        GridPane panel=new GridPane();
        //panel.add(clientiTextArea,0,10);
        List<Client> clienti=Service.findAllClient();
        List<Client> clientiAL=new ArrayList<Client>();
        for(Client client: clienti){
			//clientiTextArea.appendText(client.toString()+"\n");
			clientiAL.add(client);
		}
        
         
        
        ObservableList<Client> data = FXCollections.observableList(clientiAL);
        TableView<Client> table = new TableView<Client>();
        table.setItems(data);
     
        TableColumn<Client,String> numeCol=new TableColumn<Client,String>("Nume");
        TableColumn<Client,String> idCol=new TableColumn<Client,String>("Id");
       
        
        
        numeCol.setCellValueFactory(new Callback<CellDataFeatures<Client, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<Client, String> p) {
                // p.getValue() returns the Person instance for a particular TableView row
                return p.getValue().getNumeP();
            }
         });
        
        idCol.setCellValueFactory(new Callback<CellDataFeatures<Client, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<Client, String> p) {
                // p.getValue() returns the Person instance for a particular TableView row
                return p.getValue().getIdP();
            }
         });

	table.getColumns().addAll(idCol,numeCol);

	root.getChildren().add(panel);
        root.getChildren().addAll(table);
        
        
        stage.setScene(scene);
        stage.show();
		
	}
	
	public void deleteById(){
		String idString=idClient.getText();
		int id=Integer.parseInt(idString);
		Service.deleteByIdClient(id);
	}
	
	public void insertNew(){
		String idString=idClient.getText();
		String nume=numeClient.getText();
		int id=Integer.parseInt(idString);
		Service.insertClient(id, nume);
	}
	
	public void update(){
		String idString=idClient.getText();
		String nume=numeClient.getText();
		int id=Integer.parseInt(idString);
		Service.updateClient(id, nume);
	}
	
}
