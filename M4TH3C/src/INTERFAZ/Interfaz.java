/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package INTERFAZ;

import CODIGO.*;
import com.formdev.flatlaf.FlatIntelliJLaf;
import compilerTools.CodeBlock;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import compilerTools.Directory;
import compilerTools.ErrorLSSL;
import compilerTools.Functions;
import compilerTools.Grammar;
import compilerTools.Production;
import compilerTools.TextColor;
import compilerTools.Token;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author chuy4
 */
public class Interfaz extends javax.swing.JFrame {

    private String title;
    private ArrayList<Token> tokens;
    private ArrayList<ErrorLSSL> errors;
    private ArrayList<TextColor> textsColor;
    private Timer timerKeyReleased;
    private ArrayList<Production> identProdD1;
    private ArrayList<Production> identProdD2;
    private ArrayList<Production> identProdA1;
    private ArrayList<Production> identProdF11;
    private ArrayList<Production> identProdF12;
    private ArrayList<Production> identProdF13;
    private ArrayList<Production> identProdF14;
    private ArrayList<Production> identProdF2;
    private ArrayList<Production> identProdF3;
    private ArrayList<Production> identProdM1;
    private ArrayList<Production> identProdM2;
    private HashMap<String, String> identDataType;
    private HashMap<String, String> identDataTypeV;
    private HashMap<String, String> identDataTypeE;
    private HashMap<String, String> identDataTypeR;
    private HashMap<String, String> identDataTypeR2;
    private HashMap<String, String> identDataTypeV2;
    private HashMap<String, String> identDataTypeV3;
    private HashMap<String, String> identDataTemp;
    private HashMap<String, String> identDataMetodo;
    private boolean codeHasBeenCompiled = false;
    private Directory directorio;
    private Tokens T = new Tokens();
    private Tripletas Tr = new Tripletas();
    private Cuadruplos C = new Cuadruplos();
    private Objeto co = new Objeto();
    private ArrayList<Production> Metodo;
    private Object[] cuadruplo;
    private ArrayList<String> CodigoObjeto;
    private ArrayList<String> Variables;
    
    JFileChooser selecciona = new JFileChooser();
    File archivo;
    FileInputStream entrada;
    FileOutputStream salida;
    /**
     * Creates new form Interfaz
     */
    public Interfaz() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("M4TH3C - PRINCIPAL");
        ImageIcon img = new ImageIcon("src/IMAGENES/M4TH3C.png");
        ImageIcon img2 = new ImageIcon("src/IMAGENES/emu8086.png");
        Icon icono1 = new ImageIcon(img.getImage().getScaledInstance(jLabel4.getWidth(), jLabel4.getHeight(), Image.SCALE_SMOOTH));
        Icon icono2 = new ImageIcon(img.getImage().getScaledInstance(jLabel5.getWidth(), jLabel5.getHeight(), Image.SCALE_SMOOTH));
        Icon icono3 = new ImageIcon(img2.getImage().getScaledInstance(jLabel8.getWidth(), jLabel8.getHeight(), Image.SCALE_SMOOTH));
        jLabel4.setIcon(icono1);
        jLabel5.setIcon(icono2);
        jLabel8.setIcon(icono3);
        
        init();
    }

    private void init() {
        
        Functions.setLineNumberOnJTextComponent(PanelFuente);
        directorio = new Directory(this, PanelFuente, title, ".MTC");
        addWindowListener(new WindowAdapter() {// Cuando presiona la "X" de la esquina superior derecha
            @Override
            public void windowClosing(WindowEvent e) {
                directorio.Exit();
                System.exit(0);
            }
        });
        Functions.setLineNumberOnJTextComponent(PanelFuente); //Pone los numeros de linea
        timerKeyReleased = new Timer((int) (1000 * 0.3), (ActionEvent e) -> {
            timerKeyReleased.stop();
            int posicion = PanelFuente.getCaretPosition();
            PanelFuente.setText(PanelFuente.getText().replaceAll("[\r]+", ""));
            PanelFuente.setCaretPosition(posicion);

            colorAnalysis();

        });

        Functions.insertAsteriskInName(this, PanelFuente, () -> {
            timerKeyReleased.restart();
        });
        tokens = new ArrayList<>();
        errors = new ArrayList<>();
        Metodo = new ArrayList<>();
        textsColor = new ArrayList<>();
        identProdD1 = new ArrayList<>();
        identProdD2 = new ArrayList<>();
        identProdA1 = new ArrayList<>();
        identProdF11 = new ArrayList<>();
        identProdF12 = new ArrayList<>();
        identProdF13 = new ArrayList<>();
        identProdF14 = new ArrayList<>();
        identProdF2 = new ArrayList<>();
        identProdF3 = new ArrayList<>();
        identProdM1 = new ArrayList<>();
        identProdM2 = new ArrayList<>();
        identDataType = new HashMap<>();
        identDataTypeV = new HashMap<>();
        identDataTypeE = new HashMap<>();
        identDataTypeR = new HashMap<>();
        identDataTypeR2 = new HashMap<>();
        identDataTypeV2 = new HashMap<>();
        identDataTypeV3 = new HashMap<>();
        identDataTemp = new HashMap<>();
        identDataMetodo = new HashMap<>();
        cuadruplo = new Object[4];
        CodigoObjeto = new ArrayList<>();
        Variables = new ArrayList<>();
        
        Functions.setAutocompleterJTextComponent(new String[]{"Sumar('VARIABLES')<\n\t      'Variable Resultado'\n>;",
            "Restar('VARIABLES')<\n\t      'Variable Resultado'\n>;", "Multiplicar('VARIABLES')<\n\t      'Variable Resultado'\n>;",
            "Dividir('VARIABLES')<\n\t      'Variable Resultado'\n>;", "Entero 'Nombre Variable' = 'Valor';", "Entero 'Nombre Variable';",
            "Decimal 'Nombre Variable' = 'Valor';", "Decimal 'Nombre Variable';", "Resultado 'Nombre Variable';",
            "Cadena 'Nombre Variable' = 'Valor';", "Cadena 'Nombre Variable';", "Figura 'Nombre Variable' = 'Valor';",
            "Figura 'Nombre Variable';", "Color 'Nombre Variable' = 'Valor';", "Color 'Nombre Variable';",
            "Mostrar(‘Variable Cadena’ , ‘Variable Resultado’ , ‘Variable Figura’ , ‘Variable Color’);", "Mostrar(‘Variable Cadena’ , ‘Variable Resultado’ , "
            + "‘Variable Figura 1’ , ‘Variable Color 1’, ‘Variable Figura 2’ , ‘Variable Color 2’, ‘Variable Figura 3’ , ‘Variable Color 3, ‘Variable Figura 4’ , "
            + "‘Variable Color 4’, ‘Variable Figura 5’ , ‘Variable Color 5);", "Metodo 'Nombre Metodo' {\n\t      'Funciones o Variables'\n}",
            "?Sumar('VARIABLES')<\n\t      'Variable Resultado'\n>;",
            "?Restar('VARIABLES')<\n\t      'Variable Resultado'\n>;", "?Multiplicar('VARIABLES')<\n\t      'Variable Resultado'\n>;",
            "?Dividir('VARIABLES')<\n\t      'Variable Resultado'\n>;", "?Entero 'Nombre Variable' = 'Valor';", "?Entero 'Nombre Variable';",
            "?Decimal 'Nombre Variable' = 'Valor';", "?Decimal 'Nombre Variable';", "?Resultado 'Nombre Variable';",
            "?Cadena 'Nombre Variable' = 'Valor';", "?Cadena 'Nombre Variable';", "?Figura 'Nombre Variable' = 'Valor';",
            "?Figura 'Nombre Variable';", "?Color 'Nombre Variable' = 'Valor';", "?Color 'Nombre Variable';",
            "?Mostrar(‘Variable Cadena’ , ‘Variable Resultado’ , ‘Variable Figura’ , ‘Variable Color’);", "?Mostrar(‘Variable Cadena’ , ‘Variable Resultado’ , "
            + "‘Variable Figura 1’ , ‘Variable Color 1’, ‘Variable Figura 2’ , ‘Variable Color 2’, ‘Variable Figura 3’ , ‘Variable Color 3, ‘Variable Figura 4’ , "
            + "‘Variable Color 4’, ‘Variable Figura 5’ , ‘Variable Color 5);", "?Metodo 'Nombre Metodo' {\n\t      'Funciones o Variables'\n}",
            "?'Nombre Variable' = 'Valor';"}, PanelFuente, () -> {
            timerKeyReleased.restart();
            timerKeyReleased.start();
        });
    }
    
    private void clearFields() {
        Functions.clearDataInTable(Tokens);
        Functions.clearDataInTable(T.Tabla());
        Functions.clearDataInTable(Cuadruplos);
        Functions.clearDataInTable(C.Tabla());
        PanelSalida.setText("");
        Tripletas.setText("");
        Tr.RellenarTripletas("");
        CodObjeto.setText("");
        co.rellenaObjeto("");
        tokens.clear();
        errors.clear();
        Metodo.clear();
        identProdD1.clear();
        identProdD2.clear();
        identProdA1.clear();
        identProdF11.clear();
        identProdF12.clear();
        identProdF13.clear();
        identProdF14.clear();
        identProdF2.clear();
        identProdF3.clear();
        identProdM1.clear();
        identProdM2.clear();
        identDataType.clear();
        identDataTypeV.clear();
        identDataTypeE.clear();
        identDataTypeR.clear();
        identDataTypeR2.clear();
        identDataTypeV2.clear();
        identDataTypeV3.clear();
        identDataTemp.clear();
        identDataMetodo.clear();
        CodigoObjeto.clear();
        Variables.clear();
        codeHasBeenCompiled = false;
    }

    private void colorAnalysis() {
        /* Limpiar el arreglo de colores */
        textsColor.clear();
        /* Extraer rangos de colores */
        LexerColor lexerColor;
        try {
            File codigo = new File("color.encrypter");
            FileOutputStream output = new FileOutputStream(codigo);
            byte[] bytesText = PanelFuente.getText().getBytes();
            output.write(bytesText);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(new FileInputStream(codigo), "UTF8"));
            lexerColor = new LexerColor(entrada);
            while (true) {
                TextColor textColor = lexerColor.yylex();
                if (textColor == null) {
                    break;
                }
                textsColor.add(textColor);
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this,"El archivo no pudo ser encontrado... " + ex.getMessage(),"¡ERROR!",JOptionPane.ERROR_MESSAGE);
            System.out.println("El archivo no pudo ser encontrado... " + ex.getMessage());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,"Error al escribir en el archivo... " + ex.getMessage(),"¡ERROR!",JOptionPane.ERROR_MESSAGE);
            System.out.println("Error al escribir en el archivo... " + ex.getMessage());
        }
        Functions.colorTextPane(textsColor, PanelFuente, new Color(40, 40, 40));
    }

    private void compilar() {
        clearFields();
        lexicalAnalysis();
        fillTableTokens();
        syntacticAnalysis();
        semanticAnalysis();
        printConsole();
        codeHasBeenCompiled = true;
    }

    private void lexicalAnalysis() {

        try {
            // Extraer tokens
            Lexer lexer;
            File codigo = new File("code.encrypter");
            FileOutputStream output = new FileOutputStream(codigo);
            byte[] bytesText = PanelFuente.getText().getBytes();
            output.write(bytesText);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(new FileInputStream(codigo), "UTF8"));
            lexer = new Lexer(entrada);
            while (true) {
                Token token = lexer.yylex();
                if (token == null) {
                    break;

                }
                tokens.add(token);
                
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this,"El archivo no pudo ser encontrado... " + ex.getMessage(),"¡ERROR!",JOptionPane.ERROR_MESSAGE);
            System.out.println("El archivo no pudo ser encontrado... " + ex.getMessage());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,"Error al escribir en el archivo... " + ex.getMessage(),"¡ERROR!",JOptionPane.ERROR_MESSAGE);
            System.out.println("Error al escribir en el archivo... " + ex.getMessage());
        }
    }
    
    private String leerArchivo(String direccion){
        String texto = "";
        
        try{
            BufferedReader bf = new BufferedReader(new FileReader(direccion));
            String temp = "";
            String bfRead;
            while((bfRead = bf.readLine()) != null){
                temp = temp + bfRead+"\n";
            }
            texto = temp;
        }catch(Exception e){
        
        }
        return texto;
    }
    
    private void syntacticAnalysis() {
        Grammar gramatica = new Grammar(tokens, errors);
        
        

        /*ELIMINAR ERRORES*/
        gramatica.delete("ERROR_\\#", 1, "----------> ERROR_\\#:  Error desconocido, verififque como esta escrito el codigo, Linea [#] Columna [%]");
        gramatica.delete("ERROR_0", 1, "----------> ERROR_0:  El caracter no pertenece al alfabeto, Linea [#] Columna [%]");
        gramatica.delete("ERROR_1", 1, "----------> ERROR_1:  La variable no inicia con una letra, Linea [#] Columna [%]");
        gramatica.delete("ERROR_2", 1, "----------> ERROR_2:  Un carácter del nombre de la variable no pertenece a un símbolo válido, Linea [#] Columna [%]");
        gramatica.delete("ERROR_3", 1, "----------> ERROR_3:  Un carácter de la cadena no pertenece a un símbolo válido, Linea [#] Columna [%]");
        gramatica.delete("ERROR_4", 1, "----------> ERROR_4:  No hay ningún elemento entre ‘ ’, Linea [#] Columna [%]");
        gramatica.delete("ERROR_5", 1, "----------> ERROR_5:  Un carácter del nombre del resultado no pertenece a un símbolo válido, Linea [#] Columna [%]");
        gramatica.delete("ERROR_6", 1, "----------> ERROR_6:  No hay por lo menos una letra después del \\#, Linea [#] Columna [%]");
        gramatica.delete("ERROR_7", 1, "----------> ERROR_7:  Un carácter del nombre del método no pertenece a un símbolo válido, Linea [#] Columna [%]");
        gramatica.delete("ERROR_8", 1, "----------> ERROR_8:  No Hay por lo menos una letra después de &, Linea [#] Columna [%]");
        gramatica.delete("ERROR_9", 1, "----------> ERROR_9:  El número contiene un caracter no valido o cuenta con más de un punto, Linea [#] Columna [%]");
        gramatica.delete("ERROR_10", 1, "----------> ERROR_10:  No contiene un dígito como mínimo después del punto en el número , Linea [#] Columna [%]");
        gramatica.delete("ERROR_11", 1, "----------> ERROR_11:  El número inicia con punto decimal, Linea [#] Columna [%]");
        gramatica.delete("ERROR_12", 1, "----------> ERROR_12:  El número inicia con punto decimal y tiene más de uno, Linea [#] Columna [%]");
        gramatica.delete("ERROR_13", 1, "----------> ERROR_13:  El número no inicia con un dígito, Linea [#] Columna [%]");

        /*VALORES*/
        gramatica.group("FINAL", "(Delimitador_De_Sentencia)");
        gramatica.group("OPERADOR", "(Operador_Aritmetico_Mas | Operador_Aritmetico_Menos)");
        gramatica.group("VARIABLE", "(Identificador_Variable)");
        gramatica.group("RESULTADO", "(Identificador_Resultado)");
        gramatica.group("VALOR_NUMEROE", "(Numero_Entero)");
        gramatica.group("VALOR_NUMEROD", "(Numero_Decimal)");
        gramatica.group("VALOR_COLOR", "(Palabra_Reservada_12 | Palabra_Reservada_13 | Palabra_Reservada_14 | Palabra_Reservada_15 | Palabra_Reservada_16)");
        gramatica.group("VALOR_FIGURA", "(Palabra_Reservada_17 | Palabra_Reservada_18 | Palabra_Reservada_19 | Palabra_Reservada_20 | Palabra_Reservada_21)");
        gramatica.group("VALOR_CADENA", "(Identificador_Cadena)");
        gramatica.group("PE", "(Palabra_Reservada_6)");
        gramatica.group("PD", "(Palabra_Reservada_7)");
        gramatica.group("PR", "(Palabra_Reservada_8)");
        gramatica.group("PC", "(Palabra_Reservada_9)");
        gramatica.group("PF", "(Palabra_Reservada_10)");
        gramatica.group("PCO", "(Palabra_Reservada_11)");
        gramatica.group("ASIGNAR", "(Operador_De_Asignacion)");

        /*DECLARACION ENTERO*/
        gramatica.group("DECLARACION_ENTERO_1", "(PE)(VARIABLE)(ASIGNAR)(OPERADOR)(VALOR_NUMEROE|VALOR_NUMEROD|VALOR_COLOR|VALOR_FIGURA|VALOR_CADENA)(FINAL)", true, identProdD1);
        gramatica.group("DECLARACION_ENTERO_2", "(PE)(VARIABLE)(FINAL)", true, identProdD2);
        /*DECLARACION DECIMAL*/
        gramatica.group("DECLARACION_DECIMAL_1", "(PD)(VARIABLE)(ASIGNAR)(OPERADOR)(VALOR_NUMEROE|VALOR_NUMEROD|VALOR_COLOR|VALOR_FIGURA|VALOR_CADENA)(FINAL)", true, identProdD1);
        gramatica.group("DECLARACION_DECIMAL_2", "(PD)(VARIABLE)(FINAL)", true, identProdD2);
        /*DECLARACION RESULTADO*/
        gramatica.group("DECLARACION_RESULTADO", "(PR)(RESULTADO)(FINAL)", true, identProdD2);
        /*DECLARACION CADENA*/
        gramatica.group("DECLARACION_CADENA_1", "(PC)(VARIABLE)(ASIGNAR)(VALOR_NUMEROE|VALOR_NUMEROD|VALOR_COLOR|VALOR_FIGURA|VALOR_CADENA)(FINAL)", true, identProdD1);
        gramatica.group("DECLARACION_CADENA_2", "(PC)(VARIABLE)(FINAL)", true, identProdD2);
        /*DECLARACION FIGURA*/
        gramatica.group("DECLARACION_FIGURA_1", "(PF)(VARIABLE)(ASIGNAR)(VALOR_NUMEROE|VALOR_NUMEROD|VALOR_COLOR|VALOR_FIGURA|VALOR_CADENA)(FINAL)", true, identProdD1);
        gramatica.group("DECLARACION_FIGURA_2", "(PF)(VARIABLE)(FINAL)", true, identProdD2);
        /*DECLARACION COLOR*/
        gramatica.group("DECLARACION_COLOR_1", "(PCO)(VARIABLE)(ASIGNAR)(VALOR_NUMEROE|VALOR_NUMEROD|VALOR_COLOR|VALOR_FIGURA|VALOR_CADENA)(FINAL)", true, identProdD1);
        gramatica.group("DECLARACION_COLOR_2", "(PCO)(VARIABLE)(FINAL)", true, identProdD2);

        /*ASIGNACION ENTERO*/
        gramatica.group("ASIGNACION_ENTERO", "(VARIABLE)(ASIGNAR)(OPERADOR)(VALOR_NUMEROE|VALOR_NUMEROD|VALOR_COLOR|VALOR_FIGURA|VALOR_CADENA)(FINAL)", true, identProdA1);
        /*ASIGNACION DECIMAL*/
        gramatica.group("ASIGNACION_DECIMAL", "(VARIABLE)(ASIGNAR)(OPERADOR)(VALOR_NUMEROE|VALOR_NUMEROD|VALOR_COLOR|VALOR_FIGURA|VALOR_CADENA)(FINAL)", true, identProdA1);
        /*ASIGNACION CADENA*/
        gramatica.group("ASIGNACION_CADENA", "(VARIABLE)(ASIGNAR)(VALOR_NUMEROE|VALOR_NUMEROD|VALOR_COLOR|VALOR_FIGURA|VALOR_CADENA)(FINAL)", true, identProdA1);
        /*ASIGNACION FIGURA*/
        gramatica.group("ASIGNACION_FIGURA", "(VARIABLE)(ASIGNAR)(VALOR_NUMEROE|VALOR_NUMEROD|VALOR_COLOR|VALOR_FIGURA|VALOR_CADENA)(FINAL)", true, identProdA1);
        /*ASIGNACION COLOR*/
        gramatica.group("ASIGNACION_COLOR", "(VARIABLE)(ASIGNAR)(VALOR_NUMEROE|VALOR_NUMEROD|VALOR_COLOR|VALOR_FIGURA|VALOR_CADENA)(FINAL)", true, identProdA1);

        /*ERRORES DECLARATIVOS*/
        gramatica.finalLineColumn();
        /*ERRORES ENTERO*/
        gramatica.group("DECLARACION_ENTERO_1", "(PE)(ASIGNAR)(OPERADOR)(VALOR_NUMEROE)(FINAL)", 15,
                "----------> ERROR_15:  Falta la variable en la declaración, Linea [#] Columna [%]");
        gramatica.group("DECLARACION_ENTERO_1", "(PE)(VARIABLE)(OPERADOR)(VALOR_NUMEROE)(FINAL)", 16,
                "----------> ERROR_16:  Falta el = en la declaración, Linea [#] Columna [%]");
        gramatica.group("DECLARACION_ENTERO_1", "(PE)(VARIABLE)(ASIGNAR)(OPERADOR)(VALOR_NUMEROE)", 17,
                "----------> ERROR_17:  Falta el ; en la declaración, Linea [#] Columna [%]");
        gramatica.group("DECLARACION_ENTERO_1", "(PE)(VARIABLE)(ASIGNAR)(OPERADOR)(FINAL)", 18,
                "----------> ERROR_18:  Falta el valor de la variable en la declaración, Linea [#] Columna [%]");
        gramatica.group("DECLARACION_ENTERO_1", "(PE)(VARIABLE)(ASIGNAR)(VALOR_NUMEROE)(FINAL)", 19,
                "----------> ERROR_19:  Falta signo “+” o “-” antes del número en la declaración, Linea [#] Columna [%]");
        gramatica.group("DECLARACION_ENTERO_1", "(PE)((VARIABLE)(ASIGNAR)(OPERADOR)(VALOR_NUMEROE)(FINAL))?", 20,
                "----------> ERROR_20:  Faltan varios elementos en la declaración, Linea [#] Columna [%]");

        gramatica.group("DECLARACION_ENTERO_2", "(VARIABLE)(FINAL)", 14,
                "----------> ERROR_14:  Falta la palabra reservada en la declaración, Linea [#] Columna [%]");
        gramatica.group("DECLARACION_ENTERO_2", "(PE)(FINAL)", 15,
                "----------> ERROR_15:  Falta la variable en la declaración, Linea [#] Columna [%]");
        gramatica.group("DECLARACION_ENTERO_2", "(PE)(VARIABLE)", 17,
                "----------> ERROR_17:  Falta el ; en la declaración, Linea [#] Columna [%]");
        gramatica.group("DECLARACION_ENTERO_2", "(PE)((VARIABLE)(FINAL))?", 20,
                "----------> ERROR_20:  Faltan varios elementos en la declaración, Linea [#] Columna [%]");
        /*ERRORES DECIMAL*/
        gramatica.group("DECLARACION_DECIMAL_1", "(PD)(ASIGNAR)(OPERADOR)(VALOR_NUMEROD)(FINAL)", 15,
                "----------> ERROR_15:  Falta la variable en la declaración, Linea [#] Columna [%]");
        gramatica.group("DECLARACION_DECIMAL_1", "(PD)(VARIABLE)(OPERADOR)(VALOR_NUMEROD)(FINAL)", 16,
                "----------> ERROR_16:  Falta el = en la declaración, Linea [#] Columna [%]");
        gramatica.group("DECLARACION_DECIMAL_1", "(PD)(VARIABLE)(ASIGNAR)(OPERADOR)(VALOR_NUMEROD)", 17,
                "----------> ERROR_17:  Falta el ; en la declaración, Linea [#] Columna [%]");
        gramatica.group("DECLARACION_DECIMAL_1", "(PD)(VARIABLE)(ASIGNAR)(OPERADOR)(FINAL)", 18,
                "----------> ERROR_18:  Falta el valor de la variable en la declaración, Linea [#] Columna [%]");
        gramatica.group("DECLARACION_DECIMAL_1", "(PD)(VARIABLE)(ASIGNAR)(VALOR_NUMEROD)(FINAL)", 19,
                "----------> ERROR_19:  Falta signo “+” o “-” antes del número en la declaración, Linea [#] Columna [%]");
        gramatica.group("DECLARACION_DECIMAL_1", "(PD)((VARIABLE)(ASIGNAR)(OPERADOR)(VALOR_NUMEROD)(FINAL))?", 20,
                "----------> ERROR_20:  Faltan varios elementos en la declaración, Linea [#] Columna [%]");

        gramatica.group("DECLARACION_DECIMAL_2", "(VARIABLE)(FINAL)", 14,
                "----------> ERROR_14:  Falta la palabra reservada en la declaración, Linea [#] Columna [%]");
        gramatica.group("DECLARACION_ENTERO_2", "(PD)(FINAL)", 15,
                "----------> ERROR_15:  Falta la variable en la declaración, Linea [#] Columna [%]");
        gramatica.group("DECLARACION_DECIMAL_2", "(PD)(VARIABLE)", 17,
                "----------> ERROR_17:  Falta el ; en la declaración, Linea [#] Columna [%]");
        gramatica.group("DECLARACION_DECIMAL_2", "(PD)((VARIABLE)(FINAL))?", 20,
                "----------> ERROR_20:  Faltan varios elementos en la declaración, Linea [#] Columna [%]");
        /*ERRORES RESULTADO*/
        gramatica.group("DECLARACION_RESULTADO", "(RESULTADO)(FINAL)", 14,
                "----------> ERROR_14:  Falta la palabra reservada en la declaración, Linea [#] Columna [%]");
        gramatica.group("DECLARACION_RESULTADO", "(PR)(FINAL)", 15,
                "----------> ERROR_15:  Falta la variable en la declaración, Linea [#] Columna [%]");
        gramatica.group("DECLARACION_RESULTADO", "(PR)(RESULTADO)", 17,
                "----------> ERROR_17:  Falta el ; en la declaración, Linea [#] Columna [%]");
        gramatica.group("DECLARACION_RESULTADO", "(PR)((RESULTADO)(FINAL))?", 20,
                "----------> ERROR_20:  Faltan varios elementos en la declaración, Linea [#] Columna [%]");
        /*ERRORES CADENA*/
        gramatica.group("DECLARACION_CADENA_1", "(PC)(ASIGNAR)(VALOR_CADENA)(FINAL)", 15,
                "----------> ERROR_15:  Falta la variable en la declaración, Linea [#] Columna [%]");
        gramatica.group("DECLARACION_CADENA_1", "(PC)(VARIABLE)(VALOR_CADENA)(FINAL)", 16,
                "----------> ERROR_16:  Falta el = en la declaración, Linea [#] Columna [%]");
        gramatica.group("DECLARACION_CADENA_1", "(PC)(VARIABLE)(ASIGNAR)(VALOR_CADENA)", 17,
                "----------> ERROR_17:  Falta el ; en la declaración, Linea [#] Columna [%]");
        gramatica.group("DECLARACION_CADENA_1", "(PC)(VARIABLE)(ASIGNAR)(FINAL)", 18,
                "----------> ERROR_18:  Falta el valor de la variable en la declaración, Linea [#] Columna [%]");
        gramatica.group("DECLARACION_CADENA_1", "(PC)((VARIABLE)(ASIGNAR)(VALOR_CADENA)(FINAL))?", 20,
                "----------> ERROR_20:  Faltan varios elementos en la declaración, Linea [#] Columna [%]");

        gramatica.group("DECLARACION_CADENA_2", "(VARIABLE)(FINAL)", 14,
                "----------> ERROR_14:  Falta la palabra reservada en la declaración, Linea [#] Columna [%]");
        gramatica.group("DECLARACION_CADENA_2", "(PC)(FINAL)", 15,
                "----------> ERROR_15:  Falta la variable en la declaración, Linea [#] Columna [%]");
        gramatica.group("DECLARACION_CADENA_2", "(PC)(VARIABLE)", 17,
                "----------> ERROR_17:  Falta el ; en la declaración, Linea [#] Columna [%]");
        gramatica.group("DECLARACION_CADENA_2", "(PC)((VARIABLE)(FINAL))?", 20,
                "----------> ERROR_20:  Faltan varios elementos en la declaración, Linea [#] Columna [%]");
        /*ERRORES FIGURA*/
        gramatica.group("DECLARACION_FIGURA_1", "(PF)(ASIGNAR)(VALOR_FIGURA)(FINAL)", 15,
                "----------> ERROR_15:  Falta la variable en la declaración, Linea [#] Columna [%]");
        gramatica.group("DECLARACION_FIGURA_1", "(PF)(VARIABLE)(VALOR_FIGURA)(FINAL)", 16,
                "----------> ERROR_16:  Falta el = en la declaración, Linea [#] Columna [%]");
        gramatica.group("DECLARACION_FIGURA_1", "(PF)(VARIABLE)(ASIGNAR)(VALOR_FIGURA)", 17,
                "----------> ERROR_17:  Falta el ; en la declaración, Linea [#] Columna [%]");
        gramatica.group("DECLARACION_FIGURA_1", "(PF)(VARIABLE)(ASIGNAR)(FINAL)", 18,
                "----------> ERROR_18:  Falta el valor de la variable en la declaración, Linea [#] Columna [%]");
        gramatica.group("DECLARACION_FIGURA_1", "(PF)((VARIABLE)(ASIGNAR)(VALOR_FIGURA)(FINAL))?", 20,
                "----------> ERROR_20:  Faltan varios elementos en la declaración, Linea [#] Columna [%]");

        gramatica.group("DECLARACION_FIGURA_2", "(VARIABLE)(FINAL)", 14,
                "----------> ERROR_14:  Falta la palabra reservada en la declaración, Linea [#] Columna [%]");
        gramatica.group("DECLARACION_FIGURA_2", "(PF)(FINAL)", 15,
                "----------> ERROR_15:  Falta la variable en la declaración, Linea [#] Columna [%]");
        gramatica.group("DECLARACION_FIGURA_2", "(PF)(VARIABLE)", 17,
                "----------> ERROR_17:  Falta el ; en la declaración, Linea [#] Columna [%]");
        gramatica.group("DECLARACION_FIGURA_2", "(PF)((VARIABLE)(FINAL))?", 20,
                "----------> ERROR_20:  Faltan varios elementos en la declaración, Linea [#] Columna [%]");
        /*ERRORES COLOR*/
        gramatica.group("DECLARACION_COLOR_1", "(PCO)(ASIGNAR)(VALOR_COLOR)(FINAL)", 15,
                "----------> ERROR_15:  Falta la variable en la declaración, Linea [#] Columna [%]");
        gramatica.group("DECLARACION_COLOR_1", "(PCO)(VARIABLE)(VALOR_COLOR)(FINAL)", 16,
                "----------> ERROR_16:  Falta el = en la declaración, Linea [#] Columna [%]");
        gramatica.group("DECLARACION_COLOR_1", "(PCO)(VARIABLE)(ASIGNAR)(VALOR_COLOR)", 17,
                "----------> ERROR_17:  Falta el ; en la declaración, Linea [#] Columna [%]");
        gramatica.group("DECLARACION_COLOR_1", "(PCO)(VARIABLE)(ASIGNAR)(FINAL)", 18,
                "----------> ERROR_18:  Falta el valor de la variable en la declaración, Linea [#] Columna [%]");
        gramatica.group("DECLARACION_COLOR_1", "(PCO)((VARIABLE)(ASIGNAR)(VALOR_COLOR)(FINAL))?", 20,
                "----------> ERROR_20:  Faltan varios elementos en la declaración, Linea [#] Columna [%]");

        gramatica.group("DECLARACION_COLOR_2", "(VARIABLE)(FINAL)", 14,
                "----------> ERROR_14:  Falta la palabra reservada en la declaración, Linea [#] Columna [%]");
        gramatica.group("DECLARACION_COLOR_2", "(PCO)(FINAL)", 15,
                "----------> ERROR_15:  Falta la variable en la declaración, Linea [#] Columna [%]");
        gramatica.group("DECLARACION_COLOR_2", "(PCO)(VARIABLE)", 17,
                "----------> ERROR_17:  Falta el ; en la declaración, Linea [#] Columna [%]");
        gramatica.group("DECLARACION_COLOR_2", "(PCO)((VARIABLE)(FINAL))?", 20,
                "----------> ERROR_20:  Faltan varios elementos en la declaración, Linea [#] Columna [%]");

        gramatica.initialLineColumn();

        /*Eliminacion de datos*/
        gramatica.delete(new String[]{"PE", "PD", "PC", "PR", "PF", "PCO"}, 26,
                "----------> ERROR_26:  La palabra reservada no se encuentran en un método declarativo, Linea [#] Columna [%]");

        /*VARIABLES*/
        gramatica.group("FUNCION", "(Palabra_Reservada_1 | Palabra_Reservada_2 | Palabra_Reservada_3 | Palabra_Reservada_4)");
        gramatica.group("FUNCION_M", "(Palabra_Reservada_5)");
        gramatica.group("AGRUPAR_1", "(Signo_De_Agrupacion_1)");
        gramatica.group("AGRUPAR_2", "(Signo_De_Agrupacion_2)");
        gramatica.group("AGRUPAR_3", "(Signo_De_Agrupacion_3)");
        gramatica.group("AGRUPAR_4", "(Signo_De_Agrupacion_4)");
        gramatica.group("COMA", "(Separador)");

        /*FUNCIONES OPERACIONES*/
        gramatica.group("FUNCION_COMPLETA_5", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)(FINAL)", true, identProdF14);
        gramatica.group("FUNCION_COMPLETA_4", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)(FINAL)", true, identProdF13);
        gramatica.group("FUNCION_COMPLETA_3", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)(FINAL)", true, identProdF12);
        gramatica.group("FUNCION_COMPLETA_2", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)(FINAL)", true, identProdF11);

        gramatica.finalLineColumn();

        /*ERRORES DE OPERACIONES*/
        gramatica.group("FUNCION_COMPLETA_5", "(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)(FINAL)", 33,
                "----------> ERROR_33:  Falta la palabra reservada en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_4", "(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)(FINAL)", 33,
                "----------> ERROR_33:  Falta la palabra reservada en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_3", "(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)(FINAL)", 33,
                "----------> ERROR_33:  Falta la palabra reservada en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_2", "(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)(FINAL)", 33,
                "----------> ERROR_33:  Falta la palabra reservada en la función, Linea [#] Columna [%]");

        gramatica.group("FUNCION_COMPLETA_5", "(FUNCION)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)(FINAL)", 34,
                "----------> ERROR_34:  Falta el paréntesis al inicio “(“ o al final “)” en la función , Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_4", "(FUNCION)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)(FINAL)", 34,
                "----------> ERROR_34:  Falta el paréntesis al inicio “(“ o al final “)” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_3", "(FUNCION)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)(FINAL)", 34,
                "----------> ERROR_34:  Falta el paréntesis al inicio “(“ o al final “)” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_2", "(FUNCION)((VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)(FINAL)", 34,
                "----------> ERROR_34:  Falta el paréntesis al inicio “(“ o al final “)” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_5", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)(FINAL)", 34,
                "----------> ERROR_34:  Falta el paréntesis al inicio “(“ o al final “)” en la función , Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_4", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)(FINAL)", 34,
                "----------> ERROR_34:  Falta el paréntesis al inicio “(“ o al final “)” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_3", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)(FINAL)", 34,
                "----------> ERROR_34:  Falta el paréntesis al inicio “(“ o al final “)” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_2", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE))(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)(FINAL)", 34,
                "----------> ERROR_34:  Falta el paréntesis al inicio “(“ o al final “)” en la función, Linea [#] Columna [%]");

        gramatica.group("FUNCION_COMPLETA_5", "(FUNCION)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)(FINAL)", 46,
                "----------> ERROR_46:  Falta el paréntesis al inicio “(“ y al final “)” en la función , Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_4", "(FUNCION)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)(FINAL)", 46,
                "----------> ERROR_46:  Falta el paréntesis al inicio “(“ y al final “)” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_3", "(FUNCION)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)(FINAL)", 46,
                "----------> ERROR_46:  Falta el paréntesis al inicio “(“ y al final “)” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_2", "(FUNCION)((VARIABLE)(COMA)(VARIABLE))(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)(FINAL)", 46,
                "----------> ERROR_46:  Falta el paréntesis al inicio “(“ y al final “)” en la función, Linea [#] Columna [%]");

        gramatica.group("FUNCION_COMPLETA_1", "(FUNCION)(AGRUPAR_1)((VARIABLE))?(AGRUPAR_2)(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)(FINAL)", 35,
                "----------> ERROR_35:  Variables fuera de los rangos en la función (Min. 2 y Max. 5), Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_6", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)(FINAL)", 35,
                "----------> ERROR_35:  Variables fuera de los rangos en la función (Min. 2 y Max. 5), Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)(FINAL)", 35,
                "----------> ERROR_35:  Variables fuera de los rangos en la función (Min. 2 y Max. 5), Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_8", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)(FINAL)", 35,
                "----------> ERROR_35:  Variables fuera de los rangos en la función (Min. 2 y Max. 5), Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_9", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)(FINAL)", 35,
                "----------> ERROR_35:  Variables fuera de los rangos en la función (Min. 2 y Max. 5), Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_10", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)(FINAL)", 35,
                "----------> ERROR_35:  Variables fuera de los rangos en la función (Min. 2 y Max. 5), Linea [#] Columna [%]");

        gramatica.group("FUNCION_COMPLETA_5", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(RESULTADO)(AGRUPAR_4)(FINAL)", 36,
                "----------> ERROR_36:  Falta signo “<” al inicio o al final “>” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_4", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(RESULTADO)(AGRUPAR_4)(FINAL)", 36,
                "----------> ERROR_36:  Falta signo “<” al inicio o al final “>” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_3", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(RESULTADO)(AGRUPAR_4)(FINAL)", 36,
                "----------> ERROR_36:  Falta signo “<” al inicio o al final “>” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_2", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(RESULTADO)(AGRUPAR_4)(FINAL)", 36,
                "----------> ERROR_36:  Falta signo “<” al inicio o al final “>” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_5", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(AGRUPAR_3)(RESULTADO)(FINAL)", 36,
                "----------> ERROR_36:  Falta signo “<” al inicio o al final “>” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_4", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(AGRUPAR_3)(RESULTADO)(FINAL)", 36,
                "----------> ERROR_36:  Falta signo “<” al inicio o al final “>” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_3", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(AGRUPAR_3)(RESULTADO)(FINAL)", 36,
                "----------> ERROR_36:  Falta signo “<” al inicio o al final “>” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_2", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(AGRUPAR_3)(RESULTADO)(FINAL)", 36,
                "----------> ERROR_36:  Falta signo “<” al inicio o al final “>” en la función, Linea [#] Columna [%]");

        gramatica.group("FUNCION_COMPLETA_5", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(RESULTADO)(FINAL)", 45,
                "----------> ERROR_45:  Falta signo “<” al inicio y al final “>” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_4", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(RESULTADO)(FINAL)", 45,
                "----------> ERROR_45:  Falta signo “<” al inicio y al final “>” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_3", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(RESULTADO)(FINAL)", 45,
                "----------> ERROR_45:  Falta signo “<” al inicio y al final “>” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_2", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(RESULTADO)(FINAL)", 45,
                "----------> ERROR_45:  Falta signo “<” al inicio y al final “>” en la función, Linea [#] Columna [%]");

        gramatica.group("FUNCION_COMPLETA_5", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(AGRUPAR_3)(AGRUPAR_4)(FINAL)", 37,
                "----------> ERROR_37:  Falta la variable tipo resultado en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_4", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(AGRUPAR_3)(AGRUPAR_4)(FINAL)", 37,
                "----------> ERROR_37:  Falta la variable tipo resultado en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_3", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(AGRUPAR_3)(AGRUPAR_4)(FINAL)", 37,
                "----------> ERROR_37:  Falta la variable tipo resultado en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_2", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(AGRUPAR_3)(AGRUPAR_4)(FINAL)", 37,
                "----------> ERROR_37:  Falta la variable tipo resultado en la función, Linea [#] Columna [%]");

        gramatica.group("FUNCION_COMPLETA_5", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)", 38,
                "----------> ERROR_38:  Falta ; al final de la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_4", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)", 38,
                "----------> ERROR_38:  Falta ; al final de la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_3", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)", 38,
                "----------> ERROR_38:  Falta ; al final de la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_2", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)", 38,
                "----------> ERROR_38:  Falta ; al final de la función, Linea [#] Columna [%]");

        gramatica.group("FUNCION_COMPLETA_5", "(FUNCION)((AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)(FINAL))?", 39,
                "----------> ERROR_39:  Faltan varios elementos en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_4", "(FUNCION)((AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)(FINAL))?", 39,
                "----------> ERROR_39:  Faltan varios elementos en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_3", "(FUNCION)((AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)(FINAL))?", 39,
                "----------> ERROR_39:  Faltan varios elementos en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_2", "(FUNCION)((AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)(FINAL))?", 39,
                "----------> ERROR_39:  Faltan varios elementos en la función, Linea [#] Columna [%]");

        /*ERRORES DE FUNCIONES ESPECIALES*/
        gramatica.group("FUNCION_COMPLETA_1", "(AGRUPAR_1)((VARIABLE))?(AGRUPAR_2)(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)(FINAL)", 33,
                "----------> ERROR_33:  Falta la palabra reservada en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_6", "(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)(FINAL)", 33,
                "----------> ERROR_33:  Falta la palabra reservada en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)(FINAL)", 33,
                "----------> ERROR_33:  Falta la palabra reservada en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_8", "(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)(FINAL)", 33,
                "----------> ERROR_33:  Falta la palabra reservada en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_9", "(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)(FINAL)", 33,
                "----------> ERROR_33:  Falta la palabra reservada en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_10", "(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)(FINAL)", 33,
                "----------> ERROR_33:  Falta la palabra reservada en la función, Linea [#] Columna [%]");

        gramatica.group("FUNCION_COMPLETA_1", "(FUNCION)((VARIABLE))?(AGRUPAR_2)(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)(FINAL)", 34,
                "----------> ERROR_34:  Falta el paréntesis al inicio “(“ o al final “)” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_6", "(FUNCION)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)(FINAL)", 34,
                "----------> ERROR_34:  Falta el paréntesis al inicio “(“ o al final “)” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)(FINAL)", 34,
                "----------> ERROR_34:  Falta el paréntesis al inicio “(“ o al final “)” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_8", "(FUNCION)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)(FINAL)", 34,
                "----------> ERROR_34:  Falta el paréntesis al inicio “(“ o al final “)” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_9", "(FUNCION)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)(FINAL)", 34,
                "----------> ERROR_34:  Falta el paréntesis al inicio “(“ o al final “)” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_10", "(FUNCION)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)(FINAL)", 34,
                "----------> ERROR_34:  Falta el paréntesis al inicio “(“ o al final “)” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_1", "(FUNCION)(AGRUPAR_1)((VARIABLE))?(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)(FINAL)", 34,
                "----------> ERROR_34:  Falta el paréntesis al inicio “(“ o al final “)” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_6", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)(FINAL)", 34,
                "----------> ERROR_34:  Falta el paréntesis al inicio “(“ o al final “)” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)(FINAL)", 34,
                "----------> ERROR_34:  Falta el paréntesis al inicio “(“ o al final “)” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_8", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)(FINAL)", 34,
                "----------> ERROR_34:  Falta el paréntesis al inicio “(“ o al final “)” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_9", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)(FINAL)", 34,
                "----------> ERROR_34:  Falta el paréntesis al inicio “(“ o al final “)” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_10", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)(FINAL)", 34,
                "----------> ERROR_34:  Falta el paréntesis al inicio “(“ o al final “)” en la función, Linea [#] Columna [%]");

        gramatica.group("FUNCION_COMPLETA_1", "(FUNCION)((VARIABLE))?(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)(FINAL)", 46,
                "----------> ERROR_46:  Falta el paréntesis al inicio “(“ y al final “)” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_6", "(FUNCION)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)(FINAL)", 46,
                "----------> ERROR_46:  Falta el paréntesis al inicio “(“ y al final “)” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)(FINAL)", 46,
                "----------> ERROR_46:  Falta el paréntesis al inicio “(“ y al final “)” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_8", "(FUNCION)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)(FINAL)", 46,
                "----------> ERROR_46:  Falta el paréntesis al inicio “(“ y al final “)” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_9", "(FUNCION)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)(FINAL)", 46,
                "----------> ERROR_46:  Falta el paréntesis al inicio “(“ y al final “)” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_10", "(FUNCION)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)(FINAL)", 46,
                "----------> ERROR_46:  Falta el paréntesis al inicio “(“ y al final “)” en la función, Linea [#] Columna [%]");

        gramatica.group("FUNCION_COMPLETA_1", "(FUNCION)(AGRUPAR_1)((VARIABLE))?(AGRUPAR_2)(RESULTADO)(AGRUPAR_4)(FINAL)", 36,
                "----------> ERROR_36:  Falta signo “<” al inicio o al final “>” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_6", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(RESULTADO)(AGRUPAR_4)(FINAL)", 36,
                "----------> ERROR_36:  Falta signo “<” al inicio o al final “>” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(RESULTADO)(AGRUPAR_4)(FINAL)", 36,
                "----------> ERROR_36:  Falta signo “<” al inicio o al final “>” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_8", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(RESULTADO)(AGRUPAR_4)(FINAL)", 36,
                "----------> ERROR_36:  Falta signo “<” al inicio o al final “>” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_9", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(RESULTADO)(AGRUPAR_4)(FINAL)", 36,
                "----------> ERROR_36:  Falta signo “<” al inicio o al final “>” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_10", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(RESULTADO)(AGRUPAR_4)(FINAL)", 36,
                "----------> ERROR_36:  Falta signo “<” al inicio o al final “>” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_1", "(FUNCION)(AGRUPAR_1)((VARIABLE))?(AGRUPAR_2)(AGRUPAR_3)(RESULTADO)(FINAL)", 36,
                "----------> ERROR_36:  Falta signo “<” al inicio o al final “>” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_6", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(AGRUPAR_3)(RESULTADO)(FINAL)", 36,
                "----------> ERROR_36:  Falta signo “<” al inicio o al final “>” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(AGRUPAR_3)(RESULTADO)(FINAL)", 36,
                "----------> ERROR_36:  Falta signo “<” al inicio o al final “>” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_8", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(AGRUPAR_3)(RESULTADO)(FINAL)", 36,
                "----------> ERROR_36:  Falta signo “<” al inicio o al final “>” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_9", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(AGRUPAR_3)(RESULTADO)(FINAL)", 36,
                "----------> ERROR_36:  Falta signo “<” al inicio o al final “>” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_10", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(AGRUPAR_3)(RESULTADO)(FINAL)", 36,
                "----------> ERROR_36:  Falta signo “<” al inicio o al final “>” en la función, Linea [#] Columna [%]");

        gramatica.group("FUNCION_COMPLETA_1", "(FUNCION)(AGRUPAR_1)((VARIABLE))?(AGRUPAR_2)(RESULTADO)(FINAL)", 45,
                "----------> ERROR_45:  Falta signo “<” al inicio y al final “>” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_6", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(RESULTADO)(FINAL)", 45,
                "----------> ERROR_45:  Falta signo “<” al inicio y al final “>” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(RESULTADO)(FINAL)", 45,
                "----------> ERROR_45:  Falta signo “<” al inicio y al final “>” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_8", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(RESULTADO)(FINAL)", 45,
                "----------> ERROR_45:  Falta signo “<” al inicio y al final “>” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_9", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(RESULTADO)(FINAL)", 45,
                "----------> ERROR_45:  Falta signo “<” al inicio y al final “>” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_10", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(RESULTADO)(FINAL)", 45,
                "----------> ERROR_45:  Falta signo “<” al inicio y al final “>” en la función, Linea [#] Columna [%]");

        gramatica.group("FUNCION_COMPLETA_1", "(FUNCION)(AGRUPAR_1)((VARIABLE))?(AGRUPAR_2)(AGRUPAR_3)(AGRUPAR_4)(FINAL)", 37,
                "----------> ERROR_37:  Falta la variable tipo resultado en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_6", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(AGRUPAR_3)(AGRUPAR_4)(FINAL)", 37,
                "----------> ERROR_37:  Falta la variable tipo resultado en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(AGRUPAR_3)(AGRUPAR_4)(FINAL)", 37,
                "----------> ERROR_37:  Falta la variable tipo resultado en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_8", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(AGRUPAR_3)(AGRUPAR_4)(FINAL)", 37,
                "----------> ERROR_37:  Falta la variable tipo resultado en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_9", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(AGRUPAR_3)(AGRUPAR_4)(FINAL)", 37,
                "----------> ERROR_37:  Falta la variable tipo resultado en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_10", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(AGRUPAR_3)(AGRUPAR_4)(FINAL)", 37,
                "----------> ERROR_37:  Falta la variable tipo resultado en la función, Linea [#] Columna [%]");

        gramatica.group("FUNCION_COMPLETA_1", "(FUNCION)(AGRUPAR_1)((VARIABLE))?(AGRUPAR_2)(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)", 38,
                "----------> ERROR_38:  Falta ; al final de la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_6", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)", 38,
                "----------> ERROR_38:  Falta ; al final de la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)", 38,
                "----------> ERROR_38:  Falta ; al final de la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_8", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)", 38,
                "----------> ERROR_38:  Falta ; al final de la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_9", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)", 38,
                "----------> ERROR_38:  Falta ; al final de la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_10", "(FUNCION)(AGRUPAR_1)((VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(AGRUPAR_3)(RESULTADO)(AGRUPAR_4)", 38,
                "----------> ERROR_38:  Falta ; al final de la función, Linea [#] Columna [%]");

        gramatica.initialLineColumn();

        /*FUNCION MOSTRAR*/
        gramatica.group("FUNCION_COMPLETA_6", "(FUNCION_M)(AGRUPAR_1)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(FINAL)", true, identProdF2);
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)(AGRUPAR_1)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(FINAL)", true, identProdF3);

        gramatica.finalLineColumn();
        /*ERRORES DE MOSTRAR*/
        gramatica.group("FUNCION_COMPLETA_7", "(AGRUPAR_1)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(FINAL)", 33,
                "----------> ERROR_33:  Falta la palabra reservada en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_6", "(AGRUPAR_1)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(FINAL)", 33,
                "----------> ERROR_33:  Falta la palabra reservada en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(FINAL)", 34,
                "----------> ERROR_34:  Falta el paréntesis al inicio “(“ o al final “)” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)(AGRUPAR_1)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE))(FINAL)", 34,
                "----------> ERROR_34:  Falta el paréntesis al inicio “(“ o al final “)” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_6", "(FUNCION_M)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(FINAL)", 34,
                "----------> ERROR_34:  Falta el paréntesis al inicio “(“ o al final “)” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_6", "(FUNCION_M)(AGRUPAR_1)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(FINAL)", 34,
                "----------> ERROR_34:  Falta el paréntesis al inicio “(“ o al final “)” en la función, Linea [#] Columna [%]");

        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE))(FINAL)", 46,
                "----------> ERROR_46:  Falta el paréntesis al inicio “(“ y al final “)” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_6", "(FUNCION_M)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(FINAL)", 46,
                "----------> ERROR_46:  Falta el paréntesis al inicio “(“ y al final “)” en la función, Linea [#] Columna [%]");

        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)(AGRUPAR_1)((VARIABLE)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(FINAL)", 37,
                "----------> ERROR_37:  Falta la variable tipo resultado en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_6", "(FUNCION_M)(AGRUPAR_1)((VARIABLE)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(FINAL)", 37,
                "----------> ERROR_37:  Falta la variable tipo resultado en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)(AGRUPAR_1)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)", 38,
                "----------> ERROR_38:  Falta ; al final de la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_6", "(FUNCION_M)(AGRUPAR_1)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)", 38,
                "----------> ERROR_38:  Falta ; al final de la función, Linea [#] Columna [%]");

        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)(AGRUPAR_1)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE))?(AGRUPAR_2)(FINAL)", 43,
                "----------> ERROR_43:  Variables fuera de los rangos en la función (Min. 4 y Max. 4), Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)(AGRUPAR_1)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(FINAL)", 43,
                "----------> ERROR_43:  Variables fuera de los rangos en la función (Min. 4 y Max. 4), Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)(AGRUPAR_1)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(FINAL)", 43,
                "----------> ERROR_43:  Variables fuera de los rangos en la función (Min. 4 y Max. 4), Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)(AGRUPAR_1)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(FINAL)", 44,
                "----------> ERROR_44:  Variables fuera de los rangos en la función (Min. 12 y Max. 12), Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)(AGRUPAR_1)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(FINAL)", 44,
                "----------> ERROR_44:  Variables fuera de los rangos en la función (Min. 12 y Max. 12), Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)(AGRUPAR_1)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(FINAL)", 44,
                "----------> ERROR_44:  Variables fuera de los rangos en la función (Min. 12 y Max. 12), Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)(AGRUPAR_1)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(FINAL)", 44,
                "----------> ERROR_44:  Variables fuera de los rangos en la función (Min. 12 y Max. 12), Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)(AGRUPAR_1)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(FINAL)", 44,
                "----------> ERROR_44:  Variables fuera de los rangos en la función (Min. 12 y Max. 12), Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)(AGRUPAR_1)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(FINAL)", 44,
                "----------> ERROR_44:  Variables fuera de los rangos en la función (Min. 12 y Max. 12), Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)(AGRUPAR_1)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(FINAL)", 44,
                "----------> ERROR_44:  Variables fuera de los rangos en la función (Min. 12 y Max. 12), Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)(AGRUPAR_1)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(FINAL)", 44,
                "----------> ERROR_44:  Variables fuera de los rangos en la función (Min. 12 y Max. 12), Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)(AGRUPAR_1)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(FINAL)", 44,
                "----------> ERROR_44:  Variables fuera de los rangos en la función (Min. 12 y Max. 12), Linea [#] Columna [%]");

        gramatica.group("FUNCION_COMPLETA_7", "(AGRUPAR_1)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE))?(AGRUPAR_2)(FINAL)", 33,
                "----------> ERROR_33:  Falta la palabra reservada en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(AGRUPAR_1)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(FINAL)", 33,
                "----------> ERROR_33:  Falta la palabra reservada en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(AGRUPAR_1)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(FINAL)", 33,
                "----------> ERROR_33:  Falta la palabra reservada en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(AGRUPAR_1)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(FINAL)", 33,
                "----------> ERROR_33:  Falta la palabra reservada en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(AGRUPAR_1)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(FINAL)", 33,
                "----------> ERROR_33:  Falta la palabra reservada en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(AGRUPAR_1)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(FINAL)", 33,
                "----------> ERROR_33:  Falta la palabra reservada en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(AGRUPAR_1)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(FINAL)", 33,
                "----------> ERROR_33:  Falta la palabra reservada en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(AGRUPAR_1)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(FINAL)", 33,
                "----------> ERROR_33:  Falta la palabra reservada en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(AGRUPAR_1)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(FINAL)", 33,
                "----------> ERROR_33:  Falta la palabra reservada en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(AGRUPAR_1)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(FINAL)", 33,
                "----------> ERROR_33:  Falta la palabra reservada en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(AGRUPAR_1)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(FINAL)", 33,
                "----------> ERROR_33:  Falta la palabra reservada en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(AGRUPAR_1)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(FINAL)", 33,
                "----------> ERROR_33:  Falta la palabra reservada en la función, Linea [#] Columna [%]");

        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE))?(AGRUPAR_2)(FINAL)", 34,
                "----------> ERROR_34:  Falta el paréntesis al inicio “(“ o al final “)” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(FINAL)", 34,
                "----------> ERROR_34:  Falta el paréntesis al inicio “(“ o al final “)” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(FINAL)", 34,
                "----------> ERROR_34:  Falta el paréntesis al inicio “(“ o al final “)” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(FINAL)", 34,
                "----------> ERROR_34:  Falta el paréntesis al inicio “(“ o al final “)” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(FINAL)", 34,
                "----------> ERROR_34:  Falta el paréntesis al inicio “(“ o al final “)” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(FINAL)", 34,
                "----------> ERROR_34:  Falta el paréntesis al inicio “(“ o al final “)” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(FINAL)", 34,
                "----------> ERROR_34:  Falta el paréntesis al inicio “(“ o al final “)” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(FINAL)", 34,
                "----------> ERROR_34:  Falta el paréntesis al inicio “(“ o al final “)” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(FINAL)", 34,
                "----------> ERROR_34:  Falta el paréntesis al inicio “(“ o al final “)” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(FINAL)", 34,
                "----------> ERROR_34:  Falta el paréntesis al inicio “(“ o al final “)” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(FINAL)", 34,
                "----------> ERROR_34:  Falta el paréntesis al inicio “(“ o al final “)” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(FINAL)", 34,
                "----------> ERROR_34:  Falta el paréntesis al inicio “(“ o al final “)” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)(AGRUPAR_1)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE))?(FINAL)", 34,
                "----------> ERROR_34:  Falta el paréntesis al inicio “(“ o al final “)” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)(AGRUPAR_1)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(FINAL)", 34,
                "----------> ERROR_34:  Falta el paréntesis al inicio “(“ o al final “)” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)(AGRUPAR_1)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(FINAL)", 34,
                "----------> ERROR_34:  Falta el paréntesis al inicio “(“ o al final “)” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)(AGRUPAR_1)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(FINAL)", 34,
                "----------> ERROR_34:  Falta el paréntesis al inicio “(“ o al final “)” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)(AGRUPAR_1)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(FINAL)", 34,
                "----------> ERROR_34:  Falta el paréntesis al inicio “(“ o al final “)” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)(AGRUPAR_1)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(FINAL)", 34,
                "----------> ERROR_34:  Falta el paréntesis al inicio “(“ o al final “)” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)(AGRUPAR_1)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(FINAL)", 34,
                "----------> ERROR_34:  Falta el paréntesis al inicio “(“ o al final “)” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)(AGRUPAR_1)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(FINAL)", 34,
                "----------> ERROR_34:  Falta el paréntesis al inicio “(“ o al final “)” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)(AGRUPAR_1)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(FINAL)", 34,
                "----------> ERROR_34:  Falta el paréntesis al inicio “(“ o al final “)” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)(AGRUPAR_1)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(FINAL)", 34,
                "----------> ERROR_34:  Falta el paréntesis al inicio “(“ o al final “)” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)(AGRUPAR_1)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(FINAL)", 34,
                "----------> ERROR_34:  Falta el paréntesis al inicio “(“ o al final “)” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)(AGRUPAR_1)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(FINAL)", 34,
                "----------> ERROR_34:  Falta el paréntesis al inicio “(“ o al final “)” en la función, Linea [#] Columna [%]");

        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE))?(FINAL)", 46,
                "----------> ERROR_46:  Falta el paréntesis al inicio “(“ y al final “)” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(FINAL)", 46,
                "----------> ERROR_46:  Falta el paréntesis al inicio “(“ y al final “)” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(FINAL)", 46,
                "----------> ERROR_46:  Falta el paréntesis al inicio “(“ y al final “)” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(FINAL)", 46,
                "----------> ERROR_46:  Falta el paréntesis al inicio “(“ y al final “)” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(FINAL)", 46,
                "----------> ERROR_46:  Falta el paréntesis al inicio “(“ y al final “)” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(FINAL)", 46,
                "----------> ERROR_46:  Falta el paréntesis al inicio “(“ y al final “)” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(FINAL)", 46,
                "----------> ERROR_46:  Falta el paréntesis al inicio “(“ y al final “)” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(FINAL)", 46,
                "----------> ERROR_46:  Falta el paréntesis al inicio “(“ y al final “)” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(FINAL)", 46,
                "----------> ERROR_46:  Falta el paréntesis al inicio “(“ y al final “)” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(FINAL)", 46,
                "----------> ERROR_46:  Falta el paréntesis al inicio “(“ y al final “)” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(FINAL)", 46,
                "----------> ERROR_46:  Falta el paréntesis al inicio “(“ y al final “)” en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(FINAL)", 46,
                "----------> ERROR_46:  Falta el paréntesis al inicio “(“ y al final “)” en la función, Linea [#] Columna [%]");

        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)(AGRUPAR_1)((VARIABLE)"
                + "(COMA)(VARIABLE))?(AGRUPAR_2)(FINAL)", 37,
                "----------> ERROR_37:  Falta la variable tipo resultado en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)(AGRUPAR_1)((VARIABLE)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(FINAL)", 37,
                "----------> ERROR_37:  Falta la variable tipo resultado en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)(AGRUPAR_1)((VARIABLE)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(FINAL)", 37,
                "----------> ERROR_37:  Falta la variable tipo resultado en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)(AGRUPAR_1)((VARIABLE)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(FINAL)", 37,
                "----------> ERROR_37:  Falta la variable tipo resultado en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)(AGRUPAR_1)((VARIABLE)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(FINAL)", 37,
                "----------> ERROR_37:  Falta la variable tipo resultado en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)(AGRUPAR_1)((VARIABLE)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(FINAL)", 37,
                "----------> ERROR_37:  Falta la variable tipo resultado en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)(AGRUPAR_1)((VARIABLE)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(FINAL)", 37,
                "----------> ERROR_37:  Falta la variable tipo resultado en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)(AGRUPAR_1)((VARIABLE)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(FINAL)", 37,
                "----------> ERROR_37:  Falta la variable tipo resultado en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)(AGRUPAR_1)((VARIABLE)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(FINAL)", 37,
                "----------> ERROR_37:  Falta la variable tipo resultado en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)(AGRUPAR_1)((VARIABLE)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(FINAL)", 37,
                "----------> ERROR_37:  Falta la variable tipo resultado en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)(AGRUPAR_1)((VARIABLE)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(FINAL)", 37,
                "----------> ERROR_37:  Falta la variable tipo resultado en la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)(AGRUPAR_1)((VARIABLE)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)(FINAL)", 37,
                "----------> ERROR_37:  Falta la variable tipo resultado en la función, Linea [#] Columna [%]");

        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)(AGRUPAR_1)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE))?(AGRUPAR_2)", 38,
                "----------> ERROR_38:  Falta ; al final de la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)(AGRUPAR_1)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)", 38,
                "----------> ERROR_38:  Falta ; al final de la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)(AGRUPAR_1)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)", 38,
                "----------> ERROR_38:  Falta ; al final de la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)(AGRUPAR_1)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)", 38,
                "----------> ERROR_38:  Falta ; al final de la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)(AGRUPAR_1)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)", 38,
                "----------> ERROR_38:  Falta ; al final de la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)(AGRUPAR_1)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)", 38,
                "----------> ERROR_38:  Falta ; al final de la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)(AGRUPAR_1)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)", 38,
                "----------> ERROR_38:  Falta ; al final de la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)(AGRUPAR_1)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)", 38,
                "----------> ERROR_38:  Falta ; al final de la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)(AGRUPAR_1)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)", 38,
                "----------> ERROR_38:  Falta ; al final de la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)(AGRUPAR_1)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)", 38,
                "----------> ERROR_38:  Falta ; al final de la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)(AGRUPAR_1)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)", 38,
                "----------> ERROR_38:  Falta ; al final de la función, Linea [#] Columna [%]");
        gramatica.group("FUNCION_COMPLETA_7", "(FUNCION_M)(AGRUPAR_1)((VARIABLE)(COMA)(RESULTADO)"
                + "(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE)(COMA)(VARIABLE))(AGRUPAR_2)", 38,
                "----------> ERROR_38:  Falta ; al final de la función, Linea [#] Columna [%]");

        gramatica.initialLineColumn();

        gramatica.finalLineColumn();
        /*ERRORES DE ASIGNACION*/
        gramatica.group("ASIGNACION_ENTERO", "(ASIGNAR)(OPERADOR)(VALOR_NUMEROE)(FINAL)", 27,
                "----------> ERROR_27:  Falta la variable en la asignación, Linea [#] Columna [%]");
        gramatica.group("ASIGNACION_DECIMAL", "(ASIGNAR)(OPERADOR)(VALOR_NUMEROD)(FINAL)", 27,
                "----------> ERROR_27:  Falta la variable en la asignación, Linea [#] Columna [%]");
        gramatica.group("ASIGNACION_CADENA", "(ASIGNAR)(VALOR_CADENA)(FINAL)", 27,
                "----------> ERROR_27:  Falta la variable en la asignación, Linea [#] Columna [%]");
        gramatica.group("ASIGNACION_FIGURA", "(ASIGNAR)(VALOR_FIGURA)(FINAL)", 27,
                "----------> ERROR_27:  Falta la variable en la asignación, Linea [#] Columna [%]");
        gramatica.group("ASIGNACION_COLOR", "(ASIGNAR)(VALOR_COLOR)(FINAL)", 27,
                "----------> ERROR_27:  Falta la variable en la asignación, Linea [#] Columna [%]");

        gramatica.group("ASIGNACION_ENTERO", "(VARIABLE)(OPERADOR)(VALOR_NUMEROE)(FINAL)", 28,
                "----------> ERROR_28:  Falta el = en la asignación, Linea [#] Columna [%]");
        gramatica.group("ASIGNACION_DECIMAL", "(VARIABLE)(OPERADOR)(VALOR_NUMEROD)(FINAL)", 28,
                "----------> ERROR_28:  Falta el = en la asignación, Linea [#] Columna [%]");
        gramatica.group("ASIGNACION_CADENA", "(VARIABLE)(VALOR_CADENA)(FINAL)", 28,
                "----------> ERROR_28:  Falta el = en la asignación, Linea [#] Columna [%]");
        gramatica.group("ASIGNACION_FIGURA", "(VARIABLE)(VALOR_FIGURA)(FINAL)", 28,
                "----------> ERROR_28:  Falta el = en la asignación, Linea [#] Columna [%]");
        gramatica.group("ASIGNACION_COLOR", "(VARIABLE)(VALOR_COLOR)(FINAL)", 28,
                "----------> ERROR_28:  Falta el = en la asignación, Linea [#] Columna [%]");

        gramatica.group("ASIGNACION_ENTERO", "(VARIABLE)(ASIGNAR)(OPERADOR)(FINAL)", 29,
                "----------> ERROR_29:  Falta el nuevo valor de la variable en la asignación, Linea [#] Columna [%]");
        gramatica.group("ASIGNACION_DECIMAL", "(VARIABLE)(ASIGNAR)(OPERADOR)(FINAL)", 29,
                "----------> ERROR_29:  Falta el nuevo valor de la variable en la asignación, Linea [#] Columna [%]");
        gramatica.group("ASIGNACION_CADENA", "(VARIABLE)(ASIGNAR)(FINAL)", 29,
                "----------> ERROR_29:  Falta el nuevo valor de la variable en la asignación, Linea [#] Columna [%]");
        gramatica.group("ASIGNACION_FIGURA", "(VARIABLE)(ASIGNAR)(FINAL)", 29,
                "----------> ERROR_29:  Falta el nuevo valor de la variable en la asignación, Linea [#] Columna [%]");
        gramatica.group("ASIGNACION_COLOR", "(VARIABLE)(ASIGNAR)(FINAL)", 29,
                "----------> ERROR_29:  Falta el nuevo valor de la variable en la asignación, Linea [#] Columna [%]");

        gramatica.group("ASIGNACION_ENTERO", "(VARIABLE)(ASIGNAR)(OPERADOR)(VALOR_NUMEROE)", 30,
                "----------> ERROR_30:  Falta el ; en la asignación, Linea [#] Columna [%]");
        gramatica.group("ASIGNACION_DECIMAL", "(VARIABLE)(ASIGNAR)(OPERADOR)(VALOR_NUMEROD)", 30,
                "----------> ERROR_30:  Falta el ; en la asignación, Linea [#] Columna [%]");
        gramatica.group("ASIGNACION_CADENA", "(VARIABLE)(ASIGNAR)(VALOR_CADENA)", 30,
                "----------> ERROR_30:  Falta el ; en la asignación, Linea [#] Columna [%]");
        gramatica.group("ASIGNACION_FIGURA", "(VARIABLE)(ASIGNAR)(VALOR_FIGURA)", 30,
                "----------> ERROR_30:  Falta el ; en la asignación, Linea [#] Columna [%]");
        gramatica.group("ASIGNACION_COLOR", "(VARIABLE)(ASIGNAR)(VALOR_COLOR)", 30,
                "----------> ERROR_30:  Falta el ; en la asignación, Linea [#] Columna [%]");

        gramatica.group("ASIGNACION_ENTERO", "(VARIABLE)(ASIGNAR)(VALOR_NUMEROE)(FINAL)", 31,
                "----------> ERROR_31:  El signo de “+” o “-” no se encuentran en la asignación, Linea [#] Columna [%]");
        gramatica.group("ASIGNACION_DECIMAL", "(VARIABLE)(ASIGNAR)(VALOR_NUMEROD)(FINAL)", 31,
                "----------> ERROR_31:  El signo de “+” o “-” no se encuentran en la asignación, Linea [#] Columna [%]");

        gramatica.group("ASIGNACION_ENTERO", "(VARIABLE)((ASIGNAR)(OPERADOR)(VALOR_NUMEROE)(FINAL))?", 32,
                "----------> ERROR_32:  Faltan varios elementos en la asignación, Linea [#] Columna [%]");
        gramatica.group("ASIGNACION_DECIMAL", "(VARIABLE)((ASIGNAR)(OPERADOR)(VALOR_NUMEROD)(FINAL))?", 32,
                "----------> ERROR_32:  Faltan varios elementos en la asignación, Linea [#] Columna [%]");
        gramatica.group("ASIGNACION_CADENA", "(VARIABLE)((ASIGNAR)(VALOR_CADENA)(FINAL))?", 32,
                "----------> ERROR_32:  Faltan varios elementos en la asignación, Linea [#] Columna [%]");
        gramatica.group("ASIGNACION_FIGURA", "(VARIABLE)((ASIGNAR)(VALOR_FIGURA)(FINAL))?", 32,
                "----------> ERROR_32:  Faltan varios elementos en la asignación, Linea [#] Columna [%]");
        gramatica.group("ASIGNACION_COLOR", "(VARIABLE)((ASIGNAR)(VALOR_COLOR)(FINAL))?", 32,
                "----------> ERROR_32:  Faltan varios elementos en la asignación, Linea [#] Columna [%]");
        gramatica.initialLineColumn();
        /*ELIMINACION DE DATOS*/
        gramatica.delete(new String[]{"VALOR_NUMEROE", "VALOR_NUMEROD", "VALOR_CADENA", "VALOR_FIGURA", "VALOR_COLOR"}, 21,
                "----------> ERROR_21:  El tipo de dato no se encuentra en un método declarado o de asignación, Linea [#] Columna [%]");
        gramatica.delete("OPERADOR", 23,
                "----------> ERROR_23:  El signo de “+” o “-” no se encuentran en un método declarativo o de asignación, Linea [#] Columna [%]");
        gramatica.delete("ASIGNAR", 24,
                "----------> ERROR_24:  El = no se encuentran en un método declarativo o de asignación, Linea [#] Columna [%]");
        /*ELIMINACION DE DATOS*/
        gramatica.delete(new String[]{"VARIABLE", "RESULTADO"}, 22,
                "----------> ERROR_22:  La variable no se encuentra en un método declarativo, de asignación o de función , Linea [#] Columna [%]");
        gramatica.delete("FINAL", 25,
                "----------> ERROR_25:  El ; no se encuentran en un método declarativo, de asignación o de función , Linea [#] Columna [%]");
        gramatica.delete("COMA", 40,
                "----------> ERROR_40:  La , no se encuentra en una función, Linea [#] Columna [%]");
        gramatica.delete(new String[]{"AGRUPAR_1", "AGRUPAR_2", "AGRUPAR_3", "AGRUPAR_4"}, 41,
                "----------> ERROR_41:  El “(”, “)”, “<” o “>” no se encuentran en una función, Linea [#] Columna [%]");
        gramatica.delete(new String[]{"FUNCION", "FUNCION_M"}, 42,
                "----------> ERROR_42:  La palabra reservada no se encuentra en una función, Linea [#] Columna [%]");

        /*CLASE*/
 /*VARIABLES*/
        gramatica.group("PM", "(Palabra_Reservada_22)");
        gramatica.group("VARIABLE_METODO", "(Identificador_Metodo)");
        gramatica.group("AGRUPAR_5", "(Signo_De_Agrupacion_5)");
        gramatica.group("AGRUPAR_6", "(Signo_De_Agrupacion_6)");

        gramatica.group("METODO", "(PM)(VARIABLE_METODO)(AGRUPAR_5)(((DECLARACION_ENTERO_1 | DECLARACION_ENTERO_2 | "
                + "DECLARACION_DECIMAL_1 | DECLARACION_DECIMAL_2 | DECLARACION_RESULTADO | DECLARACION_CADENA_1 | "
                + "DECLARACION_CADENA_2 | DECLARACION_FIGURA_1 | DECLARACION_FIGURA_2 | DECLARACION_COLOR_1 | "
                + "DECLARACION_COLOR_2 | ASIGNACION_ENTERO | ASIGNACION_DECIMAL | ASIGNACION_CADENA | ASIGNACION_FIGURA | "
                + "ASIGNACION_COLOR | FUNCION_COMPLETA_1 | FUNCION_COMPLETA_2 | FUNCION_COMPLETA_3 | FUNCION_COMPLETA_4 | "
                + "FUNCION_COMPLETA_5 | FUNCION_COMPLETA_6 | FUNCION_COMPLETA_7 | FUNCION_COMPLETA_8 | FUNCION_COMPLETA_9 | "
                + "FUNCION_COMPLETA_10))*)?(AGRUPAR_6)", true, identProdM1);

        gramatica.finalLineColumn();
        /*ERRORES DE CLASES*/
        gramatica.group("METODO", "(VARIABLE_METODO)(AGRUPAR_5)(((DECLARACION_ENTERO_1 | DECLARACION_ENTERO_2 | "
                + "DECLARACION_DECIMAL_1 | DECLARACION_DECIMAL_2 | DECLARACION_RESULTADO | DECLARACION_CADENA_1 | "
                + "DECLARACION_CADENA_2 | DECLARACION_FIGURA_1 | DECLARACION_FIGURA_2 | DECLARACION_COLOR_1 | "
                + "DECLARACION_COLOR_2 | ASIGNACION_ENTERO | ASIGNACION_DECIMAL | ASIGNACION_CADENA | ASIGNACION_FIGURA | "
                + "ASIGNACION_COLOR | FUNCION_COMPLETA_1 | FUNCION_COMPLETA_2 | FUNCION_COMPLETA_3 | FUNCION_COMPLETA_4 | "
                + "FUNCION_COMPLETA_5 | FUNCION_COMPLETA_6 | FUNCION_COMPLETA_7 | FUNCION_COMPLETA_8 | FUNCION_COMPLETA_9 | "
                + "FUNCION_COMPLETA_10))*)?(AGRUPAR_6)", 47,
                "----------> ERROR_47:  Falta la palabra reservada en la clase, Linea [#] Columna [%]");

        gramatica.group("METODO", "(PM)(AGRUPAR_5)(((DECLARACION_ENTERO_1 | DECLARACION_ENTERO_2 | "
                + "DECLARACION_DECIMAL_1 | DECLARACION_DECIMAL_2 | DECLARACION_RESULTADO | DECLARACION_CADENA_1 | "
                + "DECLARACION_CADENA_2 | DECLARACION_FIGURA_1 | DECLARACION_FIGURA_2 | DECLARACION_COLOR_1 | "
                + "DECLARACION_COLOR_2 | ASIGNACION_ENTERO | ASIGNACION_DECIMAL | ASIGNACION_CADENA | ASIGNACION_FIGURA | "
                + "ASIGNACION_COLOR | FUNCION_COMPLETA_1 | FUNCION_COMPLETA_2 | FUNCION_COMPLETA_3 | FUNCION_COMPLETA_4 | "
                + "FUNCION_COMPLETA_5 | FUNCION_COMPLETA_6 | FUNCION_COMPLETA_7 | FUNCION_COMPLETA_8 | FUNCION_COMPLETA_9 | "
                + "FUNCION_COMPLETA_10))*)?(AGRUPAR_6)", 48,
                "----------> ERROR_48:  Falta el nombre de la clase, Linea [#] Columna [%]");

        gramatica.group("METODO", "(PM)(VARIABLE_METODO)(((DECLARACION_ENTERO_1 | DECLARACION_ENTERO_2 | "
                + "DECLARACION_DECIMAL_1 | DECLARACION_DECIMAL_2 | DECLARACION_RESULTADO | DECLARACION_CADENA_1 | "
                + "DECLARACION_CADENA_2 | DECLARACION_FIGURA_1 | DECLARACION_FIGURA_2 | DECLARACION_COLOR_1 | "
                + "DECLARACION_COLOR_2 | ASIGNACION_ENTERO | ASIGNACION_DECIMAL | ASIGNACION_CADENA | ASIGNACION_FIGURA | "
                + "ASIGNACION_COLOR | FUNCION_COMPLETA_1 | FUNCION_COMPLETA_2 | FUNCION_COMPLETA_3 | FUNCION_COMPLETA_4 | "
                + "FUNCION_COMPLETA_5 | FUNCION_COMPLETA_6 | FUNCION_COMPLETA_7 | FUNCION_COMPLETA_8 | FUNCION_COMPLETA_9 | "
                + "FUNCION_COMPLETA_10))*)?(AGRUPAR_6)", 49,
                "----------> ERROR_49:  Falta signo “{“ al inicio o al final “}” en la clase, Linea [#] Columna [%]");
        gramatica.group("METODO", "(PM)(VARIABLE_METODO)(AGRUPAR_5)(((DECLARACION_ENTERO_1 | DECLARACION_ENTERO_2 | "
                + "DECLARACION_DECIMAL_1 | DECLARACION_DECIMAL_2 | DECLARACION_RESULTADO | DECLARACION_CADENA_1 | "
                + "DECLARACION_CADENA_2 | DECLARACION_FIGURA_1 | DECLARACION_FIGURA_2 | DECLARACION_COLOR_1 | "
                + "DECLARACION_COLOR_2 | ASIGNACION_ENTERO | ASIGNACION_DECIMAL | ASIGNACION_CADENA | ASIGNACION_FIGURA | "
                + "ASIGNACION_COLOR | FUNCION_COMPLETA_1 | FUNCION_COMPLETA_2 | FUNCION_COMPLETA_3 | FUNCION_COMPLETA_4 | "
                + "FUNCION_COMPLETA_5 | FUNCION_COMPLETA_6 | FUNCION_COMPLETA_7 | FUNCION_COMPLETA_8 | FUNCION_COMPLETA_9 | "
                + "FUNCION_COMPLETA_10))*)?", 49,
                "----------> ERROR_49:  Falta signo “{“ al inicio o al final “}” en la clase, Linea [#] Columna [%]");
        
        gramatica.group("METODO", "(PM)(VARIABLE_METODO)(((DECLARACION_ENTERO_1 | DECLARACION_ENTERO_2 | "
                + "DECLARACION_DECIMAL_1 | DECLARACION_DECIMAL_2 | DECLARACION_RESULTADO | DECLARACION_CADENA_1 | "
                + "DECLARACION_CADENA_2 | DECLARACION_FIGURA_1 | DECLARACION_FIGURA_2 | DECLARACION_COLOR_1 | "
                + "DECLARACION_COLOR_2 | ASIGNACION_ENTERO | ASIGNACION_DECIMAL | ASIGNACION_CADENA | ASIGNACION_FIGURA | "
                + "ASIGNACION_COLOR | FUNCION_COMPLETA_1 | FUNCION_COMPLETA_2 | FUNCION_COMPLETA_3 | FUNCION_COMPLETA_4 | "
                + "FUNCION_COMPLETA_5 | FUNCION_COMPLETA_6 | FUNCION_COMPLETA_7 | FUNCION_COMPLETA_8 | FUNCION_COMPLETA_9 | "
                + "FUNCION_COMPLETA_10))*)?", 50,
                "----------> ERROR_50:  Falta signo “{“ al inicio y al final “}” en la clase, Linea [#] Columna [%]");

        gramatica.initialLineColumn();
        /*ELIMINACION DE DATOS*/
        gramatica.delete(new String[]{"DECLARACION_ENTERO_1", "DECLARACION_ENTERO_2", "DECLARACION_DECIMAL_1", "DECLARACION_DECIMAL_2",
            "DECLARACION_RESULTADO", "DECLARACION_CADENA_1", "DECLARACION_CADENA_2", "DECLARACION_FIGURA_1",
            "DECLARACION_FIGURA_2", "DECLARACION_COLOR_1", "DECLARACION_COLOR_2", "ASIGNACION_ENTERO", "ASIGNACION_DECIMAL",
            "ASIGNACION_CADENA", "ASIGNACION_FIGURA", "ASIGNACION_COLOR", "FUNCION_COMPLETA_1", "FUNCION_COMPLETA_2",
            "FUNCION_COMPLETA_3", "FUNCION_COMPLETA_4", "FUNCION_COMPLETA_5", "FUNCION_COMPLETA_6", "FUNCION_COMPLETA_7",
            "FUNCION_COMPLETA_8", "FUNCION_COMPLETA_9", "FUNCION_COMPLETA_10"}, 51,
                "----------> ERROR_51:  Las funciones, las asignaciones o declaraciones no pueden estar fuera de una clase, Linea [#] Columna [%]");

        /*ELIMINACION DE DATOS*/
        gramatica.delete("PM", 52, "----------> ERROR_52:  La palabra reservada no se encuentra en una clase, Linea [#] Columna [%]");
        gramatica.delete("VARIABLE_METODO", 53, "----------> ERROR_53:  La variable no se encuentra en una clase, Linea [#] Columna [%]");
        gramatica.delete(new String[]{"AGRUPAR_5", "AGRUPAR_6"}, 54, "----------> ERROR_54:  El signo “{” o “}” no se encuentra en una clase, Linea [#] Columna [%]");

        /* Mostrar gramáticas */
        gramatica.show();
        //Acomodar las producciones de Clases en orden
        int tamaño = identProdM1.size();
        for(Production id: identProdM1){
            identProdM2.add(identProdM1.get(tamaño-1));
            tamaño--;
        }
    }

    private void semanticAnalysis() {
        //Error 55
        for (Production id1 : identProdD1) {
            if(id1.lexemeRank(0).equals("Color")){
            
            }
            if (identDataType == null) {
                identDataType.put(id1.lexemeRank(1), id1.lexicalCompRank(-2));
            }
            if (identDataType.containsKey(id1.lexemeRank(1))) {
                errors.add(new ErrorLSSL(55, "----------> ERROR_55:  La variable ya ha sido declarada en la clase, Linea [#] Columna [%]", id1, true));
            }
            identDataType.put(id1.lexemeRank(1), id1.lexicalCompRank(-2));
        }
        for (Production id2 : identProdD2) {
            if (identDataType == null) {
                identDataType.put(id2.lexemeRank(1), id2.lexicalCompRank(-2));
            }
            if (identDataType.containsKey(id2.lexemeRank(1))) {
                errors.add(new ErrorLSSL(55, "----------> ERROR_55:  La variable ya ha sido declarada, Linea [#] Columna [%]", id2, true));
            }
            if(id2.lexemeRank(0).equals("Entero")){
                identDataType.put(id2.lexemeRank(1),"Numero_Entero");
            }
            if(id2.lexemeRank(0).equals("Decimal")){
                identDataType.put(id2.lexemeRank(1),"Numero_Decimal");
            }
            if(id2.lexemeRank(0).equals("Cadena")){
                identDataType.put(id2.lexemeRank(1),"Identificador_Cadena");
            }
            if(id2.lexemeRank(0).equals("Color")){
                identDataType.put(id2.lexemeRank(1),"Color");
            }
            if(id2.lexemeRank(0).equals("Figura")){
                identDataType.put(id2.lexemeRank(1),"Figura");
            }
            if(id2.lexemeRank(0).equals("Resultado")){
                identDataType.put(id2.lexemeRank(1),"Resultado");
            }
        }//Error 55
        //Error 56
        HashMap<String, String> identDataType1 = new HashMap<>();
        HashMap<String, String> identDataType2 = new HashMap<>();
        HashMap<String, String> identDataType3 = new HashMap<>();
        HashMap<String, String> identDataType4 = new HashMap<>();
        HashMap<String, String> identDataType5 = new HashMap<>();
        identDataType1.put("Entero", "Numero_Entero");
        identDataType2.put("Entero", "Numero_Entero");
        identDataType3.put("Entero", "Numero_Entero");
        identDataType4.put("Entero", "Numero_Entero");
        identDataType5.put("Entero", "Numero_Entero");
        identDataType1.put("Decimal", "Numero_Decimal");
        identDataType2.put("Decimal", "Numero_Decimal");
        identDataType3.put("Decimal", "Numero_Decimal");
        identDataType4.put("Decimal", "Numero_Decimal");
        identDataType5.put("Decimal", "Numero_Decimal");
        identDataType1.put("Cadena", "Identificador_Cadena");
        identDataType2.put("Cadena", "Identificador_Cadena");
        identDataType3.put("Cadena", "Identificador_Cadena");
        identDataType4.put("Cadena", "Identificador_Cadena");
        identDataType5.put("Cadena", "Identificador_Cadena");
        identDataType1.put("Color", "Palabra_Reservada_12");
        identDataType2.put("Color", "Palabra_Reservada_13");
        identDataType3.put("Color", "Palabra_Reservada_14");
        identDataType4.put("Color", "Palabra_Reservada_15");
        identDataType5.put("Color", "Palabra_Reservada_16");
        identDataType1.put("Figura", "Palabra_Reservada_17");
        identDataType2.put("Figura", "Palabra_Reservada_18");
        identDataType3.put("Figura", "Palabra_Reservada_19");
        identDataType4.put("Figura", "Palabra_Reservada_20");
        identDataType5.put("Figura", "Palabra_Reservada_21");
        for (Production id : identProdD1) {
            if (!identDataType1.get(id.lexemeRank(0)).equals(id.lexicalCompRank(-2))
                    && !identDataType2.get(id.lexemeRank(0)).equals(id.lexicalCompRank(-2))
                    && !identDataType3.get(id.lexemeRank(0)).equals(id.lexicalCompRank(-2))
                    && !identDataType4.get(id.lexemeRank(0)).equals(id.lexicalCompRank(-2))
                    && !identDataType5.get(id.lexemeRank(0)).equals(id.lexicalCompRank(-2))) {
                    if(id.lexemeRank(0).equals("Entero")){
                        if(id.lexicalCompRank(-2).equals("Numero_Decimal")){
                            errors.add(new ErrorLSSL(56, "----------> ERROR_56:   El valor de la variable declarada no esta permitido, Linea [#] Columna [%]", id, true));
                        }
                    }else if(id.lexemeRank(0).equals("Decimal")){
                        if(id.lexicalCompRank(-2).equals("Numero_Entero")){
                            errors.add(new ErrorLSSL(56, "----------> ERROR_56:   El valor de la variable declarada no esta permitido, Linea [#] Columna [%]", id, true));
                        }
                    }else{
                        errors.add(new ErrorLSSL(56, "----------> ERROR_56:   El valor de la variable declarada no esta permitido, Linea [#] Columna [%]", id, true));
                    }
            }else{
                if(id.lexemeRank(0).equals("Entero")){
                    if(Integer.parseInt(id.lexemeRank(-2)) > 100000){
                        errors.add(new ErrorLSSL(56, "----------> ERROR_56:   El valor de la variable declarada no esta permitido, Linea [#] Columna [%]", id, true));
                    }else{
                        identDataTypeV.put(id.lexemeRank(1), id.lexemeRank(-2));
                        identDataTypeE.put(id.lexemeRank(1), id.lexemeRank(-3));
                        
                    }
                }else if(id.lexemeRank(0).equals("Decimal")){
                    if(Double.parseDouble(id.lexemeRank(-2)) > 99.9999){
                        errors.add(new ErrorLSSL(56, "----------> ERROR_56:   El valor de la variable declarada no esta permitido, Linea [#] Columna [%]", id, true));
                    }else{
                        identDataTypeV.put(id.lexemeRank(1), id.lexemeRank(-2));
                        identDataTypeE.put(id.lexemeRank(1), id.lexemeRank(-3));
                    }
                }else{
                    identDataTypeV.put(id.lexemeRank(1), id.lexemeRank(-2));
                    identDataTypeE.put(id.lexemeRank(1), id.lexemeRank(-3));
                }
            }
        }///Error 56
        //Error 57
        for (Production id : identProdA1){
            if (!identDataType.containsKey(id.lexemeRank(0))) {
                errors.add(new ErrorLSSL(57, "----------> ERROR_57:  La variable no ha sido declarada, Linea [#] Columna [%]", id, true));
            }
        }//Error 57
        //Error 58 y Error 67
        for(Production id: identProdA1){
            if(identDataType.containsKey(id.lexemeRank(0))){
                if(!id.lexicalCompRank(-2).equals(identDataType.get(id.lexemeRank(0)))){
                    if(identDataType.get(id.lexemeRank(0)).equals("Figura")){
                        if(!id.lexicalCompRank(-2).equals("Palabra_Reservada_17") && !id.lexicalCompRank(-2).equals("Palabra_Reservada_18") && !id.lexicalCompRank(-2).equals("Palabra_Reservada_19") && !id.lexicalCompRank(-2).equals("Palabra_Reservada_20") && !id.lexicalCompRank(-2).equals("Palabra_Reservada_21")){
                            errors.add(new ErrorLSSL(58,"----------> ERROR_58:   El valor que se le asigna a la variable no esta permitido, Linea [#] Columna [%]",id,true));
                        }else{
                            if(identDataTypeV.containsKey(id.lexemeRank(0))){
                                errors.add(new ErrorLSSL(67, "----------> ERROR_67:  La variable ya tiene un valor asignado, Linea [#] Columna [%]", id, true));
                            }else{
                                identDataTypeV.put(id.lexemeRank(0), id.lexemeRank(-2));
                                identDataTypeE.put(id.lexemeRank(0), id.lexemeRank(-3));
                                identDataType.put(id.lexemeRank(0),id.lexicalCompRank(-2));
                            }
                        }
                    }else if(identDataType.get(id.lexemeRank(0)).equals("Color")){
                        if(!id.lexicalCompRank(-2).equals("Palabra_Reservada_12") && !id.lexicalCompRank(-2).equals("Palabra_Reservada_13") && !id.lexicalCompRank(-2).equals("Palabra_Reservada_14") && !id.lexicalCompRank(-2).equals("Palabra_Reservada_15") && !id.lexicalCompRank(-2).equals("Palabra_Reservada_16")){
                            errors.add(new ErrorLSSL(58,"----------> ERROR_58:   El valor que se le asigna a la variable no esta permitido, Linea [#] Columna [%]",id,true));
                        }else{
                            if(identDataTypeV.containsKey(id.lexemeRank(0))){
                                errors.add(new ErrorLSSL(67, "----------> ERROR_67:  La variable ya tiene un valor asignado, Linea [#] Columna [%]", id, true));
                            }else{
                                identDataTypeV.put(id.lexemeRank(0), id.lexemeRank(-2));
                                identDataTypeE.put(id.lexemeRank(0), id.lexemeRank(-3));
                                identDataType.put(id.lexemeRank(0),id.lexicalCompRank(-2));
                            }
                        }
                    }else if(identDataType.get(id.lexemeRank(0)).equals("Numero_Entero")){
                        if(id.lexicalCompRank(-2).equals("Numero_Decimal")){
                            errors.add(new ErrorLSSL(58,"----------> ERROR_58:   El valor que se le asigna a la variable no esta permitido, Linea [#] Columna [%]",id,true));
                        }
                    }else if(identDataType.get(id.lexemeRank(0)).equals("Numero_Decimal")){
                        if(id.lexicalCompRank(-2).equals("Numero_Entero")){
                            errors.add(new ErrorLSSL(58,"----------> ERROR_58:   El valor que se le asigna a la variable no esta permitido, Linea [#] Columna [%]",id,true));
                        }
                    }
                    else{
                        errors.add(new ErrorLSSL(58,"----------> ERROR_58:   El valor que se le asigna a la variable no esta permitido, Linea [#] Columna [%]",id,true));
                    }
                }else{
                    if(identDataType.get(id.lexemeRank(0)).equals("Numero_Entero")){
                        if(Integer.parseInt(id.lexemeRank(-2)) > 100000){
                            errors.add(new ErrorLSSL(58,"----------> ERROR_58:   El valor que se le asigna a la variable no esta permitido, Linea [#] Columna [%]",id,true));
                        }else{
                            if(identDataTypeV.containsKey(id.lexemeRank(0))){
                                errors.add(new ErrorLSSL(67, "----------> ERROR_67:  La variable ya tiene un valor asignado, Linea [#] Columna [%]", id, true));
                            }else{
                                identDataTypeV.put(id.lexemeRank(0), id.lexemeRank(-2));
                                identDataTypeE.put(id.lexemeRank(0), id.lexemeRank(-3));
                                identDataType.put(id.lexemeRank(0),id.lexicalCompRank(-2));
                            }
                        }
                    }else if(identDataType.get(id.lexemeRank(0)).equals("Numero_Decimal")){
                        if(Double.parseDouble(id.lexemeRank(-2)) > 99.9999){
                            errors.add(new ErrorLSSL(58,"----------> ERROR_58:   El valor que se le asigna a la variable no esta permitido, Linea [#] Columna [%]",id,true));
                        }else{
                            if(identDataTypeV.containsKey(id.lexemeRank(0))){
                                errors.add(new ErrorLSSL(67, "----------> ERROR_67:  La variable ya tiene un valor asignado, Linea [#] Columna [%]", id, true));
                            }else{
                                identDataTypeV.put(id.lexemeRank(0), id.lexemeRank(-2));
                                identDataTypeE.put(id.lexemeRank(0), id.lexemeRank(-3));
                                identDataType.put(id.lexemeRank(0),id.lexicalCompRank(-2));
                            }
                        }
                    }else{
                        if(identDataTypeV.containsKey(id.lexemeRank(0))){
                            errors.add(new ErrorLSSL(67, "----------> ERROR_67:  La variable ya tiene un valor asignado, Linea [#] Columna [%]", id, true));
                        }else{
                            identDataTypeV.put(id.lexemeRank(0),id.lexemeRank(-2)); 
                            identDataTypeE.put(id.lexemeRank(0),id.lexemeRank(-3));
                            identDataType.put(id.lexemeRank(0),id.lexicalCompRank(-2));
                        }
                    }   
                }
            }
        }
        //Error 58 y Error 67
        //Error 64 y Error 65 (Primera Parte)
        //Funciones de Sumar, Restar, Multiplicar y Dividir
        //2 Variables
        for(Production id: identProdF11){
            if(identDataType.containsKey(id.lexemeRank(2)) && identDataType.containsKey(id.lexemeRank(4))){
                int i = 0;
                if(!identDataType.get(id.lexemeRank(2)).equals("Numero_Entero") && !identDataType.get(id.lexemeRank(2)).equals("Numero_Decimal")){
                    i++;
                }
                if(!identDataType.get(id.lexemeRank(4)).equals("Numero_Entero") && !identDataType.get(id.lexemeRank(4)).equals("Numero_Decimal")){
                    i++;
                }
                if(i == 1){
                    errors.add(new ErrorLSSL(64, "----------> ERROR_64:  El valor de una variable empleada en la función no está permitido, Linea [#] Columna [%]", id, true));
                }else if(i > 1){
                    errors.add(new ErrorLSSL(65, "----------> ERROR_65:  El valor de varias variables empleadas en la función no están permitidos, Linea [#] Columna [%]", id, true));
                }
            }
        }//2 Variables
        //3 Variables
        for(Production id: identProdF12){
            if(identDataType.containsKey(id.lexemeRank(2)) && identDataType.containsKey(id.lexemeRank(4)) && identDataType.containsKey(id.lexemeRank(6))){
                int i = 0;
                if(!identDataType.get(id.lexemeRank(2)).equals("Numero_Entero") && !identDataType.get(id.lexemeRank(2)).equals("Numero_Decimal")){
                    i++;
                }
                if(!identDataType.get(id.lexemeRank(4)).equals("Numero_Entero") && !identDataType.get(id.lexemeRank(4)).equals("Numero_Decimal")){
                    i++;
                }
                if(!identDataType.get(id.lexemeRank(6)).equals("Numero_Entero") && !identDataType.get(id.lexemeRank(6)).equals("Numero_Decimal")){
                    i++;
                }
                if(i == 1){
                    errors.add(new ErrorLSSL(64, "----------> ERROR_64:  El valor de una variable empleada en la función no está permitido, Linea [#] Columna [%]", id, true));
                }else if(i > 1){
                    errors.add(new ErrorLSSL(65, "----------> ERROR_65:  El valor de varias variables empleadas en la función no están permitidos, Linea [#] Columna [%]", id, true));
                }
            }
        }//3 Variables
        //4 Variables
        for(Production id: identProdF13){
            if(identDataType.containsKey(id.lexemeRank(2)) && identDataType.containsKey(id.lexemeRank(4)) && identDataType.containsKey(id.lexemeRank(6)) && identDataType.containsKey(id.lexemeRank(8))){
                int i = 0;
                if(!identDataType.get(id.lexemeRank(2)).equals("Numero_Entero") && !identDataType.get(id.lexemeRank(2)).equals("Numero_Decimal")){
                    i++;
                }
                if(!identDataType.get(id.lexemeRank(4)).equals("Numero_Entero") && !identDataType.get(id.lexemeRank(4)).equals("Numero_Decimal")){
                    i++;
                }
                if(!identDataType.get(id.lexemeRank(6)).equals("Numero_Entero") && !identDataType.get(id.lexemeRank(6)).equals("Numero_Decimal")){
                    i++;
                }
                if(!identDataType.get(id.lexemeRank(8)).equals("Numero_Entero") && !identDataType.get(id.lexemeRank(8)).equals("Numero_Decimal")){
                    i++;
                }
                if(i == 1){
                    errors.add(new ErrorLSSL(64, "----------> ERROR_64:  El valor de una variable empleada en la función no está permitido, Linea [#] Columna [%]", id, true));
                }else if(i > 1){
                    errors.add(new ErrorLSSL(65, "----------> ERROR_65:  El valor de varias variables empleadas en la función no están permitidos, Linea [#] Columna [%]", id, true));
                }
            }
        }//4 Variables
        //5 Variables
        for(Production id: identProdF14){
            if(identDataType.containsKey(id.lexemeRank(2)) && identDataType.containsKey(id.lexemeRank(4)) && identDataType.containsKey(id.lexemeRank(6)) && identDataType.containsKey(id.lexemeRank(8)) && identDataType.containsKey(id.lexemeRank(10))){
                int i = 0;
                if(!identDataType.get(id.lexemeRank(2)).equals("Numero_Entero") && !identDataType.get(id.lexemeRank(2)).equals("Numero_Decimal")){
                    i++;
                }
                if(!identDataType.get(id.lexemeRank(4)).equals("Numero_Entero") && !identDataType.get(id.lexemeRank(4)).equals("Numero_Decimal")){
                    i++;
                }
                if(!identDataType.get(id.lexemeRank(6)).equals("Numero_Entero") && !identDataType.get(id.lexemeRank(6)).equals("Numero_Decimal")){
                    i++;
                }
                if(!identDataType.get(id.lexemeRank(8)).equals("Numero_Entero") && !identDataType.get(id.lexemeRank(8)).equals("Numero_Decimal")){
                    i++;
                }
                if(!identDataType.get(id.lexemeRank(10)).equals("Numero_Entero") && !identDataType.get(id.lexemeRank(10)).equals("Numero_Decimal")){
                    i++;
                }
                if(i == 1){
                    errors.add(new ErrorLSSL(64, "----------> ERROR_64:  El valor de una variable empleada en la función no está permitido, Linea [#] Columna [%]", id, true));
                }else if(i > 1){
                    errors.add(new ErrorLSSL(65, "----------> ERROR_65:  El valor de varias variables empleadas en la función no están permitidos, Linea [#] Columna [%]", id, true));
                }
            }
        }//5 Variables
        //Funciones de Sumar, Restar, Multiplicar y Dividir
        //Error 64 y Error 65 (Primera Parte)
        //Error 59
        //2 Variables
        for(Production id: identProdF11){
            if(identDataType.containsKey(id.lexemeRank(2)) && identDataType.containsKey(id.lexemeRank(4))){
                if(identDataType.get(id.lexemeRank(2)).equals("Numero_Entero") && identDataType.get(id.lexemeRank(4)).equals("Numero_Decimal")){
                    errors.add(new ErrorLSSL(59,"----------> ERROR_59:  Se están empleando variables de tipos de datos distintos para la función, Linea [#] Columna [%]",id,true));
                }else if(identDataType.get(id.lexemeRank(4)).equals("Numero_Entero") && identDataType.get(id.lexemeRank(2)).equals("Numero_Decimal")){
                    errors.add(new ErrorLSSL(59,"----------> ERROR_59:  Se están empleando variables de tipos de datos distintos para la función, Linea [#] Columna [%]",id,true));
                }
            }
        }//2 Variables
        //3 Variables
        for(Production id: identProdF12){
            if(identDataType.containsKey(id.lexemeRank(2)) && identDataType.containsKey(id.lexemeRank(4)) && identDataType.containsKey(id.lexemeRank(6))){
                if(identDataType.get(id.lexemeRank(2)).equals("Numero_Entero")){
                    if(identDataType.get(id.lexemeRank(4)).equals("Numero_Decimal") || identDataType.get(id.lexemeRank(6)).equals("Numero_Decimal")){
                        errors.add(new ErrorLSSL(59,"----------> ERROR_59:  Se están empleando variables de tipos de datos distintos para la función, Linea [#] Columna [%]",id,true));
                    }
                }else if(identDataType.get(id.lexemeRank(2)).equals("Numero_Decimal")){
                    if(identDataType.get(id.lexemeRank(4)).equals("Numero_Entero") || identDataType.get(id.lexemeRank(6)).equals("Numero_Entero")){
                        errors.add(new ErrorLSSL(59,"----------> ERROR_59:  Se están empleando variables de tipos de datos distintos para la función, Linea [#] Columna [%]",id,true));
                    }
                }
            }
        }//3 Variables
        //4 Variables
        for(Production id: identProdF13){
            if(identDataType.containsKey(id.lexemeRank(2)) && identDataType.containsKey(id.lexemeRank(4)) && identDataType.containsKey(id.lexemeRank(6)) && identDataType.containsKey(id.lexemeRank(8))){
                if(identDataType.get(id.lexemeRank(2)).equals("Numero_Entero")){
                    if(identDataType.get(id.lexemeRank(4)).equals("Numero_Decimal") || identDataType.get(id.lexemeRank(6)).equals("Numero_Decimal") || identDataType.get(id.lexemeRank(8)).equals("Numero_Decimal")){
                        errors.add(new ErrorLSSL(59,"----------> ERROR_59:  Se están empleando variables de tipos de datos distintos para la función, Linea [#] Columna [%]",id,true));
                    }
                }else if(identDataType.get(id.lexemeRank(2)).equals("Numero_Decimal")){
                    if(identDataType.get(id.lexemeRank(4)).equals("Numero_Entero") || identDataType.get(id.lexemeRank(6)).equals("Numero_Entero") || identDataType.get(id.lexemeRank(8)).equals("Numero_Entero")){
                        errors.add(new ErrorLSSL(59,"----------> ERROR_59:  Se están empleando variables de tipos de datos distintos para la función, Linea [#] Columna [%]",id,true));     
                    }
                }
            }     
        }//4 Variables
        //5 Variables
        for(Production id: identProdF14){
            if(identDataType.containsKey(id.lexemeRank(2)) && identDataType.containsKey(id.lexemeRank(4)) && identDataType.containsKey(id.lexemeRank(6)) && identDataType.containsKey(id.lexemeRank(8)) && identDataType.containsKey(id.lexemeRank(10))){
                if(identDataType.get(id.lexemeRank(2)).equals("Numero_Entero")){
                    if(identDataType.get(id.lexemeRank(4)).equals("Numero_Decimal") || identDataType.get(id.lexemeRank(6)).equals("Numero_Decimal") || identDataType.get(id.lexemeRank(8)).equals("Numero_Decimal") || identDataType.get(id.lexemeRank(10)).equals("Numero_Decimal")){
                        errors.add(new ErrorLSSL(59,"----------> ERROR_59:  Se están empleando variables de tipos de datos distintos para la función, Linea [#] Columna [%]",id,true));
                    }
                }else if(identDataType.get(id.lexemeRank(2)).equals("Numero_Decimal")){
                    if(identDataType.get(id.lexemeRank(4)).equals("Numero_Entero") || identDataType.get(id.lexemeRank(6)).equals("Numero_Entero") || identDataType.get(id.lexemeRank(8)).equals("Numero_Entero") || identDataType.get(id.lexemeRank(10)).equals("Numero_Entero")){
                        errors.add(new ErrorLSSL(59,"----------> ERROR_59:  Se están empleando variables de tipos de datos distintos para la función, Linea [#] Columna [%]",id,true));     
                    }
                }
            } 
        }//5 Variables
        //Error 59
        //Error 60 y Error 61
        //Funciones de Sumar, Restar, Multiplicar y Dividir
        //3 Variables
        for(Production id: identProdF11){
            int i = 0;
            if(!identDataType.containsKey(id.lexemeRank(2))){
                i++;
            }
            if(!identDataType.containsKey(id.lexemeRank(4))){
                i++;
            }
            if(!identDataType.containsKey(id.lexemeRank(7))){
                i++;
            }
            if(i == 1){
                errors.add(new ErrorLSSL(60, "----------> ERROR_60:  Una de las variables empleadas en la función no ha sido declarada, Linea [#] Columna [%]", id, true));
            }else if(i > 1){
                errors.add(new ErrorLSSL(61, "----------> ERROR_61:  Varias variables empleadas en la función no han sido declaradas, Linea [#] Columna [%]", id, true));
            }
        }//3 Variables
        //4 Variables
        for(Production id: identProdF12){
            int i = 0;
            if(!identDataType.containsKey(id.lexemeRank(2))){
                i++;
            }
            if(!identDataType.containsKey(id.lexemeRank(4))){
                i++;
            }
            if(!identDataType.containsKey(id.lexemeRank(6))){
                i++;
            }
            if(!identDataType.containsKey(id.lexemeRank(9))){
                i++;
            }
            if(i == 1){
                errors.add(new ErrorLSSL(60, "----------> ERROR_60:  Una de las variables empleadas en la función no ha sido declarada, Linea [#] Columna [%]", id, true));
            }else if(i > 1){
                errors.add(new ErrorLSSL(61, "----------> ERROR_61:  Varias variables empleadas en la función no han sido declaradas, Linea [#] Columna [%]", id, true));
            }
        }//4 Variables
        //5 Variables
        for(Production id: identProdF13){
            int i = 0;
            if(!identDataType.containsKey(id.lexemeRank(2))){
                i++;
            }
            if(!identDataType.containsKey(id.lexemeRank(4))){
                i++;
            }
            if(!identDataType.containsKey(id.lexemeRank(6))){
                i++;
            }
            if(!identDataType.containsKey(id.lexemeRank(8))){
                i++;
            }
            if(!identDataType.containsKey(id.lexemeRank(11))){
                i++;
            }
            if(i == 1){
                errors.add(new ErrorLSSL(60, "----------> ERROR_60:  Una de las variables empleadas en la función no ha sido declarada, Linea [#] Columna [%]", id, true));
            }else if(i > 1){
                errors.add(new ErrorLSSL(61, "----------> ERROR_61:  Varias variables empleadas en la función no han sido declaradas, Linea [#] Columna [%]", id, true));
            }
        }//5 Variables
        //6 Variables
        for(Production id: identProdF14){
            int i = 0;
            if(!identDataType.containsKey(id.lexemeRank(2))){
                i++;
            }
            if(!identDataType.containsKey(id.lexemeRank(4))){
                i++;
            }
            if(!identDataType.containsKey(id.lexemeRank(6))){
                i++;
            }
            if(!identDataType.containsKey(id.lexemeRank(8))){
                i++;
            }
            if(!identDataType.containsKey(id.lexemeRank(10))){
                i++;
            }
            if(!identDataType.containsKey(id.lexemeRank(13))){
                i++;
            }
            if(i == 1){
                errors.add(new ErrorLSSL(60, "----------> ERROR_60:  Una de las variables empleadas en la función no ha sido declarada, Linea [#] Columna [%]", id, true));
            }else if(i > 1){
                errors.add(new ErrorLSSL(61, "----------> ERROR_61:  Varias variables empleadas en la función no han sido declaradas, Linea [#] Columna [%]", id, true));
            }
        }//6 Variables
        //Funciones de Sumar, Restar, Multiplicar y Dividir
        //Funciones de Mostrar (Entero)
        for(Production id: identProdF3){
            int i = 0;
            if(!identDataType.containsKey(id.lexemeRank(2))){
                i++;
            }
            if(!identDataType.containsKey(id.lexemeRank(4))){
                i++;
            }
            if(!identDataType.containsKey(id.lexemeRank(6))){
                i++;
            }
            if(!identDataType.containsKey(id.lexemeRank(8))){
                i++;
            }
            if(i == 1){
                errors.add(new ErrorLSSL(60, "----------> ERROR_60:  Una de las variables empleadas en la función no ha sido declarada, Linea [#] Columna [%]", id, true));
            }else if(i > 1){
                errors.add(new ErrorLSSL(61, "----------> ERROR_61:  Varias variables empleadas en la función no han sido declaradas, Linea [#] Columna [%]", id, true));
            }
        }
        //Funciones de Mostrar (Entero)
        //Funciones de Mostrar (Decimal)
        for(Production id: identProdF2){
            int i = 0;
            if(!identDataType.containsKey(id.lexemeRank(2))){
                i++;
            }
            if(!identDataType.containsKey(id.lexemeRank(4))){
                i++;
            }
            if(!identDataType.containsKey(id.lexemeRank(6))){
                i++;
            }
            if(!identDataType.containsKey(id.lexemeRank(8))){
                i++;
            }
            if(!identDataType.containsKey(id.lexemeRank(10))){
                i++;
            }
            if(!identDataType.containsKey(id.lexemeRank(12))){
                i++;
            }
            if(!identDataType.containsKey(id.lexemeRank(14))){
                i++;
            }
            if(!identDataType.containsKey(id.lexemeRank(16))){
                i++;
            }
            if(!identDataType.containsKey(id.lexemeRank(18))){
                i++;
            }
            if(!identDataType.containsKey(id.lexemeRank(20))){
                i++;
            }
            if(!identDataType.containsKey(id.lexemeRank(22))){
                i++;
            }
            if(!identDataType.containsKey(id.lexemeRank(24))){
                i++;
            }
            if(i == 1){
                errors.add(new ErrorLSSL(60, "----------> ERROR_60:  Una de las variables empleadas en la función no ha sido declarada, Linea [#] Columna [%]", id, true));
            }else if(i > 1){
                errors.add(new ErrorLSSL(61, "----------> ERROR_61:  Varias variables empleadas en la función no han sido declaradas, Linea [#] Columna [%]", id, true));
            }
        }
        //Funciones de Mostrar (Decimal)
        //Error 60 y Error 61
        //Error 62 y Error 63 (Primera Parte)
        //Funciones de Sumar, Restar, Multiplicar y Dividir
        //2 Variables
        for(Production id: identProdF11){
            int i = 0;
            if(!identDataTypeV.containsKey(id.lexemeRank(2))){
                i++;
            }
            if(!identDataTypeV.containsKey(id.lexemeRank(4))){
                i++;
            }
            if(i == 1){
                errors.add(new ErrorLSSL(62, "----------> ERROR_62:  Una de las variables empleadas en la función no tiene valor asignado, Linea [#] Columna [%]", id, true));
            }else if(i > 1){
                errors.add(new ErrorLSSL(63, "----------> ERROR_63:  Varias variables empleadas en la función no tienen valor asignado, Linea [#] Columna [%]", id, true));
            }
        }//2 Variables
        //3 Variables
        for(Production id: identProdF12){
            int i = 0;
            if(!identDataTypeV.containsKey(id.lexemeRank(2))){
                i++;
            }
            if(!identDataTypeV.containsKey(id.lexemeRank(4))){
                i++;
            }
            if(!identDataTypeV.containsKey(id.lexemeRank(6))){
                i++;
            }
            if(i == 1){
                errors.add(new ErrorLSSL(62, "----------> ERROR_62:  Una de las variables empleadas en la función no tiene valor asignado, Linea [#] Columna [%]", id, true));
            }else if(i > 1){
                errors.add(new ErrorLSSL(63, "----------> ERROR_63:  Varias variables empleadas en la función no tienen valor asignado, Linea [#] Columna [%]", id, true));
            }
        }//3 Variables
        //4 Variables
        for(Production id: identProdF13){
            int i = 0;
            if(!identDataTypeV.containsKey(id.lexemeRank(2))){
                i++;
            }
            if(!identDataTypeV.containsKey(id.lexemeRank(4))){
                i++;
            }
            if(!identDataTypeV.containsKey(id.lexemeRank(6))){
                i++;
            }
            if(!identDataTypeV.containsKey(id.lexemeRank(8))){
                i++;
            }
            if(i == 1){
                errors.add(new ErrorLSSL(62, "----------> ERROR_62:  Una de las variables empleadas en la función no tiene valor asignado, Linea [#] Columna [%]", id, true));
            }else if(i > 1){
                errors.add(new ErrorLSSL(63, "----------> ERROR_63:  Varias variables empleadas en la función no tienen valor asignado, Linea [#] Columna [%]", id, true));
            }
        }//4 Variables
        //5 Variables
        for(Production id: identProdF14){
            int i = 0;
            if(!identDataTypeV.containsKey(id.lexemeRank(2))){
                i++;
            }
            if(!identDataTypeV.containsKey(id.lexemeRank(4))){
                i++;
            }
            if(!identDataTypeV.containsKey(id.lexemeRank(6))){
                i++;
            }
            if(!identDataTypeV.containsKey(id.lexemeRank(8))){
                i++;
            }
            if(!identDataTypeV.containsKey(id.lexemeRank(10))){
                i++;
            }
            if(i == 1){
                errors.add(new ErrorLSSL(62, "----------> ERROR_62:  Una de las variables empleadas en la función no tiene valor asignado, Linea [#] Columna [%]", id, true));
            }else if(i > 1){
                errors.add(new ErrorLSSL(63, "----------> ERROR_63:  Varias variables empleadas en la función no tienen valor asignado, Linea [#] Columna [%]", id, true));
            }
        }//5 Variables
        //Funciones de Sumar, Restar, Multiplicar y Dividir
        //Error 62 y Error 63 (Primera Parte)
        //Error 66 y Error 67
        //Funciones de Sumar, Restar, Multiplicar y Dividir
        //2 Variables/
        for(Production id: identProdF11){
            if(identDataTypeV.containsKey(id.lexemeRank(2)) && identDataTypeV.containsKey(id.lexemeRank(4)) && identDataTypeE.containsKey(id.lexemeRank(2)) && identDataTypeE.containsKey(id.lexemeRank(4))){
                identDataTypeV2.put(id.lexemeRank(2), identDataTypeE.get(id.lexemeRank(2))+identDataTypeV.get(id.lexemeRank(2)));
                identDataTypeV2.put(id.lexemeRank(4), identDataTypeE.get(id.lexemeRank(4))+identDataTypeV.get(id.lexemeRank(4)));
            }
        }
        for(Production id: identProdF11){
            if(identDataType.get(id.lexemeRank(2)).equals(identDataType.get(id.lexemeRank(4)))){
                if(identDataTypeV2.containsKey(id.lexemeRank(2)) && identDataTypeV2.containsKey(id.lexemeRank(4))){
                    if(identDataType.get(id.lexemeRank(2)).equals("Numero_Entero")){
                        int val1 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(2)));
                        int val2 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(4)));
                        int res;
                        if(id.lexemeRank(0).equals("Sumar")){
                            res = val1 + val2;
                        }else if(id.lexemeRank(0).equals("Restar")){
                            res = val1 - val2;
                        }else if(id.lexemeRank(0).equals("Multiplicar")){
                            res = val1 * val2;
                        }else{
                            res = val1 / val2;
                        }
                        if(res > 200000 || res < -200000){
                            errors.add(new ErrorLSSL(66, "----------> ERROR_66:  El valor del resultado de la operación realizada en la función no está en el rango permitido, Linea [#] Columna [%]", id, true));
                        }else{
                            if(identDataTypeR.containsKey(id.lexemeRank(7))){
                                errors.add(new ErrorLSSL(67, "----------> ERROR_67:  La variable ya tiene un valor asignado, Linea [#] Columna [%]", id, true));
                            }else{
                                identDataTypeR.put(id.lexemeRank(7),Integer.toString(res));
                                if(identDataTypeR2.containsKey(Integer.toString(res))){
                                    
                                }else{
                                    identDataTypeR2.put(Integer.toString(res),id.lexemeRank(7));
                                }
                                    
                                identDataType.put(id.lexemeRank(7),"Numero_Entero");
                            }
                        }
                    }else{
                        double val1 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(2)));
                        double val2 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(4)));
                        double res;
                        if(id.lexemeRank(0).equals("Sumar")){
                            res = val1 + val2;
                        }else if(id.lexemeRank(0).equals("Restar")){
                            res = val1 - val2;
                        }else if(id.lexemeRank(0).equals("Multiplicar")){
                            res = val1 * val2;
                        }else{
                            res = val1 / val2;
                        }
                        if(res > 199.9999 || res < -199.9999){
                            errors.add(new ErrorLSSL(66, "----------> ERROR_66:  El valor del resultado de la operación realizada en la función no está en el rango permitido, Linea [#] Columna [%]", id, true));
                        }else{
                            if(identDataTypeR.containsKey(id.lexemeRank(7))){
                                errors.add(new ErrorLSSL(67, "----------> ERROR_67:  La variable ya tiene un valor asignado, Linea [#] Columna [%]", id, true));
                            }else{
                                identDataTypeR.put(id.lexemeRank(7),Double.toString(res));
                                if(identDataTypeR2.containsKey(Double.toString(res))){
                                    
                                }else{
                                    identDataTypeR2.put(Double.toString(res),id.lexemeRank(7));
                                }
                                identDataType.put(id.lexemeRank(7),"Numero_Decimal");
                            }
                        }
                    }
                }
            }
        }//2 Variables
        //3 Variables
        for(Production id: identProdF12){
            if(identDataTypeV.containsKey(id.lexemeRank(2)) && identDataTypeV.containsKey(id.lexemeRank(4)) && identDataTypeV.containsKey(id.lexemeRank(6)) && identDataTypeE.containsKey(id.lexemeRank(2)) && identDataTypeE.containsKey(id.lexemeRank(4)) && identDataTypeE.containsKey(id.lexemeRank(6))){
                identDataTypeV2.put(id.lexemeRank(2), identDataTypeE.get(id.lexemeRank(2))+identDataTypeV.get(id.lexemeRank(2)));
                identDataTypeV2.put(id.lexemeRank(4), identDataTypeE.get(id.lexemeRank(4))+identDataTypeV.get(id.lexemeRank(4)));
                identDataTypeV2.put(id.lexemeRank(6), identDataTypeE.get(id.lexemeRank(4))+identDataTypeV.get(id.lexemeRank(6)));
            }
        }
        for(Production id: identProdF12){
            if(identDataType.get(id.lexemeRank(2)).equals(identDataType.get(id.lexemeRank(4))) && identDataType.get(id.lexemeRank(2)).equals(identDataType.get(id.lexemeRank(6)))){
                if(identDataTypeV2.containsKey(id.lexemeRank(2)) && identDataTypeV2.containsKey(id.lexemeRank(4)) && identDataTypeV2.containsKey(id.lexemeRank(6))){
                    if(identDataType.get(id.lexemeRank(2)).equals("Numero_Entero")){
                        int val1 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(2)));
                        int val2 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(4)));
                        int val3 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(6)));
                        int res;
                        if(id.lexemeRank(0).equals("Sumar")){
                            res = val1 + val2 + val3;
                        }else if(id.lexemeRank(0).equals("Restar")){
                            res = val1 - val2 - val3;
                        }else if(id.lexemeRank(0).equals("Multiplicar")){
                            res = val1 * val2 * val3;
                        }else{
                            res = val1 / val2 / val3;
                        }
                        if(res > 200000 || res < -200000){
                            errors.add(new ErrorLSSL(66, "----------> ERROR_66:  El valor del resultado de la operación realizada en la función no está en el rango permitido, Linea [#] Columna [%]", id, true));
                        }else{
                            if(identDataTypeR.containsKey(id.lexemeRank(9))){
                                errors.add(new ErrorLSSL(67, "----------> ERROR_67:  La variable ya tiene un valor asignado, Linea [#] Columna [%]", id, true));
                            }else{
                                identDataTypeR.put(id.lexemeRank(9),Integer.toString(res));
                                if(identDataTypeR2.containsKey(Integer.toString(res))){
                                
                                }else{
                                    identDataTypeR2.put(Integer.toString(res),id.lexemeRank(9));
                                } 
                                identDataType.put(id.lexemeRank(9),"Numero_Entero");
                            }
                        }
                    }else{
                        double val1 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(2)));
                        double val2 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(4)));
                        double val3 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(6)));
                        double res;
                        if(id.lexemeRank(0).equals("Sumar")){
                            res = val1 + val2 + val3;
                        }else if(id.lexemeRank(0).equals("Restar")){
                            res = val1 - val2 - val3;
                        }else if(id.lexemeRank(0).equals("Multiplicar")){
                            res = val1 * val2 * val3;
                        }else{
                            res = val1 / val2 / val3;
                        }
                        if(res > 199.9999 || res < -199.9999){
                            errors.add(new ErrorLSSL(66, "----------> ERROR_66:  El valor del resultado de la operación realizada en la función no está en el rango permitido, Linea [#] Columna [%]", id, true));
                        }else{
                            if(identDataTypeR.containsKey(id.lexemeRank(9))){
                                errors.add(new ErrorLSSL(67, "----------> ERROR_67:  La variable ya tiene un valor asignado, Linea [#] Columna [%]", id, true));
                            }else{
                                identDataTypeR.put(id.lexemeRank(9),Double.toString(res));
                                if(identDataTypeR2.containsKey(Double.toString(res))){
                                    
                                }else{
                                    identDataTypeR2.put(Double.toString(res),id.lexemeRank(9));
                                }  
                                identDataType.put(id.lexemeRank(9),"Numero_Decimal");
                            }
                        }
                    }
                }
            }
        }//3 Variables
        //4 Variables
        for(Production id: identProdF13){
            if(identDataTypeV.containsKey(id.lexemeRank(2)) && identDataTypeV.containsKey(id.lexemeRank(4)) && identDataTypeV.containsKey(id.lexemeRank(6)) && identDataTypeV.containsKey(id.lexemeRank(8)) && identDataTypeE.containsKey(id.lexemeRank(2)) && identDataTypeE.containsKey(id.lexemeRank(4)) && identDataTypeE.containsKey(id.lexemeRank(6)) && identDataTypeE.containsKey(id.lexemeRank(8))){
                identDataTypeV2.put(id.lexemeRank(2), identDataTypeE.get(id.lexemeRank(2))+identDataTypeV.get(id.lexemeRank(2)));
                identDataTypeV2.put(id.lexemeRank(4), identDataTypeE.get(id.lexemeRank(4))+identDataTypeV.get(id.lexemeRank(4)));
                identDataTypeV2.put(id.lexemeRank(6), identDataTypeE.get(id.lexemeRank(6))+identDataTypeV.get(id.lexemeRank(6)));
                identDataTypeV2.put(id.lexemeRank(8), identDataTypeE.get(id.lexemeRank(8))+identDataTypeV.get(id.lexemeRank(8)));
            }
        }
        for(Production id: identProdF13){
            if(identDataType.get(id.lexemeRank(2)).equals(identDataType.get(id.lexemeRank(4))) && identDataType.get(id.lexemeRank(2)).equals(identDataType.get(id.lexemeRank(6))) && identDataType.get(id.lexemeRank(2)).equals(identDataType.get(id.lexemeRank(8)))){
                if(identDataTypeV2.containsKey(id.lexemeRank(2)) && identDataTypeV2.containsKey(id.lexemeRank(4)) && identDataTypeV2.containsKey(id.lexemeRank(6)) && identDataTypeV2.containsKey(id.lexemeRank(8))){
                    if(identDataType.get(id.lexemeRank(2)).equals("Numero_Entero")){
                        int val1 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(2)));
                        int val2 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(4)));
                        int val3 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(6)));
                        int val4 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(8)));
                        int res;
                        if(id.lexemeRank(0).equals("Sumar")){
                            res = val1 + val2 + val3 + val4;
                        }else if(id.lexemeRank(0).equals("Restar")){
                            res = val1 - val2 - val3 - val4;
                        }else if(id.lexemeRank(0).equals("Multiplicar")){
                            res = val1 * val2 * val3 * val4;
                        }else{
                            res = val1 / val2 / val3 / val4;
                        }                   
                        if(res > 200000 || res < -200000){
                            errors.add(new ErrorLSSL(66, "----------> ERROR_66:  El valor del resultado de la operación realizada en la función no está en el rango permitido, Linea [#] Columna [%]", id, true));
                        }else{
                            if(identDataTypeR.containsKey(id.lexemeRank(11))){
                                errors.add(new ErrorLSSL(67, "----------> ERROR_67:  La variable ya tiene un valor asignado, Linea [#] Columna [%]", id, true));
                            }else{
                                identDataTypeR.put(id.lexemeRank(11),Integer.toString(res));
                                if(identDataTypeR2.containsKey(Integer.toString(res))){
                                    
                                }else{
                                    identDataTypeR2.put(Integer.toString(res),id.lexemeRank(11));
                                }   
                                identDataType.put(id.lexemeRank(11),"Numero_Entero");
                            }
                        }
                    }else{
                        double val1 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(2)));
                        double val2 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(4)));
                        double val3 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(6)));
                        double val4 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(8)));
                        double res;
                        if(id.lexemeRank(0).equals("Sumar")){
                            res = val1 + val2 + val3 + val4;
                        }else if(id.lexemeRank(0).equals("Restar")){
                            res = val1 - val2 - val3 - val4;
                        }else if(id.lexemeRank(0).equals("Multiplicar")){
                            res = val1 * val2 * val3 * val4;
                        }else{
                            res = val1 / val2 / val3 * val4;
                        }
                        if(res > 199.9999 || res < -199.9999){
                            errors.add(new ErrorLSSL(66, "----------> ERROR_66:  El valor del resultado de la operación realizada en la función no está en el rango permitido, Linea [#] Columna [%]", id, true));
                        }else{
                            if(identDataTypeR.containsKey(id.lexemeRank(11))){
                                errors.add(new ErrorLSSL(67, "----------> ERROR_67:  La variable ya tiene un valor asignado, Linea [#] Columna [%]", id, true));
                            }else{
                                identDataTypeR.put(id.lexemeRank(11),Double.toString(res));
                                if(identDataTypeR2.containsKey(Double.toString(res))){
                                    
                                }else{
                                    identDataTypeR2.put(Double.toString(res),id.lexemeRank(11));
                                }
                                identDataType.put(id.lexemeRank(11),"Numero_Decimal");
                            }
                        }
                    }
                }
            }
        }//4 Variables
        //5 Variables
        for(Production id: identProdF14){
            if(identDataTypeV.containsKey(id.lexemeRank(2)) && identDataTypeV.containsKey(id.lexemeRank(4)) && identDataTypeV.containsKey(id.lexemeRank(6)) && identDataTypeV.containsKey(id.lexemeRank(8)) && identDataTypeV.containsKey(id.lexemeRank(10)) && identDataTypeE.containsKey(id.lexemeRank(2)) && identDataTypeE.containsKey(id.lexemeRank(4)) && identDataTypeE.containsKey(id.lexemeRank(6)) && identDataTypeE.containsKey(id.lexemeRank(8)) && identDataTypeE.containsKey(id.lexemeRank(10))){
                identDataTypeV2.put(id.lexemeRank(2), identDataTypeE.get(id.lexemeRank(2))+identDataTypeV.get(id.lexemeRank(2)));
                identDataTypeV2.put(id.lexemeRank(4), identDataTypeE.get(id.lexemeRank(4))+identDataTypeV.get(id.lexemeRank(4)));
                identDataTypeV2.put(id.lexemeRank(6), identDataTypeE.get(id.lexemeRank(6))+identDataTypeV.get(id.lexemeRank(6)));
                identDataTypeV2.put(id.lexemeRank(8), identDataTypeE.get(id.lexemeRank(8))+identDataTypeV.get(id.lexemeRank(8)));
                identDataTypeV2.put(id.lexemeRank(10), identDataTypeE.get(id.lexemeRank(10))+identDataTypeV.get(id.lexemeRank(10)));
            }
        }
        for(Production id: identProdF14){
            if(identDataType.get(id.lexemeRank(2)).equals(identDataType.get(id.lexemeRank(4))) && identDataType.get(id.lexemeRank(2)).equals(identDataType.get(id.lexemeRank(6))) && identDataType.get(id.lexemeRank(2)).equals(identDataType.get(id.lexemeRank(8))) && identDataType.get(id.lexemeRank(2)).equals(identDataType.get(id.lexemeRank(10)))){
                if(identDataTypeV2.containsKey(id.lexemeRank(2)) && identDataTypeV2.containsKey(id.lexemeRank(4)) && identDataTypeV2.containsKey(id.lexemeRank(6)) && identDataTypeV2.containsKey(id.lexemeRank(8)) && identDataTypeV2.containsKey(id.lexemeRank(10))){
                    if(identDataType.get(id.lexemeRank(2)).equals("Numero_Entero")){
                        int val1 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(2)));
                        int val2 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(4)));
                        int val3 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(6)));
                        int val4 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(8)));
                        int val5 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(10)));
                        int res;
                        if(id.lexemeRank(0).equals("Sumar")){
                            res = val1 + val2 + val3 + val4 + val5;
                        }else if(id.lexemeRank(0).equals("Restar")){
                            res = val1 - val2 - val3 - val4 - val5;
                        }else if(id.lexemeRank(0).equals("Multiplicar")){
                            res = val1 * val2 * val3 * val4 * val5;
                        }else{
                            res = val1 / val2 / val3 / val4 / val5;
                        }
                        if(res > 200000 || res < -200000){
                            errors.add(new ErrorLSSL(66, "----------> ERROR_66:  El valor del resultado de la operación realizada en la función no está en el rango permitido, Linea [#] Columna [%]", id, true));
                        }else{
                            if(identDataTypeR.containsKey(id.lexemeRank(13))){
                                errors.add(new ErrorLSSL(67, "----------> ERROR_67:  La variable ya tiene un valor asignado, Linea [#] Columna [%]", id, true));
                            }else{
                                identDataTypeR.put(id.lexemeRank(13),Integer.toString(res));
                                if(identDataTypeR2.containsKey(Integer.toString(res))){
                                    
                                }else{
                                    identDataTypeR2.put(Integer.toString(res),id.lexemeRank(13));
                                }
                                identDataType.put(id.lexemeRank(13),"Numero_Entero");
                            }
                        }
                    }else{
                        double val1 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(2)));
                        double val2 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(4)));
                        double val3 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(6)));
                        double val4 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(8)));
                        double val5 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(10)));
                        double res;
                        if(id.lexemeRank(0).equals("Sumar")){
                            res = val1 + val2 + val3 + val4 + val5;
                        }else if(id.lexemeRank(0).equals("Restar")){
                            res = val1 - val2 - val3 - val4 - val5;
                        }else if(id.lexemeRank(0).equals("Multiplicar")){
                            res = val1 * val2 * val3 * val4 * val5;
                        }else{
                            res = val1 / val2 / val3 / val4 / val5;
                        }
                        if(res > 199.9999 || res < -199.9999){
                            errors.add(new ErrorLSSL(66, "----------> ERROR_66:  El valor del resultado de la operación realizada en la función no está en el rango permitido, Linea [#] Columna [%]", id, true));
                        }else{
                            if(identDataTypeR.containsKey(id.lexemeRank(13))){
                                errors.add(new ErrorLSSL(67, "----------> ERROR_67:  La variable ya tiene un valor asignado, Linea [#] Columna [%]", id, true));
                            }else{
                                identDataTypeR.put(id.lexemeRank(13),Double.toString(res));
                                if(identDataTypeR2.containsKey(Double.toString(res))){
                                    
                                }else{
                                    identDataTypeR2.put(Double.toString(res),id.lexemeRank(13));
                                }
                                identDataType.put(id.lexemeRank(13),"Numero_Decimal");
                            }
                        }
                    }
                }
            }    
        }//5 Variables
        //Funciones de Sumar, Restar, Multiplicar y Dividir
        //Error 66 y Error 67
        //Error 62 y Error 63 (Segunda Parte)
        //Funcion de mostrar (Entero)
        for(Production id: identProdF3){
            int i = 0;
            if(!identDataTypeV.containsKey(id.lexemeRank(2))){
                i++;
            }
            if(!identDataTypeR.containsKey(id.lexemeRank(4))){
                i++;
            }
            if(!identDataTypeV.containsKey(id.lexemeRank(6))){
                i++;
            }
            if(!identDataTypeV.containsKey(id.lexemeRank(8))){
                i++;
            }
            if(i == 1){
                errors.add(new ErrorLSSL(62, "----------> ERROR_62:  Una de las variables empleadas en la función no tiene valor asignado, Linea [#] Columna [%]", id, true));
            }else if(i > 1){
                errors.add(new ErrorLSSL(63, "----------> ERROR_63:  Varias variables empleadas en la función no tienen valor asignado, Linea [#] Columna [%]", id, true));
            }
        }//Funcion de mostrar (Entero)
        //Funcion de mostrar (Decimal)
        for(Production id: identProdF2){
            int i = 0;
            if(!identDataTypeV.containsKey(id.lexemeRank(2))){
                i++;
            }
            if(!identDataTypeR.containsKey(id.lexemeRank(4))){
                i++;
            }
            if(!identDataTypeV.containsKey(id.lexemeRank(6))){
                i++;
            }
            if(!identDataTypeV.containsKey(id.lexemeRank(8))){
                i++;
            }
            if(!identDataTypeV.containsKey(id.lexemeRank(10))){
                i++;
            }
            if(!identDataTypeV.containsKey(id.lexemeRank(12))){
                i++;
            }
            if(!identDataTypeV.containsKey(id.lexemeRank(14))){
                i++;
            }
            if(!identDataTypeV.containsKey(id.lexemeRank(16))){
                i++;
            }
            if(!identDataTypeV.containsKey(id.lexemeRank(18))){
                i++;
            }
            if(!identDataTypeV.containsKey(id.lexemeRank(20))){
                i++;
            }
            if(!identDataTypeV.containsKey(id.lexemeRank(22))){
                i++;
            }
            if(!identDataTypeV.containsKey(id.lexemeRank(24))){
                i++;
            }
            if(i == 1){
                errors.add(new ErrorLSSL(62, "----------> ERROR_62:  Una de las variables empleadas en la función no tiene valor asignado, Linea [#] Columna [%]", id, true));
            }else if(i > 1){
                errors.add(new ErrorLSSL(63, "----------> ERROR_63:  Varias variables empleadas en la función no tienen valor asignado, Linea [#] Columna [%]", id, true));
            }
        }//Funcion de mostrar (Decimal)
        //Error 62 y Error 63 (Segunda Parte)
        //Error 64 y Error 65 (Segunda Parte)
        //Funciones de Mostrar (Entero)
        for(Production id: identProdF3){
            if(identDataType.containsKey(id.lexemeRank(2)) && identDataType.containsKey(id.lexemeRank(4)) && identDataType.containsKey(id.lexemeRank(6)) && identDataType.containsKey(id.lexemeRank(8))){
                int i = 0;
                if(!identDataType.get(id.lexemeRank(2)).equals("Identificador_Cadena")){
                    i++;
                }
                if(!identDataType.get(id.lexemeRank(4)).equals("Numero_Entero")){
                    i++;
                }
                if(!identDataType.get(id.lexemeRank(6)).equals("Palabra_Reservada_17") && !identDataType.get(id.lexemeRank(6)).equals("Palabra_Reservada_18") && !identDataType.get(id.lexemeRank(6)).equals("Palabra_Reservada_19") && !identDataType.get(id.lexemeRank(6)).equals("Palabra_Reservada_20") && !identDataType.get(id.lexemeRank(6)).equals("Palabra_Reservada_21")){
                    i++;
                }
                if(!identDataType.get(id.lexemeRank(8)).equals("Palabra_Reservada_12") && !identDataType.get(id.lexemeRank(8)).equals("Palabra_Reservada_13") && !identDataType.get(id.lexemeRank(8)).equals("Palabra_Reservada_14") && !identDataType.get(id.lexemeRank(8)).equals("Palabra_Reservada_15") && !identDataType.get(id.lexemeRank(8)).equals("Palabra_Reservada_16")){
                    i++;
                }
                if(i == 1){
                    errors.add(new ErrorLSSL(64, "----------> ERROR_64:  El valor de una variable empleada en la función no está permitido, Linea [#] Columna [%]", id, true));
                }else if(i > 1){
                    errors.add(new ErrorLSSL(65, "----------> ERROR_65:  El valor de varias variables empleadas en la función no están permitidos, Linea [#] Columna [%]", id, true));
                }
            }
        }//Funciones de Mostrar (Entero)
        //Funciones de Mostrar (Decimal)
        for(Production id: identProdF2){
            if(identDataType.containsKey(id.lexemeRank(2)) && identDataType.containsKey(id.lexemeRank(4)) && identDataType.containsKey(id.lexemeRank(6)) && identDataType.containsKey(id.lexemeRank(8)) && identDataType.containsKey(id.lexemeRank(10)) && identDataType.containsKey(id.lexemeRank(12)) && identDataType.containsKey(id.lexemeRank(14)) && identDataType.containsKey(id.lexemeRank(16)) && identDataType.containsKey(id.lexemeRank(18)) && identDataType.containsKey(id.lexemeRank(20)) && identDataType.containsKey(id.lexemeRank(22)) && identDataType.containsKey(id.lexemeRank(24))){
                int i = 0;
                if(!identDataType.get(id.lexemeRank(2)).equals("Identificador_Cadena")){
                    i++;
                }
                if(!identDataType.get(id.lexemeRank(4)).equals("Numero_Decimal")){
                    i++;
                }
                if(!identDataType.get(id.lexemeRank(6)).equals("Palabra_Reservada_17") && !identDataType.get(id.lexemeRank(6)).equals("Palabra_Reservada_18") && !identDataType.get(id.lexemeRank(6)).equals("Palabra_Reservada_19") && !identDataType.get(id.lexemeRank(6)).equals("Palabra_Reservada_20") && !identDataType.get(id.lexemeRank(6)).equals("Palabra_Reservada_21")){
                    i++;
                }
                if(!identDataType.get(id.lexemeRank(8)).equals("Palabra_Reservada_12") && !identDataType.get(id.lexemeRank(8)).equals("Palabra_Reservada_13") && !identDataType.get(id.lexemeRank(8)).equals("Palabra_Reservada_14") && !identDataType.get(id.lexemeRank(8)).equals("Palabra_Reservada_15") && !identDataType.get(id.lexemeRank(8)).equals("Palabra_Reservada_16")){
                    i++;
                }
                if(!identDataType.get(id.lexemeRank(10)).equals("Palabra_Reservada_17") && !identDataType.get(id.lexemeRank(10)).equals("Palabra_Reservada_18") && !identDataType.get(id.lexemeRank(10)).equals("Palabra_Reservada_19") && !identDataType.get(id.lexemeRank(10)).equals("Palabra_Reservada_20") && !identDataType.get(id.lexemeRank(10)).equals("Palabra_Reservada_21")){
                    i++;
                }
                if(!identDataType.get(id.lexemeRank(12)).equals("Palabra_Reservada_12") && !identDataType.get(id.lexemeRank(12)).equals("Palabra_Reservada_13") && !identDataType.get(id.lexemeRank(12)).equals("Palabra_Reservada_14") && !identDataType.get(id.lexemeRank(12)).equals("Palabra_Reservada_15") && !identDataType.get(id.lexemeRank(12)).equals("Palabra_Reservada_16")){
                    i++;
                }
                if(!identDataType.get(id.lexemeRank(14)).equals("Palabra_Reservada_17") && !identDataType.get(id.lexemeRank(14)).equals("Palabra_Reservada_18") && !identDataType.get(id.lexemeRank(14)).equals("Palabra_Reservada_19") && !identDataType.get(id.lexemeRank(14)).equals("Palabra_Reservada_20") && !identDataType.get(id.lexemeRank(14)).equals("Palabra_Reservada_21")){
                    i++;
                }
                if(!identDataType.get(id.lexemeRank(16)).equals("Palabra_Reservada_12") && !identDataType.get(id.lexemeRank(16)).equals("Palabra_Reservada_13") && !identDataType.get(id.lexemeRank(16)).equals("Palabra_Reservada_14") && !identDataType.get(id.lexemeRank(16)).equals("Palabra_Reservada_15") && !identDataType.get(id.lexemeRank(16)).equals("Palabra_Reservada_16")){
                    i++;
                }
                if(!identDataType.get(id.lexemeRank(18)).equals("Palabra_Reservada_17") && !identDataType.get(id.lexemeRank(18)).equals("Palabra_Reservada_18") && !identDataType.get(id.lexemeRank(18)).equals("Palabra_Reservada_19") && !identDataType.get(id.lexemeRank(18)).equals("Palabra_Reservada_20") && !identDataType.get(id.lexemeRank(18)).equals("Palabra_Reservada_21")){
                    i++;
                }
                if(!identDataType.get(id.lexemeRank(20)).equals("Palabra_Reservada_12") && !identDataType.get(id.lexemeRank(20)).equals("Palabra_Reservada_13") && !identDataType.get(id.lexemeRank(20)).equals("Palabra_Reservada_14") && !identDataType.get(id.lexemeRank(20)).equals("Palabra_Reservada_15") && !identDataType.get(id.lexemeRank(20)).equals("Palabra_Reservada_16")){
                    i++;
                }
                if(!identDataType.get(id.lexemeRank(22)).equals("Palabra_Reservada_17") && !identDataType.get(id.lexemeRank(22)).equals("Palabra_Reservada_18") && !identDataType.get(id.lexemeRank(22)).equals("Palabra_Reservada_19") && !identDataType.get(id.lexemeRank(22)).equals("Palabra_Reservada_20") && !identDataType.get(id.lexemeRank(22)).equals("Palabra_Reservada_21")){
                    i++;
                }
                if(!identDataType.get(id.lexemeRank(24)).equals("Palabra_Reservada_12") && !identDataType.get(id.lexemeRank(24)).equals("Palabra_Reservada_13") && !identDataType.get(id.lexemeRank(24)).equals("Palabra_Reservada_14") && !identDataType.get(id.lexemeRank(24)).equals("Palabra_Reservada_15") && !identDataType.get(id.lexemeRank(24)).equals("Palabra_Reservada_16")){
                    i++;
                }
                if(i == 1){
                    errors.add(new ErrorLSSL(64, "----------> ERROR_64:  El valor de una variable empleada en la función no está permitido, Linea [#] Columna [%]", id, true));
                }else if(i > 1){
                    errors.add(new ErrorLSSL(65, "----------> ERROR_65:  El valor de varias variables empleadas en la función no están permitidos, Linea [#] Columna [%]", id, true));
                }
            }
        }
        //Funciones de Mostrar (Decimal)
        //Error 64 y Error 65 (Segunda Parte)
        //Error 68
        for (Production id1 : identProdM1) {
            for (Production id2 : identProdM1) {
                if (id1.getLine() < id2.getLine()) {

                } else if (id1.lexemeRank(1).equals(id2.lexemeRank(1)) && id1.getLine() != id2.getLine()) {
                    errors.add(new ErrorLSSL(68, "----------> ERROR_68:  La clase ya existe, Linea [#] Columna [%]", id1, true));
                }
            }
        }//Error 68
    }

    private void fillTableTokens() {
        tokens.forEach(token -> {
            Object[] data = new Object[]{token.getLexicalComp(), token.getLexeme(), "[Linea " + token.getLine() + ", Columna " + token.getColumn() + "]"};
            Functions.addRowDataInTable(Tokens, data);
            Functions.addRowDataInTable(T.Tabla(), data);
        });
    }
    
    public void cuadruplos(String operador,String argumento1,String argumento2,String resultado){    
        cuadruplo[0] = operador;
        cuadruplo[1] = argumento1;
        cuadruplo[2] = argumento2;
        cuadruplo[3] = resultado;
        
        Functions.addRowDataInTable(Cuadruplos, cuadruplo);
        Functions.addRowDataInTable(C.Tabla(), cuadruplo);
    }
    
    private void printConsole() {
        int sizeErrors = errors.size();
        if (sizeErrors > 0) {
            Functions.sortErrorsByLineAndColumn(errors);
            String strErrors = "\n";
            for (ErrorLSSL error : errors) {
                String strError = String.valueOf(error);
                strErrors += strError + "\n";
            }
            PanelSalida.setText("Compilación terminada...\n\n\t" + strErrors + "\n\nLa compilación terminó con errores...");
        } else {
            PanelSalida.setText("Compilación terminada...\n\nLa compilacion termino sin errores...\n\n");
            //cuadruplos();
        }
        PanelSalida.setCaretPosition(0);
    }
 

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        Nuevo = new javax.swing.JButton();
        Abrir = new javax.swing.JButton();
        Guardar = new javax.swing.JButton();
        GuardarComo = new javax.swing.JButton();
        btnCompilar = new javax.swing.JButton();
        Ejecutar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        PanelSalida = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Tokens = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        Cuadruplos = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        Tripletas = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        CodObjeto = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        PanelFuente = new javax.swing.JTextPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        btnmenuNuevo = new javax.swing.JMenuItem();
        btnmenuAbrir = new javax.swing.JMenuItem();
        btnmenuGuardar = new javax.swing.JMenuItem();
        btnmenuGuardarComo = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenu10 = new javax.swing.JMenu();
        jMenuItem20 = new javax.swing.JMenuItem();
        jMenuItem21 = new javax.swing.JMenuItem();
        jMenuItem22 = new javax.swing.JMenuItem();
        jMenuItem23 = new javax.swing.JMenuItem();
        jMenuItem24 = new javax.swing.JMenuItem();
        jMenu11 = new javax.swing.JMenu();
        jMenuItem25 = new javax.swing.JMenuItem();
        jMenuItem26 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        btnMenuLEATE = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenu14 = new javax.swing.JMenu();
        jMenuItem37 = new javax.swing.JMenuItem();
        jMenu9 = new javax.swing.JMenu();
        jMenu15 = new javax.swing.JMenu();
        jMenuItem39 = new javax.swing.JMenuItem();
        jMenuItem40 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenuItem38 = new javax.swing.JMenuItem();
        jMenuItem27 = new javax.swing.JMenuItem();
        jMenu12 = new javax.swing.JMenu();
        jMenuItem28 = new javax.swing.JMenuItem();
        jMenuItem29 = new javax.swing.JMenuItem();
        jMenuItem30 = new javax.swing.JMenuItem();
        jMenuItem31 = new javax.swing.JMenuItem();
        jMenu13 = new javax.swing.JMenu();
        jMenuItem32 = new javax.swing.JMenuItem();
        jMenuItem33 = new javax.swing.JMenuItem();
        jMenuItem34 = new javax.swing.JMenuItem();
        jMenuItem36 = new javax.swing.JMenuItem();

        jMenu2.setText("jMenu2");

        jMenu3.setText("jMenu3");

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 153));

        jToolBar1.setBackground(new java.awt.Color(0, 0, 0));
        jToolBar1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jToolBar1.setRollover(true);

        Nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/Nuevo.png"))); // NOI18N
        Nuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Nuevo.setFocusable(false);
        Nuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Nuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Nuevo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                NuevoFocusGained(evt);
            }
        });
        Nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NuevoActionPerformed(evt);
            }
        });
        jToolBar1.add(Nuevo);

        Abrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/Abrir.png"))); // NOI18N
        Abrir.setFocusable(false);
        Abrir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Abrir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Abrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AbrirActionPerformed(evt);
            }
        });
        jToolBar1.add(Abrir);

        Guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/guardar.png"))); // NOI18N
        Guardar.setFocusable(false);
        Guardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Guardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarActionPerformed(evt);
            }
        });
        jToolBar1.add(Guardar);

        GuardarComo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/GuardarComo.png"))); // NOI18N
        GuardarComo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        GuardarComo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        GuardarComo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarComoActionPerformed(evt);
            }
        });
        jToolBar1.add(GuardarComo);

        btnCompilar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/Compilar.png"))); // NOI18N
        btnCompilar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCompilar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCompilar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompilarActionPerformed(evt);
            }
        });
        jToolBar1.add(btnCompilar);

        Ejecutar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/Ejecutar.png"))); // NOI18N
        Ejecutar.setFocusable(false);
        Ejecutar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Ejecutar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Ejecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EjecutarActionPerformed(evt);
            }
        });
        jToolBar1.add(Ejecutar);

        PanelSalida.setEditable(false);
        PanelSalida.setBackground(new java.awt.Color(255, 255, 255));
        PanelSalida.setColumns(20);
        PanelSalida.setRows(5);
        PanelSalida.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        PanelSalida.setEnabled(false);
        jScrollPane2.setViewportView(PanelSalida);

        jTabbedPane2.setBackground(new java.awt.Color(0, 0, 0));
        jTabbedPane2.setForeground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));

        Tokens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Token", "Lexema", "[Fila,Columna]"
            }
        ));
        jScrollPane3.setViewportView(Tokens);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Tokens", jPanel2);

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));

        Cuadruplos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Operador", "Argumento 1", "Argumento 2", "Resultado"
            }
        ));
        jScrollPane6.setViewportView(Cuadruplos);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Cuadruplos", jPanel3);

        jPanel4.setBackground(new java.awt.Color(204, 204, 255));

        Tripletas.setColumns(20);
        Tripletas.setRows(5);
        Tripletas.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        Tripletas.setEnabled(false);
        jScrollPane4.setViewportView(Tripletas);

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("Zoom");

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/MasZoom.png"))); // NOI18N
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/MenosZoom.png"))); // NOI18N
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton10)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton10)
                            .addComponent(jButton11)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(22, 22, 22)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 179, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane2.addTab("Tripletas", jPanel4);

        jPanel5.setBackground(new java.awt.Color(204, 204, 255));

        CodObjeto.setColumns(20);
        CodObjeto.setRows(5);
        CodObjeto.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        CodObjeto.setEnabled(false);
        jScrollPane7.setViewportView(CodObjeto);

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("Zoom");

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/MasZoom.png"))); // NOI18N
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/MenosZoom.png"))); // NOI18N
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setText("Abrir");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton15.setText("Guardar Como");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton16.setText("Guardar");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(0, 43, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton12))
                    .addComponent(jButton14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton12)
                            .addComponent(jButton13)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(61, 61, 61)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                        .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane7))
                .addContainerGap())
        );

        jTabbedPane2.addTab("Ensamblador", jPanel5);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/MasZoom.png"))); // NOI18N
        jButton6.setDefaultCapable(false);
        jButton6.setFocusable(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/MenosZoom.png"))); // NOI18N
        jButton7.setDefaultCapable(false);
        jButton7.setFocusable(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Zoom");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Zoom");

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/MasZoom.png"))); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/MenosZoom.png"))); // NOI18N
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(PanelFuente);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 685, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(62, 62, 62)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane5)
                                .addGap(493, 493, 493))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addGap(89, 89, 89)
                                .addComponent(jButton8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(291, 291, 291))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 16, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton8)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel1)
                        .addGap(17, 17, 17)
                        .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jMenuBar1.setBackground(new java.awt.Color(0, 0, 0));

        jMenu1.setForeground(new java.awt.Color(255, 255, 255));
        jMenu1.setText("Archivo");

        btnmenuNuevo.setText("Nuevo");
        btnmenuNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmenuNuevoActionPerformed(evt);
            }
        });
        jMenu1.add(btnmenuNuevo);

        btnmenuAbrir.setText("Abrir");
        btnmenuAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmenuAbrirActionPerformed(evt);
            }
        });
        jMenu1.add(btnmenuAbrir);

        btnmenuGuardar.setText("Guardar");
        btnmenuGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmenuGuardarActionPerformed(evt);
            }
        });
        jMenu1.add(btnmenuGuardar);

        btnmenuGuardarComo.setText("Guardar como");
        btnmenuGuardarComo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmenuGuardarComoActionPerformed(evt);
            }
        });
        jMenu1.add(btnmenuGuardarComo);
        jMenu1.add(jSeparator3);

        jMenuItem11.setText("Compilar");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem11);

        jMenuItem12.setText("Ejecutar");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem12);

        jMenuBar1.add(jMenu1);

        jMenu8.setText("Ejemplos");

        jMenuItem15.setText("Programa 1 -> Declaraciones");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem15);

        jMenuItem16.setText("Programa 2 -> Asignaciones");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem16);

        jMenu10.setText("Programa 3 -> Funciones");

        jMenuItem20.setText("Sumar");
        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem20ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem20);

        jMenuItem21.setText("Restar");
        jMenuItem21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem21ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem21);

        jMenuItem22.setText("Multiplicar");
        jMenuItem22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem22ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem22);

        jMenuItem23.setText("Dividir");
        jMenuItem23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem23ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem23);

        jMenu8.add(jMenu10);

        jMenuItem24.setText("Programa 4 -> Clases");
        jMenuItem24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem24ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem24);

        jMenu11.setText("Programa 5 -> Mostrar");

        jMenuItem25.setText("Entero");
        jMenuItem25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem25ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem25);

        jMenuItem26.setText("Decimal");
        jMenuItem26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem26ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem26);

        jMenu8.add(jMenu11);

        jMenuBar1.add(jMenu8);

        jMenu4.setText("Acerca de");

        btnMenuLEATE.setText("Sobre M4TH3C");
        btnMenuLEATE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuLEATEActionPerformed(evt);
            }
        });
        jMenu4.add(btnMenuLEATE);

        jMenuBar1.add(jMenu4);

        jMenu5.setText("Tablas");
        jMenu5.setActionCommand("");

        jMenuItem2.setText("De Componentes Léxicos");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem2);

        jMenuItem3.setText("De Símbolos");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem3);

        jMenuItem9.setText("De Tipos de Datos");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem9);
        jMenu5.add(jSeparator2);

        jMenuItem10.setText("De Tokens");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem10);

        jMenuBar1.add(jMenu5);

        jMenu6.setText("Funcionamiento");

        jMenuItem4.setText("Declaraciones");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem4);

        jMenuItem5.setText("Asignaciones");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem5);

        jMenuItem6.setText("Funciones <Primera parte>");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem6);

        jMenuItem8.setText("Funciones <Segunda parte>");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem8);

        jMenuItem7.setText("Clases");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem7);

        jMenuBar1.add(jMenu6);

        jMenu7.setText("Código Intermedio");

        jMenuItem14.setText("Tripletas");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem14);

        jMenuItem13.setText("Cuadruplos");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem13);

        jMenuBar1.add(jMenu7);

        jMenu14.setText("Código Objeto");
        jMenu14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu14ActionPerformed(evt);
            }
        });

        jMenuItem37.setText("Ensamblador");
        jMenuItem37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem37ActionPerformed(evt);
            }
        });
        jMenu14.add(jMenuItem37);

        jMenuBar1.add(jMenu14);

        jMenu9.setText("Ayuda");

        jMenu15.setText("Manual");

        jMenuItem39.setText("Usuario");
        jMenuItem39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem39ActionPerformed(evt);
            }
        });
        jMenu15.add(jMenuItem39);

        jMenuItem40.setText("Técnico");
        jMenuItem40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem40ActionPerformed(evt);
            }
        });
        jMenu15.add(jMenuItem40);

        jMenu9.add(jMenu15);

        jMenuItem17.setText("Documentación");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem17);

        jMenuItem18.setText("Componentes Léxicos");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem18);

        jMenuItem19.setText("Tabla de Tokens");
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem19);

        jMenuItem38.setText("Tipos de Datos");
        jMenuItem38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem38ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem38);

        jMenuItem27.setText("Estructuras de los Métodos");
        jMenuItem27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem27ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem27);

        jMenu12.setText("Errores");

        jMenuItem28.setText("Léxicos");
        jMenuItem28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem28ActionPerformed(evt);
            }
        });
        jMenu12.add(jMenuItem28);

        jMenuItem29.setText("Sintácticos");
        jMenuItem29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem29ActionPerformed(evt);
            }
        });
        jMenu12.add(jMenuItem29);

        jMenuItem30.setText("Semánticos");
        jMenuItem30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem30ActionPerformed(evt);
            }
        });
        jMenu12.add(jMenuItem30);

        jMenu9.add(jMenu12);

        jMenuItem31.setText("Ejemplos");
        jMenuItem31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem31ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem31);

        jMenu13.setText("Código Intermedio");

        jMenuItem32.setText("Tripletas");
        jMenuItem32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem32ActionPerformed(evt);
            }
        });
        jMenu13.add(jMenuItem32);

        jMenuItem33.setText("Cuadruplas");
        jMenuItem33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem33ActionPerformed(evt);
            }
        });
        jMenu13.add(jMenuItem33);

        jMenu9.add(jMenu13);

        jMenuItem34.setText("Optimización");
        jMenuItem34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem34ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem34);

        jMenuItem36.setText("Código Objeto");
        jMenuItem36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem36ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem36);

        jMenuBar1.add(jMenu9);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnmenuNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmenuNuevoActionPerformed
        directorio.New();
        clearFields();
    }//GEN-LAST:event_btnmenuNuevoActionPerformed

    private void btnmenuAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmenuAbrirActionPerformed
        if (directorio.Open()) {
            colorAnalysis();
            clearFields();
        }
    }//GEN-LAST:event_btnmenuAbrirActionPerformed

    private void btnmenuGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmenuGuardarActionPerformed
        if (directorio.Save()) {
            clearFields();
        }
    }//GEN-LAST:event_btnmenuGuardarActionPerformed

    private void btnmenuGuardarComoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmenuGuardarComoActionPerformed
        if (directorio.SaveAs()) {
            clearFields();
        }
    }//GEN-LAST:event_btnmenuGuardarComoActionPerformed

    private void btnMenuLEATEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuLEATEActionPerformed
        AcercaDe ac = new AcercaDe();
        ac.setLocationRelativeTo(this);
        ac.setVisible(true);
    }//GEN-LAST:event_btnMenuLEATEActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        JOptionPane.showMessageDialog(this,"Este método nos ayuda a poder declarar las variables de acuerdo al tipo de dato que necesitemos,"
                                         + " y podemos declarar las variables con valor o sin el, la manera en como declara es la siguiente:\n\n"
                                         + "Sin valor: TIPO_DE_DATO (IDENTIFICADOR_VARIABLE|IDENTIFICADOR_RESULTADO) DELIMITADOR\n\n"
                                         + "Con valor: TIPO_DE_DATO IDENTIFICADOR_VARIABLE OPERADOR_DE_ASIGNACIÓN VALOR_VARIABLE DELIMITADOR\n\n"
                                         + "En el caso de con valor, si la declaracion es de un número, se le agregará un OPERADOR ARITMETICO entre el OPERADOR_DE_ASIGNACION y el VALOR_VARIABLE, y al tipo de dato RESULTADO no se le puede asignar valor de esta manera.","SOBRE DECLARACIONES... (M4TH3C)",JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        JOptionPane.showMessageDialog(this,"Este método nos ayuda a asignarle un nuevo valor a las variables que ya han sido declaradas, pero"
                                         + " algo a tener en cuenta, es que si ya cuenta con un valor la variable, ya no se le podrá asignar "
                                         + "un nuevo valor, la manera de como se asigna es la siguiente:\n\n"
                                         + "IDENTIFICADOR_VARIABLE OPERADOR_DE_ASIGNACIÓN VALOR_VARIABLE DELIMITADOR\n\n"
                                         + "si la asignación es de un número, se le agregará un OPERADOR ARITMETICO entre el OPERADOR_DE_ASIGNACION y el VALOR_VARIABLE.","SOBRE ASIGNACIONES... (M4TH3C)",JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        JOptionPane.showMessageDialog(this,"Este método se divide en dos partes, porque hay dos estructuras diferentes en este método, en esta"
                                         + " ocasión hablaremos de las funciones de Sumar, Restar, Multiplicar y Dividir, estas funciones realizan"
                                         + ", dependiendo la que se utilice, la operación entre las variables que se especifiquen para la función"
                                         + ", dicho resultado se guardará en la variable de tipo resultado que se utlice en la función, hay tres cosas "
                                         + "a tener en cuanta sobre estos métodos, la primera es de que solo acepta un total de 5 variables como máximo, "
                                         + "un mínimo de 2, la segunda es que solo acepta variables que su valor sea un número, claro esto es para "
                                         + "las variables que se utlizarán para realizar la operación, para la variables donde se guardará el resultado de"
                                         + " la operación deberá de ser de tipo Resultado, y por ultimo el valor de cada variable debera de ser del mismo tipo"
                                         + " de número, o puros Enteros o puros Decimales, la manera de como recibe los parametros la función es la siguiente:\n\n"
                                         + "(SUMAR|RESTAR|MULTIPLICAR|DIVIDIR) OPERADOR_DE_AGRUPACIÓN_( IDENTIFICADOR_VARIABLE SEPARADOR IDENTIFICADOR_VARIABLE SEPARADOR IDENTIFICADOR_VARIABLE SEPARADOR IDENTIFICADOR_VARIABLE SEPARADOR IDENTIFICADOR_VARIABLE OPERADOR_DE_AGRUPACIÓN_) OPERADOR_DE_AGRUPACIÓN_< IDENTIFICADOR_RESULTADO OPERADOR_DE_AGRUPACIÓN_> DELIMITADOR\n\n"
                                         + "Dependiendo cuantas variables requieras son los IDENTIFICADOR_VARIABLE que utilices, en este caso es para una función, "
                                         + "que recibe 5 variables como parametro. Otro dato a tener en cuenta es que todas las variables utilizadas tienen que tener un valor asignado, excepto la variable de tipo Resultado.","SOBRE FUNCIONES <PRIMERA PARTE>... (M4TH3C)",JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        JOptionPane.showMessageDialog(this,"Esta segunda parte sobre el método de funciones será sobre la función de Mostrar, está función se divide"
                                         + " en dos, una que es para números Enteros y otra para Decimales, en el caso del funcionamiento de la función "
                                         + "que recibe un número Entero, creará una interfaz donde en esta se mostrará en formas de figuras el número que"
                                         + " recibio como parametro, el cual la forma y el color de las figuras se especificarán por medio de los paramatres que "
                                         + "acepta la función, y un pequeño mensaje tambien se mostrará donde eso lo define el usuario y lo pone en los paramatros,"
                                         + " por medio de una variable que contenga el mensaje personalizado del usuario, en este caso para los tipo entero solo se podrá"
                                         + " definir la forma y el color de la figura una vez, ya que esta se utlizará para representar todo el número Entero, "
                                         + "en caso de la que recibe un número Decimal, lo único que cambia es los parametros de forma y color de la figura, debido a "
                                         + "que se le agregan poder definir cuatro veces mas tanto el color como la forma, y la primera definción de derecha a izquierda en "
                                         + " la funcion, representará los números enteros del número Decimal, la segunda definición sera el primer digito decimal del "
                                         + "número Decimal de izquierda a derecha, la tercera definción sera el segundo digito Decimal, la cuarta definición sera para el "
                                         + "tercer digito Decimal y la quinta y ultima definción sera para el cuarto digito Decimal, la manera de como recibe los parametros "
                                         + "la funcón es la siguiente:\n\n"
                                         + "Para Entero:  MOSTRAR OPERADOR_DE_AGRUPACIÓN_( IDENTIFICADOR_VARIABLE_CADENA SEPARADOR IDENTIFICADOR_RESULTADO SEPARADOR IDENTIFICADOR_VARIABLE_FIGURA SEPARADOR IDENTIFICADOR_VARIABLE_COLOR OPERADOR_DE_AGRUPACIÓN_) DELIMITADOR\n\n"
                                         + "Para Decimal:  MOSTRAR OPERADOR_DE_AGRUPACIÓN_( IDENTIFICADOR_VARIABLE_CADENA SEPARADOR IDENTIFICADOR_RESULTADO SEPARADOR IDENTIFICADOR_VARIABLE_FIGURA SEPARADOR IDENTIFICADOR_VARIABLE_COLOR SEPARADOR IDENTIFICADOR_VARIABLE_FIGURA SEPARADOR IDENTIFICADOR_VARIABLE_COLOR SEPARADOR IDENTIFICADOR_VARIABLE_FIGURA SEPARADOR IDENTIFICADOR_VARIABLE_COLOR SEPARADOR IDENTIFICADOR_VARIABLE_FIGURA SEPARADOR IDENTIFICADOR_VARIABLE_COLOR SEPARADOR IDENTIFICADOR_VARIABLE_FIGURA SEPARADOR IDENTIFICADOR_VARIABLE_COLOR OPERADOR_DE_AGRUPACIÓN_) DELIMITADOR\n\n"
                                         + "Algo a tener en cuanta para estos métodos es que toda variable tiene que tener un valor asignado.","SOBRE FUNCIONES <SEGUNDA PARTE>... (M4TH3C)",JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        JOptionPane.showMessageDialog(this,"Este método nos ayuda a almacenar las delaraciones, asignaciones o funciones dentro de una clase"
                                          + ", en total puede almacenar 30 de los métodos mencionados anteriormente, algo a tener en cuenta es que,"
                                          + " si alguna declaración, asignación o función no se encuentra dentro de una clase, será incorrecto, la manera"
                                          + " en como se utiliza el método es el siguiente:\n\n"
                                          + "METODO IDENTIFICADOR_METODO OPERADOR_DE_AGRUPACIÓN_{ (DECLARACIÓN|ASIGNACIÓN|FUNCIÓN) OPERADOR_DE_AGRUPACIÓN_}","SOBRE CLASES... (M4TH3C)",JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        ComponentesLexicos s = new ComponentesLexicos();
        s.setLocationRelativeTo(this);
        s.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        Simbolos t = new Simbolos();
        t.setLocationRelativeTo(this);
        t.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        TiposDeDatos tdd = new TiposDeDatos();
        tdd.setLocationRelativeTo(this);
        tdd.setVisible(true);
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void EjecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EjecutarActionPerformed
        btnCompilar.doClick();
        if(codeHasBeenCompiled){
            if(!errors.isEmpty()){
                JOptionPane.showMessageDialog(this, "No se puede ejecutar el código ya que se encontró uno o más errores","¡ERROR!",JOptionPane.ERROR_MESSAGE);
            }else{
                CodeBlock codeBlock = Functions.splitCodeInCodeBlocks(tokens, "{", "}", ";");
                ArrayList<String> blocksOfCode = codeBlock.getBlocksOfCodeInOrderOfExec();
                executeCode(blocksOfCode,1);
            }
        }
    }//GEN-LAST:event_EjecutarActionPerformed

    private void btnCompilarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompilarActionPerformed
        if (getTitle().contains("*") || getTitle().equals(title)) {
            if (directorio.Save()) {
                compilar();
            }
        } else {
            compilar();
        }
    }//GEN-LAST:event_btnCompilarActionPerformed

    private void GuardarComoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarComoActionPerformed
        if (directorio.SaveAs()) {
            clearFields();
        }
    }//GEN-LAST:event_GuardarComoActionPerformed

    private void GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarActionPerformed
        if (directorio.Save()) {
            clearFields();
        }
    }//GEN-LAST:event_GuardarActionPerformed

    private void AbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AbrirActionPerformed
        if (directorio.Open()) {
            colorAnalysis();
            clearFields();
        }
    }//GEN-LAST:event_AbrirActionPerformed

    private void NuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NuevoActionPerformed
        directorio.New();
        clearFields();
    }//GEN-LAST:event_NuevoActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        String fontName = PanelFuente.getFont().getFontName();
        int fontSize = PanelFuente.getFont().getSize() + 2;
        
        Font font = new Font(fontName, 0, fontSize);
        
        PanelFuente.setFont(font);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        String fontName = PanelFuente.getFont().getFontName();
        int fontSize = PanelFuente.getFont().getSize() - 2;
        
        Font font = new Font(fontName, 0, fontSize);
        
        PanelFuente.setFont(font);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        String fontName = PanelSalida.getFont().getFontName();
        int fontSize = PanelSalida.getFont().getSize() + 2;
        
        Font font = new Font(fontName, 0, fontSize);
        
        PanelSalida.setFont(font);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        String fontName = PanelSalida.getFont().getFontName();
        int fontSize = PanelSalida.getFont().getSize() - 2;
        
        Font font = new Font(fontName, 0, fontSize);
        
        PanelSalida.setFont(font);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        if (getTitle().contains("*") || getTitle().equals(title)) {
            if (directorio.Save()) {
                compilar();
            }
        } else {
            compilar();
        }
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        btnCompilar.doClick();
        if(codeHasBeenCompiled){
            if(!errors.isEmpty()){
                JOptionPane.showMessageDialog(this, "No se puede ejecutar el código ya que se encontró uno o más errores","¡ERROR!",JOptionPane.ERROR_MESSAGE);
            }else{
                CodeBlock codeBlock = Functions.splitCodeInCodeBlocks(tokens, "{", "}", ";");
                ArrayList<String> blocksOfCode = codeBlock.getBlocksOfCodeInOrderOfExec();
                executeCode(blocksOfCode,1);
            }
        }
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        T.setVisible(true);
        T.setLocationRelativeTo(this);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        String fontName = Tripletas.getFont().getFontName();
        int fontSize = Tripletas.getFont().getSize() + 2;
        
        Font font = new Font(fontName, 0, fontSize);
        
        Tripletas.setFont(font);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        String fontName = Tripletas.getFont().getFontName();
        int fontSize = Tripletas.getFont().getSize() - 2;
        
        Font font = new Font(fontName, 0, fontSize);
        
        Tripletas.setFont(font);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        Tr.setVisible(true);
        Tr.setLocationRelativeTo(this);
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        URL ruta = getClass().getResource("/CODIGO_EJEMPLO/DEMOSTRACION_DECLARACIONES.MTC");
        String rutaNueva = ruta.getFile();
        PanelFuente.setText(leerArchivo(rutaNueva.replaceAll("%20"," ")));
        colorAnalysis();
        clearFields();
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        URL ruta = getClass().getResource("/CODIGO_EJEMPLO/DEMOSTRACION_ASIGNACIONES.MTC");
        String rutaNueva = ruta.getFile();
        PanelFuente.setText(leerArchivo(rutaNueva.replaceAll("%20"," ")));
        colorAnalysis();
        clearFields();
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem20ActionPerformed
        URL ruta = getClass().getResource("/CODIGO_EJEMPLO/DEMOSTRACION_FUNCIONES_SUMAR.MTC");
        String rutaNueva = ruta.getFile();
        PanelFuente.setText(leerArchivo(rutaNueva.replaceAll("%20"," ")));
        colorAnalysis();
        clearFields();
    }//GEN-LAST:event_jMenuItem20ActionPerformed

    private void jMenuItem21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem21ActionPerformed
        URL ruta = getClass().getResource("/CODIGO_EJEMPLO/DEMOSTRACION_FUNCIONES_RESTAR.MTC");
        String rutaNueva = ruta.getFile();
        PanelFuente.setText(leerArchivo(rutaNueva.replaceAll("%20"," ")));
        colorAnalysis();
        clearFields();
    }//GEN-LAST:event_jMenuItem21ActionPerformed

    private void jMenuItem22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem22ActionPerformed
        URL ruta = getClass().getResource("/CODIGO_EJEMPLO/DEMOSTRACION_FUNCIONES_MULTIPLICAR.MTC");
        String rutaNueva = ruta.getFile();
        PanelFuente.setText(leerArchivo(rutaNueva.replaceAll("%20"," ")));
        colorAnalysis();
        clearFields();
    }//GEN-LAST:event_jMenuItem22ActionPerformed

    private void jMenuItem23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem23ActionPerformed
        URL ruta = getClass().getResource("/CODIGO_EJEMPLO/DEMOSTRACION_FUNCIONES_DIVIDIR.MTC");
        String rutaNueva = ruta.getFile();
        PanelFuente.setText(leerArchivo(rutaNueva.replaceAll("%20"," ")));
        colorAnalysis();
        clearFields();
    }//GEN-LAST:event_jMenuItem23ActionPerformed

    private void jMenuItem24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem24ActionPerformed
        URL ruta = getClass().getResource("/CODIGO_EJEMPLO/DEMOSTRACION_CLASES.MTC");
        String rutaNueva = ruta.getFile();
        PanelFuente.setText(leerArchivo(rutaNueva.replaceAll("%20"," ")));
        colorAnalysis();
        clearFields();
    }//GEN-LAST:event_jMenuItem24ActionPerformed

    private void jMenuItem25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem25ActionPerformed
        URL ruta = getClass().getResource("/CODIGO_EJEMPLO/DEMOSTRACION_ENTERO.MTC");
        String rutaNueva = ruta.getFile();
        PanelFuente.setText(leerArchivo(rutaNueva.replaceAll("%20"," ")));
        colorAnalysis();
        clearFields();
    }//GEN-LAST:event_jMenuItem25ActionPerformed

    private void jMenuItem26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem26ActionPerformed
        URL ruta = getClass().getResource("/CODIGO_EJEMPLO/DEMOSTRACION_DECIMAL.MTC");
        String rutaNueva = ruta.getFile();
        PanelFuente.setText(leerArchivo(rutaNueva.replaceAll("%20"," ")));
        colorAnalysis();
        clearFields();
    }//GEN-LAST:event_jMenuItem26ActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        try{
            URL ruta = getClass().getResource("/DOCUMENTACION/M4TH3C_DOCUMENTACION.docx.pdf");
            String rutaNueva = ruta.getFile();
            File ruta2 = new File(rutaNueva.replaceAll("%20"," "));
            Desktop.getDesktop().open(ruta2);
        }catch(IOException e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        C.setVisible(true);
        C.setLocationRelativeTo(this);
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
        try{
            URL ruta = getClass().getResource("/DOCUMENTACION/M4TH3C_COMPONENTES_LEXICOS.docx.pdf");
            String rutaNueva = ruta.getFile();
            File ruta2 = new File(rutaNueva.replaceAll("%20"," "));
            Desktop.getDesktop().open(ruta2);
        }catch(IOException e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_jMenuItem18ActionPerformed

    private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed
        try{
            URL ruta = getClass().getResource("/DOCUMENTACION/M4TH3C_TABLA_TOKENS.docx.pdf");
            String rutaNueva = ruta.getFile();
            File ruta2 = new File(rutaNueva.replaceAll("%20"," "));
            Desktop.getDesktop().open(ruta2);
        }catch(IOException e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_jMenuItem19ActionPerformed

    private void jMenuItem27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem27ActionPerformed
        try{
            URL ruta = getClass().getResource("/DOCUMENTACION/M4TH3C_ESTRUCTURAS.docx.pdf");
            String rutaNueva = ruta.getFile();
            File ruta2 = new File(rutaNueva.replaceAll("%20"," "));
            Desktop.getDesktop().open(ruta2);
        }catch(IOException e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_jMenuItem27ActionPerformed

    private void jMenuItem28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem28ActionPerformed
        try{
            URL ruta = getClass().getResource("/DOCUMENTACION/M4TH3C_ERRORES_LEXICOS.docx.pdf");
            String rutaNueva = ruta.getFile();
            File ruta2 = new File(rutaNueva.replaceAll("%20"," "));
            Desktop.getDesktop().open(ruta2);
        }catch(IOException e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_jMenuItem28ActionPerformed

    private void jMenuItem29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem29ActionPerformed
        try{
            URL ruta = getClass().getResource("/DOCUMENTACION/M4TH3C_ERRORES_SINTACTICOS.docx.pdf");
            String rutaNueva = ruta.getFile();
            File ruta2 = new File(rutaNueva.replaceAll("%20"," "));
            Desktop.getDesktop().open(ruta2);
        }catch(IOException e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_jMenuItem29ActionPerformed

    private void jMenuItem30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem30ActionPerformed
        try{
            URL ruta = getClass().getResource("/DOCUMENTACION/M4TH3C_ERRORES_SEMANTICOS.docx.pdf");
            String rutaNueva = ruta.getFile();
            File ruta2 = new File(rutaNueva.replaceAll("%20"," "));
            Desktop.getDesktop().open(ruta2);
        }catch(IOException e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_jMenuItem30ActionPerformed

    private void jMenuItem31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem31ActionPerformed
        try{
            URL ruta = getClass().getResource("/DOCUMENTACION/M4TH3C_EJEMPLOS.docx.pdf");
            String rutaNueva = ruta.getFile();
            File ruta2 = new File(rutaNueva.replaceAll("%20"," "));
            Desktop.getDesktop().open(ruta2);
        }catch(IOException e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_jMenuItem31ActionPerformed

    private void jMenuItem34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem34ActionPerformed
        try{
            URL ruta = getClass().getResource("/DOCUMENTACION/M4TH3C_OPTIMIZACION.docx.pdf");
            String rutaNueva = ruta.getFile();
            File ruta2 = new File(rutaNueva.replaceAll("%20"," "));
            Desktop.getDesktop().open(ruta2);
        }catch(IOException e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_jMenuItem34ActionPerformed

    private void jMenuItem32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem32ActionPerformed
        try{
            URL ruta = getClass().getResource("/DOCUMENTACION/M4TH3C_INTERMEDIO_TRIPLETAS.docx.pdf");
            String rutaNueva = ruta.getFile();
            File ruta2 = new File(rutaNueva.replaceAll("%20"," "));
            Desktop.getDesktop().open(ruta2);
        }catch(IOException e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_jMenuItem32ActionPerformed

    private void jMenuItem33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem33ActionPerformed
        try{
            URL ruta = getClass().getResource("/DOCUMENTACION/M4TH3C_INTERMEDIO_CUADRUPLOS.docx.pdf");
            String rutaNueva = ruta.getFile();
            File ruta2 = new File(rutaNueva.replaceAll("%20"," "));
            Desktop.getDesktop().open(ruta2);
        }catch(IOException e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_jMenuItem33ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        String fontName = CodObjeto.getFont().getFontName();
        int fontSize = CodObjeto.getFont().getSize() + 2;
        
        Font font = new Font(fontName, 0, fontSize);
        
        CodObjeto.setFont(font);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        String fontName = CodObjeto.getFont().getFontName();
        int fontSize = CodObjeto.getFont().getSize() - 2;
        
        Font font = new Font(fontName, 0, fontSize);
        
        CodObjeto.setFont(font);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
       try {
            salida = new FileOutputStream("D:/ARCHIVOS/"+this.getTitle().replaceAll(".MTC","")+".asm");
            char ch[] = CodObjeto.getText().toCharArray();
            for(int j = 0; j<CodObjeto.getText().length(); j++){
                    salida.write(ch[j]);
            } 
            salida.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, ex, "ERROR", JOptionPane.ERROR_MESSAGE);
        }   
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jMenuItem36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem36ActionPerformed
        try{
            URL ruta = getClass().getResource("/DOCUMENTACION/M4TH3C_OBJETO.docx.pdf");
            String rutaNueva = ruta.getFile();
            File ruta2 = new File(rutaNueva.replaceAll("%20"," "));
            Desktop.getDesktop().open(ruta2);
        }catch(IOException e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_jMenuItem36ActionPerformed

    private void jMenu14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu14ActionPerformed
        
    }//GEN-LAST:event_jMenu14ActionPerformed

    private void jMenuItem37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem37ActionPerformed
        co.setVisible(true);
        co.setLocationRelativeTo(this);
    }//GEN-LAST:event_jMenuItem37ActionPerformed

    private void jMenuItem38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem38ActionPerformed
        try{
            URL ruta = getClass().getResource("/DOCUMENTACION/M4TH3C_TIPOS_DE_DATOS.docx.pdf");
            String rutaNueva = ruta.getFile();
            File ruta2 = new File(rutaNueva.replaceAll("%20"," "));
            Desktop.getDesktop().open(ruta2);
        }catch(IOException e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_jMenuItem38ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        try{
            File f = new File("D:/ARCHIVOS/"+this.getTitle().replaceAll(".MTC","")+".asm");
            Desktop.getDesktop().open(f);
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        String nombre = "";
        try {
            String name = JOptionPane.showInputDialog("Ingresa nombre de archivo");
            nombre = name;
            salida = new FileOutputStream("D:/ARCHIVOS/"+name+".asm");
            char ch[] = CodObjeto.getText().toCharArray();
            for(int j = 0; j<CodObjeto.getText().length(); j++){
                    salida.write(ch[j]);
            } 
            salida.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, ex, "ERROR", JOptionPane.ERROR_MESSAGE);
        }   
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jMenuItem39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem39ActionPerformed
        try{
            URL ruta = getClass().getResource("/DOCUMENTACION/M4TH3C_MANUAL_USUARIO.docx.pdf");
            String rutaNueva = ruta.getFile();
            File ruta2 = new File(rutaNueva.replaceAll("%20"," "));
            Desktop.getDesktop().open(ruta2);
        }catch(IOException e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_jMenuItem39ActionPerformed

    private void jMenuItem40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem40ActionPerformed
        try{
            URL ruta = getClass().getResource("/DOCUMENTACION/M4TH3C_MANUAL_TECNICO.docx.pdf");
            String rutaNueva = ruta.getFile();
            File ruta2 = new File(rutaNueva.replaceAll("%20"," "));
            Desktop.getDesktop().open(ruta2);
        }catch(IOException e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_jMenuItem40ActionPerformed

    private void NuevoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_NuevoFocusGained

    }//GEN-LAST:event_NuevoFocusGained
    
    private void executeCode(ArrayList<String> blocksOfCode,int repeats){
        int temporal = 0;
        String metodo = "";
        int contador = 0;
        for(int j = 1;j <= repeats; j++){
            int repeatCode = -1;
            for(int i = 0; i < blocksOfCode.size(); i++){
                String blockOfCode = blocksOfCode.get(i);
                if(repeatCode != -1){
                    int[] posicionMarcador = CodeBlock.getPositionOfBothMarkers(blocksOfCode, blockOfCode);
                    executeCode(new ArrayList<>(blocksOfCode.subList(posicionMarcador[0], posicionMarcador[1])),repeatCode);
                    repeatCode = -1;
                    i = posicionMarcador[1];
                }else{
                    String[] sentences = blockOfCode.split(";");
                    for(String sentence: sentences){
                        sentence = sentence.trim();
                        if(sentence.startsWith("Metodo")){
                            for(Production id: identProdM2){
                                if(sentence.contains(id.lexemeRank(1))){
                                    metodo = id.lexemeRank(1);
                                }  
                            }
                            temporal++;
                            Tripletas.append("T"+temporal+" := "+metodo+"\n");
                            cuadruplos("=",metodo,"","T"+temporal);
                            identDataTemp.put(metodo,"T"+temporal);
                            Tripletas.append(metodo+" := "+identDataTemp.get(metodo)+"\n");
                            cuadruplos("=",identDataTemp.get(metodo),"",metodo);
                            PanelSalida.append("-->   Se ha creado una Clase llamada " + metodo + ".........\n");
                            CodigoObjeto.add(metodo+":\n");
                        }
                        if(sentence.startsWith("Entero")){
                            if(sentence.contains("=")){
                                String variable = "";
                                String valor = "";
                                String estado = "";
                                String valor2 = "";
                                for(Production id: identProdD1){
                                    if(sentence.contains(id.lexemeRank(1))){
                                        variable = id.lexemeRank(1);
                                        valor = id.lexemeRank(-3)+id.lexemeRank(-2);
                                        estado = id.lexemeRank(-3);
                                        valor2 = id.lexemeRank(-2);
                                    }
                                }
                                if(estado.equals("-")){
                                    temporal++;
                                    Tripletas.append("T"+temporal+" := "+variable+"\n");
                                    cuadruplos("=",variable,"","T"+temporal);
                                    temporal++;
                                    Tripletas.append("T"+temporal+" := T"+(temporal-1) + " + "+valor2+"\n");
                                    cuadruplos("+","T"+(temporal-1),valor2,"T"+temporal);
                                    temporal++;
                                    if(identDataTemp.containsKey("-1")){
                                        Tripletas.append(variable+" := T"+(temporal-1)+" * "+identDataTemp.get("-1")+"\n");
                                        cuadruplos("*","T"+(temporal-1),identDataTemp.get("-1"),variable);
                                        if(valor2.length() == 1){
                                            Variables.add("    "+variable+" dw \""+valor2.charAt(0)+"\"\n");
                                        }
                                        if(valor2.length() == 2){
                                            Variables.add("    "+variable+" dw \""+valor2.charAt(0)+"\",\""+valor2.charAt(1)+"\"\n");
                                        }
                                        if(valor2.length() == 3){
                                            Variables.add("    "+variable+" dw \""+valor2.charAt(0)+"\",\""+valor2.charAt(1)+"\","+
                                                          "\""+valor2.charAt(2)+"\"\n");
                                        }
                                        if(valor2.length() == 4){
                                            Variables.add("    "+variable+" dw \""+valor2.charAt(0)+"\",\""+valor2.charAt(1)+"\","+
                                                          "\""+valor2.charAt(2)+"\",\""+valor2.charAt(3)+"\"\n");
                                        }
                                        if(valor2.length() == 5){
                                            Variables.add("    "+variable+" dw \""+valor2.charAt(0)+"\",\""+valor2.charAt(1)+"\","+
                                                          "\""+valor2.charAt(2)+"\",\""+valor2.charAt(3)+"\","+
                                                          "\""+valor2.charAt(4)+"\"\n");
                                        }
                                        if(valor2.length() == 6){
                                            Variables.add("    "+variable+" dw \""+valor2.charAt(0)+"\",\""+valor2.charAt(1)+"\","+
                                                          "\""+valor2.charAt(2)+"\",\""+valor2.charAt(3)+"\","+
                                                          "\""+valor2.charAt(4)+"\",\""+valor2.charAt(5)+"\"\n");
                                        }
                                    }else{
                                        Tripletas.append("T"+temporal+" := 1 - 2"+"\n");
                                        identDataTemp.put("-1","T"+temporal);
                                        cuadruplos("-","1","2","T"+temporal);
                                        Tripletas.append(variable+" := T"+(temporal-1)+" * T"+(temporal)+"\n");
                                        cuadruplos("*","T"+(temporal-1),"T"+temporal,variable);
                                        if(valor2.length() == 1){
                                            Variables.add("    "+variable+" dw \""+valor2.charAt(0)+"\"\n");
                                        }
                                        if(valor2.length() == 2){
                                            Variables.add("    "+variable+" dw \""+valor2.charAt(0)+"\",\""+valor2.charAt(1)+"\"\n");
                                        }
                                        if(valor2.length() == 3){
                                            Variables.add("    "+variable+" dw \""+valor2.charAt(0)+"\",\""+valor2.charAt(1)+"\","+
                                                          "\""+valor2.charAt(2)+"\"\n");
                                        }
                                        if(valor2.length() == 4){
                                            Variables.add("    "+variable+" dw \""+valor2.charAt(0)+"\",\""+valor2.charAt(1)+"\","+
                                                          "\""+valor2.charAt(2)+"\",\""+valor2.charAt(3)+"\"\n");
                                        }
                                        if(valor2.length() == 5){
                                            Variables.add("    "+variable+" dw \""+valor2.charAt(0)+"\",\""+valor2.charAt(1)+"\","+
                                                          "\""+valor2.charAt(2)+"\",\""+valor2.charAt(3)+"\","+
                                                          "\""+valor2.charAt(4)+"\"\n");
                                        }
                                        if(valor2.length() == 6){
                                            Variables.add("    "+variable+" dw \""+valor2.charAt(0)+"\",\""+valor2.charAt(1)+"\","+
                                                          "\""+valor2.charAt(2)+"\",\""+valor2.charAt(3)+"\","+
                                                          "\""+valor2.charAt(4)+"\",\""+valor2.charAt(5)+"\"\n");
                                        }
                                        Variables.add("    Negativo dw \"-\"\n");
                                    }
                                }else{
                                    temporal++;
                                    Tripletas.append("T"+temporal+" := "+variable+"\n");
                                    cuadruplos("=",variable,"","T"+temporal);
                                    temporal++;
                                    Tripletas.append("T"+temporal+" := T"+(temporal-1)+" + "+valor2+"\n");
                                    cuadruplos("+","T"+(temporal-1),valor2,"T"+temporal);
                                    Tripletas.append(variable+" := T"+temporal+"\n");
                                    cuadruplos("=","T"+(temporal),"",variable);
                                    if(valor2.length() == 1){
                                        Variables.add("    "+variable+" dw \""+valor2.charAt(0)+"\"\n");
                                    }
                                    if(valor2.length() == 2){
                                        Variables.add("    "+variable+" dw \""+valor2.charAt(0)+"\",\""+valor2.charAt(1)+"\"\n");
                                    }
                                    if(valor2.length() == 3){
                                        Variables.add("    "+variable+" dw \""+valor2.charAt(0)+"\",\""+valor2.charAt(1)+"\","+
                                                      "\""+valor2.charAt(2)+"\"\n");
                                    }
                                    if(valor2.length() == 4){
                                        Variables.add("    "+variable+" dw \""+valor2.charAt(0)+"\",\""+valor2.charAt(1)+"\","+
                                                      "\""+valor2.charAt(2)+"\",\""+valor2.charAt(3)+"\"\n");
                                    }
                                    if(valor2.length() == 5){
                                        Variables.add("    "+variable+" dw \""+valor2.charAt(0)+"\",\""+valor2.charAt(1)+"\","+
                                                      "\""+valor2.charAt(2)+"\",\""+valor2.charAt(3)+"\","+
                                                      "\""+valor2.charAt(4)+"\"\n");
                                    }
                                    if(valor2.length() == 6){
                                        Variables.add("    "+variable+" dw \""+valor2.charAt(0)+"\",\""+valor2.charAt(1)+"\","+
                                                      "\""+valor2.charAt(2)+"\",\""+valor2.charAt(3)+"\","+
                                                      "\""+valor2.charAt(4)+"\",\""+valor2.charAt(5)+"\"\n");
                                    }
                                }
                                 PanelSalida.append("-->   Se ha declarado una variable de tipo Entero llamada " + variable + " con valor " + valor + ".........\n");
                            }else{
                                String variable = sentence.substring(7,sentence.length());
                                temporal++;
                                Tripletas.append("T"+temporal+" := "+variable+"\n");
                                cuadruplos("=",variable,"","T"+temporal);
                                identDataTemp.put(variable,"T"+temporal);
                                PanelSalida.append("-->   Se ha declarado una variable de tipo Entero llamada " + variable + ".........\n");
                                Variables.add("    "+variable+" dw 0,0,0,0,0,0\n");
                            }
                        }
                        if(sentence.startsWith("Decimal")){
                            if(sentence.contains("=")){
                                String variable = "";
                                String valor = "";
                                String estado = "";
                                String valor2 = "";
                                
                                
                                for(Production id: identProdD1){
                                    if(sentence.contains(id.lexemeRank(1))){
                                        variable = id.lexemeRank(1);
                                        valor = id.lexemeRank(-3)+id.lexemeRank(-2);
                                        estado = id.lexemeRank(-3);
                                        valor2 = id.lexemeRank(-2);
                                    }
                                }
                                PanelSalida.append("-->   Se ha declarado una variable de tipo Decimal llamada " + variable + " con valor " + valor + ".........\n");
                                if(estado.equals("-")){
                                    temporal++;
                                    Tripletas.append("T"+temporal+" := "+variable+"\n");
                                    cuadruplos("=",variable,"","T"+temporal);
                                    temporal++;
                                    Tripletas.append("T"+temporal+" := T"+(temporal-1) + " + "+valor2+"\n");
                                    cuadruplos("+","T"+(temporal-1),valor2,"T"+temporal);
                                    temporal++;
                                    if(identDataTemp.containsKey("-1")){
                                        Tripletas.append(variable+" := T"+(temporal-1)+" * "+identDataTemp.get("-1")+"\n");
                                        cuadruplos("*","T"+(temporal-1),identDataTemp.get("-1"),variable);
                                        if(valor2.length() == 3){
                                            Variables.add("    "+variable+" dw \""+valor2.charAt(0)+"\",\""+valor2.charAt(1)+"\","+
                                                          "\""+valor2.charAt(2)+"\"\n");
                                        }
                                        if(valor2.length() == 4){
                                            Variables.add("    "+variable+" dw \""+valor2.charAt(0)+"\",\""+valor2.charAt(1)+"\","+
                                                          "\""+valor2.charAt(2)+"\",\""+valor2.charAt(3)+"\"\n");
                                        }
                                        if(valor2.length() == 5){
                                            Variables.add("    "+variable+" dw \""+valor2.charAt(0)+"\",\""+valor2.charAt(1)+"\","+
                                                          "\""+valor2.charAt(2)+"\",\""+valor2.charAt(3)+"\","+
                                                          "\""+valor2.charAt(4)+"\"\n");
                                        }
                                        if(valor2.length() == 6){
                                            Variables.add("    "+variable+" dw \""+valor2.charAt(0)+"\",\""+valor2.charAt(1)+"\","+
                                                          "\""+valor2.charAt(2)+"\",\""+valor2.charAt(3)+"\","+
                                                          "\""+valor2.charAt(4)+"\",\""+valor2.charAt(5)+"\"\n");
                                        }
                                        if(valor2.length() == 7){
                                            Variables.add("    "+variable+" dw \""+valor2.charAt(0)+"\",\""+valor2.charAt(1)+"\","+
                                                          "\""+valor2.charAt(2)+"\",\""+valor2.charAt(3)+"\","+
                                                          "\""+valor2.charAt(4)+"\",\""+valor2.charAt(5)+"\","+
                                                          "\""+valor2.charAt(6)+"\"\n");
                                        }
                                    }else{
                                        Tripletas.append("T"+temporal+" := 1 - 2"+"\n");
                                        identDataTemp.put("-1","T"+temporal);
                                        cuadruplos("-","1","2","T"+temporal);
                                        Tripletas.append(variable+" := T"+(temporal-1)+" * T"+(temporal)+"\n");
                                        cuadruplos("*","T"+(temporal-1),"T"+temporal,variable);
                                        if(valor2.length() == 3){
                                            Variables.add("    "+variable+" dw \""+valor2.charAt(0)+"\",\""+valor2.charAt(1)+"\","+
                                                          "\""+valor2.charAt(2)+"\"\n");
                                        }
                                        if(valor2.length() == 4){
                                            Variables.add("    "+variable+" dw \""+valor2.charAt(0)+"\",\""+valor2.charAt(1)+"\","+
                                                          "\""+valor2.charAt(2)+"\",\""+valor2.charAt(3)+"\"\n");
                                        }
                                        if(valor2.length() == 5){
                                            Variables.add("    "+variable+" dw \""+valor2.charAt(0)+"\",\""+valor2.charAt(1)+"\","+
                                                          "\""+valor2.charAt(2)+"\",\""+valor2.charAt(3)+"\","+
                                                          "\""+valor2.charAt(4)+"\"\n");
                                        }
                                        if(valor2.length() == 6){
                                            Variables.add("    "+variable+" dw \""+valor2.charAt(0)+"\",\""+valor2.charAt(1)+"\","+
                                                          "\""+valor2.charAt(2)+"\",\""+valor2.charAt(3)+"\","+
                                                          "\""+valor2.charAt(4)+"\",\""+valor2.charAt(5)+"\"\n");
                                        }
                                        if(valor2.length() == 7){
                                            Variables.add("    "+variable+" dw \""+valor2.charAt(0)+"\",\""+valor2.charAt(1)+"\","+
                                                          "\""+valor2.charAt(2)+"\",\""+valor2.charAt(3)+"\","+
                                                          "\""+valor2.charAt(4)+"\",\""+valor2.charAt(5)+"\","+
                                                          "\""+valor2.charAt(6)+"\"\n");
                                        }
                                        Variables.add("    Negativo dw \"-\"\n");
                                    }
                                }else{
                                    temporal++;
                                    Tripletas.append("T"+temporal+" := "+variable+"\n");
                                    cuadruplos("=",variable,"","T"+temporal);
                                    temporal++;
                                    Tripletas.append("T"+temporal+" := T"+(temporal-1)+" + "+valor2+"\n");
                                    cuadruplos("+","T"+(temporal-1),valor2,"T"+temporal);
                                    Tripletas.append(variable+" := T"+temporal+"\n");
                                    cuadruplos("=","T"+(temporal),"",variable);
                                    if(valor2.length() == 3){
                                        Variables.add("    "+variable+" dw \""+valor2.charAt(0)+"\",\""+valor2.charAt(1)+"\","+
                                                      "\""+valor2.charAt(2)+"\"\n");
                                    }
                                    if(valor2.length() == 4){
                                        Variables.add("    "+variable+" dw \""+valor2.charAt(0)+"\",\""+valor2.charAt(1)+"\","+
                                                      "\""+valor2.charAt(2)+"\",\""+valor2.charAt(3)+"\"\n");
                                    }
                                    if(valor2.length() == 5){
                                        Variables.add("    "+variable+" dw \""+valor2.charAt(0)+"\",\""+valor2.charAt(1)+"\","+
                                                      "\""+valor2.charAt(2)+"\",\""+valor2.charAt(3)+"\","+
                                                      "\""+valor2.charAt(4)+"\"\n");
                                    }
                                    if(valor2.length() == 6){
                                        Variables.add("    "+variable+" dw \""+valor2.charAt(0)+"\",\""+valor2.charAt(1)+"\","+
                                                      "\""+valor2.charAt(2)+"\",\""+valor2.charAt(3)+"\","+
                                                      "\""+valor2.charAt(4)+"\",\""+valor2.charAt(5)+"\"\n");
                                    }
                                    if(valor2.length() == 7){
                                        Variables.add("    "+variable+" dw \""+valor2.charAt(0)+"\",\""+valor2.charAt(1)+"\","+
                                                      "\""+valor2.charAt(2)+"\",\""+valor2.charAt(3)+"\","+
                                                      "\""+valor2.charAt(4)+"\",\""+valor2.charAt(5)+"\","+
                                                      "\""+valor2.charAt(6)+"\"\n");
                                    }
                                }
                            }else{
                                String variable = sentence.substring(8,sentence.length());
                                temporal++;
                                Tripletas.append("T"+temporal+" := "+variable+"\n");
                                cuadruplos("=",variable,"","T"+temporal);
                                identDataTemp.put(variable,"T"+temporal);
                                PanelSalida.append("-->   Se ha declarado una variable de tipo Decimal llamada " + variable + ".........\n");
                                Variables.add("    "+variable+" dw 0,0,0,0,0,0,0\n");
                            }
                        }
                        if(sentence.startsWith("Cadena")){
                            if(sentence.contains("=")){
                                String variable = "";
                                String valor = "";
                                for(Production id: identProdD1){
                                    if(sentence.contains(id.lexemeRank(1))){
                                        variable = id.lexemeRank(1);
                                        valor = id.lexemeRank(-2);
                                    }
                                }
                                temporal++;
                                Tripletas.append("T"+temporal+" := "+variable+"\n");
                                cuadruplos("=",variable,"","T"+temporal);
                                temporal++;
                                Tripletas.append("T"+temporal+" := T"+(temporal-1)+" + "+valor+"\n");
                                cuadruplos("+","T"+(temporal-1),valor,"T"+temporal);
                                Tripletas.append(variable+" := T"+temporal+"\n");
                                cuadruplos("=","T"+(temporal),"",variable);
                                PanelSalida.append("-->   Se ha declarado una variable de tipo Cadena llamada " + variable + " con valor " + valor + ".........\n");
                                Variables.add("    "+variable+" dw ");
                                for(int m = 0; m < valor.length()-2; m++){
                                    if(m == (valor.length()-3)){
                                        Variables.add("\""+valor.charAt(m+1)+"\"\n");
                                    }else{
                                        Variables.add("\""+valor.charAt(m+1)+"\",");
                                    }
                                }
                            }else{
                                String variable = sentence.substring(7,sentence.length());
                                temporal++;
                                Tripletas.append("T"+temporal+" := "+variable+"\n");
                                cuadruplos("=",variable,"","T"+temporal);
                                identDataTemp.put(variable,"T"+temporal);
                                PanelSalida.append("-->   Se ha declarado una variable de tipo Cadena llamada " + variable + ".........\n");
                            }
                        }
                        if(sentence.startsWith("Figura")){
                            if(sentence.contains("=")){
                                String variable = "";
                                String valor = "";
                                for(Production id: identProdD1){
                                    if(sentence.contains(id.lexemeRank(1))){
                                        variable = id.lexemeRank(1);
                                        valor = id.lexemeRank(-2);
                                    }
                                }
                                temporal++;
                                Tripletas.append("T"+temporal+" := "+variable+"\n");
                                cuadruplos("=",variable,"","T"+temporal);
                                temporal++;
                                Tripletas.append("T"+temporal+" := T"+(temporal-1)+" + "+valor+"\n");
                                cuadruplos("+","T"+(temporal-1),valor,"T"+temporal);
                                Tripletas.append(variable+" := T"+temporal+"\n");
                                cuadruplos("=","T"+(temporal),"",variable);
                                PanelSalida.append("-->   Se ha declarado una variable de tipo Figura llamada " + variable + " con valor " + valor + ".........\n");
                                if(valor.length() == 5){
                                    Variables.add("    "+variable+" dw \""+valor.charAt(0)+"\",\""+valor.charAt(1)+"\","+
                                                  "\""+valor.charAt(2)+"\",\""+valor.charAt(3)+"\","+
                                                  "\""+valor.charAt(4)+"\"\n");
                                }
                                if(valor.length() == 7){
                                    Variables.add("    "+variable+" dw \""+valor.charAt(0)+"\",\""+valor.charAt(1)+"\","+
                                                  "\""+valor.charAt(2)+"\",\""+valor.charAt(3)+"\","+
                                                  "\""+valor.charAt(4)+"\",\""+valor.charAt(5)+"\","+
                                                  "\""+valor.charAt(6)+"\"\n");
                                }
                                if(valor.length() == 8){
                                    Variables.add("    "+variable+" dw \""+valor.charAt(0)+"\",\""+valor.charAt(1)+"\","+
                                                  "\""+valor.charAt(2)+"\",\""+valor.charAt(3)+"\","+
                                                  "\""+valor.charAt(4)+"\",\""+valor.charAt(5)+"\","+
                                                  "\""+valor.charAt(6)+"\",\""+valor.charAt(7)+"\"\n");
                                }
                                if(valor.length() == 9){
                                    Variables.add("    "+variable+" dw \""+valor.charAt(0)+"\",\""+valor.charAt(1)+"\","+
                                                  "\""+valor.charAt(2)+"\",\""+valor.charAt(3)+"\","+
                                                  "\""+valor.charAt(4)+"\",\""+valor.charAt(5)+"\","+
                                                  "\""+valor.charAt(6)+"\",\""+valor.charAt(7)+"\","+
                                                  "\""+valor.charAt(8)+"\"\n");
                                }
                                if(valor.length() == 10){
                                    Variables.add("    "+variable+" dw \""+valor.charAt(0)+"\",\""+valor.charAt(1)+"\","+
                                                  "\""+valor.charAt(2)+"\",\""+valor.charAt(3)+"\","+
                                                  "\""+valor.charAt(4)+"\",\""+valor.charAt(5)+"\","+
                                                  "\""+valor.charAt(6)+"\",\""+valor.charAt(7)+"\","+
                                                  "\""+valor.charAt(8)+"\",\""+valor.charAt(9)+"\"\n");
                                }
                            }else{
                                String variable = sentence.substring(7,sentence.length());
                                temporal++;
                                Tripletas.append("T"+temporal+" := "+variable+"\n");
                                cuadruplos("=",variable,"","T"+temporal);
                                identDataTemp.put(variable,"T"+temporal);
                                PanelSalida.append("-->   Se ha declarado una variable de tipo Figura llamada " + variable + ".........\n");
                                Variables.add("    "+variable+" dw 0,0,0,0,0,0,0,0,0,0\n");
                            }
                        }
                        if(sentence.startsWith("Color")){
                            
                            if(sentence.contains("=")){
                                String variable = "";
                                String valor = "";
                                for(Production id: identProdD1){
                                    if(sentence.contains(id.lexemeRank(1))){
                                        variable = id.lexemeRank(1);
                                        valor = id.lexemeRank(-2);
                                    }
                                }
                                temporal++;
                                Tripletas.append("T"+temporal+" := "+variable+"\n");
                                cuadruplos("=",variable,"","T"+temporal);
                                temporal++;
                                Tripletas.append("T"+temporal+" := T"+(temporal-1)+" + "+valor+"\n");
                                cuadruplos("+","T"+(temporal-1),valor,"T"+temporal);
                                Tripletas.append(variable+" := T"+temporal+"\n");
                                cuadruplos("=","T"+(temporal),"",variable);
                                PanelSalida.append("-->   Se ha declarado una variable de tipo Color llamada " + variable + " con valor " + valor + ".........\n");
                                if(valor.length() == 4){
                                    Variables.add("    "+variable+" dw \""+valor.charAt(0)+"\",\""+valor.charAt(1)+"\","+
                                                  "\""+valor.charAt(2)+"\",\""+valor.charAt(3)+"\"\n");
                                }
                                if(valor.length() == 5){
                                    Variables.add("    "+variable+" dw \""+valor.charAt(0)+"\",\""+valor.charAt(1)+"\","+
                                                  "\""+valor.charAt(2)+"\",\""+valor.charAt(3)+"\","+
                                                  "\""+valor.charAt(4)+"\"\n");
                                }
                                if(valor.length() == 6){
                                    Variables.add("    "+variable+" dw \""+valor.charAt(0)+"\",\""+valor.charAt(1)+"\","+
                                                  "\""+valor.charAt(2)+"\",\""+valor.charAt(3)+"\","+
                                                  "\""+valor.charAt(4)+"\",\""+valor.charAt(5)+"\"\n");
                                }
                            }else{
                                String variable = sentence.substring(6,sentence.length());
                                temporal++;
                                Tripletas.append("T"+temporal+" := "+variable+"\n");
                                cuadruplos("=",variable,"","T"+temporal);
                                identDataTemp.put(variable,"T"+temporal);
                                PanelSalida.append("-->   Se ha declarado una variable de tipo Color llamada " + variable + ".........\n");
                                Variables.add("    "+variable+" dw 0,0,0,0,0,0\n");
                            }
                        }
                        if(sentence.startsWith("Resultado")){
                            String variable = sentence.substring(10,sentence.length());
                            temporal++;
                            Tripletas.append("T"+temporal+" := "+variable+"\n");
                            cuadruplos("=",variable,"","T"+temporal);
                            identDataTemp.put(variable,"T"+temporal);
                            PanelSalida.append("-->   Se ha declarado una variable de tipo Resultado llamada " + variable + ".........\n");
                            Variables.add("    "+variable.replaceFirst("#","")+" dw 0,0,0,0,0,0,0,0\n");
                        }
                        for(Production id: identProdA1){
                            if(sentence.startsWith(id.lexemeRank(0))){

                                String tipo = "";
                                if(id.lexicalCompRank(-2).equals("Numero_Entero")){
                                    if(id.lexemeRank(-3).equals("-")){
                                        temporal++;
                                        Tripletas.append("T"+temporal+" := "+identDataTemp.get(id.lexemeRank(0))+" + "+id.lexemeRank(-2)+"\n");
                                        cuadruplos("+",identDataTemp.get(id.lexemeRank(0)),id.lexemeRank(-2),"T"+temporal);
                                        temporal++;
                                        if(identDataTemp.containsKey("-1")){
                                            Tripletas.append(id.lexemeRank(0)+" := T"+(temporal-1)+" * "+identDataTemp.get("-1")+"\n");
                                            cuadruplos("*","T"+(temporal-1),"T"+identDataTemp.get("-1"),id.lexemeRank(0));
                                            CodigoObjeto.add("    MOV AX,"+id.lexemeRank(0)+"\n"
                                                           + "    MUL Negativo\n"
                                                           + "    MOV "+id.lexemeRank(0)+",AX\n");
                                        }else{
                                            Tripletas.append("T"+temporal+" := 1 - 2"+"\n");
                                            identDataTemp.put("-1","T"+temporal);
                                            cuadruplos("-","1","2","T"+temporal);
                                            Tripletas.append(id.lexemeRank(0)+" := T"+(temporal-1)+" * T"+(temporal)+"\n");
                                            cuadruplos("*","T"+(temporal-1),"T"+temporal,id.lexemeRank(0));
                                            Variables.add("    Negativo dw \"-\"\n");
                                            CodigoObjeto.add("    MOV AX,"+id.lexemeRank(0)+"\n"
                                                           + "    MUL Negativo\n"
                                                           + "    MOV "+id.lexemeRank(0)+",AX\n");
                                        }
                                    }else{
                                        temporal++;
                                        Tripletas.append("T"+temporal+" := "+identDataTemp.get(id.lexemeRank(0))+" + "+id.lexemeRank(-2)+"\n");
                                        cuadruplos("+",identDataTemp.get(id.lexemeRank(0)),id.lexemeRank(-2),"T"+temporal);
                                        Tripletas.append(id.lexemeRank(0)+" := T"+temporal+"\n");
                                        cuadruplos("=","T"+temporal,"",id.lexemeRank(0));
                                        if(id.lexemeRank(-2).length() == 1){
                                            CodigoObjeto.add("    ADD "+id.lexemeRank(0)+"[0],\""+id.lexemeRank(-2).charAt(0)+"\"\n");
                                        }
                                        if(id.lexemeRank(-2).length() == 2){
                                            CodigoObjeto.add("    ADD "+id.lexemeRank(0)+"[0],\""+id.lexemeRank(-2).charAt(0)+"\"\n"
                                                           + "    ADD "+id.lexemeRank(0)+"[2],\""+id.lexemeRank(-2).charAt(1)+"\"\n");
                                        }
                                        if(id.lexemeRank(-2).length() == 3){
                                            CodigoObjeto.add("    ADD "+id.lexemeRank(0)+"[0],\""+id.lexemeRank(-2).charAt(0)+"\"\n"
                                                           + "    ADD "+id.lexemeRank(0)+"[2],\""+id.lexemeRank(-2).charAt(1)+"\"\n"
                                                           + "    ADD "+id.lexemeRank(0)+"[4],\""+id.lexemeRank(-2).charAt(2)+"\"\n");
                                        }
                                        if(id.lexemeRank(-2).length() == 4){
                                            CodigoObjeto.add("    ADD "+id.lexemeRank(0)+"[0],\""+id.lexemeRank(-2).charAt(0)+"\"\n"
                                                           + "    ADD "+id.lexemeRank(0)+"[2],\""+id.lexemeRank(-2).charAt(1)+"\"\n"
                                                           + "    ADD "+id.lexemeRank(0)+"[4],\""+id.lexemeRank(-2).charAt(2)+"\"\n"
                                                           + "    ADD "+id.lexemeRank(0)+"[6],\""+id.lexemeRank(-2).charAt(3)+"\"\n");
                                        }
                                        if(id.lexemeRank(-2).length() == 5){
                                            CodigoObjeto.add("    ADD "+id.lexemeRank(0)+"[0],\""+id.lexemeRank(-2).charAt(0)+"\"\n"
                                                           + "    ADD "+id.lexemeRank(0)+"[2],\""+id.lexemeRank(-2).charAt(1)+"\"\n"
                                                           + "    ADD "+id.lexemeRank(0)+"[4],\""+id.lexemeRank(-2).charAt(2)+"\"\n"
                                                           + "    ADD "+id.lexemeRank(0)+"[6],\""+id.lexemeRank(-2).charAt(3)+"\"\n"
                                                           + "    ADD "+id.lexemeRank(0)+"[8],\""+id.lexemeRank(-2).charAt(4)+"\"\n");
                                        }
                                        if(id.lexemeRank(-2).length() == 6){
                                            CodigoObjeto.add("    ADD "+id.lexemeRank(0)+"[0],\""+id.lexemeRank(-2).charAt(0)+"\"\n"
                                                           + "    ADD "+id.lexemeRank(0)+"[2],\""+id.lexemeRank(-2).charAt(1)+"\"\n"
                                                           + "    ADD "+id.lexemeRank(0)+"[4],\""+id.lexemeRank(-2).charAt(2)+"\"\n"
                                                           + "    ADD "+id.lexemeRank(0)+"[6],\""+id.lexemeRank(-2).charAt(3)+"\"\n"
                                                           + "    ADD "+id.lexemeRank(0)+"[8],\""+id.lexemeRank(-2).charAt(4)+"\"\n"
                                                           + "    ADD "+id.lexemeRank(0)+"[10],\""+id.lexemeRank(-2).charAt(5)+"\"\n");
                                        }
                                    }
                                    tipo = "Entero";
                                    PanelSalida.append("-->   Se le esta asignando a la variable " + id.lexemeRank(0) + " de tipo " + tipo + " un nuevo valor de " + id.lexemeRank(-3) + id.lexemeRank(-2) + ".........\n");
                                }else if(id.lexicalCompRank(-2).equals("Numero_Decimal")){
                                    if(id.lexemeRank(-3).equals("-")){
                                        temporal++;
                                        Tripletas.append("T"+temporal+" := "+identDataTemp.get(id.lexemeRank(0))+" + "+id.lexemeRank(-2)+"\n");
                                        cuadruplos("+",identDataTemp.get(id.lexemeRank(0)),id.lexemeRank(-2),"T"+temporal);
                                        temporal++;
                                        if(identDataTemp.containsKey("-1")){
                                            Tripletas.append(id.lexemeRank(0)+" := T"+(temporal-1)+" * "+identDataTemp.get("-1")+"\n");
                                            cuadruplos("*","T"+(temporal-1),"T"+identDataTemp.get("-1"),id.lexemeRank(0));
                                            CodigoObjeto.add("    MOV AX,"+id.lexemeRank(0)+"\n"
                                                           + "    MUL Negativo\n"
                                                           + "    MOV "+id.lexemeRank(0)+",AX\n");
                                        }else{
                                            Tripletas.append("T"+temporal+" := 1 - 2"+"\n");
                                            identDataTemp.put("-1","T"+temporal);
                                            cuadruplos("-","1","2","T"+temporal);
                                            Tripletas.append(id.lexemeRank(0)+" := T"+(temporal-1)+" * T"+(temporal)+"\n");
                                            cuadruplos("*","T"+(temporal-1),"T"+temporal,id.lexemeRank(0));
                                            Variables.add("    Negativo dw \"-\"\n");
                                            CodigoObjeto.add("    MOV AX,"+id.lexemeRank(0)+"\n"
                                                           + "    MUL Negativo\n"
                                                           + "    MOV "+id.lexemeRank(0)+",AX\n");
                                        }
                                    }else{
                                        temporal++;
                                        Tripletas.append("T"+temporal+" := "+identDataTemp.get(id.lexemeRank(0))+" + "+id.lexemeRank(-2)+"\n");
                                        cuadruplos("+",identDataTemp.get(id.lexemeRank(0)),id.lexemeRank(-2),"T"+temporal);
                                        Tripletas.append(id.lexemeRank(0)+" := T"+temporal+"\n");
                                        cuadruplos("=","T"+temporal,"",id.lexemeRank(0));
                                        if(id.lexemeRank(-2).length() == 3){
                                            CodigoObjeto.add("    ADD "+id.lexemeRank(0)+"[0],\""+id.lexemeRank(-2).charAt(0)+"\"\n"
                                                           + "    ADD "+id.lexemeRank(0)+"[2],\""+id.lexemeRank(-2).charAt(1)+"\"\n"
                                                           + "    ADD "+id.lexemeRank(0)+"[4],\""+id.lexemeRank(-2).charAt(2)+"\"\n");
                                        }
                                        if(id.lexemeRank(-2).length() == 4){
                                            CodigoObjeto.add("    ADD "+id.lexemeRank(0)+"[0],\""+id.lexemeRank(-2).charAt(0)+"\"\n"
                                                           + "    ADD "+id.lexemeRank(0)+"[2],\""+id.lexemeRank(-2).charAt(1)+"\"\n"
                                                           + "    ADD "+id.lexemeRank(0)+"[4],\""+id.lexemeRank(-2).charAt(2)+"\"\n"
                                                           + "    ADD "+id.lexemeRank(0)+"[6],\""+id.lexemeRank(-2).charAt(3)+"\"\n");
                                        }
                                        if(id.lexemeRank(-2).length() == 5){
                                            CodigoObjeto.add("    ADD "+id.lexemeRank(0)+"[0],\""+id.lexemeRank(-2).charAt(0)+"\"\n"
                                                           + "    ADD "+id.lexemeRank(0)+"[2],\""+id.lexemeRank(-2).charAt(1)+"\"\n"
                                                           + "    ADD "+id.lexemeRank(0)+"[4],\""+id.lexemeRank(-2).charAt(2)+"\"\n"
                                                           + "    ADD "+id.lexemeRank(0)+"[6],\""+id.lexemeRank(-2).charAt(3)+"\"\n"
                                                           + "    ADD "+id.lexemeRank(0)+"[8],\""+id.lexemeRank(-2).charAt(4)+"\"\n");
                                        }
                                        if(id.lexemeRank(-2).length() == 6){
                                            CodigoObjeto.add("    ADD "+id.lexemeRank(0)+"[0],\""+id.lexemeRank(-2).charAt(0)+"\"\n"
                                                           + "    ADD "+id.lexemeRank(0)+"[2],\""+id.lexemeRank(-2).charAt(1)+"\"\n"
                                                           + "    ADD "+id.lexemeRank(0)+"[4],\""+id.lexemeRank(-2).charAt(2)+"\"\n"
                                                           + "    ADD "+id.lexemeRank(0)+"[6],\""+id.lexemeRank(-2).charAt(3)+"\"\n"
                                                           + "    ADD "+id.lexemeRank(0)+"[8],\""+id.lexemeRank(-2).charAt(4)+"\"\n"
                                                           + "    ADD "+id.lexemeRank(0)+"[10],\""+id.lexemeRank(-2).charAt(5)+"\"\n");
                                        }
                                    }
                                    tipo = "Decimal";
                                    PanelSalida.append("-->   Se le esta asignando a la variable " + id.lexemeRank(0) + " de tipo " + tipo + " un nuevo valor de " + id.lexemeRank(-3) + id.lexemeRank(-2) + ".........\n");
                                }else if(id.lexicalCompRank(-2).equals("Identificador_Cadena")){
                                    temporal++;
                                    Tripletas.append("T"+temporal+" := "+identDataTemp.get(id.lexemeRank(0))+" + "+id.lexemeRank(-2)+"\n");
                                    cuadruplos("+",identDataTemp.get(id.lexemeRank(0)),id.lexemeRank(-2),"T"+temporal);
                                    Tripletas.append(id.lexemeRank(0)+" := T"+temporal+"\n");
                                    cuadruplos("=","T"+temporal,"",id.lexemeRank(0));
                                    tipo = "Cadena";
                                    PanelSalida.append("-->   Se le esta asignando a la variable " + id.lexemeRank(0) + " de tipo " + tipo + " un nuevo valor de " + id.lexemeRank(-2) + ".........\n");
                                    Variables.add("    "+id.lexemeRank(0)+" dw ");
                                    for(int m = 0; m < id.lexemeRank(-2).length()-2; m++){
                                        if(m == (id.lexemeRank(-2).length()-3)){
                                            CodigoObjeto.add("    ADD "+id.lexemeRank(0)+"["+m+"],\""+id.lexemeRank(-2).charAt(m+1)+"\"\n");
                                            Variables.add("0\n");
                                        }else{
                                            Variables.add("0,");
                                            CodigoObjeto.add("    ADD "+id.lexemeRank(0)+"["+m+"],\""+id.lexemeRank(-2).charAt(m+1)+"\"\n");
                                        }
                                    }
                                }else if(id.lexicalCompRank(-2).equals("Palabra_Reservada_12") || id.lexicalCompRank(-2).equals("Palabra_Reservada_13") || id.lexicalCompRank(-2).equals("Palabra_Reservada_14") || id.lexicalCompRank(-2).equals("Palabra_Reservada_15") || id.lexicalCompRank(-2).equals("Palabra_Reservada_16")){
                                    temporal++;
                                    Tripletas.append("T"+temporal+" := "+identDataTemp.get(id.lexemeRank(0))+" + "+id.lexemeRank(-2)+"\n");
                                    cuadruplos("+",identDataTemp.get(id.lexemeRank(0)),id.lexemeRank(-2),"T"+temporal);
                                    Tripletas.append(id.lexemeRank(0)+" := T"+temporal+"\n");
                                    cuadruplos("=","T"+temporal,"",id.lexemeRank(0));
                                    tipo = "Color";
                                    PanelSalida.append("-->   Se le esta asignando a la variable " + id.lexemeRank(0) + " de tipo " + tipo + " un nuevo valor de " + id.lexemeRank(-2) + ".........\n");
                                    if(id.lexemeRank(-2).length() == 4){
                                        CodigoObjeto.add("    ADD "+id.lexemeRank(0)+"[0],\""+id.lexemeRank(-2).charAt(0)+"\"\n"
                                                       + "    ADD "+id.lexemeRank(0)+"[2],\""+id.lexemeRank(-2).charAt(1)+"\"\n"
                                                       + "    ADD "+id.lexemeRank(0)+"[4],\""+id.lexemeRank(-2).charAt(2)+"\"\n"
                                                       + "    ADD "+id.lexemeRank(0)+"[6],\""+id.lexemeRank(-2).charAt(3)+"\"\n");
                                    }
                                    if(id.lexemeRank(-2).length() == 5){
                                        CodigoObjeto.add("    ADD "+id.lexemeRank(0)+"[0],\""+id.lexemeRank(-2).charAt(0)+"\"\n"
                                                       + "    ADD "+id.lexemeRank(0)+"[2],\""+id.lexemeRank(-2).charAt(1)+"\"\n"
                                                       + "    ADD "+id.lexemeRank(0)+"[4],\""+id.lexemeRank(-2).charAt(2)+"\"\n"
                                                       + "    ADD "+id.lexemeRank(0)+"[6],\""+id.lexemeRank(-2).charAt(3)+"\"\n"
                                                       + "    ADD "+id.lexemeRank(0)+"[8],\""+id.lexemeRank(-2).charAt(4)+"\"\n");
                                    }
                                    if(id.lexemeRank(-2).length() == 6){
                                        CodigoObjeto.add("    ADD "+id.lexemeRank(0)+"[0],\""+id.lexemeRank(-2).charAt(0)+"\"\n"
                                                       + "    ADD "+id.lexemeRank(0)+"[2],\""+id.lexemeRank(-2).charAt(1)+"\"\n"
                                                       + "    ADD "+id.lexemeRank(0)+"[4],\""+id.lexemeRank(-2).charAt(2)+"\"\n"
                                                       + "    ADD "+id.lexemeRank(0)+"[6],\""+id.lexemeRank(-2).charAt(3)+"\"\n"
                                                       + "    ADD "+id.lexemeRank(0)+"[8],\""+id.lexemeRank(-2).charAt(4)+"\"\n"
                                                       + "    ADD "+id.lexemeRank(0)+"[10],\""+id.lexemeRank(-2).charAt(5)+"\"\n");
                                    }
                                }else if(id.lexicalCompRank(-2).equals("Palabra_Reservada_17") || id.lexicalCompRank(-2).equals("Palabra_Reservada_18") || id.lexicalCompRank(-2).equals("Palabra_Reservada_19") || id.lexicalCompRank(-2).equals("Palabra_Reservada_20") || id.lexicalCompRank(-2).equals("Palabra_Reservada_21")){
                                    temporal++;
                                    Tripletas.append("T"+temporal+" := "+identDataTemp.get(id.lexemeRank(0))+" + "+id.lexemeRank(-2)+"\n");
                                    cuadruplos("+",identDataTemp.get(id.lexemeRank(0)),id.lexemeRank(-2),"T"+temporal);
                                    Tripletas.append(id.lexemeRank(0)+" := T"+temporal+"\n");
                                    cuadruplos("=","T"+temporal,"",id.lexemeRank(0));
                                    tipo = "Figura";
                                    PanelSalida.append("-->   Se le esta asignando a la variable " + id.lexemeRank(0) + " de tipo " + tipo + " un nuevo valor de " + id.lexemeRank(-2) + ".........\n");
                                    if(id.lexemeRank(-2).length() == 5){
                                        CodigoObjeto.add("    ADD "+id.lexemeRank(0)+"[0],\""+id.lexemeRank(-2).charAt(0)+"\"\n"
                                                       + "    ADD "+id.lexemeRank(0)+"[2],\""+id.lexemeRank(-2).charAt(1)+"\"\n"
                                                       + "    ADD "+id.lexemeRank(0)+"[4],\""+id.lexemeRank(-2).charAt(2)+"\"\n"
                                                       + "    ADD "+id.lexemeRank(0)+"[6],\""+id.lexemeRank(-2).charAt(3)+"\"\n"
                                                       + "    ADD "+id.lexemeRank(0)+"[8],\""+id.lexemeRank(-2).charAt(4)+"\"\n");
                                    }
                                    if(id.lexemeRank(-2).length() == 7){
                                        CodigoObjeto.add("    ADD "+id.lexemeRank(0)+"[0],\""+id.lexemeRank(-2).charAt(0)+"\"\n"
                                                       + "    ADD "+id.lexemeRank(0)+"[2],\""+id.lexemeRank(-2).charAt(1)+"\"\n"
                                                       + "    ADD "+id.lexemeRank(0)+"[4],\""+id.lexemeRank(-2).charAt(2)+"\"\n"
                                                       + "    ADD "+id.lexemeRank(0)+"[6],\""+id.lexemeRank(-2).charAt(3)+"\"\n"
                                                       + "    ADD "+id.lexemeRank(0)+"[8],\""+id.lexemeRank(-2).charAt(4)+"\"\n"
                                                       + "    ADD "+id.lexemeRank(0)+"[10],\""+id.lexemeRank(-2).charAt(5)+"\"\n"
                                                       + "    ADD "+id.lexemeRank(0)+"[12],\""+id.lexemeRank(-2).charAt(6)+"\"\n");
                                    }
                                    if(id.lexemeRank(-2).length() == 8){
                                        CodigoObjeto.add("    ADD "+id.lexemeRank(0)+"[0],\""+id.lexemeRank(-2).charAt(0)+"\"\n"
                                                       + "    ADD "+id.lexemeRank(0)+"[2],\""+id.lexemeRank(-2).charAt(1)+"\"\n"
                                                       + "    ADD "+id.lexemeRank(0)+"[4],\""+id.lexemeRank(-2).charAt(2)+"\"\n"
                                                       + "    ADD "+id.lexemeRank(0)+"[6],\""+id.lexemeRank(-2).charAt(3)+"\"\n"
                                                       + "    ADD "+id.lexemeRank(0)+"[8],\""+id.lexemeRank(-2).charAt(4)+"\"\n"
                                                       + "    ADD "+id.lexemeRank(0)+"[10],\""+id.lexemeRank(-2).charAt(5)+"\"\n"
                                                       + "    ADD "+id.lexemeRank(0)+"[12],\""+id.lexemeRank(-2).charAt(6)+"\"\n"
                                                       + "    ADD "+id.lexemeRank(0)+"[14],\""+id.lexemeRank(-2).charAt(7)+"\"\n");
                                    }
                                    if(id.lexemeRank(-2).length() == 9){
                                        CodigoObjeto.add("    ADD "+id.lexemeRank(0)+"[0],\""+id.lexemeRank(-2).charAt(0)+"\"\n"
                                                       + "    ADD "+id.lexemeRank(0)+"[2],\""+id.lexemeRank(-2).charAt(1)+"\"\n"
                                                       + "    ADD "+id.lexemeRank(0)+"[4],\""+id.lexemeRank(-2).charAt(2)+"\"\n"
                                                       + "    ADD "+id.lexemeRank(0)+"[6],\""+id.lexemeRank(-2).charAt(3)+"\"\n"
                                                       + "    ADD "+id.lexemeRank(0)+"[8],\""+id.lexemeRank(-2).charAt(4)+"\"\n"
                                                       + "    ADD "+id.lexemeRank(0)+"[10],\""+id.lexemeRank(-2).charAt(5)+"\"\n"
                                                       + "    ADD "+id.lexemeRank(0)+"[12],\""+id.lexemeRank(-2).charAt(6)+"\"\n"
                                                       + "    ADD "+id.lexemeRank(0)+"[14],\""+id.lexemeRank(-2).charAt(7)+"\"\n"
                                                       + "    ADD "+id.lexemeRank(0)+"[16],\""+id.lexemeRank(-2).charAt(8)+"\"\n");
                                    }
                                    if(id.lexemeRank(-2).length() == 10){
                                        CodigoObjeto.add("    ADD "+id.lexemeRank(0)+"[0],\""+id.lexemeRank(-2).charAt(0)+"\"\n"
                                                       + "    ADD "+id.lexemeRank(0)+"[2],\""+id.lexemeRank(-2).charAt(1)+"\"\n"
                                                       + "    ADD "+id.lexemeRank(0)+"[4],\""+id.lexemeRank(-2).charAt(2)+"\"\n"
                                                       + "    ADD "+id.lexemeRank(0)+"[6],\""+id.lexemeRank(-2).charAt(3)+"\"\n"
                                                       + "    ADD "+id.lexemeRank(0)+"[8],\""+id.lexemeRank(-2).charAt(4)+"\"\n"
                                                       + "    ADD "+id.lexemeRank(0)+"[10],\""+id.lexemeRank(-2).charAt(5)+"\"\n"
                                                       + "    ADD "+id.lexemeRank(0)+"[12],\""+id.lexemeRank(-2).charAt(6)+"\"\n"
                                                       + "    ADD "+id.lexemeRank(0)+"[14],\""+id.lexemeRank(-2).charAt(7)+"\"\n"
                                                       + "    ADD "+id.lexemeRank(0)+"[16],\""+id.lexemeRank(-2).charAt(8)+"\"\n"
                                                       + "    ADD "+id.lexemeRank(0)+"[18],\""+id.lexemeRank(-2).charAt(9)+"\"\n");
                                    }
                                }
                            }
                        }
                        for(Production id: identProdF11){
                            if(sentence.startsWith(id.lexemeRank(0))){
                                if(id.lexemeRank(0).equals("Sumar") && sentence.contains(id.lexemeRank(2)) && sentence.contains(id.lexemeRank(4)) && sentence.contains(id.lexemeRank(7))){
                                    temporal++;
                                    if(identDataTemp.containsKey(identDataTypeR.get(id.lexemeRank(7)))){
                                        Tripletas.append(id.lexemeRank(7)+" := "+identDataTemp.get(identDataTypeR.get(id.lexemeRank(7)))+"\n");
                                        cuadruplos("=",identDataTemp.get(identDataTypeR.get(id.lexemeRank(7))),"",id.lexemeRank(7));
                                        CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(identDataTypeR.get(id.lexemeRank(7)))+"\n"
                                                       + "    MOV "+id.lexemeRank(7)+",AX\n");
                                    }else{
                                        Tripletas.append("T"+temporal+" := "+id.lexemeRank(2)+" + "+id.lexemeRank(4)+"\n");
                                        cuadruplos("+",id.lexemeRank(2),id.lexemeRank(4),"T"+temporal);
                                        identDataTemp.put(identDataTypeR.get(id.lexemeRank(7)),"T"+temporal);
                                        Tripletas.append(id.lexemeRank(7)+" := T"+temporal+"\n");
                                        cuadruplos("=","T"+temporal,"",id.lexemeRank(7));
                                        CodigoObjeto.add("    MOV AX,"+id.lexemeRank(2)+"\n"
                                                       + "    MOV BX,"+id.lexemeRank(4)+"\n"
                                                       + "    ADD AX,BX\n"
                                                       + "    MOV "+id.lexemeRank(7)+",AX\n");
                                    }
                                    PanelSalida.append("-->   Se ha utlizado la función de Sumar, que tiene como parametros a:        " + id.lexemeRank(2) + " = " + identDataTypeV2.get(id.lexemeRank(2)) + " , " + id.lexemeRank(4) + " = " + identDataTypeV2.get(id.lexemeRank(4)) + "\nDonde el resultado de la operación es: " + identDataTypeR.get(id.lexemeRank(7)) + " , y se guardó en: " + id.lexemeRank(7) + ".........\n");
                                    break;
                                }else if(id.lexemeRank(0).equals("Restar") && sentence.contains(id.lexemeRank(2)) && sentence.contains(id.lexemeRank(4)) && sentence.contains(id.lexemeRank(7))){
                                    temporal++;
                                    if(identDataTemp.containsKey(identDataTypeR.get(id.lexemeRank(7)))){
                                        Tripletas.append(id.lexemeRank(7)+" := "+identDataTemp.get(identDataTypeR.get(id.lexemeRank(7)))+"\n");
                                        cuadruplos("=",identDataTemp.get(identDataTypeR.get(id.lexemeRank(7))),"",id.lexemeRank(7));
                                        CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(identDataTypeR.get(id.lexemeRank(7)))+"\n"
                                                       + "    MOV "+id.lexemeRank(7)+",AX\n");
                                    }else{
                                        Tripletas.append("T"+temporal+" := "+id.lexemeRank(2)+" - "+id.lexemeRank(4)+"\n");
                                        cuadruplos("-",id.lexemeRank(2),id.lexemeRank(4),"T"+temporal);
                                        identDataTemp.put(identDataTypeR.get(id.lexemeRank(7)),"T"+temporal);
                                        Tripletas.append(id.lexemeRank(7)+" := T"+temporal+"\n");
                                        cuadruplos("=","T"+temporal,"",id.lexemeRank(7));
                                        CodigoObjeto.add("    MOV AX,"+id.lexemeRank(2)+"\n"
                                                       + "    MOV BX,"+id.lexemeRank(4)+"\n"
                                                       + "    SUB AX,BX\n"
                                                       + "    MOV "+id.lexemeRank(7)+",AX\n");
                                    }
                                    PanelSalida.append("-->   Se ha utlizado la función de Restar, que tiene como parametros a:       " + id.lexemeRank(2) + " = " + identDataTypeV2.get(id.lexemeRank(2)) + " , " + id.lexemeRank(4) + " = " + identDataTypeV2.get(id.lexemeRank(4)) + "\nDonde el resultado de la operación es: " + identDataTypeR.get(id.lexemeRank(7)) + " , y se guardó en: " + id.lexemeRank(7) + ".........\n");
                                    break;
                                }else if(id.lexemeRank(0).equals("Multiplicar") && sentence.contains(id.lexemeRank(2)) && sentence.contains(id.lexemeRank(4)) && sentence.contains(id.lexemeRank(7))){
                                    temporal++;
                                    if(identDataTemp.containsKey(identDataTypeR.get(id.lexemeRank(7)))){
                                        Tripletas.append(id.lexemeRank(7)+" := "+identDataTemp.get(identDataTypeR.get(id.lexemeRank(7)))+"\n");
                                        cuadruplos("=",identDataTemp.get(identDataTypeR.get(id.lexemeRank(7))),"",id.lexemeRank(7));
                                        CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(identDataTypeR.get(id.lexemeRank(7)))+"\n"
                                                       + "    MOV "+id.lexemeRank(7)+",AX\n");
                                    }else{
                                        Tripletas.append("T"+temporal+" := "+id.lexemeRank(2)+" * "+id.lexemeRank(4)+"\n");
                                        cuadruplos("*",id.lexemeRank(2),id.lexemeRank(4),"T"+temporal);
                                        identDataTemp.put(identDataTypeR.get(id.lexemeRank(7)),"T"+temporal);
                                        Tripletas.append(id.lexemeRank(7)+" := T"+temporal+"\n");
                                        cuadruplos("=","T"+temporal,"",id.lexemeRank(7));
                                        CodigoObjeto.add("    MOV AX,"+id.lexemeRank(2)+"\n"
                                                       + "    MOV BX,"+id.lexemeRank(4)+"\n"
                                                       + "    MUL BX\n"
                                                       + "    MOV "+id.lexemeRank(7)+",AX\n");
                                    }
                                    PanelSalida.append("-->   Se ha utlizado la función de Multiplicar, que tiene como parametros a:  " + id.lexemeRank(2) + " = " + identDataTypeV2.get(id.lexemeRank(2)) + " , " + id.lexemeRank(4) + " = " + identDataTypeV2.get(id.lexemeRank(4)) + "\nDonde el resultado de la operación es: " + identDataTypeR.get(id.lexemeRank(7)) + " , y se guardó en: " + id.lexemeRank(7) + ".........\n");
                                    break;
                                }else if(id.lexemeRank(0).equals("Dividir") && sentence.contains(id.lexemeRank(2)) && sentence.contains(id.lexemeRank(4)) && sentence.contains(id.lexemeRank(7))){
                                    temporal++;
                                    if(identDataTemp.containsKey(identDataTypeR.get(id.lexemeRank(7)))){
                                        Tripletas.append(id.lexemeRank(7)+" := "+identDataTemp.get(identDataTypeR.get(id.lexemeRank(7)))+"\n");
                                        cuadruplos("=",identDataTemp.get(identDataTypeR.get(id.lexemeRank(7))),"",id.lexemeRank(7));
                                        CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(identDataTypeR.get(id.lexemeRank(7)))+"\n"
                                                       + "    MOV "+id.lexemeRank(7)+",AX\n");
                                    }else{
                                        Tripletas.append("T"+temporal+" := "+id.lexemeRank(2)+" / "+id.lexemeRank(4)+"\n");
                                        cuadruplos("/",id.lexemeRank(2),id.lexemeRank(4),"T"+temporal);
                                        identDataTemp.put(identDataTypeR.get(id.lexemeRank(7)),"T"+temporal);
                                        Tripletas.append(id.lexemeRank(7)+" := T"+temporal+"\n");
                                        cuadruplos("=","T"+temporal,"",id.lexemeRank(7));
                                        CodigoObjeto.add("    MOV AX,"+id.lexemeRank(2)+"\n"
                                                       + "    MOV BX,"+id.lexemeRank(4)+"\n"
                                                       + "    DIV BX\n"
                                                       + "    MOV "+id.lexemeRank(7)+",AX\n");
                                    }
                                    PanelSalida.append("-->   Se ha utlizado la función de Dividir, que tiene como parametros a:      " + id.lexemeRank(2) + " = " + identDataTypeV2.get(id.lexemeRank(2)) + " , " + id.lexemeRank(4) + " = " + identDataTypeV2.get(id.lexemeRank(4)) + "\nDonde el resultado de la operación es: " + identDataTypeR.get(id.lexemeRank(7)) + " , y se guardó en: " + id.lexemeRank(7) + ".........\n");
                                    break;
                                }
                            }
                        }
                        for(Production id: identProdF12){
                            if(sentence.startsWith(id.lexemeRank(0))){
                                if(id.lexemeRank(0).equals("Sumar") && sentence.contains(id.lexemeRank(2)) && sentence.contains(id.lexemeRank(4)) && sentence.contains(id.lexemeRank(6)) && sentence.contains(id.lexemeRank(9))){
                                    temporal++;
                                    if(identDataTemp.containsKey(identDataTypeR.get(id.lexemeRank(9)))){
                                        Tripletas.append(id.lexemeRank(9)+" := "+identDataTemp.get(identDataTypeR.get(id.lexemeRank(9)))+"\n");
                                        cuadruplos("=",identDataTemp.get(identDataTypeR.get(id.lexemeRank(9))),"",id.lexemeRank(9));
                                        CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(identDataTypeR.get(id.lexemeRank(9)))+"\n"
                                                       + "    MOV "+id.lexemeRank(9)+",AX\n");
                                    }else{
                                        if(identDataType.get(id.lexemeRank(2)).equals("Numero_Decimal")){
                                            double num1 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(2)));
                                            double num2 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(4)));
                                            double res = num1 + num2;
                                            if(identDataTemp.containsKey(Double.toString(res))){
                                                Tripletas.append("T"+temporal+" := "+identDataTemp.get(Double.toString(res))+" + "+id.lexemeRank(6)+"\n");
                                                cuadruplos("+",identDataTemp.get(Double.toString(res)),id.lexemeRank(6),"T"+temporal);
                                                CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(res)+"\n"
                                                               + "    MOV BX,"+id.lexemeRank(6)+"\n"
                                                               + "    ADD AX,BX\n"
                                                               + "    MOV "+id.lexemeRank(9)+",AX\n");
                                            }else{
                                                Tripletas.append("T"+temporal+" := "+id.lexemeRank(2)+" + "+id.lexemeRank(4)+"\n");
                                                cuadruplos("+",id.lexemeRank(2),id.lexemeRank(4),"T"+temporal);
                                                identDataTemp.put(Double.toString(res),"T"+temporal);
                                                temporal++;
                                                Tripletas.append("T"+temporal+" := T"+(temporal-1)+" + "+id.lexemeRank(6)+"\n");
                                                cuadruplos("+","T"+(temporal-1),id.lexemeRank(6),"T"+temporal);
                                                CodigoObjeto.add("    MOV AX,"+id.lexemeRank(2)+"\n"
                                                               + "    MOV BX,"+id.lexemeRank(4)+"\n"
                                                               + "    ADD AX,BX\n"
                                                               + "    MOV BX,"+id.lexemeRank(6)+"\n"
                                                               + "    ADD AX,BX"
                                                               + "    MOV "+id.lexemeRank(9)+",AX\n");
                                            }
                                        }
                                        if(identDataType.get(id.lexemeRank(2)).equals("Numero_Entero")){
                                            int num1 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(2)));
                                            int num2 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(4)));
                                            int res = num1 + num2;
                                            if(identDataTemp.containsKey(Integer.toString(res))){
                                                Tripletas.append("T"+temporal+" := "+identDataTemp.get(Integer.toString(res))+" + "+id.lexemeRank(6)+"\n");
                                                cuadruplos("+",identDataTemp.get(Integer.toString(res)),id.lexemeRank(6),"T"+temporal);
                                                CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(res)+"\n"
                                                               + "    MOV BX,"+id.lexemeRank(6)+"\n"
                                                               + "    ADD AX,BX\n"
                                                               + "    MOV "+id.lexemeRank(9)+",AX\n");
                                            }else{
                                                Tripletas.append("T"+temporal+" := "+id.lexemeRank(2)+" + "+id.lexemeRank(4)+"\n");
                                                cuadruplos("+",id.lexemeRank(2),id.lexemeRank(4),"T"+temporal);
                                                identDataTemp.put(Integer.toString(res),"T"+temporal);
                                                temporal++;
                                                Tripletas.append("T"+temporal+" := T"+(temporal-1)+" + "+id.lexemeRank(6)+"\n");
                                                cuadruplos("+","T"+(temporal-1),id.lexemeRank(6),"T"+temporal);
                                                CodigoObjeto.add("    MOV AX,"+id.lexemeRank(2)+"\n"
                                                               + "    MOV BX,"+id.lexemeRank(4)+"\n"
                                                               + "    ADD AX,BX\n"
                                                               + "    MOV BX,"+id.lexemeRank(6)+"\n"
                                                               + "    ADD AX,BX"
                                                               + "    MOV "+id.lexemeRank(9)+",AX\n");
                                            }
                                        }
                                        identDataTemp.put(identDataTypeR.get(id.lexemeRank(9)),"T"+temporal);
                                        Tripletas.append(id.lexemeRank(9)+" := T"+temporal+"\n");
                                        cuadruplos("=","T"+temporal,"",id.lexemeRank(9));
                                    }   
                                    PanelSalida.append("-->   Se ha utlizado la función de Sumar, que tiene como parametros a:        " + id.lexemeRank(2) + " = " + identDataTypeV2.get(id.lexemeRank(2)) + " , " + id.lexemeRank(4) + " = " + identDataTypeV2.get(id.lexemeRank(4)) + " , " + id.lexemeRank(6) + " = " + identDataTypeV2.get(id.lexemeRank(6)) +  "\nDonde el resultado de la operación es: " + identDataTypeR.get(id.lexemeRank(9)) + " , y se guardó en: " + id.lexemeRank(9) + ".........\n");
                                    break;
                                }else if(id.lexemeRank(0).equals("Restar") && sentence.contains(id.lexemeRank(2)) && sentence.contains(id.lexemeRank(4)) && sentence.contains(id.lexemeRank(6)) && sentence.contains(id.lexemeRank(9))){
                                    temporal++;
                                    if(identDataTemp.containsKey(identDataTypeR.get(id.lexemeRank(9)))){
                                        Tripletas.append(id.lexemeRank(9)+" := "+identDataTemp.get(identDataTypeR.get(id.lexemeRank(9)))+"\n");
                                        cuadruplos("=",identDataTemp.get(identDataTypeR.get(id.lexemeRank(9))),"",id.lexemeRank(9));
                                        CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(identDataTypeR.get(id.lexemeRank(9)))+"\n"
                                                       + "    MOV "+id.lexemeRank(9)+",AX\n");
                                    }else{
                                        if(identDataType.get(id.lexemeRank(2)).equals("Numero_Decimal")){
                                            double num1 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(2)));
                                            double num2 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(4)));
                                            double res = num1 - num2;
                                            if(identDataTemp.containsKey(Double.toString(res))){
                                                Tripletas.append("T"+temporal+" := "+identDataTemp.get(Double.toString(res))+" - "+id.lexemeRank(6)+"\n");
                                                cuadruplos("-",identDataTemp.get(Double.toString(res)),id.lexemeRank(6),"T"+temporal);
                                                CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(res)+"\n"
                                                               + "    MOV BX,"+id.lexemeRank(6)+"\n"
                                                               + "    SUB AX,BX\n"
                                                               + "    MOV "+id.lexemeRank(9)+",AX\n");
                                            }else{
                                                Tripletas.append("T"+temporal+" := "+id.lexemeRank(2)+" - "+id.lexemeRank(4)+"\n");
                                                cuadruplos("-",id.lexemeRank(2),id.lexemeRank(4),"T"+temporal);
                                                identDataTemp.put(Double.toString(res),"T"+temporal);
                                                temporal++;
                                                Tripletas.append("T"+temporal+" := T"+(temporal-1)+" - "+id.lexemeRank(6)+"\n");
                                                cuadruplos("-","T"+(temporal-1),id.lexemeRank(6),"T"+temporal);
                                                CodigoObjeto.add("    MOV AX,"+id.lexemeRank(2)+"\n"
                                                               + "    MOV BX,"+id.lexemeRank(4)+"\n"
                                                               + "    SUB AX,BX\n"
                                                               + "    MOV BX,"+id.lexemeRank(6)+"\n"
                                                               + "    SUB AX,BX"
                                                               + "    MOV "+id.lexemeRank(9)+",AX\n");
                                            }
                                        }
                                        if(identDataType.get(id.lexemeRank(2)).equals("Numero_Entero")){
                                            int num1 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(2)));
                                            int num2 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(4)));
                                            int res = num1 - num2;
                                            if(identDataTemp.containsKey(Integer.toString(res))){
                                                Tripletas.append("T"+temporal+" := "+identDataTemp.get(Integer.toString(res))+" - "+id.lexemeRank(6)+"\n");
                                                cuadruplos("-",identDataTemp.get(Integer.toString(res)),id.lexemeRank(6),"T"+temporal);
                                                CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(res)+"\n"
                                                               + "    MOV BX,"+id.lexemeRank(6)+"\n"
                                                               + "    SUB AX,BX\n"
                                                               + "    MOV "+id.lexemeRank(9)+",AX\n");
                                            }else{
                                                Tripletas.append("T"+temporal+" := "+id.lexemeRank(2)+" - "+id.lexemeRank(4)+"\n");
                                                cuadruplos("-",id.lexemeRank(2),id.lexemeRank(4),"T"+temporal);
                                                identDataTemp.put(Integer.toString(res),"T"+temporal);
                                                temporal++;
                                                Tripletas.append("T"+temporal+" := T"+(temporal-1)+" - "+id.lexemeRank(6)+"\n");
                                                cuadruplos("-","T"+(temporal-1),id.lexemeRank(6),"T"+temporal);
                                                CodigoObjeto.add("    MOV AX,"+id.lexemeRank(2)+"\n"
                                                               + "    MOV BX,"+id.lexemeRank(4)+"\n"
                                                               + "    SUB AX,BX\n"
                                                               + "    MOV BX,"+id.lexemeRank(6)+"\n"
                                                               + "    SUB AX,BX"
                                                               + "    MOV "+id.lexemeRank(9)+",AX\n");
                                            }
                                        }
                                        identDataTemp.put(identDataTypeR.get(id.lexemeRank(9)),"T"+temporal);
                                        Tripletas.append(id.lexemeRank(9)+" := T"+temporal+"\n");
                                        cuadruplos("=","T"+temporal,"",id.lexemeRank(9));
                                    }
                                    PanelSalida.append("-->   Se ha utlizado la función de Restar, que tiene como parametros a:       " + id.lexemeRank(2) + " = " + identDataTypeV2.get(id.lexemeRank(2)) + " , " + id.lexemeRank(4) + " = " + identDataTypeV2.get(id.lexemeRank(4)) + " , " + id.lexemeRank(6) + " = " + identDataTypeV2.get(id.lexemeRank(6)) +  "\nDonde el resultado de la operación es: " + identDataTypeR.get(id.lexemeRank(9)) + " , y se guardó en: " + id.lexemeRank(9) + ".........\n");
                                    break;
                                }else if(id.lexemeRank(0).equals("Multiplicar") && sentence.contains(id.lexemeRank(2)) && sentence.contains(id.lexemeRank(4)) && sentence.contains(id.lexemeRank(6)) && sentence.contains(id.lexemeRank(9))){
                                    temporal++;
                                    if(identDataTemp.containsKey(identDataTypeR.get(id.lexemeRank(9)))){
                                        Tripletas.append(id.lexemeRank(9)+" := "+identDataTemp.get(identDataTypeR.get(id.lexemeRank(9)))+"\n");
                                        cuadruplos("=",identDataTemp.get(identDataTypeR.get(id.lexemeRank(9))),"",id.lexemeRank(9));
                                        CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(identDataTypeR.get(id.lexemeRank(9)))+"\n"
                                                       + "    MOV "+id.lexemeRank(9)+",AX\n");
                                    }else{
                                        if(identDataType.get(id.lexemeRank(2)).equals("Numero_Decimal")){
                                            double num1 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(2)));
                                            double num2 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(4)));
                                            double res = num1 * num2;
                                            if(identDataTemp.containsKey(Double.toString(res))){
                                                Tripletas.append("T"+temporal+" := "+identDataTemp.get(Double.toString(res))+" * "+id.lexemeRank(6)+"\n");
                                                cuadruplos("*",identDataTemp.get(Double.toString(res)),id.lexemeRank(6),"T"+temporal);
                                                CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(res)+"\n"
                                                               + "    MOV BX,"+id.lexemeRank(6)+"\n"
                                                               + "    MUL BX\n"
                                                               + "    MOV "+id.lexemeRank(9)+",AX\n");
                                            }else{
                                                Tripletas.append("T"+temporal+" := "+id.lexemeRank(2)+" * "+id.lexemeRank(4)+"\n");
                                                cuadruplos("*",id.lexemeRank(2),id.lexemeRank(4),"T"+temporal);
                                                identDataTemp.put(Double.toString(res),"T"+temporal);
                                                temporal++;
                                                Tripletas.append("T"+temporal+" := T"+(temporal-1)+" * "+id.lexemeRank(6)+"\n");
                                                cuadruplos("*","T"+(temporal-1),id.lexemeRank(6),"T"+temporal);
                                                CodigoObjeto.add("    MOV AX,"+id.lexemeRank(2)+"\n"
                                                               + "    MOV BX,"+id.lexemeRank(4)+"\n"
                                                               + "    MUL BX\n"
                                                               + "    MOV BX,"+id.lexemeRank(6)+"\n"
                                                               + "    MUL BX"
                                                               + "    MOV "+id.lexemeRank(9)+",AX\n");
                                            }
                                        }
                                        if(identDataType.get(id.lexemeRank(2)).equals("Numero_Entero")){
                                            int num1 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(2)));
                                            int num2 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(4)));
                                            int res = num1 * num2;
                                            if(identDataTemp.containsKey(Integer.toString(res))){
                                                Tripletas.append("T"+temporal+" := "+identDataTemp.get(Integer.toString(res))+" * "+id.lexemeRank(6)+"\n");
                                                cuadruplos("*",identDataTemp.get(Integer.toString(res)),id.lexemeRank(6),"T"+temporal);
                                                CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(res)+"\n"
                                                               + "    MOV BX,"+id.lexemeRank(6)+"\n"
                                                               + "    MUL BX\n"
                                                               + "    MOV "+id.lexemeRank(9)+",AX\n");
                                            }else{
                                                Tripletas.append("T"+temporal+" := "+id.lexemeRank(2)+" * "+id.lexemeRank(4)+"\n");
                                                cuadruplos("*",id.lexemeRank(2),id.lexemeRank(4),"T"+temporal);
                                                identDataTemp.put(Integer.toString(res),"T"+temporal);
                                                temporal++;
                                                Tripletas.append("T"+temporal+" := T"+(temporal-1)+" * "+id.lexemeRank(6)+"\n");
                                                cuadruplos("*","T"+(temporal-1),id.lexemeRank(6),"T"+temporal);
                                                CodigoObjeto.add("    MOV AX,"+id.lexemeRank(2)+"\n"
                                                               + "    MOV BX,"+id.lexemeRank(4)+"\n"
                                                               + "    MUL BX\n"
                                                               + "    MOV BX,"+id.lexemeRank(6)+"\n"
                                                               + "    MUL BX"
                                                               + "    MOV "+id.lexemeRank(9)+",AX\n");
                                            }
                                        }
                                        identDataTemp.put(identDataTypeR.get(id.lexemeRank(9)),"T"+temporal);
                                        Tripletas.append(id.lexemeRank(9)+" := T"+temporal+"\n");
                                        cuadruplos("=","T"+temporal,"",id.lexemeRank(9));
                                    }
                                    PanelSalida.append("-->   Se ha utlizado la función de Multiplicar, que tiene como parametros a:  " + id.lexemeRank(2) + " = " + identDataTypeV2.get(id.lexemeRank(2)) + " , " + id.lexemeRank(4) + " = " + identDataTypeV2.get(id.lexemeRank(4)) + " , " + id.lexemeRank(6) + " = " + identDataTypeV2.get(id.lexemeRank(6)) +  "\nDonde el resultado de la operación es: " + identDataTypeR.get(id.lexemeRank(9)) + " , y se guardó en: " + id.lexemeRank(9) + ".........\n");
                                    break;
                                }else if(id.lexemeRank(0).equals("Dividir") && sentence.contains(id.lexemeRank(2)) && sentence.contains(id.lexemeRank(4)) && sentence.contains(id.lexemeRank(6)) && sentence.contains(id.lexemeRank(9))){
                                    temporal++;
                                    if(identDataTemp.containsKey(identDataTypeR.get(id.lexemeRank(9)))){
                                        Tripletas.append(id.lexemeRank(9)+" := "+identDataTemp.get(identDataTypeR.get(id.lexemeRank(9)))+"\n");
                                        cuadruplos("=",identDataTemp.get(identDataTypeR.get(id.lexemeRank(9))),"",id.lexemeRank(9));
                                        CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(identDataTypeR.get(id.lexemeRank(9)))+"\n"
                                                       + "    MOV "+id.lexemeRank(9)+",AX\n");
                                    }else{
                                        if(identDataType.get(id.lexemeRank(2)).equals("Numero_Decimal")){
                                            double num1 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(2)));
                                            double num2 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(4)));
                                            double res = num1 / num2;
                                            if(identDataTemp.containsKey(Double.toString(res))){
                                                Tripletas.append("T"+temporal+" := "+identDataTemp.get(Double.toString(res))+" / "+id.lexemeRank(6)+"\n");
                                                cuadruplos("/",identDataTemp.get(Double.toString(res)),id.lexemeRank(6),"T"+temporal);
                                                CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(res)+"\n"
                                                               + "    MOV BX,"+id.lexemeRank(6)+"\n"
                                                               + "    DIV BX\n"
                                                               + "    MOV "+id.lexemeRank(9)+",AX\n");
                                            }else{
                                                Tripletas.append("T"+temporal+" := "+id.lexemeRank(2)+" / "+id.lexemeRank(4)+"\n");
                                                cuadruplos("/",id.lexemeRank(2),id.lexemeRank(4),"T"+temporal);
                                                identDataTemp.put(Double.toString(res),"T"+temporal);
                                                temporal++;
                                                Tripletas.append("T"+temporal+" := T"+(temporal-1)+" / "+id.lexemeRank(6)+"\n");
                                                cuadruplos("/","T"+(temporal-1),id.lexemeRank(6),"T"+temporal);
                                                CodigoObjeto.add("    MOV AX,"+id.lexemeRank(2)+"\n"
                                                               + "    MOV BX,"+id.lexemeRank(4)+"\n"
                                                               + "    DIV BX\n"
                                                               + "    MOV BX,"+id.lexemeRank(6)+"\n"
                                                               + "    DIV BX"
                                                               + "    MOV "+id.lexemeRank(9)+",AX\n");
                                            }
                                        }
                                        if(identDataType.get(id.lexemeRank(2)).equals("Numero_Entero")){
                                            int num1 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(2)));
                                            int num2 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(4)));
                                            int res = num1 / num2;
                                            if(identDataTemp.containsKey(Integer.toString(res))){
                                                Tripletas.append("T"+temporal+" := "+identDataTemp.get(Integer.toString(res))+" / "+id.lexemeRank(6)+"\n");
                                                cuadruplos("/",identDataTemp.get(Integer.toString(res)),id.lexemeRank(6),"T"+temporal);
                                                CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(res)+"\n"
                                                               + "    MOV BX,"+id.lexemeRank(6)+"\n"
                                                               + "    DIV BX\n"
                                                               + "    MOV "+id.lexemeRank(9)+",AX\n");
                                            }else{
                                                Tripletas.append("T"+temporal+" := "+id.lexemeRank(2)+" / "+id.lexemeRank(4)+"\n");
                                                cuadruplos("/",id.lexemeRank(2),id.lexemeRank(4),"T"+temporal);
                                                identDataTemp.put(Integer.toString(res),"T"+temporal);
                                                temporal++;
                                                Tripletas.append("T"+temporal+" := T"+(temporal-1)+" / "+id.lexemeRank(6)+"\n");
                                                cuadruplos("/","T"+(temporal-1),id.lexemeRank(6),"T"+temporal);
                                                CodigoObjeto.add("    MOV AX,"+id.lexemeRank(2)+"\n"
                                                               + "    MOV BX,"+id.lexemeRank(4)+"\n"
                                                               + "    DIV BX\n"
                                                               + "    MOV BX,"+id.lexemeRank(6)+"\n"
                                                               + "    DIV BX"
                                                               + "    MOV "+id.lexemeRank(9)+",AX\n");
                                            }
                                        }
                                        identDataTemp.put(identDataTypeR.get(id.lexemeRank(9)),"T"+temporal);
                                        Tripletas.append(id.lexemeRank(9)+" := T"+temporal+"\n");
                                        cuadruplos("=","T"+temporal,"",id.lexemeRank(9));
                                    }
                                    PanelSalida.append("-->   Se ha utlizado la función de Dividir, que tiene como parametros a:      " + id.lexemeRank(2) + " = " + identDataTypeV2.get(id.lexemeRank(2)) + " , " + id.lexemeRank(4) + " = " + identDataTypeV2.get(id.lexemeRank(4))  + " , " + id.lexemeRank(6) + " = " + identDataTypeV2.get(id.lexemeRank(6)) + "\nDonde el resultado de la operación es: " + identDataTypeR.get(id.lexemeRank(9)) + " , y se guardó en: " + id.lexemeRank(9) + ".........\n");
                                    break;
                                }
                            }
                        }
                        for(Production id: identProdF13){
                            if(sentence.startsWith(id.lexemeRank(0))){
                                if(id.lexemeRank(0).equals("Sumar") && sentence.contains(id.lexemeRank(2)) && sentence.contains(id.lexemeRank(4)) && sentence.contains(id.lexemeRank(6)) && sentence.contains(id.lexemeRank(8)) && sentence.contains(id.lexemeRank(11))){
                                    temporal++;
                                    if(identDataTemp.containsKey(identDataTypeR.get(id.lexemeRank(11)))){
                                        Tripletas.append(id.lexemeRank(11)+" := "+identDataTemp.get(identDataTypeR.get(id.lexemeRank(11)))+"\n");
                                        cuadruplos("=",identDataTemp.get(identDataTypeR.get(id.lexemeRank(11))),"",id.lexemeRank(11));
                                        CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(identDataTypeR.get(id.lexemeRank(11)))+"\n"
                                                       + "    MOV "+id.lexemeRank(11)+",AX\n");
                                    }else{
                                        if(identDataType.get(id.lexemeRank(2)).equals("Numero_Decimal")){
                                            double num1 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(2)));
                                            double num2 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(4)));
                                            double res = num1 + num2;
                                            if(identDataTemp.containsKey(Double.toString(res))){
                                                num1 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(2)));
                                                num2 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(4)));
                                                double num3 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(6)));
                                                res = num1 + num2 + num3;
                                                if(identDataTemp.containsKey(Double.toString(res))){
                                                    Tripletas.append("T"+temporal+" := "+identDataTemp.get(Double.toString(res))+" + "+id.lexemeRank(8)+"\n");
                                                    cuadruplos("+",identDataTemp.get(Double.toString(res)),id.lexemeRank(8),"T"+temporal);
                                                    CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(res)+"\n"
                                                                   + "    MOV BX,"+id.lexemeRank(8)+"\n"
                                                                   + "    ADD AX,BX\n"
                                                                   + "    MOV "+id.lexemeRank(11)+",AX\n");
                                                }else{
                                                    num1 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(2)));
                                                    num2 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(4)));
                                                    num3 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(6)));
                                                    res = num1 + num2;
                                                    Tripletas.append("T"+temporal+" := "+identDataTemp.get(Double.toString(res))+" + "+id.lexemeRank(6)+"\n");
                                                    cuadruplos("+",identDataTemp.get(Double.toString(res)),id.lexemeRank(6),"T"+temporal);
                                                    res = num1+num2+num3;
                                                    identDataTemp.put(identDataTypeR.get(Double.toString(res)),"T"+temporal);
                                                    temporal++;
                                                    Tripletas.append("T"+temporal+" := T"+(temporal-1)+" + "+id.lexemeRank(8)+"\n");
                                                    cuadruplos("+","T"+(temporal-1),id.lexemeRank(8),"T"+temporal);
                                                    CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(res)+"\n"
                                                                   + "    MOV BX,"+id.lexemeRank(6)+"\n"
                                                                   + "    ADD AX,BX\n"
                                                                   + "    MOV BX,"+id.lexemeRank(8)+"\n"
                                                                   + "    ADD AX,BX\n"
                                                                   + "    MOV "+id.lexemeRank(11)+",AX\n");
                                                }
                                            }else{
                                                Tripletas.append("T"+temporal+" := "+id.lexemeRank(2)+" + "+id.lexemeRank(4)+"\n");
                                                cuadruplos("+",id.lexemeRank(2),id.lexemeRank(4),"T"+temporal);
                                                num1 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(2)));
                                                num2 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(4)));
                                                res = num1 + num2;
                                                identDataTemp.put(Double.toString(res),"T"+temporal);
                                                temporal++;
                                                Tripletas.append("T"+temporal+" := T"+(temporal-1)+" + "+id.lexemeRank(6)+"\n");
                                                cuadruplos("+","T"+(temporal-1),id.lexemeRank(6),"T"+temporal);
                                                temporal++;
                                                Tripletas.append("T"+temporal+" := T"+(temporal-1)+" + "+id.lexemeRank(8)+"\n");
                                                cuadruplos("+","T"+(temporal-1),id.lexemeRank(8),"T"+temporal);
                                                CodigoObjeto.add("    MOV AX,"+id.lexemeRank(2)+"\n"
                                                               + "    MOV BX,"+id.lexemeRank(4)+"\n"
                                                               + "    ADD AX,BX\n"        
                                                               + "    MOV BX,"+id.lexemeRank(6)+"\n"
                                                               + "    ADD AX,BX\n"
                                                               + "    MOV BX,"+id.lexemeRank(8)+"\n"
                                                               + "    ADD AX,BX\n"
                                                               + "    MOV "+id.lexemeRank(11)+",AX\n");
                                            }
                                        }
                                        if(identDataType.get(id.lexemeRank(2)).equals("Numero_Entero")){
                                            int num1 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(2)));
                                            int num2 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(4)));
                                            int res = num1 + num2;
                                            if(identDataTemp.containsKey(Integer.toString(res))){
                                                num1 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(2)));
                                                num2 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(4)));
                                                int num3 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(6)));
                                                res = num1 + num2 + num3;
                                                if(identDataTemp.containsKey(Integer.toString(res))){
                                                    Tripletas.append("T"+temporal+" := "+identDataTemp.get(Integer.toString(res))+" + "+id.lexemeRank(8)+"\n");
                                                    cuadruplos("+",identDataTemp.get(Integer.toString(res)),id.lexemeRank(8),"T"+temporal);
                                                    CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(res)+"\n"
                                                                   + "    MOV BX,"+id.lexemeRank(8)+"\n"
                                                                   + "    ADD AX,BX\n"
                                                                   + "    MOV "+id.lexemeRank(11)+",AX\n");
                                                }else{
                                                    num1 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(2)));
                                                    num2 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(4)));
                                                    num3 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(6)));
                                                    res = num1 + num2;
                                                    Tripletas.append("T"+temporal+" := "+identDataTemp.get(Integer.toString(res))+" + "+id.lexemeRank(6)+"\n");
                                                    cuadruplos("+",identDataTemp.get(Integer.toString(res)),id.lexemeRank(6),"T"+temporal);
                                                    res = num1+num2+num3;
                                                    identDataTemp.put(identDataTypeR.get(Integer.toString(res)),"T"+temporal);
                                                    temporal++;
                                                    Tripletas.append("T"+temporal+" := T"+(temporal-1)+" + "+id.lexemeRank(8)+"\n");
                                                    cuadruplos("+","T"+(temporal-1),id.lexemeRank(8),"T"+temporal);
                                                    CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(res)+"\n"
                                                                   + "    MOV BX,"+id.lexemeRank(6)+"\n"
                                                                   + "    ADD AX,BX\n"
                                                                   + "    MOV BX,"+id.lexemeRank(8)+"\n"
                                                                   + "    ADD AX,BX\n"
                                                                   + "    MOV "+id.lexemeRank(11)+",AX\n");
                                                }
                                            }else{
                                                Tripletas.append("T"+temporal+" := "+id.lexemeRank(2)+" + "+id.lexemeRank(4)+"\n");
                                                cuadruplos("+",id.lexemeRank(2),id.lexemeRank(4),"T"+temporal);
                                                num1 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(2)));
                                                num2 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(4)));
                                                res = num1 + num2;
                                                identDataTemp.put(Integer.toString(res),"T"+temporal);
                                                temporal++;
                                                Tripletas.append("T"+temporal+" := T"+(temporal-1)+" + "+id.lexemeRank(6)+"\n");
                                                cuadruplos("+","T"+(temporal-1),id.lexemeRank(6),"T"+temporal);
                                                temporal++;
                                                Tripletas.append("T"+temporal+" := T"+(temporal-1)+" + "+id.lexemeRank(8)+"\n");
                                                cuadruplos("+","T"+(temporal-1),id.lexemeRank(8),"T"+temporal);
                                                CodigoObjeto.add("    MOV AX,"+id.lexemeRank(2)+"\n"
                                                               + "    MOV BX,"+id.lexemeRank(4)+"\n"
                                                               + "    ADD AX,BX\n"        
                                                               + "    MOV BX,"+id.lexemeRank(6)+"\n"
                                                               + "    ADD AX,BX\n"
                                                               + "    MOV BX,"+id.lexemeRank(8)+"\n"
                                                               + "    ADD AX,BX\n"
                                                               + "    MOV "+id.lexemeRank(11)+",AX\n");
                                            }
                                        }
                                        identDataTemp.put(identDataTypeR.get(id.lexemeRank(11)),"T"+temporal);
                                        Tripletas.append(id.lexemeRank(11)+" := T"+temporal+"\n");
                                        cuadruplos("=","T"+temporal,"",id.lexemeRank(11));
                                    }
                                    PanelSalida.append("-->   Se ha utlizado la función de Sumar, que tiene como parametros a:        " + id.lexemeRank(2) + " = " + identDataTypeV2.get(id.lexemeRank(2)) + " , " + id.lexemeRank(4) + " = " + identDataTypeV2.get(id.lexemeRank(4)) + " , " + id.lexemeRank(6) + " = " + identDataTypeV2.get(id.lexemeRank(6)) + " , " + id.lexemeRank(8) + " = " + identDataTypeV2.get(id.lexemeRank(8)) + "\nDonde el resultado de la operación es: " + identDataTypeR.get(id.lexemeRank(11)) + " , y se guardó en: " + id.lexemeRank(11) + ".........\n");
                                    break;
                                }else if(id.lexemeRank(0).equals("Restar") && sentence.contains(id.lexemeRank(2)) && sentence.contains(id.lexemeRank(4)) && sentence.contains(id.lexemeRank(6)) && sentence.contains(id.lexemeRank(8)) && sentence.contains(id.lexemeRank(11))){
                                    temporal++;
                                    if(identDataTemp.containsKey(identDataTypeR.get(id.lexemeRank(11)))){
                                        Tripletas.append(id.lexemeRank(11)+" := "+identDataTemp.get(identDataTypeR.get(id.lexemeRank(11)))+"\n");
                                        cuadruplos("=",identDataTemp.get(identDataTypeR.get(id.lexemeRank(11))),"",id.lexemeRank(11));
                                        CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(identDataTypeR.get(id.lexemeRank(11)))+"\n"
                                                       + "    MOV "+id.lexemeRank(11)+",AX\n");
                                    }else{
                                        if(identDataType.get(id.lexemeRank(2)).equals("Numero_Decimal")){
                                            double num1 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(2)));
                                            double num2 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(4)));
                                            double res = num1 - num2;
                                            if(identDataTemp.containsKey(Double.toString(res))){
                                                num1 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(2)));
                                                num2 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(4)));
                                                double num3 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(6)));
                                                res = num1 - num2 - num3;
                                                if(identDataTemp.containsKey(Double.toString(res))){
                                                    Tripletas.append("T"+temporal+" := "+identDataTemp.get(Double.toString(res))+" - "+id.lexemeRank(8)+"\n");
                                                    cuadruplos("-",identDataTemp.get(Double.toString(res)),id.lexemeRank(8),"T"+temporal);
                                                    CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(res)+"\n"
                                                                   + "    MOV BX,"+id.lexemeRank(8)+"\n"
                                                                   + "    SUB AX,BX\n"
                                                                   + "    MOV "+id.lexemeRank(11)+",AX\n");
                                                }else{
                                                    num1 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(2)));
                                                    num2 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(4)));
                                                    num3 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(6)));
                                                    res = num1 - num2;
                                                    Tripletas.append("T"+temporal+" := "+identDataTemp.get(Double.toString(res))+" - "+id.lexemeRank(6)+"\n");
                                                    cuadruplos("-",identDataTemp.get(Double.toString(res)),id.lexemeRank(6),"T"+temporal);
                                                    res = num1 - num2 - num3;
                                                    identDataTemp.put(identDataTypeR.get(Double.toString(res)),"T"+temporal);
                                                    temporal++;
                                                    Tripletas.append("T"+temporal+" := T"+(temporal-1)+" - "+id.lexemeRank(8)+"\n");
                                                    cuadruplos("-","T"+(temporal-1),id.lexemeRank(8),"T"+temporal);
                                                    CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(res)+"\n"
                                                                   + "    MOV BX,"+id.lexemeRank(6)+"\n"
                                                                   + "    SUB AX,BX\n"
                                                                   + "    MOV BX,"+id.lexemeRank(8)+"\n"
                                                                   + "    SUB AX,BX\n"
                                                                   + "    MOV "+id.lexemeRank(11)+",AX\n");
                                                }
                                            }else{
                                                Tripletas.append("T"+temporal+" := "+id.lexemeRank(2)+" - "+id.lexemeRank(4)+"\n");
                                                cuadruplos("-",id.lexemeRank(2),id.lexemeRank(4),"T"+temporal);
                                                num1 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(2)));
                                                num2 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(4)));
                                                res = num1 - num2;
                                                identDataTemp.put(Double.toString(res),"T"+temporal);
                                                temporal++;
                                                Tripletas.append("T"+temporal+" := T"+(temporal-1)+" - "+id.lexemeRank(6)+"\n");
                                                cuadruplos("-","T"+(temporal-1),id.lexemeRank(6),"T"+temporal);
                                                temporal++;
                                                Tripletas.append("T"+temporal+" := T"+(temporal-1)+" - "+id.lexemeRank(8)+"\n");
                                                cuadruplos("-","T"+(temporal-1),id.lexemeRank(8),"T"+temporal);
                                                CodigoObjeto.add("    MOV AX,"+id.lexemeRank(2)+"\n"
                                                               + "    MOV BX,"+id.lexemeRank(4)+"\n"
                                                               + "    SUB AX,BX\n"        
                                                               + "    MOV BX,"+id.lexemeRank(6)+"\n"
                                                               + "    SUB AX,BX\n"
                                                               + "    MOV BX,"+id.lexemeRank(8)+"\n"
                                                               + "    SUB AX,BX\n"
                                                               + "    MOV "+id.lexemeRank(11)+",AX\n");
                                            }
                                        }
                                        if(identDataType.get(id.lexemeRank(2)).equals("Numero_Entero")){
                                            int num1 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(2)));
                                            int num2 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(4)));
                                            int res = num1 - num2;
                                            if(identDataTemp.containsKey(Integer.toString(res))){
                                                num1 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(2)));
                                                num2 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(4)));
                                                int num3 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(6)));
                                                res = num1 - num2 - num3;
                                                if(identDataTemp.containsKey(Integer.toString(res))){
                                                    Tripletas.append("T"+temporal+" := "+identDataTemp.get(Integer.toString(res))+" - "+id.lexemeRank(8)+"\n");
                                                    cuadruplos("-",identDataTemp.get(Integer.toString(res)),id.lexemeRank(8),"T"+temporal);
                                                    CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(res)+"\n"
                                                                   + "    MOV BX,"+id.lexemeRank(8)+"\n"
                                                                   + "    SUB AX,BX\n"
                                                                   + "    MOV "+id.lexemeRank(11)+",AX\n");
                                                }else{
                                                    num1 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(2)));
                                                    num2 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(4)));
                                                    num3 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(6)));
                                                    res = num1 - num2;
                                                    Tripletas.append("T"+temporal+" := "+identDataTemp.get(Integer.toString(res))+" - "+id.lexemeRank(6)+"\n");
                                                    cuadruplos("-",identDataTemp.get(Integer.toString(res)),id.lexemeRank(6),"T"+temporal);
                                                    res = num1-num2-num3;
                                                    identDataTemp.put(identDataTypeR.get(Integer.toString(res)),"T"+temporal);
                                                    temporal++;
                                                    Tripletas.append("T"+temporal+" := T"+(temporal-1)+" - "+id.lexemeRank(8)+"\n");
                                                    cuadruplos("-","T"+(temporal-1),id.lexemeRank(8),"T"+temporal);
                                                    CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(res)+"\n"
                                                                   + "    MOV BX,"+id.lexemeRank(6)+"\n"
                                                                   + "    SUB AX,BX\n"
                                                                   + "    MOV BX,"+id.lexemeRank(8)+"\n"
                                                                   + "    SUB AX,BX\n"
                                                                   + "    MOV "+id.lexemeRank(11)+",AX\n");
                                                }
                                            }else{
                                                Tripletas.append("T"+temporal+" := "+id.lexemeRank(2)+" - "+id.lexemeRank(4)+"\n");
                                                cuadruplos("-",id.lexemeRank(2),id.lexemeRank(4),"T"+temporal);
                                                num1 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(2)));
                                                num2 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(4)));
                                                res = num1 - num2;
                                                identDataTemp.put(Integer.toString(res),"T"+temporal);
                                                temporal++;
                                                Tripletas.append("T"+temporal+" := T"+(temporal-1)+" - "+id.lexemeRank(6)+"\n");
                                                cuadruplos("-","T"+(temporal-1),id.lexemeRank(6),"T"+temporal);
                                                temporal++;
                                                Tripletas.append("T"+temporal+" := T"+(temporal-1)+" - "+id.lexemeRank(8)+"\n");
                                                cuadruplos("-","T"+(temporal-1),id.lexemeRank(8),"T"+temporal);
                                                CodigoObjeto.add("    MOV AX,"+id.lexemeRank(2)+"\n"
                                                               + "    MOV BX,"+id.lexemeRank(4)+"\n"
                                                               + "    SUB AX,BX\n"        
                                                               + "    MOV BX,"+id.lexemeRank(6)+"\n"
                                                               + "    SUB AX,BX\n"
                                                               + "    MOV BX,"+id.lexemeRank(8)+"\n"
                                                               + "    SUB AX,BX\n"
                                                               + "    MOV "+id.lexemeRank(11)+",AX\n");
                                            }
                                        }
                                        identDataTemp.put(identDataTypeR.get(id.lexemeRank(11)),"T"+temporal);
                                        Tripletas.append(id.lexemeRank(11)+" := T"+temporal+"\n");
                                        cuadruplos("=","T"+temporal,"",id.lexemeRank(11));
                                    }
                                    PanelSalida.append("-->   Se ha utlizado la función de Restar, que tiene como parametros a:       " + id.lexemeRank(2) + " = " + identDataTypeV2.get(id.lexemeRank(2)) + " , " + id.lexemeRank(4) + " = " + identDataTypeV2.get(id.lexemeRank(4)) + " , " + id.lexemeRank(6) + " = " + identDataTypeV2.get(id.lexemeRank(6)) + " , " + id.lexemeRank(8) + " = " + identDataTypeV2.get(id.lexemeRank(8)) + "\nDonde el resultado de la operación es: " + identDataTypeR.get(id.lexemeRank(11)) + " , y se guardó en: " + id.lexemeRank(11) + ".........\n");
                                    break;
                                }else if(id.lexemeRank(0).equals("Multiplicar") && sentence.contains(id.lexemeRank(2)) && sentence.contains(id.lexemeRank(4)) && sentence.contains(id.lexemeRank(6)) && sentence.contains(id.lexemeRank(8)) && sentence.contains(id.lexemeRank(11))){
                                    temporal++;
                                    if(identDataTemp.containsKey(identDataTypeR.get(id.lexemeRank(11)))){
                                        Tripletas.append(id.lexemeRank(11)+" := "+identDataTemp.get(identDataTypeR.get(id.lexemeRank(11)))+"\n");
                                        cuadruplos("=",identDataTemp.get(identDataTypeR.get(id.lexemeRank(11))),"",id.lexemeRank(11));
                                        CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(identDataTypeR.get(id.lexemeRank(11)))+"\n"
                                                       + "    MOV "+id.lexemeRank(11)+",AX\n");
                                    }else{
                                        if(identDataType.get(id.lexemeRank(2)).equals("Numero_Decimal")){
                                            double num1 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(2)));
                                            double num2 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(4)));
                                            double res = num1 * num2;
                                            if(identDataTemp.containsKey(Double.toString(res))){
                                                num1 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(2)));
                                                num2 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(4)));
                                                double num3 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(6)));
                                                res = num1 * num2 * num3;
                                                if(identDataTemp.containsKey(Double.toString(res))){
                                                    Tripletas.append("T"+temporal+" := "+identDataTemp.get(Double.toString(res))+" * "+id.lexemeRank(8)+"\n");
                                                    cuadruplos("*",identDataTemp.get(Double.toString(res)),id.lexemeRank(8),"T"+temporal);
                                                    CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(res)+"\n"
                                                                   + "    MOV BX,"+id.lexemeRank(8)+"\n"
                                                                   + "    MUL BX\n"
                                                                   + "    MOV "+id.lexemeRank(11)+",AX\n");
                                                }else{
                                                    num1 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(2)));
                                                    num2 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(4)));
                                                    num3 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(6)));
                                                    res = num1 * num2;
                                                    Tripletas.append("T"+temporal+" := "+identDataTemp.get(Double.toString(res))+" * "+id.lexemeRank(6)+"\n");
                                                    cuadruplos("*",identDataTemp.get(Double.toString(res)),id.lexemeRank(6),"T"+temporal);
                                                    res = num1*num2*num3;
                                                    identDataTemp.put(identDataTypeR.get(Double.toString(res)),"T"+temporal);
                                                    temporal++;
                                                    Tripletas.append("T"+temporal+" := T"+(temporal-1)+" * "+id.lexemeRank(8)+"\n");
                                                    cuadruplos("*","T"+(temporal-1),id.lexemeRank(8),"T"+temporal);
                                                    CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(res)+"\n"
                                                                   + "    MOV BX,"+id.lexemeRank(6)+"\n"
                                                                   + "    MUL BX\n"
                                                                   + "    MOV BX,"+id.lexemeRank(8)+"\n"
                                                                   + "    MUL BX\n"
                                                                   + "    MOV "+id.lexemeRank(11)+",AX\n");
                                                }
                                            }else{
                                                Tripletas.append("T"+temporal+" := "+id.lexemeRank(2)+" * "+id.lexemeRank(4)+"\n");
                                                cuadruplos("*",id.lexemeRank(2),id.lexemeRank(4),"T"+temporal);
                                                num1 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(2)));
                                                num2 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(4)));
                                                res = num1 * num2;
                                                identDataTemp.put(Double.toString(res),"T"+temporal);
                                                temporal++;
                                                Tripletas.append("T"+temporal+" := T"+(temporal-1)+" * "+id.lexemeRank(6)+"\n");
                                                cuadruplos("*","T"+(temporal-1),id.lexemeRank(6),"T"+temporal);
                                                temporal++;
                                                Tripletas.append("T"+temporal+" := T"+(temporal-1)+" * "+id.lexemeRank(8)+"\n");
                                                cuadruplos("*","T"+(temporal-1),id.lexemeRank(8),"T"+temporal);
                                                CodigoObjeto.add("    MOV AX,"+id.lexemeRank(2)+"\n"
                                                               + "    MOV BX,"+id.lexemeRank(4)+"\n"
                                                               + "    MUL BX\n"        
                                                               + "    MOV BX,"+id.lexemeRank(6)+"\n"
                                                               + "    MUL BX\n"
                                                               + "    MOV BX,"+id.lexemeRank(8)+"\n"
                                                               + "    MUL BX\n"
                                                               + "    MOV "+id.lexemeRank(11)+",AX\n");
                                            }
                                        }
                                        if(identDataType.get(id.lexemeRank(2)).equals("Numero_Entero")){
                                            int num1 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(2)));
                                            int num2 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(4)));
                                            int res = num1 * num2;
                                            if(identDataTemp.containsKey(Integer.toString(res))){
                                                num1 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(2)));
                                                num2 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(4)));
                                                int num3 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(6)));
                                                res = num1 * num2 * num3;
                                                if(identDataTemp.containsKey(Integer.toString(res))){
                                                    Tripletas.append("T"+temporal+" := "+identDataTemp.get(Integer.toString(res))+" * "+id.lexemeRank(8)+"\n");
                                                    cuadruplos("*",identDataTemp.get(Integer.toString(res)),id.lexemeRank(8),"T"+temporal);
                                                    CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(res)+"\n"
                                                                   + "    MOV BX,"+id.lexemeRank(8)+"\n"
                                                                   + "    MUL BX\n"
                                                                   + "    MOV "+id.lexemeRank(11)+",AX\n");
                                                }else{
                                                    num1 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(2)));
                                                    num2 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(4)));
                                                    num3 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(6)));
                                                    res = num1 * num2;
                                                    Tripletas.append("T"+temporal+" := "+identDataTemp.get(Integer.toString(res))+" * "+id.lexemeRank(6)+"\n");
                                                    cuadruplos("*",identDataTemp.get(Integer.toString(res)),id.lexemeRank(6),"T"+temporal);
                                                    res = num1*num2*num3;
                                                    identDataTemp.put(identDataTypeR.get(Integer.toString(res)),"T"+temporal);
                                                    temporal++;
                                                    Tripletas.append("T"+temporal+" := T"+(temporal-1)+" * "+id.lexemeRank(8)+"\n");
                                                    cuadruplos("*","T"+(temporal-1),id.lexemeRank(8),"T"+temporal);
                                                    CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(res)+"\n"
                                                                   + "    MOV BX,"+id.lexemeRank(6)+"\n"
                                                                   + "    MUL BX\n"
                                                                   + "    MOV BX,"+id.lexemeRank(8)+"\n"
                                                                   + "    MUL BX\n"
                                                                   + "    MOV "+id.lexemeRank(11)+",AX\n");
                                                }
                                            }else{
                                                Tripletas.append("T"+temporal+" := "+id.lexemeRank(2)+" * "+id.lexemeRank(4)+"\n");
                                                cuadruplos("*",id.lexemeRank(2),id.lexemeRank(4),"T"+temporal);
                                                num1 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(2)));
                                                num2 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(4)));
                                                res = num1 * num2;
                                                identDataTemp.put(Integer.toString(res),"T"+temporal);
                                                temporal++;
                                                Tripletas.append("T"+temporal+" := T"+(temporal-1)+" * "+id.lexemeRank(6)+"\n");
                                                cuadruplos("*","T"+(temporal-1),id.lexemeRank(6),"T"+temporal);
                                                temporal++;
                                                Tripletas.append("T"+temporal+" := T"+(temporal-1)+" * "+id.lexemeRank(8)+"\n");
                                                cuadruplos("*","T"+(temporal-1),id.lexemeRank(8),"T"+temporal);
                                                CodigoObjeto.add("    MOV AX,"+id.lexemeRank(2)+"\n"
                                                               + "    MOV BX,"+id.lexemeRank(4)+"\n"
                                                               + "    MUL BX\n"        
                                                               + "    MOV BX,"+id.lexemeRank(6)+"\n"
                                                               + "    MUL BX\n"
                                                               + "    MOV BX,"+id.lexemeRank(8)+"\n"
                                                               + "    MUL BX\n"
                                                               + "    MOV "+id.lexemeRank(11)+",AX\n");
                                            }
                                        }
                                        identDataTemp.put(identDataTypeR.get(id.lexemeRank(11)),"T"+temporal);
                                        Tripletas.append(id.lexemeRank(11)+" := T"+temporal+"\n");
                                        cuadruplos("=","T"+temporal,"",id.lexemeRank(11));
                                    }
                                    PanelSalida.append("-->   Se ha utlizado la función de Multiplicar, que tiene como parametros a:  " + id.lexemeRank(2) + " = " + identDataTypeV2.get(id.lexemeRank(2)) + " , " + id.lexemeRank(4) + " = " + identDataTypeV2.get(id.lexemeRank(4)) + " , " + id.lexemeRank(6) + " = " + identDataTypeV2.get(id.lexemeRank(6)) + " , " + id.lexemeRank(8) + " = " + identDataTypeV2.get(id.lexemeRank(8)) + "\nDonde el resultado de la operación es: " + identDataTypeR.get(id.lexemeRank(11)) + " , y se guardó en: " + id.lexemeRank(11) + ".........\n");
                                    break;
                                }else if(id.lexemeRank(0).equals("Dividir") && sentence.contains(id.lexemeRank(2)) && sentence.contains(id.lexemeRank(4)) && sentence.contains(id.lexemeRank(6)) && sentence.contains(id.lexemeRank(8)) && sentence.contains(id.lexemeRank(11))){
                                    temporal++;
                                    if(identDataTemp.containsKey(identDataTypeR.get(id.lexemeRank(11)))){
                                        Tripletas.append(id.lexemeRank(11)+" := "+identDataTemp.get(identDataTypeR.get(id.lexemeRank(11)))+"\n");
                                        cuadruplos("=",identDataTemp.get(identDataTypeR.get(id.lexemeRank(11))),"",id.lexemeRank(11));
                                        CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(identDataTypeR.get(id.lexemeRank(11)))+"\n"
                                                       + "    MOV "+id.lexemeRank(11)+",AX\n");
                                    }else{
                                        if(identDataType.get(id.lexemeRank(2)).equals("Numero_Decimal")){
                                            double num1 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(2)));
                                            double num2 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(4)));
                                            double res = num1 / num2;
                                            if(identDataTemp.containsKey(Double.toString(res))){
                                                num1 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(2)));
                                                num2 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(4)));
                                                double num3 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(6)));
                                                res = num1 / num2 / num3;
                                                if(identDataTemp.containsKey(Double.toString(res))){
                                                    Tripletas.append("T"+temporal+" := "+identDataTemp.get(Double.toString(res))+" / "+id.lexemeRank(8)+"\n");
                                                    cuadruplos("/",identDataTemp.get(Double.toString(res)),id.lexemeRank(8),"T"+temporal);
                                                    CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(res)+"\n"
                                                                   + "    MOV BX,"+id.lexemeRank(8)+"\n"
                                                                   + "    DIV BX\n"
                                                                   + "    MOV "+id.lexemeRank(11)+",AX\n");
                                                }else{
                                                    num1 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(2)));
                                                    num2 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(4)));
                                                    num3 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(6)));
                                                    res = num1 / num2;
                                                    Tripletas.append("T"+temporal+" := "+identDataTemp.get(Double.toString(res))+" / "+id.lexemeRank(6)+"\n");
                                                    cuadruplos("/",identDataTemp.get(Double.toString(res)),id.lexemeRank(6),"T"+temporal);
                                                    res = num1/num2/num3;
                                                    identDataTemp.put(identDataTypeR.get(Double.toString(res)),"T"+temporal);
                                                    temporal++;
                                                    Tripletas.append("T"+temporal+" := T"+(temporal-1)+" / "+id.lexemeRank(8)+"\n");
                                                    cuadruplos("/","T"+(temporal-1),id.lexemeRank(8),"T"+temporal);
                                                    CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(res)+"\n"
                                                                   + "    MOV BX,"+id.lexemeRank(6)+"\n"
                                                                   + "    DIV BX\n"
                                                                   + "    MOV BX,"+id.lexemeRank(8)+"\n"
                                                                   + "    DIV BX\n"
                                                                   + "    MOV "+id.lexemeRank(11)+",AX\n");
                                                }
                                            }else{
                                                Tripletas.append("T"+temporal+" := "+id.lexemeRank(2)+" / "+id.lexemeRank(4)+"\n");
                                                cuadruplos("/",id.lexemeRank(2),id.lexemeRank(4),"T"+temporal);
                                                num1 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(2)));
                                                num2 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(4)));
                                                res = num1 / num2;
                                                identDataTemp.put(Double.toString(res),"T"+temporal);
                                                temporal++;
                                                Tripletas.append("T"+temporal+" := T"+(temporal-1)+" / "+id.lexemeRank(6)+"\n");
                                                cuadruplos("/","T"+(temporal-1),id.lexemeRank(6),"T"+temporal);
                                                temporal++;
                                                Tripletas.append("T"+temporal+" := T"+(temporal-1)+" / "+id.lexemeRank(8)+"\n");
                                                cuadruplos("/","T"+(temporal-1),id.lexemeRank(8),"T"+temporal);
                                                CodigoObjeto.add("    MOV AX,"+id.lexemeRank(2)+"\n"
                                                               + "    MOV BX,"+id.lexemeRank(4)+"\n"
                                                               + "    DIV BX\n"        
                                                               + "    MOV BX,"+id.lexemeRank(6)+"\n"
                                                               + "    DIV BX\n"
                                                               + "    MOV BX,"+id.lexemeRank(8)+"\n"
                                                               + "    DIV BX\n"
                                                               + "    MOV "+id.lexemeRank(11)+",AX\n");
                                            }
                                        }
                                        if(identDataType.get(id.lexemeRank(2)).equals("Numero_Entero")){
                                            int num1 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(2)));
                                            int num2 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(4)));
                                            int res = num1 / num2;
                                            if(identDataTemp.containsKey(Integer.toString(res))){
                                                num1 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(2)));
                                                num2 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(4)));
                                                int num3 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(6)));
                                                res = num1 / num2 / num3;
                                                if(identDataTemp.containsKey(Integer.toString(res))){
                                                    Tripletas.append("T"+temporal+" := "+identDataTemp.get(Integer.toString(res))+" / "+id.lexemeRank(8)+"\n");
                                                    cuadruplos("/",identDataTemp.get(Integer.toString(res)),id.lexemeRank(8),"T"+temporal);
                                                    CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(res)+"\n"
                                                                   + "    MOV BX,"+id.lexemeRank(8)+"\n"
                                                                   + "    DIV BX\n"
                                                                   + "    MOV "+id.lexemeRank(11)+",AX\n");
                                                }else{
                                                    num1 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(2)));
                                                    num2 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(4)));
                                                    num3 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(6)));
                                                    res = num1 / num2;
                                                    Tripletas.append("T"+temporal+" := "+identDataTemp.get(Integer.toString(res))+" / "+id.lexemeRank(6)+"\n");
                                                    cuadruplos("/",identDataTemp.get(Integer.toString(res)),id.lexemeRank(6),"T"+temporal);
                                                    res = num1/num2/num3;
                                                    identDataTemp.put(identDataTypeR.get(Integer.toString(res)),"T"+temporal);
                                                    temporal++;
                                                    Tripletas.append("T"+temporal+" := T"+(temporal-1)+" / "+id.lexemeRank(8)+"\n");
                                                    cuadruplos("/","T"+(temporal-1),id.lexemeRank(8),"T"+temporal);
                                                    CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(res)+"\n"
                                                                   + "    MOV BX,"+id.lexemeRank(6)+"\n"
                                                                   + "    DIV BX\n"
                                                                   + "    MOV BX,"+id.lexemeRank(8)+"\n"
                                                                   + "    DIV BX\n"
                                                                   + "    MOV "+id.lexemeRank(11)+",AX\n");
                                                }
                                            }else{
                                                Tripletas.append("T"+temporal+" := "+id.lexemeRank(2)+" / "+id.lexemeRank(4)+"\n");
                                                cuadruplos("/",id.lexemeRank(2),id.lexemeRank(4),"T"+temporal);
                                                num1 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(2)));
                                                num2 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(4)));
                                                res = num1 / num2;
                                                identDataTemp.put(Integer.toString(res),"T"+temporal);
                                                temporal++;
                                                Tripletas.append("T"+temporal+" := T"+(temporal-1)+" / "+id.lexemeRank(6)+"\n");
                                                cuadruplos("/","T"+(temporal-1),id.lexemeRank(6),"T"+temporal);
                                                temporal++;
                                                Tripletas.append("T"+temporal+" := T"+(temporal-1)+" / "+id.lexemeRank(8)+"\n");
                                                cuadruplos("/","T"+(temporal-1),id.lexemeRank(8),"T"+temporal);
                                                CodigoObjeto.add("    MOV AX,"+id.lexemeRank(2)+"\n"
                                                               + "    MOV BX,"+id.lexemeRank(4)+"\n"
                                                               + "    DIV BX\n"        
                                                               + "    MOV BX,"+id.lexemeRank(6)+"\n"
                                                               + "    DIV BX\n"
                                                               + "    MOV BX,"+id.lexemeRank(8)+"\n"
                                                               + "    DIV BX\n"
                                                               + "    MOV "+id.lexemeRank(11)+",AX\n");
                                            }
                                        }
                                        identDataTemp.put(identDataTypeR.get(id.lexemeRank(11)),"T"+temporal);
                                        Tripletas.append(id.lexemeRank(11)+" := T"+temporal+"\n");
                                        cuadruplos("=","T"+temporal,"",id.lexemeRank(11));
                                    }
                                    PanelSalida.append("-->   Se ha utlizado la función de Dividir, que tiene como parametros a:      " + id.lexemeRank(2) + " = " + identDataTypeV2.get(id.lexemeRank(2)) + " , " + id.lexemeRank(4) + " = " + identDataTypeV2.get(id.lexemeRank(4)) + " , " + id.lexemeRank(6) + " = " + identDataTypeV2.get(id.lexemeRank(6)) + " , " + id.lexemeRank(8) + " = " + identDataTypeV2.get(id.lexemeRank(8)) + "\nDonde el resultado de la operación es: " + identDataTypeR.get(id.lexemeRank(11)) + " , y se guardó en: " + id.lexemeRank(11) + ".........\n");
                                    break;
                                }
                            }
                        }
                        for(Production id: identProdF14){
                            if(sentence.startsWith(id.lexemeRank(0))){
                                if(id.lexemeRank(0).equals("Sumar") && sentence.contains(id.lexemeRank(2)) && sentence.contains(id.lexemeRank(4)) && sentence.contains(id.lexemeRank(6)) && sentence.contains(id.lexemeRank(8)) && sentence.contains(id.lexemeRank(10)) && sentence.contains(id.lexemeRank(13))){
                                    temporal++;
                                    if(identDataTemp.containsKey(identDataTypeR.get(id.lexemeRank(13)))){
                                        Tripletas.append(id.lexemeRank(13)+" := "+identDataTemp.get(identDataTypeR.get(id.lexemeRank(13)))+"\n");
                                        cuadruplos("=",identDataTemp.get(identDataTypeR.get(id.lexemeRank(13))),"",id.lexemeRank(13));
                                        CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(identDataTypeR.get(id.lexemeRank(13)))+"\n"
                                                       + "    MOV "+id.lexemeRank(13)+",AX\n");
                                    }else{
                                        if(identDataType.get(id.lexemeRank(2)).equals("Numero_Decimal")){
                                            double num1 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(2)));
                                            double num2 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(4)));
                                            double res = num1 + num2;
                                            if(identDataTemp.containsKey(Double.toString(res))){
                                                num1 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(2)));
                                                num2 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(4)));
                                                double num3 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(6)));
                                                res = num1 + num2 + num3;
                                                if(identDataTemp.containsKey(Double.toString(res))){
                                                    num1 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(2)));
                                                    num2 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(4)));
                                                    num3 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(6)));
                                                    double num4 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(8)));
                                                    res = num1 + num2 + num3 + num4;
                                                    if(identDataTemp.containsKey(Double.toString(res))){
                                                        Tripletas.append("T"+temporal+" := "+identDataTemp.get(Double.toString(res))+" + "+id.lexemeRank(10)+"\n");
                                                        cuadruplos("+",identDataTemp.get(Double.toString(res)),id.lexemeRank(10),"T"+temporal);
                                                        CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(res)+"\n"        
                                                                       + "    MOV BX,"+id.lexemeRank(10)+"\n"
                                                                       + "    ADD AX,BX\n"
                                                                       + "    MOV "+id.lexemeRank(13)+",AX\n");
                                                    }else{
                                                        num1 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(2)));
                                                        num2 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(4)));
                                                        num3 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(6)));
                                                        res = num1 + num2 + num3;
                                                        Tripletas.append("T"+temporal+" := "+identDataTemp.get(Double.toString(res))+" + "+id.lexemeRank(8)+"\n");
                                                        cuadruplos("+",identDataTemp.get(Double.toString(res)),id.lexemeRank(8),"T"+temporal);
                                                        temporal++;
                                                        Tripletas.append("T"+temporal+" := T"+(temporal-1)+" + "+id.lexemeRank(10)+"\n");
                                                        cuadruplos("+","T"+(temporal-1),id.lexemeRank(10),"T"+temporal);
                                                        CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(res)+"\n"        
                                                                       + "    MOV BX,"+id.lexemeRank(8)+"\n"
                                                                       + "    ADD AX,BX\n"
                                                                       + "    MOV BX,"+id.lexemeRank(10)+"\n"
                                                                       + "    ADD AX,BX\n"
                                                                       + "    MOV "+id.lexemeRank(13)+",AX\n");
                                                    }
                                                }else{
                                                    num1 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(2)));
                                                    num2 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(4)));
                                                    num3 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(6)));
                                                    res = num1 + num2;
                                                    Tripletas.append("T"+temporal+" := "+identDataTemp.get(Double.toString(res))+" + "+id.lexemeRank(6)+"\n");
                                                    cuadruplos("+",identDataTemp.get(Double.toString(res)),id.lexemeRank(6),"T"+temporal);
                                                    res = num1+num2+num3;
                                                    identDataTemp.put(identDataTypeR.get(Double.toString(res)),"T"+temporal);
                                                    temporal++;
                                                    Tripletas.append("T"+temporal+" := T"+(temporal-1)+" + "+id.lexemeRank(8)+"\n");
                                                    cuadruplos("+","T"+(temporal-1),id.lexemeRank(8),"T"+temporal);
                                                    temporal++;
                                                    Tripletas.append("T"+temporal+" := T"+(temporal-1)+" + "+id.lexemeRank(10)+"\n");
                                                    cuadruplos("+","T"+(temporal-1),id.lexemeRank(10),"T"+temporal);
                                                    CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(res)+"\n"
                                                                   + "    MOV BX,"+id.lexemeRank(6)+"\n"
                                                                   + "    ADD AX,BX\n"        
                                                                   + "    MOV BX,"+id.lexemeRank(8)+"\n"
                                                                   + "    ADD AX,BX\n"
                                                                   + "    MOV BX,"+id.lexemeRank(10)+"\n"
                                                                   + "    ADD AX,BX\n"
                                                                   + "    MOV "+id.lexemeRank(13)+",AX\n");
                                                }
                                            }else{
                                                Tripletas.append("T"+temporal+" := "+id.lexemeRank(2)+" + "+id.lexemeRank(4)+"\n");
                                                cuadruplos("+",id.lexemeRank(2),id.lexemeRank(4),"T"+temporal);
                                                num1 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(2)));
                                                num2 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(4)));
                                                res = num1 + num2;
                                                identDataTemp.put(Double.toString(res),"T"+temporal);
                                                temporal++;
                                                Tripletas.append("T"+temporal+" := T"+(temporal-1)+" + "+id.lexemeRank(6)+"\n");
                                                cuadruplos("+","T"+(temporal-1),id.lexemeRank(6),"T"+temporal);
                                                temporal++;
                                                Tripletas.append("T"+temporal+" := T"+(temporal-1)+" + "+id.lexemeRank(8)+"\n");
                                                cuadruplos("+","T"+(temporal-1),id.lexemeRank(8),"T"+temporal);
                                                temporal++;
                                                Tripletas.append("T"+temporal+" := T"+(temporal-1)+" + "+id.lexemeRank(10)+"\n");
                                                cuadruplos("+","T"+(temporal-1),id.lexemeRank(10),"T"+temporal);
                                                CodigoObjeto.add("    MOV AX,"+id.lexemeRank(2)+"\n"
                                                               + "    MOV BX,"+id.lexemeRank(4)+"\n"
                                                               + "    ADD AX,BX\n"        
                                                               + "    MOV BX,"+id.lexemeRank(6)+"\n"
                                                               + "    ADD AX,BX\n"
                                                               + "    MOV BX,"+id.lexemeRank(8)+"\n"
                                                               + "    ADD AX,BX\n"
                                                               + "    MOV BX,"+id.lexemeRank(10)+"\n"
                                                               + "    ADD AX,BX\n"
                                                               + "    MOV "+id.lexemeRank(13)+",AX\n");
                                            }
                                        }
                                        if(identDataType.get(id.lexemeRank(2)).equals("Numero_Entero")){
                                            int num1 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(2)));
                                            int num2 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(4)));
                                            int res = num1 + num2;
                                            if(identDataTemp.containsKey(Integer.toString(res))){
                                                num1 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(2)));
                                                num2 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(4)));
                                                int num3 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(6)));
                                                res = num1 + num2 + num3;
                                                if(identDataTemp.containsKey(Integer.toString(res))){
                                                    num1 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(2)));
                                                    num2 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(4)));
                                                    num3 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(6)));
                                                    int num4 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(8)));
                                                    res = num1 + num2 + num3 + num4;
                                                    if(identDataTemp.containsKey(Integer.toString(res))){
                                                        Tripletas.append("T"+temporal+" := "+identDataTemp.get(Integer.toString(res))+" + "+id.lexemeRank(10)+"\n");
                                                        cuadruplos("+",identDataTemp.get(Integer.toString(res)),id.lexemeRank(10),"T"+temporal);
                                                        CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(res)+"\n"        
                                                                       + "    MOV BX,"+id.lexemeRank(10)+"\n"
                                                                       + "    ADD AX,BX\n"
                                                                       + "    MOV "+id.lexemeRank(13)+",AX\n");
                                                    }else{
                                                        num1 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(2)));
                                                        num2 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(4)));
                                                        num3 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(6)));
                                                        res = num1 + num2 + num3;
                                                        Tripletas.append("T"+temporal+" := "+identDataTemp.get(Integer.toString(res))+" + "+id.lexemeRank(8)+"\n");
                                                        cuadruplos("+",identDataTemp.get(Integer.toString(res)),id.lexemeRank(8),"T"+temporal);
                                                        temporal++;
                                                        Tripletas.append("T"+temporal+" := T"+(temporal-1)+" + "+id.lexemeRank(10)+"\n");
                                                        cuadruplos("+","T"+(temporal-1),id.lexemeRank(10),"T"+temporal);
                                                        CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(res)+"\n"        
                                                                       + "    MOV BX,"+id.lexemeRank(8)+"\n"
                                                                       + "    ADD AX,BX\n"
                                                                       + "    MOV BX,"+id.lexemeRank(10)+"\n"
                                                                       + "    ADD AX,BX\n"
                                                                       + "    MOV "+id.lexemeRank(13)+",AX\n");
                                                    }
                                                }else{
                                                    num1 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(2)));
                                                    num2 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(4)));
                                                    num3 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(6)));
                                                    res = num1 + num2;
                                                    Tripletas.append("T"+temporal+" := "+identDataTemp.get(Integer.toString(res))+" + "+id.lexemeRank(6)+"\n");
                                                    cuadruplos("+",identDataTemp.get(Integer.toString(res)),id.lexemeRank(6),"T"+temporal);
                                                    res = num1+num2+num3;
                                                    identDataTemp.put(identDataTypeR.get(Integer.toString(res)),"T"+temporal);
                                                    temporal++;
                                                    Tripletas.append("T"+temporal+" := T"+(temporal-1)+" + "+id.lexemeRank(8)+"\n");
                                                    cuadruplos("+","T"+(temporal-1),id.lexemeRank(8),"T"+temporal);
                                                    temporal++;
                                                    Tripletas.append("T"+temporal+" := T"+(temporal-1)+" + "+id.lexemeRank(10)+"\n");
                                                    cuadruplos("+","T"+(temporal-1),id.lexemeRank(10),"T"+temporal);
                                                    CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(res)+"\n"
                                                                   + "    MOV BX,"+id.lexemeRank(6)+"\n"
                                                                   + "    ADD AX,BX\n"        
                                                                   + "    MOV BX,"+id.lexemeRank(8)+"\n"
                                                                   + "    ADD AX,BX\n"
                                                                   + "    MOV BX,"+id.lexemeRank(10)+"\n"
                                                                   + "    ADD AX,BX\n"
                                                                   + "    MOV "+id.lexemeRank(13)+",AX\n");
                                                }
                                            }else{
                                                Tripletas.append("T"+temporal+" := "+id.lexemeRank(2)+" + "+id.lexemeRank(4)+"\n");
                                                cuadruplos("+",id.lexemeRank(2),id.lexemeRank(4),"T"+temporal);
                                                num1 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(2)));
                                                num2 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(4)));
                                                res = num1 + num2;
                                                identDataTemp.put(Integer.toString(res),"T"+temporal);
                                                temporal++;
                                                Tripletas.append("T"+temporal+" := T"+(temporal-1)+" + "+id.lexemeRank(6)+"\n");
                                                cuadruplos("+","T"+(temporal-1),id.lexemeRank(6),"T"+temporal);
                                                temporal++;
                                                Tripletas.append("T"+temporal+" := T"+(temporal-1)+" + "+id.lexemeRank(8)+"\n");
                                                cuadruplos("+","T"+(temporal-1),id.lexemeRank(8),"T"+temporal);
                                                temporal++;
                                                Tripletas.append("T"+temporal+" := T"+(temporal-1)+" + "+id.lexemeRank(10)+"\n");
                                                cuadruplos("+","T"+(temporal-1),id.lexemeRank(10),"T"+temporal);
                                                CodigoObjeto.add("    MOV AX,"+id.lexemeRank(2)+"\n"
                                                               + "    MOV BX,"+id.lexemeRank(4)+"\n"
                                                               + "    ADD AX,BX\n"        
                                                               + "    MOV BX,"+id.lexemeRank(6)+"\n"
                                                               + "    ADD AX,BX\n"
                                                               + "    MOV BX,"+id.lexemeRank(8)+"\n"
                                                               + "    ADD AX,BX\n"
                                                               + "    MOV BX,"+id.lexemeRank(10)+"\n"
                                                               + "    ADD AX,BX\n"
                                                               + "    MOV "+id.lexemeRank(13)+",AX\n");
                                            }
                                        }
                                        identDataTemp.put(identDataTypeR.get(id.lexemeRank(13)),"T"+temporal);
                                        Tripletas.append(id.lexemeRank(13)+" := T"+temporal+"\n");
                                        cuadruplos("=","T"+temporal,"",id.lexemeRank(13));
                                    }
                                    PanelSalida.append("-->   Se ha utlizado la función de Sumar, que tiene como parametros a:        " + id.lexemeRank(2) + " = " + identDataTypeV2.get(id.lexemeRank(2)) + " , " + id.lexemeRank(4) + " = " + identDataTypeV2.get(id.lexemeRank(4)) + " , " + id.lexemeRank(6) + " = " + identDataTypeV2.get(id.lexemeRank(6)) + " , " + id.lexemeRank(8) + " = " + identDataTypeV2.get(id.lexemeRank(8)) + " , " + id.lexemeRank(10) + " = " + identDataTypeV2.get(id.lexemeRank(10)) + "\ndonde el resultado de la operación es: " + identDataTypeR.get(id.lexemeRank(13)) + " , y se guardó en: " + id.lexemeRank(13) + ".........\n");
                                    break;
                                }else if(id.lexemeRank(0).equals("Restar") && sentence.contains(id.lexemeRank(2)) && sentence.contains(id.lexemeRank(4)) && sentence.contains(id.lexemeRank(6)) && sentence.contains(id.lexemeRank(8)) && sentence.contains(id.lexemeRank(10)) && sentence.contains(id.lexemeRank(13))){
                                    temporal++;
                                    if(identDataTemp.containsKey(identDataTypeR.get(id.lexemeRank(13)))){
                                        Tripletas.append(id.lexemeRank(13)+" := "+identDataTemp.get(identDataTypeR.get(id.lexemeRank(13)))+"\n");
                                        cuadruplos("=",identDataTemp.get(identDataTypeR.get(id.lexemeRank(13))),"",id.lexemeRank(13));
                                        CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(identDataTypeR.get(id.lexemeRank(13)))+"\n"
                                                       + "    MOV "+id.lexemeRank(13)+",AX\n");
                                    }else{
                                        if(identDataType.get(id.lexemeRank(2)).equals("Numero_Decimal")){
                                            double num1 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(2)));
                                            double num2 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(4)));
                                            double res = num1 - num2;
                                            if(identDataTemp.containsKey(Double.toString(res))){
                                                num1 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(2)));
                                                num2 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(4)));
                                                double num3 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(6)));
                                                res = num1 - num2 - num3;
                                                if(identDataTemp.containsKey(Double.toString(res))){
                                                    num1 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(2)));
                                                    num2 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(4)));
                                                    num3 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(6)));
                                                    double num4 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(8)));
                                                    res = num1 - num2 - num3 - num4;
                                                    if(identDataTemp.containsKey(Double.toString(res))){
                                                        Tripletas.append("T"+temporal+" := "+identDataTemp.get(Double.toString(res))+" - "+id.lexemeRank(10)+"\n");
                                                        cuadruplos("-",identDataTemp.get(Double.toString(res)),id.lexemeRank(10),"T"+temporal);
                                                        CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(res)+"\n"        
                                                                       + "    MOV BX,"+id.lexemeRank(10)+"\n"
                                                                       + "    SUB AX,BX\n"
                                                                       + "    MOV "+id.lexemeRank(13)+",AX\n");
                                                    }else{
                                                        num1 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(2)));
                                                        num2 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(4)));
                                                        num3 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(6)));
                                                        res = num1 - num2 - num3;
                                                        Tripletas.append("T"+temporal+" := "+identDataTemp.get(Double.toString(res))+" - "+id.lexemeRank(8)+"\n");
                                                        cuadruplos("-",identDataTemp.get(Double.toString(res)),id.lexemeRank(8),"T"+temporal);
                                                        temporal++;
                                                        Tripletas.append("T"+temporal+" := T"+(temporal-1)+" - "+id.lexemeRank(10)+"\n");
                                                        cuadruplos("-","T"+(temporal-1),id.lexemeRank(10),"T"+temporal);
                                                        CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(res)+"\n"        
                                                                       + "    MOV BX,"+id.lexemeRank(8)+"\n"
                                                                       + "    SUB AX,BX\n"
                                                                       + "    MOV BX,"+id.lexemeRank(10)+"\n"
                                                                       + "    SUB AX,BX\n"
                                                                       + "    MOV "+id.lexemeRank(13)+",AX\n");
                                                    }
                                                }else{
                                                    num1 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(2)));
                                                    num2 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(4)));
                                                    num3 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(6)));
                                                    res = num1 - num2;
                                                    Tripletas.append("T"+temporal+" := "+identDataTemp.get(Double.toString(res))+" - "+id.lexemeRank(6)+"\n");
                                                    cuadruplos("-",identDataTemp.get(Double.toString(res)),id.lexemeRank(6),"T"+temporal);
                                                    res = num1-num2-num3;
                                                    identDataTemp.put(identDataTypeR.get(Double.toString(res)),"T"+temporal);
                                                    temporal++;
                                                    Tripletas.append("T"+temporal+" := T"+(temporal-1)+" - "+id.lexemeRank(8)+"\n");
                                                    cuadruplos("-","T"+(temporal-1),id.lexemeRank(8),"T"+temporal);
                                                    temporal++;
                                                    Tripletas.append("T"+temporal+" := T"+(temporal-1)+" - "+id.lexemeRank(10)+"\n");
                                                    cuadruplos("-","T"+(temporal-1),id.lexemeRank(10),"T"+temporal);
                                                    CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(res)+"\n"
                                                                   + "    MOV BX,"+id.lexemeRank(6)+"\n"
                                                                   + "    SUB AX,BX\n"        
                                                                   + "    MOV BX,"+id.lexemeRank(8)+"\n"
                                                                   + "    SUB AX,BX\n"
                                                                   + "    MOV BX,"+id.lexemeRank(10)+"\n"
                                                                   + "    SUB AX,BX\n"
                                                                   + "    MOV "+id.lexemeRank(13)+",AX\n");
                                                }
                                            }else{
                                                Tripletas.append("T"+temporal+" := "+id.lexemeRank(2)+" - "+id.lexemeRank(4)+"\n");
                                                cuadruplos("-",id.lexemeRank(2),id.lexemeRank(4),"T"+temporal);
                                                num1 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(2)));
                                                num2 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(4)));
                                                res = num1 - num2;
                                                identDataTemp.put(Double.toString(res),"T"+temporal);
                                                temporal++;
                                                Tripletas.append("T"+temporal+" := T"+(temporal-1)+" - "+id.lexemeRank(6)+"\n");
                                                cuadruplos("-","T"+(temporal-1),id.lexemeRank(6),"T"+temporal);
                                                temporal++;
                                                Tripletas.append("T"+temporal+" := T"+(temporal-1)+" - "+id.lexemeRank(8)+"\n");
                                                cuadruplos("-","T"+(temporal-1),id.lexemeRank(8),"T"+temporal);
                                                temporal++;
                                                Tripletas.append("T"+temporal+" := T"+(temporal-1)+" - "+id.lexemeRank(10)+"\n");
                                                cuadruplos("-","T"+(temporal-1),id.lexemeRank(10),"T"+temporal);
                                                CodigoObjeto.add("    MOV AX,"+id.lexemeRank(2)+"\n"
                                                               + "    MOV BX,"+id.lexemeRank(4)+"\n"
                                                               + "    SUB AX,BX\n"        
                                                               + "    MOV BX,"+id.lexemeRank(6)+"\n"
                                                               + "    SUB AX,BX\n"
                                                               + "    MOV BX,"+id.lexemeRank(8)+"\n"
                                                               + "    SUB AX,BX\n"
                                                               + "    MOV BX,"+id.lexemeRank(10)+"\n"
                                                               + "    SUB AX,BX\n"
                                                               + "    MOV "+id.lexemeRank(13)+",AX\n");
                                            }
                                        }
                                        if(identDataType.get(id.lexemeRank(2)).equals("Numero_Entero")){
                                            int num1 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(2)));
                                            int num2 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(4)));
                                            int res = num1 - num2;
                                            if(identDataTemp.containsKey(Integer.toString(res))){
                                                num1 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(2)));
                                                num2 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(4)));
                                                int num3 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(6)));
                                                res = num1 - num2 - num3;
                                                if(identDataTemp.containsKey(Integer.toString(res))){
                                                    num1 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(2)));
                                                    num2 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(4)));
                                                    num3 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(6)));
                                                    int num4 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(8)));
                                                    res = num1 - num2 - num3 - num4;
                                                    if(identDataTemp.containsKey(Integer.toString(res))){
                                                        Tripletas.append("T"+temporal+" := "+identDataTemp.get(Integer.toString(res))+" - "+id.lexemeRank(10)+"\n");
                                                        cuadruplos("-",identDataTemp.get(Integer.toString(res)),id.lexemeRank(10),"T"+temporal);
                                                        CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(res)+"\n"        
                                                                       + "    MOV BX,"+id.lexemeRank(10)+"\n"
                                                                       + "    SUB AX,BX\n"
                                                                       + "    MOV "+id.lexemeRank(13)+",AX\n");
                                                    }else{
                                                        num1 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(2)));
                                                        num2 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(4)));
                                                        num3 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(6)));
                                                        res = num1 - num2 - num3;
                                                        Tripletas.append("T"+temporal+" := "+identDataTemp.get(Integer.toString(res))+" - "+id.lexemeRank(8)+"\n");
                                                        cuadruplos("-",identDataTemp.get(Integer.toString(res)),id.lexemeRank(8),"T"+temporal);
                                                        temporal++;
                                                        Tripletas.append("T"+temporal+" := T"+(temporal-1)+" - "+id.lexemeRank(10)+"\n");
                                                        cuadruplos("-","T"+(temporal-1),id.lexemeRank(10),"T"+temporal);
                                                        CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(res)+"\n"        
                                                                       + "    MOV BX,"+id.lexemeRank(8)+"\n"
                                                                       + "    SUB AX,BX\n"
                                                                       + "    MOV BX,"+id.lexemeRank(10)+"\n"
                                                                       + "    SUB AX,BX\n"
                                                                       + "    MOV "+id.lexemeRank(13)+",AX\n");
                                                    }
                                                }else{
                                                    num1 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(2)));
                                                    num2 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(4)));
                                                    num3 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(6)));
                                                    res = num1 - num2;
                                                    Tripletas.append("T"+temporal+" := "+identDataTemp.get(Integer.toString(res))+" - "+id.lexemeRank(6)+"\n");
                                                    cuadruplos("-",identDataTemp.get(Integer.toString(res)),id.lexemeRank(6),"T"+temporal);
                                                    res = num1-num2-num3;
                                                    identDataTemp.put(identDataTypeR.get(Integer.toString(res)),"T"+temporal);
                                                    temporal++;
                                                    Tripletas.append("T"+temporal+" := T"+(temporal-1)+" - "+id.lexemeRank(8)+"\n");
                                                    cuadruplos("-","T"+(temporal-1),id.lexemeRank(8),"T"+temporal);
                                                    temporal++;
                                                    Tripletas.append("T"+temporal+" := T"+(temporal-1)+" - "+id.lexemeRank(10)+"\n");
                                                    cuadruplos("-","T"+(temporal-1),id.lexemeRank(10),"T"+temporal);
                                                    CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(res)+"\n"
                                                                   + "    MOV BX,"+id.lexemeRank(6)+"\n"
                                                                   + "    SUB AX,BX\n"        
                                                                   + "    MOV BX,"+id.lexemeRank(8)+"\n"
                                                                   + "    SUB AX,BX\n"
                                                                   + "    MOV BX,"+id.lexemeRank(10)+"\n"
                                                                   + "    SUB AX,BX\n"
                                                                   + "    MOV "+id.lexemeRank(13)+",AX\n");
                                                }
                                            }else{
                                                Tripletas.append("T"+temporal+" := "+id.lexemeRank(2)+" - "+id.lexemeRank(4)+"\n");
                                                cuadruplos("-",id.lexemeRank(2),id.lexemeRank(4),"T"+temporal);
                                                num1 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(2)));
                                                num2 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(4)));
                                                res = num1 - num2;
                                                identDataTemp.put(Integer.toString(res),"T"+temporal);
                                                temporal++;
                                                Tripletas.append("T"+temporal+" := T"+(temporal-1)+" - "+id.lexemeRank(6)+"\n");
                                                cuadruplos("-","T"+(temporal-1),id.lexemeRank(6),"T"+temporal);
                                                temporal++;
                                                Tripletas.append("T"+temporal+" := T"+(temporal-1)+" - "+id.lexemeRank(8)+"\n");
                                                cuadruplos("-","T"+(temporal-1),id.lexemeRank(8),"T"+temporal);
                                                temporal++;
                                                Tripletas.append("T"+temporal+" := T"+(temporal-1)+" - "+id.lexemeRank(10)+"\n");
                                                cuadruplos("-","T"+(temporal-1),id.lexemeRank(10),"T"+temporal);
                                                CodigoObjeto.add("    MOV AX,"+id.lexemeRank(2)+"\n"
                                                               + "    MOV BX,"+id.lexemeRank(4)+"\n"
                                                               + "    SUB AX,BX\n"        
                                                               + "    MOV BX,"+id.lexemeRank(6)+"\n"
                                                               + "    SUB AX,BX\n"
                                                               + "    MOV BX,"+id.lexemeRank(8)+"\n"
                                                               + "    SUB AX,BX\n"
                                                               + "    MOV BX,"+id.lexemeRank(10)+"\n"
                                                               + "    SUB AX,BX\n"
                                                               + "    MOV "+id.lexemeRank(13)+",AX\n");
                                            }
                                        }
                                        identDataTemp.put(identDataTypeR.get(id.lexemeRank(13)),"T"+temporal);
                                        Tripletas.append(id.lexemeRank(13)+" := T"+temporal+"\n");
                                        cuadruplos("=","T"+temporal,"",id.lexemeRank(13));
                                    }
                                    PanelSalida.append("-->   Se ha utlizado la función de Restar, que tiene como parametros a:       " + id.lexemeRank(2) + " = " + identDataTypeV2.get(id.lexemeRank(2)) + " , " + id.lexemeRank(4) + " = " + identDataTypeV2.get(id.lexemeRank(4)) + " , " + id.lexemeRank(6) + " = " + identDataTypeV2.get(id.lexemeRank(6)) + " , " + id.lexemeRank(8) + " = " + identDataTypeV2.get(id.lexemeRank(8)) + " , " + id.lexemeRank(10) + " = " + identDataTypeV2.get(id.lexemeRank(10)) + "\ndonde el resultado de la operación es: " + identDataTypeR.get(id.lexemeRank(13)) + " , y se guardó en: " + id.lexemeRank(13) + ".........\n");
                                    break;
                                }else if(id.lexemeRank(0).equals("Multiplicar") && sentence.contains(id.lexemeRank(2)) && sentence.contains(id.lexemeRank(4)) && sentence.contains(id.lexemeRank(6)) && sentence.contains(id.lexemeRank(8)) && sentence.contains(id.lexemeRank(10)) && sentence.contains(id.lexemeRank(13))){
                                    temporal++;
                                    if(identDataTemp.containsKey(identDataTypeR.get(id.lexemeRank(13)))){
                                        Tripletas.append(id.lexemeRank(13)+" := "+identDataTemp.get(identDataTypeR.get(id.lexemeRank(13)))+"\n");
                                        cuadruplos("=",identDataTemp.get(identDataTypeR.get(id.lexemeRank(13))),"",id.lexemeRank(13));
                                        CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(identDataTypeR.get(id.lexemeRank(13)))+"\n"
                                                       + "    MOV "+id.lexemeRank(13)+",AX\n");
                                    }else{
                                        if(identDataType.get(id.lexemeRank(2)).equals("Numero_Decimal")){
                                            double num1 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(2)));
                                            double num2 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(4)));
                                            double res = num1 * num2;
                                            if(identDataTemp.containsKey(Double.toString(res))){
                                                num1 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(2)));
                                                num2 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(4)));
                                                double num3 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(6)));
                                                res = num1 * num2 * num3;
                                                if(identDataTemp.containsKey(Double.toString(res))){
                                                    num1 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(2)));
                                                    num2 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(4)));
                                                    num3 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(6)));
                                                    double num4 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(8)));
                                                    res = num1 * num2 * num3 * num4;
                                                    if(identDataTemp.containsKey(Double.toString(res))){
                                                        Tripletas.append("T"+temporal+" := "+identDataTemp.get(Double.toString(res))+" * "+id.lexemeRank(10)+"\n");
                                                        cuadruplos("*",identDataTemp.get(Double.toString(res)),id.lexemeRank(10),"T"+temporal);
                                                        CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(res)+"\n"        
                                                                       + "    MOV BX,"+id.lexemeRank(10)+"\n"
                                                                       + "    MUL BX\n"
                                                                       + "    MOV "+id.lexemeRank(13)+",AX\n");
                                                    }else{
                                                        num1 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(2)));
                                                        num2 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(4)));
                                                        num3 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(6)));
                                                        res = num1 * num2 * num3;
                                                        Tripletas.append("T"+temporal+" := "+identDataTemp.get(Double.toString(res))+" * "+id.lexemeRank(8)+"\n");
                                                        cuadruplos("*",identDataTemp.get(Double.toString(res)),id.lexemeRank(8),"T"+temporal);
                                                        temporal++;
                                                        Tripletas.append("T"+temporal+" := T"+(temporal-1)+" * "+id.lexemeRank(10)+"\n");
                                                        cuadruplos("*","T"+(temporal-1),id.lexemeRank(10),"T"+temporal);
                                                        CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(res)+"\n"        
                                                                       + "    MOV BX,"+id.lexemeRank(8)+"\n"
                                                                       + "    MUL AX,BX\n"
                                                                       + "    MOV BX,"+id.lexemeRank(10)+"\n"
                                                                       + "    MUL AX,BX\n"
                                                                       + "    MOV "+id.lexemeRank(13)+",AX\n");
                                                    }
                                                }else{
                                                    num1 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(2)));
                                                    num2 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(4)));
                                                    num3 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(6)));
                                                    res = num1 * num2;
                                                    Tripletas.append("T"+temporal+" := "+identDataTemp.get(Double.toString(res))+" * "+id.lexemeRank(6)+"\n");
                                                    cuadruplos("*",identDataTemp.get(Double.toString(res)),id.lexemeRank(6),"T"+temporal);
                                                    res = num1*num2*num3;
                                                    identDataTemp.put(identDataTypeR.get(Double.toString(res)),"T"+temporal);
                                                    temporal++;
                                                    Tripletas.append("T"+temporal+" := T"+(temporal-1)+" * "+id.lexemeRank(8)+"\n");
                                                    cuadruplos("*","T"+(temporal-1),id.lexemeRank(8),"T"+temporal);
                                                    temporal++;
                                                    Tripletas.append("T"+temporal+" := T"+(temporal-1)+" * "+id.lexemeRank(10)+"\n");
                                                    cuadruplos("*","T"+(temporal-1),id.lexemeRank(10),"T"+temporal);
                                                    CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(res)+"\n"
                                                                   + "    MOV BX,"+id.lexemeRank(6)+"\n"
                                                                   + "    MUL BX\n"        
                                                                   + "    MOV BX,"+id.lexemeRank(8)+"\n"
                                                                   + "    MUL BX\n"
                                                                   + "    MOV BX,"+id.lexemeRank(10)+"\n"
                                                                   + "    MUL BX\n"
                                                                   + "    MOV "+id.lexemeRank(13)+",AX\n");
                                                }
                                            }else{
                                                Tripletas.append("T"+temporal+" := "+id.lexemeRank(2)+" * "+id.lexemeRank(4)+"\n");
                                                cuadruplos("*",id.lexemeRank(2),id.lexemeRank(4),"T"+temporal);
                                                num1 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(2)));
                                                num2 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(4)));
                                                res = num1 * num2;
                                                identDataTemp.put(Double.toString(res),"T"+temporal);
                                                temporal++;
                                                Tripletas.append("T"+temporal+" := T"+(temporal-1)+" * "+id.lexemeRank(6)+"\n");
                                                cuadruplos("*","T"+(temporal-1),id.lexemeRank(6),"T"+temporal);
                                                temporal++;
                                                Tripletas.append("T"+temporal+" := T"+(temporal-1)+" * "+id.lexemeRank(8)+"\n");
                                                cuadruplos("*","T"+(temporal-1),id.lexemeRank(8),"T"+temporal);
                                                temporal++;
                                                Tripletas.append("T"+temporal+" := T"+(temporal-1)+" * "+id.lexemeRank(10)+"\n");
                                                cuadruplos("*","T"+(temporal-1),id.lexemeRank(10),"T"+temporal);
                                                CodigoObjeto.add("    MOV AX,"+id.lexemeRank(2)+"\n"
                                                               + "    MOV BX,"+id.lexemeRank(4)+"\n"
                                                               + "    MUL BX\n"        
                                                               + "    MOV BX,"+id.lexemeRank(6)+"\n"
                                                               + "    MUL BX\n"
                                                               + "    MOV BX,"+id.lexemeRank(8)+"\n"
                                                               + "    MUL BX\n"
                                                               + "    MOV BX,"+id.lexemeRank(10)+"\n"
                                                               + "    MUL BX\n"
                                                               + "    MOV "+id.lexemeRank(13)+",AX\n");
                                            }
                                        }
                                        if(identDataType.get(id.lexemeRank(2)).equals("Numero_Entero")){
                                            int num1 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(2)));
                                            int num2 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(4)));
                                            int res = num1 * num2;
                                            if(identDataTemp.containsKey(Integer.toString(res))){
                                                num1 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(2)));
                                                num2 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(4)));
                                                int num3 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(6)));
                                                res = num1 * num2 * num3;
                                                if(identDataTemp.containsKey(Integer.toString(res))){
                                                    num1 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(2)));
                                                    num2 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(4)));
                                                    num3 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(6)));
                                                    int num4 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(8)));
                                                    res = num1 * num2 * num3 * num4;
                                                    if(identDataTemp.containsKey(Integer.toString(res))){
                                                        Tripletas.append("T"+temporal+" := "+identDataTemp.get(Integer.toString(res))+" * "+id.lexemeRank(10)+"\n");
                                                        cuadruplos("*",identDataTemp.get(Integer.toString(res)),id.lexemeRank(10),"T"+temporal);
                                                        CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(res)+"\n"        
                                                                       + "    MOV BX,"+id.lexemeRank(10)+"\n"
                                                                       + "    MUL BX\n"
                                                                       + "    MOV "+id.lexemeRank(13)+",AX\n");
                                                    }else{
                                                        num1 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(2)));
                                                        num2 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(4)));
                                                        num3 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(6)));
                                                        res = num1 * num2 * num3;
                                                        Tripletas.append("T"+temporal+" := "+identDataTemp.get(Integer.toString(res))+" * "+id.lexemeRank(8)+"\n");
                                                        cuadruplos("*",identDataTemp.get(Integer.toString(res)),id.lexemeRank(8),"T"+temporal);
                                                        temporal++;
                                                        Tripletas.append("T"+temporal+" := T"+(temporal-1)+" * "+id.lexemeRank(10)+"\n");
                                                        cuadruplos("*","T"+(temporal-1),id.lexemeRank(10),"T"+temporal);
                                                        CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(res)+"\n"        
                                                                       + "    MOV BX,"+id.lexemeRank(8)+"\n"
                                                                       + "    MUL AX,BX\n"
                                                                       + "    MOV BX,"+id.lexemeRank(10)+"\n"
                                                                       + "    MUL AX,BX\n"
                                                                       + "    MOV "+id.lexemeRank(13)+",AX\n");
                                                    }
                                                }else{
                                                    num1 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(2)));
                                                    num2 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(4)));
                                                    num3 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(6)));
                                                    res = num1 * num2;
                                                    Tripletas.append("T"+temporal+" := "+identDataTemp.get(Integer.toString(res))+" * "+id.lexemeRank(6)+"\n");
                                                    cuadruplos("*",identDataTemp.get(Integer.toString(res)),id.lexemeRank(6),"T"+temporal);
                                                    res = num1*num2*num3;
                                                    identDataTemp.put(identDataTypeR.get(Integer.toString(res)),"T"+temporal);
                                                    temporal++;
                                                    Tripletas.append("T"+temporal+" := T"+(temporal-1)+" * "+id.lexemeRank(8)+"\n");
                                                    cuadruplos("*","T"+(temporal-1),id.lexemeRank(8),"T"+temporal);
                                                    temporal++;
                                                    Tripletas.append("T"+temporal+" := T"+(temporal-1)+" * "+id.lexemeRank(10)+"\n");
                                                    cuadruplos("*","T"+(temporal-1),id.lexemeRank(10),"T"+temporal);
                                                    CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(res)+"\n"
                                                                   + "    MOV BX,"+id.lexemeRank(6)+"\n"
                                                                   + "    MUL BX\n"        
                                                                   + "    MOV BX,"+id.lexemeRank(8)+"\n"
                                                                   + "    MUL BX\n"
                                                                   + "    MOV BX,"+id.lexemeRank(10)+"\n"
                                                                   + "    MUL BX\n"
                                                                   + "    MOV "+id.lexemeRank(13)+",AX\n");
                                                }
                                            }else{
                                                Tripletas.append("T"+temporal+" := "+id.lexemeRank(2)+" * "+id.lexemeRank(4)+"\n");
                                                cuadruplos("*",id.lexemeRank(2),id.lexemeRank(4),"T"+temporal);
                                                num1 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(2)));
                                                num2 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(4)));
                                                res = num1 * num2;
                                                identDataTemp.put(Integer.toString(res),"T"+temporal);
                                                temporal++;
                                                Tripletas.append("T"+temporal+" := T"+(temporal-1)+" * "+id.lexemeRank(6)+"\n");
                                                cuadruplos("*","T"+(temporal-1),id.lexemeRank(6),"T"+temporal);
                                                temporal++;
                                                Tripletas.append("T"+temporal+" := T"+(temporal-1)+" * "+id.lexemeRank(8)+"\n");
                                                cuadruplos("*","T"+(temporal-1),id.lexemeRank(8),"T"+temporal);
                                                temporal++;
                                                Tripletas.append("T"+temporal+" := T"+(temporal-1)+" * "+id.lexemeRank(10)+"\n");
                                                cuadruplos("*","T"+(temporal-1),id.lexemeRank(10),"T"+temporal);
                                                CodigoObjeto.add("    MOV AX,"+id.lexemeRank(2)+"\n"
                                                               + "    MOV BX,"+id.lexemeRank(4)+"\n"
                                                               + "    MUL BX\n"        
                                                               + "    MOV BX,"+id.lexemeRank(6)+"\n"
                                                               + "    MUL BX\n"
                                                               + "    MOV BX,"+id.lexemeRank(8)+"\n"
                                                               + "    MUL BX\n"
                                                               + "    MOV BX,"+id.lexemeRank(10)+"\n"
                                                               + "    MUL BX\n"
                                                               + "    MOV "+id.lexemeRank(13)+",AX\n");
                                            }
                                        }
                                        identDataTemp.put(identDataTypeR.get(id.lexemeRank(13)),"T"+temporal);
                                        Tripletas.append(id.lexemeRank(13)+" := T"+temporal+"\n");
                                        cuadruplos("=","T"+temporal,"",id.lexemeRank(13));
                                    }
                                    PanelSalida.append("-->   Se ha utlizado la función de Multiplicar, que tiene como parametros a:  " + id.lexemeRank(2) + " = " + identDataTypeV2.get(id.lexemeRank(2)) + " , " + id.lexemeRank(4) + " = " + identDataTypeV2.get(id.lexemeRank(4)) + " , " + id.lexemeRank(6) + " = " + identDataTypeV2.get(id.lexemeRank(6)) + " , " + id.lexemeRank(8) + " = " + identDataTypeV2.get(id.lexemeRank(8)) + " , " + id.lexemeRank(10) + " = " + identDataTypeV2.get(id.lexemeRank(10)) + "\ndonde el resultado de la operación es: " + identDataTypeR.get(id.lexemeRank(13)) + " , y se guardó en: " + id.lexemeRank(13) + ".........\n");
                                    break;
                                }else if(id.lexemeRank(0).equals("Dividir") && sentence.contains(id.lexemeRank(2)) && sentence.contains(id.lexemeRank(4)) && sentence.contains(id.lexemeRank(6)) && sentence.contains(id.lexemeRank(8)) && sentence.contains(id.lexemeRank(10)) && sentence.contains(id.lexemeRank(13))){
                                    temporal++;
                                    if(identDataTemp.containsKey(identDataTypeR.get(id.lexemeRank(13)))){
                                        Tripletas.append(id.lexemeRank(13)+" := "+identDataTemp.get(identDataTypeR.get(id.lexemeRank(13)))+"\n");
                                        cuadruplos("=",identDataTemp.get(identDataTypeR.get(id.lexemeRank(13))),"",id.lexemeRank(13));
                                        CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(identDataTypeR.get(id.lexemeRank(13)))+"\n"
                                                       + "    MOV "+id.lexemeRank(13)+",AX\n");
                                    }else{
                                        if(identDataType.get(id.lexemeRank(2)).equals("Numero_Decimal")){
                                            double num1 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(2)));
                                            double num2 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(4)));
                                            double res = num1 / num2;
                                            if(identDataTemp.containsKey(Double.toString(res))){
                                                num1 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(2)));
                                                num2 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(4)));
                                                double num3 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(6)));
                                                res = num1 / num2 / num3;
                                                if(identDataTemp.containsKey(Double.toString(res))){
                                                    num1 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(2)));
                                                    num2 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(4)));
                                                    num3 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(6)));
                                                    double num4 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(8)));
                                                    res = num1 / num2 / num3 / num4;
                                                    if(identDataTemp.containsKey(Double.toString(res))){
                                                        Tripletas.append("T"+temporal+" := "+identDataTemp.get(Double.toString(res))+" / "+id.lexemeRank(10)+"\n");
                                                        cuadruplos("/",identDataTemp.get(Double.toString(res)),id.lexemeRank(10),"T"+temporal);
                                                        CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(res)+"\n"        
                                                                       + "    MOV BX,"+id.lexemeRank(10)+"\n"
                                                                       + "    DIV BX\n"
                                                                       + "    MOV "+id.lexemeRank(13)+",AX\n");
                                                    }else{
                                                        num1 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(2)));
                                                        num2 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(4)));
                                                        num3 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(6)));
                                                        res = num1 / num2 / num3;
                                                        Tripletas.append("T"+temporal+" := "+identDataTemp.get(Double.toString(res))+" / "+id.lexemeRank(8)+"\n");
                                                        cuadruplos("/",identDataTemp.get(Double.toString(res)),id.lexemeRank(8),"T"+temporal);
                                                        temporal++;
                                                        Tripletas.append("T"+temporal+" := T"+(temporal-1)+" / "+id.lexemeRank(10)+"\n");
                                                        cuadruplos("/","T"+(temporal-1),id.lexemeRank(10),"T"+temporal);
                                                        CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(res)+"\n"        
                                                                       + "    MOV BX,"+id.lexemeRank(8)+"\n"
                                                                       + "    DIV AX,BX\n"
                                                                       + "    MOV BX,"+id.lexemeRank(10)+"\n"
                                                                       + "    DIV AX,BX\n"
                                                                       + "    MOV "+id.lexemeRank(13)+",AX\n");
                                                    }
                                                }else{
                                                    num1 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(2)));
                                                    num2 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(4)));
                                                    num3 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(6)));
                                                    res = num1 / num2;
                                                    Tripletas.append("T"+temporal+" := "+identDataTemp.get(Double.toString(res))+" / "+id.lexemeRank(6)+"\n");
                                                    cuadruplos("/",identDataTemp.get(Double.toString(res)),id.lexemeRank(6),"T"+temporal);
                                                    res = num1/num2/num3;
                                                    identDataTemp.put(identDataTypeR.get(Double.toString(res)),"T"+temporal);
                                                    temporal++;
                                                    Tripletas.append("T"+temporal+" := T"+(temporal-1)+" / "+id.lexemeRank(8)+"\n");
                                                    cuadruplos("/","T"+(temporal-1),id.lexemeRank(8),"T"+temporal);
                                                    temporal++;
                                                    Tripletas.append("T"+temporal+" := T"+(temporal-1)+" / "+id.lexemeRank(10)+"\n");
                                                    cuadruplos("/","T"+(temporal-1),id.lexemeRank(10),"T"+temporal);
                                                    CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(res)+"\n"
                                                                   + "    MOV BX,"+id.lexemeRank(6)+"\n"
                                                                   + "    DIV BX\n"        
                                                                   + "    MOV BX,"+id.lexemeRank(8)+"\n"
                                                                   + "    DIV BX\n"
                                                                   + "    MOV BX,"+id.lexemeRank(10)+"\n"
                                                                   + "    DIV BX\n"
                                                                   + "    MOV "+id.lexemeRank(13)+",AX\n");
                                                }
                                            }else{
                                                Tripletas.append("T"+temporal+" := "+id.lexemeRank(2)+" / "+id.lexemeRank(4)+"\n");
                                                cuadruplos("/",id.lexemeRank(2),id.lexemeRank(4),"T"+temporal);
                                                num1 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(2)));
                                                num2 = Double.parseDouble(identDataTypeV2.get(id.lexemeRank(4)));
                                                res = num1 / num2;
                                                identDataTemp.put(Double.toString(res),"T"+temporal);
                                                temporal++;
                                                Tripletas.append("T"+temporal+" := T"+(temporal-1)+" / "+id.lexemeRank(6)+"\n");
                                                cuadruplos("/","T"+(temporal-1),id.lexemeRank(6),"T"+temporal);
                                                temporal++;
                                                Tripletas.append("T"+temporal+" := T"+(temporal-1)+" / "+id.lexemeRank(8)+"\n");
                                                cuadruplos("/","T"+(temporal-1),id.lexemeRank(8),"T"+temporal);
                                                temporal++;
                                                Tripletas.append("T"+temporal+" := T"+(temporal-1)+" / "+id.lexemeRank(10)+"\n");
                                                cuadruplos("/","T"+(temporal-1),id.lexemeRank(10),"T"+temporal);
                                                CodigoObjeto.add("    MOV AX,"+id.lexemeRank(2)+"\n"
                                                               + "    MOV BX,"+id.lexemeRank(4)+"\n"
                                                               + "    DIV BX\n"        
                                                               + "    MOV BX,"+id.lexemeRank(6)+"\n"
                                                               + "    DIV BX\n"
                                                               + "    MOV BX,"+id.lexemeRank(8)+"\n"
                                                               + "    DIV BX\n"
                                                               + "    MOV BX,"+id.lexemeRank(10)+"\n"
                                                               + "    DIV BX\n"
                                                               + "    MOV "+id.lexemeRank(13)+",AX\n");
                                            }
                                        }
                                        if(identDataType.get(id.lexemeRank(2)).equals("Numero_Entero")){
                                            int num1 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(2)));
                                            int num2 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(4)));
                                            int res = num1 / num2;
                                            if(identDataTemp.containsKey(Integer.toString(res))){
                                                num1 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(2)));
                                                num2 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(4)));
                                                int num3 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(6)));
                                                res = num1 / num2 / num3;
                                                if(identDataTemp.containsKey(Integer.toString(res))){
                                                    num1 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(2)));
                                                    num2 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(4)));
                                                    num3 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(6)));
                                                    int num4 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(8)));
                                                    res = num1 / num2 / num3 / num4;
                                                    if(identDataTemp.containsKey(Integer.toString(res))){
                                                        Tripletas.append("T"+temporal+" := "+identDataTemp.get(Integer.toString(res))+" / "+id.lexemeRank(10)+"\n");
                                                        cuadruplos("/",identDataTemp.get(Integer.toString(res)),id.lexemeRank(10),"T"+temporal);
                                                        CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(res)+"\n"        
                                                                       + "    MOV BX,"+id.lexemeRank(10)+"\n"
                                                                       + "    DIV BX\n"
                                                                       + "    MOV "+id.lexemeRank(13)+",AX\n");
                                                    }else{
                                                        num1 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(2)));
                                                        num2 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(4)));
                                                        num3 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(6)));
                                                        res = num1 / num2 / num3;
                                                        Tripletas.append("T"+temporal+" := "+identDataTemp.get(Integer.toString(res))+" / "+id.lexemeRank(8)+"\n");
                                                        cuadruplos("/",identDataTemp.get(Integer.toString(res)),id.lexemeRank(8),"T"+temporal);
                                                        temporal++;
                                                        Tripletas.append("T"+temporal+" := T"+(temporal-1)+" / "+id.lexemeRank(10)+"\n");
                                                        cuadruplos("/","T"+(temporal-1),id.lexemeRank(10),"T"+temporal);
                                                        CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(res)+"\n"        
                                                                       + "    MOV BX,"+id.lexemeRank(8)+"\n"
                                                                       + "    DIV AX,BX\n"
                                                                       + "    MOV BX,"+id.lexemeRank(10)+"\n"
                                                                       + "    DIV AX,BX\n"
                                                                       + "    MOV "+id.lexemeRank(13)+",AX\n");
                                                    }
                                                }else{
                                                    num1 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(2)));
                                                    num2 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(4)));
                                                    num3 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(6)));
                                                    res = num1 / num2;
                                                    Tripletas.append("T"+temporal+" := "+identDataTemp.get(Integer.toString(res))+" / "+id.lexemeRank(6)+"\n");
                                                    cuadruplos("/",identDataTemp.get(Integer.toString(res)),id.lexemeRank(6),"T"+temporal);
                                                    res = num1/num2/num3;
                                                    identDataTemp.put(identDataTypeR.get(Integer.toString(res)),"T"+temporal);
                                                    temporal++;
                                                    Tripletas.append("T"+temporal+" := T"+(temporal-1)+" / "+id.lexemeRank(8)+"\n");
                                                    cuadruplos("/","T"+(temporal-1),id.lexemeRank(8),"T"+temporal);
                                                    temporal++;
                                                    Tripletas.append("T"+temporal+" := T"+(temporal-1)+" / "+id.lexemeRank(10)+"\n");
                                                    cuadruplos("/","T"+(temporal-1),id.lexemeRank(10),"T"+temporal);
                                                    CodigoObjeto.add("    MOV AX,"+identDataTypeR2.get(res)+"\n"
                                                                   + "    MOV BX,"+id.lexemeRank(6)+"\n"
                                                                   + "    DIV BX\n"        
                                                                   + "    MOV BX,"+id.lexemeRank(8)+"\n"
                                                                   + "    DIV BX\n"
                                                                   + "    MOV BX,"+id.lexemeRank(10)+"\n"
                                                                   + "    DIV BX\n"
                                                                   + "    MOV "+id.lexemeRank(13)+",AX\n");
                                                }
                                            }else{
                                                Tripletas.append("T"+temporal+" := "+id.lexemeRank(2)+" / "+id.lexemeRank(4)+"\n");
                                                cuadruplos("/",id.lexemeRank(2),id.lexemeRank(4),"T"+temporal);
                                                num1 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(2)));
                                                num2 = Integer.parseInt(identDataTypeV2.get(id.lexemeRank(4)));
                                                res = num1 / num2;
                                                identDataTemp.put(Integer.toString(res),"T"+temporal);
                                                temporal++;
                                                Tripletas.append("T"+temporal+" := T"+(temporal-1)+" / "+id.lexemeRank(6)+"\n");
                                                cuadruplos("/","T"+(temporal-1),id.lexemeRank(6),"T"+temporal);
                                                temporal++;
                                                Tripletas.append("T"+temporal+" := T"+(temporal-1)+" / "+id.lexemeRank(8)+"\n");
                                                cuadruplos("/","T"+(temporal-1),id.lexemeRank(8),"T"+temporal);
                                                temporal++;
                                                Tripletas.append("T"+temporal+" := T"+(temporal-1)+" / "+id.lexemeRank(10)+"\n");
                                                cuadruplos("/","T"+(temporal-1),id.lexemeRank(10),"T"+temporal);
                                                CodigoObjeto.add("    MOV AX,"+id.lexemeRank(2)+"\n"
                                                               + "    MOV BX,"+id.lexemeRank(4)+"\n"
                                                               + "    DIV BX\n"        
                                                               + "    MOV BX,"+id.lexemeRank(6)+"\n"
                                                               + "    DIV BX\n"
                                                               + "    MOV BX,"+id.lexemeRank(8)+"\n"
                                                               + "    DIV BX\n"
                                                               + "    MOV BX,"+id.lexemeRank(10)+"\n"
                                                               + "    DIV BX\n"
                                                               + "    MOV "+id.lexemeRank(13)+",AX\n");
                                            }
                                        }
                                        identDataTemp.put(identDataTypeR.get(id.lexemeRank(13)),"T"+temporal);
                                        Tripletas.append(id.lexemeRank(13)+" := T"+temporal+"\n");
                                        cuadruplos("=","T"+temporal,"",id.lexemeRank(13));
                                    }
                                    PanelSalida.append("-->   Se ha utlizado la función de Dividir, que tiene como parametros a:      " + id.lexemeRank(2) + " = " + identDataTypeV2.get(id.lexemeRank(2)) + " , " + id.lexemeRank(4) + " = " + identDataTypeV2.get(id.lexemeRank(4)) + " , " + id.lexemeRank(6) + " = " + identDataTypeV2.get(id.lexemeRank(6)) + " , " + id.lexemeRank(8) + " = " + identDataTypeV2.get(id.lexemeRank(8)) + " , " + id.lexemeRank(10) + " = " + identDataTypeV2.get(id.lexemeRank(10)) + "\ndonde el resultado de la operación es: " + identDataTypeR.get(id.lexemeRank(13)) + " , y se guardó en: " + id.lexemeRank(13) + ".........\n");
                                    break;
                                }
                            }
                        }
                        for(Production id: identProdF3){
                            if(sentence.startsWith(id.lexemeRank(0)) && sentence.contains(id.lexemeRank(2))  && sentence.contains(id.lexemeRank(4)) && sentence.contains(id.lexemeRank(6)) && sentence.contains(id.lexemeRank(8))){
                                temporal++;
                                if(identDataTemp.containsKey(identDataTypeV.get(id.lexemeRank(2))+" "+identDataTypeR.get(id.lexemeRank(4))+" "+identDataTypeV.get(id.lexemeRank(6))+" "+identDataTypeV.get(id.lexemeRank(8)))){
                                    Tripletas.append("T"+temporal+" := "+identDataTemp.get(identDataTypeV.get(id.lexemeRank(2))+" "+identDataTypeR.get(id.lexemeRank(4))+" "+identDataTypeV.get(id.lexemeRank(6))+" "+identDataTypeV.get(id.lexemeRank(8)))+"\n");
                                    cuadruplos("=",identDataTemp.get(identDataTypeV.get(id.lexemeRank(2))+" "+identDataTypeR.get(id.lexemeRank(4))+" "+identDataTypeV.get(id.lexemeRank(6))+" "+identDataTypeV.get(id.lexemeRank(8))),"","T"+temporal);
                                    PanelSalida.append("-->   Se ha utlizado la función de Mostrar en su forma de tipo Entero, pero\nya hay una funcion de mostrar con los mismos parametros y no se genera una interfaz.........\n");
                                }else{
                                    if(identDataTemp.containsKey(identDataTypeV.get(id.lexemeRank(2))+" "+identDataTypeR.get(id.lexemeRank(4)))){
                                        
                                    }else{
                                        Tripletas.append("T"+temporal+" := "+id.lexemeRank(2)+" + "+id.lexemeRank(4)+"\n");
                                        cuadruplos("+",id.lexemeRank(2),id.lexemeRank(4),"T"+temporal);
                                        identDataTemp.put(identDataTypeV.get(id.lexemeRank(2))+" "+identDataTypeR.get(id.lexemeRank(4)),"T"+temporal);
                                    }
                                    temporal++;
                                    if(identDataTemp.containsKey(identDataTypeV.get(id.lexemeRank(6))+" "+identDataTypeV.get(id.lexemeRank(8)))){

                                    }else{
                                        Tripletas.append("T"+temporal+" := "+id.lexemeRank(6)+" + "+id.lexemeRank(8)+"\n");
                                        cuadruplos("+",id.lexemeRank(6),id.lexemeRank(8),"T"+temporal);
                                        identDataTemp.put(identDataTypeV.get(id.lexemeRank(6))+" "+identDataTypeV.get(id.lexemeRank(8)),"T"+temporal);
                                    }
                                    temporal++;
                                    Tripletas.append("T"+temporal+" := "+identDataTemp.get(identDataTypeV.get(id.lexemeRank(2))+" "+identDataTypeR.get(id.lexemeRank(4)))+" + "+identDataTemp.get(identDataTypeV.get(id.lexemeRank(6))+" "+identDataTypeV.get(id.lexemeRank(8)))+"\n");
                                    cuadruplos("+",identDataTemp.get(identDataTypeV.get(id.lexemeRank(2))+" "+identDataTypeR.get(id.lexemeRank(4))),identDataTemp.get(identDataTypeV.get(id.lexemeRank(6))+" "+identDataTypeV.get(id.lexemeRank(8))),"T"+temporal);
                                    identDataTemp.put(identDataTypeV.get(id.lexemeRank(2))+" "+identDataTypeR.get(id.lexemeRank(4))+" "+identDataTypeV.get(id.lexemeRank(6))+" "+identDataTypeV.get(id.lexemeRank(8)),"T"+temporal);
                                    PanelSalida.append("-->   Se ha utlizado la función de Mostrar en su forma de tipo Entero, del cual se crea la siguiente interfaz.........\n");
                                    MostrarE me = new MostrarE();
                                    me.setLocationRelativeTo(this);
                                    me.setVisible(true);
                                    me.setCadena(id.lexemeRank(2),identDataTypeV.get(id.lexemeRank(2)).replace("'",""));
                                    me.setResultado(id.lexemeRank(4),identDataTypeR.get(id.lexemeRank(4)));
                                    me.setFigura(id.lexemeRank(6),identDataTypeV.get(id.lexemeRank(6)));
                                    me.setColor(id.lexemeRank(8),identDataTypeV.get(id.lexemeRank(8)));
                                    me.Figura();
                                    me.Cadena();
                                    me.Color();
                                    me.Resultado();
                                }
                                CodigoObjeto.add("    MOV AX,"+id.lexemeRank(2)+"\n"
                                               + "    ADD AX,"+id.lexemeRank(4)+"\n"
                                               + "    MOV BX,"+id.lexemeRank(6)+"\n"
                                               + "    ADD BX,"+id.lexemeRank(8)+"\n"
                                               + "    ADD AX,BX\n");
                                break;
                            }
                        }
                        for(Production id: identProdF2){
                            if(sentence.startsWith(id.lexemeRank(0)) && sentence.contains(id.lexemeRank(2))  && sentence.contains(id.lexemeRank(4)) && sentence.contains(id.lexemeRank(6)) && sentence.contains(id.lexemeRank(8)) && sentence.contains(id.lexemeRank(10))  && sentence.contains(id.lexemeRank(12)) && sentence.contains(id.lexemeRank(14)) && sentence.contains(id.lexemeRank(16)) && sentence.contains(id.lexemeRank(18))  && sentence.contains(id.lexemeRank(20)) && sentence.contains(id.lexemeRank(22)) && sentence.contains(id.lexemeRank(24))){
                                temporal++;
                                if(identDataTemp.containsKey(identDataTypeV.get(id.lexemeRank(2))+" "+identDataTypeR.get(id.lexemeRank(4))+" "+identDataTypeV.get(id.lexemeRank(6))+" "+identDataTypeV.get(id.lexemeRank(8))+" "+identDataTypeV.get(id.lexemeRank(10))+" "+identDataTypeV.get(id.lexemeRank(12))+" "+identDataTypeV.get(id.lexemeRank(14))+" "+identDataTypeV.get(id.lexemeRank(16))+" "+identDataTypeV.get(id.lexemeRank(18))+" "+identDataTypeV.get(id.lexemeRank(20))+" "+identDataTypeV.get(id.lexemeRank(22))+" "+identDataTypeV.get(id.lexemeRank(24)))){
                                    Tripletas.append("T"+temporal+" := "+identDataTemp.get(identDataTypeV.get(id.lexemeRank(2))+" "+identDataTypeR.get(id.lexemeRank(4))+" "+identDataTypeV.get(id.lexemeRank(6))+" "+identDataTypeV.get(id.lexemeRank(8))+" "+identDataTypeV.get(id.lexemeRank(10))+" "+identDataTypeV.get(id.lexemeRank(12))+" "+identDataTypeV.get(id.lexemeRank(14))+" "+identDataTypeV.get(id.lexemeRank(16))+" "+identDataTypeV.get(id.lexemeRank(18))+" "+identDataTypeV.get(id.lexemeRank(20))+" "+identDataTypeV.get(id.lexemeRank(22))+" "+identDataTypeV.get(id.lexemeRank(24)))+"\n");
                                    cuadruplos("=",identDataTemp.get(identDataTypeV.get(id.lexemeRank(2))+" "+identDataTypeR.get(id.lexemeRank(4))+" "+identDataTypeV.get(id.lexemeRank(6))+" "+identDataTypeV.get(id.lexemeRank(8))+" "+identDataTypeV.get(id.lexemeRank(10))+" "+identDataTypeV.get(id.lexemeRank(12))+" "+identDataTypeV.get(id.lexemeRank(14))+" "+identDataTypeV.get(id.lexemeRank(16))+" "+identDataTypeV.get(id.lexemeRank(18))+" "+identDataTypeV.get(id.lexemeRank(20))+" "+identDataTypeV.get(id.lexemeRank(22))+" "+identDataTypeV.get(id.lexemeRank(24))),"","T"+temporal);
                                    PanelSalida.append("-->   Se ha utlizado la función de Mostrar en su forma de tipo Decimal, del cual se crea la siguiente interfaz, pero\nya hay una funcion de mostrar con los mismos parametros y no se genera una interfaz.........\n");
                                }else{
                                    if(identDataTemp.containsKey(identDataTypeV.get(id.lexemeRank(2))+" "+identDataTypeR.get(id.lexemeRank(4)))){
                                        
                                    }else{
                                        Tripletas.append("T"+temporal+" := "+id.lexemeRank(2)+" + "+id.lexemeRank(4)+"\n");
                                        cuadruplos("+",id.lexemeRank(2),id.lexemeRank(4),"T"+temporal);
                                        identDataTemp.put(identDataTypeV.get(id.lexemeRank(2))+" "+identDataTypeR.get(id.lexemeRank(4)),"T"+temporal);
                                    }
                                    temporal++;
                                    if(identDataTemp.containsKey(identDataTypeV.get(id.lexemeRank(6))+" "+identDataTypeV.get(id.lexemeRank(8))+" "+identDataTypeV.get(id.lexemeRank(10))+" "+identDataTypeV.get(id.lexemeRank(12)))){
                                        
                                    }else{
                                        if(identDataTemp.containsKey(identDataTypeV.get(id.lexemeRank(6))+" "+identDataTypeV.get(id.lexemeRank(8)))){

                                        }else{
                                            Tripletas.append("T"+temporal+" := "+id.lexemeRank(6)+" + "+id.lexemeRank(8)+"\n");
                                            cuadruplos("+",id.lexemeRank(6),id.lexemeRank(8),"T"+temporal);
                                            identDataTemp.put(identDataTypeV.get(id.lexemeRank(6))+" "+identDataTypeV.get(id.lexemeRank(8)),"T"+temporal);
                                        }
                                        temporal++;
                                        if(identDataTemp.containsKey(identDataTypeV.get(id.lexemeRank(10))+" "+identDataTypeV.get(id.lexemeRank(12)))){

                                        }else{
                                            Tripletas.append("T"+temporal+" := "+id.lexemeRank(10)+" + "+id.lexemeRank(12)+"\n");
                                            cuadruplos("+",id.lexemeRank(10),id.lexemeRank(12),"T"+temporal);
                                            identDataTemp.put(identDataTypeV.get(id.lexemeRank(10))+" "+identDataTypeV.get(id.lexemeRank(12)),"T"+temporal);
                                        }
                                        temporal++;
                                        Tripletas.append("T"+temporal+" := "+identDataTemp.get(identDataTypeV.get(id.lexemeRank(6))+" "+identDataTypeV.get(id.lexemeRank(8)))+" + "+identDataTemp.get(identDataTypeV.get(id.lexemeRank(10))+" "+identDataTypeV.get(id.lexemeRank(12)))+"\n");
                                        cuadruplos("+",identDataTemp.get(identDataTypeV.get(id.lexemeRank(6))+" "+identDataTypeV.get(id.lexemeRank(8))),identDataTemp.get(identDataTypeV.get(id.lexemeRank(10))+" "+identDataTypeV.get(id.lexemeRank(12))),"T"+temporal);
                                        identDataTemp.put(identDataTypeV.get(id.lexemeRank(6))+" "+identDataTypeV.get(id.lexemeRank(8))+" "+identDataTypeV.get(id.lexemeRank(10))+" "+identDataTypeV.get(id.lexemeRank(12)),"T"+temporal);
                                    }
                                    temporal++;
                                    if(identDataTemp.containsKey(identDataTypeV.get(id.lexemeRank(14))+" "+identDataTypeV.get(id.lexemeRank(16))+" "+identDataTypeV.get(id.lexemeRank(18))+" "+identDataTypeV.get(id.lexemeRank(20)))){
                                        
                                    }else{
                                        if(identDataTemp.containsKey(identDataTypeV.get(id.lexemeRank(14))+" "+identDataTypeV.get(id.lexemeRank(16)))){

                                        }else{
                                            Tripletas.append("T"+temporal+" := "+id.lexemeRank(14)+" + "+id.lexemeRank(16)+"\n");
                                            cuadruplos("+",id.lexemeRank(14),id.lexemeRank(16),"T"+temporal);
                                            identDataTemp.put(identDataTypeV.get(id.lexemeRank(14))+" "+identDataTypeV.get(id.lexemeRank(16)),"T"+temporal);
                                        }
                                        temporal++;
                                        if(identDataTemp.containsKey(identDataTypeV.get(id.lexemeRank(18))+" "+identDataTypeV.get(id.lexemeRank(20)))){

                                        }else{
                                            Tripletas.append("T"+temporal+" := "+id.lexemeRank(18)+" + "+id.lexemeRank(20)+"\n");
                                            cuadruplos("+",id.lexemeRank(18),id.lexemeRank(20),"T"+temporal);
                                            identDataTemp.put(identDataTypeV.get(id.lexemeRank(18))+" "+identDataTypeV.get(id.lexemeRank(20)),"T"+temporal);
                                        }
                                        temporal++;
                                        Tripletas.append("T"+temporal+" := "+identDataTemp.get(identDataTypeV.get(id.lexemeRank(14))+" "+identDataTypeV.get(id.lexemeRank(16)))+" + "+identDataTemp.get(identDataTypeV.get(id.lexemeRank(18))+" "+identDataTypeV.get(id.lexemeRank(20)))+"\n");
                                        cuadruplos("+",identDataTemp.get(identDataTypeV.get(id.lexemeRank(14))+" "+identDataTypeV.get(id.lexemeRank(16))),identDataTemp.get(identDataTypeV.get(id.lexemeRank(18))+" "+identDataTypeV.get(id.lexemeRank(20))),"T"+temporal);
                                        identDataTemp.put(identDataTypeV.get(id.lexemeRank(14))+" "+identDataTypeV.get(id.lexemeRank(16))+" "+identDataTypeV.get(id.lexemeRank(18))+" "+identDataTypeV.get(id.lexemeRank(20)),"T"+temporal);
                                    }
                                    temporal++;
                                    if(identDataTemp.containsKey(identDataTypeV.get(id.lexemeRank(22))+" "+identDataTypeV.get(id.lexemeRank(24)))){

                                    }else{
                                        Tripletas.append("T"+temporal+" := "+id.lexemeRank(22)+" + "+id.lexemeRank(24)+"\n");
                                        cuadruplos("+",id.lexemeRank(22),id.lexemeRank(24),"T"+temporal);
                                        identDataTemp.put(identDataTypeV.get(id.lexemeRank(22))+" "+identDataTypeV.get(id.lexemeRank(24)),"T"+temporal);
                                    }
                                    temporal++;
                                    if(identDataTemp.containsKey(identDataTypeV.get(id.lexemeRank(6))+" "+identDataTypeV.get(id.lexemeRank(8))+" "+identDataTypeV.get(id.lexemeRank(10))+" "+identDataTypeV.get(id.lexemeRank(12))+" "+identDataTypeV.get(id.lexemeRank(14))+" "+identDataTypeV.get(id.lexemeRank(16))+" "+identDataTypeV.get(id.lexemeRank(18))+" "+identDataTypeV.get(id.lexemeRank(20)))){
                                    
                                    }else{
                                        Tripletas.append("T"+temporal+" := "+identDataTemp.get(identDataTypeV.get(id.lexemeRank(6))+" "+identDataTypeV.get(id.lexemeRank(8))+" "+identDataTypeV.get(id.lexemeRank(10))+" "+identDataTypeV.get(id.lexemeRank(12)))+" + "+identDataTemp.get(identDataTypeV.get(id.lexemeRank(14))+" "+identDataTypeV.get(id.lexemeRank(16))+" "+identDataTypeV.get(id.lexemeRank(18))+" "+identDataTypeV.get(id.lexemeRank(20)))+"\n");
                                        cuadruplos("+",identDataTemp.get(identDataTypeV.get(id.lexemeRank(6))+" "+identDataTypeV.get(id.lexemeRank(8))+" "+identDataTypeV.get(id.lexemeRank(10))+" "+identDataTypeV.get(id.lexemeRank(12))),identDataTemp.get(identDataTypeV.get(id.lexemeRank(14))+" "+identDataTypeV.get(id.lexemeRank(16))+" "+identDataTypeV.get(id.lexemeRank(18))+" "+identDataTypeV.get(id.lexemeRank(20))),"T"+temporal);
                                        identDataTemp.put(identDataTypeV.get(id.lexemeRank(6))+" "+identDataTypeV.get(id.lexemeRank(8))+" "+identDataTypeV.get(id.lexemeRank(10))+" "+identDataTypeV.get(id.lexemeRank(12))+" "+identDataTypeV.get(id.lexemeRank(14))+" "+identDataTypeV.get(id.lexemeRank(16))+" "+identDataTypeV.get(id.lexemeRank(18))+" "+identDataTypeV.get(id.lexemeRank(20)),"T"+temporal);
                                    }
                                    temporal++;
                                    if(identDataTemp.containsKey(identDataTypeV.get(id.lexemeRank(6))+" "+identDataTypeV.get(id.lexemeRank(8))+" "+identDataTypeV.get(id.lexemeRank(10))+" "+identDataTypeV.get(id.lexemeRank(12))+" "+identDataTypeV.get(id.lexemeRank(14))+" "+identDataTypeV.get(id.lexemeRank(16))+" "+identDataTypeV.get(id.lexemeRank(18))+" "+identDataTypeV.get(id.lexemeRank(20))+" "+identDataTypeV.get(id.lexemeRank(22))+" "+identDataTypeV.get(id.lexemeRank(24)))){
                                        Tripletas.append("T"+temporal+" := "+identDataTemp.get(identDataTypeV.get(id.lexemeRank(2))+" "+identDataTypeR.get(id.lexemeRank(4)))+" + "+identDataTemp.get(identDataTypeV.get(id.lexemeRank(6))+" "+identDataTypeV.get(id.lexemeRank(8))+" "+identDataTypeV.get(id.lexemeRank(10))+" "+identDataTypeV.get(id.lexemeRank(12))+" "+identDataTypeV.get(id.lexemeRank(14))+" "+identDataTypeV.get(id.lexemeRank(16))+" "+identDataTypeV.get(id.lexemeRank(18))+" "+identDataTypeV.get(id.lexemeRank(20))+" "+identDataTypeV.get(id.lexemeRank(22))+" "+identDataTypeV.get(id.lexemeRank(24)))+"\n");
                                        cuadruplos("+",identDataTemp.get(identDataTypeV.get(id.lexemeRank(2))+" "+identDataTypeR.get(id.lexemeRank(4))),identDataTemp.get(identDataTypeV.get(id.lexemeRank(6))+" "+identDataTypeV.get(id.lexemeRank(8))+" "+identDataTypeV.get(id.lexemeRank(10))+" "+identDataTypeV.get(id.lexemeRank(12))+" "+identDataTypeV.get(id.lexemeRank(14))+" "+identDataTypeV.get(id.lexemeRank(16))+" "+identDataTypeV.get(id.lexemeRank(18))+" "+identDataTypeV.get(id.lexemeRank(20))+" "+identDataTypeV.get(id.lexemeRank(22))+" "+identDataTypeV.get(id.lexemeRank(24))),"T"+temporal);
                                        identDataTemp.put(identDataTypeV.get(id.lexemeRank(2))+" "+identDataTypeR.get(id.lexemeRank(4))+" "+identDataTypeV.get(id.lexemeRank(6))+" "+identDataTypeV.get(id.lexemeRank(8))+" "+identDataTypeV.get(id.lexemeRank(10))+" "+identDataTypeV.get(id.lexemeRank(12))+" "+identDataTypeV.get(id.lexemeRank(14))+" "+identDataTypeV.get(id.lexemeRank(16))+" "+identDataTypeV.get(id.lexemeRank(18))+" "+identDataTypeV.get(id.lexemeRank(20))+" "+identDataTypeV.get(id.lexemeRank(22))+" "+identDataTypeV.get(id.lexemeRank(24)),"T"+temporal);
                                    }else{
                                        Tripletas.append("T"+temporal+" := "+identDataTemp.get(identDataTypeV.get(id.lexemeRank(6))+" "+identDataTypeV.get(id.lexemeRank(8))+" "+identDataTypeV.get(id.lexemeRank(10))+" "+identDataTypeV.get(id.lexemeRank(12))+" "+identDataTypeV.get(id.lexemeRank(14))+" "+identDataTypeV.get(id.lexemeRank(16))+" "+identDataTypeV.get(id.lexemeRank(18))+" "+identDataTypeV.get(id.lexemeRank(20)))+" + "+identDataTemp.get(identDataTypeV.get(id.lexemeRank(22))+" "+identDataTypeV.get(id.lexemeRank(24)))+"\n");
                                        cuadruplos("+",identDataTemp.get(identDataTypeV.get(id.lexemeRank(6))+" "+identDataTypeV.get(id.lexemeRank(8))+" "+identDataTypeV.get(id.lexemeRank(10))+" "+identDataTypeV.get(id.lexemeRank(12))+" "+identDataTypeV.get(id.lexemeRank(14))+" "+identDataTypeV.get(id.lexemeRank(16))+" "+identDataTypeV.get(id.lexemeRank(18))+" "+identDataTypeV.get(id.lexemeRank(20))),identDataTemp.get(identDataTypeV.get(id.lexemeRank(22))+" "+identDataTypeV.get(id.lexemeRank(24))),"T"+temporal);
                                        identDataTemp.put(identDataTypeV.get(id.lexemeRank(6))+" "+identDataTypeV.get(id.lexemeRank(8))+" "+identDataTypeV.get(id.lexemeRank(10))+" "+identDataTypeV.get(id.lexemeRank(12))+" "+identDataTypeV.get(id.lexemeRank(14))+" "+identDataTypeV.get(id.lexemeRank(16))+" "+identDataTypeV.get(id.lexemeRank(18))+" "+identDataTypeV.get(id.lexemeRank(20))+" "+identDataTypeV.get(id.lexemeRank(22))+" "+identDataTypeV.get(id.lexemeRank(24)),"T"+temporal);
                                        temporal++;
                                        Tripletas.append("T"+temporal+" := "+identDataTemp.get(identDataTypeV.get(id.lexemeRank(2))+" "+identDataTypeR.get(id.lexemeRank(4)))+" + T"+(temporal-1)+"\n");
                                        cuadruplos("+",identDataTemp.get(identDataTypeV.get(id.lexemeRank(2))+" "+identDataTypeR.get(id.lexemeRank(4))),"T"+(temporal-1),"T"+temporal);
                                        identDataTemp.put(identDataTypeV.get(id.lexemeRank(2))+" "+identDataTypeR.get(id.lexemeRank(4))+" "+identDataTypeV.get(id.lexemeRank(6))+" "+identDataTypeV.get(id.lexemeRank(8))+" "+identDataTypeV.get(id.lexemeRank(10))+" "+identDataTypeV.get(id.lexemeRank(12))+" "+identDataTypeV.get(id.lexemeRank(14))+" "+identDataTypeV.get(id.lexemeRank(16))+" "+identDataTypeV.get(id.lexemeRank(18))+" "+identDataTypeV.get(id.lexemeRank(20))+" "+identDataTypeV.get(id.lexemeRank(22))+" "+identDataTypeV.get(id.lexemeRank(24)),"T"+temporal);
                                    }
                                    PanelSalida.append("-->   Se ha utlizado la función de Mostrar en su forma de tipo Decimal, del cual se crea la siguiente interfaz.........\n");
                                    MostrarD md = new MostrarD();
                                    md.setLocationRelativeTo(this);
                                    md.setVisible(true);
                                    md.setCadena(id.lexemeRank(2),identDataTypeV.get(id.lexemeRank(2)).replace("'",""));
                                    md.setResultado(id.lexemeRank(4),identDataTypeR.get(id.lexemeRank(4)));
                                    md.setFigura1(id.lexemeRank(6),identDataTypeV.get(id.lexemeRank(6)));
                                    md.setColor1(id.lexemeRank(8),identDataTypeV.get(id.lexemeRank(8)));
                                    md.setFigura2(id.lexemeRank(10),identDataTypeV.get(id.lexemeRank(10)));
                                    md.setColor2(id.lexemeRank(12),identDataTypeV.get(id.lexemeRank(12)));
                                    md.setFigura3(id.lexemeRank(14),identDataTypeV.get(id.lexemeRank(14)));
                                    md.setColor3(id.lexemeRank(16),identDataTypeV.get(id.lexemeRank(16)));
                                    md.setFigura4(id.lexemeRank(18),identDataTypeV.get(id.lexemeRank(18)));
                                    md.setColor4(id.lexemeRank(20),identDataTypeV.get(id.lexemeRank(20)));
                                    md.setFigura5(id.lexemeRank(22),identDataTypeV.get(id.lexemeRank(22)));
                                    md.setColor5(id.lexemeRank(24),identDataTypeV.get(id.lexemeRank(24)));
                                    md.Figura1();
                                    md.Color1();
                                    md.Figura2();
                                    md.Color2();
                                    md.Figura3();
                                    md.Color3();
                                    md.Figura4();
                                    md.Color4();
                                    md.Figura5();
                                    md.Color5();
                                    md.Cadena();
                                    md.Resultado();
                                }
                                CodigoObjeto.add("    MOV AX,"+id.lexemeRank(2)+"\n"
                                               + "    ADD AX,"+id.lexemeRank(4)+"\n"
                                               + "    MOV BX,"+id.lexemeRank(6)+"\n"
                                               + "    ADD BX,"+id.lexemeRank(8)+"\n"
                                               + "    MOV CX,"+id.lexemeRank(10)+"\n"
                                               + "    ADD CX,"+id.lexemeRank(12)+"\n"
                                               + "    ADD BX,CX\n"
                                               + "    MOV CX,"+id.lexemeRank(14)+"\n"
                                               + "    ADD CX,"+id.lexemeRank(16)+"\n"
                                               + "    MOV DX,"+id.lexemeRank(18)+"\n"
                                               + "    ADD DX,"+id.lexemeRank(20)+"\n"
                                               + "    ADD CX,BX\n"
                                               + "    MOV DX,"+id.lexemeRank(22)+"\n"
                                               + "    ADD DX,"+id.lexemeRank(24)+"\n"
                                               + "    ADD BX,CX\n"
                                               + "    ADD BX,DX\n"
                                               + "    ADD AX,BX\n");
                                break;
                            }
                        }
                        Tr.RellenarTripletas(Tripletas.getText());
                    }
                }
            }
        }
        CodObjeto.append(";***********************************************************\n"
                       + ";*                 LENGUAJES Y AUTOMATAS II                *\n"
                       + ";*                   COMPILADOR - M4THEC                   *\n"
                       + ";*                     DESARROLLADORES:                    *\n"
                       + ";* NL - 14 MARLETT ALESSANDRA MARIN BAUTISTA - SEMESTRE 7  *\n"
                       + ";* NL - 21 JESUS ALBERTO PARTIDA MICHEL      - SEMESTRE 7  *\n"
                       + ";* NL - 22 JOSE DE JESUS RAMIREZ ORTEGA      - SEMESTRE 11 *\n"
                       + ";* NL - 1  MARTIN ERNESTO BARRON DOROTEO     - SEMESTRE 7  *\n"
                       + ";***********************************************************\n"
                       + ".model small\n"
                       + ".stack\n"
                       + ".data\n");
        for(int i = 0; i < Variables.size(); i++){
            CodObjeto.append(Variables.get(i));
        }  
        CodObjeto.append(".code\n"
                       + "Inicio:\n"
                       + "    MOV AX,@DATA\n"
                       + "    MOV DS,AX\n"
                       + "    ;Inicio de las estructuras de Metodos,Asignaciones y Funciones\n");
        for(int i = 0; i < CodigoObjeto.size(); i++){
            CodObjeto.append(CodigoObjeto.get(i));
        }
        CodObjeto.append("    ;Fin de las estructuras de Metodos,Asignaciones y Funciones\n"
                       + "Fin:\n"
                       + "    MOV AX,4C00H\n"
                       + "    INT 21H\n"
                       + "END");
        co.rellenaObjeto(CodObjeto.getText());
    }
    /**
     * @param args the command line arguments
     */
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Abrir;
    private javax.swing.JTextArea CodObjeto;
    private javax.swing.JTable Cuadruplos;
    private javax.swing.JButton Ejecutar;
    private javax.swing.JButton Guardar;
    private javax.swing.JButton GuardarComo;
    private javax.swing.JButton Nuevo;
    private javax.swing.JTextPane PanelFuente;
    private javax.swing.JTextArea PanelSalida;
    private javax.swing.JTable Tokens;
    private javax.swing.JTextArea Tripletas;
    private javax.swing.JButton btnCompilar;
    private javax.swing.JMenuItem btnMenuLEATE;
    private javax.swing.JMenuItem btnmenuAbrir;
    private javax.swing.JMenuItem btnmenuGuardar;
    private javax.swing.JMenuItem btnmenuGuardarComo;
    private javax.swing.JMenuItem btnmenuNuevo;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu12;
    private javax.swing.JMenu jMenu13;
    private javax.swing.JMenu jMenu14;
    private javax.swing.JMenu jMenu15;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JMenuItem jMenuItem25;
    private javax.swing.JMenuItem jMenuItem26;
    private javax.swing.JMenuItem jMenuItem27;
    private javax.swing.JMenuItem jMenuItem28;
    private javax.swing.JMenuItem jMenuItem29;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem30;
    private javax.swing.JMenuItem jMenuItem31;
    private javax.swing.JMenuItem jMenuItem32;
    private javax.swing.JMenuItem jMenuItem33;
    private javax.swing.JMenuItem jMenuItem34;
    private javax.swing.JMenuItem jMenuItem36;
    private javax.swing.JMenuItem jMenuItem37;
    private javax.swing.JMenuItem jMenuItem38;
    private javax.swing.JMenuItem jMenuItem39;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem40;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
