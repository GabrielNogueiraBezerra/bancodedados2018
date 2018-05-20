package Models;

import java.sql.SQLException;

/**
 *
 * @author Gabriel
 * @modificado Williana
 */
public interface InterfaceManter {
    
    public void inserir()  throws ClassNotFoundException, SQLException;
    
    public void alterar()  throws ClassNotFoundException, SQLException;
    
    public void buscar(int codigo)  throws ClassNotFoundException, SQLException;
    
    public void excluir()  throws ClassNotFoundException, SQLException;
    
}
