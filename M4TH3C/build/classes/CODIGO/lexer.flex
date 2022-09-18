import compilerTools.Token;

%%
%class Lexer
%type Token
%line
%column
%{
    private Token token(String lexeme, String lexicalComp, int line, int column){
        return new Token(lexeme, lexicalComp, line+1, column+1);
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

{Funcion1} { return token(yytext(), "Palabra_Reservada_1", yyline, yycolumn); }
{Funcion2} { return token(yytext(), "Palabra_Reservada_2", yyline, yycolumn); }
{Funcion3} { return token(yytext(), "Palabra_Reservada_3", yyline, yycolumn); }
{Funcion4} { return token(yytext(), "Palabra_Reservada_4", yyline, yycolumn); }
{Funcion5} { return token(yytext(), "Palabra_Reservada_5", yyline, yycolumn); }

{Dato1} { return token(yytext(), "Palabra_Reservada_6", yyline, yycolumn); }
{Dato2} { return token(yytext(), "Palabra_Reservada_7", yyline, yycolumn); }
{Dato3} { return token(yytext(), "Palabra_Reservada_8", yyline, yycolumn); }
{Dato4} { return token(yytext(), "Palabra_Reservada_9", yyline, yycolumn); }
{Dato5} { return token(yytext(), "Palabra_Reservada_10", yyline, yycolumn); }
{Dato6} { return token(yytext(), "Palabra_Reservada_11", yyline, yycolumn); }

{Color1} { return token(yytext(), "Palabra_Reservada_12", yyline, yycolumn); }
{Color2} { return token(yytext(), "Palabra_Reservada_13", yyline, yycolumn); }
{Color3} { return token(yytext(), "Palabra_Reservada_14", yyline, yycolumn); }
{Color4} { return token(yytext(), "Palabra_Reservada_15", yyline, yycolumn); }
{Color5} { return token(yytext(), "Palabra_Reservada_16", yyline, yycolumn); }

{Figura1} { return token(yytext(), "Palabra_Reservada_17", yyline, yycolumn); }
{Figura2} { return token(yytext(), "Palabra_Reservada_18", yyline, yycolumn); }
{Figura3} { return token(yytext(), "Palabra_Reservada_19", yyline, yycolumn); }
{Figura4} { return token(yytext(), "Palabra_Reservada_20", yyline, yycolumn); }
{Figura5} { return token(yytext(), "Palabra_Reservada_21", yyline, yycolumn); }

{Metodo1} { return token(yytext(), "Palabra_Reservada_22", yyline, yycolumn); }

{IdentificadorVariable} { return token(yytext(), "Identificador_Variable", yyline, yycolumn); }
{IdentificadorCadena} { return token(yytext(), "Identificador_Cadena", yyline, yycolumn); }
{IdentificadorResultado} { return token(yytext(), "Identificador_Resultado", yyline, yycolumn); }
{IdentificadorMetodo} { return token(yytext(), "Identificador_Metodo", yyline, yycolumn); }

{SignoDeAgrupacion1} { return token(yytext(), "Signo_De_Agrupacion_1", yyline, yycolumn); }
{SignoDeAgrupacion2} { return token(yytext(), "Signo_De_Agrupacion_2", yyline, yycolumn); }
{SignoDeAgrupacion3} { return token(yytext(), "Signo_De_Agrupacion_3", yyline, yycolumn); }
{SignoDeAgrupacion4} { return token(yytext(), "Signo_De_Agrupacion_4", yyline, yycolumn); }
{SignoDeAgrupacion5} { return token(yytext(), "Signo_De_Agrupacion_5", yyline, yycolumn); }
{SignoDeAgrupacion6} { return token(yytext(), "Signo_De_Agrupacion_6", yyline, yycolumn); }

{NumeroEntero} { return token(yytext(), "Numero_Entero", yyline, yycolumn); }
{NumeroDecimal} { return token(yytext(), "Numero_Decimal", yyline, yycolumn); }

{OperadorDeAsignacion} { return token(yytext(), "Operador_De_Asignacion", yyline, yycolumn); }

{SignoDelimitadorDeSentencia} { return token(yytext(), "Delimitador_De_Sentencia", yyline, yycolumn); }

{OperadorAritmetico1} { return token(yytext(), "Operador_Aritmetico_Mas", yyline, yycolumn); }
{OperadorAritmetico2} { return token(yytext(), "Operador_Aritmetico_Menos", yyline, yycolumn); }

{Separador} { return token(yytext(), "Separador", yyline, yycolumn); }

{Error0} { return token(yytext(), "ERROR_0", yyline, yycolumn); }
{Error1} { return token(yytext(), "ERROR_1", yyline, yycolumn); }
{Error2} { return token(yytext(), "ERROR_2", yyline, yycolumn); }
{Error3} { return token(yytext(), "ERROR_3", yyline, yycolumn); }
{Error4} { return token(yytext(), "ERROR_4", yyline, yycolumn); }
{Error5} { return token(yytext(), "ERROR_5", yyline, yycolumn); }
{Error6} { return token(yytext(), "ERROR_6", yyline, yycolumn); }
{Error7} { return token(yytext(), "ERROR_7", yyline, yycolumn); }
{Error8} { return token(yytext(), "ERROR_8", yyline, yycolumn); }
{Error9} { return token(yytext(), "ERROR_9", yyline, yycolumn); }
{Error10} { return token(yytext(), "ERROR_10", yyline, yycolumn); }
{Error11} { return token(yytext(), "ERROR_11", yyline, yycolumn); }
{Error12} { return token(yytext(), "ERROR_12", yyline, yycolumn); }
{Error13} { return token(yytext(), "ERROR_13", yyline, yycolumn); }

. { return token(yytext(), "ERROR_#", yyline, yycolumn); }