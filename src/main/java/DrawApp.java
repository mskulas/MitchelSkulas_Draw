// run cmd:
// C:\Users\angry\OneDrive\Documents\MitchelSkulas_Draw\.maven\apache-maven-3.9.6\bin\mvn.cmd -DskipTests javafx:run

package main.java;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.event.EventHandler;


public class DrawApp extends Application {
	private Stage primaryStage;
	private Canvas canvas;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		primaryStage.setTitle("DrawApp");
		Pane root = new Pane();
		canvas = new Canvas(800, 600);
		root.getChildren().add(canvas);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		System.out.println("DrawApp started");
	}
	public static void main(String[] args) {
		launch(args);
	}
}

