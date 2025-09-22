// run cmd:
// C:\Users\angry\OneDrive\Documents\MitchelSkulas_Draw\.maven\apache-maven-3.9.6\bin\mvn.cmd -DskipTests javafx:run

package main.java;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
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
    private Slider sizeSlider;

	@Override
	public void start(Stage primaryStage) {
		//set up stage
    this.primaryStage = primaryStage;
    primaryStage.setTitle("DrawApp");
    StackPane root = new StackPane();
    canvas = new Canvas(800, 600);
    // canvas is the bottom layer
    root.getChildren().add(canvas);
    Scene scene = new Scene(root, 800, 600);
		primaryStage.setScene(scene);
		primaryStage.show();

        // Controls container (on top of the canvas)
    VBox controls = new VBox(6);
    // constrain size so VBox doesn't expand to fill the StackPane
    controls.setMaxWidth(160);
    controls.setPrefWidth(160);
    controls.setMaxHeight(140);
    controls.setPrefHeight(140);
    controls.setStyle("-fx-padding: 8; -fx-background-color: rgba(255,255,255,0.9); -fx-border-color: #333; -fx-border-width: 1; -fx-background-radius: 4;");
    controls.setSpacing(6);
        //add radio buttons for shape selection
        Label shapeLabel = new Label("Select Shape:");
        // initialize the single ToggleGroup instance
        shapeButtons = new ToggleGroup();
        RadioButton circleButton = new RadioButton("Circle");
        circleButton.setToggleGroup(shapeButtons);
        RadioButton squareButton = new RadioButton("Square");
        squareButton.setToggleGroup(shapeButtons);
        // select a default
        shapeButtons.selectToggle(circleButton);
        //make listener
        shapeButtons.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                RadioButton selectedButton = (RadioButton) newValue;
                System.out.println("Selected shape: " + selectedButton.getText());
            }
        });

        //create size slider (use the field)
        Label sizeLabel = new Label("Size:");
        this.sizeSlider = new Slider(1, 100, 10);
        this.sizeSlider.setPrefWidth(120);
        this.sizeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Selected size: " + newValue.intValue());
        });

    controls.getChildren().addAll(shapeLabel, circleButton, squareButton, sizeLabel, this.sizeSlider);
    // add controls on top of the canvas and align to top-left
    // Wrap controls in an AnchorPane so they don't grow with the StackPane
    javafx.scene.layout.AnchorPane anchor = new javafx.scene.layout.AnchorPane();
    anchor.setPickOnBounds(false);
    anchor.getChildren().add(controls);
    javafx.scene.layout.AnchorPane.setTopAnchor(controls, 8.0);
    javafx.scene.layout.AnchorPane.setLeftAnchor(controls, 8.0);
    root.getChildren().add(anchor);
    StackPane.setAlignment(anchor, Pos.TOP_LEFT);
    controls.toFront();
	}
	public static void main(String[] args) {
		launch(args);
	}
}

