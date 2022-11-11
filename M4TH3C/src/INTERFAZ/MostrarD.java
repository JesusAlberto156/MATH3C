/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package INTERFAZ;

/**
 *
 * @author chuy4
 */
public class MostrarD extends javax.swing.JFrame {
    private String varCadena;
    private String valCadena;
    private String varResultado;
    private String valResultado;
    private String varFigura1;
    private String valFigura1;
    private String varColor1;
    private String valColor1;
    private String varFigura2;
    private String valFigura2;
    private String varColor2;
    private String valColor2;
    private String varFigura3;
    private String valFigura3;
    private String varColor3;
    private String valColor3;
    private String varFigura4;
    private String valFigura4;
    private String varColor4;
    private String valColor4;
    private String varFigura5;
    private String valFigura5;
    private String varColor5;
    private String valColor5;
    /**
     * Creates new form MostrarD
     */
    public MostrarD() {
        initComponents();
        this.setTitle("Mostrar (Decimal) - M4TH3C");
        this.setResizable(false);
    }
    //CADENA
    public void setCadena(String varc,String valc){
        this.varCadena = varc;
        this.valCadena = valc;
    }
    public void Cadena(){
        Cadena.append("---> Variable: "+varCadena+"\n\n--->Valor: "+valCadena);
    }
    //CADENA
    //RESULTADO
    public void setResultado(String varr,String valr){
        this.varResultado = varr;
        this.valResultado = valr;
    }
    public void Resultado(){
        if(valResultado.contains("-")){
            if(Character.toString(valResultado.charAt(4)).equals(".")){
                E.setText(Character.toString(valResultado.charAt(0)));
                DE3.setText(Character.toString(valResultado.charAt(1)));
                DE2.setText(Character.toString(valResultado.charAt(2)));
                DE1.setText(Character.toString(valResultado.charAt(3)));
                P.setText(Character.toString(valResultado.charAt(4)));
                DD1.setText(Character.toString(valResultado.charAt(5)));
                DD2.setText(Character.toString(valResultado.charAt(6)));
                DD3.setText(Character.toString(valResultado.charAt(7)));
                DD4.setText(Character.toString(valResultado.charAt(8)));
            }if(Character.toString(valResultado.charAt(3)).equals(".")){
                E.setText(Character.toString(valResultado.charAt(0)));
                DE2.setText(Character.toString(valResultado.charAt(1)));
                DE1.setText(Character.toString(valResultado.charAt(2)));
                P.setText(Character.toString(valResultado.charAt(3)));
                DD1.setText(Character.toString(valResultado.charAt(4)));
                DD2.setText(Character.toString(valResultado.charAt(5)));
                DD3.setText(Character.toString(valResultado.charAt(6)));
                DD4.setText(Character.toString(valResultado.charAt(7)));
            }if(Character.toString(valResultado.charAt(2)).equals(".")){
                E.setText(Character.toString(valResultado.charAt(0)));
                DE1.setText(Character.toString(valResultado.charAt(1)));
                P.setText(Character.toString(valResultado.charAt(2)));
                DD1.setText(Character.toString(valResultado.charAt(3)));
                DD2.setText(Character.toString(valResultado.charAt(4)));
                DD3.setText(Character.toString(valResultado.charAt(5)));
                DD4.setText(Character.toString(valResultado.charAt(6)));
            }if(Character.toString(valResultado.charAt(1)).equals(".")){
                E.setText(Character.toString(valResultado.charAt(0)));
                P.setText(Character.toString(valResultado.charAt(1)));
                DD1.setText(Character.toString(valResultado.charAt(2)));
                DD2.setText(Character.toString(valResultado.charAt(3)));
                DD3.setText(Character.toString(valResultado.charAt(4)));
                DD4.setText(Character.toString(valResultado.charAt(5)));
            }if(Character.toString(valResultado.charAt(4)).equals(".")){
                E.setText(Character.toString(valResultado.charAt(0)));
                DE3.setText(Character.toString(valResultado.charAt(1)));
                DE2.setText(Character.toString(valResultado.charAt(2)));
                DE1.setText(Character.toString(valResultado.charAt(3)));
                P.setText(Character.toString(valResultado.charAt(4)));
                DD1.setText(Character.toString(valResultado.charAt(5)));
                DD2.setText(Character.toString(valResultado.charAt(6)));
                DD3.setText(Character.toString(valResultado.charAt(7)));
            }if(Character.toString(valResultado.charAt(4)).equals(".")){
                E.setText(Character.toString(valResultado.charAt(0)));
                DE3.setText(Character.toString(valResultado.charAt(1)));
                DE2.setText(Character.toString(valResultado.charAt(2)));
                DE1.setText(Character.toString(valResultado.charAt(3)));
                P.setText(Character.toString(valResultado.charAt(4)));
                DD1.setText(Character.toString(valResultado.charAt(5)));
                DD2.setText(Character.toString(valResultado.charAt(6)));
            }if(Character.toString(valResultado.charAt(4)).equals(".")){
                E.setText(Character.toString(valResultado.charAt(0)));
                DE3.setText(Character.toString(valResultado.charAt(1)));
                DE2.setText(Character.toString(valResultado.charAt(2)));
                DE1.setText(Character.toString(valResultado.charAt(3)));
                P.setText(Character.toString(valResultado.charAt(4)));
                DD1.setText(Character.toString(valResultado.charAt(5)));
            }if(Character.toString(valResultado.charAt(3)).equals(".")){
                E.setText(Character.toString(valResultado.charAt(0)));
                DE2.setText(Character.toString(valResultado.charAt(1)));
                DE1.setText(Character.toString(valResultado.charAt(2)));
                P.setText(Character.toString(valResultado.charAt(3)));
                DD1.setText(Character.toString(valResultado.charAt(4)));
                DD2.setText(Character.toString(valResultado.charAt(5)));
                DD3.setText(Character.toString(valResultado.charAt(6)));
            }if(Character.toString(valResultado.charAt(3)).equals(".")){
                E.setText(Character.toString(valResultado.charAt(0)));
                DE2.setText(Character.toString(valResultado.charAt(1)));
                DE1.setText(Character.toString(valResultado.charAt(2)));
                P.setText(Character.toString(valResultado.charAt(3)));
                DD1.setText(Character.toString(valResultado.charAt(4)));
                DD2.setText(Character.toString(valResultado.charAt(5)));
            }if(Character.toString(valResultado.charAt(3)).equals(".")){
                E.setText(Character.toString(valResultado.charAt(0)));
                DE2.setText(Character.toString(valResultado.charAt(1)));
                DE1.setText(Character.toString(valResultado.charAt(2)));
                P.setText(Character.toString(valResultado.charAt(3)));
                DD1.setText(Character.toString(valResultado.charAt(4)));
            }if(Character.toString(valResultado.charAt(2)).equals(".")){
                E.setText(Character.toString(valResultado.charAt(0)));
                DE1.setText(Character.toString(valResultado.charAt(1)));
                P.setText(Character.toString(valResultado.charAt(2)));
                DD1.setText(Character.toString(valResultado.charAt(3)));
                DD2.setText(Character.toString(valResultado.charAt(4)));
                DD3.setText(Character.toString(valResultado.charAt(5)));
            }if(Character.toString(valResultado.charAt(2)).equals(".")){
                E.setText(Character.toString(valResultado.charAt(0)));
                DE1.setText(Character.toString(valResultado.charAt(1)));
                P.setText(Character.toString(valResultado.charAt(2)));
                DD1.setText(Character.toString(valResultado.charAt(3)));
                DD2.setText(Character.toString(valResultado.charAt(4)));
            }if(Character.toString(valResultado.charAt(2)).equals(".")){
                E.setText(Character.toString(valResultado.charAt(0)));
                DE1.setText(Character.toString(valResultado.charAt(1)));
                P.setText(Character.toString(valResultado.charAt(2)));
                DD1.setText(Character.toString(valResultado.charAt(3)));
            }if(Character.toString(valResultado.charAt(1)).equals(".")){
                E.setText(Character.toString(valResultado.charAt(0)));
                P.setText(Character.toString(valResultado.charAt(1)));
                DD1.setText(Character.toString(valResultado.charAt(2)));
                DD2.setText(Character.toString(valResultado.charAt(3)));
                DD3.setText(Character.toString(valResultado.charAt(4)));
            }if(Character.toString(valResultado.charAt(1)).equals(".")){
                E.setText(Character.toString(valResultado.charAt(0)));
                P.setText(Character.toString(valResultado.charAt(1)));
                DD1.setText(Character.toString(valResultado.charAt(2)));
                DD2.setText(Character.toString(valResultado.charAt(3)));
            }if(Character.toString(valResultado.charAt(1)).equals(".")){
                E.setText(Character.toString(valResultado.charAt(0)));
                P.setText(Character.toString(valResultado.charAt(1)));
                DD1.setText(Character.toString(valResultado.charAt(2)));
            }
        }else{
            if(Character.toString(valResultado.charAt(3)).equals(".")){
                E.setText("+");
                DE3.setText(Character.toString(valResultado.charAt(0)));
                DE2.setText(Character.toString(valResultado.charAt(1)));
                DE1.setText(Character.toString(valResultado.charAt(2)));
                P.setText(Character.toString(valResultado.charAt(3)));
                DD1.setText(Character.toString(valResultado.charAt(4)));
                DD2.setText(Character.toString(valResultado.charAt(5)));
                DD3.setText(Character.toString(valResultado.charAt(6)));
                DD4.setText(Character.toString(valResultado.charAt(7)));
            }if(Character.toString(valResultado.charAt(2)).equals(".")){
                E.setText("+");
                DE2.setText(Character.toString(valResultado.charAt(0)));
                DE1.setText(Character.toString(valResultado.charAt(1)));
                P.setText(Character.toString(valResultado.charAt(2)));
                DD1.setText(Character.toString(valResultado.charAt(3)));
                DD2.setText(Character.toString(valResultado.charAt(4)));
                DD3.setText(Character.toString(valResultado.charAt(5)));
                DD4.setText(Character.toString(valResultado.charAt(6)));
            }if(Character.toString(valResultado.charAt(1)).equals(".")){
                E.setText("+");
                DE1.setText(Character.toString(valResultado.charAt(0)));
                P.setText(Character.toString(valResultado.charAt(1)));
                DD1.setText(Character.toString(valResultado.charAt(2)));
                DD2.setText(Character.toString(valResultado.charAt(3)));
                DD3.setText(Character.toString(valResultado.charAt(4)));
                DD4.setText(Character.toString(valResultado.charAt(5)));
            }if(Character.toString(valResultado.charAt(0)).equals(".")){
                E.setText("+");
                P.setText(Character.toString(valResultado.charAt(0)));
                DD1.setText(Character.toString(valResultado.charAt(1)));
                DD2.setText(Character.toString(valResultado.charAt(2)));
                DD3.setText(Character.toString(valResultado.charAt(3)));
                DD4.setText(Character.toString(valResultado.charAt(4)));
            }if(Character.toString(valResultado.charAt(3)).equals(".")){
                E.setText("+");
                DE3.setText(Character.toString(valResultado.charAt(0)));
                DE2.setText(Character.toString(valResultado.charAt(1)));
                DE1.setText(Character.toString(valResultado.charAt(2)));
                P.setText(Character.toString(valResultado.charAt(3)));
                DD1.setText(Character.toString(valResultado.charAt(4)));
                DD2.setText(Character.toString(valResultado.charAt(5)));
                DD3.setText(Character.toString(valResultado.charAt(6)));
            }if(Character.toString(valResultado.charAt(3)).equals(".")){
                E.setText("+");
                DE3.setText(Character.toString(valResultado.charAt(0)));
                DE2.setText(Character.toString(valResultado.charAt(1)));
                DE1.setText(Character.toString(valResultado.charAt(2)));
                P.setText(Character.toString(valResultado.charAt(3)));
                DD1.setText(Character.toString(valResultado.charAt(4)));
                DD2.setText(Character.toString(valResultado.charAt(5)));
            }if(Character.toString(valResultado.charAt(3)).equals(".")){
                E.setText("+");
                DE3.setText(Character.toString(valResultado.charAt(0)));
                DE2.setText(Character.toString(valResultado.charAt(1)));
                DE1.setText(Character.toString(valResultado.charAt(2)));
                P.setText(Character.toString(valResultado.charAt(3)));
                DD1.setText(Character.toString(valResultado.charAt(4)));
            }if(Character.toString(valResultado.charAt(2)).equals(".")){
                E.setText("+");
                DE2.setText(Character.toString(valResultado.charAt(0)));
                DE1.setText(Character.toString(valResultado.charAt(1)));
                P.setText(Character.toString(valResultado.charAt(2)));
                DD1.setText(Character.toString(valResultado.charAt(3)));
                DD2.setText(Character.toString(valResultado.charAt(4)));
                DD3.setText(Character.toString(valResultado.charAt(5)));
            }if(Character.toString(valResultado.charAt(2)).equals(".")){
                E.setText("+");
                DE2.setText(Character.toString(valResultado.charAt(0)));
                DE1.setText(Character.toString(valResultado.charAt(1)));
                P.setText(Character.toString(valResultado.charAt(2)));
                DD1.setText(Character.toString(valResultado.charAt(3)));
                DD2.setText(Character.toString(valResultado.charAt(4)));
            }if(Character.toString(valResultado.charAt(2)).equals(".")){
                E.setText("+");
                DE2.setText(Character.toString(valResultado.charAt(0)));
                DE1.setText(Character.toString(valResultado.charAt(1)));
                P.setText(Character.toString(valResultado.charAt(2)));
                DD1.setText(Character.toString(valResultado.charAt(3)));
            }if(Character.toString(valResultado.charAt(1)).equals(".")){
                E.setText("+");
                DE1.setText(Character.toString(valResultado.charAt(0)));
                P.setText(Character.toString(valResultado.charAt(1)));
                DD1.setText(Character.toString(valResultado.charAt(2)));
                DD2.setText(Character.toString(valResultado.charAt(3)));
                DD3.setText(Character.toString(valResultado.charAt(4)));
            }if(Character.toString(valResultado.charAt(1)).equals(".")){
                E.setText("+");
                DE1.setText(Character.toString(valResultado.charAt(0)));
                P.setText(Character.toString(valResultado.charAt(1)));
                DD1.setText(Character.toString(valResultado.charAt(2)));
                DD2.setText(Character.toString(valResultado.charAt(3)));
            }if(Character.toString(valResultado.charAt(1)).equals(".")){
                E.setText("+");
                DE1.setText(Character.toString(valResultado.charAt(0)));
                P.setText(Character.toString(valResultado.charAt(1)));
                DD1.setText(Character.toString(valResultado.charAt(2)));
            }if(Character.toString(valResultado.charAt(0)).equals(".")){
                E.setText("+");
                P.setText(Character.toString(valResultado.charAt(0)));
                DD1.setText(Character.toString(valResultado.charAt(1)));
                DD2.setText(Character.toString(valResultado.charAt(2)));
                DD3.setText(Character.toString(valResultado.charAt(3)));
            }if(Character.toString(valResultado.charAt(0)).equals(".")){
                E.setText("+");
                P.setText(Character.toString(valResultado.charAt(0)));
                DD1.setText(Character.toString(valResultado.charAt(1)));
                DD2.setText(Character.toString(valResultado.charAt(2)));
            }if(Character.toString(valResultado.charAt(0)).equals(".")){
                E.setText("+");
                P.setText(Character.toString(valResultado.charAt(0)));
                DD1.setText(Character.toString(valResultado.charAt(1)));
            }
        }
    }
    //RESULTADO
    //FIGURA
    public void setFigura1(String varf,String valf){
        this.varFigura1 = varf;
        this.valFigura1 = valf;
    }
    public void Figura1(){
        Figura.append(" Dígitos Enteros ---> Variable: "+varFigura1+" ---> Valor: "+valFigura1+"\n");
    }
    public void setFigura2(String varf,String valf){
        this.varFigura2 = varf;
        this.valFigura2 = valf;
    }
    public void Figura2(){
        Figura.append(" Dígito Decimal 1 ---> Variable: "+varFigura2+" ---> Valor: "+valFigura2+"\n");
    }
    public void setFigura3(String varf,String valf){
        this.varFigura3 = varf;
        this.valFigura3 = valf;
    }
    public void Figura3(){
        Figura.append("  Dígito Decimal 2 ---> Variable: "+varFigura3+" ---> Valor: "+valFigura3+"\n");
    }
    public void setFigura4 (String varf,String valf){
        this.varFigura4 = varf;
        this.valFigura4 = valf;
    }
    public void Figura4(){
        Figura.append(" Dígito Decimal 3 ---> Variable: "+varFigura4+" ---> Valor: "+valFigura4+"\n");
    }
    public void setFigura5(String varf,String valf){
        this.varFigura5 = varf;
        this.valFigura5 = valf;
    }
    public void Figura5(){
        Figura.append(" Dígito Decimal 4 ---> Variable: "+varFigura5+" ---> Valor: "+valFigura5+"\n");
    }
    //FIGURA
    //COLOR
    public void setColor1(String varc,String valc){
        this.varColor1 = varc;
        this.valColor1 = valc;
    }
    public void Color1(){
        Color.append(" Dígitos Enteros ---> Variable: "+varColor1+" ---> Valor: "+valColor1+"\n");
    }
    public void setColor2(String varc,String valc){
        this.varColor2 = varc;
        this.valColor2 = valc;
    }
    public void Color2(){
        Color.append(" Dígito Decimal 1 ---> Variable: "+varColor2+" ---> Valor: "+valColor2+"\n");
    }
    public void setColor3(String varc,String valc){
        this.varColor3 = varc;
        this.valColor3 = valc;
    }
    public void Color3(){
        Color.append(" Dígito Decimal 2 ---> Variable: "+varColor3+" ---> Valor: "+valColor3+"\n");
    }
    public void setColor4(String varc,String valc){
        this.varColor4 = varc;
        this.valColor4 = valc;
    }
    public void Color4(){
        Color.append(" Dígito Decimal 3 ---> Variable: "+varColor4+" ---> Valor: "+valColor4+"\n");
    }
    public void setColor5(String varc,String valc){
        this.varColor5 = varc;
        this.valColor5 = valc;
    }
    public void Color5(){
        Color.append(" Dígito Decimal 4 ---> Variable: "+varColor5+" ---> Valor: "+valColor5+"\n");
    }
    //COLOR
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        Cadena = new javax.swing.JTextArea();
        jPanel10 = new javax.swing.JPanel();
        jTextField4 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        Color = new javax.swing.JTextArea();
        jPanel11 = new javax.swing.JPanel();
        jTextField3 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        Figura = new javax.swing.JTextArea();
        jPanel12 = new javax.swing.JPanel();
        jTextField5 = new javax.swing.JTextField();
        E = new javax.swing.JTextField();
        DE1 = new javax.swing.JTextField();
        P = new javax.swing.JTextField();
        DD1 = new javax.swing.JTextField();
        DD2 = new javax.swing.JTextField();
        DD3 = new javax.swing.JTextField();
        DD4 = new javax.swing.JTextField();
        F11 = new javax.swing.JPanel();
        F12 = new javax.swing.JPanel();
        F2 = new javax.swing.JPanel();
        F3 = new javax.swing.JPanel();
        F4 = new javax.swing.JPanel();
        F5 = new javax.swing.JPanel();
        DE3 = new javax.swing.JTextField();
        DE2 = new javax.swing.JTextField();
        F13 = new javax.swing.JPanel();
        btnDibujarD = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Mostrar (Decimal)");

