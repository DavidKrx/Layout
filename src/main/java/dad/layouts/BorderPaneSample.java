package dad.layouts;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BorderPaneSample extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		HBox norte =new HBox(5, new Label("Etiqueta"), new Button("Botón"));
		norte.setAlignment(Pos.BASELINE_CENTER);
		norte.setStyle("-fx-background-color: red;");
		norte.setPadding(new Insets(5));
		
		Button este= new Button("Este");
		
		Button b1=new Button("Botón 1");
		b1.setMaxWidth(Double.MAX_VALUE);
		
		Button b2=new Button("Botón 22222");
		b2.setMaxWidth(Double.MAX_VALUE);
		
		Button b3=new Button("Botón 3333333");
		b3.setMaxWidth(Double.MAX_VALUE);
		
		VBox oeste=new VBox(5);
		oeste.getChildren().addAll(b1,b2,b3);
		oeste.setPadding(new Insets(5));
		oeste.setStyle("-fx-background-color: blue;");
		//abajo
		Button b4=new Button("Botón 444");
		b4.setMaxWidth(Double.MAX_VALUE);
		
		Button b5=new Button("Botón 22222");
		b5.setMaxWidth(Double.MAX_VALUE);
		
		Button b6=new Button("Botón 3333333");
		b6.setMaxWidth(Double.MAX_VALUE);
		
		HBox sur=new HBox(5);
		sur.setAlignment(Pos.BASELINE_CENTER);
		sur.getChildren().addAll(b4,b5,b6);
		sur.setPadding(new Insets(5));
		sur.setStyle("-fx-background-color: green;");
		// centro
		
		
		BorderPane root=new BorderPane();
		root.setTop(norte);
		root.setRight(este);
		root.setLeft(oeste);
		
		BorderPane.setAlignment(este, Pos.CENTER);
		
		Scene scene =new Scene(root,640, 480);
		
	}
	public static void main(String[] args) {
		launch(args);
	}
}
