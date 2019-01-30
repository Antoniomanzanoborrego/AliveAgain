/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aliveagain.AliveAgain;

import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Antonio Manzano Borrego
 */
public class groupFantasma1X extends Application {
    
//--------------------Variables usadas al inicio del juego, situadas fuera del start porque el reinicio necesita cambiarlas-----------------
        int imagenViewWallA = 10;
        int imagenViewWallA2 = -600;
        int groupMuñecoX = 512;
        int velocidad = 0;   
        float velocidadCity = 0;
        double fondoCityY = 0;
        float groupCityX = 0;   
//        float groupFantasmaY1 = 1200;
//        float groupFantasmaY2 = 1200;
//        float groupFantasmaY3 = 1700;
//        float groupFantasmaY4 = 2200;
//        float groupFantasmaY5 = 2700;
//        float groupFantasmaY6 = 3200;
//        float groupFantasmaY7 = 3700;
//        float groupFantasmaY8 = 4200;
//        float groupFantasmaReyY = 900;
        float dificultad = 8;
        int numVidas = 2;
        int marcador;
//        int groupFantasmaX1;
//        int groupFantasmaX2;
//        int groupFantasmaX3;
//        int groupFantasmaX4;
//        int groupFantasmaX5;
//        int groupFantasmaX6;
//        int groupFantasmaX7;
//        int groupFantasmaX8;
//        int groupFantasmaReyX;
//        int groupFantasmaRebote1X;
//        int groupFantasmaRebote2X;
//        int groupFantasmaRebote3X;
        int groupFantasmaRebote1X2;
        int groupFantasmaRebote2X2;
        int groupFantasmaRebote3X2;
        int velocidadRebote1X = 3;
        int velocidadRebote2X = 3;
        int velocidadRebote3X = 3;
//        float groupFantasmaRebote1Y = 1200;
//        float groupFantasmaRebote2Y = 1600;
//        float groupFantasmaRebote3Y = 2100;
        
