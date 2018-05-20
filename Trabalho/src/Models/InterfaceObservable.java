package Models;

/**
 *
 * @author Gabriel
 */
public interface InterfaceObservable {

    public void incluir(InterfaceObserver observer);

    public void excluir(InterfaceObserver observer);

    public void avisarObservers();

}
