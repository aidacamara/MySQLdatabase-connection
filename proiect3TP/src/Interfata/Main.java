package Interfata;

import java.io.FileInputStream;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		String fxmlDocPath = "C:/Users/Aida/Desktop/fxmlfiles/bazadedate.fxml";
		FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);
		AnchorPane root = (AnchorPane) loader.load(fxmlStream);
		
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("proiect");
		
		
		stage.show();
	
		
	}
}