        jPanel1.setBackground(new java.awt.Color(0, 0, 153));

        jPanel2.setBackground(new java.awt.Color(153, 51, 255));

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(255, 255, 255));
        jTextField1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("INTERFAZ GENERADA POR LA FUNCIÓN DE MOSTRAR");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(153, 51, 255));

        jPanel9.setBackground(new java.awt.Color(0, 0, 153));

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(255, 255, 255));
        jTextField2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setText("CADENA");

        Cadena.setEditable(false);
        Cadena.setBackground(new java.awt.Color(255, 255, 255));
        Cadena.setColumns(20);
        Cadena.setRows(4);
        jScrollPane3.setViewportView(Cadena);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField2)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(0, 0, 153));

        jTextField4.setEditable(false);
        jTextField4.setBackground(new java.awt.Color(255, 255, 255));
        jTextField4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTextField4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField4.setText("COLORES");

        Color.setEditable(false);
        Color.setBackground(new java.awt.Color(255, 255, 255));
        Color.setColumns(20);
        Color.setRows(4);
        jScrollPane2.setViewportView(Color);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField4)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2)
                .addContainerGap())
        );

        jPanel11.setBackground(new java.awt.Color(0, 0, 153));

        jTextField3.setEditable(false);
        jTextField3.setBackground(new java.awt.Color(255, 255, 255));
        jTextField3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField3.setText("FIGURAS");

        Figura.setEditable(false);
        Figura.setBackground(new java.awt.Color(255, 255, 255));
        Figura.setColumns(20);
        Figura.setRows(4);
        jScrollPane1.setViewportView(Figura);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField3)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel12.setBackground(new java.awt.Color(153, 51, 255));

        jTextField5.setEditable(false);
        jTextField5.setBackground(new java.awt.Color(255, 255, 255));
        jTextField5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTextField5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField5.setText("RESULTADO");

        E.setEditable(false);
        E.setBackground(new java.awt.Color(255, 255, 255));

        DE1.setEditable(false);
        DE1.setBackground(new java.awt.Color(255, 255, 255));

        P.setEditable(false);
        P.setBackground(new java.awt.Color(255, 255, 255));

        DD1.setEditable(false);
        DD1.setBackground(new java.awt.Color(255, 255, 255));

        DD2.setEditable(false);
        DD2.setBackground(new java.awt.Color(255, 255, 255));

        DD3.setEditable(false);
        DD3.setBackground(new java.awt.Color(255, 255, 255));

        DD4.setEditable(false);
        DD4.setBackground(new java.awt.Color(255, 255, 255));

        F11.setBackground(new java.awt.Color(155, 170, 196));

        javax.swing.GroupLayout F11Layout = new javax.swing.GroupLayout(F11);
        F11.setLayout(F11Layout);
        F11Layout.setHorizontalGroup(
            F11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 95, Short.MAX_VALUE)
        );
        F11Layout.setVerticalGroup(
            F11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        F12.setBackground(new java.awt.Color(155, 170, 196));

        javax.swing.GroupLayout F12Layout = new javax.swing.GroupLayout(F12);
        F12.setLayout(F12Layout);
        F12Layout.setHorizontalGroup(
            F12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 95, Short.MAX_VALUE)
        );
        F12Layout.setVerticalGroup(
            F12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        F2.setBackground(new java.awt.Color(155, 170, 196));

        javax.swing.GroupLayout F2Layout = new javax.swing.GroupLayout(F2);
        F2.setLayout(F2Layout);
        F2Layout.setHorizontalGroup(
            F2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 95, Short.MAX_VALUE)
        );
        F2Layout.setVerticalGroup(
            F2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        F3.setBackground(new java.awt.Color(155, 170, 196));

        javax.swing.GroupLayout F3Layout = new javax.swing.GroupLayout(F3);
        F3.setLayout(F3Layout);
        F3Layout.setHorizontalGroup(
            F3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 95, Short.MAX_VALUE)
        );
        F3Layout.setVerticalGroup(
            F3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        F4.setBackground(new java.awt.Color(155, 170, 196));

        javax.swing.GroupLayout F4Layout = new javax.swing.GroupLayout(F4);
        F4.setLayout(F4Layout);
        F4Layout.setHorizontalGroup(
            F4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 95, Short.MAX_VALUE)
        );
        F4Layout.setVerticalGroup(
            F4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        F5.setBackground(new java.awt.Color(155, 170, 196));

        javax.swing.GroupLayout F5Layout = new javax.swing.GroupLayout(F5);
        F5.setLayout(F5Layout);
        F5Layout.setHorizontalGroup(
            F5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        F5Layout.setVerticalGroup(
            F5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        DE3.setEditable(false);
        DE3.setBackground(new java.awt.Color(255, 255, 255));

        DE2.setEditable(false);
        DE2.setBackground(new java.awt.Color(255, 255, 255));

        F13.setBackground(new java.awt.Color(155, 170, 196));

        javax.swing.GroupLayout F13Layout = new javax.swing.GroupLayout(F13);
        F13.setLayout(F13Layout);
        F13Layout.setHorizontalGroup(
            F13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 95, Short.MAX_VALUE)
        );
        F13Layout.setVerticalGroup(
            F13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        btnDibujarD.setText("Dibujar");
        btnDibujarD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDibujarDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField5)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnDibujarD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(E, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                                .addComponent(DE3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(DE2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(DE1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(P, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                                .addComponent(F13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(F12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(F11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(107, 107, 107)))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(DD1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(F2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(DD2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(F3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(DD3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(F4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(DD4, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                            .addComponent(F5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DD1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(E, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(DE1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(DD2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(DD3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(DD4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(P, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(DE3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(DE2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(F4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(F3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(F11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(F12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(F2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(F5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(F13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(btnDibujarD)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDibujarDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDibujarDActionPerformed
        if(valFigura1.equals("Cuadrado")){
            int y1 = 5;
            int y2 = 55;
            if(!DE1.getText().equals("")){
                for(int i = 0; i < Integer.parseInt(DE1.getText());i++){
                    Dibujar.Cuadrado(F11.getGraphics(),valColor1,y1,y2);
                    y1 += 55;
                    y2 += 55;
                }
            }
            y1 = 5;
            y2 = 55;
            if(!DE2.getText().equals("")){
                for(int i = 0; i < Integer.parseInt(DE2.getText());i++){
                    Dibujar.Cuadrado(F12.getGraphics(),valColor1,y1,y2);
                    y1 += 55;
                    y2 += 55;
                }
            }
            y1 = 5;
            y2 = 55;
            if(!DE3.getText().equals("")){
                for(int i = 0; i < Integer.parseInt(DE3.getText());i++){
                    Dibujar.Cuadrado(F13.getGraphics(),valColor1,y1,y2);
                    y1 += 55;
                    y2 += 55;
                }
            }
        }else if(valFigura1.equals("Triangulo")){
            int y1 = 5;
            int y2 = 55;
            if(!DE1.getText().equals("")){
                for(int i = 0; i < Integer.parseInt(DE1.getText());i++){
                    Dibujar.Triangulo(F11.getGraphics(),valColor1,y1,y2);
                    y1 += 55;
                    y2 += 55;
                }
            }
            y1 = 5;
            y2 = 55;
            if(!DE2.getText().equals("")){
                for(int i = 0; i < Integer.parseInt(DE2.getText());i++){
                    Dibujar.Triangulo(F12.getGraphics(),valColor1,y1,y2);
                    y1 += 55;
                    y2 += 55;
                }
            }
            y1 = 5;
            y2 = 55;
            if(!DE3.getText().equals("")){
                for(int i = 0; i < Integer.parseInt(DE3.getText());i++){
                    Dibujar.Triangulo(F13.getGraphics(),valColor1,y1,y2);
                    y1 += 55;
                    y2 += 55;
                }
            }
        }else if(valFigura1.equals("Rectangulo")){
            int y1 = 15;
            int y2 = 45;
            if(!DE1.getText().equals("")){
                for(int i = 0; i < Integer.parseInt(DE1.getText());i++){
                    Dibujar.Rectangulo(F11.getGraphics(),valColor1,y1,y2);
                    y1 += 55;
                    y2 += 55;
                }
            }
            y1 = 15;
            y2 = 45;
            if(!DE2.getText().equals("")){
                for(int i = 0; i < Integer.parseInt(DE2.getText());i++){
                    Dibujar.Rectangulo(F12.getGraphics(),valColor1,y1,y2);
                    y1 += 55;
                    y2 += 55;
                }
            }
            y1 = 15;
            y2 = 45;
            if(!DE3.getText().equals("")){
                for(int i = 0; i < Integer.parseInt(DE3.getText());i++){
                    Dibujar.Rectangulo(F13.getGraphics(),valColor1,y1,y2);
                    y1 += 55;
                    y2 += 55;
                }
            }
        }else if(valFigura1.equals("Rombo")){
            int y1 = 5;
            int y2 = 30;
            int y3 = 55;
            if(!DE1.getText().equals("")){
                for(int i = 0; i < Integer.parseInt(DE1.getText());i++){
                    Dibujar.Rombo(F11.getGraphics(),valColor1,y1,y2,y3);
                    y1 += 55;
                    y2 += 55;
                    y3 += 55;
                }
            }
            y1 = 5;
            y2 = 30;
            y3 = 55;
            if(!DE2.getText().equals("")){
                for(int i = 0; i < Integer.parseInt(DE2.getText());i++){
                    Dibujar.Rombo(F12.getGraphics(),valColor1,y1,y2,y3);
                    y1 += 55;
                    y2 += 55;
                    y3 += 55;
                }
            }
            y1 = 5;
            y2 = 30;
            y3 = 55;
            if(!DE3.getText().equals("")){
                for(int i = 0; i < Integer.parseInt(DE3.getText());i++){
                    Dibujar.Rombo(F13.getGraphics(),valColor1,y1,y2,y3);
                    y1 += 55;
                    y2 += 55;
                    y3 += 55;
                }
            }
        }else if(valFigura1.equals("Circulo")){
            int y1 = 5;
            if(!DE1.getText().equals("")){
                for(int i = 0; i < Integer.parseInt(DE1.getText());i++){
                    Dibujar.Circulo(F11.getGraphics(),valColor1,y1);
                    y1 += 55;
                }
            }
            y1 = 5;
            if(!DE2.getText().equals("")){
                for(int i = 0; i < Integer.parseInt(DE2.getText());i++){
                    Dibujar.Circulo(F12.getGraphics(),valColor1,y1);
                    y1 += 55;
                }
            }
            y1 = 5;
            if(!DE3.getText().equals("")){
                for(int i = 0; i < Integer.parseInt(DE3.getText());i++){
                    Dibujar.Circulo(F13.getGraphics(),valColor1,y1);
                    y1 += 55;
                }
            }
        }
        if(valFigura2.equals("Cuadrado")){
            int y1 = 5;
            int y2 = 55;
            if(!DD1.getText().equals("")){
                for(int i = 0; i < Integer.parseInt(DD1.getText());i++){
                    Dibujar.Cuadrado(F2.getGraphics(),valColor2,y1,y2);
                    y1 += 55;
                    y2 += 55;
                }
            }
        }else if(valFigura2.equals("Triangulo")){
            int y1 = 5;
            int y2 = 55;
            if(!DD1.getText().equals("")){
                for(int i = 0; i < Integer.parseInt(DD1.getText());i++){
                    Dibujar.Triangulo(F2.getGraphics(),valColor2,y1,y2);
                    y1 += 55;
                    y2 += 55;
                }
            }
        }else if(valFigura2.equals("Rectangulo")){
            int y1 = 15;
            int y2 = 45;
            if(!DD1.getText().equals("")){
                for(int i = 0; i < Integer.parseInt(DD1.getText());i++){
                    Dibujar.Rectangulo(F2.getGraphics(),valColor2,y1,y2);
                    y1 += 55;
                    y2 += 55;
                }
            }
        }else if(valFigura2.equals("Rombo")){
            int y1 = 5;
            int y2 = 30;
            int y3 = 55;
            if(!DD1.getText().equals("")){
                for(int i = 0; i < Integer.parseInt(DD1.getText());i++){
                    Dibujar.Rombo(F2.getGraphics(),valColor2,y1,y2,y3);
                    y1 += 55;
                    y2 += 55;
                    y3 += 55;
                }
            }
        }else if(valFigura2.equals("Circulo")){
            int y1 = 5;
            if(!DD1.getText().equals("")){
                for(int i = 0; i < Integer.parseInt(DD1.getText());i++){
                    Dibujar.Circulo(F2.getGraphics(),valColor2,y1);
                    y1 += 55;
                }
            }
        }
        if(valFigura3.equals("Cuadrado")){
            int y1 = 5;
            int y2 = 55;
            if(!DD2.getText().equals("")){
                for(int i = 0; i < Integer.parseInt(DD2.getText());i++){
                    Dibujar.Cuadrado(F3.getGraphics(),valColor3,y1,y2);
                    y1 += 55;
                    y2 += 55;
                }
            }
        }else if(valFigura3.equals("Triangulo")){
            int y1 = 5;
            int y2 = 55;
            if(!DD2.getText().equals("")){
                for(int i = 0; i < Integer.parseInt(DD2.getText());i++){
                    Dibujar.Triangulo(F3.getGraphics(),valColor3,y1,y2);
                    y1 += 55;
                    y2 += 55;
                }
            }
        }else if(valFigura3.equals("Rectangulo")){
            int y1 = 15;
            int y2 = 45;
            if(!DD2.getText().equals("")){
                for(int i = 0; i < Integer.parseInt(DD2.getText());i++){
                    Dibujar.Rectangulo(F3.getGraphics(),valColor3,y1,y2);
                    y1 += 55;
                    y2 += 55;
                }
            }
        }else if(valFigura3.equals("Rombo")){
            int y1 = 5;
            int y2 = 30;
            int y3 = 55;
            if(!DD2.getText().equals("")){
                for(int i = 0; i < Integer.parseInt(DD2.getText());i++){
                    Dibujar.Rombo(F3.getGraphics(),valColor3,y1,y2,y3);
                    y1 += 55;
                    y2 += 55;
                    y3 += 55;
                }
            }
        }else if(valFigura3.equals("Circulo")){
            int y1 = 5;
            if(!DD2.getText().equals("")){
                for(int i = 0; i < Integer.parseInt(DD2.getText());i++){
                    Dibujar.Circulo(F3.getGraphics(),valColor3,y1);
                    y1 += 55;
                }
            }
        }
        if(valFigura4.equals("Cuadrado")){
            int y1 = 5;
            int y2 = 55;
            if(!DD3.getText().equals("")){
                for(int i = 0; i < Integer.parseInt(DD3.getText());i++){
                    Dibujar.Cuadrado(F4.getGraphics(),valColor4,y1,y2);
                    y1 += 55;
                    y2 += 55;
                }
            }
        }else if(valFigura4.equals("Triangulo")){
            int y1 = 5;
            int y2 = 55;
            if(!DD3.getText().equals("")){
                for(int i = 0; i < Integer.parseInt(DD3.getText());i++){
                    Dibujar.Triangulo(F4.getGraphics(),valColor4,y1,y2);
                    y1 += 55;
                    y2 += 55;
                }
            }
        }else if(valFigura4.equals("Rectangulo")){
            int y1 = 15;
            int y2 = 45;
            if(!DD3.getText().equals("")){
                for(int i = 0; i < Integer.parseInt(DD3.getText());i++){
                    Dibujar.Rectangulo(F4.getGraphics(),valColor4,y1,y2);
                    y1 += 55;
                    y2 += 55;
                }
            }
        }else if(valFigura4.equals("Rombo")){
            int y1 = 5;
            int y2 = 30;
            int y3 = 55;
            if(!DD3.getText().equals("")){
                for(int i = 0; i < Integer.parseInt(DD3.getText());i++){
                    Dibujar.Rombo(F4.getGraphics(),valColor4,y1,y2,y3);
                    y1 += 55;
                    y2 += 55;
                    y3 += 55;
                }
            }
        }else if(valFigura4.equals("Circulo")){
            int y1 = 5;
            if(!DD3.getText().equals("")){
                for(int i = 0; i < Integer.parseInt(DD3.getText());i++){
                    Dibujar.Circulo(F4.getGraphics(),valColor4,y1);
                    y1 += 55;
                }
            }
        }
        if(valFigura5.equals("Cuadrado")){
            int y1 = 5;
            int y2 = 55;
            if(!DD4.getText().equals("")){
                for(int i = 0; i < Integer.parseInt(DD4.getText());i++){
                    Dibujar.Cuadrado(F5.getGraphics(),valColor5,y1,y2);
                    y1 += 55;
                    y2 += 55;
                }
            }
        }else if(valFigura5.equals("Triangulo")){
            int y1 = 5;
            int y2 = 55;
            if(!DD4.getText().equals("")){
                for(int i = 0; i < Integer.parseInt(DD4.getText());i++){
                    Dibujar.Triangulo(F5.getGraphics(),valColor5,y1,y2);
                    y1 += 55;
                    y2 += 55;
                }
            }
        }else if(valFigura5.equals("Rectangulo")){
            int y1 = 15;
            int y2 = 45;
            if(!DD4.getText().equals("")){
                for(int i = 0; i < Integer.parseInt(DD4.getText());i++){
                    Dibujar.Rectangulo(F5.getGraphics(),valColor5,y1,y2);
                    y1 += 55;
                    y2 += 55;
                }
            }
        }else if(valFigura5.equals("Rombo")){
            int y1 = 5;
            int y2 = 30;
            int y3 = 55;
            if(!DD4.getText().equals("")){
                for(int i = 0; i < Integer.parseInt(DD4.getText());i++){
                    Dibujar.Rombo(F5.getGraphics(),valColor5,y1,y2,y3);
                    y1 += 55;
                    y2 += 55;
                    y3 += 55;
                }
            }
        }else if(valFigura5.equals("Circulo")){
            int y1 = 5;
            if(!DD4.getText().equals("")){
                for(int i = 0; i < Integer.parseInt(DD4.getText());i++){
                    Dibujar.Circulo(F5.getGraphics(),valColor5,y1);
                    y1 += 55;
                }
            }
        }
    }//GEN-LAST:event_btnDibujarDActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MostrarD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MostrarD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MostrarD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MostrarD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MostrarD().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea Cadena;
    private javax.swing.JTextArea Color;
    private javax.swing.JTextField DD1;
    private javax.swing.JTextField DD2;
    private javax.swing.JTextField DD3;
    private javax.swing.JTextField DD4;
    private javax.swing.JTextField DE1;
    private javax.swing.JTextField DE2;
    private javax.swing.JTextField DE3;
    private javax.swing.JTextField E;
    private javax.swing.JPanel F11;
    private javax.swing.JPanel F12;
    private javax.swing.JPanel F13;
    private javax.swing.JPanel F2;
    private javax.swing.JPanel F3;
    private javax.swing.JPanel F4;
    private javax.swing.JPanel F5;
    private javax.swing.JTextArea Figura;
    private javax.swing.JTextField P;
    private javax.swing.JButton btnDibujarD;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables
}
