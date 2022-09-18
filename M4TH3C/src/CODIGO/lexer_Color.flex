import compilerTools.TextColor;
import java.awt.Color;

%%

%class LexerColor
%type TextColor
%char
%{
    private TextColor textColor(long start, int size, Color color){
        return new TextColor((int) start, size, color);
    }
%}

/*Variables*/

CaracterNoValido = [!¡¿?"$"%_"-""*""/""|"]
SimboloError1 = [-+.:<>]
SimboloError2 = [-+.:#&<>]
SimboloError3 = [.,=()<>#{}+-;:&]
SimboloError4 = [.,=()<>#{}+-:&]
SimboloError6 = [,=()<>#{}+-:&]
SimboloError7 = [,=()<>#{}+-;:&]

Letra = [a-zA-Z]
Digito = [0-9]
Comilla = [']
Gato = [#]
Ampersand = [&]
Agrupacion1 = [(]
Agrupacion2 = [)]
Agrupacion3 = [<]
Agrupacion4 = [>]
Agrupacion5 = [{]
Agrupacion6 = [}]
Punto = [.]
Coma = [,]
Simbolo = [ .,=()<>#{}+-;:&]
Asignacion = [=]
Delimitador = [;]
Operador1 = [+]
Operador2 = [-]
DosPuntos = [:]


Sumar = [S][u][m][a][r]
Restar = [R][e][s][t][a][r]
Multiplicar = [M][u][l][t][i][p][l][i][c][a][r] 
Dividir = [D][i][v][i][d][i][r]
Mostrar = [M][o][s][t][r][a][r]

Entero = [E][n][t][e][r][o]
Decimal = [D][e][c][i][m][a][l]
Resultado = [R][e][s][u][l][t][a][d][o]
Cadena = [C][a][d][e][n][a]
Figura = [F][i][g][u][r][a]
Color = [C][o][l][o][r]

Rojo = [R][o][j][o]
Azul = [A][z][u][l]
Verde = [V][e][r][d][e]
Blanco = [B][l][a][n][c][o]
Negro = [N][e][g][r][o]

Cuadrado = [C][u][a][d][r][a][d][o]
Triangulo = [T][r][i][a][n][g][u][l][o]
Rectangulo = [R][e][c][t][a][n][g][u][l][o]
Rombo = [R][o][m][b][o]
Circulo = [C][i][r][c][u][l][o]

Metodo = [M][e][t][o][d][o]


Espacio = [[ ]|\n|\t|\f|\r]

/* -------- COMPONENTES LEXICOS -------*/

/*Identificador*/

IdentificadorVariable = {Letra}({Letra}|{Digito})*
IdentificadorCadena = {Comilla}({Letra}|{Digito})({Letra}|{Digito}|{Simbolo})*{Comilla}
IdentificadorResultado = {Gato}{Letra}({Letra}|{Digito})*
IdentificadorMetodo = {Ampersand}{Letra}({Letra}|{Digito})*

/*Signo de agrupacion*/

SignoDeAgrupacion1 = {Agrupacion1}
SignoDeAgrupacion2 = {Agrupacion2}
SignoDeAgrupacion3 = {Agrupacion3}
SignoDeAgrupacion4 = {Agrupacion4}
SignoDeAgrupacion5 = {Agrupacion5}
SignoDeAgrupacion6 = {Agrupacion6}

/*Numero*/

NumeroEntero = {Digito}({Digito})*
NumeroDecimal = {Digito}({Digito})*{Punto}{Digito}({Digito})* 

/*Operador de asignacion*/

OperadorDeAsignacion = {Asignacion}

/*Signo delimitador de sentencia*/

SignoDelimitadorDeSentencia = {Delimitador}

/*Operador aritmetico*/

OperadorAritmetico1 = {Operador1}
OperadorAritmetico2 = {Operador2}

/*Separador*/

Separador = {Coma}

/*Palabras reservadas*/

Funcion1 = {Sumar}
Funcion2 = {Restar}
Funcion3 = {Dividir}
Funcion4 = {Multiplicar}
Funcion5 = {Mostrar}

Dato1 = {Entero}
Dato2 = {Decimal}
Dato3 = {Resultado}
Dato4 = {Cadena}
Dato5 = {Figura}
Dato6 = {Color}

Color1 = {Rojo}
Color2 = {Azul}
Color3 = {Verde}
Color4 = {Blanco}
Color5 = {Negro}

Figura1 = {Cuadrado}
Figura2 = {Triangulo}
Figura3 = {Rectangulo}
Figura4 = {Rombo}
Figura5 = {Circulo}

Metodo1 = {Metodo}

/*ERRORES*/

Error0 = {CaracterNoValido}

Error1 = ({Digito}|{SimboloError1})({Letra})({Letra}|{Digito})*

Error2 = {Letra}({Letra}|{Digito}|{SimboloError2})({Letra}|{Digito}|{SimboloError2})*

Error3 = ({Comilla}({CaracterNoValido}|{SimboloError3})({Letra}|{Digito}|{Simbolo}|{CaracterNoValido})({Letra}|{Digito}|{Simbolo}|{CaracterNoValido})*{Comilla})|
         ({Comilla}({CaracterNoValido}|{SimboloError3})({Letra}|{Digito}|{Simbolo})({Letra}|{Digito}|{Simbolo})*{Comilla})|
         ({Comilla}({Digito}|{Letra})({Letra}|{Digito}|{Simbolo}|{CaracterNoValido})({Letra}|{Digito}|{Simbolo}|{CaracterNoValido})*{Comilla})

Error4 = {Comilla}{Espacio}{Espacio}*{Comilla}|{Comilla}{Comilla}

Error5 = {Gato}{Letra}({Letra}|{Digito}|{SimboloError2}|{CaracterNoValido})({Letra}|{Digito}|{SimboloError2}|{CaracterNoValido})*|
         {Gato}({Digito}|{SimboloError4}|{CaracterNoValido})({Letra}|{Digito})({Letra}|{Digito})*|
         {Gato}({Digito}|{SimboloError4}|{CaracterNoValido})({Letra}|{Digito}|{SimboloError4}|{CaracterNoValido})({Letra}|{Digito}|{SimboloError4}|{CaracterNoValido})*

Error6 = {Gato}

Error7 = {Ampersand}{Letra}({Letra}|{Digito}|{SimboloError2}|{CaracterNoValido})({Letra}|{Digito}|{SimboloError2}|{CaracterNoValido})*|
         {Ampersand}({Digito}|{SimboloError4}|{CaracterNoValido})({Letra}|{Digito})({Letra}|{Digito})*|
         {Ampersand}({Digito}|{SimboloError4}|{CaracterNoValido})({Letra}|{Digito}|{SimboloError4}|{CaracterNoValido})({Letra}|{Digito}|{SimboloError4}|{CaracterNoValido})*

Error8 = {Ampersand}

Error9 = {Digito}({Letra}|{CaracterNoValido}|{SimboloError6})({Letra}|{CaracterNoValido}|{SimboloError6})*|
         {Digito}({Digito})*{Punto}({Letra}|{CaracterNoValido}|{SimboloError6})({Letra}|{CaracterNoValido}|{SimboloError6})*|
         {Digito}({Digito})*({Letra}|{CaracterNoValido}|{SimboloError6})({Letra}|{CaracterNoValido}|{SimboloError6})*|
         {Digito}(({Digito})*({Letra}|{CaracterNoValido}|{SimboloError6})({Letra}|{CaracterNoValido}|{SimboloError6})*)(({Digito})*({Letra}|{CaracterNoValido}|{SimboloError6})({Letra}|{CaracterNoValido}|{SimboloError6})*)*|
         {Digito}({Digito})*{Punto}{Digito}({Digito})*({Letra}|{CaracterNoValido}|{SimboloError6})({Letra}|{CaracterNoValido}|{SimboloError6})*|
         {Digito}({Digito})*{Punto}{Digito}(({Digito})*({Letra}|{CaracterNoValido}|{SimboloError6})({Letra}|{CaracterNoValido}|{SimboloError6})*)(({Digito})*({Letra}|{CaracterNoValido}|{SimboloError6})({Letra}|{CaracterNoValido}|{SimboloError6})*)*|
         {Digito}({Digito})*({Letra}|{CaracterNoValido}|{SimboloError6})({Letra}|{CaracterNoValido}|{SimboloError6})*{Punto}{Digito}({Digito})*
         {Digito}(({Digito})*({Letra}|{CaracterNoValido}|{SimboloError6})({Letra}|{CaracterNoValido}|{SimboloError6})*)(({Digito})*({Letra}|{CaracterNoValido}|{SimboloError6})({Letra}|{CaracterNoValido}|{SimboloError6})*)*{Punto}{Digito}({Digito})*

Error10 = {Digito}({Digito})*{Punto}

Error11 = {Punto}{Digito}({Digito})* 

Error12 = {Punto}({Digito}|{Punto})({Digito}|{Punto})*

Error13 = ({Letra}|{SimboloError7}|{CaracterNoValido}){Digito}({Digito})*|
          ({Letra}|{SimboloError7}|{CaracterNoValido}){Digito}({Digito})*{Punto}{Digito}({Digito})*

%%

{Espacio} {/*Ignorar*/}

{Color1} { return textColor(yychar, yylength(), new Color(255, 0, 0)); }
{Color2} { return textColor(yychar, yylength(), new Color(0, 0, 255)); }
{Color3} { return textColor(yychar, yylength(), new Color(0, 255, 0)); }
{Color4} { return textColor(yychar, yylength(), new Color(255, 255, 210)); }
{Color5} { return textColor(yychar, yylength(), new Color(0, 0, 0)); }

{Funcion1} { return textColor(yychar, yylength(), new Color(182, 50, 159)); }
{Funcion2} { return textColor(yychar, yylength(), new Color(182, 50, 159)); }
{Funcion3} { return textColor(yychar, yylength(), new Color(182, 50, 159)); }
{Funcion4} { return textColor(yychar, yylength(), new Color(182, 50, 159)); }
{Funcion5} { return textColor(yychar, yylength(), new Color(182, 50, 159)); }

{Dato1} { return textColor(yychar, yylength(), new Color(221, 200, 25)); }
{Dato2} { return textColor(yychar, yylength(), new Color(221, 200, 25)); }
{Dato3} { return textColor(yychar, yylength(), new Color(221, 200, 25)); }
{Dato4} { return textColor(yychar, yylength(), new Color(221, 200, 25)); }
{Dato5} { return textColor(yychar, yylength(), new Color(221, 200, 25)); }
{Dato6} { return textColor(yychar, yylength(), new Color(221, 200, 25)); }

{Figura1} { return textColor(yychar, yylength(), new Color(72, 224, 219)); }
{Figura2} { return textColor(yychar, yylength(), new Color(72, 224, 219)); }
{Figura3} { return textColor(yychar, yylength(), new Color(72, 224, 219)); }
{Figura4} { return textColor(yychar, yylength(), new Color(72, 224, 219)); }
{Figura5} { return textColor(yychar, yylength(), new Color(72, 224, 219)); }

{Metodo1} { return textColor(yychar, yylength(), new Color(182, 50, 159)); }

{IdentificadorCadena} { return textColor(yychar, yylength(), new Color(255, 50, 233)); }
{IdentificadorVariable} { /*Ignorar*/ }
{IdentificadorResultado} { /*Ignorar*/}
{IdentificadorMetodo} { /*Ignorar*/ }

{SignoDeAgrupacion1} { /*Ignorar*/ }
{SignoDeAgrupacion2} { /*Ignorar*/ }
{SignoDeAgrupacion3} { /*Ignorar*/ }
{SignoDeAgrupacion4} { /*Ignorar*/ }
{SignoDeAgrupacion5} { /*Ignorar*/ }
{SignoDeAgrupacion6} { /*Ignorar*/ }

{NumeroEntero} { /*Ignorar*/ }
{NumeroDecimal} { /*Ignorar*/ }

{OperadorDeAsignacion} { /*Ignorar*/ }

{SignoDelimitadorDeSentencia} { /*Ignorar*/ }

{OperadorAritmetico1} { return textColor(yychar, yylength(), new Color(255, 0, 0)); }
{OperadorAritmetico1} { return textColor(yychar, yylength(), new Color(0, 0, 255)); }

{Separador} { /*Ignorar*/ }

{Error0} { return textColor(yychar, yylength(), new Color(255, 95, 109)); }
{Error1} { return textColor(yychar, yylength(), new Color(255, 95, 109)); }
{Error2} { return textColor(yychar, yylength(), new Color(255, 95, 109)); }
{Error3} { return textColor(yychar, yylength(), new Color(255, 95, 109)); }
{Error4} { return textColor(yychar, yylength(), new Color(255, 95, 109)); }
{Error5} { return textColor(yychar, yylength(), new Color(255, 95, 109)); }
{Error6} { return textColor(yychar, yylength(), new Color(255, 95, 109)); }
{Error7} { return textColor(yychar, yylength(), new Color(255, 95, 109)); }
{Error8} { return textColor(yychar, yylength(), new Color(255, 95, 109)); }
{Error9} { return textColor(yychar, yylength(), new Color(255, 95, 109)); }
{Error10} { return textColor(yychar, yylength(), new Color(255, 95, 109)); }
{Error11} { return textColor(yychar, yylength(), new Color(255, 95, 109)); }
{Error12} { return textColor(yychar, yylength(), new Color(255, 95, 109)); }
{Error13} { return textColor(yychar, yylength(), new Color(255, 95, 109)); }

. { return textColor(yychar, yylength(), new Color(255, 95, 109)); }