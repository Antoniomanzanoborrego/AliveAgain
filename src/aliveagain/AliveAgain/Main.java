/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aliveagain.AliveAgain;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author Antonio Manzano Borrego
 */
public class Main extends Application {
    
    // Variables usadas en el movimiento y rebote de la cabeza
    int movimientoMuñecoX = 0;
    int movimientoMuñecoY = 0;
    int cambioEjeX = 0;
    int cambioEjeY = 0;
        
    @Override
    public void start(Stage primaryStage) {
        Circle circleCabeza = new Circle(150,125,25);        
        Circle circleOjoIzquierdo = new Circle(137.5,112.5,3,Color.RED);
        Circle circleOjoDerecho = new Circle(162.5,112.5,3,Color.RED);
        Rectangle rectangleBoca = new Rectangle(137.5,131.75,25,5);
        Arc arcPañuelo = new Arc(150, 125, 25, 25, 180, 180);
        arcPañuelo.setFill(Color.BLUEVIOLET);
        
        Group groupMuñeco = new Group ();
            groupMuñeco.getChildren().addAll(circleCabeza, circleOjoIzquierdo, circleOjoDerecho, arcPañuelo);
        
        AnimationTimer animationMuñeco = new AnimationTimer (){
            
            @Override
            public void handle (long now) {                
                if (cambioEjeX == 0) {
                    groupMuñeco.setLayoutX(movimientoMuñecoX);
                    movimientoMuñecoX++;
                    if (movimientoMuñecoX > 300) {
                        cambioEjeX = 1;
                    };
                }
                else {
                    groupMuñeco.setLayoutX(movimientoMuñecoX);
                    movimientoMuñecoX--;
                    if (movimientoMuñecoX < 1) {
                        cambioEjeX = 0;
                    };                        
                };
                if (cambioEjeY == 0) {
                    groupMuñeco.setLayoutY(movimientoMuñecoY);
                    movimientoMuñecoY++;
                    if (movimientoMuñecoY > 250) {
                        cambioEjeY = 1;
                    };
                }
                else {
                    groupMuñeco.setLayoutY(movimientoMuñecoY);
                    movimientoMuñecoY--;
                    if (movimientoMuñecoY < 1) {
                        cambioEjeY = 0;
                    };                        
                };
            };
        };
            
        Pane root = new Pane();
            root.getChildren().add(groupMuñeco);
        
        animationMuñeco.start();
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
