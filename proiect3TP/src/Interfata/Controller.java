package Interfata;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * 
 * @author Aida
 * 
 * Aceasta clasa contine metodele care fac cele trei ferestre corespunzatoare fiecarui tip de operatii:
 * cu produse, clienzi si comenzi
 *
 */

public class Controller {


	@FXML
	private Button btnClienti;
	
	@FXML
	private Button btnProduse;
	
	@FXML
	private Button btnComenzi;
	
	public Controller() {

	}

	@FXML
	private void initialize() {
		
	}
	@FXML
	public void clientiNewWindow(){
		FXMLLoader loader = new FXMLLoader();
		Stage stage = new Stage();

		String fxmlDocPath = "C:/Users/Aida/Desktop/fxmlfiles/clientiOperatii.fxml";
		FileInputStream fxmlStream;
		try {
			fxmlStream = new FileInputStream(fxmlDocPath);
			AnchorPane root = (AnchorPane) loader.load(fxmlStream);
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("proiect2");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		
		stage.show();
	}
	
	@FXML
	public void produseNewWindow(){
		FXMLLoader loader = new FXMLLoader();
		Stage stage = new Stage();

		String fxmlDocPath = "C:/Users/Aida/Desktop/fxmlfiles/produseOperatii.fxml";
		FileInputStream fxmlStream;
		try {
			fxmlStream = new FileInputStream(fxmlDocPath);
			AnchorPane root = (AnchorPane) loader.load(fxmlStream);
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("proiect2");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		
		stage.show();
	}
	
	@FXML
	public void comenziNewWindow(){
		FXMLLoader loader = new FXMLLoader();
		Stage stage = new Stage();

		String fxmlDocPath = "C:/Users/Aida/Desktop/fxmlfiles/comeziOperatii.fxml";
		FileInputStream fxmlStream;
		try {
			fxmlStream = new FileInputStream(fxmlDocPath);
			AnchorPane root = (AnchorPane) loader.load(fxmlStream);
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("proiect2");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		
		stage.show();
	}

}
