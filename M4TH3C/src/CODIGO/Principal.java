/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CODIGO;

import INTERFAZ.*;
import com.formdev.flatlaf.FlatIntelliJLaf;
import java.io.*;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
//import jflex.exceptions.SilentExit;
 /*
 * @author Carlos
 */
public class Principal {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            System.out.println("LookAndFeel no soportado: " + ex);
        }
        Interfaz main = new Interfaz();
        main.setVisible(true);
    }
    /*
    public static void generateLexer(String ruta) throws SilentExit{
        File file = new File(ruta, "lexer");
        jflex.Main.generate(file.list());
    }*/
}
