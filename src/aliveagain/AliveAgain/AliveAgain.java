/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aliveagain.AliveAgain;

import java.io.File;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author Antonio Manzano Borrego
 */
public class AliveAgain extends Application {
    
//--------------------Variables usadas al inicio del juego, situadas fuera del start porque el reinicio necesita cambiarlas-----------------
        
        //-----------------Constantes--------------------------------------------------------------------------------------------
        final int ANCHO_MUROS = 200; //mayor que 200
        final int ANCHO_PANTALLA = 1000; //superior al cuatruple del ANCHO_MURO
        final int ALTO_PANTALLA = 800; // acabado en 0
        
        int groupMuñecoX = (ANCHO_PANTALLA-32)/2;
        int imagenViewWallA = 0;
        int imagenViewWallA2 = -ALTO_PANTALLA;
        int velocidad = 0;
        float velocidadCity = 0;
        double fondoCityY = 0;
        float groupCityX = 0;   
        float dificultadMin = 6;
        float dificultad = dificultadMin;
        float dificultadMax = 12;
        int numVidas = 2;
        int marcador;
        int groupFantasmaRebote1XInicial;
        int groupFantasmaRebote2XInicial;
        int groupFantasmaRebote3XInicial;
        int velocidadRebote1X = 3;
        int velocidadRebote2X = 3;
        int velocidadRebote3X = 3;
        boolean estarVivo = true;
        
