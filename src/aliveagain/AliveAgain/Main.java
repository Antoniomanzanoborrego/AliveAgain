/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aliveagain.AliveAgain;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author Antonio Manzano Borrego
 */
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Circle cabeza = new Circle(150,125,25);        
        Circle ojoIzquierdo = new Circle(137.5,112.5,3,Color.WHITE);
        Circle ojoDerecho = new Circle(162.5,112.5,3,Color.WHITE);
        Rectangle boca = new Rectangle(137.5,131.75,25,5);
        boca.setFill(Color.WHITE);
        
        Pane root = new Pane();
        root.getChildren().add(cabeza);
        root.getChildren().add(ojoIzquierdo);
        root.getChildren().add(ojoDerecho);
        root.getChildren().add(boca);
        
        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("Alive Again");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
