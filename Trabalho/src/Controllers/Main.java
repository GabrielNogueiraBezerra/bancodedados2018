package Controllers;

import Models.Configuracao;
import Views.FrmPrincipal;

/**
 *
 * @author Gabriel
 */
public class Main {

    private static FrmPrincipal principal;

    public static void main(String[] args) {
        try {
            Main.principal = new FrmPrincipal(new Configuracao());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