    @Override
    public void start(Stage primaryStage) {
        
//-----------------Implementar musica--------------------------------------------------------------------------------
        Media sound = new Media(new File("src\\aliveagain\\AliveAgain\\Music\\AdventureMeme.mp3").toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
        
//-----------------Establecemos variables ajenas al reinicio del juego--------------------------------------------------------------------
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
        Rectangle rectangleVida = new Rectangle(0, 0, ANCHO_MUROS, 75);
        Rectangle rectangleFinal = new Rectangle(0, 0, ANCHO_PANTALLA, ALTO_PANTALLA);
        rectangleFinal.setVisible(false);
        
        //-----------------Creamos objetos clase rectángulo para la colisión del jugador con los laterales-------------------------------------------------------------------
        Rectangle rectangleDerecha = new Rectangle (ANCHO_MUROS, ALTO_PANTALLA);
        Rectangle rectangleIzquierda = new Rectangle (ANCHO_PANTALLA-ANCHO_MUROS, 0, ANCHO_MUROS, ALTO_PANTALLA);
        
        //-----------------Creamos objetos clase rectángulo del jugador y visibilidad---------------------------------------------------------------------------------
        Rectangle rectangleBoy = new Rectangle (32, 111);
        rectangleBoy.setVisible(false);
        
        //-----------------Creamos objetos de clase imagen--------------------------------------------------------------------------------------
        Image imagenBoy = new Image(getClass().getResourceAsStream("Imagen/boy.png"));
        Image imagenWallA = new Image(getClass().getResourceAsStream("Imagen/wall_A.jpg"));
        Image imagenCity = new Image(getClass().getResourceAsStream("Imagen/city.jpg"));
        Image imagenVida = new Image(getClass().getResourceAsStream("Imagen/vida.jpg"));
        
        //-----------------Creamos objetos de clase ImageViews------------------------------------------------------------------------------------
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
        imagenViewCity.setFitWidth(ANCHO_PANTALLA);
        imagenViewCity.setFitHeight(4*ALTO_PANTALLA);
        imagenViewWallA_izquierda.setX(0);
        imagenViewWallA_izquierda.setY(0);
        imagenViewWallA_izquierda.setFitWidth(ANCHO_MUROS);
        imagenViewWallA_izquierda.setFitHeight(ALTO_PANTALLA + 10);
        imagenViewWallA_derecha.setX(ANCHO_PANTALLA-ANCHO_MUROS);
        imagenViewWallA_derecha.setY(0);
        imagenViewWallA_derecha.setFitWidth(ANCHO_MUROS);
        imagenViewWallA_derecha.setFitHeight(ALTO_PANTALLA + 10);
        imagenViewWallA_izquierda2.setX(0);
        imagenViewWallA_izquierda2.setY(0);
        imagenViewWallA_izquierda2.setFitWidth(ANCHO_MUROS);
        imagenViewWallA_izquierda2.setFitHeight(ALTO_PANTALLA + 10);
        imagenViewWallA_derecha2.setX(ANCHO_PANTALLA-ANCHO_MUROS);
        imagenViewWallA_derecha2.setY(0);
        imagenViewWallA_derecha2.setFitWidth(ANCHO_MUROS);
        imagenViewWallA_derecha2.setFitHeight(ALTO_PANTALLA + 10);
        
        //-----------------Creación y posición del objeto clase HBox para contener las vidas---------------------------------------------------------------------------------------
        HBox cajaVidas = new HBox();
        cajaVidas.getChildren().addAll(imagenViewVida1, imagenViewVida2);
        
        //-----------------Creación y posición del objeto clase Group grupoMuñeco---------------------------------------------------------------------------------------
        Group groupMuñeco = new Group ();
            groupMuñeco.getChildren().addAll(rectangleBoy, imagenViewBoy);
        groupMuñeco.setLayoutX(groupMuñecoX);
        groupMuñeco.setLayoutY(50);
        
        //-----------------Creación de objetos clase Group grupoFantasmaX---------------------------------------------------------------------------------------
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
            fantasmaRebote1.rectangleFantasma.setFill(Color.DARKBLUE);
            
            fantasmaRebote2.circleFantasmaD.setFill(Color.DARKBLUE);
            fantasmaRebote2.circleFantasmaI.setFill(Color.DARKBLUE);
            fantasmaRebote2.circleFantasma.setFill(Color.PURPLE);
            fantasmaRebote2.polygon1Fantasma.setFill(Color.DARKBLUE);
            fantasmaRebote2.polygon2Fantasma.setFill(Color.DARKBLUE);
            fantasmaRebote2.polygon3Fantasma.setFill(Color.DARKBLUE);
            fantasmaRebote2.polygon4Fantasma.setFill(Color.DARKBLUE);
            fantasmaRebote2.polygon5Fantasma.setFill(Color.DARKBLUE);
            fantasmaRebote2.polygon6Fantasma.setFill(Color.DARKBLUE);
            fantasmaRebote2.rectangleFantasma.setFill(Color.DARKBLUE);
            
            fantasmaRebote3.circleFantasmaD.setFill(Color.DARKBLUE);
            fantasmaRebote3.circleFantasmaI.setFill(Color.DARKBLUE);
            fantasmaRebote3.circleFantasma.setFill(Color.PURPLE);
            fantasmaRebote3.polygon1Fantasma.setFill(Color.DARKBLUE);
            fantasmaRebote3.polygon2Fantasma.setFill(Color.DARKBLUE);
            fantasmaRebote3.polygon3Fantasma.setFill(Color.DARKBLUE);
            fantasmaRebote3.polygon4Fantasma.setFill(Color.DARKBLUE);
            fantasmaRebote3.polygon5Fantasma.setFill(Color.DARKBLUE);
            fantasmaRebote3.polygon6Fantasma.setFill(Color.DARKBLUE);
            fantasmaRebote3.rectangleFantasma.setFill(Color.DARKBLUE);
            
        //-----------------Posición de los grupoFantasmaX-------------------------------------------------------------------------
        fantasma1.groupFantasmaY = 1200;
        fantasma1.groupFantasma.setLayoutY(fantasma1.groupFantasmaY);
        fantasma1.groupFantasmaX = randomEnemigosFantasma.nextInt(ANCHO_PANTALLA-(2*ANCHO_MUROS) - 30) + ANCHO_MUROS;
        fantasma1.groupFantasma.setLayoutX(fantasma1.groupFantasmaX);
        
        fantasma2.groupFantasmaY = 1400;
        fantasma2.groupFantasma.setLayoutY(fantasma2.groupFantasmaY);
        fantasma2.groupFantasmaX = randomEnemigosFantasma.nextInt(ANCHO_PANTALLA-(2*ANCHO_MUROS) - 30) + ANCHO_MUROS;
        fantasma2.groupFantasma.setLayoutX(fantasma2.groupFantasmaX);
        
        fantasma3.groupFantasmaY = 1700;
        fantasma3.groupFantasma.setLayoutY(fantasma3.groupFantasmaY);
        fantasma3.groupFantasmaX = randomEnemigosFantasma.nextInt(ANCHO_PANTALLA-(2*ANCHO_MUROS) - 30) + ANCHO_MUROS;
        fantasma3.groupFantasma.setLayoutX(fantasma3.groupFantasmaX);
        
        fantasma4.groupFantasmaY = 2200;
        fantasma4.groupFantasma.setLayoutY(fantasma4.groupFantasmaY);
        fantasma4.groupFantasmaX = randomEnemigosFantasma.nextInt(ANCHO_PANTALLA-(2*ANCHO_MUROS) - 30) + ANCHO_MUROS;
        fantasma4.groupFantasma.setLayoutX(fantasma4.groupFantasmaX);
        
        fantasma5.groupFantasmaY = 2700;
        fantasma5.groupFantasma.setLayoutY(fantasma5.groupFantasmaY);
        fantasma5.groupFantasmaX = randomEnemigosFantasma.nextInt(ANCHO_PANTALLA-(2*ANCHO_MUROS) - 30) + ANCHO_MUROS;
        fantasma5.groupFantasma.setLayoutX(fantasma5.groupFantasmaX);
        
        fantasma6.groupFantasmaY = 3200;
        fantasma6.groupFantasma.setLayoutY(fantasma6.groupFantasmaY);
        fantasma6.groupFantasmaX = randomEnemigosFantasma.nextInt(ANCHO_PANTALLA-(2*ANCHO_MUROS) - 30) + ANCHO_MUROS;
        fantasma6.groupFantasma.setLayoutX(fantasma6.groupFantasmaX);
        
        fantasma7.groupFantasmaY = 3700;
        fantasma7.groupFantasma.setLayoutY(fantasma7.groupFantasmaY);
        fantasma7.groupFantasmaX = randomEnemigosFantasma.nextInt(ANCHO_PANTALLA-(2*ANCHO_MUROS) - 30) + ANCHO_MUROS;
        fantasma7.groupFantasma.setLayoutX(fantasma7.groupFantasmaX);
        
        fantasma8.groupFantasmaY = 4200;
        fantasma8.groupFantasma.setLayoutY(fantasma8.groupFantasmaY);
        fantasma8.groupFantasmaX = randomEnemigosFantasma.nextInt(ANCHO_PANTALLA-(2*ANCHO_MUROS) - 30) + ANCHO_MUROS;
        fantasma8.groupFantasma.setLayoutX(fantasma8.groupFantasmaX);
        
        fantasmaRey.groupFantasmaY = 3000;
        fantasmaRey.groupFantasma.setLayoutY(fantasmaRey.groupFantasmaY);
        fantasmaRey.groupFantasma.setLayoutX(groupMuñecoX);
        
        fantasmaRebote1.groupFantasmaY = 2500;
        fantasmaRebote1.groupFantasma.setLayoutY(fantasmaRebote1.groupFantasmaY);
        fantasmaRebote1.groupFantasmaX = randomEnemigosFantasma.nextInt(ANCHO_PANTALLA-(2*ANCHO_MUROS)-90) + ANCHO_MUROS + 30;
        fantasmaRebote1.groupFantasma.setLayoutX(fantasmaRebote1.groupFantasmaX);
        groupFantasmaRebote1XInicial = fantasmaRebote1.groupFantasmaX;
        
        fantasmaRebote2.groupFantasmaY = 3300;
        fantasmaRebote2.groupFantasma.setLayoutY(fantasmaRebote2.groupFantasmaY);
        fantasmaRebote2.groupFantasmaX = randomEnemigosFantasma.nextInt(ANCHO_PANTALLA-(2*ANCHO_MUROS)-90) + ANCHO_MUROS + 30;
        fantasmaRebote2.groupFantasma.setLayoutX(fantasmaRebote2.groupFantasmaX);
        groupFantasmaRebote2XInicial = fantasmaRebote2.groupFantasmaX;
        
        fantasmaRebote3.groupFantasmaY = 3900;
        fantasmaRebote3.groupFantasma.setLayoutY(fantasmaRebote3.groupFantasmaY);
        fantasmaRebote3.groupFantasmaX = randomEnemigosFantasma.nextInt(ANCHO_PANTALLA-(2*ANCHO_MUROS)-90) + ANCHO_MUROS + 30;
        fantasmaRebote3.groupFantasma.setLayoutX(fantasmaRebote3.groupFantasmaX);
        groupFantasmaRebote3XInicial = fantasmaRebote3.groupFantasmaX;
        
//-----------------Texto Puntuación: Creación, posición, fuente (...)---------------------------------------------------------------------------------------
        Text marcadorText = new Text ("0");
        marcadorText.setFont(Font.font(25));
        marcadorText.setX(ANCHO_PANTALLA-ANCHO_MUROS);
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
                fantasmaRebote2.groupFantasma.setLayoutX(fantasmaRebote2.groupFantasmaX);
                fantasmaRebote3.groupFantasma.setLayoutX(fantasmaRebote3.groupFantasmaX);
                
                if (fantasma1.groupFantasmaY<-30) {
                    fantasma1.groupFantasmaY = randomEnemigosFantasma.nextInt(800) + (ALTO_PANTALLA);
                    fantasma1.groupFantasma.setLayoutX(randomEnemigosFantasma.nextInt(ANCHO_PANTALLA-(2*ANCHO_MUROS) - 30) + ANCHO_MUROS);
                    marcador = marcador + 10;
                    marcadorText.setText(String.valueOf(marcador));
                }
                
                if (fantasma2.groupFantasmaY<-30) {
                    fantasma2.groupFantasmaY = randomEnemigosFantasma.nextInt(800) + (ALTO_PANTALLA);
                    fantasma2.groupFantasma.setLayoutX(randomEnemigosFantasma.nextInt(ANCHO_PANTALLA-(2*ANCHO_MUROS) - 30) + ANCHO_MUROS);
                    marcador = marcador + 10;
                    marcadorText.setText(String.valueOf(marcador));
                }
                
                if (fantasma3.groupFantasmaY<-30) {
                    fantasma3.groupFantasmaY = randomEnemigosFantasma.nextInt(800) + (ALTO_PANTALLA);
                    fantasma3.groupFantasma.setLayoutX(randomEnemigosFantasma.nextInt(ANCHO_PANTALLA-(2*ANCHO_MUROS) - 30) + ANCHO_MUROS);
                    marcador = marcador + 10;
                    marcadorText.setText(String.valueOf(marcador));
                }
                
                if (fantasma4.groupFantasmaY<-30) {
                    fantasma4.groupFantasmaY = randomEnemigosFantasma.nextInt(800) + (ALTO_PANTALLA);
                    fantasma4.groupFantasma.setLayoutX(randomEnemigosFantasma.nextInt(ANCHO_PANTALLA-(2*ANCHO_MUROS) - 30) + ANCHO_MUROS);
                    marcador = marcador + 10;
                    marcadorText.setText(String.valueOf(marcador));
                }
                
                if (fantasma5.groupFantasmaY<-30) {
                    fantasma5.groupFantasmaY = randomEnemigosFantasma.nextInt(800) + (ALTO_PANTALLA);
                    fantasma5.groupFantasma.setLayoutX(randomEnemigosFantasma.nextInt(ANCHO_PANTALLA-(2*ANCHO_MUROS) - 30) + ANCHO_MUROS);
                    marcador = marcador + 10;
                    marcadorText.setText(String.valueOf(marcador));
                }
                
                if (fantasma6.groupFantasmaY<-30) {
                    fantasma6.groupFantasmaY = randomEnemigosFantasma.nextInt(800) + (ALTO_PANTALLA);
                    fantasma6.groupFantasma.setLayoutX(randomEnemigosFantasma.nextInt(ANCHO_PANTALLA-(2*ANCHO_MUROS) - 30) + ANCHO_MUROS);
                    marcador = marcador + 10;
                    marcadorText.setText(String.valueOf(marcador));
                }
                
                if (fantasma7.groupFantasmaY<-30) {
                    fantasma7.groupFantasmaY = randomEnemigosFantasma.nextInt(800) + (ALTO_PANTALLA);
                    fantasma7.groupFantasma.setLayoutX(randomEnemigosFantasma.nextInt(ANCHO_PANTALLA-(2*ANCHO_MUROS) - 30) + ANCHO_MUROS);
                    marcador = marcador + 10;
                    marcadorText.setText(String.valueOf(marcador));
                }
                
                if (fantasma8.groupFantasmaY<-30) {
                    fantasma8.groupFantasmaY = randomEnemigosFantasma.nextInt(800) + (ALTO_PANTALLA);
                    fantasma8.groupFantasma.setLayoutX(randomEnemigosFantasma.nextInt(ANCHO_PANTALLA-(2*ANCHO_MUROS) - 30) + ANCHO_MUROS);
                    marcador = marcador + 10;
                    marcadorText.setText(String.valueOf(marcador));
                }
                
                if (fantasmaRey.groupFantasmaY<-30) {
                    fantasmaRey.groupFantasmaY = randomEnemigosFantasma.nextInt(400) + (ALTO_PANTALLA*2);
                    fantasmaRey.groupFantasma.setLayoutX(groupMuñecoX);
                    marcador = marcador + 25;
                    marcadorText.setText(String.valueOf(marcador));
                }
                
                if (fantasmaRebote1.groupFantasmaY<-30) {
                    fantasmaRebote1.groupFantasmaY = randomEnemigosFantasma.nextInt(800) + (ALTO_PANTALLA);
                    fantasmaRebote1.groupFantasmaX = randomEnemigosFantasma.nextInt(ANCHO_PANTALLA-(2*ANCHO_MUROS)-90) + ANCHO_MUROS +30;
                    groupFantasmaRebote1XInicial = fantasmaRebote1.groupFantasmaX;
                    fantasmaRebote1.groupFantasma.setLayoutX(fantasmaRebote1.groupFantasmaX);
                    marcador = marcador + 20;
                    marcadorText.setText(String.valueOf(marcador));
                }
                
                if (fantasmaRebote2.groupFantasmaY<-30) {
                    fantasmaRebote2.groupFantasmaY = randomEnemigosFantasma.nextInt(800) + (ALTO_PANTALLA);
                    fantasmaRebote2.groupFantasmaX = randomEnemigosFantasma.nextInt(ANCHO_PANTALLA-(2*ANCHO_MUROS)-90) + ANCHO_MUROS +30;
                    groupFantasmaRebote2XInicial = fantasmaRebote2.groupFantasmaX;
                    fantasmaRebote2.groupFantasma.setLayoutX(fantasmaRebote2.groupFantasmaX);
                    marcador = marcador + 20;
                    marcadorText.setText(String.valueOf(marcador));
                }
                
                if (fantasmaRebote3.groupFantasmaY<-30) {
                    fantasmaRebote3.groupFantasmaY = randomEnemigosFantasma.nextInt(800) + (ALTO_PANTALLA);
                    fantasmaRebote3.groupFantasmaX = randomEnemigosFantasma.nextInt(ANCHO_PANTALLA-(2*ANCHO_MUROS)-90) + ANCHO_MUROS +30;
                    groupFantasmaRebote3XInicial = fantasmaRebote3.groupFantasmaX;
                    fantasmaRebote3.groupFantasma.setLayoutX(fantasmaRebote3.groupFantasmaX);
                    marcador = marcador + 20;
                    marcadorText.setText(String.valueOf(marcador));
                }
                
                if (fantasmaRebote1.groupFantasmaX>(groupFantasmaRebote1XInicial+30)) {
                    velocidadRebote1X = -3;
                }
                if (fantasmaRebote1.groupFantasmaX<(groupFantasmaRebote1XInicial-30)) {
                    velocidadRebote1X = 3;
                }
                
                if (fantasmaRebote2.groupFantasmaX>(groupFantasmaRebote2XInicial+30)) {
                    velocidadRebote2X = -3;
                }
                if (fantasmaRebote2.groupFantasmaX<(groupFantasmaRebote2XInicial-30)) {
                    velocidadRebote2X = 3;
                }
                
                if (fantasmaRebote3.groupFantasmaX>(groupFantasmaRebote3XInicial+30)) {
                    velocidadRebote3X = -3;
                }
                if (fantasmaRebote3.groupFantasmaX<(groupFantasmaRebote3XInicial-30)) {
                    velocidadRebote3X = 3;
                }
            };
        };
        
//-----------------Animación Ciudad---------------------------------------------------------------------------------------
        AnimationTimer animationCity = new AnimationTimer (){
            
            @Override
            @SuppressWarnings("empty-statement")
            public void handle (long now) {
                if (fondoCityY>-ALTO_PANTALLA*2.4){
                    imagenViewCity.setY(fondoCityY);
                    fondoCityY = fondoCityY - 0.4;
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
                if (imagenViewWallA_izquierda.getY() == -5){
                    imagenViewWallA2 = ALTO_PANTALLA;
                    imagenViewWallA_izquierda2.setY(imagenViewWallA2);
                    imagenViewWallA_derecha2.setY(imagenViewWallA2); 
                    imagenViewWallA = imagenViewWallA - 5;
                    imagenViewWallA_izquierda.setY(imagenViewWallA);
                    imagenViewWallA_derecha.setY(imagenViewWallA);
                }
                else{
                    imagenViewWallA = imagenViewWallA - 5;
                    imagenViewWallA_izquierda.setY(imagenViewWallA);
                    imagenViewWallA_derecha.setY(imagenViewWallA);
                };
                if (imagenViewWallA_izquierda2.getY() == -5){
                    imagenViewWallA = ALTO_PANTALLA;
                    imagenViewWallA_izquierda.setY(imagenViewWallA);
                    imagenViewWallA_derecha.setY(imagenViewWallA); 
                    imagenViewWallA2 = imagenViewWallA2 - 5;
                    imagenViewWallA_izquierda2.setY(imagenViewWallA2);
                    imagenViewWallA_derecha2.setY(imagenViewWallA2);
                }
                else{
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
                if (dificultad<=dificultadMax){
                    dificultad = (float) (dificultad + 0.0025);
                }
            };
        };
        
//-----------------Texto Derrota: Creación, posición, fuente (...)---------------------------------------------------------------------------------------
        Text derrota = new Text ("Fin de la Partida");
        derrota.setFont(Font.font(ANCHO_PANTALLA/10));
        derrota.setX(100);
        derrota.setY(200);
        derrota.setFill(Color.ORANGE);
        derrota.setVisible(false);
        
//-----------------Texto Puntuación: Creación, posición, fuente (...)---------------------------------------------------------------------------------------
        Text score = new Text ("Puntuación:");
        score.setFont(Font.font(25));
        score.setX(ANCHO_PANTALLA-ANCHO_MUROS);
        score.setY(25);
        score.setFill(Color.ORANGE);
        Rectangle rectangleScore = new Rectangle(ANCHO_PANTALLA-ANCHO_MUROS, 0, ANCHO_MUROS, 75);

//-----------------Texto Puntuación: Creación, posición, fuente (...)---------------------------------------------------------------------------------------
        Text pressEnter = new Text ("Press Enter to retry");
        pressEnter.setFont(Font.font(ANCHO_PANTALLA/20));
        pressEnter.setX(20);
        pressEnter.setY(50);
        pressEnter.setFill(Color.GREEN);
        pressEnter.setVisible(false);
        
//-----------------Escenario (Pane)---------------------------------------------------------------------------------------
        Pane root = new Pane();
            root.getChildren().addAll(imagenViewCity, imagenViewWallA_derecha, 
                    imagenViewWallA_izquierda, imagenViewWallA_derecha2, 
                    imagenViewWallA_izquierda2, rectangleVida, cajaVidas, groupMuñeco,
                    fantasmaRebote1.groupFantasma, fantasmaRebote2.groupFantasma, fantasmaRebote3.groupFantasma,
                    fantasma1.groupFantasma, fantasma2.groupFantasma, fantasma3.groupFantasma, fantasma4.groupFantasma,
                    fantasma5.groupFantasma, fantasma6.groupFantasma, fantasma7.groupFantasma, fantasma8.groupFantasma,
                    fantasmaRey.groupFantasma, rectangleScore, rectangleFinal, score, marcadorText, derrota, pressEnter);

//-----------------Inicio Escena---------------------------------------------------------------------------------------
        Scene scene = new Scene(root, ANCHO_PANTALLA, ALTO_PANTALLA);
        
//-----------------Detección de teclas pulsadas---------------------------------------------------------------------------------------
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
                    case ENTER:
                        if (!estarVivo){
                            
                            reinicio();
                            groupMuñeco.setLayoutX(groupMuñecoX);
                            imagenViewCity.setY(fondoCityY);
                            cajaVidas.getChildren().addAll(imagenViewVida1, imagenViewVida2);
                            marcadorText.setText(String.valueOf(marcador));
                            estarVivo = true;
                            score.setFont(Font.font(25));
                            score.setX(ANCHO_PANTALLA-ANCHO_MUROS);
                            score.setY(25);
                            marcadorText.setFont(Font.font(25));
                            marcadorText.setX(ANCHO_PANTALLA-ANCHO_MUROS);
                            marcadorText.setY(60);
                            rectangleFinal.setVisible(false);
                            pressEnter.setVisible(false);
                            derrota.setVisible(false);
                        
                            fantasma1.groupFantasmaY = 1200;
                            fantasma2.groupFantasmaY = 1400;
                            fantasma3.groupFantasmaY = 1700;
                            fantasma4.groupFantasmaY = 2200;
                            fantasma5.groupFantasmaY = 2700;
                            fantasma6.groupFantasmaY = 3200;
                            fantasma7.groupFantasmaY = 3700;
                            fantasma8.groupFantasmaY = 4200;
                            fantasmaRey.groupFantasmaY = 3000;
                            fantasmaRebote1.groupFantasmaY = 2500;
                            fantasmaRebote2.groupFantasmaY = 3300;
                            fantasmaRebote3.groupFantasmaY = 3900;

                            fantasma1.groupFantasma.setLayoutY(fantasma1.groupFantasmaY);
                            fantasma2.groupFantasma.setLayoutY(fantasma2.groupFantasmaY);
                            fantasma3.groupFantasma.setLayoutY(fantasma3.groupFantasmaY);
                            fantasma4.groupFantasma.setLayoutY(fantasma4.groupFantasmaY);
                            fantasma5.groupFantasma.setLayoutY(fantasma5.groupFantasmaY);
                            fantasma6.groupFantasma.setLayoutY(fantasma6.groupFantasmaY);
                            fantasma7.groupFantasma.setLayoutY(fantasma7.groupFantasmaY);
                            fantasma8.groupFantasma.setLayoutY(fantasma8.groupFantasmaY);
                            fantasmaRey.groupFantasma.setLayoutY(fantasmaRey.groupFantasmaY);
                            fantasmaRebote1.groupFantasma.setLayoutY(fantasmaRebote1.groupFantasmaY);
                            fantasmaRebote2.groupFantasma.setLayoutY(fantasmaRebote2.groupFantasmaY);
                            fantasmaRebote3.groupFantasma.setLayoutY(fantasmaRebote3.groupFantasmaY);                                        

                            fantasmaRebote1.groupFantasma.setLayoutX(fantasmaRebote1.groupFantasmaX);                                        
                            fantasmaRebote2.groupFantasma.setLayoutX(fantasmaRebote2.groupFantasmaX);
                            fantasmaRebote3.groupFantasma.setLayoutX(fantasmaRebote3.groupFantasmaX);

                            fantasma1.groupFantasmaX = randomEnemigosFantasma.nextInt(ANCHO_PANTALLA-(2*ANCHO_MUROS) - 30) + ANCHO_MUROS;
                            fantasma2.groupFantasmaX = randomEnemigosFantasma.nextInt(ANCHO_PANTALLA-(2*ANCHO_MUROS) - 30) + ANCHO_MUROS;
                            fantasma3.groupFantasmaX = randomEnemigosFantasma.nextInt(ANCHO_PANTALLA-(2*ANCHO_MUROS) - 30) + ANCHO_MUROS;
                            fantasma4.groupFantasmaX = randomEnemigosFantasma.nextInt(ANCHO_PANTALLA-(2*ANCHO_MUROS) - 30) + ANCHO_MUROS;
                            fantasma5.groupFantasmaX = randomEnemigosFantasma.nextInt(ANCHO_PANTALLA-(2*ANCHO_MUROS) - 30) + ANCHO_MUROS;
                            fantasma6.groupFantasmaX = randomEnemigosFantasma.nextInt(ANCHO_PANTALLA-(2*ANCHO_MUROS) - 30) + ANCHO_MUROS;
                            fantasma7.groupFantasmaX = randomEnemigosFantasma.nextInt(ANCHO_PANTALLA-(2*ANCHO_MUROS) - 30) + ANCHO_MUROS;
                            fantasma8.groupFantasmaX = randomEnemigosFantasma.nextInt(ANCHO_PANTALLA-(2*ANCHO_MUROS) - 30) + ANCHO_MUROS;
                            fantasmaRebote1.groupFantasmaX = randomEnemigosFantasma.nextInt(ANCHO_PANTALLA-(2*ANCHO_MUROS)-90) + ANCHO_MUROS + 30;
                            fantasmaRebote2.groupFantasmaX = randomEnemigosFantasma.nextInt(ANCHO_PANTALLA-(2*ANCHO_MUROS)-90) + ANCHO_MUROS + 30;
                            fantasmaRebote3.groupFantasmaX = randomEnemigosFantasma.nextInt(ANCHO_PANTALLA-(2*ANCHO_MUROS)-90) + ANCHO_MUROS + 30;

                            groupFantasmaRebote1XInicial = fantasmaRebote1.groupFantasmaX;
                            groupFantasmaRebote2XInicial = fantasmaRebote2.groupFantasmaX;
                            groupFantasmaRebote3XInicial = fantasmaRebote3.groupFantasmaX;

                            fantasma1.groupFantasma.setLayoutX(fantasma1.groupFantasmaX);
                            fantasma2.groupFantasma.setLayoutX(fantasma2.groupFantasmaX);
                            fantasma3.groupFantasma.setLayoutX(fantasma3.groupFantasmaX);
                            fantasma4.groupFantasma.setLayoutX(fantasma4.groupFantasmaX);
                            fantasma5.groupFantasma.setLayoutX(fantasma5.groupFantasmaX);
                            fantasma6.groupFantasma.setLayoutX(fantasma6.groupFantasmaX);
                            fantasma7.groupFantasma.setLayoutX(fantasma7.groupFantasmaX);
                            fantasma8.groupFantasma.setLayoutX(fantasma8.groupFantasmaX);
                            fantasmaRey.groupFantasma.setLayoutX(groupMuñecoX);

                            derrota.setVisible(false);
                            animationDificultad.start();
                            animationWall.start();
                            animationCity.start();
                            animationMuñeco.start();
                            animationFantasma.start();
                            mediaPlayer.play();
                        }
                        break;
                }
            }
        });