    @Override
    public void start(Stage primaryStage) {
        
        //-----------------Variables ajenas al reinicio del juego--------------------------------------------------------------------
        Fantasma fantasma1 = new Fantasma ();                 
        Fantasma fantasma2 = new Fantasma ();                 
        Fantasma fantasma3 = new Fantasma ();                 
        Fantasma fantasma4 = new Fantasma ();                 
        Fantasma fantasma5 = new Fantasma ();                 
        Fantasma fantasma6 = new Fantasma ();                 
        Fantasma fantasma7 = new Fantasma ();
        Fantasma fantasma8 = new Fantasma ();
        Fantasma fantasmaRey = new Fantasma ();
        Fantasma fantasmaRebote1 = new Fantasma ();
        Fantasma fantasmaRebote2 = new Fantasma ();
        Fantasma fantasmaRebote3 = new Fantasma ();
        Random randomEnemigosFantasma = new Random();    
        Rectangle rectangleVida = new Rectangle(0, 0, 200, 75);
        
        //-----------------Rectángulos para la colisión del jugador con los laterales-------------------------------------------------------------------
        Rectangle rectangleDerecha = new Rectangle (200, 600);
        Rectangle rectangleIzquierda = new Rectangle (824, 0, 200, 600);
        
        //-----------------Rectángulo del jugador y visibilidad---------------------------------------------------------------------------------
        Rectangle rectangleBoy = new Rectangle (32, 111);
        rectangleBoy.setVisible(false);
        
        //-----------------Adición de imágenes--------------------------------------------------------------------------------------
        Image imagenBoy = new Image(getClass().getResourceAsStream("Imagen/boy.png"));
        Image imagenWallA = new Image(getClass().getResourceAsStream("Imagen/wall_A.jpg"));
        Image imagenCity = new Image(getClass().getResourceAsStream("Imagen/city.jpg"));
        Image imagenVida = new Image(getClass().getResourceAsStream("Imagen/vida.jpg"));
        
        //-----------------Creación de ImageViews------------------------------------------------------------------------------------
        ImageView imagenViewBoy = new ImageView(imagenBoy); 
        ImageView imagenViewWallA_derecha = new ImageView(imagenWallA);
        ImageView imagenViewWallA_izquierda = new ImageView(imagenWallA);
        ImageView imagenViewWallA_derecha2 = new ImageView(imagenWallA);
        ImageView imagenViewWallA_izquierda2 = new ImageView(imagenWallA);
        ImageView imagenViewCity = new ImageView(imagenCity);
        ImageView imagenViewVida1 = new ImageView(imagenVida);
        ImageView imagenViewVida2 = new ImageView(imagenVida);
        
        //-----------------ImagenViews: Posición y tamaño-------------------------------------------------------------------------
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
        
        //-----------------Creación y posición de las vidas en un HBox---------------------------------------------------------------------------------------
        HBox cajaVidas = new HBox();
        cajaVidas.getChildren().addAll(imagenViewVida1, imagenViewVida2);
        
        //-----------------Creación y posición de grupoMuñeco---------------------------------------------------------------------------------------
        Group groupMuñeco = new Group ();
            groupMuñeco.getChildren().addAll(rectangleBoy, imagenViewBoy);
        groupMuñeco.setLayoutX(groupMuñecoX);
        groupMuñeco.setLayoutY(50);
        
        //-----------------Creación de los grupoFantasmaX---------------------------------------------------------------------------------------
        fantasma1.groupFantasma.getChildren().addAll(fantasma1);            
        fantasma2.groupFantasma.getChildren().addAll(fantasma2);            
        fantasma3.groupFantasma.getChildren().addAll(fantasma3);            
        fantasma4.groupFantasma.getChildren().addAll(fantasma4);            
        fantasma5.groupFantasma.getChildren().addAll(fantasma5);            
        fantasma6.groupFantasma.getChildren().addAll(fantasma6);            
        fantasma7.groupFantasma.getChildren().addAll(fantasma7);            
        fantasma8.groupFantasma.getChildren().addAll(fantasma8);            
        fantasmaRey.groupFantasma.getChildren().addAll(fantasmaRey);            
        fantasmaRebote1.groupFantasma.getChildren().addAll(fantasmaRebote1);            
        fantasmaRebote2.groupFantasma.getChildren().addAll(fantasmaRebote2);            
        fantasmaRebote3.groupFantasma.getChildren().addAll(fantasmaRebote3);
            
            fantasmaRey.circleFantasmaD.setFill(Color.BLACK);
            fantasmaRey.circleFantasmaI.setFill(Color.BLACK);
            fantasmaRey.circleFantasma.setFill(Color.AQUAMARINE);
            fantasmaRey.polygon1Fantasma.setFill(Color.AQUAMARINE);
            fantasmaRey.polygon2Fantasma.setFill(Color.BLACK);
            fantasmaRey.polygon3Fantasma.setFill(Color.AQUAMARINE);
            fantasmaRey.polygon4Fantasma.setFill(Color.AQUAMARINE);
            fantasmaRey.polygon5Fantasma.setFill(Color.BLACK);
            fantasmaRey.polygon6Fantasma.setFill(Color.AQUAMARINE);
            fantasmaRey.rectangleFantasma.setFill(Color.AQUAMARINE);
            
            fantasmaRebote1.circleFantasmaD.setFill(Color.DARKBLUE);
            fantasmaRebote1.circleFantasmaI.setFill(Color.DARKBLUE);
            fantasmaRebote1.circleFantasma.setFill(Color.PURPLE);
            fantasmaRebote1.polygon1Fantasma.setFill(Color.DARKBLUE);
            fantasmaRebote1.polygon2Fantasma.setFill(Color.DARKBLUE);
            fantasmaRebote1.polygon3Fantasma.setFill(Color.DARKBLUE);
            fantasmaRebote1.polygon4Fantasma.setFill(Color.DARKBLUE);
            fantasmaRebote1.polygon5Fantasma.setFill(Color.DARKBLUE);
            fantasmaRebote1.polygon6Fantasma.setFill(Color.DARKBLUE);
            fantasmaRebote1.rectangleFantasma.setFill(Color.PURPLE);
            
            fantasmaRebote2.circleFantasmaD.setFill(Color.DARKBLUE);
            fantasmaRebote2.circleFantasmaI.setFill(Color.DARKBLUE);
            fantasmaRebote2.circleFantasma.setFill(Color.PURPLE);
            fantasmaRebote2.polygon1Fantasma.setFill(Color.DARKBLUE);
            fantasmaRebote2.polygon2Fantasma.setFill(Color.DARKBLUE);
            fantasmaRebote2.polygon3Fantasma.setFill(Color.DARKBLUE);
            fantasmaRebote2.polygon4Fantasma.setFill(Color.DARKBLUE);
            fantasmaRebote2.polygon5Fantasma.setFill(Color.DARKBLUE);
            fantasmaRebote2.polygon6Fantasma.setFill(Color.DARKBLUE);
            fantasmaRebote2.rectangleFantasma.setFill(Color.PURPLE);
            
            fantasmaRebote3.circleFantasmaD.setFill(Color.DARKBLUE);
            fantasmaRebote3.circleFantasmaI.setFill(Color.DARKBLUE);
            fantasmaRebote3.circleFantasma.setFill(Color.PURPLE);
            fantasmaRebote3.polygon1Fantasma.setFill(Color.DARKBLUE);
            fantasmaRebote3.polygon2Fantasma.setFill(Color.DARKBLUE);
            fantasmaRebote3.polygon3Fantasma.setFill(Color.DARKBLUE);
            fantasmaRebote3.polygon4Fantasma.setFill(Color.DARKBLUE);
            fantasmaRebote3.polygon5Fantasma.setFill(Color.DARKBLUE);
            fantasmaRebote3.polygon6Fantasma.setFill(Color.DARKBLUE);
            fantasmaRebote3.rectangleFantasma.setFill(Color.PURPLE);
            
        //-----------------Posición de los grupoFantasmaX-------------------------------------------------------------------------
        fantasma1.groupFantasma.setLayoutY(fantasma1.groupFantasmaY);
        fantasma1.groupFantasmaX = randomEnemigosFantasma.nextInt(594) + 200;
        fantasma1.groupFantasma.setLayoutX(fantasma1.groupFantasmaX);
        
        fantasma2.groupFantasma.setLayoutY(fantasma2.groupFantasmaY);
        fantasma2.groupFantasmaX = randomEnemigosFantasma.nextInt(594) + 200;
        fantasma2.groupFantasma.setLayoutX(fantasma2.groupFantasmaX);
        
        fantasma3.groupFantasma.setLayoutY(fantasma3.groupFantasmaY);
        fantasma3.groupFantasmaX = randomEnemigosFantasma.nextInt(594) + 200;
        fantasma3.groupFantasma.setLayoutX(fantasma3.groupFantasmaX);
        
        fantasma4.groupFantasma.setLayoutY(fantasma4.groupFantasmaY);
        fantasma4.groupFantasmaX = randomEnemigosFantasma.nextInt(594) + 200;
        fantasma4.groupFantasma.setLayoutX(fantasma4.groupFantasmaX);
        
        fantasma5.groupFantasma.setLayoutY(fantasma5.groupFantasmaY);
        fantasma5.groupFantasmaX = randomEnemigosFantasma.nextInt(594) + 200;
        fantasma5.groupFantasma.setLayoutX(fantasma5.groupFantasmaX);
        
        fantasma6.groupFantasma.setLayoutY(fantasma6.groupFantasmaY);
        fantasma6.groupFantasmaX = randomEnemigosFantasma.nextInt(594) + 200;
        fantasma6.groupFantasma.setLayoutX(fantasma6.groupFantasmaX);
        
        fantasma7.groupFantasma.setLayoutY(fantasma7.groupFantasmaY);
        fantasma7.groupFantasmaX = randomEnemigosFantasma.nextInt(594) + 200;
        fantasma7.groupFantasma.setLayoutX(fantasma7.groupFantasmaX);
        
        fantasma8.groupFantasma.setLayoutY(fantasma8.groupFantasmaY);
        fantasma8.groupFantasmaX = randomEnemigosFantasma.nextInt(594) + 200;
        fantasma8.groupFantasma.setLayoutX(fantasma8.groupFantasmaX);
        
        fantasmaRey.groupFantasma.setLayoutY(fantasmaRey.groupFantasmaY);
        fantasmaRey.groupFantasmaX = randomEnemigosFantasma.nextInt(594) + 200;
        fantasmaRey.groupFantasma.setLayoutX(fantasmaRey.groupFantasmaX);
        
        fantasmaRebote1.groupFantasma.setLayoutY(fantasmaRebote1.groupFantasmaY);
        fantasmaRebote1.groupFantasmaX = randomEnemigosFantasma.nextInt(594) + 200;
        fantasmaRebote1.groupFantasma.setLayoutX(fantasmaRebote1.groupFantasmaX);
        
        fantasmaRebote2.groupFantasma.setLayoutY(fantasmaRebote2.groupFantasmaY);
        fantasmaRebote2.groupFantasmaX = randomEnemigosFantasma.nextInt(594) + 200;
        fantasmaRebote2.groupFantasma.setLayoutX(fantasmaRebote2.groupFantasmaX);
        
        fantasmaRebote3.groupFantasma.setLayoutY(fantasmaRebote3.groupFantasmaY);
        fantasmaRebote3.groupFantasmaX = randomEnemigosFantasma.nextInt(594) + 200;
        fantasmaRebote3.groupFantasma.setLayoutX(fantasmaRebote3.groupFantasmaX);
        
//-----------------Texto Puntuación: Creación, posición, fuente (...)---------------------------------------------------------------------------------------
        Text marcadorText = new Text ("0");
        marcadorText.setFont(Font.font(25));
        marcadorText.setX(824);
        marcadorText.setY(60);
        marcadorText.setFill(Color.ORANGE);
        
//-----------------Animación Fantasmas---------------------------------------------------------------------------------------
        AnimationTimer animationFantasma = new AnimationTimer (){
        
            @Override
            public void handle (long now) {
                fantasma1.groupFantasmaY = fantasma1.groupFantasmaY - dificultad;
                fantasma1.groupFantasma.setLayoutY(fantasma1.groupFantasmaY);
                fantasma2.groupFantasmaY = fantasma2.groupFantasmaY - dificultad;
                fantasma2.groupFantasma.setLayoutY(fantasma2.groupFantasmaY);
                fantasma3.groupFantasmaY = fantasma3.groupFantasmaY - dificultad;
                fantasma3.groupFantasma.setLayoutY(fantasma3.groupFantasmaY);
                fantasma4.groupFantasmaY = fantasma4.groupFantasmaY - dificultad;
                fantasma4.groupFantasma.setLayoutY(fantasma4.groupFantasmaY);
                fantasma5.groupFantasmaY = fantasma5.groupFantasmaY - dificultad;
                fantasma5.groupFantasma.setLayoutY(fantasma5.groupFantasmaY);
                fantasma6.groupFantasmaY = fantasma6.groupFantasmaY - dificultad;
                fantasma6.groupFantasma.setLayoutY(fantasma6.groupFantasmaY);
                fantasma7.groupFantasmaY = fantasma7.groupFantasmaY - dificultad;
                fantasma7.groupFantasma.setLayoutY(fantasma7.groupFantasmaY);
                fantasma8.groupFantasmaY = fantasma8.groupFantasmaY - dificultad;
                fantasma8.groupFantasma.setLayoutY(fantasma8.groupFantasmaY);
                fantasmaRey.groupFantasmaY = fantasmaRey.groupFantasmaY - dificultad;
                fantasmaRey.groupFantasma.setLayoutY(fantasmaRey.groupFantasmaY);
                fantasmaRebote1.groupFantasmaY = fantasmaRebote1.groupFantasmaY - dificultad;
                fantasmaRebote1.groupFantasma.setLayoutY(fantasmaRebote1.groupFantasmaY);
                fantasmaRebote2.groupFantasmaY = fantasmaRebote2.groupFantasmaY - dificultad;
                fantasmaRebote2.groupFantasma.setLayoutY(fantasmaRebote2.groupFantasmaY);
                fantasmaRebote3.groupFantasmaY = fantasmaRebote3.groupFantasmaY - dificultad;
                fantasmaRebote3.groupFantasma.setLayoutY(fantasmaRebote3.groupFantasmaY);
                
                fantasmaRebote1.groupFantasmaX = fantasmaRebote1.groupFantasmaX + velocidadRebote1X;
                fantasmaRebote2.groupFantasmaX = fantasmaRebote2.groupFantasmaX + velocidadRebote2X;
                fantasmaRebote3.groupFantasmaX = fantasmaRebote3.groupFantasmaX + velocidadRebote3X;
                fantasmaRebote1.groupFantasma.setLayoutX(fantasmaRebote1.groupFantasmaX);
                fantasmaRebote2.groupFantasma.setLayoutX(fantasmaRebote1.groupFantasmaX);
                fantasmaRebote1.groupFantasma.setLayoutX(fantasmaRebote1.groupFantasmaX);
                
                if (fantasma1.groupFantasmaY<-30) {
                    fantasma1.groupFantasmaY = randomEnemigosFantasma.nextInt(400) + 1000;
                    fantasma1.groupFantasma.setLayoutX(randomEnemigosFantasma.nextInt(594) + 200);
                    marcador = marcador + 10;
                    marcadorText.setText(String.valueOf(marcador));
                }
                
                if (fantasma2.groupFantasmaY<-30) {
                    fantasma2.groupFantasmaY = randomEnemigosFantasma.nextInt(400) + 1000;
                    fantasma2.groupFantasma.setLayoutX(randomEnemigosFantasma.nextInt(594) + 200);
                    marcador = marcador + 10;
                    marcadorText.setText(String.valueOf(marcador));
                }
                
                if (fantasma3.groupFantasmaY<-30) {
                    fantasma3.groupFantasmaY = randomEnemigosFantasma.nextInt(400) + 1000;
                    fantasma3.groupFantasma.setLayoutX(randomEnemigosFantasma.nextInt(594) + 200);
                    marcador = marcador + 10;
                    marcadorText.setText(String.valueOf(marcador));
                }
                
                if (fantasma4.groupFantasmaY<-30) {
                    fantasma4.groupFantasmaY = randomEnemigosFantasma.nextInt(400) + 1000;
                    fantasma4.groupFantasma.setLayoutX(randomEnemigosFantasma.nextInt(594) + 200);
                    marcador = marcador + 10;
                    marcadorText.setText(String.valueOf(marcador));
                }
                
                if (fantasma5.groupFantasmaY<-30) {
                    fantasma5.groupFantasmaY = randomEnemigosFantasma.nextInt(400) + 1000;
                    fantasma5.groupFantasma.setLayoutX(randomEnemigosFantasma.nextInt(594) + 200);
                    marcador = marcador + 10;
                    marcadorText.setText(String.valueOf(marcador));
                }
                
                if (fantasma6.groupFantasmaY<-30) {
                    fantasma6.groupFantasmaY = randomEnemigosFantasma.nextInt(400) + 1000;
                    fantasma6.groupFantasma.setLayoutX(randomEnemigosFantasma.nextInt(594) + 200);
                    marcador = marcador + 10;
                    marcadorText.setText(String.valueOf(marcador));
                }
                
                if (fantasma7.groupFantasmaY<-30) {
                    fantasma7.groupFantasmaY = randomEnemigosFantasma.nextInt(400) + 1000;
                    fantasma7.groupFantasma.setLayoutX(randomEnemigosFantasma.nextInt(594) + 200);
                    marcador = marcador + 10;
                    marcadorText.setText(String.valueOf(marcador));
                }
                
                if (fantasma8.groupFantasmaY<-30) {
                    fantasma8.groupFantasmaY = randomEnemigosFantasma.nextInt(400) + 1000;
                    fantasma8.groupFantasma.setLayoutX(randomEnemigosFantasma.nextInt(594) + 200);
                    marcador = marcador + 10;
                    marcadorText.setText(String.valueOf(marcador));
                }
                
                if (fantasmaRey.groupFantasmaY<-30) {
                    fantasmaRey.groupFantasmaY = randomEnemigosFantasma.nextInt(400) + 1000;
                    fantasmaRey.groupFantasma.setLayoutX(groupMuñecoX);
                    marcador = marcador + 25;
                    marcadorText.setText(String.valueOf(marcador));
                }
                
                if (fantasmaRebote1.groupFantasmaY<-30) {
                    fantasmaRebote1.groupFantasmaY = randomEnemigosFantasma.nextInt(400) + 1500;
                    fantasmaRebote1.groupFantasmaX = randomEnemigosFantasma.nextInt(534) + 230;
                    groupFantasmaRebote1X2 = fantasmaRebote1.groupFantasmaX;
                    fantasmaRebote1.groupFantasma.setLayoutX(fantasmaRebote1.groupFantasmaX);
                    marcador = marcador + 20;
                    marcadorText.setText(String.valueOf(marcador));
                }
                
                if (fantasmaRebote2.groupFantasmaY<-30) {
                    fantasmaRebote2.groupFantasmaY = randomEnemigosFantasma.nextInt(400) + 1500;
                    fantasmaRebote2.groupFantasmaX = randomEnemigosFantasma.nextInt(534) + 230;
                    groupFantasmaRebote2X2 = fantasmaRebote2.groupFantasmaX;
                    fantasmaRebote2.groupFantasma.setLayoutX(fantasmaRebote2.groupFantasmaX);
                    marcador = marcador + 20;
                    marcadorText.setText(String.valueOf(marcador));
                }
                
                if (fantasmaRebote3.groupFantasmaY<-30) {
                    fantasmaRebote3.groupFantasmaY = randomEnemigosFantasma.nextInt(400) + 1500;
                    fantasmaRebote3.groupFantasmaX = randomEnemigosFantasma.nextInt(534) + 230;
                    groupFantasmaRebote3X2 = fantasmaRebote3.groupFantasmaX;
                    fantasmaRebote3.groupFantasma.setLayoutX(fantasmaRebote3.groupFantasmaX);
                    marcador = marcador + 20;
                    marcadorText.setText(String.valueOf(marcador));
                }
                
                if (fantasmaRebote1.groupFantasmaX>(groupFantasmaRebote1X2+30)) {
                    velocidadRebote1X = -3;
                }
                if (fantasmaRebote1.groupFantasmaX<(groupFantasmaRebote1X2-30)) {
                    velocidadRebote1X = 3;
                }
                
                if (fantasmaRebote2.groupFantasmaX>(groupFantasmaRebote2X2+30)) {
                    velocidadRebote2X = -3;
                }
                if (fantasmaRebote2.groupFantasmaX<(groupFantasmaRebote2X2-30)) {
                    velocidadRebote2X = 3;
                }
                
                if (fantasmaRebote3.groupFantasmaX>(groupFantasmaRebote3X2+30)) {
                    velocidadRebote3X = -3;
                }
                if (fantasmaRebote3.groupFantasmaX<(groupFantasmaRebote3X2-30)) {
                    velocidadRebote3X = 3;
                }
            };
        };
        
//-----------------Animación Ciudad---------------------------------------------------------------------------------------
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
        
//-----------------Animación Muñeco---------------------------------------------------------------------------------------
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
        
//-----------------Animación Muros---------------------------------------------------------------------------------------
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
        
//-----------------Animación Dificultad incrementa---------------------------------------------------------------------------------------
        AnimationTimer animationDificultad = new AnimationTimer (){
            
            @Override
            public void handle (long now) {
                if (dificultad<10){
                    dificultad = (float) (dificultad + 0.0025);
                }
            };
        };
        
//-----------------Texto Derrota: Creación, posición, fuente (...)---------------------------------------------------------------------------------------
        Text derrota = new Text ("Fin de la Partida");
        derrota.setFont(Font.font(120));
        derrota.setX(100);
        derrota.setY(200);
        derrota.setFill(Color.ORANGE);
        
//-----------------Texto Puntuación: Creación, posición, fuente (...)---------------------------------------------------------------------------------------
        Text score = new Text ("Puntuación:");
        score.setFont(Font.font(25));
        score.setX(824);
        score.setY(25);
        score.setFill(Color.ORANGE);
        Rectangle rectangleScore = new Rectangle(824, 0, 200, 75);
        
//-----------------Escenario (Pane)---------------------------------------------------------------------------------------
        Pane root = new Pane();
            root.getChildren().addAll(imagenViewCity, imagenViewWallA_derecha, 
                    imagenViewWallA_izquierda, imagenViewWallA_derecha2, 
                    imagenViewWallA_izquierda2, rectangleVida, cajaVidas, groupMuñeco,
                    fantasmaRebote1.groupFantasma, fantasmaRebote2.groupFantasma, fantasmaRebote3.groupFantasma,
                    fantasma1.groupFantasma, fantasma2.groupFantasma, fantasma3.groupFantasma, fantasma4.groupFantasma,
                    fantasma5.groupFantasma, fantasma6.groupFantasma, fantasma7.groupFantasma, fantasma8.groupFantasma,
                    fantasmaRey.groupFantasma, rectangleScore, score, marcadorText);

//-----------------Animación choque---------------------------------------------------------------------------------------
        AnimationTimer animationChoque = new AnimationTimer (){
            
            @Override
            public void handle (long now) {
                Shape shapeCollision1 = Shape.intersect(rectangleBoy, rectangleDerecha);
                boolean colisionShape1 = shapeCollision1.getBoundsInLocal().isEmpty();
                if (colisionShape1 == false){
                    if (numVidas == 0){
                        root.getChildren().add(derrota);
                        this.stop();
                        animationDificultad.stop();
                        animationWall.stop();
                        animationCity.stop();
                        animationMuñeco.stop();
                        animationFantasma.stop();
                    }
                    else {
                        numVidas--;
                        cajaVidas.getChildren().remove(numVidas);
                        reinicio();
                        groupMuñecoX = 512;
                    }                
                };
                          
                Shape shapeCollision2 = Shape.intersect(rectangleBoy, rectangleIzquierda);
                boolean colisionShape2 = shapeCollision2.getBoundsInLocal().isEmpty();
                if (colisionShape2 == false){
                    if (numVidas == 0){
                        root.getChildren().add(derrota);
                        this.stop();
                        animationDificultad.stop();
                        animationWall.stop();
                        animationCity.stop();
                        animationMuñeco.stop();
                        animationFantasma.stop();
                    }
                    else {
                        numVidas--;
                        cajaVidas.getChildren().remove(numVidas);
                        reinicio();
                        groupMuñecoX = 512;
                    }                
                };
                         
                Shape shapeCollision3 = Shape.intersect(rectangleBoy, fantasma1.circleFantasma);
                boolean colisionShape3 = shapeCollision3.getBoundsInLocal().isEmpty();
                if (colisionShape3 == false){
                    if (numVidas == 0){
                        root.getChildren().add(derrota);
                        this.stop();
                        animationDificultad.stop();
                        animationWall.stop();
                        animationCity.stop();
                        animationMuñeco.stop();
                        animationFantasma.stop();
                    }
                    else {
                        numVidas--;
                        cajaVidas.getChildren().remove(numVidas);
                        reinicio();
                        fantasma1.groupFantasmaY=2000;
                    }                
                };
                
                Shape shapeCollision4 = Shape.intersect(rectangleBoy, fantasma2.circleFantasma);
                boolean colisionShape4 = shapeCollision4.getBoundsInLocal().isEmpty();
                if (colisionShape4 == false){
                    if (numVidas == 0){
                        root.getChildren().add(derrota);
                        this.stop();
                        animationDificultad.stop();
                        animationWall.stop();
                        animationCity.stop();
                        animationMuñeco.stop();
                        animationFantasma.stop();
                    }
                    else {
                        numVidas--;
                        cajaVidas.getChildren().remove(numVidas);
                        reinicio();
                        fantasma2.groupFantasmaY=2000;
                    }                
                };
                
                Shape shapeCollision5 = Shape.intersect(rectangleBoy, fantasma3.circleFantasma);
                boolean colisionShape5 = shapeCollision5.getBoundsInLocal().isEmpty();
                if (colisionShape5 == false){
                    if (numVidas == 0){
                        root.getChildren().add(derrota);
                        this.stop();
                        animationDificultad.stop();
                        animationWall.stop();
                        animationCity.stop();
                        animationMuñeco.stop();
                        animationFantasma.stop();
                    }
                    else {
                        numVidas--;
                        cajaVidas.getChildren().remove(numVidas);
                        reinicio();
                        fantasma3.groupFantasmaY=2000;
                    }                
                };
                
                Shape shapeCollision6 = Shape.intersect(rectangleBoy, fantasma4.circleFantasma);
                boolean colisionShape6 = shapeCollision6.getBoundsInLocal().isEmpty();
                if (colisionShape6 == false){
                    if (numVidas == 0){
                        root.getChildren().add(derrota);
                        this.stop();
                        animationDificultad.stop();
                        animationWall.stop();
                        animationCity.stop();
                        animationMuñeco.stop();
                        animationFantasma.stop();
                    }
                    else {
                        numVidas--;
                        cajaVidas.getChildren().remove(numVidas);
                        reinicio();
                        fantasma4.groupFantasmaY=2000;
                    }                
                };
                
                Shape shapeCollision7 = Shape.intersect(rectangleBoy, fantasma5.circleFantasma);
                boolean colisionShape7 = shapeCollision7.getBoundsInLocal().isEmpty();
                if (colisionShape7 == false){
                    if (numVidas == 0){
                        root.getChildren().add(derrota);
                        this.stop();
                        animationDificultad.stop();
                        animationWall.stop();
                        animationCity.stop();
                        animationMuñeco.stop();
                        animationFantasma.stop();
                    }
                    else {
                        numVidas--;
                        cajaVidas.getChildren().remove(numVidas);
                        reinicio();
                        fantasma5.groupFantasmaY=2000;
                    }                
                };
                
                Shape shapeCollision8 = Shape.intersect(rectangleBoy, fantasma6.circleFantasma);
                boolean colisionShape8 = shapeCollision8.getBoundsInLocal().isEmpty();
                if (colisionShape8 == false){
                    if (numVidas == 0){
                        root.getChildren().add(derrota);
                        this.stop();
                        animationDificultad.stop();
                        animationWall.stop();
                        animationCity.stop();
                        animationMuñeco.stop();
                        animationFantasma.stop();
                    }
                    else {
                        numVidas--;
                        cajaVidas.getChildren().remove(numVidas);
                        reinicio();
                        fantasma6.groupFantasmaY=2000;
                    }                
                };
                
                Shape shapeCollision9 = Shape.intersect(rectangleBoy, fantasma7.circleFantasma);
                boolean colisionShape9 = shapeCollision9.getBoundsInLocal().isEmpty();
                if (colisionShape9 == false){
                    if (numVidas == 0){
                        root.getChildren().add(derrota);
                        this.stop();
                        animationDificultad.stop();
                        animationWall.stop();
                        animationCity.stop();
                        animationMuñeco.stop();
                        animationFantasma.stop();
                    }
                    else {
                        numVidas--;
                        cajaVidas.getChildren().remove(numVidas);
                        reinicio();
                        fantasma7.groupFantasmaY=2000;
                    }                
                };
                
                Shape shapeCollision10 = Shape.intersect(rectangleBoy, fantasma8.circleFantasma);
                boolean colisionShape10 = shapeCollision10.getBoundsInLocal().isEmpty();
                if (colisionShape10 == false){
                    if (numVidas == 0){
                        root.getChildren().add(derrota);
                        this.stop();
                        animationDificultad.stop();
                        animationWall.stop();
                        animationCity.stop();
                        animationMuñeco.stop();
                        animationFantasma.stop();
                    }
                    else {
                        numVidas--;
                        cajaVidas.getChildren().remove(numVidas);
                        reinicio();
                        fantasma8.groupFantasmaY=2000;
                    }                
                };
                
                Shape shapeCollision11 = Shape.intersect(rectangleBoy, fantasmaRey.circleFantasma);
                boolean colisionShape11 = shapeCollision11.getBoundsInLocal().isEmpty();
                if (colisionShape11 == false){
                    if (numVidas == 0){
                        root.getChildren().add(derrota);
                        this.stop();
                        animationDificultad.stop();
                        animationWall.stop();
                        animationCity.stop();
                        animationMuñeco.stop();
                        animationFantasma.stop();
                    }
                    else {
                        numVidas--;
                        cajaVidas.getChildren().remove(numVidas);
                        reinicio();
                        fantasmaRey.groupFantasmaY=2000;
                    }
                };
                
                Shape shapeCollision12 = Shape.intersect(rectangleBoy, fantasmaRebote1.circleFantasma);
                boolean colisionShape12 = shapeCollision12.getBoundsInLocal().isEmpty();
                if (colisionShape12 == false){
                    if (numVidas == 0){
                        root.getChildren().add(derrota);
                        this.stop();
                        animationDificultad.stop();
                        animationWall.stop();
                        animationCity.stop();
                        animationMuñeco.stop();
                        animationFantasma.stop();
                    }
                    else {
                        numVidas--;
                        cajaVidas.getChildren().remove(numVidas);
                        reinicio();
                        fantasmaRebote1.groupFantasmaY=2000;
                    }                
                };
                
                Shape shapeCollision13 = Shape.intersect(rectangleBoy, fantasmaRebote2.circleFantasma);
                boolean colisionShape13 = shapeCollision13.getBoundsInLocal().isEmpty();
                if (colisionShape13 == false){
                    if (numVidas == 0){
                        root.getChildren().add(derrota);
                        this.stop();
                        animationDificultad.stop();
                        animationWall.stop();
                        animationCity.stop();
                        animationMuñeco.stop();
                        animationFantasma.stop();
                    }
                    else {
                        numVidas--;
                        cajaVidas.getChildren().remove(numVidas);
                        reinicio();
                        fantasmaRebote2.groupFantasmaY=2000;
                    }                
                };
                
                Shape shapeCollision14 = Shape.intersect(rectangleBoy, fantasmaRebote3.circleFantasma);
                boolean colisionShape14 = shapeCollision14.getBoundsInLocal().isEmpty();
                if (colisionShape14 == false){
                    if (numVidas == 0){
                        root.getChildren().add(derrota);
                        this.stop();
                        animationDificultad.stop();
                        animationWall.stop();
                        animationCity.stop();
                        animationMuñeco.stop();
                        animationFantasma.stop();
                    }
                    else {
                        numVidas--;
                        cajaVidas.getChildren().remove(numVidas);
                        reinicio();
                        fantasmaRebote3.groupFantasmaY=2000;
                    }                
                };
            };
        };
        
//-----------------Inicio de animaciones---------------------------------------------------------------------------------------
        animationDificultad.start();
        animationChoque.start();
        animationWall.start();
        animationCity.start();
        animationMuñeco.start();
        animationFantasma.start();
        
//-----------------Inicio Escena---------------------------------------------------------------------------------------
        Scene scene = new Scene(root, 1024, 600);
        
//-----------------Animación Fantasmas---------------------------------------------------------------------------------------
        scene.setOnKeyReleased((KeyEvent ) -> {
            velocidad = 0;            
        });
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent teclapulsada) {
                switch(teclapulsada.getCode()) {
                    
                    case LEFT:
                        velocidad = -5;
                        break;
                    case RIGHT:
                        velocidad = 5;
                        break;
                }
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
//        groupMuñecoX = 512;
        velocidad = 0;
        dificultad = 8;
//        
//        groupFantasmaY1 = 1200;
//        groupFantasmaY2 = 1200;
//        groupFantasmaY3 = 1700;
//        groupFantasmaY4 = 2200;
//        groupFantasmaY5 = 2700;
//        groupFantasmaY6 = 3200;
//        groupFantasmaY7 = 3700;
//        groupFantasmaY8 = 4200;
//        groupFantasmaReyY = 900;
//        groupFantasmaRebote1Y = 1300;
//        groupFantasmaRebote2Y = 1700;
//        groupFantasmaRebote3Y = 2100;
    }
    
