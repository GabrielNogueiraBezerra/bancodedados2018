package Models;

import java.util.ArrayList;

/**
 *
 * @author Gabriel
 */
public class Configuracao implements InterfaceObservable {

    private ArrayList<InterfaceObserver> observers;

    public Configuracao() {
        if (this.observers != null) {
            this.observers = new ArrayList<>();
        }
    }

    @Override
    public void incluir(InterfaceObserver observer) {
        if (observer != null) {
            this.observers.add(observer);
        }
    }

    @Override
    public void excluir(InterfaceObserver observer) {
        if (observer != null) {
            this.observers.remove(observer);
        }
    }

    @Override
    public void avisarObservers() {
        for (InterfaceObserver observer : this.observers) {
            if (observer != null) {
                observer.alterar();
            }
        }
    }

}