//-----------------Animación choque---------------------------------------------------------------------------------------
        AnimationTimer animationChoque = new AnimationTimer (){
            
            @Override
            public void handle (long now) {
                Shape shapeCollision1 = Shape.intersect(rectangleBoy, rectangleDerecha);
                boolean colisionShape1 = shapeCollision1.getBoundsInLocal().isEmpty();
                if (colisionShape1 == false){
                    if (numVidas == 0){
                        reiniciar();
                    }
                    else {
                        vidaMenos();
                        groupMuñecoX = (ANCHO_PANTALLA-32)/2;
                    }                
                };
                          
                Shape shapeCollision2 = Shape.intersect(rectangleBoy, rectangleIzquierda);
                boolean colisionShape2 = shapeCollision2.getBoundsInLocal().isEmpty();
                if (colisionShape2 == false){
                    if (numVidas == 0){
                        reiniciar();
                    }
                    else {
                        vidaMenos();
                        groupMuñecoX = (ANCHO_PANTALLA-32)/2;
                    }                
                };
                         
                Shape shapeCollision3 = Shape.intersect(rectangleBoy, fantasma1.circleFantasma);
                boolean colisionShape3 = shapeCollision3.getBoundsInLocal().isEmpty();
                if (colisionShape3 == false){
                    if (numVidas == 0){      
                        reiniciar();
                    }
                    else {
                        vidaMenos();
                        fantasma1.groupFantasmaY=2000;
                    }                
                };
                
                Shape shapeCollision4 = Shape.intersect(rectangleBoy, fantasma2.circleFantasma);
                boolean colisionShape4 = shapeCollision4.getBoundsInLocal().isEmpty();
                if (colisionShape4 == false){
                    if (numVidas == 0){        
                        reiniciar();
                    }
                    else {
                        vidaMenos();
                        fantasma2.groupFantasmaY=2000;
                    }                
                };
                
                Shape shapeCollision5 = Shape.intersect(rectangleBoy, fantasma3.circleFantasma);
                boolean colisionShape5 = shapeCollision5.getBoundsInLocal().isEmpty();
                if (colisionShape5 == false){
                    if (numVidas == 0){      
                        reiniciar();
                    }
                    else {
                        vidaMenos();
                        fantasma3.groupFantasmaY=2000;
                    }                
                };
                
                Shape shapeCollision6 = Shape.intersect(rectangleBoy, fantasma4.circleFantasma);
                boolean colisionShape6 = shapeCollision6.getBoundsInLocal().isEmpty();
                if (colisionShape6 == false){
                    if (numVidas == 0){            
                        reiniciar();
                    }
                    else {
                        vidaMenos();
                        fantasma4.groupFantasmaY=2000;
                    }                
                };
                
                Shape shapeCollision7 = Shape.intersect(rectangleBoy, fantasma5.circleFantasma);
                boolean colisionShape7 = shapeCollision7.getBoundsInLocal().isEmpty();
                if (colisionShape7 == false){
                    if (numVidas == 0){
                        reiniciar();
                    }
                    else {
                        vidaMenos();
                        fantasma5.groupFantasmaY=2000;
                    }                
                };
                
                Shape shapeCollision8 = Shape.intersect(rectangleBoy, fantasma6.circleFantasma);
                boolean colisionShape8 = shapeCollision8.getBoundsInLocal().isEmpty();
                if (colisionShape8 == false){
                    if (numVidas == 0){
                        reiniciar();
                    }
                    else {
                        vidaMenos();
                        fantasma6.groupFantasmaY=2000;
                    }                
                };
                
                Shape shapeCollision9 = Shape.intersect(rectangleBoy, fantasma7.circleFantasma);
                boolean colisionShape9 = shapeCollision9.getBoundsInLocal().isEmpty();
                if (colisionShape9 == false){
                    if (numVidas == 0){            
                        reiniciar();
                    }
                    else {
                        vidaMenos();
                        fantasma7.groupFantasmaY=2000;
                    }                
                };
                
                Shape shapeCollision10 = Shape.intersect(rectangleBoy, fantasma8.circleFantasma);
                boolean colisionShape10 = shapeCollision10.getBoundsInLocal().isEmpty();
                if (colisionShape10 == false){
                    if (numVidas == 0){       
                        reiniciar();
                    }
                    else {
                        vidaMenos();
                        fantasma8.groupFantasmaY=2000;
                    }                
                };
                
                Shape shapeCollision11 = Shape.intersect(rectangleBoy, fantasmaRey.circleFantasma);
                boolean colisionShape11 = shapeCollision11.getBoundsInLocal().isEmpty();
                if (colisionShape11 == false){
                    if (numVidas == 0){        
                        reiniciar();
                    }
                    else {
                        vidaMenos();
                        fantasmaRey.groupFantasmaY=2000;
                    }
                };
                
                Shape shapeCollision12 = Shape.intersect(rectangleBoy, fantasmaRebote1.circleFantasma);
                boolean colisionShape12 = shapeCollision12.getBoundsInLocal().isEmpty();
                if (colisionShape12 == false){
                    if (numVidas == 0){ 
                        reiniciar();
                    }
                    else {
                        vidaMenos();
                        fantasmaRebote1.groupFantasmaY=2000;
                    }                
                };
                
                Shape shapeCollision13 = Shape.intersect(rectangleBoy, fantasmaRebote2.circleFantasma);
                boolean colisionShape13 = shapeCollision13.getBoundsInLocal().isEmpty();
                if (colisionShape13 == false){
                    if (numVidas == 0){
                        reiniciar();
                    }
                    else {
                        vidaMenos();
                        fantasmaRebote2.groupFantasmaY=2000;
                    }                
                };
                
                Shape shapeCollision14 = Shape.intersect(rectangleBoy, fantasmaRebote3.circleFantasma);
                boolean colisionShape14 = shapeCollision14.getBoundsInLocal().isEmpty();
                if (colisionShape14 == false){
                    if (numVidas == 0){
                        reiniciar();
                    }
                    else {
                        vidaMenos();
                        fantasmaRebote3.groupFantasmaY=2000;
                    }                
                };
            };

            private void reiniciar() {
                animationDificultad.stop();
                animationWall.stop();
                animationCity.stop();
                animationMuñeco.stop();
                animationFantasma.stop();
                mediaPlayer.stop();
                derrota.setVisible(true);
                estarVivo = false;
                score.setFont(Font.font(ANCHO_PANTALLA/15));
                score.setX(ANCHO_PANTALLA/4);
                score.setY(ALTO_PANTALLA/2);
                marcadorText.setFont(Font.font(ANCHO_PANTALLA/15));
                marcadorText.setX(ANCHO_PANTALLA*3/4);
                marcadorText.setY(ALTO_PANTALLA/2);
                rectangleFinal.setVisible(true);
                pressEnter.setVisible(true);
                derrota.setVisible(true);
            }

            private void vidaMenos() {
                        numVidas--;
                        cajaVidas.getChildren().remove(numVidas);
            }
        };
        
//-----------------Inicio de animaciones---------------------------------------------------------------------------------------
        animationDificultad.start();
        animationChoque.start();
        animationWall.start();
        animationCity.start();
        animationMuñeco.start();
        animationFantasma.start();
        
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
        groupMuñecoX = (ANCHO_PANTALLA-32)/2;
        imagenViewWallA = 0;
        imagenViewWallA2 = -ALTO_PANTALLA;
        velocidad = 0;
        velocidadCity = 0;
        fondoCityY = 0;
        groupCityX = 0;
        dificultad = dificultadMin;
        numVidas = 2;
        marcador = 0;
    }
}