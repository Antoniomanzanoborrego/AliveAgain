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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 *
 * @author Antonio Manzano Borrego
 */
public class Main extends Application {
    
    // Variables usadas en el movimiento y rebote de la cabeza
//    int movimientoMuñecoX = 0;
//    int movimientoMuñecoY = 0;
//    int cambioEjeX = 0;
//    int cambioEjeY = 0;
      double fondoCityY = 0;
      int imagenViewWallA = 0;
      int imagenViewWallA2 = -610;
    
    @Override
    public void start(Stage primaryStage) {
        Circle circleCabeza = new Circle(150,125,25);        
        Circle circleOjoIzquierdo = new Circle(137.5,112.5,3,Color.RED);
        Circle circleOjoDerecho = new Circle(162.5,112.5,3,Color.RED);
        Arc arcPañuelo = new Arc(150, 125, 25, 25, 180, 180);
        arcPañuelo.setFill(Color.BLUEVIOLET);
        
        Group groupMuñeco = new Group ();
            groupMuñeco.getChildren().addAll(circleCabeza, circleOjoIzquierdo, circleOjoDerecho, arcPañuelo);
        
        Image imagenWallA = new Image(getClass().getResourceAsStream("Imagen/wall_A.jpg"));
        // Image imagenWallB = new Image(getClass().getResourceAsStream("Imagen/wall_B.jpg"));
        Image imagenCity = new Image(getClass().getResourceAsStream("Imagen/city.jpg"));
        ImageView imagenViewWallA_derecha = new ImageView(imagenWallA);
        ImageView imagenViewWallA_izquierda = new ImageView(imagenWallA);
        ImageView imagenViewWallA_derecha2 = new ImageView(imagenWallA);
        ImageView imagenViewWallA_izquierda2 = new ImageView(imagenWallA);
        ImageView imagenViewCity = new ImageView(imagenCity);
        imagenViewCity.setX(0);
        imagenViewCity.setY(0);
        imagenViewCity.setFitWidth(1024);
        imagenViewCity.setFitHeight(2400);
        imagenViewWallA_izquierda.setX(15);
        imagenViewWallA_izquierda.setY(0);
        imagenViewWallA_derecha.setX(800);
        imagenViewWallA_derecha.setY(0);
        imagenViewWallA_izquierda2.setX(0);
        imagenViewWallA_izquierda2.setY(0);
        imagenViewWallA_derecha2.setX(824);
        imagenViewWallA_derecha2.setY(0);
                
        AnimationTimer animationCity = new AnimationTimer (){
            
            @Override
            public void handle (long now) {
                if (fondoCityY>-1440){
                    imagenViewCity.setY(fondoCityY);
                    fondoCityY = fondoCityY - 0.2;
                };
            };
        };
                
        AnimationTimer animationWall = new AnimationTimer (){
            
            @Override
            public void handle (long now) {
                if (imagenViewWallA_izquierda.getY()<-599){
                    imagenViewWallA = 600;
                    imagenViewWallA_izquierda.setY(imagenViewWallA);
                    imagenViewWallA_derecha.setY(imagenViewWallA);                    
                }
                else {
                    imagenViewWallA = imagenViewWallA - 5;
                    imagenViewWallA_izquierda.setY(imagenViewWallA);
                    imagenViewWallA_derecha.setY(imagenViewWallA);
                };
                if (imagenViewWallA_izquierda2.getY()<-599){
                    imagenViewWallA2 = 600;
                    imagenViewWallA_izquierda2.setY(imagenViewWallA2);
                    imagenViewWallA_derecha2.setY(imagenViewWallA2); 
                }
                else {
                    imagenViewWallA2 = imagenViewWallA2 - 5;
                    imagenViewWallA_izquierda2.setY(imagenViewWallA2);
                    imagenViewWallA_derecha2.setY(imagenViewWallA2);
                };
            };
        };
//      AnimationTimer animationMuñeco = new AnimationTimer (){
//            
//            @Override
//            public void handle (long now) {                
//                if (cambioEjeX == 0) {
//                    groupMuñeco.setLayoutX(movimientoMuñecoX);
//                    movimientoMuñecoX++;
//                    if (movimientoMuñecoX > 300) {
//                        cambioEjeX = 1;
//                    };
//                }
//                else {
//                    groupMuñeco.setLayoutX(movimientoMuñecoX);
//                    movimientoMuñecoX--;
//                    if (movimientoMuñecoX < 1) {
//                        cambioEjeX = 0;
//                    };                        
//                };
//                if (cambioEjeY == 0) {
//                    groupMuñeco.setLayoutY(movimientoMuñecoY);
//                    movimientoMuñecoY++;
//                    if (movimientoMuñecoY > 250) {
//                        cambioEjeY = 1;
//                    };
//                }
//                else {
//                    groupMuñeco.setLayoutY(movimientoMuñecoY);
//                    movimientoMuñecoY--;
//                    if (movimientoMuñecoY < 1) {
//                        cambioEjeY = 0;
//                    };                        
//                };
//            };
//        };
            
        Pane root = new Pane();
            root.getChildren().addAll(imagenViewCity, imagenViewWallA_derecha, imagenViewWallA_izquierda, imagenViewWallA_derecha2, imagenViewWallA_izquierda2);
//          root.getChildren().add(groupMuñeco);
        
//      animationMuñeco.start();
        animationWall.start();
        animationCity.start();
        Scene scene = new Scene(root, 1024, 600);
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