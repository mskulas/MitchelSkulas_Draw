// run cmd:
// C:\Users\angry\OneDrive\Documents\MitchelSkulas_Draw\.maven\apache-maven-3.9.6\bin\mvn.cmd -DskipTests javafx:run

package main.java;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.event.EventHandler;


public class DrawApp extends Application {
	private Stage primaryStage;
	private Canvas canvas;
    // single ToggleGroup for shape buttons so only one can be selected
    private ToggleGroup shapeButtons;

	@Override
	public void start(Stage primaryStage) {
		//set up stage
        this.primaryStage = primaryStage;
		primaryStage.setTitle("DrawApp");
		Pane root = new Pane();
		canvas = new Canvas(800, 600);
		root.getChildren().add(canvas);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();

        //add radio buttons for shape selection
        Label shapeLabel = new Label("Select Shape:");
        shapeLabel.setLayoutX(10);
        shapeLabel.setLayoutY(0);
    // initialize the single ToggleGroup instance
    shapeButtons = new ToggleGroup();
        RadioButton circleButton = new RadioButton("Circle");
        circleButton.setToggleGroup(shapeButtons);
        circleButton.setLayoutX(10);
        circleButton.setLayoutY(20);
        RadioButton squareButton = new RadioButton("Square");
        squareButton.setLayoutX(10);
        squareButton.setLayoutY(40);
        squareButton.setToggleGroup(shapeButtons);
        root.getChildren().add(shapeLabel);
        root.getChildren().add(circleButton);
        root.getChildren().add(squareButton);
    // select a default
    shapeButtons.selectToggle(circleButton);
        //make listener
        shapeButtons.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                RadioButton selectedButton = (RadioButton) newValue;
                System.out.println("Selected shape: " + selectedButton.getText());
            }
        });
	}
	public static void main(String[] args) {
		launch(args);
	}
}

