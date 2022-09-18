

package CODIGO;
import INTERFAZ.Interfaz;
import jflex.exceptions.SilentExit;
/**
 *
 * @author chuy4
 */
public class ExecuteJFlex {
    public static void main(String omega[]) {
        String lexerFile = System.getProperty("user.dir") + "/src/CODIGO/lexer.flex",
                lexerFileColor = System.getProperty("user.dir") + "/src/CODIGO/lexer_Color.flex";
        try {
            jflex.Main.generate(new String[]{lexerFile, lexerFileColor});
        } catch (SilentExit ex) {
            System.out.println("Error al compilar/generar el archivo flex: " + ex);
        }
    }
}
