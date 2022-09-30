/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package INTERFAZ;
        
import java.awt.Color;
import java.awt.Graphics;


/**
 *
 * @author Lenovo
 */
public class Dibujar {
    
    public static void Cuadrado(Graphics g,String color,int y1, int y2) {
        int[] xPoints = new int[4]; // Las coordenadas funcionan en Arrays
        int[] yPoints = new int[4];
        xPoints[0] = 22;  // Inferior izquierda
        xPoints[1] = 22;  // Superior izquierda
        xPoints[2] = 72; // Superior derecha
        xPoints[3] = 72; // Inferior derecha
        yPoints[0] = y1;  // Inferior izquierda
        yPoints[1] = y2; // Superior izquierda
        yPoints[2] = y2; // Superior derecha
        yPoints[3] = y1;  // Inferior derecha
        if(color.equals("Rojo")){
            g.setColor(new Color(255, 0, 0));
        }else if(color.equals("Azul")){
            g.setColor(new Color(0, 0, 255));
        }else if(color.equals("Verde")){
            g.setColor(new Color(0, 255, 0));
        }else if(color.equals("Blanco")){
            g.setColor(new Color(255, 255, 210));
        }else if(color.equals("Negro")){
            g.setColor(new Color(0, 0, 0));
        }
        g.fillPolygon(xPoints, yPoints, 4); // El último valor es el número de puntos del polígono
    }

    public static void Triangulo(Graphics g,String color,int y1,int y2) {
        int[] xPoints = new int[3];
        int[] yPoints = new int[3];
        xPoints[0] = 22;  // Inferior izquierda
        xPoints[1] = 47; // Superior
        xPoints[2] = 72; // Inferior derecha
        yPoints[0] = y2; // Inferior izquierda
        yPoints[1] = y1;  // Superior
        yPoints[2] = y2; // Superior derecha

        if(color.equals("Rojo")){
            g.setColor(new Color(255, 0, 0));
        }else if(color.equals("Azul")){
            g.setColor(new Color(0, 0, 255));
        }else if(color.equals("Verde")){
            g.setColor(new Color(0, 255, 0));
        }else if(color.equals("Blanco")){
            g.setColor(new Color(255, 255, 210));
        }else if(color.equals("Negro")){
            g.setColor(new Color(0, 0, 0));
        }
        g.fillPolygon(xPoints, yPoints, 3);
    }

    public static void Rectangulo(Graphics g,String color,int y1,int y2) {
        int[] xPoints = new int[4];
        int[] yPoints = new int[4];
        xPoints[0] = 22;  // Inferior izquierda
        xPoints[1] = 22;  // Superior izquierda
        xPoints[2] = 72; // Superior derecha
        xPoints[3] = 72; // Inferior derecha
        yPoints[0] = y1;  // Inferior izquierda
        yPoints[1] = y2; // Superior izquierda
        yPoints[2] = y2; // Superior derecha
        yPoints[3] = y1; // Inferior derecha

        if(color.equals("Rojo")){
            g.setColor(new Color(255, 0, 0));
        }else if(color.equals("Azul")){
            g.setColor(new Color(0, 0, 255));
        }else if(color.equals("Verde")){
            g.setColor(new Color(0, 255, 0));
        }else if(color.equals("Blanco")){
            g.setColor(new Color(255, 255, 210));
        }else if(color.equals("Negro")){
            g.setColor(new Color(0, 0, 0));
        }
        g.fillPolygon(xPoints, yPoints, 4);
    }

    public static void Rombo(Graphics g,String color,int y1,int y2,int y3) {
        int[] xPoints = new int[4];
        int[] yPoints = new int[4];
        xPoints[0] = 48; // Inferior
        xPoints[1] = 22;  // Izquierda
        xPoints[2] = 48; // Superior
        xPoints[3] = 72; // Derecha
        yPoints[0] = y1;  // Inferior
        yPoints[1] = y2; // Izquierda
        yPoints[2] = y3; // Superior
        yPoints[3] = y2;  // Derecha

        if(color.equals("Rojo")){
            g.setColor(new Color(255, 0, 0));
        }else if(color.equals("Azul")){
            g.setColor(new Color(0, 0, 255));
        }else if(color.equals("Verde")){
            g.setColor(new Color(0, 255, 0));
        }else if(color.equals("Blanco")){
            g.setColor(new Color(255, 255, 210));
        }else if(color.equals("Negro")){
            g.setColor(new Color(0, 0, 0));
        }
        g.fillPolygon(xPoints, yPoints, 4);
    }

    public static void Circulo(Graphics g,String color,int y) {
        if(color.equals("Rojo")){
            g.setColor(new Color(255, 0, 0));
        }else if(color.equals("Azul")){
            g.setColor(new Color(0, 0, 255));
        }else if(color.equals("Verde")){
            g.setColor(new Color(0, 255, 0));
        }else if(color.equals("Blanco")){
            g.setColor(new Color(255, 255, 210));
        }else if(color.equals("Negro")){
            g.setColor(new Color(0, 0, 0));
        }
        g.fillOval(22, y, 50, 50); // Coordenada X, Coordenada Y de la esquina superior izquierda del círculo
    }                             // Los 50 son, respectivamente ancho y alto
    
}

