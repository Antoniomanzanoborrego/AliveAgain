/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aliveagain.AliveAgain;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;


/**
 *
 * @author Antonio Manzano Borrego
 */
public class Fantasma extends Group {
    
    int groupFantasmaX = 0;
    float groupFantasmaY = 0;
    Circle circleFantasmaD = new Circle(22, 15, 6, Color.RED);
    Circle circleFantasmaI = new Circle(5, 15, 2, Color.RED);
    Circle circleFantasma = new Circle(15, 15, 15, Color.DARKGRAY);
    Rectangle rectangleFantasma = new Rectangle (0,15,30,15);
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
    Group groupFantasma = new Group ();
        
    public Fantasma() {
        polygon1Fantasma.setFill(Color.DARKGRAY);
        polygon2Fantasma.setFill(Color.DARKGRAY);
        polygon3Fantasma.setFill(Color.DARKGRAY);
        polygon4Fantasma.setFill(Color.DARKGRAY);
        polygon5Fantasma.setFill(Color.DARKGRAY);
        polygon6Fantasma.setFill(Color.DARKGRAY);
        rectangleFantasma.setFill(Color.DARKGRAY);
        this.getChildren().addAll(polygon1Fantasma, polygon2Fantasma, polygon3Fantasma, polygon4Fantasma, polygon5Fantasma, polygon6Fantasma, rectangleFantasma, circleFantasma, circleFantasmaD, circleFantasmaI);
    }
}