    public void restablecer (){   
        marcador = 0;
        numVidas = 2;
        imagenViewWallA = 10;
        imagenViewWallA2 = -600;
        groupMuñecoX = 512;
        velocidad = 0;   
        velocidadCity = 0;
        fondoCityY = 0;
        groupCityX = 0;   
//        groupFantasmaY1 = 1200;
//        groupFantasmaY2 = 1200;
//        groupFantasmaY3 = 1700;
//        groupFantasmaY4 = 2200;
//        groupFantasmaY5 = 2700;
//        groupFantasmaY6 = 3200;
//        groupFantasmaY7 = 3700;
//        groupFantasmaY8 = 4200;
//        groupFantasmaReyY = 900;
        dificultad = 8;
        numVidas = 2;
//        groupFantasmaX1;
//        groupFantasmaX2;
//        groupFantasmaX3;
//        groupFantasmaX4;
//        groupFantasmaX5;
//        groupFantasmaX6;
//        groupFantasmaX7;
//        groupFantasmaX8;
//        groupFantasmaReyX;
//        groupFantasmaRebote1X;
//        groupFantasmaRebote2X;
//        groupFantasmaRebote3X;
//        groupFantasmaRebote1X2;
//        groupFantasmaRebote2X2;
//        groupFantasmaRebote3X2;
        velocidadRebote1X = 3;
        velocidadRebote2X = 3;
        velocidadRebote3X = 3;
//        groupFantasmaRebote1Y = 1200;
//        groupFantasmaRebote2Y = 1600;
//        groupFantasmaRebote3Y = 2100;
    } 
}