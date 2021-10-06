package dad.layouts;


import java.time.LocalDate;
import java.time.Period;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class GridPaneSample extends Application{
	//model
	private ObjectProperty<LocalDate> fechaNac= new SimpleObjectProperty();
	private IntegerProperty edad=new SimpleIntegerProperty();
	//view
	private TextField nombreText;
	private TextField apellidosText;
	private TextField dniText;
	private DatePicker fechaNacPicker;
	private TextField [] ibanText;
	private Label edadLabel;
	private Label descripcionLabel;
	private RadioButton hombreRadio, mujerRadio;
	private TextArea descripcionText;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		nombreText =new TextField();
		nombreText.setPromptText("Nombre del alumno");
		
		apellidosText=new TextField();
		apellidosText.setPromptText("Apellidos del alumno");
		
		dniText=new TextField();
		dniText.setPromptText("DNI del alumno");
		
		ibanText=new TextField [6];
		for(int i=0; i<ibanText.length; i++) {
			ibanText[i]=new TextField();
			ibanText[i].setPrefColumnCount(4);;
		}
		
		edadLabel=new Label("xxx");
		descripcionLabel=new Label("Descripción");
		
		hombreRadio=new RadioButton("Hombre");
		mujerRadio=new RadioButton("Mujer");
		
		descripcionText=new TextArea();
		
		fechaNacPicker= new DatePicker();
		
		ToggleGroup sexoGroup=new ToggleGroup();
		sexoGroup.getToggles().addAll(hombreRadio, mujerRadio);
		sexoGroup.selectedToggleProperty().addListener((o, ov, nv)->{
			RadioButton seleccionado= (RadioButton) nv;
			System.out.print(seleccionado.getText());
		});;
		
		
		GridPane formPane=new GridPane();
		formPane.setGridLinesVisible(true);
		formPane.setPadding(new Insets(5));
		formPane.setHgap(5);
		formPane.setVgap(5);
		formPane.addRow(0, new Label("Nombre:"), nombreText);
		formPane.addRow(1, new Label("Apellidos:"), apellidosText);
		formPane.addRow(2, new Label("Dni:"), dniText);
		formPane.addRow(3, new Label("Fecha de nacimiento:"), fechaNacPicker);
		formPane.addRow(4, new Label("IBAN:"), new HBox(5, ibanText));
		formPane.addRow(5, new Label("Sexo:"), new HBox(5, hombreRadio, mujerRadio));
		formPane.addRow(6, descripcionLabel, descripcionText);
		formPane.add(edadLabel, 2, 3);
		
		ColumnConstraints [] cols= {
			new ColumnConstraints(),
			new ColumnConstraints()					
		};
		formPane.getColumnConstraints().setAll(cols);
		
		RowConstraints [] rows= {
			new RowConstraints(),
			new RowConstraints(),
			new RowConstraints(),
			new RowConstraints(),
			new RowConstraints(),
			new RowConstraints(),
			new RowConstraints()
		};
		
		//restricciones de la columna 0
		cols[0].setHalignment(HPos.RIGHT);
		//restricciones de la columna 1
		cols[1].setHgrow(Priority.ALWAYS);
		
		//
		rows[6].setVgrow(Priority.ALWAYS);
		//restricciones de la celda dni
		GridPane.setFillWidth(dniText, false);
		GridPane.setColumnSpan(nombreText, 2);		
		GridPane.setColumnSpan(apellidosText, 2);				
		GridPane.setColumnSpan(descripcionText, 2);	
		GridPane.setValignment(descripcionLabel, VPos.TOP);
		
		CheckBox gridLinesCheck=new CheckBox("Mostrar GridLines");
		formPane.gridLinesVisibleProperty().bind(gridLinesCheck.selectedProperty());
		
		BorderPane root=new BorderPane(formPane);
		root.setPadding(new Insets(5));
		root.setCenter(formPane);
		root.setBottom(new HBox(gridLinesCheck));
		
		Scene scene=new Scene(root, 640,480);
		
		primaryStage.setTitle("GridPaneSample");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		//Bindeos
		fechaNac.bind(fechaNacPicker.valueProperty());
		fechaNac.addListener((o,ov,nv)-> OnFechaNacChanged(o,ov,nv));
		
	}
	
	private void OnFechaNacChanged(ObservableValue<? extends LocalDate> o, LocalDate ov, LocalDate nv) {
		edad.set(Period.between(nv, LocalDate.now()).getYears());
	}

	public static void main(String[] args) {
	launch(args);
	}

}
