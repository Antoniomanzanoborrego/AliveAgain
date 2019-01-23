/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aliveagain.AliveAgain;

import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
        int imagenViewWallA = 10;
        int imagenViewWallA2 = -600;
        int groupMuñecoX = 512;
        int velocidad = 0;
        float velocidadCity = 0;
        double fondoCityY = 0;
        float groupCityX = 0;
      
        int groupFantasmaX = 500;
        float groupFantasmaY = 700;
        int groupMuñecoY = 100;
    
    @Override
    public void start(Stage primaryStage) {
        Rectangle rectangleDerecha = new Rectangle (200, 600);
        Rectangle rectangleIzquierda = new Rectangle (824, 0, 200, 600);
        Image imagenBoy = new Image(getClass().getResourceAsStream("Imagen/boy.png"));
        ImageView imagenViewBoy = new ImageView(imagenBoy);
        Rectangle rectangleBoy = new Rectangle (32, 111);
        rectangleBoy.setVisible(false);
        Circle circleFantasmaD = new Circle(22, 15, 6, Color.RED);
        Circle circleFantasmaI = new Circle(5, 15, 2, Color.RED);
        Circle circleFantasma = new Circle(15, 15, 15, Color.DARKGRAY);
        Rectangle rectangleFantasma = new Rectangle (0,15,30,15);
        rectangleFantasma.setFill(Color.DARKGRAY);
        Polygon polygon1Fantasma = new Polygon (new double[]{
            0, 30,
            0, 35,
            5, 30
        });
        Polygon polygon2Fantasma = new Polygon (new double[]{
            5, 30,
            7, 37,
            9, 30
        });
        Polygon polygon3Fantasma = new Polygon (new double[]{
            9, 30,
            13, 36,
            15, 30
        });
        Polygon polygon4Fantasma = new Polygon (new double[]{
            15, 30,
            18, 35,
            22, 30
        });
        Polygon polygon5Fantasma = new Polygon (new double[]{
            22, 30,
            25, 39,
            27, 30
        });
        Polygon polygon6Fantasma = new Polygon (new double[]{
            27, 30,
            30, 36,
            30, 30
        });
        polygon1Fantasma.setFill(Color.DARKGRAY);
        polygon2Fantasma.setFill(Color.DARKGRAY);
        polygon3Fantasma.setFill(Color.DARKGRAY);
        polygon4Fantasma.setFill(Color.DARKGRAY);
        polygon5Fantasma.setFill(Color.DARKGRAY);
        polygon6Fantasma.setFill(Color.DARKGRAY);
        Group groupFantasma = new Group ();
            groupFantasma.getChildren().addAll( rectangleFantasma, circleFantasma, circleFantasmaD, circleFantasmaI, polygon1Fantasma, polygon2Fantasma, polygon3Fantasma, polygon4Fantasma, polygon5Fantasma, polygon6Fantasma);
        
        Group groupMuñeco = new Group ();
            groupMuñeco.getChildren().addAll(rectangleBoy, imagenViewBoy);
         
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
        imagenViewWallA_izquierda.setX(0);
        imagenViewWallA_izquierda.setY(0);
        imagenViewWallA_derecha.setX(824);
        imagenViewWallA_derecha.setY(0);
        imagenViewWallA_izquierda2.setX(0);
        imagenViewWallA_izquierda2.setY(0);
        imagenViewWallA_derecha2.setX(824);
        imagenViewWallA_derecha2.setY(0);
        groupFantasma.setLayoutX(groupFantasmaX);
        groupFantasma.setLayoutY(groupFantasmaY);
        groupMuñeco.setLayoutX(groupMuñecoX);
        groupMuñeco.setLayoutY(groupMuñecoY);
        Random randomEnemigosFantasma = new Random();
        groupFantasmaX = randomEnemigosFantasma.nextInt(594) + 200;
        groupFantasma.setLayoutX(groupFantasmaX);
        System.out.println(groupFantasmaX);
        
        AnimationTimer animationFantasma = new AnimationTimer (){
        
            @Override
            public void handle (long now) {
                groupFantasmaY--;
                groupFantasma.setLayoutY(groupFantasmaY);
            };
        };
        
        AnimationTimer animationCity = new AnimationTimer (){
            
            @Override
            @SuppressWarnings("empty-statement")
            public void handle (long now) {
                if (fondoCityY>-1440){
                    imagenViewCity.setY(fondoCityY);
                    fondoCityY = fondoCityY - 0.5;
                };
            };
        };
        
        AnimationTimer animationMuñeco = new AnimationTimer (){
            
            @Override
            public void handle (long now) {
                groupMuñecoX = velocidad + groupMuñecoX;
                groupMuñeco.setLayoutX(groupMuñecoX);
                velocidadCity = velocidad/3;      
                groupCityX = velocidadCity + groupCityX;             
                imagenViewCity.setX(groupCityX);
            };
        };
        
        AnimationTimer animationWall = new AnimationTimer (){
            
            @Override
            public void handle (long now) {
                if (imagenViewWallA_izquierda.getY()<-599){
                    imagenViewWallA = 610;
                    imagenViewWallA_izquierda.setY(imagenViewWallA);
                    imagenViewWallA_derecha.setY(imagenViewWallA);                    
                }
                else {
                    imagenViewWallA = imagenViewWallA - 5;
                    imagenViewWallA_izquierda.setY(imagenViewWallA);
                    imagenViewWallA_derecha.setY(imagenViewWallA);
                };
                if (imagenViewWallA_izquierda2.getY()<-599){
                    imagenViewWallA2 = 610;
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
        
        Text derrota = new Text ("Has perdido");
        derrota.setFont(Font.font(150));
        derrota.setX(50);
        derrota.setY(200);
        derrota.setFill(Color.ORANGE);
        
        
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
            root.getChildren().addAll(imagenViewCity, imagenViewWallA_derecha, imagenViewWallA_izquierda, imagenViewWallA_derecha2, imagenViewWallA_izquierda2, groupMuñeco, groupFantasma);
//          root.getChildren().add(groupMuñeco);
        AnimationTimer animationChoque = new AnimationTimer (){
            
            @Override
            public void handle (long now) {                
                Shape shapeCollisionA = Shape.intersect(rectangleBoy, rectangleDerecha);
                boolean colisionShapeA = shapeCollisionA.getBoundsInLocal().isEmpty();
                if (colisionShapeA == false){
                   root.getChildren().add(derrota);
                   this.stop();
                };
                          
                Shape shapeCollisionB = Shape.intersect(rectangleBoy, rectangleIzquierda);
                boolean colisionShapeB = shapeCollisionB.getBoundsInLocal().isEmpty();
                if (colisionShapeB == false){
                    root.getChildren().add(derrota);
                    this.stop();
                };
                
            };
        };
//      animationMuñeco.start();
        animationChoque.start();
        animationWall.start();
        animationCity.start();
        animationMuñeco.start();
        animationFantasma.start();
        Scene scene = new Scene(root, 1024, 600); 
        scene.setOnKeyReleased((KeyEvent teclasoltada) -> {
            velocidad = 0;            
        });
        scene.setOnKeyPressed((KeyEvent teclapulsada) -> {
            
            switch(teclapulsada.getCode()) {
                
                case LEFT:
                        velocidad = -5;
                    break;
                case RIGHT:
                        velocidad = 5;                    
                    break;
            }
        });
        
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
    
    public void reinicio (){
        imagenViewWallA = 10;
        imagenViewWallA2 = -600;
        groupMuñecoX = 512;
        velocidad = 0;
        velocidadCity = 0;
        fondoCityY = 0;
        groupCityX = 0;
    }
    
}