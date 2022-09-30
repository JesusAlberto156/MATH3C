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
    public static void Cuadrado(Graphics g) {
        int[] xPoints = new int[4]; // Las coordenadas funcionan en Arrays
        int[] yPoints = new int[4];
        xPoints[0] = 2;  // Inferior izquierda
        xPoints[1] = 2;  // Superior izquierda
        xPoints[2] = 50; // Superior derecha
        xPoints[3] = 50; // Inferior derecha
        yPoints[0] = 2;  // Inferior izquierda
        yPoints[1] = 50; // Superior izquierda
        yPoints[2] = 50; // Superior derecha
        yPoints[3] = 2;  // Inferior derecha

        g.setColor(Color.red);
        g.fillPolygon(xPoints, yPoints, 4); // El último valor es el número de puntos del polígono
    }

    public static void Triangulo(Graphics g) {
        int[] xPoints = new int[3];
        int[] yPoints = new int[3];
        xPoints[0] = 2;  // Inferior izquierda
        xPoints[1] = 25; // Superior
        xPoints[2] = 50; // Inferior derecha
        yPoints[0] = 50; // Inferior izquierda
        yPoints[1] = 2;  // Superior
        yPoints[2] = 50; // Superior derecha

        g.setColor(Color.blue);
        g.fillPolygon(xPoints, yPoints, 3);
    }

    public static void Rectangulo(Graphics g) {
        int[] xPoints = new int[4];
        int[] yPoints = new int[4];
        xPoints[0] = 2;  // Inferior izquierda
        xPoints[1] = 2;  // Superior izquierda
        xPoints[2] = 50; // Superior derecha
        xPoints[3] = 50; // Inferior derecha
        yPoints[0] = 2;  // Inferior izquierda
        yPoints[1] = 30; // Superior izquierda
        yPoints[2] = 30; // Superior derecha
        yPoints[3] = 2; // Inferior derecha

        g.setColor(Color.green);
        g.fillPolygon(xPoints, yPoints, 4);
    }

    public static void Rombo(Graphics g) {
        int[] xPoints = new int[4];
        int[] yPoints = new int[4];
        xPoints[0] = 25; // Inferior
        xPoints[1] = 2;  // Izquierda
        xPoints[2] = 25; // Superior
        xPoints[3] = 50; // Derecha
        yPoints[0] = 2;  // Inferior
        yPoints[1] = 25; // Izquierda
        yPoints[2] = 50; // Superior
        yPoints[3] = 25;  // Derecha

        g.setColor(Color.white);
        g.fillPolygon(xPoints, yPoints, 4);
    }

    public static void Circulo(Graphics g) {
        g.setColor(Color.black);
        g.fillOval(2, 2, 50, 50); // Coordenada X, Coordenada Y de la esquina superior izquierda del círculo
    }                             // Los 50 son, respectivamente ancho y alto
    
}

